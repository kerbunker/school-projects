import java.util.*;

/**
 * This program takes input of essay and SAT scores, portfolio scores
 * and number of alumni family members
 * to accept, deny, or defer admissions candidates
 *
 * @author Katelyn Bunker
 */
public class Admissions {
    /** low essay constant */
    public static final int ESSAYLOW = 1;
    /** high essay constant */
    public static final int ESSAYHIGH = 4;
    /** low SAT score */
    public static final int SATLOW = 200;
    /** high SAT score */
    public static final int SATHIGH = 800;
    /** low value for alumni */
    public static final int ALUMNILOW = 0;
    /**
     * Starts the program
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        //prints out Intro instructions
        System.out.println("            Welcome to the College Admissions Program!");
        System.out.println("When prompted, please enter the applicant's name, the school to ");
        System.out.println("which he/she is applying - E (Engineering), L (Liberal Arts), or");
        System.out.println("F (Fine Arts), and the applicant's essay score. Depending on the");
        System.out.println("school, enter the Math/Reading SAT scores or the Portfolio rating");
        System.out.println("- E (Excellent), G (Good), F (Fair), or P (Poor). Also, enter the");
        System.out.println("number of alumni family members. The applicant's admission status ");
        System.out.println("of Admit, Defer, or Deny will then be displayed.");
        System.out.println("");
        
        Scanner in = new Scanner(System.in);//starts scanner and asks for aplicant info
        System.out.print("Applicant name: ");
        String name = in.nextLine();
        System.out.print("E (Engineering), L (Liberal Arts), or F (Fine Arts): ");
        String school = in.next();
        school = school.toUpperCase();//chnages school to uppercase to avoid confusion

        validInputSchool(school);//tests school input for validity
        char s = school.charAt(0);//makes school string into char value
        
        System.out.print("Essay score (1-4): ");
        int essay = in.nextInt();
        if (essay < ESSAYLOW || essay > ESSAYHIGH) {//tests essay score for validity
            System.out.println("Invalid Input");
            System.exit(1);//exits program if invalid
        }    
        int mathSAT = 0;//creates SAT variables
        int readSAT = 0;
        String port = "";//creates portfolio variable
        char p = 'N';//creates portfolio char value
        //checks for school of engineering or liberal arts to ask for SAT scores
        if (school.charAt(0) == 'E' || school.charAt(0) == 'L') {
            System.out.print("Math SAT score (200-800): ");
            mathSAT = in.nextInt();
            validInputSAT(mathSAT);//validates mathSAT input
            System.out.print("Reading SAT score (200 - 800): ");
            readSAT = in.nextInt();
            validInputSAT(readSAT);//validates readingSAT input
            
        // asks for portfolio score if school of fine arts   
        } else if (school.charAt(0) == 'F'){
            System.out.print("Portfolio rating (E (Excellent), G (Good), F (Fair), or P (Poor)): ");
            port = in.next();
            port = port.toUpperCase();//changes ot uppercase
            validInputPortfolio(port);//validates input
            p = port.charAt(0);//chnages to char value
            
        }
        
        System.out.print("Number of alumni family members: ");
        int alumni = in.nextInt();
        if (alumni < ALUMNILOW) {//tests for alumni validity
            System.out.println("Invalid Input");
            System.exit(1);//exits program if invalid
        }
        
        //inputs user input data and prints admission status based on scores
        System.out.println(getAdmissionStatus(s, essay, mathSAT, readSAT, p, alumni));

    }
    
    /**
     * Tests input for valid school character
     * prints out "Invalid Input" and exits program if invalid
     *
     * @param school string of school id
     */
    public static void validInputSchool(String school){
        boolean valid = false;
        if (school.charAt(0) == 'E') {
            valid = true;
        } else if(school.charAt(0) == 'L') {
            valid = true;
        } else if(school.charAt(0) == 'F') {
            valid = true;
        }
        if(school.length() != 1){
            valid = false;
        }
        if(!valid) {
            System.out.println("Invalid input");
            System.exit(1);
        }
    }
    
    /**
     * Tests for valid portfolio input
     * Prints "Invalid Input" and exits the program if not valid
     *
     * @param port string of portfolio grade
     */
    public static void validInputPortfolio(String port) {
        boolean valid = false;
        if (port.charAt(0) == 'E') {
            valid = true;
        } else if (port.charAt(0) == 'G') {
            valid = true;
        } else if (port.charAt(0) == 'F') {
            valid = true;
        } else if (port.charAt(0) == 'P') {
            valid = true;
        }
        if (port.length() != 1) {
            valid = false;
        }
        if(!valid) {
            System.out.println("Invalid Input");
            System.exit(1);
        }
    }
    
