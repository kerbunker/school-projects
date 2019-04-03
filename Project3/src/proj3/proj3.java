package proj3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class proj3 {


    
    public static void insertMST( Edge head, Edge e )
    {
        if( head == null ) {
            head = e;
        } else {
            Edge current = head;
            while( current.getNext()!= null ) {
                current = current.getNext();
            }
            current.setNext( e );
        }
    }
    
    

	
    public static void main( String args[] ) throws FileNotFoundException
    {
        System.out.println( "Enter the input file: " );
        Scanner input = new Scanner( System.in );
        String fileName = input.nextLine();
        File inputFile = new File( fileName );
        Scanner fileScanner = new Scanner( inputFile );
        HeapArray heap = new HeapArray();
        UpTree upTree = new UpTree();
        AdjacencyList adList = new AdjacencyList();
        Edge head = null;
        int listMSTSize = 0;

        int current = fileScanner.nextInt();
        int components = 0;
        while ( current != -1 ) {
            int v1 = current;
            int v2 = fileScanner.nextInt();
            double w = fileScanner.nextDouble();
            Edge currentEdge = new Edge( v1, v2, w );
            heap.insert( currentEdge );
            adList.insert( v1, v2, currentEdge );
            adList.insert( v2, v1, currentEdge );
            if( upTree.getKey( v1 ) == 0 ) {
                upTree.setKey(v1, -1);
                components++;
            }
            if( upTree.getKey(v2) == 0 ) {
                upTree.setKey(v2, -1);
                components++;
            }
            current = fileScanner.nextInt();	
        }
		
        heap.printHeap( 0 );
        adList.printList( 0 );
        /**
        while( components > 1 ) {
            Edge minEdge = deleteMin();
            int setU = find( minEdge.getVertex1() );
            int setV = find( minEdge.getVertex2() );
            if( setU != setV ) {
                union( setU, setV );
                insertMST( head, minEdge );
                listMSTSize++;
                components--;
            }
        }
        */
        
        fileScanner.close();
        input.close();
    }

}
