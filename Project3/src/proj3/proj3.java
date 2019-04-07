package proj3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

/**
 * This is the main class for the implementation Kruskal's Algorithm for Minimum Spanning Trees.
 * This class contains the main method and also the methods for running the MST algorithm.
 * This program asks for an input and an output file, then reads the data from the output file to
 * construct the various Edges between the vertices that make up the graph. The Edges are added into
 * a heap structure, which is sorted to always have the Edge with the minimum weight, given by the 
 * file, at the root of the heap. As the edges are being read in from the file, each new vertex
 * that is read is also added to a new up-tree. Each vertex is originally in its own uptree and
 * then as each minimum Edge is removed from the heap, if the two vertices of the Edge are not
 * already connected, their respective up-trees undergo a balanced union. This continues with each
 * successive minimum Edge until all the vertices have been connected into a single up-tree, and
 * thus, the minimum spanning tree has been determined. 
 */
public class proj3 {
    
    /** This is a global constant to hold the Edge at the beginning of the MST list */
    public static Edge head;
    
    /**
     * This method inserts the given Edge into the MST list stored with the head Node
     * in the global variable. The method first finds the high and low vertices of the Edge, so
     * the Edges can be stored in order of their lowest vertex. If the head Node is empty,
     * the given Edge is made into the head Node, otherwise, the head Node, and subsequent
     * Nodes following the head Node are compared to the given Edge to put the new Edge into
     * the appropriate place so the list stays sorted in increases vertex order.
     * @param e the Edge to add into the list
     */
    public static void insertMST( Edge e )
    {
        //variables to hold the low and high vertices from e, and from the current Node compared
        int vertexLow;
        int vertexHigh;
        int currentLow;
        int currentHigh;
        //compares the two vertices in e and stores them in the appropriate variable
        if( e.getVertex1() < e.getVertex2() ) {
            vertexLow = e.getVertex1();
            vertexHigh = e.getVertex2();
        } else {
            vertexLow = e.getVertex2();
            vertexHigh = e.getVertex1();
        }
        //if the head Node is empty, e is made the new head Node
        if( head == null ) {
            head = e;
        } else {
            //The high and low vertices of the head Node are set to the appropriate variable
            Edge current = head;
            if( current.getVertex1() < current.getVertex2() ) {
                currentLow = current.getVertex1();
                currentHigh = current.getVertex2();
            } else {
                currentLow = current.getVertex2();
                currentHigh = current.getVertex1();
            }
            //Checks if the low vertex in e is less than the low vertex of the head Node
            //if yes, e is made into the new head Node
            if( currentLow > vertexLow ) {
                e.setNext( current );
                head = e;
            } else {
                //If the head Node and e have the same low vertex, the high vertices are compared
                if( currentLow == vertexLow && 
                        currentHigh >= vertexHigh ) {
                        e.setNext( current );
                        head = e;
                } else {
                    boolean found = false;
                    int nextLow;
                    int nextHigh;
                    //Finds the low and high vertex values for the next Edge in the list
                    while( current.getNext()!= null && !found ) {
                        if( current.getNext().getVertex1() < current.getNext().getVertex2()) {
                            nextLow = current.getNext().getVertex1();
                            nextHigh = current.getNext().getVertex2();
                        } else {
                            nextLow = current.getNext().getVertex2();
                            nextHigh = current.getNext().getVertex1();
                        }
                        //If the head Node is less than e, the next Node is compared to e
                        if( nextLow > vertexLow ) {
                            e.setNext( current.getNext() );
                            current.setNext( e );
                            found = true;
                        } else {
                            if( nextLow == vertexLow && 
                                    nextHigh >= vertexHigh ) {
                                e.setNext( current.getNext() );
                                current.setNext( e );
                                found = true;
                            } else {
                                //if the next Node is lower than e, current moves on to the next Node
                                current = current.getNext();
                            }
                        }
                    }
                    //if the entire list has been compared to e, it is added to the end of the list
                    if( !found ) {
                        current.setNext( e );
                    }
                }
            }
        }
    }
    
