package project4;

import java.io.File;
import java.util.Scanner;

public class Proj4 {
	
	
	public static void main( String[] args )
	{
		System.out.println( "Enter the dictionary file: ");
		Scanner input = new Scanner( System.in );
		
		String dictionaryName = input.nextLine();
		File dictionaryFile = new File( dictionaryName );
		
		System.out.println( "Enter the file to be spell checked: " );
		String inputName = input.nextLine();
		File inputFile = new File( inputName );
		
		System.out.println( "Enter the output file: " );
		String outputName = input.nextLine();
		File outputFile = new File( outputName );
		
		
		
		
		
		
		
		input.close();
		
	}

}
