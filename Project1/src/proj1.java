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
            //node to keep track of the Node before the current one to
            //facilitate moving the Node to the front
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
         * This method is used by the decrypt method to find the word that
         * is pointed to by the number in the compressed file. This number is the
         * position of the Node holding the given word in the list.
         * @param list The LinkedList generated by the input
         * @param position the position of the Node in the list
         * @return the String stored in the Node indicated
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

        /**
         * This method combines the findByIndex and moveToFront methods to increase
         * the efficiency of the program and keep the list from being searched through
         * when a Node is accessed and moved.
         * @param list the list storing the words for the input
         * @param position the position in the list the needed word is 
         * @return the word stored at the Node given by the position
         */
        public static String findAndMoveByIndex( LinkedList list, int position )
        {
            //stores the node current being searched
            Node currentNode = list.head;
            //stores the node directly before the current node to facilitate movement
            Node previousNode = list.head;
            //the current position the currentNode is pointing to
            int listPos = 1;
            //if the Node needed is the first one, no more searching is needed
            if( position == 1 ) {
                return currentNode.str;
            } else {
                //if the needed node is not the first, move the currentNode to the next
                currentNode = currentNode.next;
                //the currentNode has moved forward one, so the counter needs to move as well
                listPos++;
                //moves the currentNode and previousNode forward while the Node counter
                //is less than the given position of the needed Node
                while( listPos < position ) {
                    currentNode = currentNode.next;
                    previousNode = previousNode.next;
                    listPos++;
                }
                //after the requested Node has been found, the previousNode is set
                //to point to the Node after the currentNode
                previousNode.next = currentNode.next;
                //the currentNode is set to point to the head of the list
                currentNode.next = list.head;
                //the head of the list now points to the currentNode
                list.head = currentNode;
            }
            //returns the String stored at the currentNode
            return currentNode.str;
        }

        /**
         * This method finds the Node that contains the String given in the parameters
         * and then it moves that Node to the front of the list. This method is no longer
         * used in this program due to its functionality being incorporated into other methods
         * @param list the list that contains the words from the input
         * @param s the String to find in the list and move
         * @return true if the String was found and moved, false otherwise
         */
        public static boolean moveToFront( LinkedList list, String s )
        {
            //if the list is empty, return false
            if( list.head == null ) {
                return false;
            }
            //sets the pointer to be used to search to the beginning of the list
            Node currentNode = list.head;
            if( s.compareTo(currentNode.str ) == 0 ) {
                //if the first node contains the list, return true, it's already at the front
                return true;
            }
            //progress through the list while there's a next node to progress to
            while( currentNode.next != null ) {
                //check if the node after the currentNode contains the wanted String
                if( s.compareTo( currentNode.next.str ) == 0 ) {
                    //if the next Node contains the wanted String set the node to be moved to
                    //that next Node
                    Node nodeMove = currentNode.next;
                    //set the pointer to the next node to be the node after the node to be moved
                    currentNode.next = nodeMove.next;
                    //set the desired Node to the front of the list by setting the pointer
                    //of that Node to point to the head Node
                    nodeMove.next = list.head;
                    //Set the head to point to the new front Node
                    list.head = nodeMove;
                    return true;
                } else {
                    //progress to the next Node if the String was not found
                    currentNode = currentNode.next;
                }
            }
            return false;
        }
    }
	
    /**
     * This method takes the input from the console and goes through each line to
     * add each new word into the LinkedList and for words that are already in the List
     * replace the word in the output with the number representing the position in the List
     * @param in the input Scanner to bring in the data to process
     * @param list the LinkedList to add the words from the input 
     */
    public static void encrypt( Scanner in, LinkedList list )
    {
        //print the notifier that a file is encrypted
        System.out.print("0 ");
        //create the variable to store the input line
        String line;
        //start the counters for uncompressed data and the compressed data
        int uncompBytes = 0;
        int compBytes = 0;
        //keep scanning in data while there is still data in the Scanner
        while( in.hasNext() ) {
            //gets the next line from the Scanner
            line = in.nextLine();
            //get the length of the line of input
            int lineLen = line.length();
            //start a new StringBuilder to store the characters for each word
            StringBuilder currentWord = new StringBuilder();
            //go through the line of text character by character
            for( int i = 0; i < lineLen; i++ ) {
                char ch = line.charAt(i);
                //if the current character(ch) is a latter, add it to the StringBuilder
                if( Character.isLetter(ch) ) {
                    currentWord.append( ch );
                } else {
                    //if the character is not a letter, check if the StringBuilder has a word
                    if( currentWord.length() == 0 ) {
                        //if StringBuilder is empty print out the character
                        System.out.print( ch );
                        //increase the counts for the uncompressed and compressed count
                        uncompBytes++;
                        compBytes++;
                    }else {
                        //convert the word in the StringBuilder to a String
                        String word = currentWord.toString();
                        //set the currentWord to a new StringBuilder
                        currentWord = new StringBuilder();
                        //find the current word in the List and move it to the front
                        int wordPosition = LinkedList.findAndMove( list, word );
                        if( wordPosition == 0 ) {
                            //if the findAndMove method did not find the word add it to the list
                            LinkedList.add( list, word );
                            //print out the word
                            System.out.print( word );
                            //increase the uncompressed and compressed counts
                            uncompBytes += word.length();
                            compBytes += word.length();
                        } else {
                            //if the findAndMove method did find the word, print out the
                            //position number given by the method
                            System.out.print( wordPosition );
                            //add the full word the the uncompressed count but just
                            //increase the compressed bytes by the number of digits printed
                            uncompBytes += word.length();
                            compBytes++;
                            //add to the compressed count dependsing on thew digits of the number printed
                            if( wordPosition > 9) {
                                compBytes++;
                            }
                            if( wordPosition > 99 ) {
                                compBytes++;
                            }
                            if( wordPosition > 999 ) {
                                compBytes++;
                            }
                        }
                        //print out the current character and adjust byte counts
                        System.out.print( ch );
                        uncompBytes++;
                        compBytes++;
                    }
                }
            }
            //if the line ends with a word and not a character, check that the StringBuilder
            //is not empty and process the word as was done before
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
            //print out the newline at the end of the line
            System.out.println("");
        }
        //after processing all of the input data, print out the statistic for the compression
        System.out.println("0 Uncompressed: " + uncompBytes + " bytes;  Compressed: "
        + compBytes + " bytes");
    }

    /**
     * This method is started when the input starts with the "0 " character to represent
     * the compressed data to be decrypted. This method goes through much the same process as the 
     * previous method though it finds String from using the position number instead
     * of comparing all of the Strings to the one found from the data
     * @param in the Scanner to read through the input data
     * @param list the List to add the input to
     */
    public static void decrypt(Scanner in, LinkedList list )
    {
        //The variable to store the input line
        String line;
        //variables to help with processing the markers for the compressed data
        Boolean beginning = true;
        Boolean end = false;
        //variable used when looking up a word in the list
        int positionNum;
        //go through all the input while input still remains
        while( in.hasNext() ) {
            //get the next line from the input
            line = in.nextLine();
            //get the length of the line to be processed
            int lineLen = line.length();
            //start a StringBuilder for the words processed from the input
            StringBuilder currentWord = new StringBuilder();
            //if the line isn't empty and it starts with '0' and it's not the beginning
            //of the file, this must be the stat line and can be ignored for the decompression
            if( lineLen != 0 && line.charAt(0) == '0' && !beginning ) {
                //get rid of the line and set the length to zero
                line = "";
                lineLen = 0;
                //tell the program that this is the end of the file and doesn't need a newline
                end = true;
            } else if (lineLen != 0 && line.charAt(0) == '0' && beginning ) {
                //if this is the beginning of the input and the line starts with '0'
                //this is the marker for the beginning of the compressed file
                //and the '0 ' must be removed before the data can be processed
                //set the line to start from after the '0 ' characters
                line = line.substring(2);
                //set the lineLen to the updated length
                lineLen = line.length();
                //we are now past the beginning, so beginning is set to false so the next line
                //that starts with '0' must be the stat line at the end
                beginning = false;
            }
            //process through the entire line of data	
            for( int i = 0; i < lineLen; i++ ) {
                //get the next character in the line
                char ch = line.charAt(i);
                //if it is a letter, add it to the StringBuilder
                if( Character.isLetter(ch) ) {
                    currentWord.append( ch );
                } else if( Character.isDigit(ch) ) {
                    //if the next character is a digit, the corresponding Node in the list
                    //must be found and replaced by the word in the list
                    //change the character to the numeric value
                    positionNum = Character.getNumericValue(ch);
                    //if the position number is more than 1 digit, it must be multiplied by 10
                    int multiplyTimes = 1;
                    //checks the next characters in the line for digits until the digits end
                    //but checking to make sure the end of the line has not been reached
                    //so that an exception is not thrown
                    while( ( i + multiplyTimes) < (lineLen) && 
                        Character.isDigit(line.charAt(i + multiplyTimes)) ) {
                        //for each digit found in subsequent characters, the positionNum must be
                        //multiplied by 10 another time
                        multiplyTimes++;
                    }
                    //multiply the positionNum by 10 for the number of times it is needed
                    for( int j = 0; j < multiplyTimes-1; j++ ) {
                        positionNum = positionNum * 10;
                        //after multiplying by 10, add the digit from the next position
                        positionNum += Character.getNumericValue(line.charAt(i+j+1));
                    }
                    //increase i past the digits that were processed
                    i += multiplyTimes-1;
                    //get the word from the list given by the position and move that Node
                    //to the front of the list
                    String foundWord = LinkedList.findAndMoveByIndex(list, positionNum);
                    multiplyTimes = 1;
                    //printout the word represented by the number
                    System.out.print(foundWord);
                } else {
                    if( currentWord.length() == 0 ) {
                        //if the character is not a letter or digit, and the StringBuilder
                        //is empty, just print out the character
                        System.out.print( ch );
                    } else {
                        //if there is data in the StringBuilder convert it to a String and add it
                        //to the list before printing out the word to the output
                        String word = currentWord.toString();
                        currentWord = new StringBuilder();
                        LinkedList.add(list, word);
                        System.out.print(word);
                        System.out.print( ch );
                    }
                }
            }
            //make sure that when the line ends there is not still a word stored in StringBuilder
            if( currentWord.length() != 0 ) {
                String word = currentWord.toString();
                currentWord = new StringBuilder();
                LinkedList.add( list, word );
                System.out.print( word );
            }
            //if this isn't the end of the file, start a newline
            if( !end ) {
                System.out.println("");
            }

        }
    }

    /**
     * This method is the start of the program. It opens the Scanner and checks if the
     * input begins with the '0 ' marker. If it does, decrypt is called, otherwise
     * the encrypt method is called. At the end of the program the Scanner is closed
     * @param args the arguments from the command line that are not used in this program
     */
    public static void main(String args[])
    {
        //Creates the empty LinkedList
        LinkedList list = new LinkedList();

        //Opens the Scanner to read the input data
        Scanner in = new Scanner(System.in);
        //checks if the first character can be read as an int, thus is '0'
        if( in.hasNextInt() ) {
            //calls the decrypt method passing in the Scanner and the list
            decrypt( in, list );
        } else {
            //call the encrypt method passing in the Scanner and the list
            encrypt( in, list );
        }

        //Closes the Scanner
        in.close();

    }
}
