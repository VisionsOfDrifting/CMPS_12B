/*	Nicholas Pappas
	nhpappas@ucsc.edu
	ID: 1558554
	Assignment: pa3
*/
//-----------------------------------------------------------------------------
// DictionaryTest.c
// Test Client for the Dictionary ADT
//-----------------------------------------------------------------------------

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#include"Dictionary.h"

#define MAX_LEN 180

int main(int argc, char* argv[]){
   Dictionary A = newDictionary();
   char* k;
   char* v;
   insert(A, "1","does"); //Start with making sure the Dictionary is loadable
 //delete(A, "1"); //Check the edge case delete head when it is the only node
   
 //insert(A, "1", "does"); // error: key collision
   insert(A, "2","this"); //I had to debug lookup() and findkey() so that insert() complied
   insert(A, "3","work");
   insert(A, "4","yes"); 
   insert(A, "5","it");
   insert(A, "6","does");
   insert(A, "7","!");
   
 /*printDictionary(stdout, A);*/ //cut&paste the left "/*" to easily comment out all but one pair
 //printf("size= %d\n", size(A)); //Check size() 
   printf("This Dictionary contains: %d elements\n", size(A));
   printf("Dictionary is empty: %s\n", (isEmpty(A)?"true":"false"));
   
   printf("Call lookup(Dictionary, key).\n");
   k = "1";
   v = lookup(A, k);
   printf("key=\"%s\" %s\"%s\"\n", k, (v==NULL?"not found ":"value="), v);
   k = "2";
   v = lookup(A, k);
   printf("key=\"%s\" %s\"%s\"\n", k, (v==NULL?"not found ":"value="), v);
   k = "3";
   v = lookup(A, k);
   printf("key=\"%s\" %s\"%s\"\n", k, (v==NULL?"not found ":"value="), v);
   
   printf("Delete first three elements.\n"); //It's essential to check edge cases. Memory was leaking from delete.
   delete(A, "1"); //Head
   delete(A, "3"); //Middle
   delete(A, "7"); //Tail
 //delete(A, "1");  // error: key not found
   printf("This Dictionary contains: %d elements\n", size(A));
   printDictionary(stdout, A);

   printf("Call makeEmpty(Dictionary).\n");
   makeEmpty(A);
   printf("Dictionary is empty: %s\n", (isEmpty(A)?"true":"false"));

   freeDictionary(&A); //1st thing: had to fix makeEmpty() to get rid of segmentation fault

   return(EXIT_SUCCESS);
}