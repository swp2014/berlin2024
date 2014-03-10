package berlin2024.anim;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import berlin2024.AnimatorItf;

public class Explosion implements AnimatorItf {

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
		g.setColor(Color.PINK);
		g.drawOval(8, 8, 400, 300);
	}

	@Override
	public String getTitle() {
		return "Tegel";
	}

	@Override
	public String getLyrics() {
		return "Booooooooooooooooooooooooooooooooooooooooooooom !";
	}

}
