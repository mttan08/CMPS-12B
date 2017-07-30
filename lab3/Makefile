# ---------------------------------------------------------------------------
# Makefile for FileReverse
# Matthew Tan
# mxtan
# CMPS 12B/M 
# ---------------------------------------------------------------------------

FileReverse: FileReverse.o
	gcc -o FileReverse FileReverse.o

FileReverse.o: FileReverse.c
	gcc -c -std=c99 -Wall FileReverse.c

clean:
	rm -f FileReverse FileReverse.o

submit:
	submit cmps012b-pt.u16 lab3 README Makefile FileReverse.c
