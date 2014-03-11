package berlin2024.anim;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import berlin2024.AnimatorItf;
import berlin2024.Main;

public class Godzilla implements AnimatorItf {

	private Component comp;
	Image godzi=null;
	
	public Godzilla (){
		try {
			godzi = ImageIO.read(Main.class.getClassLoader().getResourceAsStream("godzilla.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
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
		g.drawImage(godzi, 600, 400, 128, 128, comp);

	}

	@Override
	public String getTitle() {
		return "Hauptbahnhof";
	}

	@Override
	public String getLyrics() {
		return "AAHHHHHHHH, es ist Godzilla!!!!!";
	}

}
