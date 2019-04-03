package proj3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class proj3 {

    /** global variable for the heap implemented as an array */
    public static Edge heapArray[];
    /** keeps track of the size of the heap to insert a new edge at the end of the array */
    public static int heapSize;


    /**
     * Inserts the given Edge at the end of the array that represents the heap.
     * Then the edge weight is compared with the weight of the parent edge to ensure
     * that the heap is kept in increasing order. Then the method is called again
     * to continue sorting until the heap order is maintained
     * @param e the Edge to insert into the heap
     * @param index the index to the current Edge is located at
     */
    public static void insert( Edge e, int index )
    {
        //if the index given is the end of the heap, inserts the new Edge into that position
        if( index == heapSize ) {
            heapArray[ index ] = e;
            heapSize++;
        }
        
        //checks that the current Edge is not the root
        if( index > 0 ) {
            //gets the index of the parent Edge
            int parentIndex = ( index - 1 ) / 2;
            //checks that the parent is less than the child
            if( heapArray[ parentIndex ].getWeight() > e.getWeight() ) {
                //swaps the Edges to maintain the sorted heap order
                heapArray[ index ] = heapArray[ parentIndex ];
                heapArray[ parentIndex ] = e;
                //recursively calls the method to continue sorting
                insert( e, parentIndex );
            }
        }
    }
	
    public static void printHeap( int index )
    {
        int v1 = heapArray[ index ].getVertex1();
        int v2 = heapArray[ index ].getVertex2();
        if( v1 < v2 ){
            System.out.printf( "%4d %4d\n", v1, v2 );
        } else {
            System.out.printf( "%4d %4d\n", v2, v1 );
        }
        index++;
        if( index < heapSize ) {
            printHeap( index );
        }
    }

	
    public static void main( String args[] ) throws FileNotFoundException
    {
        //System.out.println( "Enter the input file: " );
        Scanner input = new Scanner( System.in );
        //String fileName = input.nextLine();
        //File inputFile = new File( fileName );
        //Scanner fileScanner = new Scanner( inputFile );
        heapArray = new Edge[ 5000 ];
        heapSize = 0;

        //be sure to change this back to fileScanner when complete
        int current = input.nextInt();
        while ( current != -1 ) {
            int v1 = current;
            int v2 = input.nextInt();
            double w = input.nextDouble();
            Edge currentEdge = new Edge( v1, v2, w );
            insert( currentEdge, heapSize );
            current = input.nextInt();	
        }
		
        printHeap( 0 );
        //fileScanner.close();
        input.close();
    }

}
