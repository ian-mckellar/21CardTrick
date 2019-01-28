import java.util.ArrayList;

public class Column {
    private int id;
    private ArrayList<Card> cards = new ArrayList<Card>();
    
    public Column(int id) {
    	this.setId(id);
    }
    
    public void addCard(Card card) {
    	cards.add(card);
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public ArrayList<Card> getCards() {
		return cards;
	}
}