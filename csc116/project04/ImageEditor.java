import java.util.*;
import java.io.*;

/**
 * Program takes a user specified ppm file and changes the pixel color values
 * by a user specified amount, inverts the colors, or changes the file to a high contrast version.
 *
 * @author Katelyn Bunker
 */
public class ImageEditor {
    /** Class constant for the minimum color value */
    public static final int MIN_RGB_VALUE = 0;
    /** Class constant for the maximum color value */
    public static final int MAX_RGB_VALUE = 255;
    /** Class constant for high contrast cutoff value */
    public static final int HIGH_CONTRAST_VALUE = 128;
    /**
     * Starts the program
     *
     * @param args array of command line arguments
     */
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        
        while(true) {
            System.out.print("Enter C-hange Intensity, I-nvert, H-igh Contrast or Q-uit: ");
            String changeType = console.next();
            if(changeType.charAt(0) == 'C' || changeType.charAt(0) == 'c') {
                System.out.print("Red change amount: ");
                while(!console.hasNextInt()) {
                    console.next();
                    System.out.println("Not an int, please try again.");
                    System.out.print("Red change amount: ");
                }
                int redValue = console.nextInt();
                System.out.print("Green change amount: ");
                while(!console.hasNextInt()) {
                    console.next();
                    System.out.println("Not an int, please try again.");
                    System.out.print("Green change amount: ");
                }
                int greenValue = console.nextInt();
                System.out.print("Blue change amount: ");
                while(!console.hasNextInt()) {
                    console.next();
                    System.out.println("Not an int, please try again.");
                    System.out.print("Blue change amount: ");
                }
                int blueValue = console.nextInt();
                Scanner input = getInputScanner(console);
                PrintStream output = getOutputPrintStream(console);
                changeIntensity(redValue, greenValue, blueValue, input, output);
            } else if (changeType.charAt(0) == 'I' || changeType.charAt(0) == 'i') {
                Scanner input = getInputScanner(console);
                PrintStream output = getOutputPrintStream(console);
                invert(input, output);
            } else if (changeType.charAt(0) == 'H' || changeType.charAt(0) == 'h') {
                Scanner input = getInputScanner(console);
                PrintStream output = getOutputPrintStream(console);
                highContrast(input, output);
            } else if (changeType.charAt(0) == 'Q' || changeType.charAt(0) == 'q') {
                System.exit(1);
            } else {
                System.out.println("Not a valid type. Please try again.");
            }
        }
    }


    /**
     * Repeatedly prompts the user for the name of an input file until the user enters
     * the name of an existing file; then creates and returns a Scanner for the input file.
     * 
     * @param console scanner for input from user
     * @return input scanner
     */
    public static Scanner getInputScanner(Scanner console) {
        Scanner input = null;
        while(input == null) {
            System.out.print("Enter input file: ");
            String fileName = console.next();
            try {
                input = new Scanner(new File(fileName));
            } catch (FileNotFoundException e) {
                System.out.println("File not found.");
            }
        }
        return input;
    }
    
    /**
     * Prompts the user for the name of an output file.
     * If the file does not exist, creates and returns a PrintStream for the output file.
     * If the file does exist, prints an error message and exits the program.
     *
     * @param console scanner for input from user
     * @return PrintStrean for output file
     */
    public static PrintStream getOutputPrintStream(Scanner console) {
        PrintStream output = null;
        File printFile = null;
        String userResponse = "";
        while (printFile == null) {
            System.out.print("Enter output file: ");
            String outputFile = console.next();
            printFile = new File(outputFile);
            if(printFile.exists()) {//asks user for permission to overwrite file
                System.out.print("OK to overwrite file? (y/n): ");
                userResponse = console.next();
                if (userResponse.charAt(0) == 'y') {
                    while(output == null) {
                        try {
                            output = new PrintStream(printFile);
                        } catch (FileNotFoundException e) {
                            System.out.println("File unable to be written.");
                        }
                    }
                }
            } else {
                while(output == null) {
                    try {
                        output = new PrintStream(printFile);
                    } catch (FileNotFoundException e) {
                        System.out.println("File unable to be written.");
                    }
                }
            }
        }
        return output;

    }
    
    
    /**
     * Copies the input ppm file to the output file, changing the intensity of each color by the
     * given amount
     *
     * @param redChange int amount to change the red value by
     * @param greenChange int amoung to change the green value by
     * @param blueChange int amount to change the blue value by
     * @param input scanner for the input file
     * @param output printstream to print to the output file
     */
    public static void changeIntensity(int redChange, int greenChange, int blueChange,
        Scanner input, PrintStream output) {
        for(int i = 0; i < 3; i++) {
            output.println(input.nextLine());
        }
        while(input.hasNextLine()) {
            String line = input.nextLine();
            Scanner lineScanner = new Scanner(line);
            String printLine = "";
            while(lineScanner.hasNextInt()) {
                int redValue = lineScanner.nextInt();
                redValue += redChange;
                if (redValue > MAX_RGB_VALUE) {
                    redValue = MAX_RGB_VALUE;
                } else if (redValue < MIN_RGB_VALUE) {
                    redValue = MIN_RGB_VALUE;
                }
                int greenValue = lineScanner.nextInt();
                greenValue += greenChange;
                if (greenValue > MAX_RGB_VALUE) {
                    greenValue = MAX_RGB_VALUE;
                } else if (greenValue < MIN_RGB_VALUE) {
                    greenValue = MIN_RGB_VALUE;
                }
                int blueValue = lineScanner.nextInt();
                blueValue += blueChange;
                if(blueValue > MAX_RGB_VALUE) {
                    blueValue = MAX_RGB_VALUE;
                } else if (blueValue < MIN_RGB_VALUE) {
                    blueValue = MIN_RGB_VALUE;
                }
                printLine = printLine + redValue + " " + greenValue + " " + blueValue + " ";
            }
            output.println(printLine);
        }
    }
    
    /**
     * Copies the input ppm file to the output file, negating the color values
     *
     * @param input scanner to read the input file
     * @param output printstream to print to output file
     */
    public static void invert(Scanner input, PrintStream output) {
        int redValue = 0;
        int greenValue = 0;
        int blueValue = 0;
        
        for(int i = 0; i < 3; i++) {
            output.println(input.nextLine());
        }
        while(input.hasNextLine()) {
            String line = input.nextLine();
            Scanner lineScanner = new Scanner(line);
            String printLine = "";
            while(lineScanner.hasNextInt()) {
                redValue = lineScanner.nextInt();
                greenValue = lineScanner.nextInt();
                blueValue = lineScanner.nextInt();
                redValue = MAX_RGB_VALUE - redValue;
                greenValue = MAX_RGB_VALUE - greenValue;
                blueValue = MAX_RGB_VALUE - blueValue;
                printLine = printLine + redValue + " " + greenValue + " " + blueValue + " ";
            }
            output.println(printLine);
        }
    }
    
    /**
     * Copies the input pp, file to the output file, converting it to high contrast
     *
     * @param input scanner for input file data
     * @param output printstream to print to output file
     */
    public static void highContrast(Scanner input, PrintStream output) {
        int redValue = 0;
        int greenValue = 0;
        int blueValue = 0;
        
        for(int i = 0; i < 3; i++) {
            output.println(input.nextLine());
        }
        while(input.hasNextLine()) {
            String line = input.nextLine();
            Scanner lineScanner = new Scanner(line);
            String printLine = "";
            while(lineScanner.hasNextInt()) {
                redValue = lineScanner.nextInt();
                greenValue = lineScanner.nextInt();
                blueValue = lineScanner.nextInt();
                if(redValue < HIGH_CONTRAST_VALUE) {
                    redValue = MIN_RGB_VALUE;
                } else if (redValue >= HIGH_CONTRAST_VALUE) {
                    redValue = MAX_RGB_VALUE;
                }
                if (greenValue < HIGH_CONTRAST_VALUE) {
                    greenValue = MIN_RGB_VALUE;
                } else if (greenValue >= HIGH_CONTRAST_VALUE) {
                    greenValue = MAX_RGB_VALUE;
                }
                if (blueValue < HIGH_CONTRAST_VALUE) {
                    blueValue = MIN_RGB_VALUE;
                } else if (blueValue >= HIGH_CONTRAST_VALUE) {
                    blueValue = MAX_RGB_VALUE;
                }
                printLine = printLine + redValue + " " + greenValue + " " + blueValue + " ";
            }
            output.println(printLine);
        }
    }
     

    
}