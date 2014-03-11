package berlin2024.anim;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import berlin2024.AnimatorItf;
import berlin2024.Main;

public class Crash implements AnimatorItf {
	
    
    private Image planeImage;
    private long startTime;
    
    private Component comp;
    private Thread thread;
    
    int bikerWidth = 40;
    int bikerHeight = 30;
	
    final int mapWidth = 1000;
    final int mapHeight = 700;
    final long maxFrame = 10000;
	
    final int[] xPosArray = {1000, 610};
    final int[] yPosArray = {0, 100};
    final long[] frameArray = {0, 2000, maxFrame, Long.MAX_VALUE};
	
    
    public Crash() {
		
        // load biker image
        try {
            planeImage = ImageIO.read(Main.class.getClassLoader().getResourceAsStream("Plane.jpg"));
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
    public void setComponent(Component c) {
		
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
    public void paint(Graphics g) {
		
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
        g.drawImage(planeImage, (int) (xPos * xScale), (int) (yPos * yScale), (int) (bikerWidth * xScale),
					(int) (bikerHeight * yScale), comp);
    }
	
    @Override
    public String getTitle() {
        // TODO Auto-generated method stub
        return "Alexanderplatz";
    }
	
    @Override
    public String getLyrics() {
        return "Shot through the heart \n" +
		"And you're to blame";
    }
	
}