    /**
     * Tests the validity of the SAT score (above 200 and below 800)
     * Prints "Invalid Input" and exits the program if invalid
     *
     * @param score int value of the SAT score
     */
    public static void validInputSAT(int score){
        if(score < SATLOW || score > SATHIGH){
            System.out.println("Invalid Input");
            System.exit(1);
        }
    }
    
    /**
     * Takes data input and decides to Admit, Defer, or Deny applicant
     *
     * @param school character of the school id
     * @param essayScore int value of the essay score
     * @param mathSAT int value of the math SAT score
     * @param readingSAT int value of the reading SAT score
     * @param portfolioRating character rating for the portfolio
     * @param numberOfAlumni int value of the number of alumni family members
     * @return string "Admit", "Defer" or "Deny" based on school criteria
     */
    public static String getAdmissionStatus(char school, int essayScore, int mathSAT,
        int readingSAT, char portfolioRating, int numberOfAlumni) {
            
        final int ESSAYSCOREDENY = 2;//value for low essay score
        final int MATHSATDENYE = 500;//value for engineering low math SAT score
        final int READINGSATDENYE = 400;//value for engineering low reading SAT score
        final int MATHSATADMITE = 700;//value for engineering admit math SAT score
        final int READINGSATADMITE = 600;//value for engineering admit readingSAT score
        final int ALUMNIADMIT = 2;//value for alumni to change from defer to admit
        final int MATHSATDENYL = 400;//liberal arts math SAT deny value
        final int READINGSATDENYL = 500;//liberal arts reading SAT deny value
        final int MATHSATADMITL = 500;//liberal arts math SAT admit value
        final int READINGSATADMITL = 650;//liberal arts reading SAT admit value
        
        //throws exception if invalid SAT score passed through
        if(school == 'E' || school == 'L' || school == 'e' || school == 'l'){
            if(mathSAT < SATLOW || readingSAT < SATLOW ||
                mathSAT > SATHIGH || readingSAT > SATHIGH){
                throw new IllegalArgumentException("Invalid Input");
            }
        }
        
        //throws exception if invalid essay score passed through
        if (essayScore < ESSAYLOW || essayScore > ESSAYHIGH){
            throw new IllegalArgumentException("Invalid Input");
        }
        
        //throws exception if invalid alumni number passed through
        if (numberOfAlumni < ALUMNILOW) {
            throw new IllegalArgumentException("Invalid Input");
        }
        
        //starts to decide applicants fate at engineering school
        if(school == 'E' || school == 'e') {
            
            //tests essay score
            if(essayScore < ESSAYSCOREDENY || mathSAT < MATHSATDENYE || 
                readingSAT < READINGSATDENYE) {
                return "Deny";//denies if too low
            //tests SAT score
            } else if(mathSAT >= MATHSATADMITE && readingSAT >= READINGSATADMITE) {
                return "Admit";//admits if high enough
            //tests alumni number
            } else if (numberOfAlumni >= ALUMNIADMIT) {
                return "Admit";//admits if over 2 if applicant was previously defered
            } else {
                return "Defer";//defers applicant if no other status has been reached
            }
            
        //tests school of liberal arts applicants    
        } else if(school == 'L' || school == 'l') {
            //tests essay score
            if(essayScore <= ESSAYSCOREDENY || mathSAT < MATHSATDENYL ||
                readingSAT < READINGSATDENYL) {
                return "Deny";//denies if too low
            //tests SAT scores
            } else if(mathSAT >= MATHSATADMITL && readingSAT >= READINGSATADMITL) {
                return "Admit";//admits if high enough
            //tests for number of alumni
            } else if (numberOfAlumni >= ALUMNIADMIT) {
                return "Admit";//admits if over 2 if student would have been defered
            } else {
                return "Defer";//defers if no other status has been reached
            }
        
        //tests fine arts school applicants
        } else if(school == 'F' || school == 'f') {
            //tests essay score
            if(essayScore < ESSAYSCOREDENY || portfolioRating == 'P' || portfolioRating == 'F') {
                return "Deny";//denies if too low
            //tests portfolio rating
            } else if(portfolioRating == 'p' || portfolioRating == 'f') {
                return "Deny";//denies if fair or poor
            } else if (portfolioRating == 'E' || portfolioRating == 'e') {
                return "Admit";//admits if excellent
            } else if (portfolioRating == 'G' || portfolioRating == 'g'){
                //tests alumni
                if (numberOfAlumni >= ALUMNIADMIT) {
                    return "Admit";//admits if 2 or higher
                } else {
                    return "Defer";//defers if nothing else has been decided
                }
            } else {//throws exception if portfolio rating not valid
                throw new IllegalArgumentException("Invalid Input");
            }
        } else {//throws exception if school not valid
            throw new IllegalArgumentException("Invalid Input");
        }
    }

}