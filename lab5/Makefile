#----------------------------------------------------------------------------
# Makefile for any ADT and its test client (lab5)
# Matthew Tan
# mxtan
# lab5
# CMPS 12B/M
#----------------------------------------------------------------------------

ADT_NAME  = Dictionary
SOURCES = $(ADT_NAME).c $(ADT_NAME)Test.c $(ADT_NAME)Client.c
OBJECTS   = $(ADT_NAME).o $(ADT_NAME)Client.o
HEADERS   = $(ADT_NAME).h
EXEBIN    = $(ADT_NAME)Client
FLAGS     = -std=c99 -Wall
SUBMIT     = submit cmps012b-pt.u16 lab1

all: $(EXEBIN)

$(EXEBIN) : $(OBJECTS) $(HEADERS)
	gcc -o $(EXEBIN) $(OBJECTS)

$(OBJECTS) : $(SOURCES) $(HEADERS)
	gcc -c $(FLAGS) $(SOURCES)

clean :
	rm -f $(EXEBIN) $(OBJECTS)

check:
	valgrind --leak-check=full $(EXEBIN)

submit: $(SOURCES)
	$(SUBMIT) $(SOURCES)
