import java.util.Scanner;



public class proj1 {
	static class LinkedList {
		Node head; //first item in the list
		
		static class Node {
		    String str;
		    Node next;
		    
		    //Constructor for the node initializing next to null
		    Node( String s ) {
		    	str = s;
		    	next = null;
		    }
		    
		    //Constructor for the node with a next element
		    Node( String s, Node n ) {
		    	str = s;
		    	next = n;
		    }
		}
	}
	public static void main(String arg[])
	{
		Scanner in = new Scanner(System.in);
		while(in.hasNext()) {
			
		}
		
		in.close();
		
	}

}
