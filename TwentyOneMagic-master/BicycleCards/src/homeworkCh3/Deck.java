//----------------------------------------------------------------------------
// Deck.java                         by Page                        Chapter 3
//
// Deck class that generates a deck of cards, displays them, and shuffles them
// In addition, includes 21 Card Magic Trick
//----------------------------------------------------------------------------

package homeworkCh3;

import ch03.stacks.*;
import java.util.Random;

public class Deck extends Card
{
    // Public values that create and assign Bounded Stacks to be used within Deck
    Random rand = new Random();
    static final int SIZE = 52;
    static final int MGC = 21;
    static final int SGLE = 7;
    String currentCard;
    BoundedStackInterface<String> deckStack = new ArrayStack<>(SIZE);
    BoundedStackInterface<String> holderStack = new ArrayStack<>(SIZE);
    
    BoundedStackInterface<String> rStack = new ArrayStack<>(SIZE);
    BoundedStackInterface<String> fStack = new ArrayStack<>(SIZE);
    
    //Stack Arrays for 21 Card Magic Trick
    BoundedStackInterface<String> masterStack = new ArrayStack<>(MGC);
    BoundedStackInterface<String> mHolderStack = new ArrayStack<>(MGC);
    
    BoundedStackInterface<String> rightStack = new ArrayStack<>(SGLE);
    BoundedStackInterface<String> middleStack = new ArrayStack<>(SGLE);
    BoundedStackInterface<String> leftStack = new ArrayStack<>(SGLE);
    BoundedStackInterface<String> tempStack = new ArrayStack<>(SGLE);

    
    
    public Deck()
    {
        // Generates ordered pair of 52 Cards
        int cardValue;
        int currHand;
        int boundary;
        
        for(int index = 3; index >= 0; index--)
        {
            currHand = 14;
            boundary = 2;
            while(currHand >= boundary)
            {
                suitValue = suit[index];
                cardValue = currHand;
                switch(cardValue)
                {
                    case 14: finalCardValue = "Ace";
                             break;
                    case 13: finalCardValue = "King";
                             break;
                    case 12: finalCardValue = "Queen";
                             break;
                    case 11: finalCardValue = "Jack";
                             break;
                    default: finalCardValue = Integer.toString(cardValue);
                             break;
                }
                completeCard = this.toString();
                deckStack.push(completeCard);
                currHand--;
            }
        }
    }
    
    public void DisplayCards()
    // Displays cards currently in DeckStack
    {
        int index = 1;
        while(!deckStack.isEmpty())
        {
            currentCard = (String) deckStack.top();
            holderStack.push(currentCard);
            deckStack.pop();
            System.out.println(index++ + ". " + currentCard);
        }
        // the following method allows us to continously access the DeckStack
        // with original values for security
        while(!holderStack.isEmpty())
        {
            currentCard = (String) holderStack.top();
            deckStack.push(currentCard);
            holderStack.pop();
        }
    }
    
    public void ShuffleCards()
    // Shuffles deck of cards using Random Selection
    {
        int selector;
        for(int index = 0; index < 100; index++)
        {
            while(!deckStack.isEmpty())
            {
                currentCard = (String) deckStack.top();
                rStack.push(currentCard);
                deckStack.pop();
            }
            while(!rStack.isEmpty())
            {
                selector = rand.nextInt(2) + 1;
                if(selector == 2)
                {
                    currentCard = rStack.top();
                    deckStack.push(currentCard);
                    rStack.pop();
                }
                else
                {
                    currentCard = rStack.top();
                    fStack.push(currentCard);
                    rStack.pop();
                }
            }
            while(!fStack.isEmpty())
            {
                currentCard = fStack.top();
                deckStack.push(currentCard);
                fStack.pop();
            }
        }
        this.DisplayCards();
    }
    
    public void genTwenOneStack()
    // builds a stack of 21 cards from a shuffled deck
    {
        while(!masterStack.isFull())
        {
            currentCard = deckStack.top();
            masterStack.push(currentCard);
            deckStack.pop();
        }
    }
    
    public void displayGenTOStack()
    // displays the original 21 card stack in one column 
    // for the user to choose a random card
    {
        int index = 1;
        while(!masterStack.isEmpty())
        {
            currentCard = (String) masterStack.top();
            mHolderStack.push(currentCard);
            masterStack.pop();
            System.out.println(index++ + ". " + currentCard);
        }
        // the following method allows us to continously access the DeckStack
        // with original values for security
        while(!mHolderStack.isEmpty())
        {
            currentCard = (String) mHolderStack.top();
            masterStack.push(currentCard);
            mHolderStack.pop();
        }
    }
    
    public void TwentyOneStack()
    // stacks 21 cards in the three stacks of seven
    {
        while(!masterStack.isEmpty())
        {
            currentCard = (String) masterStack.top();
            rightStack.push(currentCard);
            masterStack.pop();
            currentCard = (String) masterStack.top();
            middleStack.push(currentCard);
            masterStack.pop();
            currentCard = (String) masterStack.top(); 
            leftStack.push(currentCard);
            masterStack.pop();
        }
        
    }

