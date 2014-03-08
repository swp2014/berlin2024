package berlin2024.anim;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import berlin2024.AnimatorItf;

public class GreenDahlem implements AnimatorItf {

	// we count the number of instances that we created
	static int instanceCounter = 0;

	long startTime;
	Thread t = null;
	Component comp = null;
	int instanceID;

	public GreenDahlem() {
		instanceID = instanceCounter++;
	}

	@Override
	public void run() {
		startTime = System.currentTimeMillis();
		while (comp != null) {
			comp.repaint();
			try {
				Thread.sleep(200);
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
	public synchronized void paint(Graphics g) {

		// ensure, that we have a component
		if (comp == null) {
			return;
		}

		// get width and height
		int width = Math.max(comp.getWidth(), 1);
		int height = Math.max(comp.getHeight(), 1);

		g.setXORMode(instanceID == 0 ? Color.GREEN : Color.lightGray);
		int ballWidth = (int) (((System.currentTimeMillis() - startTime) / 10 % width) * 0.8);
		int ballHeight = (int) (height * (3 + Math.random()) / 4);
		g.fillOval((width - ballWidth) / 2, (height - ballHeight) / 2, ballWidth, ballHeight);
		g.setPaintMode();
	}

	@Override
	public synchronized void setComponent(Component c) {

		// check, if anything to do
		if (c == comp) {
			return;
		}

		// set component
		comp = c;

		// start a new thread, if necessary
		if (comp != null) {
			t = new Thread(this);
			t.start();
		}
	}

	@Override
	public String getLyrics() {
		return "This is instance #" + instanceID + " of green Dahlem";
	}

}
