import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    /**
     * The location of the top card. All cards before this position are in use or
     * have been discarded. From this position on is the deck.
     */
    private int topCardLocation = 0;
    private ArrayList<Card> cardArrayList = new ArrayList<>();

    ArrayList<Card> getCardArrayList() {
        return cardArrayList;
    }

    /**
     * Fills cardArrayList with one of each card
     */
    public void fillDeck() {
        String[] faceValues = {"ace", "jack", "queen", "king"};
        String[] suits = {"clubs", "diamonds", "hearts", "spades"};

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                cardArrayList.add(new Card(faceValues[i], suits[j],
                        "images/" + faceValues[i] + "_of_" + suits[j] + ".png"));
            }
        }
    }

    /**
     * Shuffles the deck
     */
    public void shuffleDeck() {
        topCardLocation = 0;
        Collections.shuffle(cardArrayList);
    }

    /**
     * Simulates adding a new deck
     */
    public void addNewDeck() {
        shuffleDeck();
    }
}