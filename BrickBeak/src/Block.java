/**
 * Block class will construct blocks in order to store into our array and to create the wall for the ball to bounce and break
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Block extends Rectangle
{
	/** color of the block */
	private Color c;
	
	/** to check to see if block has been hit or not*/
	private boolean remove;
	
	/**
	 * Constructor of block that will create the block object from the given parameters
	 * we set the boolean variable remove to false to start off because of blocks not being hit by ball yet
	 * @param x - xCoordinate of where the block will be placed
	 * @param y - yCoordinate of where the block will be placed
	 * @param w - width of the block
	 * @param h - height of the block 
	 * @param color - color of the block
	 */
	public Block(int x, int y,int w, int h, Color color) 
	{
		setBounds(x, y, w, h);
		c = color;
		remove = false;
	}
	
	/**
	 * draw function will set the color of the block
	 * function will also fill the whole rectangle of the color
	 * @param g - creates the outline of the shape
	 */
	public void draw(Graphics g)
	{
		g.setColor(c);
		g.fillRect(x, y, width, height);
	}
	
	/**
	 * this function will tell us whether the bock has been hit or not
	 * @return remove -  whether remove is either true or false
	 */
	public boolean isRemove()
	{
		return remove;
	}
	
	/**
	 * this function will automatically set remove to true instead of false which indicates that the block as been hit
	 * @return remove - returns the remove as true to indicate that the block has been hit by the ball
	 */
	public boolean setRemove()
	{
		remove = true;
		return remove;
	}
	
}
