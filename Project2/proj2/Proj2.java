
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;


/**
 * This project takes an input file and an output file from the console and then reads
 * the file to build and analyze a family tree. The file provides a tree listed in preorder
 * as well as postorder and creates the tree from this data using a recursive build method.
 * The file is then read for questions about the relationships between people on the tree. 
 * The least common ancestor between the two people is found and from the distance from each
 * person and this ancestor, the relationship between the people is found abd printed to the 
 * given file. The tree is then printed out in level order.
 * 
 * @author Katelyn Bunker (kbunker)
 *
 */
public class proj2 {

    /** This character array holds the characters read from the file in the preorder tree*/
    public static char posttrav[];
    /** This character array holds the characters read from the postorder line of the given file*/
    public static char pretrav[];


    /**
     * This class creates the nodes used to build the tree. It holds
     * an array for any and all children nodes the node has. It also holds
     * a character that represents the person for the tree, as well as a
     * boolean field for when the nodes need to be marked to find a common ancestor
     *
     */
    static class Node {
        Node childArray[];//holds the children nodes
        int childrenNum;//holds the number of children the node has
        char person;// the character representing the person
        boolean mark;//true when a node is marked, otherwise false

        /**
         * This is the constructor for a node with an array of children
         * @param children the array holding the children nodes
         * @param num the number of children nodes in the array
         * @param p the character representing the person
         */
        Node( Node children[], int num, char p ){
        	childArray = children;//holds the children nodes
        	childrenNum = num;//holds the number of children the array holds
        	person = p;//represents the person for the node
        	mark = false;//initialized to false. changed to true to mark
        }
        
        /**
         * The node constructor for a node without children
         * @param p represent the person for the node
         */
        Node( char p ) {
        	childArray = null;//set to null because there are no children
        	childrenNum = 0;//there are no children in the array
        	person = p;//represents the person for the Node
        	mark = false;//default is false, only true when looking for a common ancestor
        }
    }
	
    /**
     * This method builds the tree from the pre- and postorder list in a recursive manner.
     * It takes in the size of the current tree, the position to start reading the preorder
     * list and the position to start reading the post order list. It returns a node which can
     * then be linked to the previous node back up the tree and then ultimately returns the
     * root node of the tree.
     * @param size the size of the tree, or subtree for the next node
     * @param prestart the position to start reading the pretrav array
     * @param poststart the position to start reading the posttrav array
     * @return the most recently created node
     */
    public static Node buildTree( int size, int prestart, int poststart )
    {
        //if there is a single node in this "tree" then it is a leaf
        //and just the single node needs to be made and returned
        if( size == 1 ){
            Node current = new Node( pretrav[prestart]);//make a new node with no children
            return current;
            }
        //keeps track of if a subtree is connected to one of the children of the current node
        //-initialized to false
        boolean subtree = false;
        int i = 0;//enables looking through the next set of the tree for a subtree
        //while a subtree has not been found, and there's still characters left
        //in the current subtree:
        while( !subtree && (i < size - 1) ){
        	//if the character in the array after the character for the current node (+i)
        	//does not match the first character in the post array (+i), then that child is
        	//the start of a subtree
            if( pretrav[prestart + 1 + i] != posttrav[poststart + i] ) {
                subtree = true;
                }
            i++;
        }
        if( !subtree ) {
        	//If none of the children are part of the subtree, and array is made of the single
        	//nodes that represent each of the children nodes on the tree
            Node children[] = new Node[ size - 1 ];
            //iterates through each character in the pre and postorder lists for the current size
            //which represent the children of the current node. The buildTree method is recursively
            //called on each to create each new child node. Each child is then placed in the
            //child array that is linked to the current node
            for( int j = 0; j < size - 1; j++ ) {
                children[j] = buildTree( 1, prestart + 1 + j, poststart + j );
            }
            //creates a new node with the children array and the size of the children array
            Node current = new Node( children, size - 1, pretrav[prestart] );
            return current;//returns the new node
        } else {//if a child with a subtree was found
            int childrenNum = 0;//starts a count for the number of children this node has
            int k = 0;//iterator to go through each character up to the size of the current subtree
            int childprestart = prestart + 1;//keeps track of where the child subtree starts
            int childpoststart = poststart;//keeps track of where the child subtree ends
            Node children[] = new Node[size-1];//creates a new array for the children
            while( k < size - 1 ) {//iterates through the characters in the given subtree
                int subSize = 0;//keeps track of the size of the child subtree
                char currentChild = pretrav[childprestart];//gets the next child character
                //searches through the characters in the posttrav array to find where it matches
                //next child character in the pretrav array. Any characters between
                //where the post array started and where the match was found are all part
                //of the child subtree
                while( currentChild != posttrav[childpoststart + subSize] ) {
                    subSize++;//if the characters don't match, another character is added to the subtree
                    k++;//moves to the next character position
                }
                subSize++;//advances to the next character after the child subtree
                k++;//advances to the next character after the child subtree
                //calls the recursive method with the part of the array determined to be 
                //part of the child subtree
                children[childrenNum] = buildTree(subSize, childprestart, childpoststart);
                childrenNum++;//adds to the count of the current node children
                childprestart += subSize;//advanced the pre- and posttrav arrays past the subtree
                childpoststart += subSize;
            }
            //after all the possible child subtrees have been taken care of, the current node
            //is made with the array of children or children subtrees
            Node current = new Node( children, childrenNum, pretrav[prestart]);
            return current;
		}
    }

