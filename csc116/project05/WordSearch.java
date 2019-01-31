import java.util.*;
import java.io.*;
/**
 * Creates and plays a WordSearch game from a given word file
 *
 * @author Katelyn Bunker
 */
public class WordSearch {
    /** class constant for number of rows */
    public static final int ROWS = 11;
    /** class constant for number of columns */
    public static final int COLUMNS = 26;
    /** class constant for number of rounds to play */
    public static final int ROUNDS = 10;
    /** constant for alphabet for random number generation */
    public static final int ALPHA = 26;
    /** constant for addition to ASC value for capitol letters */
    public static final int ASC = 65;
    /**
     * Starts the program
     *
     * @param args array of command line arguments
     */
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        Scanner input = null;
        //outputs error message if no file given, or twoo many are given
        if (args.length != 1) {
            System.out.println("Usage: java WordSearch wordsearchfilename");
            System.exit(1);
        } else {
            File game = new File(args[0]);
            try {
                input = new Scanner(game);
            } catch (FileNotFoundException e) {
                System.out.println(args[0] + " (No such file or directory)");
                System.out.println("Usage: java WordSearch wordsearchfilename");
                System.exit(1);
            }
        }
        //gets game title from first line
        String gameTitle = input.nextLine();
        Scanner lineScanner = null;
        String line = null;
        Word[] words = new Word[10];
        int i = 0;
        boolean h = false;
        //creates words array from word file
        while(input.hasNextLine()) {
            line = input.nextLine();
            lineScanner = new Scanner(line);
            String gameWord = lineScanner.next();
            int wordRow = lineScanner.nextInt();
            int wordColumn = lineScanner.nextInt();
            String dir = lineScanner.next();
            if(dir.charAt(0) == 'H') {
                h = true;
            } else {
                h = false;
            }
            words[i] = new Word(gameWord, wordRow, wordColumn, h);
            i++;
        }
        char[][] puzzle = createPuzzle(ROWS, COLUMNS, words);
        obscurePuzzle(puzzle);

