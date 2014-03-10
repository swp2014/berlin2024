package berlin2024.anim;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import berlin2024.Main;

public class BungeeJump implements berlin2024.AnimatorItf {

	/**
	 * @param args
	 */

	long startTime;
	Thread thread = null;
	Component comp = null;
	Image jumperImg = null;
	int jumperWidth = 27;
	int jumperHeight = 85;

	final int mapWidth = 1000;
	final int mapHeight = 700;
	final long maxFrame = 700;

	final int[] xPosArray = {560, 540, 550};
	final int[] yPosArray = {150, 175, 320};
	final long[] frameArray = {0, 300, maxFrame, Long.MAX_VALUE};

	public BungeeJump() {

		// load jumper image
		try {
			jumperImg = ImageIO.read(Main.class.getClassLoader().getResourceAsStream("jumper.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

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
	public synchronized void setComponent(Component c) {
		// TODO Auto-generated method stub
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

		// compute location for the biker
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
		g.drawImage(jumperImg, (int) (xPos * xScale), (int) (yPos * yScale), (int) (jumperWidth * xScale),
				(int) (jumperHeight * yScale), comp);
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return "Alexanderplatz";
	}

	@Override
	public String getLyrics() {
		// TODO Auto-generated method stub
		return "Oh shit! Ich hab' das Seil n icht befestigt!!! - AAAAAAAHHHHHHHHH!!!! *crash*";
	}

}
