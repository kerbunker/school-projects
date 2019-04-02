package proj3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class proj3 {
	
	public static Edge heapArray[];
	public static int heapSize;
	
	public static void insert( Edge e )
	{
		heapArray[heapSize] = e;
	}
	
	public static void main( String args[] ) throws FileNotFoundException
	{
		System.out.println( "Enter the input file: " );
		Scanner input = new Scanner( System.in );
		String fileName = input.nextLine();
		File inputFile = new File( fileName );
		Scanner fileScanner = new Scanner( inputFile );
		heapArray = new Edge[ 5000 ];
		heapSize = 0;
		
		while ( fileScanner.hasNext() ) {
			int current = fileScanner.nextInt();
			if ( current != -1 ) {
				int v1 = current;
				int v2 = fileScanner.nextInt();
				double w = fileScanner.nextDouble();
				Edge currentEdge = new Edge( v1, v2, w );
				insert( currentEdge );
			}
				
		}
		
		fileScanner.close();
		input.close();
	}

}
