import java.awt.*;
import java.util.*;

/**
 * Shows and animation of a figures jumping across a mario-like background
 *
 * @author Katelyn Bunker
 */
public class Animation {
    
    /**
     * final value for the width of the drawing panel
     */
    public static final int WIDTH = 500;
    /**
     * final value for the height of the drawing panel
     */
    public static final int HEIGHT = 400;
    /**
     * final value for the sleep time
     */
    public static final int SLEEP_TIME = 200;
    /**
     * class constant for the initial x value
     */
    public static final int INITIAL_X = 30;
    /**
     * class constant for the initial y value
     */
    public static final int INITIAL_Y = 250;
    /**
     * class constant for the number of updates
     */
    public static final int NUMBER_OF_UPDATES = 80;
    /**
     * class constant for how far figure should move
     */
    public static final int DX = 5;
    

    /**
     * Starts the program*
     *
     * @param args command line arguments
     */
    public static void main (String[] args) {
        
        DrawingPanel panel = new DrawingPanel(WIDTH, HEIGHT);//starts drawing panel
        Graphics g = panel.getGraphics();
        
        int x = INITIAL_X;
        int y = INITIAL_Y;
        //starts loop to cycle through the background and the figure to create the movement
        for (int i = 0; i < NUMBER_OF_UPDATES; i++) {
            drawBackground(g);
            //allows the figure to "jump" over the pipe and hit the block
            if(i >= 10 && i <= 30){
                drawFigure(g, x + DX * i, y - DX * i + 20);
            //allows the figure to jump up the stairs and reach the ending flagpole
            } else if(i >= 50) {
                drawFigure(g, x + DX * i, y - DX * i + 230);
            //just moves the figure to the right
            } else {
                drawFigure(g, x + DX * i, y);
            }
            //has the program stop so it's not constantly updating
            panel.sleep(SLEEP_TIME);
        } 
         
        //tells to user to close the panel to end the program 
        System.out.println("\n*CLOSE the Drawing Panel to exit the program*");
    }
    

    /**
     * draws a mario style background with a blue sky gray ground and block to hit and climb
     * also features the classic green pipe and ending flagpole
     *
     * @param g graphics variable
     */
    public static void drawBackground(Graphics g) {
        g.setColor(Color.BLUE);//Sets background color
        g.fillRect(0, 0, WIDTH, HEIGHT);//Creates backgound
        for(int i = 0; i < 8; i++) { //Starts the loop for the block stairs
            for(int j = i; j <= 8; j++) {
                g.setColor(Color.DARK_GRAY);
                g.fillRect(280 + 20 * j, 300 - 20 * i, 20, 20);
                g.setColor(Color.BLACK);
                g.drawRect(280 + 20 * j, 300 - 20 * i, 20, 20);
                
            }
        }
        for(int i = 0; i < 4; i++) {//draws the block for the figure to hit
            g.setColor(Color.DARK_GRAY);
            g.fillRect(150 + 40 * i, 90, 40, 40);
            g.setColor(Color.BLACK);
            g.drawRect(150 + 40 * i, 90, 40, 40);
            
        }
        
        g.setColor(Color.GREEN);//creates pipe and flagpole
        g.fillRect(100, 270, 70, 60);
        g.fillRect(95, 270, 80, 10);
        g.fillOval(480, 100, 10, 10);
        g.fillRect(483, 105, 5, 230);
        
        g.setColor(Color.DARK_GRAY);//draws ground
        g.fillRect(0, 320, WIDTH, 200);
    }
    

    /**
     * Draws the figure and has him move and jump across the panel
     *
     * @param g graphics variable
     * @param x int x value for the figure to be drawn at
     * @param y int y value for the figure to be drawn at
     */
    public static void drawFigure(Graphics g, int x, int y) {
        g.setColor(Color.BLACK);
        g.drawOval(x, y - 10, 15, 15);//draws head
        g.fillRect(x + 6, y + 4, 3, 40);//draws body
        g.drawLine(x - 5, y + 5, x + 6, y + 25);//draws left arm
        g.drawLine(x + 9, y + 25, x + 21, y + 5);//draws right arm
        g.drawLine(x - 5, y + 65, x + 6, y + 43);//draws left left leg
        g.drawLine(x + 9, y + 43, x + 21, y + 65);//draws right leg
    } 
}