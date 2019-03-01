package proj2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class proj2 {
	
	public static char posttrav[];
	public static char pretrav[];
	
	//static class familyTree {
		//Node root;
		
		static class Node {
			//Node parent;
			Node childArray[];
			int childrenNum;
			char person;
			
			Node( Node children[], int num, char p ){
				//parent = parentNode;
				childArray = children;
				childrenNum = num;
				person = p;
			}
			
			
			Node( char p ) {
				//parent = null;
				childArray = null;
				childrenNum = 0;
				person = p;
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
			System.out.println(current.person);
			for ( int l = 0; l < size-1; l++ ) {
				System.out.print(current.childArray[l].person);
				System.out.print(", ");
			}
			System.out.println("");
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
			System.out.println(current.person);
			for ( int l = 0; l < childrenNum; l++ ) {
				System.out.print(current.childArray[l].person);
				System.out.print(", ");
			}
			System.out.println("");
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
    	
         pretrav= new char[250];
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
         //System.out.print(root.person);
         //printTree( root );
         testInput.close();
         
         //input.close();
         //fileScanner.close();
    }
}
