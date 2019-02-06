import java.util.Scanner;


/**
 * This program takes input from standard.in and compresses it. Or, it takes an
 * already compressed file and decompresses it. This is done by
 * adding each word in the input to a linked list and every time that word appears
 * in the input again, it is replaced by the position the word is in the list, and
 * word is moved to the front of the list. Moving the words to the front of the list
 * every time it is used helps to keep the more frequently accessed words near the front
 * of the list to cut down on how long it takes to find the word in the list each time
 * it is used for the frequently used words.
 * @author Katelyn Bunker (kbunker)
 *
 */
public class proj1 {
	
	/**
	 * This class implements a linked list for the words to be stored in
	 * during compression and decompression. The list is made up of Nodes
	 * which contain the String that makes up the word from the input
	 * and the pointer to the next node in the list. The beginning of the 
	 * list is denoted by the "head" node. The methods used in the maintaining
	 * of the LinkedList are add, find, findAndMove, moveToFront, findByIndex,
	 * and findAndMoveByIndex.
	 *
	 */
	static class LinkedList {
		Node head; //first item in the list
		
		/**
		 * This is the class for the Node object which makes up the 
		 * LinkedList. Each Node stores a String and the pointer to the
		 * next Node in the list. If there is no next Node, next is null.
		 * 
		 *
		 */
		static class Node {
		    String str;
		    Node next;
		    
		    /**
		     * This is the constructor for the Node object when there is no
		     * next Node in the list.
		     * @param s is the String containing the input word
		     */
		    Node( String s ) {
		    	str = s; //sets the str String to the given String s
		    	next = null; //sets the next pointer to null
		    }
		    
		    /**
		     * This is the constructor for the Node object when a next Node is
		     * given to the constructor
		     * @param s the String containing the input word
		     * @param n the next Node in the list. Usually the previous head
		     * Node in this implementation of a LinkedList
		     */
		    Node( String s, Node n ) {
		    	str = s; //Sets the str String to the given s
		    	next = n; //Sets the the next pointer to the next node given by n
		    }
		}
		
		/**
		 * This method add a new Node to the LinkedList and sets the "head" Node
		 * to point to this new Node.
		 * @param list The LinkedList to edit
		 * @param s the new String to add to the new Node in the list
		 * @return the LinkedList
		 */
		public static LinkedList add( LinkedList list, String s )
		{
			//Creates the new Node with next set as null
			Node newNode = new Node( s );
			
			//checks if the list is empty and sets the newNode to the head
			if( list.head == null ) {
				list.head = newNode;
			} else {
				//if the list is not empty, the next pointer of the newNode is
				//set to point to the head Node and the head is set to the newNode
				newNode.next = list.head;
				list.head = newNode;
			}
			return list;
		}
		
		/**
		 * Looks through the given LinkedList and find the Node with the given
		 * String and returns the position the Node is in the list. (The first item
		 * in the list is given position 1)
		 * @param list the LinkedList to look through
		 * @param s the String to find the match of in the Nodes
		 * @return the position of the Node in the list, or 0 if it was not found
		 */
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
				nodeCount++;//moves the nodeCount so the first Node is position 1
				return nodeCount;
			} else {
				nodeCount++;//increases the nodeCount so it starts at 1
				//searches through each node until there is not a next Node to search
				while( currentNode.next != null ) {
					//moves the pointer to the next Node in the list
					currentNode = currentNode.next;
					//increases the nodeCount
					nodeCount++;
					//Compares the given String s to the String stored in the Node (str)
					if( s.compareTo(currentNode.str) == 0 ) {
						//returns the position in the List the String was found at
						return nodeCount;
					}
				}
			}
			//returns 0 if the String s was not found in the list
			return 0;
		}
		
		/**
		 * This method combines the find method and the moveToFront method to keep the
		 * program from needing to search through the list twice every time a word
		 * is accessed and moved
		 * @param list the list to search through
		 * @param s the String to find stored in the Nodes of the list
		 * @return the position the String was found at, or 0 if the String was not found
		 */
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
			//node to keept track of the Node before the current one to
			//facilitate moveing the Node to the front
			Node previousNode = list.head;
			//if the head has the data, no more nodes need to be searched
			if( s.compareTo(currentNode.str) == 0 ) {
				//increases the nodeCount to 1 so the first node in the List is given position 1
				nodeCount++;
				return nodeCount;
			} else {
				nodeCount++;
				//searches the list while there are still Nodes to search
				while( currentNode.next != null ) {
					//moves the currentNode to the next Node in the list
					currentNode = currentNode.next;
					//increases the nodeCount
					nodeCount++;
					//Compares the given String to the String stored in the Node
					if( s.compareTo(currentNode.str) == 0 ) {
						//If the matching String is found the Node needs to be moved
						//This makes the node before the currentNode point to the Node after 
						//which breaks the currentNode out of the list
						previousNode.next = currentNode.next;
						//Now tehe currentNode points to the first Node in the list
						currentNode.next = list.head;
						//The currentNode is set to the head of the list
						list.head = currentNode;
						//return the position that the Node was originally found at
						return nodeCount;
					}
					//if the matching String was not found, move the previousNode pointer up one
					previousNode = previousNode.next;
				}
			}
			//returns 0 if the String was not found in the list
			return 0;
		}
		
		/**
		 * 
		 * @param list
		 * @param position
		 * @return
		 */
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
