package berlin2024.anim;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import berlin2024.AnimatorItf;

public class haushaus implements AnimatorItf {

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
		g.setColor(Color.BLACK);
		g.drawLine(700,700,700,800);
		g.drawLine(700,800,800,800);
		g.drawLine(800,800,800,700);
		g.drawLine(800,700,700,700);
		g.drawLine(700,700,750,650);
		g.drawLine(750,650,800,700);
	}

	@Override
	public String getTitle() {
		return "Tempelhof";
	}

	@Override
	public String getLyrics() {
		return "haushaushaus";
	}

}
