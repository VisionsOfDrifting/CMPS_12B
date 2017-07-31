# A general purpose Makefile w/ macros
#Note replace yourProgram w/ proper file name
                        
JAVASRC = yourProgram.java
SOURCES = README Makefile $(JAVASRC)
MAINCLASS = yourProgram
CLASSES = yourProgram.class
JARFILE = yourProgram
SUBMIT = submit cmps012b-pt.u16 pa2
#replace pa2 w/ assignment name

all: $(JARFILE)

$(JARFILE): $(CLASSES)
    echo Main-class: $(MAINCLASS) > Manifest
    jar cvfm $(JARFILE) Manifest $(CLASSES)
    rm Manifest
    chmod +x $(JARFILE)

$(CLASSES): $(JAVASRC)
    javac -Xlint $(JAVASRC)

clean:
    rm $(CLASSES) $(JARFILE)

submit: $(SOURCES)
    $(SUBMIT) $(SOURCES)