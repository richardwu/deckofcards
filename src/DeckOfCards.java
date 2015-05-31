import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


class DeckOfCards extends JFrame
{
	private Deck deck = new Deck (true);
	private Deck dealtDeck = new Deck (false);

	private String [] ranks = {"A", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"}; 
	private String [] suits = {"Spade", "Heart", "Diamond", "Club"};

	private JComboBox positions = new JComboBox ();
	private JComboBox chooseSuit = new JComboBox (suits);
	private JComboBox chooseRank = new JComboBox (ranks);

	private JButton shuffleBtn = new JButton ("Shuffle");
	private JButton dealBtn = new JButton ("Deal Top Card");
	private JButton dealCustomBtn = new JButton ("Deal Card");
	private JButton flipBtn = new JButton ("Flip Card");
	private JButton addBtn = new JButton ("Add Card");
	private JButton searchBtn = new JButton ("Search");
	private JButton quickSortBtn = new JButton ("Quick Sort");
	private JButton selectionSortBtn = new JButton ("Selection Sort");

	private JLabel deckHeading = new JLabel ("Original Deck");
	private JLabel dealtDeckHeading = new JLabel ("Dealt Pile");

	private boolean searched = false;

	public DeckOfCards ()
	{
		for (int i = 1; i < deck.length (); i++)        // initialises the positions combo box
			positions.addItem (i);

		shuffleBtn.addActionListener(new ShuffleBtnListener ());                                // add action listeners to the buttons
		dealBtn.addActionListener (new DealBtnListener ());
		dealCustomBtn.addActionListener (new DealCustomBtnListener ());
		flipBtn.addActionListener (new FlipBtnListener ());
		addBtn.addActionListener (new AddBtnListener ());
		searchBtn.addActionListener (new SearchBtnListener ());
		quickSortBtn.addActionListener (new QuickSortBtnListener ());
		selectionSortBtn.addActionListener (new SelectionSortBtnListener ());

		deckHeading.setHorizontalAlignment (SwingConstants.CENTER);                     // centres the JLabels (useful when adding to JPanel)
		dealtDeckHeading.setHorizontalAlignment (SwingConstants.CENTER);

		JPanel content = new JPanel ();                                         // main JPanel
		content.setLayout (new GridBagLayout ());
		GridBagConstraints c = new GridBagConstraints ();

		c.ipadx = 0;                                            // generic settings
		c.fill = GridBagConstraints.BOTH;

		c.ipadx = 20;                                                   // sets up button settings and first button settings (shuffleBtn)
		c.ipady = 0;
		c.weightx = 0.2;
		c.weighty = 0.0;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(0,0,0,0);
		c.gridx = 0;
		c.gridy = 0;
		content.add (shuffleBtn, c);

		c.ipadx = 0;                    // sets up second button settings (dealBtn)
		c.gridx = 1;
		c.gridy = 0;
		content.add (dealBtn,c);

		c.gridx = 2;                                                                                            // creates a divider space block between button sections
		c.gridy = 0;
		content.add (Box.createRigidArea(new Dimension (30, 0)), c);

		c.gridx = 3;                    // sets up third button settings (flipBtn)
		c.gridy = 0;
		content.add (flipBtn,c);

		c.gridx = 4;                            // sets up first combo box settings (positions)
		c.gridy = 0;
		content.add (positions, c);

		c.gridx = 5;                                    // sets up fourth button settings (dealCustomBtn)
		c.gridy = 0;
		content.add (dealCustomBtn, c);

		c.gridx = 6;                                                                                            // creates a divider space block between button sections
		c.gridy = 0;
		content.add (Box.createRigidArea(new Dimension (30, 0)), c);

		c.gridx = 7;                    // sets up fifth button settings (addBtn)
		c.gridy = 0;
		content.add (addBtn, c);

		c.gridx = 8;                            // sets up second combo box settings (chooseRank)
		c.gridy = 0;
		content.add (chooseRank, c);

		c.gridx = 9;                            // sets up third combo box settings (chooseSuit)
		c.gridy = 0;
		content.add (chooseSuit, c);

		c.gridx = 10;                           // sets up sixth button settings (searchBtn)
		c.gridy = 0;
		content.add (searchBtn, c);

		c.gridx = 11;                                                                                           // creates a divder space block between button sections
		c.gridy = 0;
		content.add (Box.createRigidArea(new Dimension (30, 0)), c);

		c.ipadx = 25;                                   // sets up seventh button settings (quickSortBtn)
		c.gridx = 12;
		c.gridy = 0;
		content.add (quickSortBtn,c);

		c.ipadx = 0;                                            // sets up eighth button settings (selectionSortBtn)
		c.gridx = 13;
		c.gridy = 0;
		content.add (selectionSortBtn,c);

		c.ipady = 40;                                   // sets up heading settings and first heading settings (deckHeading)
		c.weightx = 0.5;
		c.gridwidth = 2;
		c.gridx = 2;
		c.gridy = 1;
		content.add (deckHeading, c);

		c.gridwidth = 1;                                        // sets up second heading settings (dealtDeckHeading)
		c.gridx = 10;
		c.gridy = 1;
		content.add (dealtDeckHeading, c);

		c.gridwidth = 5;                // sets up deck panel settings and first deck panel settings (original deck)
		c.weighty = 1;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 2;
		content.add(deck, c);

		c.gridx = 8;                            // sets up second deck panel settings (dealt deck)
		c.gridy = 2;
		content.add(dealtDeck, c);

		setContentPane (content);                                               // configures the JFrame settings
		pack();
		setTitle ("Deck of Cards!");
		setSize (1260,900);
		setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo (null);
		setResizable (false);
	}

	class ShuffleBtnListener implements ActionListener      // action listener to shuffle deck
	{
		public void actionPerformed (ActionEvent e)
		{
			deck.shuffle ();
			deck.show ();
		}
	}

	class FlipBtnListener implements ActionListener // action listener to flip a card
	{
		public void actionPerformed (ActionEvent e)
		{
			if (positions.getSelectedItem () != null)       // if selection is not empty, flips the card
				deck.getDeck().get((Integer) positions.getSelectedItem()).flip();
			deck.show ();
		}
	}

	class QuickSortBtnListener implements ActionListener    // action listener to quick sort deck
	{
		public void actionPerformed (ActionEvent e)
		{
			deck.quickSort ();
			deck.show ();
		}
	}

	class SelectionSortBtnListener implements ActionListener        // action listener to select sort deck
	{
		public void actionPerformed (ActionEvent e)
		{
			deck.selectionSort ();
			deck.show ();
		}
	}

	class DealBtnListener implements ActionListener // action listener to deal the top card
	{
		public void actionPerformed (ActionEvent e)
		{
			if (deck.length() > 1)  // if deck isn't empty
			{
				dealtDeck.add(deck.deal ());
				deck.show ();
				dealtDeck.show ();

				positions.removeItemAt (deck.length()-1);       // reduces position combo box options accordingly
			}
		}
	}

	class DealCustomBtnListener implements ActionListener   // action listener to deal card at specific position
	{
		public void actionPerformed (ActionEvent e)
		{
			if (deck.length() > 1)  // if deck isn't empty
			{
				dealtDeck.add(deck.deal ((Integer) positions.getSelectedItem()));
				deck.show ();
				dealtDeck.show ();

				positions.removeItemAt (deck.length()-1);       // reduces position combo box options accordingly
			}
		}
	}

	class AddBtnListener implements ActionListener  // action listener to add cards to deck
	{
		public void actionPerformed (ActionEvent e)
		{
			int suit;
			int rank;

			if (((String)chooseSuit.getSelectedItem()).equals("Spade"))                     // finds corresponding suit value
				suit = 0;
			else if (((String)chooseSuit.getSelectedItem()).equals("Heart"))
				suit = 13;
			else if (((String)chooseSuit.getSelectedItem()).equals("Diamond"))
				suit = 26;
			else
				suit = 39;

			if (((String)chooseRank.getSelectedItem()).equals("A"))                         // finds corresponding rank value
				rank = 1;
			else if (((String)chooseRank.getSelectedItem()).equals("2"))
				rank = 2;
			else if (((String)chooseRank.getSelectedItem()).equals("3"))
				rank = 3;
			else if (((String)chooseRank.getSelectedItem()).equals("4"))
				rank = 4;
			else if (((String)chooseRank.getSelectedItem()).equals("5"))
				rank = 5;
			else if (((String)chooseRank.getSelectedItem()).equals("6"))
				rank = 6;
			else if (((String)chooseRank.getSelectedItem()).equals("7"))
				rank = 7;
			else if (((String)chooseRank.getSelectedItem()).equals("8"))
				rank = 8;
			else if (((String)chooseRank.getSelectedItem()).equals("9"))
				rank = 9;
			else if (((String)chooseRank.getSelectedItem()).equals("10"))
				rank = 10;
			else if (((String)chooseRank.getSelectedItem()).equals("J"))
				rank = 11;
			else if (((String)chooseRank.getSelectedItem()).equals("Q"))
				rank = 12;
			else
				rank = 13;

			deck.add (new Card (rank + suit, true));
			deck.show ();

			positions.addItem(deck.length() - 1);   // increases positions combo box options accordingly
		}
	}

	class SearchBtnListener implements ActionListener       // action listener to search for a specific card type
	{
		public void actionPerformed (ActionEvent e)
		{
			int suit;
			int rank;

			if (((String)chooseSuit.getSelectedItem()).equals("Spade"))                     // finds corresponding suit value
				suit = 0;
			else if (((String)chooseSuit.getSelectedItem()).equals("Heart"))
				suit = 13;
			else if (((String)chooseSuit.getSelectedItem()).equals("Diamond"))
				suit = 26;
			else
				suit = 39;

			if (((String)chooseRank.getSelectedItem()).equals("A"))                         // finds corresponding rank value
				rank = 1;
			else if (((String)chooseRank.getSelectedItem()).equals("2"))
				rank = 2;
			else if (((String)chooseRank.getSelectedItem()).equals("3"))
				rank = 3;
			else if (((String)chooseRank.getSelectedItem()).equals("4"))
				rank = 4;
			else if (((String)chooseRank.getSelectedItem()).equals("5"))
				rank = 5;
			else if (((String)chooseRank.getSelectedItem()).equals("6"))
				rank = 6;
			else if (((String)chooseRank.getSelectedItem()).equals("7"))
				rank = 7;
			else if (((String)chooseRank.getSelectedItem()).equals("8"))
				rank = 8;
			else if (((String)chooseRank.getSelectedItem()).equals("9"))
				rank = 9;
			else if (((String)chooseRank.getSelectedItem()).equals("10"))
				rank = 10;
			else if (((String)chooseRank.getSelectedItem()).equals("J"))
				rank = 11;
			else if (((String)chooseRank.getSelectedItem()).equals("Q"))
				rank = 12;
			else
				rank = 13;

			ArrayList<Integer> positionsToNotFlip = deck.search (new Card (rank + suit, true));     // finds array of positions

			if (searched == false)  // only does it the first time around (not when it flips it back over)
			{
				for (int i = 1; i < deck.length (); i++)                                                // finds already face-down cards to flip face up
				{
					if (deck.getDeck().get(i).getFaceup() == Card.ORIENTATION_DOWN) // if facedown card found, flips it
						deck.getDeck().get(i).flip();
				}
			}

			boolean [] toFlip = new boolean [deck.length ()];	// boolean array to keep track of which cards to flip

			for (int i = 1; i < toFlip.length; i++)	// intialises boolean array to all true
				toFlip [i] = true;

			for (int i = 0; i < positionsToNotFlip.size (); i++)	// sets positions with card to false (won't flip those cards)
			{
				if (positionsToNotFlip.get(i) != -1)
					toFlip [positionsToNotFlip.get(i)] = false;
			}

			for (int i = 1; i < toFlip.length; i++)	// flips the other cards
			{
				if (toFlip [i])
					deck.getDeck().get(i).flip();
			}

			deck.show ();

			if (searched == false)                                  // disables other buttons until user presses "Go Back" button
			{
				shuffleBtn.setEnabled (false);
				dealBtn.setEnabled (false);
				dealCustomBtn.setEnabled (false);
				flipBtn.setEnabled (false);
				addBtn.setEnabled (false);
				quickSortBtn.setEnabled (false);
				selectionSortBtn.setEnabled (false);
				positions.setEnabled(false);
				chooseSuit.setEnabled (false);
				chooseRank.setEnabled (false);

				searchBtn.setText ("Go Back");
				searched = true;
			}
			else                                                            // renables buttons when user presses "Go Back" button
			{
				shuffleBtn.setEnabled (true);
				dealBtn.setEnabled (true);
				dealCustomBtn.setEnabled (true);
				flipBtn.setEnabled (true);
				addBtn.setEnabled (true);
				quickSortBtn.setEnabled (true);
				selectionSortBtn.setEnabled (true);
				positions.setEnabled(true);
				chooseSuit.setEnabled (true);
				chooseRank.setEnabled (true);

				searchBtn.setText ("Search");
				searched = false;
			}
		}
	}

	public static void main (String [] args)
	{
		DeckOfCards window = new DeckOfCards ();
		window.setVisible (true);
	}
}
