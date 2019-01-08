/**
 * This program prints out an ASCII art design from www.ascii-art.de/ascii/
 *
 * @author Katelyn Bunker
 */
public class Art {
    /**
     * Makes a final variable that controls the number of butterlies that are printed out
     */
    public static final int BUTTERFLY = 6;
    
    /**
     * Makes a final variable for the number of ^ characters in the ground of the flowers section
     */
    public static final int GROUND = 61;
    
    /**
     * Starts the program
     *
     * @param args
     *             Command line arguments
     */
    public static void main(String[] args) {
        butterfly();//method for the butterflies
        flower();//method for the flowers
    }
    
    /**
     * Method for the butterflies
     * made up of the top half and bottom half methods
     * Design from bottom of www.ascii-art.de/ascii/ab/butterfly.txt
     */
    public static void butterfly() {
        butterflyTop();//method for top half
        butterflyBot();//method for bottom half
    }    
    
    /**
     * Method for the flowers
     * made up of the top half and the bottom half and the ground
     * Design from middle of www.ascii-art.de/ascii/def/flowers.txt
     */
    public static void flower() {
        flowerTop();//method for top half
        flowerBot();//method for bottom half
        flowerGround();//method for the ground
    }
    
    /**
     * Method for the top two lines of the butterflies
     * uses for loops to make x number of butterflies
     * controled by the BUTTERFLY variable
     */
    public static void butterflyTop() {
        for(int i = 0; i < BUTTERFLY; i++) {//loop for the top row
            System.out.print(" _    _   ");
        }
        System.out.println(" ");//send to the next line
        for(int i = 0; i < BUTTERFLY; i++) {//loop for the second row
            System.out.print("(.\\\\//.)  ");
        }
        System.out.println(" ");//send to the next line
    }
    
    /**
     * Method for the bottom two lines of the butterflies
     * again uses loops for the multiple different butterflies
     */
    public static void butterflyBot() {
        for(int i = 0; i < BUTTERFLY; i++) {//loop for third row
            System.out.print(" \\ () /   ");
        }
        System.out.println(" ");//sends to next line
        for(int i = 0; i < BUTTERFLY; i++) {//loop for bottom row
            System.out.print(" (_/\\_)   ");
        }
        System.out.println(" ");//sends to next line
    }
    
    /**
     * Method for the first four lines of the flowers section
     */
    public static void flowerTop() {
        System.out.println("                 _");
        System.out.print("               _(_)_");
        System.out.println("                          wWWWw   _");
        System.out.print("   @@@@       (_)@(_)   vVVVv");
        System.out.println("     _     @@@@  (___) _(_)_");
        System.out.print("  @@()@@ wWWWw  (_)\\    (___)");
        System.out.println("   _(_)_  @@()@@   Y  (_)@(_)");
    }

    /**
     * Method for the next four rows of the flower section
     */
    public static void flowerBot() {
        System.out.print("   @@@@  (___)     `|/    Y    ");
        System.out.println("(_)@(_)  @@@@   \\|/   (_)\\");
        System.out.print("    /      Y       \\|    \\|/");
        System.out.println("    /(_)    \\|      |/      |");
        System.out.print(" \\ |     \\ |/       | / \\ | /");
        System.out.println("  \\|/       |/    \\|      \\|/");
        System.out.print("  \\|//   \\\\|//   \\\\\\|//\\\\\\|///");
        System.out.println(" \\|///  \\\\\\|//  \\\\|//  \\\\\\|//");
    }    
    
    /** Method for the ground of the flowers
     * Uses the final variable GROUND to control the length of the ground
     * and uses a for loop to print out the repeating ^ characters
     */
    public static void flowerGround() {
        for(int i = 0; i < GROUND; i++) {
            System.out.print("^");
        }
        System.out.println(" ");
    }
}