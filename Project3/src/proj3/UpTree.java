package proj3;

public class UpTree {
    private int array[];
    
    public UpTree()
    {
        array = new int[ 1000 ];
    }
    
    public int getKey( int vertex )
    {
        return array[ vertex ];
    }
    
    public void setKey( int vertex, int key )
    {
        array[ vertex ] = key;
    }
    
    public int find( int vertex )
    {
        while( array[ vertex ] >= 0 ) {
            vertex = array[ vertex ];
        }
        return vertex;
    }
    
    public int union( int v1, int v2 )
    {
        //v1 = find( v1 );
        //v2 = find( v2 );
        if( array[ v1 ] >= array[ v2 ] ) {
            array[ v1 ] = array[ v1 ] + array[ v2 ];
            array[ v2 ] = v1;
            return v1;
        } else {
            array[ v2 ] = array[ v1 ] + array[ v2 ];
            array[ v1 ] = v2;
            return v2;
        }
    }

}
