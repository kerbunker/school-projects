import java.util.Arrays;
import java.util.Scanner;

/**
 * Program to test WordSearch
 * 
 * @author
 */
public class WordSearchTest {

    /** Constant for passing test output */
    public static final String PASS = "PASS";
    /** Constant for failing test output */
    public static final String FAIL = "FAIL";

    /** Counter for test cases */
    public static int testCounter = 0;
    /** Counter for passing test cases */
    public static int passingTestCounter = 0;

    /**
     * Starts the test program
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        testCreatePuzzle();
        testObscurePuzzle();
        testIsPuzzleWord();
        testUpdatePuzzle();
        testPlayGame();
        testDisplayPuzzle();

        System.out.println("\n***************************************");
        System.out.println("*               Results               *");
        System.out.println("***************************************");
        System.out.printf("%4d / %4d passing tests%n", passingTestCounter, testCounter);
    }

    public static void testUpdatePuzzle() {
        Word word = new Word("BRUSH", 0, 1, true);
        String id = "Update with word";
        String description = "WordSearch.updatePuzzle(word, actual)";
        char[][] actual = { { 'A', 'B', 'R', 'U', 'S', 'H', 'B' } };
        // Want separate copy since obscurePuzzle changes array.
        char[][] expected = { { 'A', '*', '*', '*', '*', '*', 'B' } };
        WordSearch.updatePuzzle(word, actual);
        testResult(id, description, expected, actual);
		
		
		Word word2 = new Word("PIZZA", 2, 1, true);
		id = "Update multi line word";
        description = "WordSearch.updatePuzzle(word, actual)";
        char[][] actual2 = { { 'A', 'B', 'C', 'D', 'E', 'F', 'G' }, 
				{ 'H', 'I', 'J', 'K', 'L', 'M', 'N'},
				{'O', 'P', 'I', 'Z', 'Z', 'A', 'P'} };
        char[][] expected2 = { { 'A', 'B', 'C', 'D', 'E', 'F', 'G' }, 
				{ 'H', 'I', 'J', 'K', 'L', 'M', 'N'},
				{'O', '*', '*', '*', '*', '*', 'P'} };
        WordSearch.updatePuzzle(word2, actual2);
        testResult(id, description, expected2, actual2);
		
		
		Word word3 = new Word("BLUE", 0, 1, false);
		id = "Update vertical word";
        description = "WordSearch.updatePuzzle(word, actual)";
        char[][] actual3 = { { 'A', 'B', 'B', 'C', 'D', 'E', 'F' }, 
				{ 'G', 'L', 'H', 'I', 'J', 'K', 'L'},
				{ 'M', 'U', 'N', 'O', 'P', 'Q', 'R'},
				{'S', 'E', 'T', 'U', 'V', 'W', 'X'} };
        char[][] expected3 = { { 'A', '*', 'B', 'C', 'D', 'E', 'F' }, 
				{ 'G', '*', 'H', 'I', 'J', 'K', 'L'},
				{ 'M', '*', 'N', 'O', 'P', 'Q', 'R'},
				{'S', '*', 'T', 'U', 'V', 'W', 'X'} };
        WordSearch.updatePuzzle(word3, actual3);
        testResult(id, description, expected3, actual3);



        id = "word is null";
        description = "WordSearch.updatePuzzle(null, actual);";
        String message = "";
        try {
            WordSearch.updatePuzzle(null, actual);
            message = null;
        } catch (NullPointerException e) {
            message = e.getMessage();
        }
        testResultExMessage(id, description, message);

        id = "puzzle is null";
        description = "WordSearch.updatePuzzle(word, null);";
        word = new Word("BRUSH", 0, 1, true);
        message = "";
        try {
            WordSearch.updatePuzzle(word, null);
            message = null;
        } catch (NullPointerException e) {
            message = e.getMessage();
        }
        testResultExMessage(id, description, message);

        id = "puzzle contains null row";
        description = "WordSearch.updatePuzzle(word, puz);";
        word = new Word("BRUSH", 0, 1, true);
        char[][] puz = new char[3][];
        message = "";
        try {
            WordSearch.updatePuzzle(word, puz);
            message = null;
        } catch (NullPointerException e) {
            message = e.getMessage();
        }
        testResultExMessage(id, description, message);

        id = "puzzle jagged";
        description = "WordSearch.updatePuzzle(word, puz);";
        word = new Word("BRUSH", 0, 1, true);
        puz = new char[3][];
        puz[0] = new char[3];
        puz[1] = new char[3];
        puz[2] = new char[1];
        String exp = "class java.lang.IllegalArgumentException";
        String actualResult = "";
        try {
            WordSearch.updatePuzzle(word, puz);
            actualResult = "No exception thrown";
        } catch (IllegalArgumentException e) {
            actualResult = "" + e.getClass();
        }
        testResult(id, description, exp, actualResult);
    }

    public static void testIsPuzzleWord() {

        // Create Word array with room for 4 Word objects
        Word[] words = new Word[4];

        // Create and add 4 Word objects to the words array
        words[0] = new Word("BRUSH", 2, 6, true);
        words[1] = new Word("SOAP", 0, 2, false);
        words[2] = new Word("SHAMPOO", 3, 4, true);
        words[3] = new Word("LOTION", 4, 2, true);

        String id = "Guess brush when not found yet";
        String description = "WordSearch.isPuzzleWord(\"brush\", words)";
        Word expected = words[0];
        Word actual = WordSearch.isPuzzleWord("brush", words);
        testResult(id, description, expected, actual);
		
		id = "Guess brush when already found";
		description = "WordSearch.isPuzzleWord(\"brush\", words)";
		expected = null;
		actual = WordSearch.isPuzzleWord("brush", words);
		testResult(id, description, expected, actual);
		
		id = "Guess is not a puzzle word";
		description = "WordSearch.isPuzzleWord(\"pizza\", words)";
		expected = null;
		actual = WordSearch.isPuzzleWord("pizza", words);
		testResult(id, description, expected, actual);



        id = "guess is null";
        description = "WordSearch.isPuzzleWord(null, words)";
        String message = "";
        try {
            WordSearch.isPuzzleWord(null, words);
            message = null;
        } catch (NullPointerException e) {
            message = e.getMessage();
        }
        testResultExMessage(id, description, message);

        id = "words is null";
        description = "WordSearch.isPuzzleWord(\"brush\", null)";
        message = "";
        try {
            WordSearch.isPuzzleWord("brush", null);
            message = null;
        } catch (NullPointerException e) {
            message = e.getMessage();
        }
        testResultExMessage(id, description, message);

        id = "words contains null element";
        description = "WordSearch.isPuzzleWord(\"bob\", wordsNull)";
        Word[] wordsNull = new Word[3];
        wordsNull[0] = new Word("BRUSH", 0, 1, true);
        message = "";
        try {
            WordSearch.isPuzzleWord("bob", wordsNull);
            message = null;
        } catch (NullPointerException e) {
            message = e.getMessage();
        }
        testResultExMessage(id, description, message);
    }

    public static void testObscurePuzzle() {

        String id = "Obscure copy of starter";
        String description = "WordSearch.obscurePuzzle(obscured)";
        char[][] starter = { { ' ', 'B', 'R', 'U', 'S', 'H', ' ' } };
        // Want separate copy since obscurePuzzle changes array.
        char[][] obscured = { { ' ', 'B', 'R', 'U', 'S', 'H', ' ' } };
        WordSearch.obscurePuzzle(obscured);
        testResultObscured(id, description, starter, obscured);
		
		
		id = "Obsure multi line puzzle";
		description = "WordSearch.obsurePuzzle(obscured)";
		char[][] starter2 = { { ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
							{ ' ', 'P', 'I', 'Z', 'Z', 'A', ' ' } };
							
		char[][] obscured2 = { { ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
							{ ' ', 'P', 'I', 'Z', 'Z', 'A', ' ' } };
							WordSearch.obscurePuzzle(obscured2);
							testResultObscured(id, description, starter2, obscured2);

        id = "Obsure multi line, multi word puzzle";
		description = "WordSearch.obsurePuzzle(obscured)";
		char[][] starter3 = { { ' ', 'B', 'L', 'U', 'E', ' ', ' ' },
							{ ' ', 'P', 'I', 'Z', 'Z', 'A', ' ' } };
							
		char[][] obscured3 = { { ' ', 'B', 'L', 'U', 'E', ' ', ' ' },
							{ ' ', 'P', 'I', 'Z', 'Z', 'A', ' ' } };
							WordSearch.obscurePuzzle(obscured3);
							testResultObscured(id, description, starter3, obscured3);
	   

 
        id = "puzzle is null";
        description = "WordSearch.obscurePuzzle(null)";
        String message = "";
        try {
            WordSearch.obscurePuzzle(null);
            message = null;
        } catch (NullPointerException e) {
            message = e.getMessage();
        }
        testResultExMessage(id, description, message);

        id = "puzzle contains null row";
        description = "WordSearch.obscurePuzzle(puz);";
        char[][] puz = new char[3][];
        message = "";
        try {
            WordSearch.obscurePuzzle(puz);
            message = null;
        } catch (NullPointerException e) {
            message = e.getMessage();
        }
        testResultExMessage(id, description, message);

        id = "puzzle jagged";
        description = "WordSearch.obscurePuzzle(puz);";
        puz = new char[3][];
        puz[0] = new char[3];
        puz[1] = new char[3];
        puz[2] = new char[1];
        String exp = "class java.lang.IllegalArgumentException";
        String actualResult = "";
        try {
            WordSearch.obscurePuzzle(puz);
            actualResult = "No exception thrown";
        } catch (IllegalArgumentException e) {
            actualResult = "" + e.getClass();
        }
        testResult(id, description, exp, actualResult);
    }

    /**
     * Tests createPuzzle method
     */
    public static void testCreatePuzzle() {

        // Create Word array with room for 4 Word objects
        Word[] words = new Word[4];

        // Create and add 4 Word objects to the words array
        words[0] = new Word("BRUSH", 2, 6, true);
        words[1] = new Word("SOAP", 0, 2, false);
        words[2] = new Word("SHAMPOO", 3, 4, true);
        words[3] = new Word("LOTION", 4, 2, true);

        String id = "Create with words";
        String description = "WordSearch.createPuzzle(7, 12, words)";
        char[][] expected = { { ' ', ' ', 'S', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
                        { ' ', ' ', 'O', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
                        { ' ', ' ', 'A', ' ', ' ', ' ', 'B', 'R', 'U', 'S', 'H', ' ' },
                        { ' ', ' ', 'P', ' ', 'S', 'H', 'A', 'M', 'P', 'O', 'O', ' ' },
                        { ' ', ' ', 'L', 'O', 'T', 'I', 'O', 'N', ' ', ' ', ' ', ' ' },
                        { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
                        { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' } };
        char[][] actual = WordSearch.createPuzzle(7, 12, words);
        testResult(id, description, expected, actual);

		
		Word[] words2 = new Word[4];
		
		words2[0] = new Word("PIZZA", 0, 1, false);
		words2[1] = new Word("BLUE", 1, 4, false);
		words2[2] = new Word("JAVA", 1, 5, false);
		words2[3] = new Word("MOUSE", 2, 9, false);
		
		id = "Create all vertical";
		description = "WordSearch.createPuzzle(7, 12, words)";
		char[][] expected2 = { { ' ', 'P', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
                        { ' ', 'I', ' ', ' ', 'B', 'J', ' ', ' ', ' ', ' ', ' ', ' ' },
                        { ' ', 'Z', ' ', ' ', 'L', 'A', ' ', ' ', ' ', 'M', ' ', ' ' },
                        { ' ', 'Z', ' ', ' ', 'U', 'V', ' ', ' ', ' ', 'O', ' ', ' ' },
                        { ' ', 'A', ' ', ' ', 'E', 'A', ' ', ' ', ' ', 'U', ' ', ' ' },
                        { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'S', ' ', ' ' },
                        { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'E', ' ', ' ' } };
		char[][] actual2 = WordSearch.createPuzzle(7, 12, words2);
        testResult(id, description, expected2, actual2);
		
		
		Word[] words3 = new Word[4];
		
		words3[0] = new Word("PIZZA", 0, 1, true);
		words3[1] = new Word("BLUE", 2, 0, true);
		words3[2] = new Word("JAVA", 5, 4, true);
		words3[3] = new Word("MOUSE", 2, 7, true);
		
		id = "Create all horizontal";
		description = "WordSearch.createPuzzle(7, 12, words)";
		char[][] expected3 = { { ' ', 'P', 'I', 'Z', 'Z', 'A', ' ', ' ', ' ', ' ', ' ', ' ' },
                        { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
                        { 'B', 'L', 'U', 'E', ' ', ' ', ' ', 'M', 'O', 'U', 'S', 'E' },
                        { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
                        { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
                        { ' ', ' ', ' ', ' ', 'J', 'A', 'V', 'A', ' ', ' ', ' ', ' ' },
                        { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' } };
		char[][] actual3 = WordSearch.createPuzzle(7, 12, words3);
        testResult(id, description, expected3, actual3);
		
						

        id = "words is null";
        description = "WordSearch.createPuzzle(7, 12, null)";
        String message = "";
        try {
            WordSearch.createPuzzle(7, 12, null);
            message = null;
        } catch (NullPointerException e) {
            message = e.getMessage();
        }
        testResultExMessage(id, description, message);

        id = "words contains null element";
        description = "WordSearch.createPuzzle(7, 12, wordsNull)";
        Word[] wordsNull = new Word[3];
        wordsNull[0] = new Word("BRUSH", 0, 1, true);
        message = "";
        try {
            WordSearch.createPuzzle(7, 12, wordsNull);
            message = null;
        } catch (NullPointerException e) {
            message = e.getMessage();
        }
        testResultExMessage(id, description, message);

        id = "row < 1";
        description = "WordSearch.createPuzzle(0, 12, wordsSingle)";
        Word[] wordsSingle = new Word[1];
        wordsSingle[0] = new Word("BRUSH", 0, 1, true);
        String exp = "class java.lang.IllegalArgumentException";
        String actualResult = "";
        try {
            WordSearch.createPuzzle(0, 12, wordsSingle);
            actualResult = "No exception thrown";
        } catch (IllegalArgumentException e) {
            actualResult = "" + e.getClass();
        }
        testResult(id, description, exp, actualResult);

        id = "col < 1";
        description = "WordSearch.createPuzzle(10, -12, wordsSingle)";
        wordsSingle = new Word[1];
        wordsSingle[0] = new Word("BRUSH", 0, 1, true);
        exp = "class java.lang.IllegalArgumentException";
        actualResult = "";
        try {
            WordSearch.createPuzzle(10, -12, wordsSingle);
            actualResult = "No exception thrown";
        } catch (IllegalArgumentException e) {
            actualResult = "" + e.getClass();
        }
        testResult(id, description, exp, actualResult);
    }

    /**
     * Tests playGame method
     */
    public static void testPlayGame() {


        Scanner scan = new Scanner("A scanner!!!!");
        String title = "Our title";
        Word[] words = new Word[4];
        // Create and add 4 Word objects to the words array
        words[0] = new Word("BRUSH", 2, 6, true);
        words[1] = new Word("SOAP", 0, 2, false);
        words[2] = new Word("SHAMPOO", 3, 4, true);
        words[3] = new Word("LOTION", 4, 2, true);
        char[][] puzzle = { { 'A', 'B', 'R', 'U', 'S', 'H', 'B' } };

        String id = "title is null";
        String description = "WordSearch.playGame(scan, null, words, puzzle)";
        String message = "";
        try {
            WordSearch.playGame(scan, null, words, puzzle);
            message = null;
        } catch (NullPointerException e) {
            message = e.getMessage();
        }
        testResultExMessage(id, description, message);

        id = "scan is null";
        description = "WordSearch.playGame(null, title, words, puzzle)";
        message = "";
        try {
            WordSearch.playGame(null, title, words, puzzle);
            message = null;
        } catch (NullPointerException e) {
            message = e.getMessage();
        }
        testResultExMessage(id, description, message);

        id = "words is null";
        description = "WordSearch.playGame(scan, title, null, puzzle)";
        message = "";
        try {
            WordSearch.playGame(scan, title, null, puzzle);
            message = null;
        } catch (NullPointerException e) {
            message = e.getMessage();
        }
        testResultExMessage(id, description, message);

        id = "words contains null element";
        description = "WordSearch.playGame(scan, title, wordsNull, puzzle)";
        Word[] wordsNull = new Word[3];
        wordsNull[0] = new Word("BRUSH", 0, 1, true);
        message = "";
        try {
            WordSearch.playGame(scan, title, wordsNull, puzzle);
            message = null;
        } catch (NullPointerException e) {
            message = e.getMessage();
        }
        testResultExMessage(id, description, message);

        id = "puzzle is null";
        description = "WordSearch.playGame(scan, title, words, null)";
        message = "";
        try {
            WordSearch.playGame(scan, title, words, null);
            message = null;
        } catch (NullPointerException e) {
            message = e.getMessage();
        }
        testResultExMessage(id, description, message);

        id = "puzzle contains null row";
        description = "WordSearch.playGame(scan, title, words, puz)";
        char[][] puz = new char[3][];
        message = "";
        try {
            WordSearch.playGame(scan, title, words, puz);
            message = null;
        } catch (NullPointerException e) {
            message = e.getMessage();
        }
        testResultExMessage(id, description, message);

        id = "puzzle jagged";
        description = "WordSearch.playGame(scan, title, words, puz)";
        puz = new char[3][];
        puz[0] = new char[3];
        puz[1] = new char[3];
        puz[2] = new char[1];
        String exp = "class java.lang.IllegalArgumentException";
        String actualResult = "";
        try {
            WordSearch.playGame(scan, title, words, puz);
            actualResult = "No exception thrown";
        } catch (IllegalArgumentException e) {
            actualResult = "" + e.getClass();
        }
        testResult(id, description, exp, actualResult);

    }

    /**
     * Tests displayPuzzle
     */
    public static void testDisplayPuzzle() {


        String title = "Our title";
        char[][] puzzle = { { 'A', 'B', 'R', 'U', 'S', 'H', 'B' } };

        String id = "title is null";
        String description = "WordSearch.displayPuzzle(null, puzzle)";
        String message = "";
        try {
            WordSearch.displayPuzzle(null, puzzle);
            message = null;
        } catch (NullPointerException e) {
            message = e.getMessage();
        }
        testResultExMessage(id, description, message);

        id = "puzzle is null";
        description = "WordSearch.displayPuzzle(title, null)";
        message = "";
        try {
            WordSearch.displayPuzzle(title, null);
            message = null;
        } catch (NullPointerException e) {
            message = e.getMessage();
        }
        testResultExMessage(id, description, message);

        id = "puzzle contains null row";
        description = "WordSearch.displayPuzzle(title, puz)";
        char[][] puz = new char[3][];
        message = "";
        try {
            WordSearch.displayPuzzle(title, puz);
            message = null;
        } catch (NullPointerException e) {
            message = e.getMessage();
        }
        testResultExMessage(id, description, message);

        id = "puzzle jagged";
        description = "WordSearch.displayPuzzle(title, puz)";
        puz = new char[3][];
        puz[0] = new char[3];
        puz[1] = new char[3];
        puz[2] = new char[1];
        String exp = "class java.lang.IllegalArgumentException";
        String actualResult = "";
        try {
            WordSearch.displayPuzzle(title, puz);
            actualResult = "No exception thrown";
        } catch (IllegalArgumentException e) {
            actualResult = "" + e.getClass();
        }
        testResult(id, description, exp, actualResult);
    }

    /**
     * Prints the test information.
     * 
     * @param id ID of test
     * @param description description of the test (e.g., method call)
     * @param starter starter array
     * @param obscured obscured array
     */
    private static void testResultObscured(String id, String description, char[][] starter,
                    char[][] obscured) {
        testCounter++;
        String result = PASS;

        if (starter.length != obscured.length) {
            result = FAIL;
        } else {
            for (int row = 0; row < starter.length && result.equals(PASS); row++) {
                if (starter[row].length != obscured[row].length) {
                    result = FAIL;
                } else {

                    // ASSERT: starter[row] and obscured[row] have the same
                    // number of columns
                    for (int col = 0; col < starter[row].length; col++) {
                        if (starter[row][col] != ' ') {
                            if (starter[row][col] != obscured[row][col]) {
                                result = FAIL;
                            }
                        } else { // ASSERT: starter[row][col] == ' '
                            if (obscured[row][col] == ' ') {
                                result = FAIL;
                            }
                        }
                    }
                }
            }
        }

        if (result.equals(PASS)) {
            passingTestCounter++;
        }
        System.out.println();
        System.out.printf("\n%3d  %-28s%-58s%7s\n", testCounter, id, description, result);
        System.out.println("Starter:    " + Arrays.deepToString(starter));
        System.out.println("Obscured:   " + Arrays.deepToString(obscured));
        System.out.println();
    }

    /**
     * Prints the test information.
     * 
     * @param id id of the test
     * @param desc description of the test (e.g., method call)
     * @param exp expected result of the test
     * @param act actual result of the test
     */
    private static void testResult(String id, String desc, char[][] exp, char[][] act) {
        testCounter++;
        String result = FAIL;
        if (Arrays.deepEquals(exp, act)) {
            result = PASS;
            passingTestCounter++;
        }
        System.out.println();
        System.out.printf("\n%3d  %-28s%-58s%7s\n", testCounter, id, desc, result);
        System.out.println("Expected:    " + Arrays.deepToString(exp));
        System.out.println("Actual:      " + Arrays.deepToString(act));
        System.out.println();
    }

    /**
     * Prints the test information.
     * 
     * @param id id of the test
     * @param desc description of the test (e.g., method call)
     * @param exp expected result of the test
     * @param act actual result of the test
     */
    private static void testResult(String id, String description, Word expected, Word actual) {
        testCounter++;
        String result = FAIL;
        if (expected == null) {
            if (expected == actual) {
                result = PASS;
                passingTestCounter++;
            }
        } else if (expected.equals(actual)) {
            result = PASS;
            passingTestCounter++;
        }
        System.out.println();
        System.out.printf("\n%3d  %-28s%-58s%7s\n", testCounter, id, description, result);
        System.out.println("Expected:    " + expected);
        System.out.println("Actual:      " + actual);
        System.out.println();
    }

    /**
     * Prints the test information for tests whose actual result is an int.
     * 
     * @param id id of the test
     * @param desc description of the test (e.g., method call)
     * @param exp expected result of the test
     * @param act actual result of the test
     */
    private static void testResult(String id, String desc, String exp, String act) {
        testCounter++;
        String result = FAIL;
        if (exp.equals(act)) {
            result = PASS;
            passingTestCounter++;
        }
        System.out.printf("\n%3d  %-28s%-58s%7s%46s%46s\n", testCounter, id, desc, result, exp, act);
    }

    /**
     * Prints the test information for tests that examines message of exception
     * 
     * @param id id of the test
     * @param desc description of the test (e.g., method call)
     * @param message Message when NullPointerException is thrown
     */
    private static void testResultExMessage(String id, String desc, String message) {
        testCounter++;
        String result = FAIL;
        if (message != null) {
            result = PASS;
            passingTestCounter++;
        }
        System.out.printf("\n%3d  %-28s%-58s%7s%46s%46s\n", testCounter, id, desc, result,
                        "non-null", message);
    }
}