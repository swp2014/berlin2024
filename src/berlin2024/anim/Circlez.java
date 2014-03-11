package berlin2024.anim;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import berlin2024.AnimatorItf;

public class Circlez implements AnimatorItf {

	private Component comp;
	
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
		g.setColor(Color.CYAN);
		Random Rand = new Random();
		for (int i = 1; i <= Rand.nextInt(1000); ++i){
			g.drawOval(Rand.nextInt(width), Rand.nextInt(height), Rand.nextInt(width), Rand.nextInt(height));
		}
		

	}

	@Override
	public String getTitle() {
		return "Tegel";
	}

	@Override
	public String getLyrics() {
		return "Zufällig viele, zufällig verteilte, zufällig große Ellipsen";
	}

}
