package project4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Proj4 {
    public static int probeNum = 0;
    public static Node hashTable[];
    public static int tableSize;
	
    static class Node {
        
        String key;
        Node next;
        
        Node( String key )
        {
            this.key = key;
            next = null;
        }
    }
    
    public static boolean wordCheck( int wordHash, String word )
    {
        Node tableNode = hashTable[ wordHash ];
        boolean found = false;
        if( tableNode == null ) {
            return found;
        } else if( tableNode.key.compareTo(word) == 0 ) {
            probeNum+= 1;
            found = true;
            return found;
        } else {
            probeNum++;
            while( tableNode.next != null && !found ) {
                if( tableNode.next.key.compareTo(word) == 0 ) {
                    found = true;
                } else {
                    tableNode = tableNode.next;
                }
                probeNum++;
            }
        }
        return found;
    }
    
    public static boolean spellCheck( String word )
    {
        int wordHash = (int) hashFunction( word );
        boolean found = false;
        if( !wordCheck( wordHash, word ) ) {
            String newWord = word;
            int newHash;
            if( word.charAt(0) >= 'A' && word.charAt(0) <= 'Z' ) {
                newWord = newWord.substring(0, 1).toLowerCase() + newWord.substring(1);
                newHash = (int) hashFunction( newWord );
                if( wordCheck( newHash, newWord ) ) {
                    found = true;
                }
            }
            if( !found && word.substring( word.length()-2 ).compareTo("'s") == 0 ) {
                newWord = word.substring(0, word.length()-2);
                newHash = (int) hashFunction( newWord );
                if( wordCheck( newHash, newWord )) {
                    found = true;
                } else if( word.charAt(0) >= 'A' && word.charAt(0) <= 'Z' ) {
                    newWord = newWord.substring(0, 1).toLowerCase() + newWord.substring(1);
                    newHash = (int) hashFunction( newWord );
                    if( wordCheck( newHash, newWord ) ) {
                        found = true;
                    }
                }
            }
            if( !found && word.substring( word.length()-1 ).compareTo("s") == 0 ) {
                newWord = word.substring(0, word.length()-1);
                newHash = (int) hashFunction( newWord );
                if( wordCheck( newHash, newWord )) {
                    found = true;
                } else if( word.charAt(0) >= 'A' && word.charAt(0) <= 'Z' ) {
                    newWord = newWord.substring(0, 1).toLowerCase() + newWord.substring(1);
                    newHash = (int) hashFunction( newWord );
                    if( wordCheck( newHash, newWord ) ) {
                        found = true;
                    }
                }
            }
            if( !found && word.substring( word.length()-2 ).compareTo("es") == 0 ) {
                newWord = word.substring(0, word.length()-2);
                newHash = (int) hashFunction( newWord );
                if( wordCheck( newHash, newWord )) {
                    found = true;
                } else if( word.charAt(0) >= 'A' && word.charAt(0) <= 'Z' ) {
                    newWord = newWord.substring(0, 1).toLowerCase() + newWord.substring(1);
                    newHash = (int) hashFunction( newWord );
                    if( wordCheck( newHash, newWord ) ) {
                        found = true;
                    }
                }
            }
            if( !found && word.substring( word.length()-2 ).compareTo("ed") == 0 ) {
                newWord = word.substring(0, word.length()-2);
                newHash = (int) hashFunction( newWord );
                if( wordCheck( newHash, newWord )) {
                    found = true;
                } else if( word.charAt(0) >= 'A' && word.charAt(0) <= 'Z' ) {
                    newWord = newWord.substring(0, 1).toLowerCase() + newWord.substring(1);
                    newHash = (int) hashFunction( newWord );
                    if( wordCheck( newHash, newWord ) ) {
                        found = true;
                    }
                }
            }
            if( !found && word.substring( word.length()-1 ).compareTo("d") == 0 ) {
                newWord = word.substring(0, word.length()-1);
                newHash = (int) hashFunction( newWord );
                if( wordCheck( newHash, newWord )) {
                    found = true;
                } else if( word.charAt(0) >= 'A' && word.charAt(0) <= 'Z' ) {
                    newWord = newWord.substring(0, 1).toLowerCase() + newWord.substring(1);
                    newHash = (int) hashFunction( newWord );
                    if( wordCheck( newHash, newWord ) ) {
                        found = true;
                    }
                }
            }
            if( !found && word.substring( word.length()-2 ).compareTo("er") == 0 ) {
                newWord = word.substring(0, word.length()-2);
                newHash = (int) hashFunction( newWord );
                if( wordCheck( newHash, newWord )) {
                    found = true;
                } else if( word.charAt(0) >= 'A' && word.charAt(0) <= 'Z' ) {
                    newWord = newWord.substring(0, 1).toLowerCase() + newWord.substring(1);
                    newHash = (int) hashFunction( newWord );
                    if( wordCheck( newHash, newWord ) ) {
                        found = true;
                    }
                }
            }
            if( !found && word.substring( word.length()-1 ).compareTo("r") == 0 ) {
                newWord = word.substring(0, word.length()-1);
                newHash = (int) hashFunction( newWord );
                if( wordCheck( newHash, newWord )) {
                    found = true;
                } else if( word.charAt(0) >= 'A' && word.charAt(0) <= 'Z' ) {
                    newWord = newWord.substring(0, 1).toLowerCase() + newWord.substring(1);
                    newHash = (int) hashFunction( newWord );
                    if( wordCheck( newHash, newWord ) ) {
                        found = true;
                    }
                }
            }
            if( !found && word.substring( word.length()-3 ).compareTo("ing") == 0 ) {
                newWord = word.substring(0, word.length()-3);
                newHash = (int) hashFunction( newWord );
                if( wordCheck( newHash, newWord )) {
                    found = true;
                } else if( word.charAt(0) >= 'A' && word.charAt(0) <= 'Z' ) {
                    newWord = newWord.substring(0, 1).toLowerCase() + newWord.substring(1);
                    newHash = (int) hashFunction( newWord );
                    if( wordCheck( newHash, newWord ) ) {
                        found = true;
                    }
                }
                if( !found ) {
                    newWord = newWord + "e";
                    newHash = (int) hashFunction( newWord );
                    if( wordCheck( newHash, newWord )) {
                        found = true;
                    } else if( word.charAt(0) >= 'A' && word.charAt(0) <= 'Z' ) {
                        newWord = newWord.substring(0, 1).toLowerCase() + newWord.substring(1);
                        newHash = (int) hashFunction( newWord );
                        if( wordCheck( newHash, newWord ) ) {
                            found = true;
                        }
                    }
                }
            }
            if( !found && word.substring( word.length()-2 ).compareTo("ly") == 0 ) {
                newWord = word.substring(0, word.length()-2);
                newHash = (int) hashFunction( newWord );
                if( wordCheck( newHash, newWord )) {
                    found = true;
                } else if( word.charAt(0) >= 'A' && word.charAt(0) <= 'Z' ) {
                    newWord = newWord.substring(0, 1).toLowerCase() + newWord.substring(1);
                    newHash = (int) hashFunction( newWord );
                    if( wordCheck( newHash, newWord ) ) {
                        found = true;
                    }
                }
            }
        } else {
            found = true;
        }
        return found;
    }
    
    public static double hashFunction( String key )
    {
        double hashKey = 7;
        int r = 7;
        int strSize = key.length();
        for( int i = 0; i < strSize; i++ ) {
            hashKey = hashKey * r + key.charAt(i);
            //System.out.println( hashKey );
        }
        hashKey = hashKey % tableSize;
        //hashKey *= g;
        //hashKey = hashKey - Math.floor(hashKey);
        //hashKey = Math.floor(tableSize * hashKey);
        //System.out.println( hashKey );
        
        return hashKey;
    }
	
	public static void main( String[] args ) throws FileNotFoundException
	{
	    Scanner input = new Scanner( System.in );
	    
	    System.out.println( "Enter the dictionary file: ");
	    String dictionaryName = input.nextLine();
	    File dictionaryFile = new File( dictionaryName );
	    Scanner dictionaryScanner = new Scanner( dictionaryFile );
	    
	    System.out.println( "Enter the file to be spell checked: " );
	    String inputName = input.nextLine();
	    File inputFile = new File( inputName );
	    Scanner fileScanner = new Scanner( inputFile );
	    
	    /**
	    System.out.println( "Enter the output file: " );
	    String outputName = input.nextLine();
	    File outputFile = new File( outputName );
	    PrintWriter output = new PrintWriter( outputFile );
	     */
	    
        tableSize = 30000;

        hashTable = new Node[tableSize];
        //double g = 1/((1 + Math.sqrt(5))/2);
        
        
        //int collisionCount = 0;
        int dictionaryCount = 0;
        int wordCount = 0;
        int misspellCount = 0;
        int maxList = 0;
        //int probeNum = 0;
		while( dictionaryScanner.hasNext() ) {
		    dictionaryCount++;
		    String key = dictionaryScanner.next();
		    Node current = new Node( key );
		    int index = (int) hashFunction( key );
		    if( hashTable[ index ] != null ) {
		        int listLen = 2;
		        //System.out.println( "Collision!" );
		        //collisionCount++;
		        Node p = hashTable[ index ];
		        while( p.next != null ) {
		            p = p.next;
		            listLen++;
		        }
		        maxList = Math.max( maxList, listLen );
		        p.next = current;
		    } else {
		        hashTable[ index ] = current;
		    }
		}
		
		while( fileScanner.hasNext() ) {
		    String word = fileScanner.next();
		    wordCount++;
		    if( !spellCheck( word ) ) {
		        System.out.println( word );
		        misspellCount++;
		    }
		}
		//System.out.println( "Collision Count: " + collisionCount );
		//System.out.println( "Word Count: " + wordCount );
		//System.out.println( "Max list length: " + maxList );
		
		System.out.println( "Words in dictionary: " + dictionaryCount );
		System.out.println( "Words in file: " + wordCount );
		System.out.println( "Misspelled words: " + misspellCount );
		System.out.println( "Probes: " + probeNum );
		System.out.println( "Average Probes: " + ( probeNum/wordCount ) );
		input.close();
		dictionaryScanner.close();
		fileScanner.close();
		/**
		output.close();
		*/
	}

}
