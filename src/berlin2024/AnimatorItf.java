package berlin2024;

import java.awt.Component;
import java.awt.Graphics;

public interface AnimatorItf extends Runnable {

	/**
	 * will be called once when the animator is created
	 */
	public void setComponent(Component c);
	
	/**
	 * paints the animation
	 * @param g
	 */
	public void paint(Graphics g);
	
	/**
	 * @return the title of the image this animator is related to
	 */
	public String getTitle();
	
	/** 
	 * 
	 * @return a string to be displayed as lyrics
	 */
	public String getLyrics();
	
	
}
