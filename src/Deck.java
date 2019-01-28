import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> deck = new ArrayList<Card>();
    private ArrayList<Card> gameCards = new ArrayList<Card>();
    private static final String[] suit = {"Hearts", "Diamonds", "Spades", "Clubs"}; 
    private static final String[] face = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

    public Deck() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                Card card = new Card(suit[i], face[j]);
                deck.add(card);
            }        
        }
        Shuffle(); 
        Random21();
    }

    public void Shuffle() {
    	Collections.shuffle(deck);
    	Collections.shuffle(gameCards);
    }

    public void Random21() {
    	setGameCards(new ArrayList<Card>(deck.subList(0, 21)));
    }
    
    public Card GetCard(int index) {
    	return gameCards.get(index);
    }

	public ArrayList<Card> getGameCards() {
		return gameCards;
	}

	public void setGameCards(ArrayList<Card> gameCards) {
		this.gameCards = gameCards;
	}
}