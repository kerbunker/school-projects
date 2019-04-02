package proj3;

public class Edge {
	
	private int vertex1;
	private int vertex2;
	private double weight;
	private Edge next;
	
	public Edge( int v1, int v2, double w )
	{
		vertex1 = v1;
		vertex2 = v2;
		weight = w;
		next = null;
		
	}
	
	public void setNext( Edge n )
	{
		next = n;
	}
	
	public int getVertex1()
	{
		return this.vertex1;
	}
	
	public int getVertex2()
	{
		return this.vertex2;
	}
	
	public double getweight()
	{
		return this.weight;
	}
	public Edge getNext()
	{
		return this.next;
	}

}
