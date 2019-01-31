/**
  @file parse.h
  @author Katelyn Bunker (kbunker)
  
  The header file for the parse.c file. Includes the declaration of the function
  needed by the regular program to parse patterns
*/
#ifndef PARSE_H
#define PARSE_H

#include "pattern.h"

/** 
  Parse the given string into Pattern object.

  @param str string cntaining a pattern.
  @return pointer to a representation of the pattern.
*/
Pattern *parsePattern( char const *str );

#endif
