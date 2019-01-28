import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> deck = new ArrayList<Card>();
    private ArrayList<Card> gameCards = new ArrayList<Card>();
    private static final String[] suit = {"Hearts", "Diamonds", "Spades", "Clovers"}; 
    private static final String[] face = {"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King", "Ace"};

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
    }

    public void Random21() {
    	gameCards = new ArrayList<Card>(deck.subList(0, 20));
    }
    
    public Card GetCard(int index) {
    	return deck.get(index);
    }
}