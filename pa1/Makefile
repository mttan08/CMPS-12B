# ---------------------------------------------------------------------------
# Makefile for pa1
# Matthew Tan
# mxtan
# CMPS 12B/M
# ---------------------------------------------------------------------------

Recursion: Recursion.class
	echo Main-class: Recursion > Manifest
	jar cvfm Recursion Manifest Recursion.class
	rm Manifest
	chmod +x Recursion

Recursion.class: Recursion.java
	javac -Xlint Recursion.java

clean:
	rm -f Recursion Recursion.java

submit: README Makefile Recursion.java
	submit cmps012b-pt.u16 pa1 README Makefile Recursion.javac
