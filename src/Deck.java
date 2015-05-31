import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JComponent;

public class Deck extends JComponent
{
	private ArrayList <Card> deck;  // index 0 left intentionally unused

	public Deck (boolean isFull) 
	{
		if (isFull)	
		{
			deck = new ArrayList <Card> (53);							// constructs full deck
			deck.add (null);	// creates spaceholder for first index
			for (int i = 1; i < 53; i++)
				deck.add (new Card (i, Card.ORIENTATION_UP));	// intialises array of cards deck
		}		
		else
		{
			deck = new ArrayList <Card> (1);	// constructs empty deck with index 0 unused
			deck.add (null);	// creates spaceholder for first index
		}
	}

	public void show ()
	{
		repaint ();
	}

	public void paintComponent (Graphics g)
	{
		if (deck.size () > 1)	// deck not empty
		{
			int x = 0;
			int y = 0;

			for (int i = 1; i < deck.size (); i++)	// paints each card in deck
			{
				deck.get(i).show(g, x, y);	// calls the show method to paint card from Card Class
				x+=30;
				if (i % 13 == 0)	// shifts to next row once there are 13 cards
				{
					y += 97;
					x = 0;
				}
			}
		}
	}

	public void shuffle ()	// shuffles the deck
	{
		if (deck.size () > 1)	// deck not empty
		{
			Random rand = new Random ();
			Card temp;

			for (int i = 0; i <= 400; i++)	// exchanges two random cards 401 times
			{
				int card1 = rand.nextInt(deck.size () - 1) + 1;	// generates two random card indices
				int card2 = rand.nextInt(deck.size () - 1) + 1;

				temp = deck.get(card1);			// swaps the two cards
				deck.set(card1, deck.get(card2));
				deck.set(card2, temp);
			}
		}
	}

	public Card deal ()	// transfers top card from original deck to dealt deck
	{
		Card dealtCard = null;
		if (deck.size () > 1)	// deck not empty
		{
			dealtCard = deck.get(1);	// stores dealt card to be returned

			deck.remove(1);	// removes first card	
		}

		return dealtCard;
	}

	public Card deal (int position)	// transfers card of specific position from o. deck to d. deck
	{
		Card dealtCard = null;
		if (deck.size () > 1 && position < deck.size ())	// deck not empty and card in deck
		{
			dealtCard = deck.get(position);	// stores dealt card to be returned

			deck.remove(position);	// removes dealt card
		}

		return dealtCard;
	}

	public void add (Card newCard)	// adds a specific card to deck
	{
		deck.add(newCard);
	}

	public ArrayList<Integer> search (Card findCard)	// finds all cards' positions corresponding to input
	{
		ArrayList <Integer> positions = new ArrayList <Integer> (0);	// declare array to store positions

		for (int i = 1; i < deck.size (); i++)	// stores positions of card into array positions
		{
			if (deck.get(i).equals(findCard))	// if card found, stores it
				positions.add (i);
		}

		if (positions.size () == 0)	// if no cards were found adds sentinel value
			positions.add (-1);

		return positions;
	}


	public void quickSort ()									// sorts deck according to their numerical value only (A,2...K)
	{
		quickSorter (deck, 1, deck.size () - 1);	// calls main sorter method
	}

	private void quickSorter (ArrayList <Card> deck, int low, int max)	// main sorter method (recursed for searching multiple partitions)
	{
		if (low < max)							// if the number is lower, calls appropriate method (partition or recurses)
		{
			int a = partition (deck, low, max);
			quickSorter (deck, low, a);
			quickSorter (deck, a+1, max);
		}
	}

	private int partition (ArrayList <Card> deck, int low, int max)	// partitions the deck; to be sorted
	{
		int pivot = deck.get(low).getRank ();
		int i = low - 1;
		int j = max + 1;

		while (true)	// loops until partition is ordered by value (when value is returned)
		{
			i++;
			while (i < max && deck.get(i).getRank () < pivot)	// increases left side index until number is found greater than pivot
				i++;
			j--;
			while (j > low && deck.get(j).getRank () > pivot)	// decreases right side index until number is found less than pivot
				j--;

			if (i < j)	// if original left index remains on the left side of right index, swaps cards (card on left side is greater than card on right side)
			{
				Card temp = deck.get(i);
				deck.set(i, deck.get(j));
				deck.set(j, temp);
			}
			else 	// else returns j to be recursed again;
				return j;
		}
	}

	public void selectionSort ()	// sorts the deck according to suit and rank (S,H,D,C) then (A,K...2)
	{
		Card temp;
		for (int i = 1; i < deck.size() - 1; i++)	// sorts deck by card number = suit & rank (highest to lowest; A,K...2)
		{
			int highPos = i;	// assume first card is "highest" card
			for (int j = i+1; j < deck.size(); j++)	// check rest of deck
			{   
				int rankValue, rankValue2;

				if(deck.get(j).getRank () == 1)					// b/c of how ranks are set up, reassigns Ace to be greatest rank in deck
					rankValue = 14;
				else
					rankValue = deck.get(j).getRank ();

				if (deck.get(highPos).getRank () == 1)			// see above comment
					rankValue2 = 14;
				else
					rankValue2 = deck.get(highPos).getRank ();

				int valOne = 100*deck.get(j).getSuit() - rankValue;			// assigns the two values to be compared (essentially card numbers)
				int valTwo = 100*deck.get(highPos).getSuit() - rankValue2;

				if (valOne < valTwo)	// if you find a "higher" card
					highPos = j;	// keep track of highest’s position
			}

			temp = deck.get(i);			// swap high card with card in its proper position
			deck.set (i, deck.get(highPos));
			deck.set(highPos, temp);
		}
	}

	public int length ()	// getter method for length of array of cards (deck)
	{
		return deck.size ();
	}

	public ArrayList <Card> getDeck ()	// getter method for the array of cards (deck)
	{
		return deck;
	}
}
