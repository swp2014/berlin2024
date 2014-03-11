package berlin2024.anim;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;

import berlin2024.AnimatorItf;

public class BigMoney implements AnimatorItf {

	Component comp = null;
	int width;
	int height;
	
	int max_money = 40;
	int id_money = 0;
	
	int[] moneyX = new int[max_money];
	int[] moneyY = new int[max_money];
	
	Font myFont = new Font(" TimesRoman", Font.BOLD, 30);
	
	Thread thread;
	
	@Override
	public void run() {
		while (comp != null) {
			moneyX[id_money] = (int)(Math.random()*width);
			moneyY[id_money] = (int)(Math.random()*height);
			id_money++;
			
			if(id_money==max_money)
				id_money = 0;
			
			comp.repaint();
			try {
				Thread.sleep(100);
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
			width = comp.getWidth()-60;
			height = comp.getHeight()-60;
			
			for(int id=0;id<max_money;id++){
				moneyX[id] = 30 + (int)(Math.random()*width);
				moneyY[id] = 30 + (int)(Math.random()*height);
			}
			
			thread = new Thread(this);
			thread.start();
		}

	}

	@Override
	public void paint(Graphics g) {
		if(comp==null)
			return;
		
		for(int id=0;id<max_money;id++){
			g.setColor(Color.green);
			g.setFont(myFont);
			g.drawString("$", moneyX[id], moneyY[id]);
		}
	}

	@Override
	public String getTitle() {
		return "Alexanderplatz";
	}

	@Override
	public String getLyrics() {
		return "where all the money comes from...";
	}

}
