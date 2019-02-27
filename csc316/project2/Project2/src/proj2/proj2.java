package proj2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class proj2 {
	public static void createTree( String preorder, String postorder )
    {
        
    }
    
    public static void main( String args[] ) throws FileNotFoundException
    {
         System.out.print("Enter input file: ");
         Scanner input = new Scanner( System.in );
         String fileName = input.next();
         File inputFile = new File( fileName );
         Scanner fileScanner = new Scanner( inputFile );
         String preorder = fileScanner.nextLine();
         String postorder = fileScanner.nextLine();
         
         createTree( preorder, postorder );
         
         input.close();
         fileScanner.close();
    }
}
