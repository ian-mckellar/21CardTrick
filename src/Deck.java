import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> deck;
    private static final String[] suit = {"Hearts", "Diamonds", "Spades", "Clovers"}; 
    private static final String[] face = {"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King", "Ace"};

    public Deck() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                Card card = new Card(suit[i], face[j]);
                Add(card);
            }        
        }
        Shuffle(); 
        Random21();
    }

    public void Shuffle() {

    }

    public void Random21() {
        
    }
}