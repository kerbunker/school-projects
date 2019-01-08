import java.awt.*;
import java.util.*;

/**
 * This program draws a pattern of rows and columns of circles inside squares
 *
 * @author Katelyn Bunker
 */
public class Pattern {
    /**
     * final variable for the low value of the rows and columns
     */
    public static final int RCLOW = 1;
    /**
     * final variable for the high for the rows and columns
     */
    public static final int RCHIGH = 10;
    /**
     * final variable for the low value for the red green and blue values
     */
    public static final int RGBLOW = 0;
    /**
     * final variable for the high value of the red, green, and blue values
     */
    public static final int RGBHIGH = 255;
    /**
     * draw panel length and width
     */
    public static final int DRAWPANEL = 540;
    /**
     * width of the squares and circles
     */
    public static final int WIDTH = 50;
    /**
     * x and y starting value
     */
    public static final int XY = 20;
    
    /**
     * Declares the variables, computes the position, and prints the results
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);//starts scanner
        System.out.print("Enter number of rows (1-10): ");
        int rows = in.nextInt();//asks for value and assigns it a variable
        System.out.print("Enter number of columns (1-10): ");
        int columns = in.nextInt();//asks for a value and assigns it a varaible
        System.out.print("Enter red value (0-255): ");
        int red = in.nextInt();//asks for a value and assigns it a variable
        System.out.print("Enter green value (0-255): ");
        int green = in.nextInt();//asks for a value and assigns it a variable
        System.out.print("Enter blue value (0-255): ");
        int blue = in.nextInt();//asks for a value and assigns it a variable
        rows = correctValuesOne(rows);//method to correct row value
        columns = correctValuesOne(columns);//method to correct column value
        red = correctValuesTwo(red);//method to correct red value
        green = correctValuesTwo(green);//method to correct green value
        blue = correctValuesTwo(blue);//method ot correct blue value
        DrawingPanel panel = new DrawingPanel(DRAWPANEL, DRAWPANEL);//starts drawing panel
        Graphics g = panel.getGraphics();
        g.setColor(new Color(red, green, blue));//sets the color to user specified values
        for(int i = RGBLOW; i < rows; i++) {//for loop to print multiple rows and columns or pattern
            for(int j = RGBLOW; j < columns; j++) {
                int x = XY + WIDTH * j;
                int y = XY + WIDTH * i; 
                drawCircle(g, x, y, WIDTH);//method to print out i*j number of circles
                drawSquare(g, x, y, WIDTH);//method to print out the i*j number of squares
            }
        }
        //tells user to close drawing panel to stop program
        System.out.println("*Close the Drawing Panel to exit the program*");
    }
    
    /**
     * corrects the rows and columns values to acceptable values
     *
     * @param value int value of the rows or columns that needs to be corrected
     *
     * @return value int final corrected value of the row or columns
     */
    public static int correctValuesOne(int value){
        if(value < RCLOW ) {//changes the value to one if less than one
            value = RCLOW;
        } else if(value > RCHIGH) {//changes value to 10 if less than 10
            value = RCHIGH;
        }
        return value;//returns corrected value
    }
    
    /**
     * corrects the red, green, and blue values
     *
     * @param value int value for the user input red, green, or blue value
     *
     * @return value int corrected value
     */
    public static int correctValuesTwo(int value) {
        if(value < RGBLOW) {//changes value to 0 if less than 0
            value = RGBLOW;
        } else if(value > RGBHIGH) {//changes value to 255 if greater than 255
            value = RGBHIGH;
        }
        return value;//returns corrected value
    }
    
    /**
     * Draws the circles for the pattern
     *
     * @param g graphics variable
     * @param x int x value for where the circle starts
     * @param y int y value for where the circle starts
     * @param width int value for the width of the circle
     */
    public static void drawCircle(Graphics g, int x, int y, int width) {
        g.drawOval(x, y, width, width);
    }
    
    /**
     * Draws the squares for the pattern
     *
     * @param g graphics variable
     * @param x int x for where the square starts
     * @param y int y for where the square starts
     * @param width int value for the width of the square
     */
    public static void drawSquare(Graphics g, int x, int y, int width) {
        g.drawRect(x, y, width, width);
    }
    
}