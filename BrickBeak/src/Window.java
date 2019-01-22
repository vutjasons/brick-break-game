/**
 * Window class will create the menu for the user to play the game on
 */
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Window extends JFrame {
	
	/**
	 * main function that will call the window class and open up the window
	 */
	public static void main(String[] args)
	{
		Window w = new Window();
	}
	
	/**
	 * Constructor for the window
	 * Will create a display window that is 600x600
	 * Title will be called Breaker Game
	 * Calls on panel class to create the ball, blocks and paddle
	 */
	public Window()
	{
		setBounds(600,600,600,600);
		setTitle("Breaker Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Panel p = new Panel();
		setContentPane(p);
		Thread t = new Thread(p);
		t.start();
		setVisible(true);
	}

}
