package proj3;

/**
 * This is the object class for the Up-Trees that represent the connection maps for
 * the vertices of the graph used to determine the MST. The Up-Trees are implemented
 * by an array, where each vertex in the tree points to the root, which holds the number
 * of nodes in the tree.
 * @author Katelyn Bunker (kbunker)
 *
 */
public class UpTree {
    /** This holds the array that implements the UpTree */
    private int array[];
    
    /**
     * This is the constructor for the UpTree. It creates a new array with 1000 nodes
     * which is the highest number of vertices that could be analyzed with this
     * implementation
     */
    public UpTree()
    {
        array = new int[ 1000 ];
    }
    
    /**
     * Returns the key that is stored at the index in the array
     * @param vertex the index of the array to find the key for
     * @return the key at the given index
     */
    public int getKey( int vertex )
    {
        return array[ vertex ];
    }
    
    /**
     * Sets the key at the given index of the array
     * @param vertex the index of the array to set
     * @param key the key value to set at the given vertex
     */
    public void setKey( int vertex, int key )
    {
        array[ vertex ] = key;
    }
    
    /**
     * Iterates through the UpTree until the root node is found, represented by a negative
     * integer giving the number of nodes in the tree
     * @param vertex the index of the array to find the key of
     * @return the key at the given index
     */
    public int find( int vertex )
    {
        //goes through the array going to each vertex pointed to by the previous key
        //until the root is found
        while( array[ vertex ] >= 0 ) {
            vertex = array[ vertex ];
        }
        return vertex;
    }
    
    /**
     * Combines the two trees given into a single tree. This is done by
     * finding the size of the two given trees and comparing them, and then sets the root of
     * the smaller tree to point to the larger tree and updates the node count at the root of the
     * new tree
     * @param v1 the root of the first tree
     * @param v2 the root of the second tree
     * @return the root of the resulting tree
     */
    public int union( int v1, int v2 )
    {
        //finds the number of nodes in each tree and compares them
        if( Math.abs( array[ v1 ] ) >= Math.abs( array[ v2 ] ) ) {
            //if the first tree is larger, or they're the same size, the first tree
            //becomes the root of the combined tree. The node count is updated to 
            //include the nodes added from the incoming tree
            array[ v1 ] = array[ v1 ] + array[ v2 ];
            array[ v2 ] = v1;//the root of the second tree now points to the combined tree root
            return v1;
        } else {
            //if the second tree is larger, it becomes the root of the combined tree
            //the root now contains the added number of nodes between the 2 trees
            array[ v2 ] = array[ v1 ] + array[ v2 ];
            array[ v1 ] = v2;
            return v2;
        }
    }
}
