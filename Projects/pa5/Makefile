#Nicholas Pappas
#nhpappas
#CMPS 12B
#8/7/16
#pa5
#------------------------------------------------------------------------------
# Makefile for DictionaryClient.c with macros
#------------------------------------------------------------------------------

FLAGS   = -std=c99 -Wall

CSOURCES = Dictionary.c DictionaryClient.c DictionaryTest.c
SOURCES = $(CSOURCES) Makefile README $(HEADERS)
OBJECTS = Dictionary.o DictionaryClient.o
HEADERS = Dictionary.h
EXEBIN  = DictionaryClient
SUBMIT  = submit cmps012b-pt.u16 pa5

all: $(EXEBIN)

$(EXEBIN) : $(OBJECTS) $(HEADERS)
	gcc -o $(EXEBIN) $(OBJECTS)
	
$(OBJECTS) : $(CSOURCES) $(HEADERS)
	gcc -c $(FLAGS) $(CSOURCES)

clean :
	rm -f $(EXEBIN) $(OBJECTS)

check:
	valgrind --leak-check=full --show-leak-kinds=all $(EXEBIN) 
	
submit: $(SOURCES)
	$(SUBMIT) $(SOURCES)