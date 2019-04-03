package proj3;

public class HeapArray {
    private Edge array[];
    private int size;
    
    public HeapArray()
    {
        array = new Edge[ 5000 ];
        size = 0;
    }
    
    public int getSize()
    {
        return size;
    }
    
    public void swap( int index1, int index2 )
    {
        Edge temp = array[ index1 ];
        array[ index1 ] = array[ index2 ];
        array[ index2 ] = temp;
    }
    
    public void upHeap( int index )
    {
        if( index > 0 ) {
            int parentIndex = ( index - 1 ) / 2;
            if( array[ parentIndex ].getWeight() > array[ index ].getWeight() ) {
                swap( parentIndex, index );
                upHeap( parentIndex );
            }
        }
    }


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
        int i = 0;
        if( ( 2*index )+2 < size ) {
            if( array[ (2*index)+2 ].getWeight() <= array[ (2*index)+1 ].getWeight() ) {
                i = (2*index)+2;
            } else {
                i = (2*index)+1;
            }
        } else if( (2*index)+1 < size ) {
            i = (2*index)+1;
        }
        if( i > 0 && array[ index ].getWeight() > array[ i ].getWeight() ) {
            swap( index, i );
            downHeap( i );
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
    public void printHeap( int index )
    {
        //gets the two vertices of the edge
        int v1 = array[ index ].getVertex1();
        int v2 = array[ index ].getVertex2();
        //checks which vertex is larger and prints the smaller one then the larger
        if( v1 < v2 ){
            System.out.printf( "%4d %4d\n", v1, v2 );
        } else {
            System.out.printf( "%4d %4d\n", v2, v1 );
        }
        //increases the index then calls the method again to print the next Edge
        index++;
        if( index < size ) {
            printHeap( index );
        }
    }

}
