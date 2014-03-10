package berlin2024.anim;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import berlin2024.AnimatorItf;
import berlin2024.Main;

import berlin2024.AnimatorItf;

public class Baum implements AnimatorItf {
	
	private Component comp;
	Image tree = null;
	
	public Baum(){
		try {
			tree = ImageIO.read(Main.class.getClassLoader().getResourceAsStream("Tree.jpeg"));
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
		
		g.drawImage(tree,300,280,100,100,comp);
	}

	

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return "Alexanderplatz";
	}

	@Override
	public String getLyrics() {
		// TODO Auto-generated method stub
		return "Oh, Ein Strauch...";
	}

}
