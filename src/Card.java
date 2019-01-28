public class Card {
    private String suit;
    private String face;
    
    public Card (String suit, String face) {
    	this.setSuit(suit);
    	this.setFace(face);
    }

	public String getSuit() {
		return suit;
	}

	public void setSuit(String suit) {
		this.suit = suit;
	}

	public String getFace() {
		return face;
	}

	public void setFace(String face) {
		this.face = face;
	}
    
}