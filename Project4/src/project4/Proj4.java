package project4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Proj4 {
    public static Node hashTable[];
    public static int tableSize;
	
    static class Node {
        
        String key;
        Node next;
        
        Node( String key )
        {
            this.key = key;
            next = null;
        }
    }
    
    public static double hashFunction( String key, double r )
    {
        double hashKey = 1;
        int strSize = key.length();
        for( int i = 0; i < strSize; i++ ) {
            hashKey *= key.charAt(i);
            System.out.println( hashKey );
        }
        hashKey *= r;
        hashKey = hashKey - Math.floor(hashKey);
        hashKey = Math.floor(tableSize * hashKey);
        System.out.println( hashKey );
        
        return hashKey;
    }
	
	public static void main( String[] args ) throws FileNotFoundException
	{
	    Scanner input = new Scanner( System.in );
	    
	    System.out.println( "Enter the dictionary file: ");
	    String dictionaryName = input.nextLine();
	    File dictionaryFile = new File( dictionaryName );
	    Scanner dictionaryScanner = new Scanner( dictionaryFile );
	    
	    /**
	    System.out.println( "Enter the file to be spell checked: " );
	    String inputName = input.nextLine();
	    File inputFile = new File( inputName );
	    Scanner fileScanner = new Scanner( inputFile );
	    
	    System.out.println( "Enter the output file: " );
	    String outputName = input.nextLine();
	    File outputFile = new File( outputName );
	    PrintWriter output = new PrintWriter( outputFile );
	     */
        tableSize = 50000;

        hashTable = new Node[tableSize];
        double r = 1/((1 + Math.sqrt(5))/2);

        int collisionCount = 0;
        int wordCount = 0;
        int maxList = 0;
		while( dictionaryScanner.hasNext() ) {
		    wordCount++;
		    String key = dictionaryScanner.next();
		    Node current = new Node( key );
		    int index = (int) hashFunction( key, r );
		    if( hashTable[ index ] != null ) {
		        int listLen = 2;
		        System.out.println( "Collision!" );
		        collisionCount++;
		        Node p = hashTable[ index ];
		        while( p.next != null ) {
		            p = p.next;
		            listLen++;
		        }
		        maxList = Math.max( maxList, listLen );
		        p.next = current;
		    } else {
		        hashTable[ index ] = current;
		    }
		}
		System.out.println( "Collision Count: " + collisionCount );
		System.out.println( "Word Count: " + wordCount );
		System.out.println( "Max list length: " + maxList );
		
		input.close();
		dictionaryScanner.close();
		/**
		fileScanner.close();
		output.close();
		*/
	}

}
