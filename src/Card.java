import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Card
{	
	public static final boolean ORIENTATION_UP = true;
	public static final boolean ORIENTATION_DOWN = false;

	private int cardNumber, rank, suit;
	private BufferedImage image;
	private boolean orientation;

	public Card (int cardNumber, boolean orientation)	//constructor
	{
		this.cardNumber = cardNumber;
		if (cardNumber % 13 != 0)	// rank 1-13 (A-K)
			rank = cardNumber % 13;	
		else
			rank = 13;
		suit = (int)(Math.ceil (cardNumber / 13.0));	// suit 1-4 (S,H,D,C)
		this.orientation = orientation;

		File filepath;													// intialises corresponding image to card
		if (orientation == ORIENTATION_UP)
			filepath = new File ("img\\" + (cardNumber - 1) + ".gif");
		else
			filepath = new File ("img\\b.gif");
		try
		{
			image = ImageIO.read (filepath);
		}catch (IOException e) {}
	}

	public void show (Graphics g, int x, int y)	// draws image
	{
		g.drawImage(image,x,y,null);
	}

	public boolean getFaceup ()	// getter method for orientation
	{
		return orientation;
	}

	public int getRank ()	// getter method for rank (1-13)
	{
		return rank;
	}

	public int getSuit ()	// getter method for suit (1-4)
	{
		return suit;
	}

	public int getCardNumber ()	// getter method for card number (1-52)
	{
		return cardNumber; 
	}

	public void flip ()	// changes orientation and image of card
	{
		File filepath;
		if (orientation == ORIENTATION_UP)		// switches orientation and image depending on current orientation
		{
			orientation = ORIENTATION_DOWN;
			filepath = new File ("img\\b.gif");
		}
		else							
		{
			orientation = ORIENTATION_UP;
			filepath = new File ("img\\" + (cardNumber - 1) + ".gif");
		}
		try 
		{
			image = ImageIO.read (filepath);
		} catch (IOException e) {}
	}

	public boolean equals (Card cardB)	// compares two cards to see if their the same card
	{
		return cardNumber == cardB.cardNumber;
	}
}
