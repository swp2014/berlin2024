package berlin2024;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
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
	ArrayList<Image> images = new ArrayList<Image>();
	ArrayList<String> titles = new ArrayList<String>();

	private Main() {
		// empty
	}

	private void init(String fileName) {

		// load images
		try {
			InputStream in = Main.class.getClassLoader().getResourceAsStream(fileName);
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));

			while (true) {
				String line = reader.readLine();
				if (line == null) {
					break;
				}
				images.add(ImageIO.read(Main.class.getClassLoader().getResourceAsStream(line)));
				System.out.println(line);
				titles.add(line.split("\\.")[0]);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		// load animators
		
		
		// set layout
		getContentPane().setLayout(new GridBagLayout());

		// add buttons
		for (int y = 0; y < titles.size(); ++y) {
			JButton btn = new MyButton(y);
			getContentPane().add(
					btn,
					new GridBagConstraints(0, y, 1, 1, 0.0, 0.5, GridBagConstraints.WEST,
							GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
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
		instance.init("images.txt");
		instance.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
			imageIndex = index;
			imagePanel.repaint();
		}

	}

	class MyPanel extends JPanel {
		@Override
		public void paint(Graphics g) {
			g.drawImage(images.get(imageIndex), 0, 0, getWidth(), getHeight(), this);
			System.out.println("repaint");
		}
		
		@Override
		public Dimension getPreferredSize() {
			int width = images.get(imageIndex).getWidth(this);
			int height = images.get(imageIndex).getHeight(this);
			return new Dimension(width, height);
		}

	}

}
