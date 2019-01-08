/**
 * Class for the overall MatchGame using Card, Deck, and Grid object classes
 *
 * @author Katelyn Bunker
 */
public class MatchGame {
    
    /** Class constant for the number of rows */
    public static final int ROWS = 4;
    /** Class constant for the number of columns */
    public static final int COLS = 13;
    
    /** Field for the Deck object */
    private Deck deck;
    /** Field for the Grid object */
    private Grid grid;
    /** Field for the number of correct guesses made */
    private int correctGuesses;
    /** Field for the total number of guesses made */
    private int totalGuesses;
    /** Field for whether the game is on easy mode or not */
    private boolean isEasyGame;
    
    /**
     * Constructor for the MatchGame setting the grid and making the deck and cards
     *
     * @param isTesting boolean for whether the game is in testing and should be shuffled or not
     * @param isEasy boolean for whether both values and colors have to match or just values
     */
    public MatchGame(boolean isTesting, boolean isEasy) {
        isEasyGame = isEasy;
        //creates the Deck
        deck = new Deck();
        if (isTesting == false) {
            //shuffles the deck if not in testing mode
            deck.shuffle();
        }
        //Creates the grid of cards
        grid = new Grid(ROWS, COLS);
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                grid.setCard(deck.nextCard(), i, j);
            }
        }
    }
    
    /**
     * Gets the number of guesses made
     *
     * @return the number of total guesses
     */
    public int getNumberOfGuesses() {
        return totalGuesses;
    }
    
    /**
     * Gets the number of correct guesses made by the user
     *
     * @return the number of correct guesses
     */
    public int getNumberOfCorrectGuesses() {
        return correctGuesses;
    }
    
    /**
     * Gets the average of the correct guesses to total guesses
     *
     * @return the average of the guesses
     */
    public double getGuessAverage() {
        if (totalGuesses == 0) {
            return 0.0;
        } else {
            return (double) correctGuesses / totalGuesses;
        }
    }
    
    /**
     * Gets the Card name at the specified index using the Card getName method
     *
     * @param row the row index of the card to get
     * @param col the column index of the card to get
     * @return the String of the name of the Card at the specified index
     */
    public String getCardName(int row, int col) {
        Card c = grid.getCard(row, col);
        String s = c.getName();
        return s;
    }
    
    /**
     * Gets whether the card has already been found
     *
     * @param row the row index of the card to test
     * @param col the column index of the card to test
     * @return whether the card has already been found
     */
    public boolean isFound(int row, int col) {
        Card c = grid.getCard(row, col);
        return c.isFound();
    }
    
    /**
     * tests whether the two cards match based on value only if easy or value and color if hard
     *
     * @param card1Row the row index for the first card
     * @param card1Col the column index for the first card
     * @param card2Row the row index for the second card
     * @param card2Col the column index for the second card
     * @return whether the two cards match
     */
    public boolean isMatch(int card1Row, int card1Col, int card2Row, int card2Col) {
        totalGuesses++;
        
        Card c1 = grid.getCard(card1Row, card1Col);
        Card c2 = grid.getCard(card2Row, card2Col);
        
        boolean card1IsFound = c1.isFound();
        boolean card2IsFound = c2.isFound();
        boolean isMatch;
        
        if (isEasyGame) {
            isMatch = c1.isValueMatch(c2);
        } else {
            isMatch = c1.isValueAndColorMatch(c2);
        }
        
        if (!card1IsFound && !card2IsFound && isMatch) {
            correctGuesses++;
            c1.setIsFound(true);
            c2.setIsFound(true);
            return true;
        } else {
            return false;
        }
        
        
    }
    
    /**
     * Tests the game and prints out the grid of cards
     *
     * @param args array of command line arguments
     */
    public static void main(String[] args) {
        MatchGame game = new MatchGame(true, true); 
        for (int i = 0; i < MatchGame.ROWS; i++) {
            for (int j = 0; j < MatchGame.COLS; j++) {
                System.out.print(game.getCardName(i,j) + " ");
            }
            System.out.println();
        }      
    }
}