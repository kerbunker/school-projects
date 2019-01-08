/**
 * Object class for the Grid for the MatchGame
 *
 * @author Katelyn Bunker
 */
public class Grid {
    
    /** Field for the number of rows in the grid */
    private int rows;
    /** Field that represents the number of columns in the grid */
    private int columns;
    /** Field for the Card object Array */
    private Card[][] cardArray;
    
    /**
     * Constructor for the Grid using the specified rows and columns
     *
     * @param rows the number of rows for the grid
     * @param cols the number of columns for the Grid
     */
    public Grid(int rows, int cols) {
        this.rows = rows;
        columns = cols;
        //creates a new card array for the cards to go into the Grid
        cardArray = new Card[rows][columns];
    }
    
    /**
     * Sets the specified Card object at the row and column given into the grid
     *
     * @param card the Card object to put into the grid
     * @param row the row of the array to put the Card
     * @param col the column in the array to put the card
     */
    public void setCard(Card card, int row, int col) {
        //throws exception if invalid row
        if (row < 0 || row > rows) {
            throw new IllegalArgumentException("invalid row value");
            //throws exception if invalid column
        } else if (col < 0 || col > columns) {
            throw new IllegalArgumentException("invalid column value");
        } else {
            //sets the card at the specified location
            cardArray[row][col] = card;
        }
    }
        
    /**
     * Gets the Card at a specific index of the array
     *
     * @param row the row index to get the card from
     * @param col the column index to get the card from
     * @return the Card at the given index
     */
    public Card getCard(int row, int col) {
        //throws exception for invalid row or column value
        if (row < 0 || row > rows) {
            throw new IllegalArgumentException("invalid row value");
        } else if (col < 0 || col > columns) {
            throw new IllegalArgumentException("invalid column value");
        } else {
            //returns the card at the specified location
            return cardArray[row][col];
        }
    }
}