    /**
     * This method runs through the tree with a queue to print the 
     * tree in level order. The first node in the tree is added to the
     * queue and then that node is removed and printed. Then each child of that node is added
     * to the tree. Then each child is visited, removed and printed, and each child
     * of those nodes is added to the end of the queue so the next level is printed
     * after all of the nodes from the first level have been printed. This continues
     * until the queue has been emptied and there are no more children to visit
     * @param writer the writer to print the data to the output file
     * @param p root node
     * @param queue the queue to run through the tree
     */
    public static void printTree( PrintWriter writer, Node p, Node[] queue)
    {
    	//if the root node is null the tree is empty and there is nothing to print
        if( p == null ) {
            return;
        }
        int size = 0;//the size of the current queue
        int front = 0;//the front of the queue, where nodes are removed
        int back = 0;//the back of the queue where nodes are added
        queue[front] = p;//adds the root node to the tree
        back++;//moves the back of the queue down
        size++;//increase the size of the queue
        while( size != 0 ) {//while the queue isn't empty
            Node q = queue[front];//dequeue the front node
            front++;//move the front of the queue
            size--;//decrease the size of the queue
            writer.print(q.person);//print the character from the node
            for( int i = 0; i < q.childrenNum; i++ ) {//add each child to the queue
                queue[back] = q.childArray[i];
                size++;
                back++;
            }
            if( size != 0 ) {//if there are still characters left to print, add a comma to separate
                writer.print(", ");
            }
        }
        writer.println(".");//print the period after all of the characters have been printed
    }
	
	/**
	 * This method searches through the tree for the given character in a node,
	 * and then it marks that node and all the nodes leading back up through the tree.
	 * @param p the current node the method is searching
	 * @param find the character to find in the nodes of the tree
	 * @return true if the node was found, and false otherwise
	 */
	public static boolean findAndMark( Node p, char find )
	{
		//if the person held in the node matches the character given, we have found the node
		//that we are looking for and we mark it and return true
		if( p.person == find ) {
			p.mark = true;
			return true;
		//If this is not the correct node, and this node has no children to search through
	    //and will return false
		} else if( p.childrenNum == 0 ) {
			return false;
		//if there are children to search through the method will search through
	    //them until either the desired node is found or there are no more children
		} else {
			int i = 0;//sets the count for the children to 0
			boolean found = false;
			//while the node hasn't been found, iterate through the children
			while( !found && i < p.childrenNum ) {
				found = findAndMark( p.childArray[i], find );
				i++;
			}
			//if the node has been found, mark this node and return true
			if( found ) {
				p.mark = true;
				return found;
			//otherwise return false because the node still has not been found
			} else {
				return false;
			}
		}
	}
    
