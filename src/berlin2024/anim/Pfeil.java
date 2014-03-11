package berlin2024.anim;


import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import berlin2024.AnimatorItf;
import berlin2024.Main;

public class Pfeil implements AnimatorItf {
	
	private Component comp;
	Image pfeil;
	
	// Alexanderplatz mit erwartetem Größenverlust von 500
	int width = 2284-500;
	int height = 1370-500;
	
	public Pfeil(){
		try {
			pfeil = ImageIO.read(Main.class.getClassLoader().getResourceAsStream("Pfeil.jpg"));
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
		// TODO Auto-generated method stub

	}

	@Override
	public void paint(Graphics g) {
		//g.setColor(Color.GREEN);
		//for(int i=0; i<60;i++){
			//g.drawLine(300-(i*i), 300, 300-(i*i), 265);
		//	g.drawOval(270+i, 280+i, 121-(2*i), 121-(2*i));
		//}

		int posX = 100+ (int)(Math.random() * (width - 100));
		int posY = 100+ (int)(Math.random() * (height - 100));
		g.drawImage(pfeil,posX,posY,100,100,comp);
	}

	

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return "Alexanderplatz";
	}

	@Override
	public String getLyrics() {
		// TODO Auto-generated method stub
		return "wow such amazing very city wow";
	}

}
