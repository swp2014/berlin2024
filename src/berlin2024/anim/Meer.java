package berlin2024.anim;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import berlin2024.AnimatorItf;

public class Meer implements AnimatorItf {
	
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
		g.setColor(Color.BLUE);
		g.fillOval(400, 200, 450, 450);

	}

	@Override
	public String getTitle() {
		return "Tempelhof";
	}

	@Override
	public String getLyrics() {
		return "splash....!";
	}

}
