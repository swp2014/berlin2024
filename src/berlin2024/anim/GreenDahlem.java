package berlin2024.anim;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import berlin2024.AnimatorItf;

public class GreenDahlem implements AnimatorItf {

	long startTime;
	Thread t = null;
	Component comp = null;

	@Override
	public void run() {
		startTime = System.currentTimeMillis();
		while (comp != null) {
			comp.repaint();
			try {
				Thread.sleep(40);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public String getTitle() {
		return "Dahlem";
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.GREEN);
		int frame = (int) (System.currentTimeMillis()-startTime) / 10 % 800;
		g.fillOval(0, 0, frame, 300);
	}

	@Override
	public void setComponent(Component c) {

		// check, if anything to do
		if (c == comp) {
			return;
		}

		// set component
		comp = c;

		// notify the thread, so it can stop
//		if (t != null) {
//			t.notify();
//		}

		if (comp != null) {
			t = new Thread(this);
			t.start();
		}
	}

	@Override
	public String getLyrics() {
		return "hier so'n Beispieltext. Kann man irgendwas reinschreiben";
	}

}
