package berlin2024.anim;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

import berlin2024.AnimatorItf;
import berlin2024.Main;

public class Home implements AnimatorItf {
	private Component comp;
	private Image home;

	public Home() {
		// load biker image
		try {
			home = ImageIO.read(Main.class.getClassLoader().getResourceAsStream("Home.png"));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
	}

	@Override
	public void setComponent(Component c) {
		comp = c;
	}

	@Override
	public void paint(Graphics g) {
		Rectangle c = g.getClipBounds();
		int width = c.width;
		int height = c.height;
		g.drawImage(this.home, (int) (Math.random() * width), (int) (Math.random() * height), 64, 64, this.comp);
	}

	@Override
	public String getTitle() {
		return "Tegel";
	}

	@Override
	public String getLyrics() {
		return "A random house in Tegel.";
	}

}