	/**
	 * This method looks through the tree to find the node that matches B, then it
	 * returns through the tree and counts each generation it passes through until it reaches the
	 * least common ancestor (LCA) and returns this count. The marked nodes that B passes through are also
	 * unmarked along the way
	 * @param p the current node the program is checking through
	 * @param B the character for person B to look for in the tree
	 * @return the count for the distance between B and the LCA
	 */
	public static int findCommon( Node p, char B )
	{
		//If this is the correct node and it is marked,
		//this is the lowest common ancestor, thus 0 is returned
		//because there is no distance between B and the LCA
		if( p.person == B ) {
			if( p.mark ) {
				p.mark = false;
				return 0;
			} else {
				//if this node is not marked then the LCA has not been found yet
				//and the LCA will not add to the count, so this must be accounted for
				return 1;
			}
			//return 0 because this is the current gen
		} else if( p.childrenNum == 0 ) {
			//if this is not the right person, but there are no
			//children nodes, return -1 because there is nowhere else to look
			return -1;
		} else {
			int gen = -1;//Set to be obvious when the common ancestor has been found
			int i = 0;//keeps track of the child the program is on
			//while the common ancestor has not been found, and there are more children to check
			while( gen < 0 && i < p.childrenNum ) {
				//recursively call the method with the next child node
				gen = findCommon( p.childArray[i], B );
				i++;//increase the child to look through
			}
			//if gen is >= 0; person B has been found
			if( gen >= 0 ) {
				//if this node is marked, it is either the LCA or above the LCA, and the count has already finished
				//so unmark this node and return the count that was previously generated
				if ( p.mark ) {
					p.mark = false;//unmark this node
					//The distance from B to the common ancestor has been found,
					//and we don't want to add to that number
					return gen;
				//if this node is not marked, the LCA has not been found yet and the count needs to be added to
				} else {
					return gen + 1;//add one more for this current gen to the distance from B
				}
			} else {
				//The program looked through all the children in this tree and did not find B
				return -1;
			}
		}
	}
	
	/**
	 * Finds the distance from the common ancestor to node a. First the node with the 
	 * character match personA is found, then the distance to the common ancestor is found.
	 * The common ancestor and all nodes above it were unmarked after the B distance
	 * was found, so the nodes should all be counted until the unmarked nodes start.
	 * Because of the way the method is set up, the unmarked common ancestor must be counted
	 * at the start of the backtrace from A, and not when the common ancestor is found
	 * @param p the current node the method is analyzing
	 * @param a the character to look for in the tree
	 * @return the count for the distance between node a and the common ancestor
	 */
	public static int findDistance( Node p, char a )
	{
		//person a has been found!
		if( p.person == a ) {
			if ( p.mark ) {//if it is marked, it was not visited by the previous method
				p.mark = false;//unmark the node
				return 1;//start the count, accounting for the common (unmarked) ancestor
			} else {//if a is unmarked, it was visited when B was found, so it must be the LCA
				return 0;//if this is the lowest common ancestor (LCA), the distance is 0
			}
		} else {//person A has not been found. the children must now be searched
			int i = 0;
			int gen = -1;//sets a marker for when a is found and no more searching is needed
			//searches through each of the children of the current node
			while( gen < 0 && i < p.childrenNum ) {
				gen = findDistance( p.childArray[i], a );//calls the recursive method
				i++;//increases to the next child
			}
			//if gen is not -1, then a was found and the distance count was started
			if( gen >= 0 ) {
				//if the current node is marked then it is before the common ancestor
				if( p.mark ) {
					p.mark = false;//unmark the node
					return gen + 1;//increase the count for the distance between A and the LCA
				} else {//if it is not marked, it is the LCA or above it, and thus not counted
					return gen;
				}
			} else {//if gen is not >0, A was not found and the previous node keeps looking
				return -1;
			}
		}
	}
	
