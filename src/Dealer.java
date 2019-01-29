import java.awt.Image;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

public class Dealer {

	// Deck that holds
	private Deck deck;

	// Default constructor for Dealer class
	public Dealer() {
		deck = new Deck();
	}

	// Reveals the card at the end of the game
	public void revealCard(ArrayList<Column> columnChoices) {
		// disable buttons
		ArrayList<Set<Card>> listOfSet = new ArrayList<Set<Card>>();
		for (Column co : columnChoices) {
			Set<Card> setOfCards = new HashSet<Card>();
			for (Card car : co.getCards()) {
				setOfCards.add(car);
			}
			listOfSet.add(setOfCards);
		}

		listOfSet.get(0).retainAll(listOfSet.get(1));
		listOfSet.get(0).retainAll(listOfSet.get(2));
		
		Card card = new Card();
		
		try {
			card = new ArrayList<Card>(listOfSet.get(0)).get(0);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No card found! Please try again and actually choose a card.");
			return;
		}
		Icon icon = resizeImage((card.getFace() + "_of_" + card.getSuit()).toLowerCase());

		UIManager.put("OptionPane.background", new ColorUIResource(255, 255, 255));
		UIManager.put("Panel.background", new ColorUIResource(255, 255, 255));
		JOptionPane.showMessageDialog(null, "Your card is the " + card.getFace() + " of " + card.getSuit(), null, 0, icon);
		return;
	}

	// Pick up the cards from the deck
	public void PickupCards(int i) {
		deck.pickUpCards(i);
	}

	// Get the deck the Dealer has
	public Deck getDeck() {
		return deck;
	}

	// Set the dealer's deck to the parameter
	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	// Converts a card object to image
	private ImageIcon resizeImage(String imageName) {

		ImageIcon card1ImageIcon = new ImageIcon(getClass().getResource("Images/" + imageName + ".png"));
		Image card1Image = card1ImageIcon.getImage();
		Image scaledImg = card1Image.getScaledInstance(89, 125, java.awt.Image.SCALE_SMOOTH);
		card1ImageIcon = new ImageIcon(scaledImg);

		return card1ImageIcon;
	}
}