/**
 * Program to test Admissions
 * 
 * @author Katelyn Bunker
 */
public class AdmissionsTest {

    /** Constants for passing and failing test output */
    public static final String PASS = "PASS";
    public static final String FAIL = "FAIL";

    /** Counters for test cases */
    public static int testCounter = 0;
    public static int passingTestCounter = 0;

    /**
     * Starts the test program
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {

        testGetAdmissionStatus();

        System.out.println("\n******** Results ********");
        System.out.printf("%4d / %4d passing tests\n", passingTestCounter, testCounter);

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
        System.out.printf("\n%-25s%-55s%7s%46s%46s\n", id, desc, result, exp, act);
    }

    /**
     * Tests getAdmissionStatus method
     */
    public static void testGetAdmissionStatus() {
        // Example test case for getAdmissionStatus() method - e-ngineering low
        // essay
        String id = "e-ngineering low essay";
        String desc = "Admissions.getAdmissionStatus('e', 1, 500, 500, ' ', 5)";
        String expected = "Deny";
        String actual = Admissions.getAdmissionStatus('e', 1, 500, 500, ' ', 5);
        testResult(id, desc, expected, actual);

        //engineering deny low math SAT (borderline)
        id = "engineering borderline deny";
        desc = "Admissions.getAdmissionStatus('e', 2, 490, 500, ' ', 5)";
        expected = "Deny";
        actual = Admissions.getAdmissionStatus('e', 2, 490, 500, ' ', 5);
        testResult(id, desc, expected, actual);
        
        //engineering defer low math sat (borderline)
        id = "engineering borderline defer";
        desc = "Admissions.getAdmissionStatus('e', 3, 690, 600, ' ', 1)";
        expected = "Defer";
        actual = Admissions.getAdmissionStatus('e', 3, 690, 600, ' ', 1);
        testResult(id, desc, expected, actual);
        
        //engineering admit due to alumni
        id = "engineering admit due to alumni";
        desc = "Admissions.getAdmissionStatus('e', 3, 600, 500, ' ', 5)";
        expected = "Admit";
        actual = Admissions.getAdmissionStatus('e', 3, 600, 500, ' ', 5);
        testResult(id, desc, expected, actual);
        
        //engineering high end border
        id = "engineering high end";
        desc = "Admissions.getAdmissionStatus('e', 4, 800, 800, ' ', 5)";
        expected = "Admit";
        actual = Admissions.getAdmissionStatus('e', 4, 800, 800, ' ', 5);
        testResult(id, desc, expected, actual);
        
        
        //engineering admit border
        id = "engineering borderline admit";
        desc = "Admissions.getAdmissionStatus('e', 2, 700, 600, ' ', 0)";
        expected = "Admit";
        actual = Admissions.getAdmissionStatus('e', 2, 700, 600, ' ', 0);
        testResult(id, desc, expected, actual);
        
        
        //Liberal-arts low end deny
        id = "Liberal Arts low end";
        desc = "Admissions.getAdmissionStatus('l', 1, 200, 200, ' ', 5)";
        expected = "Deny";
        actual = Admissions.getAdmissionStatus('l', 1, 200, 200, ' ', 5);
        testResult(id, desc, expected, actual);
        
        //liberal arts low reading SAT (borderline)
        id = "Liberal Arts low reading SAT";
        desc = "Admissions.getAdmissionStatus('l', 3, 500, 490, ' ', 5)";
        expected = "Deny";
        actual = Admissions.getAdmissionStatus('l', 3, 500, 490, ' ', 5);
        testResult(id, desc, expected, actual);
        
        //liberal arts borderline admit
        id = "liberal arts borderline admit";
        desc = "Admissions.getAdmissionStatus('l', 3, 500, 650, ' ', 1)";
        expected = "Admit";
        actual = Admissions.getAdmissionStatus('l', 3, 500, 650, ' ', 1);
        testResult(id, desc, expected, actual);
        
        //high end liberal arts
        id = "liberal arts high end";
        desc = "Admissions.getAdmissionStatus('l', 4, 800, 800, ' ', 5)";
        expected = "Admit";
        actual = Admissions.getAdmissionStatus('l', 4, 800, 800, ' ', 5);
        testResult(id, desc, expected, actual);
        
        //liberal arts admit due to alumni
        id = "liberal arts alumni";
        desc = "Admissions.getAdmissionStatus('l', 3, 500, 500, ' ', 5)";
        expected = "Admit";
        actual = Admissions.getAdmissionStatus('l', 3, 500, 500, ' ', 5);
        testResult(id, desc, expected, actual);


        //liberal arts defer
        id = "liberal arts defer";
        desc = "Admissions.getAdmissionStatus('l', 3, 500, 500, ' ', 1)";
        expected = "Defer";
        actual = Admissions.getAdmissionStatus('l', 3, 500, 500, ' ', 1);
        testResult(id, desc, expected, actual);
        
        
        //fine arts low end
        id = "fine arts low";
        desc = "Admissions.getAdmissionStatus('f', 1, 0, 0, 'p', 1)";
        expected = "Deny";
        actual = Admissions.getAdmissionStatus('f', 1, 0, 0, 'p', 1);
        testResult(id, desc, expected, actual);
        
        
        //fine arts high end
        id = "fine arts high";
        desc = "Admissions.getAdmissionStatus('f', 4, 0, 0, 'e', 1)";
        expected = "Admit";
        actual = Admissions.getAdmissionStatus('f', 4, 0, 0, 'e', 1);
        testResult(id, desc, expected, actual);
        
        //fine arts deny (borderline)
        id = "fine arts border deny";
        desc = "Admissions.getAdmissionStatus('f', 2, 0, 0, 'f', 1)";
        expected = "Deny";
        actual = Admissions.getAdmissionStatus('f', 2, 0, 0, 'f', 1);
        testResult(id, desc, expected, actual);
        
        //fine arts defer good portfolio (borderline with admit)
        id = "fine arts good portfolio";
        desc = "Admissions.getAdmissionStatus('f', 2, 0, 0, 'g', 1)";
        expected = "Defer";
        actual = Admissions.getAdmissionStatus('f', 2, 0, 0, 'g', 1);
        testResult(id, desc, expected, actual);
        
        //fine arts admit due to alumni
        id = "fine arts alumni admit";
        desc = "Admissions.getAdmissionStatus('f', 2, 0, 0, 'g', 5)";
        expected = "Admit";
        actual = Admissions.getAdmissionStatus('f', 2, 0, 0, 'g', 5);
        testResult(id, desc, expected, actual);
        
        
        
        // Invalid test cases are provided for you below - You do NOT
        // need to add additional invalid tests. Just make sure these
        // pass!
        id = "School X";
        desc = "Admissions.getAdmissionStatus('X', 3, 500, 500, 'g', 5)";
        expected = "class java.lang.IllegalArgumentException";
        String actualResult = "";
        try {
            actual = Admissions.getAdmissionStatus('X', 3, 500, 500, 'g', 5);
        } catch (IllegalArgumentException e) {
            actualResult = "" + e.getClass();
        }
        testResult(id, desc, expected, actualResult);

        id = "Essay score too low";
        desc = "Admissions.getAdmissionStatus('e', 0, 500, 500, 'g', 5)";
        expected = "class java.lang.IllegalArgumentException";
        actualResult = "";
        try {
            actual = Admissions.getAdmissionStatus('e', 0, 500, 500, 'g', 5);
        } catch (IllegalArgumentException e) {
            actualResult = "" + e.getClass();
        }

        testResult(id, desc, expected, actualResult);
        id = "Essay score too high";
        desc = "Admissions.getAdmissionStatus('e', 5, 500, 500, 'g', 5)";
        expected = "class java.lang.IllegalArgumentException";
        actualResult = "";
        try {
            actual = Admissions.getAdmissionStatus('e', 5, 500, 500, 'g', 5);
        } catch (IllegalArgumentException e) {
            actualResult = "" + e.getClass();
        }
        testResult(id, desc, expected, actualResult);

        id = "Math SAT too low";
        desc = "Admissions.getAdmissionStatus('e', 1, 100, 500, 'g', 5)";
        expected = "class java.lang.IllegalArgumentException";
        actualResult = "";
        try {
            actual = Admissions.getAdmissionStatus('e', 1, 100, 500, 'g', 5);
        } catch (IllegalArgumentException e) {
            actualResult = "" + e.getClass();
        }

        testResult(id, desc, expected, actualResult);
        id = "Math SAT too high";
        desc = "Admissions.getAdmissionStatus('e', 3, 850, 500, 'g', 5)";
        expected = "class java.lang.IllegalArgumentException";
        actualResult = "";
        try {
            actual = Admissions.getAdmissionStatus('e', 3, 850, 500, 'g', 5);
        } catch (IllegalArgumentException e) {
            actualResult = "" + e.getClass();
        }
        testResult(id, desc, expected, actualResult);

        id = "Reading SAT too low";
        desc = "Admissions.getAdmissionStatus('e', 1, 400, 100, 'g', 5)";
        expected = "class java.lang.IllegalArgumentException";
        actualResult = "";
        try {
            actual = Admissions.getAdmissionStatus('e', 1, 400, 100, 'g', 5);
        } catch (IllegalArgumentException e) {
            actualResult = "" + e.getClass();
        }

        testResult(id, desc, expected, actualResult);
        id = "Reading SAT too high";
        desc = "Admissions.getAdmissionStatus('e', 2, 800, 900, 'g', 5)";
        expected = "class java.lang.IllegalArgumentException";
        actualResult = "";
        try {
            actual = Admissions.getAdmissionStatus('e',2, 800, 900, 'g', 5);
        } catch (IllegalArgumentException e) {
            actualResult = "" + e.getClass();
        }
        testResult(id, desc, expected, actualResult);

        id = "Portfolio x";
        desc = "Admissions.getAdmissionStatus('F', 3, 0, 0, 'x', 5)";
        expected = "class java.lang.IllegalArgumentException";
        actualResult = "";
        try {
            actual = Admissions.getAdmissionStatus('F', 3, 0, 0, 'x', 5);
        } catch (IllegalArgumentException e) {
            actualResult = "" + e.getClass();
        }
        testResult(id, desc, expected, actualResult);

    }

}