        playGame(console, gameTitle, words, puzzle);
        
    }
    /**
     * Creates and returns a @D char array with the given number of rows and columns.
     * 
     * @param rows number of rows in the array
     * @param cols number of columns in the array
     * @param words array of words for the wordsearch game
     * @return the char array of the puzzle with given words added
     */
    public static char[][] createPuzzle(int rows, int cols, Word[] words) {
        if(words == null) {
            throw new NullPointerException("words cannot be null");
        }
        
        int r = 0;
        int c = 0;
        String w = null;
        if(rows < 1 || cols < 1) {
            throw new IllegalArgumentException();
        }
        
        
        
        for(int i = 0; i < words.length; i++) {
            if(words[i] == null) {
                throw new NullPointerException("words[i] cannot be null");
            }
        }
        
        char[][] puzzle = new char[rows][cols];
        //gets data from Word objects
        for(int i = 0; i < words.length; i++) {
            r = words[i].getRow();
            c = words[i].getCol();
            w = words[i].getWord();
            if(words[i].isHorizontal()){
                //adds horizontal words to puzzle array
                for(int j = 0; j < w.length(); j++) {
                    puzzle[r][c + j] = w.charAt(j);
                }
            } else {
                //adds vertical words to puzzle array
                for(int j = 0; j < w.length(); j++) {
                    puzzle[r + j][c] = w.charAt(j);
                }
            }
        }

        for(int i = 0; i < puzzle.length; i++) {
            for(int j = 0; j < puzzle[i].length; j++){
                //adds spaces to all the nonletter array values
                if(!Character.isLetter(puzzle[i][j])) {
                    puzzle[i][j] = ' ';
                }

            } 
        }
        
        
        return puzzle;
    }
    
    /**
     * Replaces each space in the @D puzzle array with a random uppercase letter
     *
     * @param puzzle the char array of the word search puzzle
     */
    public static void obscurePuzzle(char[][] puzzle) {
        if(puzzle == null) {
            throw new NullPointerException("puzzle cannot be null");
        }
        for (int i = 0; i < puzzle.length; i++) {
            if (puzzle[i] == null) {
                throw new NullPointerException("row in puzzle cannot be null");
            }
        }
        int len = puzzle[0].length;
        for (int i = 0; i < puzzle.length; i++) {
            if (puzzle[i].length != len) {
                throw new IllegalArgumentException("puzzle cannot be jagged");
            }
            
        }
        Random r = new Random();
        int code = 0;
        for (int i = 0; i < puzzle.length; i++) {
            for (int j = 0; j < puzzle[i].length; j++) {
                if (puzzle[i][j] == ' ') {
                    //gets random uppercase Letter
                    code = r.nextInt(ALPHA) + ASC;

                    char l = (char) code;
                    //fills in spaces with random letter
                    puzzle[i][j] = l;
                }
            }
        }
    }
    
    /**
     * Handles the play for the word search game
     * Outputs the instruction for the game
     * Repeatedly displays the ObscuredPuzzle and prompts the user to enter a word
     *
     * @param console scanner for input from the user
     * @param title the title of the WordSearch game
     * @param words the array of Word objects used in the game
     * @param puzzle the char array that makes up the puzzle
     */
    public static void playGame(Scanner console, String title, Word[] words, char[][] puzzle) {
        if (puzzle == null) {
            throw new NullPointerException("puzzle cannot be null");
        }
        for (int i = 0; i < puzzle.length; i++) {
            if (puzzle[i] == null) {
                throw new NullPointerException("puzzle[i] cannot be null");
            }
        }
        
        int len = puzzle[0].length;
        for (int i = 0; i < puzzle.length; i++) {
            if (puzzle[i].length != len) {
                throw new IllegalArgumentException("puzzle cannot be jagged");
            }
        }
        
        if (console == null) {
            throw new NullPointerException("console cannot be null");
        } else if (title == null) {
            throw new NullPointerException("title cannot be null");
        } else if (words == null) {
            throw new NullPointerException("words cannot be null");
        }
        
        for (int i = 0; i < words.length; i++) {
            if (words[i] == null) {
                throw new NullPointerException("words[i] cannot be null");
            }
        }
        
        
        
        System.out.println();
        System.out.println("See how many of the 10 hidden words you can find!");
        System.out.println();
        String guess = null;
        int count = 0;
        for (int i = 1; i <= ROUNDS; i++) {//plays for 10 rounds
            displayPuzzle(title, puzzle);//displays the title
            System.out.println();
            System.out.print("Word " + i + ": ");//gets the guess form user
            guess = console.next();
            guess = guess.toUpperCase();
            Word puzzleWord = isPuzzleWord(guess, words);
            if (puzzleWord != null) {//finds if the word is in the puzzle
                updatePuzzle(puzzleWord, puzzle);
                count ++;//counts number of found words
            } else {
                System.out.println("Sorry, " + guess + " is not a puzzle word!");
            }
            System.out.println();
        }
        displayPuzzle(title, puzzle);
        System.out.println();
        System.out.println("You found " + count + " out of 10 words!");
        System.out.println();
        System.out.println("Thanks for playing, have a nice day!");
        
    }
    
    /**
     * Outputs the title of the puzzle followed by characters contained
     * in the 2D puzzle array
     * 
     * @param title the title of the Word Search game
     * @param puzzle the char array of the word search puzzle
     */
    public static void displayPuzzle(String title, char[][] puzzle) {
        if (title == null){
            throw new NullPointerException("title cannot be null");
        }
        if (puzzle == null){
            throw new NullPointerException("puzzle cannot be null");
        }
        
        for (int i = 0; i < puzzle.length; i++) {
            if (puzzle[i] == null) {
                throw new NullPointerException("puzzle[i] cannot be null");
            }
        }
        
        
        int len = puzzle[0].length;
        for (int i = 0; i < puzzle.length; i++) {
            if (puzzle[i].length != len) {
                throw new IllegalArgumentException("puzzle cannot be jagged");
            }
        }
        System.out.println(title);//prints title
        System.out.println();
        for (int i = 0; i < puzzle.length; i++) {
            for (int j = 0; j < puzzle[i].length; j++) {
                System.out.print(puzzle[i][j]);//prints out each character in the array
            }
            System.out.println();
        }
        
    }
    
    /**
     * If the guess is in the words array and has not been found yet
     * The corresponding word object is returned
     * otherwise null is returned
     *
     * @param guess the user's guess for a word search word
     * @param words the array of words in the puzzle
     * @return the Word object of the found word
     */
    public static Word isPuzzleWord (String guess, Word[] words) {
        if (guess == null) {
            throw new NullPointerException("guess cannot be null");
        }
        if (words == null) {
            throw new NullPointerException("words cannot be null");
        }
        for (int i = 0; i < words.length; i++) {
            if (words[i] == null) {
                throw new NullPointerException("words[i] cannot be null");
            }
        }
        guess = guess.toUpperCase();
        String w = null;
        for (int i = 0; i < words.length; i++) {
            w = words[i].getWord();
            if (w.equals(guess)) {//tests if guess is one of the puzzle words
                if (!words[i].isFound()) {//tests whether word is found already
                    words[i].setIsFound(true);//sets word as found
                    return words[i];
                }
                
            }
        }
        return null;
    }
    
    /**
     * The word is set as found and the puzzle array is updated
     * so that the word is replaced by asterisks
     *
     * @param word the word object of the found word
     * @param puzzle the char array of the word search game puzzle
     */
    public static void updatePuzzle(Word word, char[][] puzzle) {
        if (word == null) {
            throw new NullPointerException("word cannot be null");
        }
        
        
        
        if (puzzle == null) {
            throw new NullPointerException("puzzle cannot be null");
        }
        
        for (int i = 0; i < puzzle.length; i++) {
            if (puzzle[i] == null) {
                throw new NullPointerException("puzzle[i] cannot be null");
            }
        }
        
        int len = puzzle[0].length;
        for (int i = 0; i < puzzle.length; i++) {
            if (puzzle[i].length != len) {
                throw new IllegalArgumentException("puzzle cannot be jagged");
            }
        }
        String w = word.getWord();//gets the found word
        int r = word.getRow();//gets the row of the found word
        int c = word.getCol();//gets the column of the found word
        if (word.isHorizontal()) {
            for (int i = 0; i < w.length(); i++) {
                puzzle[r][c + i] = '*';//replaces found word with '*' if horizontal
            }
        } else {
            for (int i = 0; i < w.length(); i++) {
                puzzle[r + i][c] = '*';//replaces the found word with '*' if vertical
            }
        }
    }
}