# ----------------------------------------------------------------------------
# Makefile for pa2
# Matthew Tan
# mxtan
# CMPS 12B/M
# ----------------------------------------------------------------------------

Search: Search.class
	echo Main-class: Search > Manifest
	jar cvfm Search Manifest Search.class
	rm Manifest
	chmod +x Search

Search.class: Search.java
	javac -Xlint Search.java

clean:
	rm -rf Search Search.class

submit: README Makefile Search.java
	submit cmps012b-pt.u16 pa2 README Makefile Search.java

