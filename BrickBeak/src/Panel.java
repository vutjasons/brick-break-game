/**
 * Panel class will be creating all our blocks, ball, and paddle for the user to play the game
 * Panel class will make it possible for user to play with left or right arrow keys or with mouse
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Panel extends JPanel implements MouseListener, MouseMotionListener, KeyListener, Runnable {
		
		/** will storage all 40 blocks created into an array*/
		private Block storage[] = new Block[40];
		
		/** will store 3 lives left for the user into an array*/
		private Block liveStorage[] = new Block[3];
		
		/** creates the paddle */
		private Paddle p1;
		
		/** creates the ball*/
		private Ball b1;
		
		/** creates the blocks */
		private Block block;
		
		/** creates the lives display blocks */
		private Block ballLives;
		
		/** boolean to let program know that game has not started */
		private boolean start = false;
		
		/** sets ballCount to 3 because user has 3 chances to win the game */
		private int ballCount = 3;
		
		/** sets count to 40 start off with 40 blocks*/
		private int count = 40;
		
		/** index for lives display */
		private int liveIndex = 0;
		
		/** will run the thread while loop is true*/
		private boolean loop = true;
		
		/**
		 * Constructor for panel class that will use the imported classes to allow the user to move the paddle with mouse or left or right key
		 * User will be able to release the ball from space bar or left click
		 * Paddle will be created as the color green and will start to be in the middle of the window
		 * Ball will be created as the color white and will be placed on top and middle of paddle
		 * We will set the window background as black
		 * We will run a for loop that will go through the storage and store each block created in each index i
		 * After 10 blocks have been created in a row then program will move on to the next row
		 * Colors for each row, green, blue, yellow, red
		 */
		public Panel()
		{
			addMouseListener(this);
			addMouseMotionListener(this);
			addKeyListener(this);
			setFocusable(true);
			p1 = new Paddle(300, 400, 100, 10, Color.GREEN);
			b1 = new Ball(340, 390, 5, Color.WHITE, 2);
			
			this.setBackground(Color.BLACK);
			int xCord = 50;
			int xCord2 = 50;
			int xCord3 = 50;
			int xCord4 = 50;
			int xCord5 = 50;
			
			for(int i = 0; i< liveStorage.length; i++)
			{
				ballLives = new Block(xCord5,500, 10, 40, Color.WHITE);
				liveStorage[i] = ballLives;
				xCord5 = xCord5 + 20;
			}
			
			for(int i = 0; i< storage.length; i++)
			{
				if(i<10 && i>=0)
				{
					block = new Block(xCord, 80, 50, 20, Color.GREEN);
					xCord = xCord + 52;
					storage[i]=block;
				}
				
				else if(i>=10 && i<=19)
				{
					block = new Block(xCord2, 60, 50, 20, Color.BLUE);
					xCord2 = xCord2 + 52;
					storage[i] = block;
				}
				
				else if(i>=20 & i<=29)
				{
					block = new Block(xCord3, 40, 50, 20, Color.RED);
					xCord3 = xCord3 + 52;
					storage[i] = block;
				}
				
				else
				{
					block = new Block(xCord4, 20, 50, 20, Color.YELLOW);
					xCord4 = xCord4+52;
					storage[i] = block;
				}
			}
		}
		/**
		 * Paint component will be creating the graphics and interaction in the game
		 * 1.If start is false then that means user has not launched ball and it will display all the blocks and start the paddle and ball in the middle
		 * 2.Lives block will be displayed on the bottom left
		 * 3.For loop function for liveStorage and storage will check to see if the ball has been hit if its true then the block/life will not displayed
		 * and that will let the user know if the block has been destroyed of if they lost a life
		 * 4.If start is not false then that means that the game has commenced and the ball is moving
		 * 5.First it will check if the ball has made contact with the paddle by checking if the ball is within the x and y coordinate of the paddle
		 * and it will reflect if it the coordinates match
		 * 6.Next it will check if the ball goes past the paddle by checking if the ball's ycord is greater than paddle ycord
		 *this will indicate that the user loses a ball and that a live block will disappear and ballCount decreases
		 *method will also check if user has run out of balls or if they beat the game, a message will appear to notify user
		 * 7.Lastly a for loop will be going through the block list to see if each index block is false/not hit yet and if it is will display block
		 * 8.Another for loop will be going through after and go through the block list again to see if the ball and block are within the same coord
		 *this will indicate that the ball has hit the block and this will set the index block to true and next iteration the block will disappear 
		 *indicating that the ball has hit the block
		 */
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			
			if(start == false)
			{
				p1.draw(g);
				b1.draw(g);
				
				for(int j = 0; j< liveStorage.length; j++)
				{
					if(liveStorage[j].isRemove() == false)
					{
						liveStorage[j].draw(g);
					}
				}
				
				for(int i = 0; i < storage.length; i++)
				{
					if(storage[i].isRemove() == false)
					{
						storage[i].draw(g);
					}
				}
				for(int i = 0; i < storage.length; i++)
				{
					if(storage[i].isRemove() == false)
					{
						if(storage[i].contains(b1.getCenterX()+10, b1.getCenterY()+10))
						{
							storage[i].setRemove();
							b1.moveCollision(getWidth(),getHeight());
						}
					}
				}
			}
			else
			{
				if(b1.getX() >= p1.getMinX() && b1.getX() <= p1.getMaxX() && b1.getY() >= p1.getMinY() && b1.getY()<= p1.getMaxY())
				{
					b1.move(getWidth(), -1*getHeight());
					b1.draw(g);
				}
				
				else if(b1.getMaxY() > p1.getMaxY())
				{
					start = false;
					ballCount--;
					System.out.println(ballCount);
					
					liveStorage[liveIndex].setRemove();
					liveIndex++;
					
					if(ballCount>0)
					{
						JOptionPane.showMessageDialog(this, "You've lost a ball. Ball Count: "+ballCount);
					}
					else
					{
						JOptionPane.showMessageDialog(this, "You have no more balls, you lose!");
					}
				}
				
				else if(count == 0)
				{
					JOptionPane.showMessageDialog(this, "Congratulations you won the game!");
				}
				else
				{
					b1.move(getWidth(), getHeight());
					b1.draw(g);
					p1.draw(g);
					
					for(int j = 0; j< liveStorage.length; j++)
					{
						if(liveStorage[j].isRemove() == false)
						{
							liveStorage[j].draw(g);
						}
					}
					
					for(int i = 0; i < storage.length; i++)
					{
						if(storage[i].isRemove() == false)
						{
							storage[i].draw(g);
						}
					}
					
					for(int i = 0; i < storage.length; i++)
					{
						if(storage[i].isRemove() == false)
						{
							if(storage[i].contains(b1.getMinX(), b1.getMinY()))
							{
								storage[i].setRemove();
								
								b1.moveCollision(getWidth(),getHeight());
								count--;
							}
						}
					}
				}

			}
			
		}

		/**
		 * Overrides the keyPressed function
		 * This will check to see if user is using arrow key to move paddle
		 * If key button is right/left arrow key move the ball and paddle 60 units to the right/left
		 * b1 becomes a new ball to make sure that ball stays within the middle of paddle at all times
		 * Else user has launched the ball and you would can move paddle to the right/left 60 units
		 * If user uses the space bar, set start = true which will launch the ball off the paddle
		 */
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_RIGHT)
			{
				if(start == false)
				{
					p1.move(60);
					b1 = new Ball((int)p1.getMaxX()-120, (int)p1.getY()-10, 5, Color.WHITE, 2 );
					b1.moveBall(60);
				}
				else
				{
					p1.move(60);
				}
			}
			if(e.getKeyCode() == KeyEvent.VK_LEFT)
			{
				
				if(start == false)
				{
					p1.move(-60);
					b1 = new Ball( (int)p1.getMaxX(), (int)p1.getY()-10, 5, Color.WHITE, 2 );
					b1.moveBall(-60);
				}
				
				else
				{
					p1.move(-60);
				}
				
			}
			if(e.getKeyCode() == KeyEvent.VK_SPACE)
			{
				start = true;
			}
			
		}

		/**
		 * Overrides the mouse move function
		 * We will get the distance of the mouse on the xCoord and get the distance of the paddle on the xCoord
		 * We will then subtract both of them in order to get the ball and paddle to be moving together and with ball in the middle
		 * If Start is false then we will move the paddle matching the mouse coordinates and also be placing a new ball in the middle of paddle
		 * Else ball has already been launched and we can move the paddle with just the mouse
		 */
		@Override
		public void mouseMoved(MouseEvent e) {
			int distance = (int)(e.getX());
			int distance2 = (int)(p1.getX());
			int findistance = distance - distance2;
			if(start == false)
			{
				p1.move(findistance);
				b1 = new Ball( (int)p1.getCenterX(),(int)p1.getCenterY()-15, 5, Color.WHITE, 2 );
			}
			else
			{
				p1.move(findistance);
			}
			
		}

		/**
		 * Override function that will check to see if mouse has been clicked
		 * If mouse is clicked then set start = to true to set the ball of the paddle
		 */
		@Override
		public void mouseClicked(MouseEvent e) {
			start = true;
			
		}
		
		@Override
		public void keyReleased(KeyEvent e) {
		}
		@Override
		public void keyTyped(KeyEvent e) {
		}
		@Override
		public void mousePressed(MouseEvent e) {
		}
		@Override
		public void mouseReleased(MouseEvent e) {
		}
		@Override
		public void mouseEntered(MouseEvent e) {
		}
		@Override
		public void mouseExited(MouseEvent e) {
		}
		@Override
		public void mouseDragged(MouseEvent e) {
		}
		
		
		@Override
		public void run() {
			while(loop)
			{
				if(ballCount == 0)
				{
					loop = false;
				}
				if(count == 0)
				{
					loop = false;
				}
				repaint();
				try
				{
					Thread.sleep(8);
				}
				catch(InterruptedException e){}
			}
			
		}
	}