#A C Makefile w/ macros
#Note replace yourProgram w/ proper file name

FLAGS = -std=c99 -Wall
SOURCES = README Makefile FileReverse.c
OBJECTS = FileReverse.o
EXEBIN = FileReverse
SUBMIT = submit cmps012b-pt.u16 lab3
#replace lab3 w/ assignment name

all: $(EXEBIN)

$(EXEBIN) : $(OBJECTS)
	gcc -o $(EXEBIN) $(OBJECTS)

$(OBJECTS) : $(SOURCES)
	gcc -c $(FLAGS) $(SOURCES)

clean : 
	rm $(EXEBIN) $(OBJECTS)
#Removed -f after rm
	
submit: $(SOURCES)
	$(SUBMIT) $(SOURCES)
#!^! Don't forget this is a tab not spaces