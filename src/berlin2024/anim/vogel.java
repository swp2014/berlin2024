package berlin2024.anim;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import berlin2024.AnimatorItf;
import berlin2024.Main;

public class vogel implements AnimatorItf {

	long startTime;
	Thread thread = null;
	Component comp = null;
	Image birdImg = null;
	int birdWidth = 50;
	int birdHeight = 40;

	final int mapWidth = 1000;
	final int mapHeight = 700;
	final long maxFrame = 3000;

	final int[] xPosArray = {400, 550, 550};
	final int[] yPosArray = {120, 120, 400};
	final long[] frameArray = {0, 2500, maxFrame, Long.MAX_VALUE};

	public vogel() {

		// load bird image
		try {
			birdImg = ImageIO.read(Main.class.getClassLoader().getResourceAsStream("vogel.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

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
		return "Alexanderplatz";
	}

	@Override
	public synchronized void paint(Graphics g) {

		// ensure, that we have a component
		if (comp == null) {
			return;
		}

		// adjust start time so that curTime - startTime is in the interval
		long curFrame = (System.currentTimeMillis() - startTime) % maxFrame;

		// find correct interval
		int index = 1;
		while (curFrame >= frameArray[index]) {
			index++;
		}
		assert index < frameArray.length;

		// compute location for the bird
		int xPos = 0;
		int yPos = 0;

		// first check, whether we are exactly on a way point
		if (curFrame == frameArray[index - 1]) {
			xPos = xPosArray[index - 1];
			yPos = yPosArray[index - 1];
		}
		else {
			// interpolate position
			double d = (double) (curFrame - frameArray[index - 1]) / (frameArray[index] - frameArray[index - 1]);
			xPos = (int) (xPosArray[index - 1] + d * (xPosArray[index] - xPosArray[index - 1]));
			yPos = (int) (yPosArray[index - 1] + d * (yPosArray[index] - yPosArray[index - 1]));
		}

		// get width and height
		int width = Math.max(comp.getWidth(), 1);
		int height = Math.max(comp.getHeight(), 1);

		// compute scale factors
		double xScale = width / (double) mapWidth;
		double yScale = height / (double) mapHeight;
		g.drawImage(birdImg, (int) (xPos * xScale), (int) (yPos * yScale), (int) (birdWidth * xScale),
				(int) (birdHeight * yScale), comp);
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
			thread = new Thread(this);
			thread.start();
		}
	}

	@Override
	public String getLyrics() {
		return "Flieg Vogel flieeeeeg!";
	}

}

