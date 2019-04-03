package proj3;

public class AdjacencyList {
    
    private Node array[];
    
    private class Node {
        private int vertex;
        private Edge edge;
        private Node next;
        
        public Node( int v, Edge e )
        {
            vertex = v;
            edge = e;
            next = null;
        }
        
        public int getVertex( )
        {
            return vertex;
        }
    }
    
    public AdjacencyList()
    {
        array = new Node[ 1001 ];
    }
    
    public void insert( int vertex, int link, Edge e )
    {
        Node n = new Node( link, e );
        if ( array[ vertex ] == null ) {
            array[ vertex ] = n;
        } else {
            Node current = array[ vertex ];
            if( current.getVertex() > n.getVertex() ) {
                n.next = current;
                array[ vertex ] = n;
            } else {
                boolean found = false;
                while( current.next != null  && !found ) {
                    if( current.next.getVertex() > n.getVertex() ) {
                        n.next = current.next;
                        current.next = n;
                        found = true;
                    } else {
                        current = current.next;
                    }
                }
                if( !found ) {
                    current.next = n;
                }
            }
        }
    }
    
    public void printList( int vertex )
    {
        if( array[ vertex ] != null ) {
            Node current = array[ vertex ];
            System.out.printf( "%4d", current.getVertex() );
            while( current.next != null ) {
                current = current.next;
                System.out.printf(" %4d", current.getVertex() );
            }
            System.out.println( "" );
        }
        if( vertex < 1000 ){
            vertex++;
            printList( vertex );
        }
    }
    
}
