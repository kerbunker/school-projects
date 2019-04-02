package proj3;

import java.io.File;
import java.util.Scanner;

public class proj3 {
	
	public static void main( String args[] )
	{
		System.out.println( "Enter the input file: " );
		Scanner input = new Scanner( System.in );
		String fileName = input.nextLine();
		File inputFile = new File( fileName );
		
		input.close();
	}

}
