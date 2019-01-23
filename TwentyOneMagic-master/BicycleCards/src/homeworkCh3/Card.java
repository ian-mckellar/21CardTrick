//----------------------------------------------------------------------------
// Card.java                  by Page                Chapter 3
//
// Card Class in which creates public values that are inherited by the Deck Class
// In addition we include a toString overide method that allows us to display our cards
//----------------------------------------------------------------------------

package homeworkCh3;

public class Card 
{
    // public values to be used within the inheritance of Deck
    static final String [] suit = {"Hearts", "Diamonds", "Spades", "Clovers"}; 
    String finalCardValue;
    String suitValue;
    String completeCard;
    
    
    public final String toString()
    // creates an overiding method which prints a suit and card value
    {
        String hand;
        hand = finalCardValue + " of " + suitValue;
        return hand;
    }
}
