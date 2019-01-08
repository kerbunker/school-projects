/**
 * Tests AreaComparison program
 * @author Suzanne Balik
 */
public class AreaComparisonTest {

  /**
   * Tests calculateSquareArea, calculateCircleArea, calculateTriangleArea,
   * and calculateRhombusArea methods
   * @param args command line arguments
   */
  public static void main(String[] args) {
    
    double area = AreaComparison.calculateSquareArea(25);
    System.out.printf("\nExpected: 625.00 Actual: %.2f\n", area);
    
    area = AreaComparison.calculateCircleArea(25);
    System.out.printf("\nExpected: 1963.50 Actual: %.2f\n", area);
    
    area = AreaComparison.calculateTriangleArea(25);
    System.out.printf("\nExpected: 270.63 Actual: %.2f\n", area);
    
    area = AreaComparison.calculateRhombusArea(25);
    System.out.printf("\nExpected: 441.94 Actual: %.2f\n", area);
    
  }
}