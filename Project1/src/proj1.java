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
		
		public static LinkedList add( LinkedList list, String s )
		{
			Node newNode = new Node( s );
			
			if( list.head == null ) {
				list.head = newNode;
			} else {
				newNode.next = list.head;
				list.head = newNode;
			}
			return list;
		}
		
		public static int find( LinkedList list, String s )
		{
			//keeps count of how far into the list the data is
			int nodeCount = 0;
			//if the list is empty return 0
			if( list.head == null ) {
				return nodeCount;
			}
			//node to keep track of the current node in the list
			Node currentNode = list.head;
			//if the head has the data, no more nodes need to be searched
			if( s.compareTo(currentNode.str) == 0 ) {
				nodeCount++;
			} else {
				while( currentNode.next != null ) {
					currentNode = currentNode.next;
					if( s.compareTo(currentNode.str) == 0 ) {
						nodeCount++;
						break;
					}
				}
			}
			return nodeCount;
		}
		
		public static boolean moveToFront( LinkedList list, String s )
		{
			if( list.head == null ) {
				return false;
			}
			Node currentNode = list.head;
			if( s.compareTo(currentNode.str ) == 0 ) {
				return true;
			}
			while( currentNode.next != null ) {
				if( s.compareTo( currentNode.next.str ) == 0 ) {
					Node nodeMove = currentNode.next;
					currentNode.next = nodeMove.next;
					nodeMove.next = list.head;
					list.head = nodeMove;
					return true;
				} else {
					currentNode = currentNode.next;
				}
			}
			
			return false;
		}
	}
	public static void encrypt( Scanner in, LinkedList list )
	{
		String line;
		while( in.hasNext() ) {
			line = in.nextLine();
			int lineLen = line.length();
			String currentWord = "";
			for( int i = 0; i < lineLen; i++ ) {
				char ch = line.charAt(i);
				if( Character.isLetter(ch) ) {
					currentWord.concat( Character.toString(ch) );
				} else {
					if( currentWord.length() == 0 ) {
						System.out.print( ch );
					}else {
						int wordPosition = LinkedList.find( list, currentWord );
						if( wordPosition == 0 ) {
							LinkedList.add( list, currentWord );
							System.out.print( currentWord );
						} else {
							LinkedList.moveToFront( list, currentWord );
							System.out.print( wordPosition );
						}
					}
				}
			}
			//Scanner lineScanner = new Scanner( line );
			
			
			//lineScanner.close();
		}
		
	}
	public static void decrypt(Scanner in, LinkedList list )
	{
		
	}
	public static void main(String arg[])
	{
		LinkedList list = new LinkedList();
		
		Scanner in = new Scanner(System.in);;
		if( in.hasNextInt() ) {
			decrypt( in, list );
		} else {
			encrypt( in, list );
		}
		
		in.close();
		
	}

}
