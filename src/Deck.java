import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	// All 52 cards
	private ArrayList<Card> deck = new ArrayList<Card>();

	// 21 Cards to be used in game
	private ArrayList<Card> gameCards = new ArrayList<Card>();

	// Arrays to hold constants of card info and info for shuffling the game cards
	private static final String[] suit = { "Hearts", "Diamonds", "Spades", "Clubs" };
	private static final String[] face = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King",
			"Ace" };
	private static final int[] intStartPosition = { 0, 7, 14, 21 };

	// Default constructor for a deck
	public Deck() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 13; j++) {
				Card card = new Card(suit[i], face[j]);
				deck.add(card);
			}
		}
		shuffleDeck();
		random21();
	}

	// Shuffle just the deck
	public void shuffleDeck() {
		Collections.shuffle(deck);
	}

	// Picks up the cards in a specific order depending on column choice
	public void pickUpCards(int i) {
		ArrayList<Card> tempDeck = new ArrayList<Card>();
		// If column 1 was chosen
		if (i == 0) {
			tempDeck = new ArrayList<Card>(gameCards.subList(intStartPosition[i + 2], intStartPosition[i + 3]));
			tempDeck.addAll(new ArrayList<Card>(gameCards.subList(intStartPosition[i], intStartPosition[i + 1])));
			tempDeck.addAll(new ArrayList<Card>(gameCards.subList(intStartPosition[i + 1], intStartPosition[i + 2])));
		}
		// If column 2 was chosen
		else if (i == 1) {
			tempDeck = new ArrayList<Card>(gameCards.subList(intStartPosition[i - 1], intStartPosition[i]));
			tempDeck.addAll(new ArrayList<Card>(gameCards.subList(intStartPosition[i], intStartPosition[i + 1])));
			tempDeck.addAll(new ArrayList<Card>(gameCards.subList(intStartPosition[i + 1], intStartPosition[i + 2])));
		}
		// If column 3 was chosen
		else if (i == 2) {
			tempDeck = new ArrayList<Card>(gameCards.subList(intStartPosition[i], intStartPosition[i + 1]));
			tempDeck.addAll(new ArrayList<Card>(gameCards.subList(intStartPosition[i - 2], intStartPosition[i - 1])));
			tempDeck.addAll(new ArrayList<Card>(gameCards.subList(intStartPosition[i - 1], intStartPosition[i])));
		}
		// Safe guard
		else {
			return;
		}
		gameCards = tempDeck;
	}

	// Grab the first 21 cards from the deck and set the Game Cards equal to it
	public void random21() {
		setGameCards(new ArrayList<Card>(deck.subList(0, 21)));
	}

	// Get a specific card from the deck
	public Card getCard(int index) {
		return gameCards.get(index);
	}

	// Get the game cards list
	public ArrayList<Card> getGameCards() {
		return gameCards;
	}

	// Set the game cards list
	public void setGameCards(ArrayList<Card> gameCards) {
		this.gameCards = gameCards;
	}
}