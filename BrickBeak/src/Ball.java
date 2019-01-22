/**
 * Ball class creates an object of a ball
 * Will create the graphics of the ball form the given coordinates
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Ball extends Rectangle 
{
	/** speed in x and y direction*/
	private int dx, dy;
	
	/**color of the ball to be*/
	private Color color;
	
	/**
	 * Contructor for the ball class that will use the given parameters to contruct the ball object
	 * @param x - xCoordinate of where the ball will be
	 * @param y - yCoordinate of where the ball will be
	 * @param radius - how wide the ball will be 
	 * @param c - color of what the ball will be
	 * @param sp - speed at which the ball will travel when moving
	 */
	public Ball(int x, int y, int radius, Color c, int sp)
	{
		setBounds(x, y, 2*radius, 2*radius);
		dx = sp;
		dy = -sp;
		color = c;
	}
	
	/**
	 * draw function will give the ball color and will fill the ball with the color
	 * @param g - passes in the outlined shape of the ball
	 */
	public void draw(Graphics g)
	{
		g.setColor(color);
		g.fillOval(x, y, width, height);
	}
	
	/**
	 * move function will pass in the width and height of the window
	 * move function makes it so the ball will reflect on the given window
	 * move function will bounce off of the walls in opposite direction
	 * @param winWidth - width of window
	 * @param winHeight - height of window
	 */
	public void move(int winWidth, int winHeight)
	{
		if( dx > 0 && x > winWidth - width)
		{
			dx = -dx;
		}
		
		else if( dx < 0 && x< 0)
		{
			dx = -dx;
		}
		
		if(dy > 0 && y> winHeight - height)
		{
			dy = -dy;
		}
		
		else if( dy< 0 && y < 0)
		{
			dy = -dy;
		}
		
		translate(dx,dy);
	}
	
	/**
	 * Function moves the ball in the opposite Y direction when ball and block intersects
	 * @param winWidth
	 * @param winHeight
	 */
	public void moveCollision(int winWidth, int winHeight)
	{
		dy = -dy;
		
		if( dx > 0 && x > winWidth - width)
		{
			dx = -dx;
		}
		
		else if( dx < 0 && x< 0)
		{
			dx = -dx;
		}
		
		if(dy > 0 && y> winHeight - height)
		{
			dy = -dy;
		}
		
		else if( dy< 0 && y < 0)
		{
			dy = -dy;
		}
		
		translate(dx,dy);
	}
	
	/**
	 * moveBall function is to help move the ball along the paddle before the game starts
	 * @param width - the paddle movement
	 */
	public void moveBall(int width)
	{
		translate(width,0);
	}
}
