public class Card {
	
	// Fields for card info
    private String suit;
    private String face;
    
    // Default Constructor for a new cards
    public Card (String suit, String face) {
    	this.setSuit(suit);
    	this.setFace(face);
    }

    // Return the card's suit
	public String getSuit() {
		return suit;
	}

	// Set the card's suit
	public void setSuit(String suit) {
		this.suit = suit;
	}

	// Return the card's face
	public String getFace() {
		return face;
	}

	// Set the card's face
	public void setFace(String face) {
		this.face = face;
	}
	
	// Override the toString method to return it in a specific manner
	@Override
	public String toString() {
		return face + " of " + suit;
	}
    
}