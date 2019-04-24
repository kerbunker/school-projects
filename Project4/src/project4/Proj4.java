package project4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * This program is designed as a spell check program. It asks for a dictionary file
 * and then a file to be spell checked and then an output file. The dictionary file is 
 * processed and the words are put into a hash table which allows for easy lookup when
 * a word from the file is compared to the dictionary words. After the dictionary file
 * is processed the spell check file is processed and each word is checked against
 * the dictionary, and if a match is not found, the word is tested for common suffixes
 * to the word, like -es, -ing, -er/-r, etc. and checked against the dictionary again.
 * If a matching word cannot be found, the suspect word is output to the file as a 
 * misspelled word. After the entire file has been checked, the number of dictionary
 * words are output, as are the number of words in the checked file, and the number of
 * misspelled words. Then the number of times the dictionary was probed and the average
 * number of probes per word and the average number of probes per lookup are printed.
 * @author Katelyn Bunker (kbunker)
 *
 */
public class Proj4 {
    /** This holds the number of probes to the dictionary */
    public static int probeNum = 0;
    /** This holds the number of times a lookup was performed */
    public static int lookupNum = 0;
    /** This is the hash table that holds the dictionary words */
    public static Node hashTable[];
    /** This is the size of the table */
    public static int tableSize;
	
    /**
     * This inner class is for the nodes that the hashTable points to
     * that hold the keys for the dictionary and point to the next Node in the
     * list if a collision has occured.
     *
     */
    static class Node {
        
        /** The key for the dictionary word */
        String key;
        /** Points to the next Node in the list, originally null */
        Node next;
        
        /**
         * The constructor for the Node object. It takes in the dictionary
         * key and stores it, and initializes the next field to null
         * @param key the dictionary key for the word
         */
        Node( String key )
        {
            this.key = key;
            next = null;
        }
    }
    
