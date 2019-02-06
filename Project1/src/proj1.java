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
				return nodeCount;
			} else {
				nodeCount++;
				while( currentNode.next != null ) {
					currentNode = currentNode.next;
					nodeCount++;
					if( s.compareTo(currentNode.str) == 0 ) {
						//nodeCount++;
						return nodeCount++;
					}
				}
			}
			return 0;
		}
		
		public static int findAndMove( LinkedList list, String s )
		{
			//keeps count of how far into the list the data is
			int nodeCount = 0;
			//if the list is empty return 0
			if( list.head == null ) {
				return nodeCount;
			}
			//node to keep track of the current node in the list
			Node currentNode = list.head;
			Node previousNode = list.head;
			//if the head has the data, no more nodes need to be searched
			if( s.compareTo(currentNode.str) == 0 ) {
				nodeCount++;
				return nodeCount;
			} else {
				nodeCount++;
				while( currentNode.next != null ) {
					currentNode = currentNode.next;
					nodeCount++;
					if( s.compareTo(currentNode.str) == 0 ) {
						//nodeCount++;
						previousNode.next = currentNode.next;
						currentNode.next = list.head;
						list.head = currentNode;
						return nodeCount;
					}
					previousNode = previousNode.next;
				}
			}
			return 0;
		}
		public static String findByIndex( LinkedList list, int position )
        {
            Node currentNode = list.head;
            while( position > 1 ) {
                currentNode = currentNode.next;
                position--;
            }
            return currentNode.str;
            
        }
		
		public static String findAndMoveByIndex( LinkedList list, int position )
        {
            Node currentNode = list.head;
            Node previousNode = list.head;
            int listPos = 1;
            if( position == 1 ) {
            	return currentNode.str;
            } else {
            	currentNode = currentNode.next;
            	listPos++;
            	while( listPos < position ) {
            		currentNode = currentNode.next;
            		previousNode = previousNode.next;
            		listPos++;
            	}
            	previousNode.next = currentNode.next;
            	currentNode.next = list.head;
            	list.head = currentNode;
            }
            return currentNode.str;
            
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
		System.out.print("0 ");
		String line;
		int uncompBytes = 0;
		int compBytes = 0;
		while( in.hasNext() ) {
			line = in.nextLine();
			int lineLen = line.length();
			StringBuilder currentWord = new StringBuilder();
			for( int i = 0; i < lineLen; i++ ) {
				char ch = line.charAt(i);
				if( Character.isLetter(ch) ) {
					currentWord.append( ch );
					//System.out.print( currentWord.toString() );
				} else {
					if( currentWord.length() == 0 ) {
						System.out.print( ch );
						uncompBytes++;
						compBytes++;
					}else {
						String word = currentWord.toString();
						currentWord = new StringBuilder();
						int wordPosition = LinkedList.findAndMove( list, word );
						if( wordPosition == 0 ) {
							LinkedList.add( list, word );
							System.out.print( word );
							uncompBytes += word.length();
							compBytes += word.length();
						} else {
							//LinkedList.moveToFront( list, word );
							System.out.print( wordPosition );
							uncompBytes += word.length();
							compBytes++;
						}
						System.out.print( ch );
						uncompBytes++;
						compBytes++;
					}
				}
			}
			if( currentWord.length() != 0 ) {
				String word = currentWord.toString();
				currentWord = new StringBuilder();
				int wordPosition = LinkedList.find( list, word );
				if( wordPosition == 0 ) {
					LinkedList.add( list, word );
					System.out.print( word );
					uncompBytes += word.length();
					compBytes += word.length();
				} else {
					LinkedList.moveToFront( list, word );
					System.out.print( wordPosition );
					uncompBytes += word.length();
					compBytes++;
				}
			}
			System.out.println("");
		}
		System.out.println("0 Uncompressed: " + uncompBytes + " bytes;  Compressed: "
		+ compBytes + " bytes");
		
	}
	public static void decrypt(Scanner in, LinkedList list )
	{
		//in.nextInt();//gets the scanner past the beginning "0 "
		//in.next();
		String line;
		Boolean beginning = true;
		Boolean end = false;
		int positionNum;
		
		while( in.hasNext() ) {
			line = in.nextLine();
			//line = line.trim();
			int lineLen = line.length();
			StringBuilder currentWord = new StringBuilder();
			if( lineLen != 0 && line.charAt(0) == '0' && !beginning ) {
				line = "";
				lineLen = 0;
				end = true;
			} else if (lineLen != 0 && line.charAt(0) == '0' && beginning ) {
				line = line.substring(2);
				lineLen = line.length();
				beginning = false;
			}
				
			for( int i = 0; i < lineLen; i++ ) {
				char ch = line.charAt(i);
				if( Character.isLetter(ch) ) {
					currentWord.append( ch );
					//System.out.print( currentWord.toString() );
				} else if( Character.isDigit(ch) ) {
					positionNum = Character.getNumericValue(ch);
					//int j = 0;
					int multiplyTimes = 1;
					
					while( ( i + multiplyTimes) < (lineLen) && Character.isDigit(line.charAt(i + multiplyTimes)) ) {
						multiplyTimes++;
					}
					for( int j = 0; j < multiplyTimes-1; j++ ) {
						positionNum = positionNum * 10;
						positionNum += Character.getNumericValue(line.charAt(i+j+1));
					}
					//if( Character.isDigit(line.charAt(i+1)) ) {
						//positionNum += 10;
					//}
					i += multiplyTimes-1;
					//if( i == lineLen ) {
						//i = (lineLen - 1);
					//}
					//positionNum = Character.getNumericValue(ch);
					String foundWord = LinkedList.findAndMoveByIndex(list, positionNum);
					multiplyTimes = 1;
					System.out.print(foundWord);
					LinkedList.moveToFront(list, foundWord);
				} else {
					if( currentWord.length() == 0 ) {
						System.out.print( ch );
						//uncompBytes++;
						//compBytes++;
					} else {
						String word = currentWord.toString();
						currentWord = new StringBuilder();
						LinkedList.add(list, word);
						System.out.print(word);
						System.out.print( ch );
						//uncompBytes++;
						//compBytes++;
						}
					}
			}
			if( currentWord.length() != 0 ) {
				String word = currentWord.toString();
				currentWord = new StringBuilder();
				LinkedList.add( list, word );
				System.out.print( word );
					//uncompBytes += word.length();
					//compBytes += word.length();
			}
			if( !end ) {
			System.out.println("");
			}
			
		}
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
