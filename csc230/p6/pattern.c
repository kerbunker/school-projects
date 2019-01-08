/**
  @file pattern.c
  @author Katelyn Bunker (kbunker)
  
  This file contains the constructors as well as the functions to locate and destroy
  the different patterns for the different types of regular patterns that can be used.
*/
#include "pattern.h"
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

/** Free the table inside a pattern, if there is one.

    @param this The pattern we're supposed to operate on.
*/
static void freeTable( Pattern *this )
{
  if ( this->table ) {
    for ( int r = 0; r <= this->len; r++ )
      free( this->table[ r ] );
    free( this->table );
  }
}

/** Make and initialize a new match table inside the given pattern,
    large enough to store matches for the given string.

    @param this The pattern we're supposed to operate on.
    @param str The string we're going to store mageches for */
static void initTable( Pattern *this, char const *str )
{
  // If we already had a table, free it.
  freeTable( this );

  // Make a table big enough for str.
  this->len = strlen( str );
  this->table = (bool **) malloc( ( this->len + 1 ) * sizeof( bool * ) );
  for ( int r = 0; r <= this->len; r++ )
    this->table[ r ] = (bool *) calloc( ( this->len + 1 ), sizeof( bool ) );
}

// Documented in the header.
bool matches( Pattern *pat, int begin, int end )
{
  return pat->table[ begin ][ end ];
}

/**
   A simple function that can be used to free the memory for any
   pattern that doesn't allocate any additional memory other than the
   struct used to represent it (e.g., if it doesn't contain any
   sub-patterns).  It's like a default implementation of the destroy
   method.

   @param pat The pattern to free memory for.
*/
static void destroySimplePattern( Pattern *pat )
{
  freeTable( pat );
  // If we don't need fields that are specific to the sub-type, we can just
  // free the block of memory where the object is stored.
  free( pat );
}

/**
   Type of pattern used to represent a single, ordinary symbol,
   like 'a' or '5'.
*/
typedef struct {
  // Fields from our superclass.
  int len;
  bool **table;
  void (*locate)( Pattern *pat, char const *str );
  void (*destroy)( Pattern *pat );
  
  /** Symbol this pattern is supposed to match. */
  char sym;
} SymbolPattern;

/**
  The struct for the pattern of characters inside a set of
  square brackets
*/
typedef struct {
  int len;
  bool **table;
  void (*locate)( Pattern *pat, char const *str );
  void (*destroy)( Pattern *pat );
  
  int num;
  char *sym;
} CharacterPattern;

/**
  The struct for a pattern that is repeated
*/
typedef struct {
  int len;
  bool **table;
  void ( *locate )( Pattern *pat, char const *str );
  void ( *destroy )( Pattern *pat );
  
  //The pattern to be repeated
  Pattern *p;
  //The repetition symbol ( '+', '*', '?' )
  char sym;
} RepetitionPattern;

/**
  The struct for a pattern inside a set of parentheses
*/
typedef struct {
  int len;
  bool **table;
  void ( *locate )( Pattern *pat, char const *str );
  void ( *destroy )( Pattern *pat );
  
  //the pattern inside the parentheses
  Pattern *p;
} ParentheticalPattern;

/**
  The function to destroy and free the memory in a character pattern struct
  
  @param pat the character pattern the destroy
*/
static void destroyCharacterPattern( Pattern *pat )
{
  freeTable( pat );
  
  CharacterPattern *this = ( CharacterPattern * ) pat;
  
  free( this->sym );
  free( this );
}

/**
  The function to destroy the repetition pattern struct
  
  @param pat the pattern to be destroyed
*/
static void destroyRepetitionPattern( Pattern *pat )
{
  RepetitionPattern *this = ( RepetitionPattern * ) pat;
  
  freeTable( pat );
  
  this->p->destroy( this->p );
  
  free( this );
}

/**
  The function to destroy the parenthetical pattern
  
  @param pat the pattern to destroy
*/
static void destroyParentheticalPattern( Pattern *pat )
{
  ParentheticalPattern *this = ( ParentheticalPattern * ) pat;
  
  freeTable( pat );
  
  this->p->destroy( this->p );
  
  free( this );
}

