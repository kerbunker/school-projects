import java.util.*;

/** Object class for the Deck containing Card objects for the MatchGame
 *
 * @author Katelyn Bunker
 */
public class Deck {
    /** class constant for the number of cards in the deck */
    public static final int CARDS_IN_DECK = 52;
    /** Class constant for the number of times cards should be shuffled */
    public static final int SHUFFLE_SWAPS = 500;
    /** Class constant for number of suits of the cards */
    public static final int SUITS_OF_CARDS = 4;
    
    /** field for the Cards array */
    private Card[] cards;
    /** field for index card is in the cards array */
    private int index;
    
    /**
     * Constructor for the Deck Object, setting the card array and making each Card object
     */
    public Deck() {
        //Makes new Card array
        cards = new Card[CARDS_IN_DECK];
        char cSuit;
        //sets the suit depending on the index of the card
        for (int i = 0; i < SUITS_OF_CARDS; i++) {
            if (i == 0) {
                cSuit = Card.CLUBS;
            } else if (i == 1) {
                cSuit = Card.DIAMONDS;
            } else if (i == 2) {
                cSuit = Card.HEARTS;
            } else {
                cSuit = Card.SPADES;
            }
            //cycles through each of the 52 indexes to add a new Card to the array
            for (int j = 0; j <= Card.HIGHEST_VALUE - 2; j++) {
                cards[j + ((Card.HIGHEST_VALUE - 1) * i)] = new Card(cSuit, j + Card.LOWEST_VALUE);
            }
        }
    }
    
    /**
     * Shuffles the deck the specified number of times
     */
    public void shuffle() {
        Random r = new Random();
        int num1;
        int num2;
        Card a;
        Card b;
        
        for (int i = 0; i < SHUFFLE_SWAPS; i++) {
            //gets 2 random numbers for the 2 cards to swap in the deck
            num1 = r.nextInt(CARDS_IN_DECK);
            num2 = r.nextInt(CARDS_IN_DECK);
            
            //Swaps card 1 with card 2
            a = cards[num1];
            b = cards[num2];
            cards[num1] = b;
            cards[num2] = a;
        }
        
        
    }
    
    /**
     * Gets card at the next index then increments the index by 1
     *
     * @return the Card object at the specified index
     */
    public Card nextCard() {
        int num = index;
        index++;
        return cards[num];
    }
    
    /**
     * Gets the string for each of the cards in the deck using the toString method of the Card class
     *
     * @return the String of the cards in the deck
     */
    public String toString() {
        String s = "";
        Card c1;
        for(int i = 0; i < CARDS_IN_DECK; i++) {
            c1 = cards[i];
            s = s + c1.toString() + "\n";
        }
        return s;
    }
    
    
}