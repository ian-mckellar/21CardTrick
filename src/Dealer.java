public class Dealer {
    private int dealNumber;
    private Deck deck;

    public Dealer() {
        deck = new Deck();
        dealNumber = 0;
    }

    public void Deal() {
        dealNumber++;
        
    }

    public void RevealCard() {
        
    }

    public void PickupCards() {
        deck.Shuffle();
    }
    
    public Deck GetDeck() {
    	return deck;
    }
    
    public void SetDeck(Deck deck) {
    	this.deck = deck;
    }
}