	/**
	 * This method starts the program by asking the user for an input and output file.
	 * It then uses Scanners to read the input file line by line, storing the preorder
	 * and postorder tree data in global variable arrays that can be accessed when 
	 * the tree is being made. The method then call a recursive buildTree method
	 * which builds the tree and returns the root of the tree. The file is then
	 * read for any lines of questions about the relationships between the people
	 * on the tree. The least common ancestor of the two people is found and the relationship
	 * between the two is determined based on their distance from the LCA and then the
	 * relationship is printed to the file. After all the lines from the file have been
	 * read, the tree is printed in level order to the file.
	 * @param args command line arguments
	 * @throws FileNotFoundException throws exception if the input file is not found
	 * @throws UnsupportedEncodingException throws exception if the output file cannot be made
	 */
    public static void main( String args[] ) throws FileNotFoundException, UnsupportedEncodingException
    {
        System.out.print("Enter input file: ");//asks for input file
        Scanner input = new Scanner( System.in );//makes a new scanner to read input
        String fileName = input.nextLine();//reads in the next input line
        System.out.print("Enter output file: ");//asks for the output file
        String outputName = input.nextLine();//reads in the next input line
        
        File inputFile = new File( fileName );//opens the input file given
        File outputFile = new File( outputName );//opens the output file given
        //creates the writer to write to the output file
        PrintWriter writer = new PrintWriter( outputFile, "UTF-8" );
        
        //creates the scanner to read the input file
        Scanner fileScanner = new Scanner( inputFile );
        //reads the first input line
        String fileLine = fileScanner.nextLine();
        String preorder = null;
        String postorder = null;
        //checks that the file line starts with the symbol for the preorder line
        if( fileLine.charAt(0) == '<' ) {
            preorder = fileLine;
        } else {
            System.out.println("First line of file is not the preorder");
        }
        //gets the next line from the file
        fileLine = fileScanner.nextLine();
        //checks that the next line starts with the symbol for the postorder line
        if( fileLine.charAt(0) == '>' ) {
            postorder = fileLine;
        } else {
            System.out.println("Second line of file is not the postorder");
        }

        //creates the global array for the preorder characters
        pretrav = new char[253];
        int strLen = preorder.length();//gets the length of the preorder string
        int size= 0;//keeps track of the array size
        //goes through the preorder sting checking each character in the string
        for ( int i = 0; i < strLen; i++ ){
            char current = preorder.charAt(i);//gets the next character
            //checks for and of the file only characters that aren't part of the tree
            if( current != '<' && current != '>' && current != '.' && current != ' '
                    && current != ',' && current != '?' ) {
                pretrav[size] = current;//if a valid tree character it is added to the array
                size++;//increases the array size
            }
        }
        posttrav = new char[size];//the post order array only needs to be as big as the preorder
        int postsize = 0;//keeps track of the current size of the post order array
        //iterates through the post order string analyzing each character
        for ( int i = 0; i < strLen; i++ ){
            char current = postorder.charAt(i);//gets the next character
            //checks for file only characters
        	if( current != '<' && current != '>' && current != '.' && current != ' '
                    && current != ',' && current != '?' ) {
                posttrav[postsize] = current;//adds the character to the array
                postsize++;//increases the size
            }
        }
        
        //calls the recursive function to build the tree, starting with the size of the arrays
        //as well as the position to start reading the arrays, in this case, at the beginning
        Node root = buildTree( size, 0, 0 );
        Node printQueue[] = new Node[size];//creates the printqueue for when the tree is printed

        String queryLine;
        //reads through the rest of the file for any and all questions lines
        while( fileScanner.hasNextLine()) {
            queryLine = fileScanner.nextLine();//gets the next line
            //checks if the line has any information
            if( queryLine.length() == 0 ) {
                //do nothing. There's no input line here
            //checks that it is a query line, noted by the '?' at the beginning of the line
          	} else if( queryLine.charAt(0) != '?' ) {
                System.out.println( "Query line error" );
            } else {
                char current;
                int i = 1;//starts reading the line after the first character ( the ?)
                current = queryLine.charAt(i);
                //while the character is a file character, read the next character
                while( current == ' ' || current == '>' || current == '<'
                        || current == ',' || current == '?' || current == '.' ) {
                    i++;//increase the counter
                    current = queryLine.charAt(i);//increases to the next character to read
                }
                i++;//increases past the last character read
                char personA = current;//the most recent character must be personA
                current = queryLine.charAt(i);//gets the next character to find personB
                //reads through the file until a non-file only character is found
        		while( current == ' ' || current == '>' || current == '<'
        				|| current == ',' || current == '?' || current == '.' ) {
        			i++;
        			current = queryLine.charAt(i);
        		}
        		//the last character must be personB
        		char personB = current;
        		//calls the method to find A and mark all nodes leading to it
        		findAndMark( root, personA );
        		 
        		//distance from B to common ancestor
        		int genBtoCommon = findCommon( root, personB );
        		//distance from A to common ancestor
        		int genAtoCommon = findDistance( root, personA );
        		
        		//The following section tests the A and B distances to find their
        		//relationship, and then prints the found relationship to the file
        		if( genAtoCommon == 0) {
        			if( genBtoCommon == 0 ) {
        				writer.println( personA + " is " + personB + "." );
        		    } else if( genBtoCommon == 1) {
        		    	writer.println( personA + " is " + personB + "'s parent." );
        		    } else if( genBtoCommon == 2) {
        		    	writer.println( personA + " is " + personB + "'s grandparent." );
        		    } else if( genBtoCommon == 3) {
        		    	writer.println( personA + " is " + personB 
        		    			+ "'s great-grandparent." );
        		    } else if( genBtoCommon > 3) {
        		    	writer.println( personA + " is " + personB + "'s" ); 
        		    	for( int j = 0; j < genBtoCommon - 2; j++) {
        					writer.print( " great" );
        				}
        				writer.println( "-grandparent." );
        		    }
        		} else if( genAtoCommon == 1 ) {
        			if( genBtoCommon == 0 ) {
        				writer.println( personA + " is " + personB + "'s child." );
        			} else if( genBtoCommon == 1 ) {
                        writer.println( personA + " is " + personB + "'s sibling." );
        			} else if( genBtoCommon == 2 ) {
        				writer.println( personA + " is " + personB + "'s aunt/uncle." );
        			} else if( genBtoCommon >= 3 ) {
        				writer.println( personA + " is " + personB + "'s" );
        				for( int j = 0; j < genAtoCommon - 2; j++) {
        					writer.print( " great" );
        				}
        				writer.println( "-aunt/uncle." );
        			}
        		} else if( genAtoCommon == 2 && genBtoCommon == 1 ) {
        			writer.println( personA + " is " + personB + "'s niece/nephew." );
        		} else if ( genAtoCommon >= 3 && genBtoCommon == 0 ) {
        			writer.print( personA + " is " + personB + "'s" );
    				for( int j = 0; j < genAtoCommon - 2; j++) {
    					writer.print( " great" );
    				}
    				writer.println( "-grandchild." );
        		} else if( genAtoCommon >= 2 ) {
        			if( genBtoCommon == 1 ) {
        				writer.print( personA + " is " + personB + "'s" );
        				for( int j = 0; j < genAtoCommon - 2; j++) {
        					writer.print( " great" );
        				}
        				writer.println( "-niece/nephew." );
        			} else if( genBtoCommon >= 2 ) {
        				int num = (Math.min( genAtoCommon, genBtoCommon ) - 1);
        				writer.println( personA + " is " + personB + "'s " + num 
        						+ "th cousin " + Math.abs( genAtoCommon - genBtoCommon ) 
        						+ " times removed." );
        			}
        		}
        	}
        }
        //after there are no more questions and the file has been completely read, the tree
        //is printed in level order
        printTree( writer, root, printQueue);
        
        //closes the scanners and the writer
        writer.close();
        input.close();
        fileScanner.close();
    }
}