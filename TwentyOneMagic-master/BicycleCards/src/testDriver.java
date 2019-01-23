//----------------------------------------------------------------------------
// testDriver.java           by Page                           Chapter 3
//
// Main Tester for displaying Unshuffled and Shuffled Cards
// In addition, the TestDriver implements an optional magic trick
//----------------------------------------------------------------------------

import homeworkCh3.*;
import java.util.Scanner;

public class testDriver
{
    public static void main(String[] args) 
    {
        Scanner scan = new Scanner(System.in);
        Boolean condition = false;
        String answer;
        String chosenCard;
        int counter = 0;
        
        System.out.println("The following program prints out a deck of shuffled and unshuffled cards\n");
        System.out.println("Unshuffled Cards: ");
        System.out.println();
        
        Deck deck = new Deck();
        deck.DisplayCards();
        
        System.out.println();
        System.out.println();
        
        System.out.println("Shuffled Cards: ");
        deck.ShuffleCards();
        
        // Allows the user the option to try the magic trick
        System.out.print("\nWould you like to see a magic trick? (y/n): ");
        answer = scan.next();
        if(answer.equalsIgnoreCase("y"))
        {
            // Beginning of magic trick
            System.out.println("\n");
            System.out.println("21 Magic Card Trick: ");
            System.out.println("Please select a random card from the list below: \n");
        
            // launches method for first 21 stack of cards
            deck.genTwenOneStack();
            deck.displayGenTOStack();
            System.out.println();
        
            // allows user time to select card
            while(!condition)
            {
                System.out.print("Have you selected your random card (y/n): ");
                answer = scan.next();
                condition = (answer.equalsIgnoreCase("Y")) ? true : false;
            }
        
            // iteration of three times through the random stack
            // each iteration is user defined by which card they select
            while(counter < 3)
            {
                System.out.println("\nThe program will now deal the twenty-one cards in three stacks\n");
                deck.TwentyOneStack();
                deck.TwentyOneDisplay();
                System.out.print("\nPlease select Stack(i.e. right, middle, left) in which your selected card is in: ");
                answer = scan.next();
                deck.MagicTrick(answer);
                counter++;
                
            }
        
            // displays final card result
            chosenCard = deck.finalDeal();
            System.out.println("\nYour secret card is " + chosenCard);
        }
        else
        {
            System.out.println("Goodbye!");
        }
    }
}