// Overridden locate() method for a SymbolPattern
static void locateSymbolPattern( Pattern *pat, char const *str )
{
  // Cast down to the struct type pat really points to.
  SymbolPattern *this = (SymbolPattern *) pat;

  // Make a fresh table for this input string.
  initTable( pat, str );
  
  if ( this->sym == '^' )
    this->table[ 0 ][ 0 ] = true;
    
  if ( this->sym == '$' )
    this->table[ strlen( str ) ][ strlen( str ) ] = true;

  // Find all occurreces of the symbol we're supposed to match, and
  // mark them in the match table as matching, 1-character substrings.
  for ( int begin = 0; str[ begin ]; begin++ )
    if ( ( str[ begin ] == this->sym ) || this->sym == '.' )
      this->table[ begin ][ begin + 1 ] = true;

}

/**
  The function to locate a character pattern
  
  @param pat the character pattern to match
  @param str the string to match the pattern against
*/
static void locateCharacterPattern( Pattern *pat, char const *str )
{
  CharacterPattern *this = ( CharacterPattern * ) pat;
  
  initTable( pat, str );
  
  int num = this->num;
  for ( int i = 0; i < num; i++ ) {
    for ( int begin = 0; str[ begin ]; begin++ )
      if ( str[ begin ] == this->sym[ i ] )
        this->table[ begin ][ begin + 1 ] = true;
  }
}

/**
  Locates the matches of the parenthetical pattern to the string
  
  @param pat the pattern to match
  @param str the string to find the matches of the pattern in
*/
static void locateParentheticalPattern( Pattern *pat, char const *str )
{
  ParentheticalPattern *this = ( ParentheticalPattern * ) pat;
  
  initTable( pat, str );
  
  this->p->locate( this->p, str );
  
  for ( int begin = 0; begin <= this->len; begin++ )
    for ( int end = begin; end <= this->len; end++ ) {
      if ( matches( this->p, begin, end ) )
        this->table[ begin ][ end ] = true;
    }
}

/**
  The function to locate the patterns that can be repeated inside a string
  
  @param pat the pattern to match to the string
  @param str the string to match the pattern
*/
static void locateRepetitionPattern( Pattern *pat, char const *str )
{
  RepetitionPattern *this = ( RepetitionPattern * ) pat;

  initTable( pat, str );

  this->p->locate( this->p, str );

  if ( this->sym == '*' || this->sym == '?' )
    for ( int i = 0; i <= this->len; i++ )
      this->table[ i ][ i ] = true;

  for ( int begin = 0; begin <= this->len; begin++ )
    for ( int end = begin; end <= this->len; end++ ) {
      if ( matches( this->p, begin, end ) )
        this->table[ begin ][ end ] = true;

      if ( this->sym == '*' || this->sym == '+' ) {
        for ( int k = begin; k <= end; k++ )
          if ( ( this->table[ begin ][ k ] ) &&
            matches( this->p, k, end ) )
            this->table[ begin ][ end ] = true;
      }
    }
}

// Documented in the header.
Pattern *makeSymbolPattern( char sym )
{
  // Make an instance of SymbolPattern, and fill in its state.
  SymbolPattern *this = (SymbolPattern *) malloc( sizeof( SymbolPattern ) );
  this->table = NULL;

  this->locate = locateSymbolPattern;
  this->destroy = destroySimplePattern;
  this->sym = sym;

  return (Pattern *) this;
}

// Documented in the header
Pattern *makeRepetitionPattern( char sym, Pattern *p )
{
  RepetitionPattern *this = ( RepetitionPattern *)malloc( sizeof( RepetitionPattern ) );
  this->table = NULL;

  this->p = p;
  this->locate = locateRepetitionPattern;
  this->destroy = destroyRepetitionPattern;
  this->sym = sym;

  return (Pattern *) this;
}

// Documented in the header
Pattern *makeCharacterPattern( char *a, int len )
{
  CharacterPattern *this = (CharacterPattern *)malloc( sizeof( CharacterPattern ) );
  this->table = NULL;

  this->locate = locateCharacterPattern;
  this->destroy = destroyCharacterPattern;

  this->num = len;
  this->sym = (char *)malloc( len );
  for ( int i = 0; i < len; i++ )
    this->sym[ i ] = a[ i ];

  return (Pattern *) this;
}

