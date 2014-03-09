package berlin2024;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JFrame {

	MyPanel imagePanel = new MyPanel();
	int imageIndex = 0;
	int animatorIndex = 0;
	Thread animatorThread = null;
	ArrayList<Image> images = new ArrayList<Image>();
	ArrayList<String> titles = new ArrayList<String>();
	ArrayList<AnimatorItf> animators = new ArrayList<AnimatorItf>();
	Font font = new Font("Arial", Font.BOLD, 18);

	private Main() {
		// empty
	}

	private void init(String imagesFile, String animFile) {

		// io stuff
		BufferedReader reader;
		InputStream in;
		try {
			// load images
			in = Main.class.getClassLoader().getResourceAsStream(imagesFile);
			reader = new BufferedReader(new InputStreamReader(in));

			while (true) {
				String line = reader.readLine();
				if (line == null) {
					break;
				}
				images.add(ImageIO.read(Main.class.getClassLoader().getResourceAsStream(line)));
				titles.add(line.split("\\.")[0]);
			}

			// add null as first animator, for no animation
			animators.add(null);

			// load animators
			in = Main.class.getClassLoader().getResourceAsStream(animFile);
			reader = new BufferedReader(new InputStreamReader(in));

			while (true) {
				String line = reader.readLine();
				if (line == null) {
					break;
				}
				animators.add((AnimatorItf) Class.forName(line).newInstance());
				System.out.println(line);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		// set layout
		getContentPane().setLayout(new GridBagLayout());

		// add buttons
		for (int y = 0; y < titles.size(); ++y) {
			JButton btn = new MyButton(y);
			getContentPane().add(
					btn,
					new GridBagConstraints(0, y, 1, 1, 0.0, 0.5, GridBagConstraints.WEST, GridBagConstraints.BOTH,
							new Insets(0, 0, 0, 0), 0, 0));
			System.out.println("add button");
		}

		// add image panel
		getContentPane().add(
				imagePanel,
				new GridBagConstraints(1, 0, images.size(), images.size(), 0.5, 0.5, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

	}
	public static void main(String[] args) {
		Main instance = new Main();
		instance.init("images.txt", "animators.txt");
		instance.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		instance.setTitle("Berlin 2024");
		instance.pack();
		instance.setVisible(true);
	}

	class MyButton extends JButton implements ActionListener {

		private int index;

		MyButton(int i) {
			super(titles.get(i));
			index = i;
			addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			// stop the animator, if any
			if (animatorIndex > 0) {
				animators.get(animatorIndex).setComponent(null);
			}

			// check, if a new image was selected
			if (imageIndex == index) {

				// image stays the same. so toggle animator
				boolean success = false;
				for (int i = animatorIndex + 1; i < animators.size(); ++i) {
					AnimatorItf anim = animators.get(i);

					// search for an animator fitting to our image
					if (anim.getTitle().equals(titles.get(imageIndex))) {
						animatorIndex = i;
						anim.setComponent(imagePanel);
						success = true;
						break;
					}
				}

				// disable the animator, if we haven't found one
				if (!success) {
					animatorIndex = 0;
				}
			}
			else {
				// new image. reset animator and update image
				animatorIndex = 0;
				imageIndex = index;
			}

			// finally repaint
			imagePanel.repaint();
		}

	}

	class MyPanel extends JPanel {
		@Override
		public void paint(Graphics g) {

			// draw background image
			g.drawImage(images.get(imageIndex), 0, 0, getWidth(), getHeight(), this);

			// ask the animator to paint, if we have any
			if (animatorIndex != 0) {
				AnimatorItf anim = animators.get(animatorIndex);
				anim.paint(g);
				g.setColor(Color.CYAN);
				g.setFont(font);
				String lyrics = anim.getLyrics();
				int len = g.getFontMetrics().stringWidth(lyrics);
				g.drawString(lyrics, (getWidth() - len) / 2, getHeight() - 30);
			}

//			System.out.println("repaint");
		}

		@Override
		public Dimension getPreferredSize() {
			int width = images.get(imageIndex).getWidth(this);
			int height = images.get(imageIndex).getHeight(this);
			return new Dimension(width, height);
		}

	}

}
