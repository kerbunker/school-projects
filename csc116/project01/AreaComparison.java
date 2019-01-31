/**
 * This class calculates the areas of a square, circle,
 * equilateral triangle, and rhombus with side length/radius "r"
 *
 * @author Katelyn Bunker
 */
public class AreaComparison {
    /**
     * creates a final value for the max r value used in the area calculations
     */
    public static final int MAX_R_VALUE = 20;
    
    /**
     * Starts the program
     *
     * @param args
     *             Command line arguments
     */    
    public static void main(String[] args) {
        System.out.println(" ");
        tableTitle();
        columnHeaders();
        for(int r = 1; r <= MAX_R_VALUE; r++){
            System.out.printf("%5d %10.2f %10.2f %10.2f    %10.2f\n", r,
                calculateSquareArea(r), calculateCircleArea(r),
                calculateTriangleArea(r), calculateRhombusArea(r));
        }
    }
    
    /**
     * Prints out the title for the tableTitle
     */
    public static void tableTitle() {
        System.out.printf("%40s\n", "Area Comparison (sq in)");
        System.out.printf("%40s\n", "r (side length/radius)");
        System.out.println(" ");        
    }
    
    /**
     * Prints out the headings for the columns
     */
    public static void columnHeaders() {
        System.out.printf("%42s %10s\n", "Equilateral", "Rhombus");
        System.out.printf("%8s %7s %10s %13s %12s\n", "r (in)", "Square",
            "Circle", "Triangle", "(45 deg)");
        System.out.printf("%7s %8s %10s %14s %10s\n",
            "-----", "------", "------", "----------", "-------");        
    }
    
    /**
     * Calculates the area of a square
     * @param r side length of a square
     * @return area of square
     */
    public static double calculateSquareArea(int r) {
        double area = Math.pow(r, 2);
        return area;
    }
    
    /**
     * Calculates the area of a circle
     * @param r radius of the circle
     * @return area of the circle
     */
    public static double calculateCircleArea(int r) {
        double area = Math.PI * Math.pow(r, 2);
        return area;
    }
    
    /**
     * Calculates the area of an equilateral triangle
     * @param r side length of a triangle
     * @return area of the triangle
     */
    public static double calculateTriangleArea(int r) {
        double area = Math.sqrt(3.0) / 4.0 * Math.pow(r, 2);
        return area;
    }
    
    /**
     * Calculates the area of a 45 degree rhombus
     * @param r side length of the rhombus
     * @return are of the rhombus
     */
    public static double calculateRhombusArea(int r) {
        double area = Math.pow( r, 2.0) / Math.sqrt(2.0);
        return area;
    }
}