package berlin2024.anim;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import berlin2024.AnimatorItf;
import berlin2024.Main;

public class Zirkus implements AnimatorItf {

	private Component comp;
	Image zirkus=null;

	public Zirkus (){
		try {
			zirkus = ImageIO.read(Main.class.getClassLoader().getResourceAsStream("zirkuszelt.jpg"));
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
		g.drawImage(zirkus, 550, 450, 100, 100, comp);

	}

	@Override
	public String getTitle() {
		return "Tempelhof";
	}

	@Override
	public String getLyrics() {
		return "Kinder, der Zirkus ist in der Stadt!";
	}

}
