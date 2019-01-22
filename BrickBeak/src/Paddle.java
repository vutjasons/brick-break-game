/**
 * Paddle class will construct the paddle/platform for the user to interact and hit the ball towards the blocks
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Paddle extends Rectangle 
{
	/** sets the color of the paddle*/
	private Color c;
	
	/**
	 * Constructor for the paddle which will create the size and color of the paddle 
	 * @param x - xCoordinate of where the paddle will be placed
	 * @param y - yCoordinate of where the paddle will be placed
	 * @param w - width of the paddle
	 * @param h - height of the paddle
	 * @param color - color of what the paddle will be
	 */
	public Paddle(int x, int y,int w, int h, Color color) 
	{
		setBounds(x, y, w, h);
		c = color;
	}
	
	/**
	 * function will create the paddle itself and display its shape
	 * the paddle will be given the color and will be filled by its color
	 * @param g - outline of the paddle
	 */
	public void draw(Graphics g)
	{
		g.setColor(c);
		g.fillRect(x, y, width, height);
	}
	
	/**
	 * function will be able to move the paddle from left to right without going out of bounds of the window
	 * function will check if the paddle will go out of bounds on the right side and if it does set x to be the max right bound 
	 * function will check if paddle will go out of bounds on the left side and if it does set x to be the minimum left bound
	 * @param width - the distance of the paddle is moving
	 */
	public void move(int width)
	{
		if(x + width > 500)
		{
			x = 500;
		}
		
		else if(x + width < 0)
		{
			x = 0;
		}
		
		else
		{
			translate(width,0);
		}
	}
}
