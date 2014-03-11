package berlin2024.anim;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import berlin2024.AnimatorItf;
import berlin2024.Main;

public class KingAlex implements AnimatorItf {

	Image kingkong = null;
	private Component comp;
	
	public KingAlex() {
		

			// load biker image
			try {
				kingkong = ImageIO.read(Main.class.getClassLoader().getResourceAsStream("king-kong-fans.jpg"));
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
		//g.drawImage(kingkong, 0, 0);
		g.drawImage(kingkong, 1050, 130, 50, 50,comp);
	}

	@Override
	public String getTitle() {
		
		return "Alexanderplatz";
	}

	@Override
	public String getLyrics() {
		// TODO Auto-generated method stub
		return "hier steht irgendwas";
	}

}