    /**
     * This method iterates through the MST list recursively and prints each Edge
     * in order of increasing low vertex
     * @param writer the PrintWriter that is printing to the given output file
     * @param current the Edge with the vertices to be printed
     */
    public static void printMST( PrintWriter writer, Edge current ) {
        //gets the two vertices from the given Edge
        int v1 = current.getVertex1();
        int v2 = current.getVertex2();
        if( v1 < v2 ) {
            //if v1 is lower, it is printed first followed by v2
            writer.printf( "%4d %4d\n", v1, v2 );
        } else {
            //if v2 is lower, it is printed first followed by v1
            writer.printf( "%4d %4d\n", v2, v1 );
        }
        //flushes the writer to ensure the data is printed to the output file
        writer.flush();
        //if there are still Nodes left in the list, moves to the next Node
        if( current.getNext() != null ) {
            printMST( writer, current.getNext() );
        }
    }   
    
    /**
     * The main method for the MST algorithm. This method prompts the user for the files, 
     * creates the head and up-trees and the MST list, reads through the file and calls the 
     * methods to add the Edges to the appropriate lists and then calls the print methods
     * @param args any command line arguments given for the program
     * @throws FileNotFoundException if there is no proper input file given, an exception is thrown
     * @throws UnsupportedEncodingException
     */
    public static void main( String args[] ) 
            throws FileNotFoundException, UnsupportedEncodingException
    {
        //asks the user for the input and output files and uses a Scanner to read the input
        System.out.println( "Enter the input file: " );
        Scanner input = new Scanner( System.in );
        String fileName = input.nextLine();
        System.out.println( "Enter the output file: " );
        String outputName = input.nextLine();
        //opens the input and output files given from the user
        File inputFile = new File( fileName );
        File outputFile = new File( outputName );
        //creates the PrintWriter to write to the output file
        PrintWriter writer = new PrintWriter( outputFile );
        //creates the Scanner to read the input file
        Scanner fileScanner = new Scanner( inputFile );
        //creates the heap, uptree, and adjancency lists used for the algorithm
        HeapArray heap = new HeapArray();
        UpTree upTree = new UpTree();
        AdjacencyList adList = new AdjacencyList();
        head = null;

        int current = fileScanner.nextInt();//the current input from the file
        int components = 0;//stores the total number of vertices in the graph
        while ( current != -1 ) {
            //the two vertices of the Edge are read from the file, the the Edge weight
            int v1 = current;
            int v2 = fileScanner.nextInt();
            double w = fileScanner.nextDouble();
            //a new Edge is created and added to the heap
            Edge currentEdge = new Edge( v1, v2, w );
            heap.insert( currentEdge );
            //adds the Edge to the adjacency list
            adList.insert( v1, v2, currentEdge );
            adList.insert( v2, v1, currentEdge );
            //checks if the given vertices are in the upTree array already and adds them if not
            if( upTree.getKey( v1 ) == 0 ) {
                upTree.setKey(v1, -1);
                components++;
            }
            if( upTree.getKey(v2) == 0 ) {
                upTree.setKey(v2, -1);
                components++;
            }
            //gets the next set of data from the file until the end of file (-1) is found
            current = fileScanner.nextInt();	
        }
		
        //prints the data from the heap after all Edges have been added from the file
        heap.printHeap( writer, 0 );
        
        //goes through each Edge from the heap in creasing order until all the vertices have
        //been connected by the minimum number of Edges
        while( components > 1 ) {
          //gets the next minimum Edge and removes it from the heap
            Edge minEdge = heap.deleteMin();
            //gets the two vertices connected by the Edge and finds which upTree sets they are in
            int setU = upTree.find( minEdge.getVertex1() );
            int setV = upTree.find( minEdge.getVertex2() );
            //if they are not in the same upTree, combines the two upTrees into 1
            if( setU != setV ) {
                upTree.union( setU, setV );
                insertMST( minEdge );//adds the Edge to the list for the MST
                components--;//decreases the number of components still unconnected
            }
        }
        printMST( writer, head );//prints the Edges from the MST
        adList.printList( writer, 0 );//prints the adjacency list for each vertex
        
        //closes the Scanners and the PrintWriter
        fileScanner.close();
        input.close();
        writer.close();
    }
}