    /**
     * Looks for the given word in at the index of the hash table given by the
     * word hash value
     * @param wordHash the hash value for the given word
     * @param word the word to compare to the words in the dictionary
     * @return true if a match was found, false otherwise
     */
    public static boolean wordCheck( int wordHash, String word )
    {
        //gets the node stored at the index given by the wordHash
        Node tableNode = hashTable[ wordHash ];
        //initializes found to false
        boolean found = false;
        //if there is no node at the given index, return false
        if( tableNode == null ) {
            return found;
        //otherwise, compare the given word to the key stored at the index
        } else if( tableNode.key.compareTo(word) == 0 ) {
            probeNum+= 1;
            found = true;
            return found;
        //if the word was not a match, check if there is a Node pointed to by the next field
        } else {
            probeNum++;
            //keep checking until there are no more stored words at the index
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
    
    /**
     * Calls the word check function on the word, and if the match was not found, checks
     * for any common suffixes that might be hiding the correct word
     * @param word the word to spell check
     * @return true if the word was found, false otherwise
     */
    public static boolean spellCheck( String word )
    {
        //gets the hash of the word to check
        int wordHash = (int) hashFunction( word );
        boolean found = false;
        //gets the length of the word
        int strLength = word.length();
        //creates the variable to store the length of a manipulated word
        int newLength = 0;
        //increases the number of lookup operations
        lookupNum++;
        //checks the word against the dictionary
        if( !wordCheck( wordHash, word ) ) {
            //if not found, initializes a newWord field for manipulating the suffixes of the word
            String newWord = word;
            int newHash;
            //checks and corrects if a word has a capital letter
            if( word.charAt(0) >= 'A' && word.charAt(0) <= 'Z' ) {
                newWord = newWord.substring(0, 1).toLowerCase() + newWord.substring(1);
                newLength = newWord.length();
                newHash = (int) hashFunction( newWord );
                lookupNum++;
                if( wordCheck( newHash, newWord ) ) {
                    found = true;
                }
            }
            //checks for the "'s" suffix and removes it and checks the dictionary,
            //then checks for a capital letter again
            if( !found && strLength > 2 && word.substring( word.length()-2 ).compareTo("'s") == 0 ) {
                newWord = word.substring(0, word.length()-2);
                newLength = newWord.length();
                newHash = (int) hashFunction( newWord );
                lookupNum++;
                if( wordCheck( newHash, newWord )) {
                    found = true;
                } else if( word.charAt(0) >= 'A' && word.charAt(0) <= 'Z' ) {
                    newWord = newWord.substring(0, 1).toLowerCase() + newWord.substring(1);
                    newLength = newWord.length();
                    newHash = (int) hashFunction( newWord );
                    lookupNum++;
                    if( wordCheck( newHash, newWord ) ) {
                        found = true;
                    }
                }
            }
            //checks the already manipulated word for an "s" suffix
            if( !found && newLength > 1 && newWord.substring( newWord.length()-1 ).compareTo("s") == 0 ) {
                newWord = newWord.substring(0, newWord.length()-1);
                newLength = newWord.length();
                newHash = (int) hashFunction( newWord );
                lookupNum++;
                if( wordCheck( newHash, newWord )) {
                    found = true;
                } else if( newWord.charAt(0) >= 'A' && newWord.charAt(0) <= 'Z' ) {
                    newWord = newWord.substring(0, 1).toLowerCase() + newWord.substring(1);
                    newHash = (int) hashFunction( newWord );
                    lookupNum++;
                    if( wordCheck( newHash, newWord ) ) {
                        found = true;
                    }
                }
            }
            //checks the original word for just an "s" suffix
            if( !found && strLength > 1 && word.substring( word.length()-1 ).compareTo("s") == 0 ) {
                newWord = word.substring(0, word.length()-1);
                newLength = newWord.length();
                newHash = (int) hashFunction( newWord );
                lookupNum++;
                if( wordCheck( newHash, newWord )) {
                    found = true;
                } else if( word.charAt(0) >= 'A' && word.charAt(0) <= 'Z' ) {
                    newWord = newWord.substring(0, 1).toLowerCase() + newWord.substring(1);
                    newHash = (int) hashFunction( newWord );
                    lookupNum++;
                    if( wordCheck( newHash, newWord ) ) {
                        found = true;
                    }
                }
            }
            //checks an already manipulated word for an "es" suffix
            if( !found && newLength > 2 && newWord.substring( newWord.length()-2 ).compareTo("es") == 0 ) {
                newWord = newWord.substring(0, newWord.length()-2);
                newLength = newWord.length();
                newHash = (int) hashFunction( newWord );
                lookupNum++;
                if( wordCheck( newHash, newWord )) {
                    found = true;
                } else if( newWord.charAt(0) >= 'A' && newWord.charAt(0) <= 'Z' ) {
                    newWord = newWord.substring(0, 1).toLowerCase() + newWord.substring(1);
                    newHash = (int) hashFunction( newWord );
                    lookupNum++;
                    if( wordCheck( newHash, newWord ) ) {
                        found = true;
                    }
                }
            }
            //checks the original word for an "es" suffix
            if( !found && strLength > 2 && word.substring( word.length()-2 ).compareTo("es") == 0 ) {
                newWord = word.substring(0, word.length()-2);
                newLength = newWord.length();
                newHash = (int) hashFunction( newWord );
                lookupNum++;
                if( wordCheck( newHash, newWord )) {
                    found = true;
                } else if( word.charAt(0) >= 'A' && word.charAt(0) <= 'Z' ) {
                    newWord = newWord.substring(0, 1).toLowerCase() + newWord.substring(1);
                    newHash = (int) hashFunction( newWord );
                    lookupNum++;
                    if( wordCheck( newHash, newWord ) ) {
                        found = true;
                    }
                }
            }
            //checks a manipulated word for an "ed" suffix
            if( !found && newLength > 2 && newWord.substring( newWord.length()-2 ).compareTo("ed") == 0 ) {
                newWord = newWord.substring(0, newWord.length()-2);
                newLength = newWord.length();
                newHash = (int) hashFunction( newWord );
                lookupNum++;
                if( wordCheck( newHash, newWord )) {
                    found = true;
                } else if( newWord.charAt(0) >= 'A' && newWord.charAt(0) <= 'Z' ) {
                    newWord = newWord.substring(0, 1).toLowerCase() + newWord.substring(1);
                    newHash = (int) hashFunction( newWord );
                    lookupNum++;
                    if( wordCheck( newHash, newWord ) ) {
                        found = true;
                    }
                }
            }
            //checks the original word for an "ed" suffix
            if( !found && strLength > 2 && word.substring( word.length()-2 ).compareTo("ed") == 0 ) {
                newWord = word.substring(0, word.length()-2);
                newLength = newWord.length();
                newHash = (int) hashFunction( newWord );
                lookupNum++;
                if( wordCheck( newHash, newWord )) {
                    found = true;
                } else if( word.charAt(0) >= 'A' && word.charAt(0) <= 'Z' ) {
                    newWord = newWord.substring(0, 1).toLowerCase() + newWord.substring(1);
                    newHash = (int) hashFunction( newWord );
                    lookupNum++;
                    if( wordCheck( newHash, newWord ) ) {
                        found = true;
                    }
                }
            }
            //checks the manipulated word for just a "d" suffix
            if( !found && newLength > 1 && newWord.substring( newWord.length()-1 ).compareTo("d") == 0 ) {
                newWord = newWord.substring(0, newWord.length()-1);
                newLength = newWord.length();
                newHash = (int) hashFunction( newWord );
                lookupNum++;
                if( wordCheck( newHash, newWord )) {
                    found = true;
                } else if( newWord.charAt(0) >= 'A' && newWord.charAt(0) <= 'Z' ) {
                    newWord = newWord.substring(0, 1).toLowerCase() + newWord.substring(1);
                    newHash = (int) hashFunction( newWord );
                    lookupNum++;
                    if( wordCheck( newHash, newWord ) ) {
                        found = true;
                    }
                }
            }
            //checks the original word for just a "d" suffix
            if( !found && strLength > 1 && word.substring( word.length()-1 ).compareTo("d") == 0 ) {
                newWord = word.substring(0, word.length()-1);
                newLength = newWord.length();
                newHash = (int) hashFunction( newWord );
                lookupNum++;
                if( wordCheck( newHash, newWord )) {
                    found = true;
                } else if( word.charAt(0) >= 'A' && word.charAt(0) <= 'Z' ) {
                    newWord = newWord.substring(0, 1).toLowerCase() + newWord.substring(1);
                    newHash = (int) hashFunction( newWord );
                    lookupNum++;
                    if( wordCheck( newHash, newWord ) ) {
                        found = true;
                    }
                }
            }
            //checks the manipulated word for an "er" suffix and if not found
            //tries again with just removing the r
            if( !found && newLength > 2 && newWord.substring( newWord.length()-2 ).compareTo("er") == 0 ) {
                newWord = newWord.substring(0, newWord.length()-2);
                newLength = newWord.length();
                newHash = (int) hashFunction( newWord );
                lookupNum++;
                if( wordCheck( newHash, newWord )) {
                    found = true;
                } else if( newWord.charAt(0) >= 'A' && newWord.charAt(0) <= 'Z' ) {
                    newWord = newWord.substring(0, 1).toLowerCase() + newWord.substring(1);
                    newHash = (int) hashFunction( newWord );
                    lookupNum++;
                    if( wordCheck( newHash, newWord ) ) {
                        found = true;
                    }
                }
                if( !found ) {
                    newWord = newWord + "e";
                    newHash = (int) hashFunction( newWord );
                    lookupNum++;
                    if( wordCheck( newHash, newWord )) {
                        found = true;
                    } else if( newWord.charAt(0) >= 'A' && newWord.charAt(0) <= 'Z' ) {
                        newWord = newWord.substring(0, 1).toLowerCase() + newWord.substring(1);
                        newHash = (int) hashFunction( newWord );
                        lookupNum++;
                        if( wordCheck( newHash, newWord ) ) {
                            found = true;
                        }
                    }
                }
            }
            //checks the original word for an "er" suffix and if not found
            //tries again with just removing the r
            if( !found && strLength > 2 && word.substring( word.length()-2 ).compareTo("er") == 0 ) {
                newWord = word.substring(0, word.length()-2);
                newHash = (int) hashFunction( newWord );
                lookupNum++;
                if( wordCheck( newHash, newWord )) {
                    found = true;
                } else if( word.charAt(0) >= 'A' && word.charAt(0) <= 'Z' ) {
                    newWord = newWord.substring(0, 1).toLowerCase() + newWord.substring(1);
                    newHash = (int) hashFunction( newWord );
                    lookupNum++;
                    if( wordCheck( newHash, newWord ) ) {
                        found = true;
                    }
                }
                if( !found ) {
                    newWord = newWord + "e";
                    newHash = (int) hashFunction( newWord );
                    lookupNum++;
                    if( wordCheck( newHash, newWord )) {
                        found = true;
                    } else if( word.charAt(0) >= 'A' && word.charAt(0) <= 'Z' ) {
                        newWord = newWord.substring(0, 1).toLowerCase() + newWord.substring(1);
                        newHash = (int) hashFunction( newWord );
                        lookupNum++;
                        if( wordCheck( newHash, newWord ) ) {
                            found = true;
                        }
                    }
                }
            }
            //checks the manipulated word for an "r" suffix
            if( !found && newLength > 1 && newWord.substring( newWord.length()-1 ).compareTo("r") == 0 ) {
                newWord = newWord.substring(0, newWord.length()-1);
                newLength = newWord.length();
                newHash = (int) hashFunction( newWord );
                lookupNum++;
                if( wordCheck( newHash, newWord )) {
                    found = true;
                } else if( newWord.charAt(0) >= 'A' && newWord.charAt(0) <= 'Z' ) {
                    newWord = newWord.substring(0, 1).toLowerCase() + newWord.substring(1);
                    newHash = (int) hashFunction( newWord );
                    lookupNum++;
                    if( wordCheck( newHash, newWord ) ) {
                        found = true;
                    }
                }
            }
            //checks the original word for an "r" suffix
            if( !found && strLength > 1 && word.substring( word.length()-1 ).compareTo("r") == 0 ) {
                newWord = word.substring(0, word.length()-1);
                newHash = (int) hashFunction( newWord );
                lookupNum++;
                if( wordCheck( newHash, newWord )) {
                    found = true;
                } else if( word.charAt(0) >= 'A' && word.charAt(0) <= 'Z' ) {
                    newWord = newWord.substring(0, 1).toLowerCase() + newWord.substring(1);
                    newHash = (int) hashFunction( newWord );
                    lookupNum++;
                    if( wordCheck( newHash, newWord ) ) {
                        found = true;
                    }
                }
            }
            //checks the manipulated word for an "ing" suffix then adds a e if not found
            if( !found && newLength > 3 && newWord.substring( newWord.length()-3 ).compareTo("ing") == 0 ) {
                newWord = newWord.substring(0, newWord.length()-3);
                newLength = newWord.length();
                newHash = (int) hashFunction( newWord );
                lookupNum++;
                if( wordCheck( newHash, newWord )) {
                    found = true;
                } else if( newWord.charAt(0) >= 'A' && newWord.charAt(0) <= 'Z' ) {
                    newWord = newWord.substring(0, 1).toLowerCase() + newWord.substring(1);
                    newHash = (int) hashFunction( newWord );
                    lookupNum++;
                    if( wordCheck( newHash, newWord ) ) {
                        found = true;
                    }
                }
                if( !found ) {
                    newWord = newWord + "e";
                    newHash = (int) hashFunction( newWord );
                    lookupNum++;
                    if( wordCheck( newHash, newWord )) {
                        found = true;
                    } else if( word.charAt(0) >= 'A' && word.charAt(0) <= 'Z' ) {
                        newWord = newWord.substring(0, 1).toLowerCase() + newWord.substring(1);
                        newHash = (int) hashFunction( newWord );
                        lookupNum++;
                        if( wordCheck( newHash, newWord ) ) {
                            found = true;
                        }
                    }
                }
            }
            //checks the original word for an "ing" suffix then adds a e if not found
            if( !found && strLength > 3 && word.substring( word.length()-3 ).compareTo("ing") == 0 ) {
                newWord = word.substring(0, word.length()-3);
                newLength = newWord.length();
                newHash = (int) hashFunction( newWord );
                lookupNum++;
                if( wordCheck( newHash, newWord )) {
                    found = true;
                } else if( word.charAt(0) >= 'A' && word.charAt(0) <= 'Z' ) {
                    newWord = newWord.substring(0, 1).toLowerCase() + newWord.substring(1);
                    newHash = (int) hashFunction( newWord );
                    lookupNum++;
                    if( wordCheck( newHash, newWord ) ) {
                        found = true;
                    }
                }
                if( !found ) {
                    newWord = newWord + "e";
                    newLength = newWord.length();
                    newHash = (int) hashFunction( newWord );
                    lookupNum++;
                    if( wordCheck( newHash, newWord )) {
                        found = true;
                    } else if( word.charAt(0) >= 'A' && word.charAt(0) <= 'Z' ) {
                        newWord = newWord.substring(0, 1).toLowerCase() + newWord.substring(1);
                        newHash = (int) hashFunction( newWord );
                        lookupNum++;
                        if( wordCheck( newHash, newWord ) ) {
                            found = true;
                        }
                    }
                }
            }
            //checks the manipulated word for an "ly" suffix
            if( !found && newLength > 2 && newWord.substring( newWord.length()-2 ).compareTo("ly") == 0 ) {
                newWord = newWord.substring(0, newWord.length()-2);
                newLength = newWord.length();
                newHash = (int) hashFunction( newWord );
                lookupNum++;
                if( wordCheck( newHash, newWord )) {
                    found = true;
                } else if( newWord.charAt(0) >= 'A' && newWord.charAt(0) <= 'Z' ) {
                    newWord = newWord.substring(0, 1).toLowerCase() + newWord.substring(1);
                    newHash = (int) hashFunction( newWord );
                    lookupNum++;
                    if( wordCheck( newHash, newWord ) ) {
                        found = true;
                    }
                }
            }
            //checks the original word for an "ly" suffix
            if( !found && strLength > 2 && word.substring( word.length()-2 ).compareTo("ly") == 0 ) {
                newWord = word.substring(0, word.length()-2);
                newLength = newWord.length();
                newHash = (int) hashFunction( newWord );
                lookupNum++;
                if( wordCheck( newHash, newWord )) {
                    found = true;
                } else if( word.charAt(0) >= 'A' && word.charAt(0) <= 'Z' ) {
                    newWord = newWord.substring(0, 1).toLowerCase() + newWord.substring(1);
                    newHash = (int) hashFunction( newWord );
                    lookupNum++;
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
    
    /**
     * calculated the hash key for the given key using a hash function which muliplies
     * each number value of each character in the word by 7, and adds them all together
     * @param key the key to use to calculate the hashkey
     * @return the double value of the hash key
     */
    public static double hashFunction( String key )
    {
        double hashKey = 1;
        double r = 7;
        int strSize = key.length();
        for( int i = 0; i < strSize; i++ ) {
            //r = Math.pow(r, i);
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
	
    /**
     * The main method of the program. This asks for the files to use and creates the 
     * scanners and print writer and then processes the dictionary and text files
     * @param args the command line arguments
     * @throws FileNotFoundException throws the exception if a file to be read if not found
     */
	public static void main( String[] args ) throws FileNotFoundException
	{
	    //creates the input scanner to read from the console
	    Scanner input = new Scanner( System.in );
	    
	    //gets the dictionary file and creates the scanner to read from the file
	    System.out.println( "Enter the dictionary file: ");
	    String dictionaryName = input.nextLine();
	    File dictionaryFile = new File( dictionaryName );
	    Scanner dictionaryScanner = new Scanner( dictionaryFile );
	    
	    //gets the text file to spell check and creates the scanner to process it 
	    System.out.println( "Enter the file to be spell checked: " );
	    String inputName = input.nextLine();
	    File inputFile = new File( inputName );
	    Scanner fileScanner = new Scanner( inputFile );
	    fileScanner.useDelimiter("[^a-zA-Z']");
	    
	    //gets the output file and creates the printwriter to write to it
	    System.out.println( "Enter the output file: " );
	    String outputName = input.nextLine();
	    File outputFile = new File( outputName );
	    PrintWriter output = new PrintWriter( outputFile );
	    
	    //sets the size of the hash table
        tableSize = 30000;

        // creates the hash table to store the dictionary
        hashTable = new Node[tableSize];
        //double g = 1/((1 + Math.sqrt(5))/2);
        
        
        //int collisionCount = 0;
        int dictionaryCount = 0;//number of words in the dictionary
        int wordCount = 0;//number of words from the file
        int misspellCount = 0;//number of misspelled words
        //int maxList = 0;//longest list at an index of the table
        //int probeNum = 0;
        //scans through the dictionary file until there are no remaining words
		while( dictionaryScanner.hasNext() ) {
		    dictionaryCount++;//adds to the dictionary word count
		    String key = dictionaryScanner.next();//gets the next word
		    Node current = new Node( key );//creates the node
		    int index = (int) hashFunction( key );//finds the index of the table to store the node
		    //if the index is not empty, the nodes are traversed until the end of the list is found
		    if( hashTable[ index ] != null ) {
		        //int listLen = 2;
		        //System.out.println( "Collision!" );
		        //collisionCount++;
		        Node p = hashTable[ index ];
		        while( p.next != null ) {
		            p = p.next;
		            //listLen++;
		        }
		        //maxList = Math.max( maxList, listLen );
		        p.next = current;
		    } else {
		        hashTable[ index ] = current;
		    }
		}
		
		//goes through the text file and calls the spell check method on each word
		while( fileScanner.hasNext() ) {
		    String word = fileScanner.next();
		    if( word.length() != 0 ) {
		        wordCount++;//increases the word count
		        if( !spellCheck( word ) ) {
		            output.println( word );//prints any words not found in the dictionary
		            misspellCount++;//adds to the misspelled words count
		        }
		    }
		}
		//System.out.println( "Collision Count: " + collisionCount );
		//System.out.println( "Word Count: " + wordCount );
		//System.out.println( "Max list length: " + maxList );
		
		//prints out the stats for the file
		output.println( "Words in dictionary: " + dictionaryCount );
		output.println( "Words in file: " + wordCount );
		output.println( "Misspelled words: " + misspellCount );
		output.println( "Probes: " + probeNum );
		output.println( "Average Probes: " + ( probeNum/wordCount ) );
		output.println( "Average Probes per lookup " + ( probeNum/lookupNum ) );
		
		//closes the scanners and printwriter
		input.close();
		dictionaryScanner.close();
		fileScanner.close();
		output.close();
	}

}
