package proj3;

import java.io.PrintWriter;

/**
 * This class holds the AdjacencyList object. This object is an array of vertices that
 * at each index of the array, it points to a vertex that is adjacent to the vertex represented by
 * the index. Then that vertex Node points to another vertex that is adjacent to the 
 * given vertex, and so on. 
 * @author Katelyn Bunker (kbunker)
 *
 */
public class AdjacencyList {
    
    /** This holds the array of Nodes for the vertices */
    private Node array[];
    
    /**
     * This inner class is for the Node objects that the array points to.
     * @author Katelyn Bunker (kbunker)
     *
     */
    private class Node {
        /** The vertex held in the Node */
        private int vertex;
        //private Edge edge;
        /** The next Node that is adjacent to the vertex at the array index*/
        private Node next;
        
        /**
         * This is the constructor for the Node object, setting the vertex to the given
         * vertex v, and the next field to Null
         * @param v the vertex to set at the Node
         */
        public Node( int v )
        {
            
            vertex = v;
            //edge = e;
            next = null;
        }
        
        /**
         * Returns the vertex at the current Node
         * @return the vertex of the node
         */
        public int getVertex( )
        {
            return vertex;
        }
    }
    
    /**
     * This is the constructor for the AdjacencyList. It creates an array that holds up to
     * 1000 vertices
     */
    public AdjacencyList()
    {
        array = new Node[ 1000 ];
    }
    
    /**
     * This function adds a new Node at the index given by the vertex parameter.
     * The list is sorted as a new Node is inserted, so the given vertex is compared to
     * each vertex currently in the list until one is found that is larger than the current
     * vertex and the new Node is inserted before that Node.
     * @param vertex the vertex that this new Node vertex is adjacent to
     * @param link the new Vertex that is adjacent to the given vertex
     */
    public void insert( int vertex, int link )
    {
        Node n = new Node( link );//creates a new Node
        //checks if there is already a Node at the array index, if there isn't, adds the new Node
        if ( array[ vertex ] == null ) {
            array[ vertex ] = n;
        } else {
            Node current = array[ vertex ];//gets the current Node
            //checks if the current Node is greater than the new Vertex, and adds the new Node 
            //before it if it is
            if( current.getVertex() > n.getVertex() ) {
                n.next = current;
                array[ vertex ] = n;
            } else {
                //At this point, it has been found that the first node is less than the 
                //new Node, so the next Node is searched so that the new Node can be inserted
                //between the two if need be
                boolean found = false;//keeps track of when the Node has been inserted
                while( current.next != null  && !found ) {
                    //compares the new vertex with the next vertex in the list
                    if( current.next.getVertex() > n.getVertex() ) {
                        //inserts the new node if next is larger
                        n.next = current.next;
                        current.next = n;
                        found = true;
                    } else {
                        //otherwise, moves on to the next node
                        current = current.next;
                    }
                }
                //if the entire list has been searched and the new Node is the largest
                //it is inserted at the end
                if( !found ) {
                    current.next = n;
                }
            }
        }
    }
    
    /**
     * This is a recursive method that runs through the array and prints
     * all the Nodes in the list at indices that are not Null
     * @param writer the PrintWriter to print to the file
     * @param vertex the current index of the array
     */
    public void printList( PrintWriter writer, int vertex )
    {
        //checks that the array index is not empty
        if( array[ vertex ] != null ) {
            Node current = array[ vertex ];
            //prints the vertex at the first node
            writer.printf( "%4d", current.getVertex() );
            //prints the vertex at each subsequent node until there are no more
            while( current.next != null ) {
                current = current.next;
                writer.printf(" %4d", current.getVertex() );
            }
            writer.println( "" );
            writer.flush();
        }
        //calls the next vertex in the array until all verticies have been printed
        if( vertex < 999 ){
            vertex++;
            printList( writer, vertex );
        }
    }
    
}
