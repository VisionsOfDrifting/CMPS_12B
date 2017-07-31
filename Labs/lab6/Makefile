#Nicholas Pappas
#nhpappas
#CMPS 12M
#8/3/16
#lab6
#------------------------------------------------------------------------------
#   Makefile for List ADT
#------------------------------------------------------------------------------

# Variables
JARFILE    = ListClient
MAINCLASS  = ListClient
SOURCES    = List.java ListInterface.java ListTest.java ListClient.java\
             ListIndexOutOfBoundsException.java Makefile README
CLASSES    = List.class ListInterface.class ListTest.class ListClient.class\
             ListIndexOutOfBoundsException.class
SUBMIT	   = submit cmps012b-pt.u16 lab6
			 
# Build Targets
all: $(JARFILE)

$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(CLASSES)
	rm Manifest
	chmod +x $(JARFILE)

$(CLASSES): $(SOURCES)
	javac -Xlint $(SOURCES)  

clean:
	rm -f $(CLASSES) $(JARFILE)
	
submit: $(SOURCES)
	$(SUBMIT) $(SOURCES)