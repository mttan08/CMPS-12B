# ---------------------------------------------------------------------------
# Makefile for lab2
# Matthew Tan
# mxtan
# CMPS 12B/M
# ---------------------------------------------------------------------------

FileReverse: FileReverse.class
	echo Main-class: FileReverse > Manifest
	jar cvfm FileReverse Manifest FileReverse.class
	rm Manifest
	chmod +x FileReverse

FileReverse.class: FileReverse.java
	javac -Xlint FileReverse.java

clean:
	rm -f FileReverse FileReverse.class

sumbit: README Makefile FileReverse.java
	submit cmps012b-pt.u16 lab2 README Makefile FileReverse.java

