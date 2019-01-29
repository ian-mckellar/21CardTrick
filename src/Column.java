import java.util.ArrayList;

public class Column {
	private ArrayList<Card> cards = new ArrayList<Card>();

	// Default constructor. Sets the id to the specified parameter
	////////// Do we need the ID? I don't think we do.
	public Column() {
	}

	// Add a card to the column container
	public void addCard(Card card) {
		cards.add(card);
	}

	// Return the list of cards contained in the column
	public ArrayList<Card> getCards() {
		return cards;
	}
}