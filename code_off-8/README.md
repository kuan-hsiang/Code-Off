#Code Off #8 - Bomberman: The bomb has been planted

##Premise
With reference to the [Entelect Challenge](http://challenge.entelect.co.za).
You're bomberman. You're given a block of walls and bombs. You need to determine the destroyed walls after the explosion occurs.

##Challenge
The radius of walls destroyed by a bomb is specified by the number on the bomb. Each bomb explodes vertically and horizontally, but never diagonally. If a bomb is adjacent to another bomb (vertically, horizontally, or diagonally) its radius increases by 1 for each adjacent bomb.
Determine the outcome of the explosion by marking destroyed walls and bombs with *.

##Legend
```
# = Wall
x = A number indicating the radius of the bomb
* = Destroyed wall
```
##Constraints
* The width and height of the grid will always be the same.
* The width and height of the grid may be sized between 16x16 and 1021x1021.

##Details

###Input
An example input text file:

```
################
################
################
################
#####2##########
##########3#####
################
#####1##########
######1#########
################
################
################
################
################
################
################

```

###Output
A text file in the following format.

```
################
################
#####*####*#####
#####*####*#####
###*****##*#####
#####*#*******##
#####**###*#####
###*****##*#####
####*****#*#####
#####**#########
######*#########
################
################
################
################
################

```
Notice that the destroyed walls and bombs are marked with *. Also, the bombs with radius 1 that were adjacent to each other had their radius increased to 2.
