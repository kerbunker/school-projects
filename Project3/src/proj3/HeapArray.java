package proj3;

import java.io.PrintWriter;

/**
 * This class implements the heap for the the MST computation. It creates an array and each
 * index of the array is a node on the heap. The parents and children of each node can be found
 * by multiplying the parent position by 2 for the left child or mulitiplying by 2 and adding 1
 * for the right. The parent can be found by dividing the child position by 2. The insert, upHeap,
 * deleteMin, and downHeap functions are also implemented in this class.
 * @author Katelyn Bunker (kbunker)
 *
 */
public class HeapArray {
    /** The array of Edges that make up the heap */
    private Edge array[];
    /** The size of the heap */
    private int size;
    
    /**
     * This is the constructor for the heap array object. It creates a new Edge array
     * and sets the size of the array to 0.
     */
    public HeapArray()
    {
        array = new Edge[ 5000 ];
        size = 0;
    }
    
    /**
     * Returns the size of the heap Array
     * @return the heap size
     */
    public int getSize()
    {
        return size;
    }
    
    /**
     * Swaps the Edges stored at the two indices of the array given as parameters
     * @param index1 the index of the array to swap with index2
     * @param index2 the index to swap with index1
     */
    public void swap( int index1, int index2 )
    {
        Edge temp = array[ index1 ];
        array[ index1 ] = array[ index2 ];
        array[ index2 ] = temp;
    }
    
    /**
     * Recursively resorts the heap after a new Edge has been added. This
     * is done by comparing the weight of the Edge at the given index with its parent,
     * and swaps the two if the parent weight is higher
     * @param index the index to sort into the heap
     */
    public void upHeap( int index )
    {
        if( index > 0 ) {//if the index is 0, this is the root and has no parent
            int parentIndex = ( index - 1 ) / 2;//gets the parent index
            //checks if the parent weight is larger than the child
            if( array[ parentIndex ].getWeight() > array[ index ].getWeight() ) {
                swap( parentIndex, index );//swaps the two
                upHeap( parentIndex );//calls the method on the new index of the Edge
            }
        }
    }

    /**
     * Inserts the given Edge in the array at the end then calls the upHeap
     * method to re-sort the heap
     * @param e the new Edge to add to the heap
     */
    public void insert( Edge e )
    {
        array[ size ] = e;
        size++;
        upHeap( size - 1 );
    }
    
    /**
     * Compares the Edge of the current index to its children, and swaps the
     * Edge with its lowest child then recursively calls itself on the child's index
     * @param index the index of the current Edge to sort into the heap
     */
    public void downHeap( int index )
    {
        int i = 0;//represents the smallest child of the index
        if( ( 2*index )+2 < size ) { //checks if the index has 2 children
            //compares the two children
            if( array[ (2*index)+2 ].getWeight() < array[ (2*index)+1 ].getWeight() ) {
                i = (2*index)+2;//the right child is smallest
            } else {
                i = (2*index)+1;//the left child is smallest
            }
        } else if( (2*index)+1 < size ) {//only the left child exists
            i = (2*index)+1;
        }
        //if i == 0, the node has no children
        if( i > 0 && array[ index ].getWeight() > array[ i ].getWeight() ) {
            //compares the weight of the Edge at the index to its smallest child the swaps them
            swap( index, i );
            downHeap( i );//recursively calls the method on the child index
        }
    }
    
    /**
     * Deletes the minimum value in the heap, then moves the Edge at the back
     * of the array to the root note and re-sorts the heap to keep the order
     * @return the minimum Edge from the heap
     */
    public Edge deleteMin()
    {
        Edge e = array[ 0 ];//gets the root Edge
        size--;//decreases the size of the heap
        swap( 0, size );//swaps the end of the array to the root
        downHeap( 0 );//calls the recursive method to re-sort the heap
        return e;//returns the minimum Edge from the heap
    }
    
    /**
     * Recursively goes through the heap and prints both vertices of
     * each edge
     * @param index the index of the Edge to print
     */
    public void printHeap( PrintWriter writer, int index )
    {
        //gets the two vertices of the edge
        int v1 = array[ index ].getVertex1();
        int v2 = array[ index ].getVertex2();
        //checks which vertex is larger and prints the smaller one then the larger
        if( v1 < v2 ){
            writer.printf( "%4d %4d\n", v1, v2 );
        } else {
            writer.printf( "%4d %4d\n", v2, v1 );
        }
        writer.flush();
        //increases the index then calls the method again to print the next Edge
        index++;
        if( index < size ) {
            printHeap( writer, index );
        }
    }

}
