/*	Nicholas Pappas
	nhpappas@ucsc.edu
	ID: 1558554
	Assignment: lab4
*/
//-----------------------------------------------------------------------------
// charType.c
// extracts alpha-numeric characters from each line of the input file
// and places them in the output file.
//
// Recall the program FileIO.c from lab3 used fscanf to parse words in
// a file and then process them.  However the function fscanf is not
// appropriate when you want to read an entire line from a file as a
// string.  In this program we use another IO function from stdio.h called 
// fgets() for this purpose.  Its prototype is:
//
//         char* fgets(char* s, int n, FILE* stream);
//
// fgets() reads up to n-1 characters from stream and places them in
// the character array ponted to by s.  Characters are read until either
// a newline or an EOF is read, or until the specified limit is reached.
// After the characters have been read, a null character '\0' is placed
// in the array immediately after the last character read.  A newline
// character in stream will be retained and placed in s.  If successful,
// fgets() returns the string s, and a NULL pointer is returned upon
// failure.  See fgets in section 3c of the unix man pages for more.
//
//-----------------------------------------------------------------------------

#include<stdio.h>
#include<stdlib.h>
#include<ctype.h>
#include<assert.h>
#include<string.h>

#define MAX_STRING_LENGTH 100

// function prototype 
void extract_chars(char* s, char* a, char* d, char* p, char* w);

// function main which takes command line arguments 
int main(int argc, char* argv[]){
   FILE* in;        // handle for input file                  
   FILE* out;       // handle for output file                 
   char* line;      // string holding input line              
   char* alphabetical; // string holding all alphabetical chars 
   char* digit; // string holding all digit chars 
   char* punctuation; // string holding all punctuational chars 
   char* whiteSpace; // string holding all white space chars 

   // check command line for correct number of arguments 
   if( argc != 3 ){
      printf("Usage: %s input-file output-file\n", argv[0]);
      exit(EXIT_FAILURE);
   }

   // open input file for reading 
   if( (in=fopen(argv[1], "r"))==NULL ){
      printf("Unable to read from file %s\n", argv[1]);
      exit(EXIT_FAILURE);
   }

   // open output file for writing 
   if( (out=fopen(argv[2], "w"))==NULL ){
      printf("Unable to write to file %s\n", argv[2]);
      exit(EXIT_FAILURE);
   }

   // allocate strings line and alphabetical on the heap 
   line = calloc(MAX_STRING_LENGTH+1, sizeof(char) );
   alphabetical = calloc(MAX_STRING_LENGTH+1, sizeof(char) );
   digit = calloc(MAX_STRING_LENGTH+1, sizeof(char) );
   punctuation = calloc(MAX_STRING_LENGTH+1, sizeof(char) );
   whiteSpace = calloc(MAX_STRING_LENGTH+1, sizeof(char) );
   assert( line!=NULL);
   assert( alphabetical!=NULL );
   assert( digit!=NULL );
   assert( punctuation!=NULL );
   assert( whiteSpace!=NULL );

   // read each line in input file, extract alpha-numeric characters
   int m=1, A=0, D=0, P=0, W=0;//malloc(sizeof(int));
   //m = 1;
   while( fgets(line, MAX_STRING_LENGTH, in) != NULL ){
      extract_chars(line, alphabetical, digit, punctuation, whiteSpace);
	  A=0; D=0; P=0; W=0;
	  while(alphabetical[A] != '\0'){ A++;}
	  while(digit[D] != '\0'){ D++;}
	  while(punctuation[P] != '\0'){ P++;}
	  while(whiteSpace[W] != '\0'){ W++;}
      fprintf(out, "line %d contains:\n", m);
	  fprintf(out, "%d alphabetic characters: %s\n", A, alphabetical );
	  fprintf(out, "%d numeric characters: %s\n", D, digit );
	  fprintf(out, "%d punctuation characters: %s\n", P, punctuation );
	  fprintf(out, "%d whitespace characters: %s\n", W, whiteSpace );
	  m++;
   }

   // free heap memory
   //free(m);
   free(line);
   free(alphabetical);
   free(digit);
   free(punctuation);
   free(whiteSpace);

   // close input and output files 
   fclose(in);
   fclose(out);

   return EXIT_SUCCESS;
}

// function definition 
void extract_chars(char* s, char* a, char* d, char* p, char* w){
   int i=0, j=0, k=0, l=0, n=0;
   while(s[i]!='\0' && i<MAX_STRING_LENGTH){
      if( isalpha( (int)s[i]) ){ a[j++] = s[i];}
	  else if( isdigit( (int)s[i]) ){ d[k++] = s[i];}
	  else if( ispunct( (int)s[i]) ){ p[l++] = s[i];}
	  else if( isspace( (int)s[i]) ){ w[n++] = s[i];}
	  else {}
      i++;
   }
   a[j] = '\0';
   d[k] = '\0';
   p[l] = '\0';
   w[n] = '\0';
}