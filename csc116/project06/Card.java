import java.awt.*;

/**
 * Object class for the Card object for the MatchGame
 *
 * @author Katelyn Bunker
 */
public class Card {
    /** class constant for clubs char */
    public static final char CLUBS = 'c';
    /** class constant for diamonds char */
    public static final char DIAMONDS = 'd';
    /** class constant for hearts char */
    public static final char HEARTS = 'h';
    /** class constant for spades char */
    public static final char SPADES = 's';
    /** class constant for the lewest card value */
    public static final int LOWEST_VALUE = 2;
    /** class constant for the highest card value */
    public static final int HIGHEST_VALUE = 14;
    
    /** field for the suit of the card */
    private char suit;
    /** field for the value of the card */
    private int value;
    /** field for the card color */
    private Color color;
    /** field for whether the card is found or not */
    private boolean isFound;
    
    /**
     * Constructor for the Card object
     *
     * @param suit the char for the suit type
     * @param value the int for the card's value
     */
    public Card(char suit, int value) {
        //throws exception if the value is not valid
        if (value < LOWEST_VALUE || value > HIGHEST_VALUE) {
            throw new IllegalArgumentException("value not valid");
        //sets the card data for the red suits
        } else if (suit == HEARTS || suit == DIAMONDS) {
            this.suit = suit;
            this.value = value;
            color = Color.RED;
            isFound = false;
        //sets the card data for the black suits
        } else if (suit == CLUBS || suit == SPADES) {
            this.suit = suit;
            this.value = value;
            color = Color.BLACK;
            isFound = false;
        } else {
            //throws exception if not one of the valid suits
            throw new IllegalArgumentException("suit not valid");
        }
    }
    
    /**
     * Getter method for the suit of the card
     *
     * @return the char of the card's suit
     */
    public char getSuit() {
        return suit;
    }
    
    /**
     * Getter method for the value of the card
     *
     * @return the int of the card's value
     */
    public int getValue() {
        return value;
    }
    
    /**
     * Getter method for the color of the card
     *
     * @return the Color object for the color of the card
     */
    public Color getColor() {
        return color;
    }
    
    /**
     * Getter method for whether the card is already found
     *
     * @return whether the card has already been found
     */
    public boolean isFound() {
        return isFound;
    }
    
    /**
     * Returns a string of the card suit and value in the form "c2"
     *
     * @return card name String
     */
    public String getName() {
        return "" + suit + value;
    }
    
    /**
     * Sets the isFound boolean to the passed in parameter
     *
     * @param isFound the boolean to set the field to
     */
    public void setIsFound(boolean isFound) {
        this.isFound = isFound;
    }
    
    /**
     * Tests the values of two cards to decide if they match (easy mode)
     *
     * @param other the second card to compare
     * @return whether the cards match
     */
    public boolean isValueMatch(Card other) {
        return value == other.getValue();
    }
    
    /**
     * Tests whether the value and color of the cards match (hard mode)
     *
     * @param other the second card for the comparison
     * @return whether the values and colors of the cards match
     */
    public boolean isValueAndColorMatch(Card other) {
        return (value == other.getValue() && color == other.getColor());
    }
    
    /**
     * Creates a String of the data for the Card object
     *
     * @return the String of the card's data
     */
    public String toString() {
        String c;
        if (color == Color.RED) {
            c = "red";
        } else {
            c = "black";
        }
        String s = "" + suit + value + " is a " + c + " card and isFound is " + isFound;
        return s;
    }
    
    /**
     * Tests whether 2 cards are equals
     *
     * @param o the second object for the comparison
     * @return whether the cards are matching
     */
    public boolean equals(Object o) {
        //finds whether the object passed in is a Card object
        if (o instanceof Card) {
            Card c = (Card) o;
            //compares the fields for the two cards
            return suit == c.getSuit() && value == c.getValue() && color == c.getColor()
                && isFound == c.isFound();
        } else {
            //returns false if o is not a Card
            return false;
        }
    }
}