    public void TwentyOneDisplay()
    // displays twenty one cards in three collumns
    // maintains original order and selection
    {
        int index = 1;
        System.out.println("Right Stack: ");
        
        while(!rightStack.isEmpty())
        {
            currentCard = (String) rightStack.top();
            tempStack.push(currentCard);
            System.out.println(index + ". " + currentCard);
            rightStack.pop();
            index++;
        }
        while(!tempStack.isEmpty())
        {
            currentCard = (String) tempStack.top();
            rightStack.push(currentCard);
            tempStack.pop();
            
        }
        
        index = 1;
        
        System.out.println("\nMiddle Stack: ");
        while(!middleStack.isEmpty())
        {
            currentCard = (String) middleStack.top();
            tempStack.push(currentCard);
            System.out.println(index + ". " + currentCard);
            middleStack.pop();
            index++;
        }
        while(!tempStack.isEmpty())
        {
            currentCard = (String) tempStack.top();
            middleStack.push(currentCard);
            tempStack.pop();
        }
        
        index = 1; 
        
        System.out.println("\nLeft Stack: ");
        while(!leftStack.isEmpty())
        {
            currentCard = (String) leftStack.top();
            tempStack.push(currentCard);
            System.out.println(index + ". " + currentCard);
            leftStack.pop();
            index++;
        }
        while(!tempStack.isEmpty())
        {
            currentCard = (String) tempStack.top();
            leftStack.push(currentCard);
            tempStack.pop();
        }
    } 
        
    public void MagicTrick(String answer)
    // user defined selection
    // the magic trick method will determine which order
    // to stack cards based on which stack the card is found
    {
        String finalAnswer = answer.toLowerCase();
        switch(finalAnswer)
        {
            case "right":
                // [Left] => Right => Middle
                while(!leftStack.isEmpty())
                {
                    currentCard = leftStack.top();
                    tempStack.push(currentCard);
                    leftStack.pop();
                }                
                while(!tempStack.isEmpty())
                {
                    currentCard = tempStack.top();
                    masterStack.push(currentCard);
                    tempStack.pop();
                }
                
                // Left => [Right] => Middle
                while(!rightStack.isEmpty())
                {
                    currentCard = rightStack.top();
                    tempStack.push(currentCard);
                    rightStack.pop();
                }
                while(!tempStack.isEmpty())
                {
                    currentCard = tempStack.top();
                    masterStack.push(currentCard);
                    tempStack.pop();
                }
                
                // Left => Right => [Middle]
                while(!middleStack.isEmpty())
                {
                    currentCard = middleStack.top();
                    tempStack.push(currentCard);
                    middleStack.pop();
                }
                while(!tempStack.isEmpty())
                {
                    currentCard = tempStack.top();
                    masterStack.push(currentCard);
                    tempStack.pop();
                }
                break;
                
                
            case "middle":
                // [Left] => Middle => Right
                while(!leftStack.isEmpty())
                {
                    currentCard = leftStack.top();
                    tempStack.push(currentCard);
                    leftStack.pop();
                }                
                while(!tempStack.isEmpty())
                {
                    currentCard = tempStack.top();
                    masterStack.push(currentCard);
                    tempStack.pop();
                }
                
                // Left => [Middle] => Right
                while(!middleStack.isEmpty())
                {
                    currentCard = middleStack.top();
                    tempStack.push(currentCard);
                    middleStack.pop();
                }
                while(!tempStack.isEmpty())
                {
                    currentCard = tempStack.top();
                    masterStack.push(currentCard);
                    tempStack.pop();
                }
                
                // Left => Middle => [Right]
                while(!rightStack.isEmpty())
                {
                    currentCard = rightStack.top();
                    tempStack.push(currentCard);
                    rightStack.pop();
                }
                while(!tempStack.isEmpty())
                {
                    currentCard = tempStack.top();
                    masterStack.push(currentCard);
                    tempStack.pop();
                }
                break;
            
            case "left":
                // [Middle] => Left => Right
                while(!middleStack.isEmpty())
                {
                    currentCard = middleStack.top();
                    tempStack.push(currentCard);
                    middleStack.pop();
                }
                while(!tempStack.isEmpty())
                {
                    currentCard = tempStack.top();
                    masterStack.push(currentCard);
                    tempStack.pop();
                }
                
                // Middle => [Left] => Right
                while(!leftStack.isEmpty())
                {
                    currentCard = leftStack.top();
                    tempStack.push(currentCard);
                    leftStack.pop();
                }                
                while(!tempStack.isEmpty())
                {
                    currentCard = tempStack.top();
                    masterStack.push(currentCard);
                    tempStack.pop();
                }
                
                // Middle => Left => [Right]
                 while(!rightStack.isEmpty())
                {
                    currentCard = rightStack.top();
                    tempStack.push(currentCard);
                    rightStack.pop();
                }
                while(!tempStack.isEmpty())
                {
                    currentCard = tempStack.top();
                    masterStack.push(currentCard);
                    tempStack.pop();
                }
                break;
        }
    }
    
    public String finalDeal()
    // deals final card
    {
        String finalCard = "";
        int boundary = 11;
        for(int index = 0; index < boundary; index++)
        {
            finalCard = masterStack.top();
            masterStack.pop();
        }
        
        return finalCard;
    }
}
