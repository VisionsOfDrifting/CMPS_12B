/*	Nicholas Pappas
	nhpappas@ucsc.edu
	ID: 1558554
	Assignment: lab5
*/
//-----------------------------------------------------------------------------
//   Dictionary.c
//   Implementation file for Dictionary ADT
//-----------------------------------------------------------------------------

#include<stdio.h>
#include<ctype.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#include"Dictionary.h"


// private types --------------------------------------------------------------

// NodeObj
typedef struct NodeObj{
   char* key;
   char* value;
   struct NodeObj* next;
} NodeObj;

// Node
typedef NodeObj* Node;

// newNode()
// constructor of the Node type
Node newNode(char* x, char* y) {
   Node N = malloc(sizeof(NodeObj));
   assert(N!=NULL);
   N->key = x;
   N->value = y;
   N->next = NULL;
   return(N);
}

// freeNode()
// destructor for the Node type
void freeNode(Node* pN){
   if( pN!=NULL && *pN!=NULL ){
      free(*pN);
      *pN = NULL;
   }
}

// DictionaryObj
// Exported reference type
typedef struct DictionaryObj{
   Node head;
   Node tail;
   int numItems;
} DictionaryObj;

typedef struct DictionaryObj* Dictionary;

// public functions -----------------------------------------------------------

// newDictionary()
// constructor for the Dictionary type
Dictionary newDictionary(void){
   Dictionary D = malloc(sizeof(DictionaryObj));
   assert(D!=NULL);
   D->head = NULL;
   D->tail = NULL;
   D->numItems = 0;
   return D;
}

// freeDictionary()
// destructor for the Dictionary type
void freeDictionary(Dictionary* pD){
   if( pD!=NULL && *pD!=NULL ){
      if( !isEmpty(*pD) ) makeEmpty(*pD);
      free(*pD);
      *pD = NULL;
   }
}

// isEmpty()
// returns 1 (true) if D is empty, 0 (false) otherwise
// pre: none
int isEmpty(Dictionary D){
   if( D==NULL ){
      fprintf(stderr, "Dictionary Error: calling isEmpty() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   return(D->numItems==0);
}

// size()
// pre: none
// post: returns the number of entries in this Dictionary
int size(Dictionary D) {
	return D->numItems;
	}

// findKey()
// returns a reference to the Node with this key in this Dictionary
Node findKey(Dictionary D, char* k){
    Node N;
    if( D==NULL ){
      fprintf(stderr, "Dictionary Error: calling findKey() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
	}
    N = D->head;
    while(N != NULL){
		if(strcmp(k, N->key)==0){
			return N;}
		else{
			N = N->next;}
		}
	return NULL;
	}

// lookup()
// returns the value v such that (k, v) is in D, or returns NULL if no 
// such value v exists.
// pre: none
char* lookup(Dictionary D, char* k){
   Node N;
   if( D==NULL ){
      fprintf(stderr, "Dictionary Error: calling lookup() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
    }
   if(findKey(D, k)!= NULL){
	  N = findKey(D, k);
	  return N->value;}
   else{
	  return NULL;
	}
}

// insert()
// inserts new (key,value) pair into D
// pre: lookup(D, k)==NULL
void insert(Dictionary D, char* k, char* v){
   Node N;
   if( D==NULL ){
      fprintf(stderr, "Dictionary Error: calling insert() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   if( lookup(D,k)!=NULL ){
      fprintf(stderr, "insert(): cannot insert duplicate keys\n");
      exit(EXIT_FAILURE);
   }
   
   if( D->head == NULL ){
	 N = newNode(k,v);
	 D->head = N;
	 D->tail = N;
	 }
   else if( D->head == D->tail ){
	 N = D->head;
	 N->next = newNode(k,v);
	 D->tail = N->next;}
   else{
	 N = D->tail;
	 N->next = newNode(k,v);
	 D->tail = N->next;
    } 
	 D->numItems++;
}

// delete()
// deletes pair with the key k
// pre: lookup(D, k)!=NULL
void delete(Dictionary D, char* k){
   Node P;
   Node S;
   if( D==NULL ){
      fprintf(stderr, "Dictionary Error: calling delete() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   if( D->numItems==0 ){
      fprintf(stderr, "Dictionary Error: calling delete() on empty Dictionary\n");
      exit(EXIT_FAILURE);
   }
   
   if( lookup(D,k)==NULL ){
      fprintf(stderr, "delete(): cannot delete non-existent key \n");
      exit(EXIT_FAILURE);
   }
   else if( findKey(D,k)== D->head ){ //delete head if only node
	  if( D->head == D->tail ){
		  P = D->head;
		  free(P);
		  D->head = NULL;
		  D->tail = NULL;}
		 else{
          P = D->head;				//delete head if multiple nodes
          D->head = P->next;
		  free(P);
		  }
	  }
	  else if(findKey(D,k)== D->tail){  //delete tail
		 P = D->head;
		 while(P->next != D->tail){
			 P = P->next;}
		 D->tail = P;
		 P = P->next;
		 P = NULL;
		 free(P);
	  }else{
         S = findKey(D,k);  //delete middle
         P = D->head;
		 while(P->next != S){
			 P = P->next;}
         P->next = S->next;
		 free(S);
      }
   D->numItems--;
   
}

// makeEmpty()
// resets D to the empty state
// pre: !isEmpty(D)
void makeEmpty(Dictionary D){
	Node N;
	Node temp;
	if(D==NULL)
	{
		fprintf(stderr,"Dictionary Error: calling makeEmpty() on NULL Dictionary reference");
		exit(EXIT_FAILURE);
	}
	N=D->head;
	while(N!=NULL)
	{
		temp=N->next;
		free(N);
		N=temp;
	}
	D->numItems=0;
}

// printDictionary()
// prints a text representation of D to the file pointed to by out
// pre: none
void printDictionary(FILE* out, Dictionary D){
   Node N;
   if( D==NULL ){
      fprintf(stderr, "Dictionary Error: calling printDictionary() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   for(N=D->head; N!=NULL; N=N->next) fprintf(out, "%s %s\n", N->key, N->value );
}