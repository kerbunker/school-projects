package proj3;

/**
 * This class creates the Edge objects that hold the data for which
 * vertices are connected and what the weight the Edge between them is. It also
 * contains a next field to connect the Edges together in a linked list
 * @author Katelyn Bunker (kbunker)
 *
 */
public class Edge {
	
    /** the variable for the first vertex in the connected Edge */
	private int vertex1;
	/** the variable for the second vertex in the connected Edge */
	private int vertex2;
	/** the weight of the Edge */
	private double weight;
	/** the field for the next Edge in a list */
	private Edge next;
	
	/**
	 * The constructor for a new Edge. Sets the two vertices and the weight
	 * to the given parameters, and sets the next Edge to null
	 * @param v1 the first vertex connected by the Edge
	 * @param v2 the second vertex connected by the Edge
	 * @param w the weight of the Edge
	 */
	public Edge( int v1, int v2, double w )
	{
		vertex1 = v1;
		vertex2 = v2;
		weight = w;
		next = null;
		
	}
	
	/**
	 * Sets the next field to the Edge given
	 * @param n the next Edge to set a pointer to from the current Edge
	 */
	public void setNext( Edge n )
	{
		next = n;
	}
	
	/**
	 * Returns the vertex stored in the vertex1 field
	 * @return one of the vertices for the Edge
	 */
	public int getVertex1()
	{
		return this.vertex1;
	}
	
	/**
	 * Returns the vertex stored in the vertex2 field
	 * @return one of the vertices for the Edge
	 */
	public int getVertex2()
	{
		return this.vertex2;
	}
	
	/**
	 * Returns the given weight of the Edge
	 * @return the weight of the Edge
	 */
	public double getWeight()
	{
		return this.weight;
	}
	
	/**
	 * Gets the Edge that is pointed to by the next field
	 * @return the next Edge in the list
	 */
	public Edge getNext()
	{
		return this.next;
	}

}
