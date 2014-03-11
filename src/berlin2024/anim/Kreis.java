package berlin2024.anim;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import berlin2024.AnimatorItf;

public class Kreis implements AnimatorItf {

	Component comp;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
	}

	@Override
	public void setComponent(Component c) {
		// TODO Auto-generated method stub
		comp = c;
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.RED);
		g.fillOval(200, 200, 400, 300);
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return "Dahlem";
	}

	@Override
	public String getLyrics() {
		// TODO Auto-generated method stub
		return "round";
	}

}
