public class Dealer {
	
	// Deck that holds 
    private Deck deck;

    // Default constructor for Dealer class
    public Dealer() {
        deck = new Deck();
    }
    
    // Reveals the card at the end of the game
    public void revealCard() {
        
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
}