package proj3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class proj3 {
    
    public static Edge head;


    
    public static void insertMST( Edge e )
    {
        if( head == null ) {
            head = e;
        } else {
            Edge current = head;
            if( current.getVertex1() > e.getVertex1() ) {
                e.setNext( current );
                head = e;
            } else {
                if( current.getVertex1() == e.getVertex1() && 
                        current.getVertex2() >= e.getVertex2() ) {
                        e.setNext( current );
                        head = e;
                } else {
                    boolean found = false;
                    while( current.getNext()!= null ) {
                        if( current.getNext().getVertex1() > e.getVertex1() ) {
                            e.setNext( current.getNext() );
                            current.setNext( e );
                            found = true;
                        } else {
                            if( current.getNext().getVertex1() == e.getVertex1() && 
                                    current.getNext().getVertex2() >= e.getVertex2() ) {
                                e.setNext( current.getNext() );
                                current.setNext( e );
                                found = true;
                            } else {
                                current = current.getNext();
                            }
                        }
                    }
                    if( !found ) {
                        current.setNext( e );
                    }
                }
            }
        }
    }
    
    public static void printMST( PrintWriter writer, Edge current ) {
        int v1 = current.getVertex1();
        int v2 = current.getVertex2();
        if( v1 < v2 ) {
            writer.printf( "%4d %4d\n", v1, v2 );
        } else {
            writer.printf( "%4d %4d\n", v2, v1 );
        }
        writer.flush();
        if( current.getNext() != null ) {
            printMST( writer, current.getNext() );
        }
    }   
    

	
    public static void main( String args[] ) throws FileNotFoundException, UnsupportedEncodingException
    {
        System.out.println( "Enter the input file: " );
        Scanner input = new Scanner( System.in );
        String fileName = input.nextLine();
        System.out.println( "Enter the output file: " );
        String outputName = input.nextLine();
        File inputFile = new File( fileName );
        File outputFile = new File( outputName );
        PrintWriter writer = new PrintWriter( outputFile );
        Scanner fileScanner = new Scanner( inputFile );
        HeapArray heap = new HeapArray();
        UpTree upTree = new UpTree();
        AdjacencyList adList = new AdjacencyList();
        head = null;
        //int listMSTSize = 0;

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
		
        heap.printHeap( writer, 0 );
        //adList.printList( 0 );
        while( components > 1 ) {
            Edge minEdge = heap.deleteMin();
            int setU = upTree.find( minEdge.getVertex1() );
            int setV = upTree.find( minEdge.getVertex2() );
            if( setU != setV ) {
                upTree.union( setU, setV );
                insertMST( minEdge );
                //listMSTSize++;
                components--;
            }
        }
        printMST( writer, head );
        adList.printList( writer, 0 );
        fileScanner.close();
        input.close();
        writer.close();
    }

}
