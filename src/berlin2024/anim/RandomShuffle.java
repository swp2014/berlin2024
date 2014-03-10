package berlin2024.anim;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import berlin2024.AnimatorItf;
import berlin2024.Main;

public class RandomShuffle implements AnimatorItf {

	long startTime;
	Thread thread = null;
	Component comp = null;
	BufferedImage backgroundImage = null;
	int maxTilesize = 100;
	int minTilesize = 40;

	final int mapWidth = 1000;
	final int mapHeight = 700;
	final long maxFrame = 10000;
	Point mouse;

	public RandomShuffle() {
		// load city image for later subimaging
		try {
			backgroundImage = ImageIO.read(Main.class.getClassLoader().getResourceAsStream("Alexanderplatz.png"));
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
	
	long map(long x, long in_min, long in_max, long out_min, long out_max)
	{
	  return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
	}

	@Override
	public void paint(Graphics g) {
		// ensure, that we have a component
		if (comp == null) {
			return;
		}
		mouse = MouseInfo.getPointerInfo().getLocation();
		for (int i = 0; i <= 50 + (int) Math.random() * 2000; i++) {
			// get a random tile as subimage
			float sizeFactor = map(mouse.x, 0, backgroundImage.getWidth(), 1, 20);
			int randomTileSize = (int) (Math.random() * minTilesize * sizeFactor);
			int randomX = (int) (Math.random() * (backgroundImage.getWidth() - randomTileSize));
			int randomY = (int) (Math.random() * (backgroundImage.getHeight() - randomTileSize));
			BufferedImage randomTile = backgroundImage.getSubimage(randomX,
					randomY, randomTileSize, randomTileSize);
			// draw random tile on another random location somewhere around the
			// mouse location
			randomX = (int) (Math.random() * (backgroundImage.getWidth() - randomTileSize));
			randomY = (int) (Math.random() * (backgroundImage.getHeight() - randomTileSize));

			g.drawImage(randomTile, randomX, randomY, randomTileSize,
					randomTileSize, comp);
		}
	}

	@Override
	public String getTitle() {
		return "Alexanderplatz";

	}

	@Override
	public String getLyrics() {
		return "bewege cursor horizontal um Kachelgrš§e zu Šndern.";
	}

}
