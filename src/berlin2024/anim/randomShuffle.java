package berlin2024.anim;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;

import berlin2024.AnimatorItf;

public class randomShuffle implements AnimatorItf {
	
	long startTime;
	Thread thread = null;
	Component comp = null;
	Image background = null;
	int maxTilesize = 40;
	int minTilesize = 5;

	final int mapWidth = 1000;
	final int mapHeight = 700;
	final long maxFrame = 10000;

	public randomShuffle() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setComponent(Component c) {
		// TODO Auto-generated method stub

	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLyrics() {
		// TODO Auto-generated method stub
		return null;
	}

}
