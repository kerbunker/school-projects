/**
  @file pattern.h
  @author Katelyn Bunker (kbunker)
  
  This is the header file containing the functions that need to be accessed by the parse
  or regular file to be able to create regular patterns to match.
*/
#ifndef PATTERN_H
#define PATTERN_H

#include <stdbool.h>

//////////////////////////////////////////////////////////////////////
// Superclass for Patterns

/** A short name to use for the Pattern interface. */
typedef struct PatternStruct Pattern;

/**
  Structure used as a superclass/interface for a regular expression
  pattern.  It includes a representation for a resizable 2D table
  where we can record regions (substrings) of an input string that are
  matched by the pattern.  There's a function pointer for an
  overridable method, locate(), that uses the table to mark all the
  places where the pattern matches the input string, and another
  overridable method for freeing resources for the pattern.
*/
struct PatternStruct {
  /** Length of the current input string, as recorded by the latest call
      to locate(). */
  int len;
  
  /** The match table, a (len + 1) X (len + 1) 2D array represented as
      an array of pointers to arrays.  The job of the locate() functon
      is to fill in this table so table[ begin ][ end ] is true if
      this pattern can match the [ begiin, end ) substring of the
      current input string.  */
  bool **table;

  /** Relallocate the match table.  Find all the [ begin, end )
      substrings of input string, str, that match the pattern.  For
      any match, set table[ begin ][ end ] to true in the match
      table.

      @param pat pointer to the pattern being matched (essentially, a this
                 pointer.
      @param str input string in which we're finding matches.
  */
  void (*locate)( Pattern *pat, char const *str );

  /** Free memory for this pattern, including any subpatterns it contains.
      @param pat pattern to free.
  */
  void (*destroy)( Pattern *pat );
};

/** Report elements of the match table for the given pattern.  This
    can be called after the pattern's locate() function, to see where
    it found a match.  It's like a non-overridable instance method for
    the Pattern object.

    @param pat pointer to the pattern for which we want to check
               reported matches.
    @param begin index of the first character in the substring
    @param end index one-past-the-end of the string
    @return true if this pattern matches the [ begin, end ) substring
            of the most recent input string
 */
bool matches( Pattern *pat, int begin, int end );

/**
  Make a pattern for a single, non-special character, like `a` or `5`.

  @param sym The symbol this pattern is supposed to match.
  @return A dynamically allocated representation for this new pattern.
*/
Pattern *makeSymbolPattern( char sym );

/**
  Make a pattern for the concatenation of patterns p1 and p2.  It
  should match anything that can be broken into two substrings, s1 and
  s2, where the p1 matches the first part (s1) and p2 matches the
  second part (s2).

  @param p1 Subpattern for matching the first part of the string.
  @param p2 Subpattern for matching the second part of the string.
  @return A dynamically allocated representation for this new pattern.
*/
Pattern *makeConcatenationPattern( Pattern *p1, Pattern *p2 );

/**
  Make a pattern for the alternation of patterns p1 and p2.
  It matches either the pattern in p1 or the pattern in p2.
  
  @param p1 the first pattern to try to match
  @param p2 the second pattern to try to match
  @return The new dynamically allocated pattern
*/
Pattern *makeAlternationPattern( Pattern *p1, Pattern *p2 );

/**
  Make a pattern for a string inside of square brackets '[]'. This should match
  any of the characters inside the brackets.
  
  @param a the pointer to the string of characters inside the brackets
  @param i the number of characters in the string
  @return the new pattern
*/
Pattern *makeCharacterPattern( char *a, int i );

/**
  Make a pattern for a character or set of characters that can be repeated
  and matched multiple times in the string.
  
  @param sym the specific repetition symbol to match
  @param p the pattern that can be repeated
  @return the new repetition pattern
*/
Pattern *makeRepetitionPattern( char sym, Pattern *p );

/**
  Make a pattern for a pattern inside of parentheses.
  
  @param p the pattern inside the parentheses
  @return the new pattern
*/
Pattern *makeParentheticalPattern( Pattern *p );

#endif
