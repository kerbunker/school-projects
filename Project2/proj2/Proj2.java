package proj2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Proj2 {
	
	public static char posttrav[];
	public static char pretrav[];
	
	//static class familyTree {
		//Node root;
		
		static class Node {
			//Node parent;
			Node childArray[];
			int childrenNum;
			char person;
			boolean mark;
			
			Node( Node children[], int num, char p ){
				//parent = parentNode;
				childArray = children;
				childrenNum = num;
				person = p;
				mark = false;
			}
			
			
			Node( char p ) {
				//parent = null;
				childArray = null;
				childrenNum = 0;
				person = p;
				mark = false;
			}
		}
	//}
		
	public static Node buildTree( int size, int prestart, int poststart )
	{
		//if there is a single node in this "tree" then it is a leaf
		//and just the single node needs to be made and returned
		if( size == 1 ){
			Node current = new Node( pretrav[prestart]);
			return current;
		}
		boolean subtree = false;
		int i = 0;
		while( !subtree && (i < size - 1) ){
			if( pretrav[prestart + 1 + i] != posttrav[poststart + i] ) {
				subtree = true;
			}
			i++;
		}
		if( !subtree ) {
			Node children[] = new Node[ size - 1 ];
			for( int j = 0; j < size - 1; j++ ) {
				children[j] = buildTree( 1, prestart + 1 + j, poststart + j );
			}
			Node current = new Node( children, size - 1, pretrav[prestart] );
			//System.out.println(current.person);
			//for ( int l = 0; l < size-1; l++ ) {
				//System.out.print(current.childArray[l].person);
				//System.out.print(", ");
			//}
			//System.out.println("");
			return current;
		} else {
			int childrenNum = 0;
			int k = 0;
			int childprestart = prestart + 1;
			int childpoststart = poststart;
			Node children[] = new Node[size-1];
			while( k < size - 1 ) {
				int subSize = 0;
				char currentChild = pretrav[childprestart];
				while( currentChild != posttrav[childpoststart + subSize] ) {
					subSize++;
					k++;
				}
				subSize++;
				k++;
				children[childrenNum] = buildTree(subSize, childprestart, childpoststart);
				childrenNum++;
				childprestart += subSize;
				childpoststart += subSize;
			}
			Node current = new Node( children, childrenNum, pretrav[prestart]);
			//System.out.println(current.person);
			//for ( int l = 0; l < childrenNum; l++ ) {
				//System.out.print(current.childArray[l].person);
				//System.out.print(", ");
			//}
			//System.out.println("");
			return current;
		}
		//return null;
	}
	
	/**
	public static void printTree( Node p ){
		
		//System.out.print(p.person + ", ");
		if( p.childrenNum != 0 ) {
			for( int i = 0; i < p.childrenNum; i++ ) {
				System.out.print( ", " + p.childArray[i].person );
			}
			for( int i = 0; i < p.childrenNum; i++ ) {
				printTree(p.childArray[i]);
			}
		}
	}
	*/
	
	public static void printTree( Node p, Node[] queue)
	{
		if( p == null ) {
			return;
		}
		int size = 0;
		int front = 0;
		int back = 0;
		queue[front] = p;
		back++;
		size++;
		while( size != 0 ) {
			Node q = queue[front];
			front++;
			size--;
			System.out.print(q.person);
			for( int i = 0; i < q.childrenNum; i++ ) {
				queue[back] = q.childArray[i];
				size++;
				back++;
			}
			if( size != 0 ) {
				System.out.print(", ");
			}
		}
		System.out.print(".");
		
	}
	
	public static boolean findAndMark( Node p, char find )
	{
		if( p.person == find ) {
			p.mark = true;
			return true;
		} else if( p.childrenNum == 0 ) {
			return false;
		} else {
			int i = 0;
			boolean found = false;
			while( !found && i < p.childrenNum ) {
				found = findAndMark( p.childArray[i], find );
				i++;
			}
			if( found ) {
				p.mark = true;
				return found;
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
	
	public static int findDistance( Node p, char a )
	{
		if( p.person == a ) {
			if ( p.mark ) {
				p.mark = false;
				return 1;
			} else {
				return 0;
			}
		} else {
			int i = 0;
			int gen = -1;
			while( gen < 0 && i < p.childrenNum ) {
				gen = findDistance( p.childArray[i], a );
				i++;
			}
			if( gen >= 0 ) {
				if( p.mark ) {
					p.mark = false;
					return gen + 1;
				} else {
					return gen;
				}
			} else {
				return -1;
			}
		}
	}
	
    public static void main( String args[] ) throws FileNotFoundException
    {
    	/**
         System.out.print("Enter input file: ");
         Scanner input = new Scanner( System.in );
         String fileName = input.next();
         File inputFile = new File( fileName );
         Scanner fileScanner = new Scanner( inputFile );
         String fileLine = fileScanner.nextLine();
         String preorder = null;
         String postorder = null;
         if( fileLine.charAt(0) == '<' ) {
        	 preorder = fileLine;
         } else {
        	 System.out.println("First line of file is not the preorder");
         }
         fileLine = fileScanner.nextLine();
         if( fileLine.charAt(0) == '>' ) {
        	 postorder = fileLine;
         } else {
        	 System.out.println("Second line of file is not the postorder");
         }
         */
    	 Scanner testInput = new Scanner( System.in );
    	 String preorder = testInput.nextLine();
    	 String postorder = testInput.nextLine();
    	
         pretrav= new char[253];
         int strLen = preorder.length();
         int size= 0;
         for ( int i = 0; i < strLen; i++ ){
        	 char current = preorder.charAt(i);
        	 if( current != '<' && current != '>' && current != '.' && current != ' '
        			 && current != ',' && current != '?' ) {
        		 pretrav[size] = current;
        		 size++;
        	 }
         }
         posttrav = new char[size];
         int postsize = 0;
         for ( int i = 0; i < strLen; i++ ){
        	 char current = postorder.charAt(i);
        	 if( current != '<' && current != '>' && current != '.' && current != ' '
        			 && current != ',' && current != '?' ) {
        		 posttrav[postsize] = current;
        		 postsize++;
        	 }
         }
         
         Node root = buildTree( size, 0, 0 );
         Node printQueue[] = new Node[size];
         
         String queryLine;
         
         while( testInput.hasNextLine()) {
        	 queryLine = testInput.nextLine();
        	 if( queryLine.charAt(0) != '?' ) {
        		 System.out.println( "Query line error" );
        	 } else {
        		 char current;
        		 int i = 1;
        		 current = queryLine.charAt(i);
        		 while( current == ' ' || current == '>' || current == '<'
        				 || current == ',' || current == '?' || current == '.' ) {
        			 i++;
        			 current = queryLine.charAt(i);
        		 }
        		 i++;
        		 char personA = current;
        		 current = queryLine.charAt(i);
        		 while( current == ' ' || current == '>' || current == '<'
        				 || current == ',' || current == '?' || current == '.' ) {
        			 i++;
        			 current = queryLine.charAt(i);
        		 }
        		 char personB = current;
        		 boolean found = findAndMark( root, personA );
        		 
        		 //distance from B to common ancestor
        		 int genBtoCommon = findCommon( root, personB );
        		 int genAtoCommon = findDistance( root, personA );
        		 
        		 System.out.print(genAtoCommon + ", " + genBtoCommon);
        	 }
         }
         /**
         System.out.print(root.person);
         for( int i = 0; i < root.childrenNum; i++ ) {
        	 printTree( root.childArray[i], printQueue );
         }
         System.out.print(".");
         */
         printTree(root, printQueue);
         //System.out.print(root.person);
         //printTree( root );
         testInput.close();
         
         //input.close();
         //fileScanner.close();
    }
}
