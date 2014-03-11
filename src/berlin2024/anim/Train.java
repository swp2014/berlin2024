package berlin2024.anim;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import berlin2024.AnimatorItf;
import berlin2024.Main;

import java.util.Random;

public class Train implements AnimatorItf {


	long startTime;
	Thread thread = null;
	Component comp = null;
	Image trainImg = null;
	int trainWidth = 48;
	int trainHeight = 48;

	final int mapWidth = 1000;
	final int mapHeight = 700;
	final long maxFrame = 10000;

	final int[] xPosArray1 = {1000, 760, 520};
	final int[] xPosArray2 = {520,220,0};
	final int[] yPosArray1 = {590, 250, 200};
	final int[] yPosArray2 = {200, 270, 530};
	int direction = 0;
	final long[] frameArray = {0, 2000, maxFrame, Long.MAX_VALUE};

	public Train() {
		
		// load train image
				try {
					trainImg = ImageIO.read(Main.class.getClassLoader().getResourceAsStream("zug.png"));
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
	                Thread.sleep( 50 );
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
			}
	}

	@Override
	public void setComponent(Component c) {
		Random dir = new Random();
		// check, if anything to do
		if (c == comp) {
			return;
		}

		// set component
		comp = c;

		// start a new thread, if necessary
		// zuerst die Fahrtrichtung ermitteln
		direction = dir.nextInt(2);
		
		if (comp != null) {
			thread = new Thread(this);
			thread.start();
			
		}
	}

	@Override
	public void paint(Graphics g) {
		if (direction == 1){
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
	            xPos = xPosArray1[index - 1];
	            yPos = yPosArray1[index - 1];
	        }
	        else {
	            // interpolate position
	            double d = (double) (curFrame - frameArray[index - 1]) / (frameArray[index] - frameArray[index - 1]);
	            xPos = (int) (xPosArray1[index - 1] + d * (xPosArray1[index] - xPosArray1[index - 1]));
	            yPos = (int) (yPosArray1[index - 1] + d * (yPosArray1[index] - yPosArray1[index - 1]));
	        }
			
	        // get width and height
	        int width = Math.max(comp.getWidth(), 1);
	        int height = Math.max(comp.getHeight(), 1);
			
	        // compute scale factors
	        double xScale = width / (double) mapWidth;
	        double yScale = height / (double) mapHeight;
	        g.drawImage(trainImg, (int) (xPos * xScale), (int) (yPos * yScale), (int) (trainWidth * xScale),
						(int) (trainHeight * yScale), comp);
		} else {
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
	            xPos = xPosArray2[index - 1];
	            yPos = yPosArray2[index - 1];
	        }
	        else {
	            // interpolate position
	            double d = (double) (curFrame - frameArray[index - 1]) / (frameArray[index] - frameArray[index - 1]);
	            xPos = (int) (xPosArray2[index - 1] + d * (xPosArray2[index] - xPosArray2[index - 1]));
	            yPos = (int) (yPosArray2[index - 1] + d * (yPosArray2[index] - yPosArray2[index - 1]));
	        }
			
	        // get width and height
	        int width = Math.max(comp.getWidth(), 1);
	        int height = Math.max(comp.getHeight(), 1);
			
	        // compute scale factors
	        double xScale = width / (double) mapWidth;
	        double yScale = height / (double) mapHeight;
	        g.drawImage(trainImg, (int) (xPos * xScale), (int) (yPos * yScale), (int) (trainWidth * xScale),
						(int) (trainHeight * yScale), comp);
		}
	        
		}

	@Override
	public String getTitle() {
		return "Hauptbahnhof";
	}

	@Override
	public String getLyrics() {
		return "Der Hauptbahnhof in Aktion (Fahrtrichtung zufällig)";
	}

}