// Documented in the header
Pattern *makeParentheticalPattern( Pattern *p )
{
  ParentheticalPattern *this = ( ParentheticalPattern * )malloc( sizeof( ParentheticalPattern ) );
  this->table = NULL;

  this->locate = locateParentheticalPattern;
  this->destroy = destroyParentheticalPattern;

  this->p = p;

  return ( Pattern * ) this;
}


/**
   Representation for a type of pattern that contains two sub-patterns
   (e.g., concatenation).  This representation could be used by more
   than one type of pattern, as long as it uses a pointer to a
   different locate() function.
*/
typedef struct {
  // Fields from our superclass.
  int len;
  bool **table;
  void (*locate)( Pattern *pat, char const *str );
  void (*destroy)( Pattern *pat );

  // Pointers to the two sub-patterns.
  Pattern *p1, *p2;
} BinaryPattern;

// destroy function used for BinaryPattern
static void destroyBinaryPattern( Pattern *pat )
{
  // Cast down to the struct type pat really points to.
  BinaryPattern *this = (BinaryPattern *) pat;

  // Free our table.
  freeTable( pat );
  // Free our two sub-patterns.
  this->p1->destroy( this->p1 );
  this->p2->destroy( this->p2 );
  // Free the struct representing this object.
  free( this );
}

// locate function for a BinaryPattern used to handle concatenation.
static void locateConcatenationPattern( Pattern *pat, const char *str )
{
  // Cast down to the struct type pat really points to.
  BinaryPattern *this = (BinaryPattern *) pat;

  initTable( pat, str );

  //  Let our two sub-patterns figure out everywhere they match.
  this->p1->locate( this->p1, str );
  this->p2->locate( this->p2, str );

  // Then, based on their matches, look for all places where their
  // concatenaton matches.  Check all substrings of the input string.
  for ( int begin = 0; begin <= this->len; begin++ )
    for ( int end = begin; end <= this->len; end++ ) {

      // For the [ begin, end ) range, check all places where it could
      // be split into two substrings, the first matching p1 and the second
      // matching p2.
      for ( int k = begin; k <= end; k++ )
        if ( matches( this->p1, begin, k ) &&
             matches( this->p2, k, end ) )
          this->table[ begin ][ end ] = true;
    }
}

/**
  Locates the places where the alternation pattern is found in the string
  
  @param pat the pattern to match
  @param str the string to find the matches of the pattern in
*/
static void locateAlternationPattern( Pattern *pat, const char *str )
{
  BinaryPattern *this = ( BinaryPattern *) pat;
  
  initTable( pat, str );
  
  this->p1->locate( this->p1, str );
  this->p2->locate( this->p2, str );

  for ( int begin = 0; begin <= this->len; begin++ )
    for ( int end = begin; end <= this->len; end++ ) {
      if ( matches( this->p1, begin, end ) ) {
        this->table[ begin ][ end ] = true;
      } else if ( matches( this->p2, begin, end ) ) {
        this->table[ begin ][ end ] = true;
      }
    }
}

// Documented in header.
Pattern *makeConcatenationPattern( Pattern *p1, Pattern *p2 )
{
  // Make an instance of Binary pattern and fill in its fields.
  BinaryPattern *this = (BinaryPattern *) malloc( sizeof( BinaryPattern ) );
  this->table = NULL;
  this->p1 = p1;
  this->p2 = p2;
  
  this->locate = locateConcatenationPattern;
  this->destroy = destroyBinaryPattern;
  
  return (Pattern *) this;
}

//Documented in header
Pattern *makeAlternationPattern( Pattern *p1, Pattern *p2 )
{
  BinaryPattern *this = ( BinaryPattern *) malloc( sizeof( BinaryPattern ) );
  this->table = NULL;
  this->p1 = p1;
  this->p2 = p2;
  
  this->locate = locateAlternationPattern;
  this->destroy = destroyBinaryPattern;
  
  return ( Pattern * ) this;
}
