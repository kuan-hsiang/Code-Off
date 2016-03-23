# Code-Off
Code Off is an initiative that challenges developers to take 30 minutes off a Friday afternoon to work on something interesting with their peers. Code Off also aims to help developers expand their Github portfolio and collaborate with the development community. Each Friday afternoon at 15:30 CAT a new problem will be released on Github. Fork the repository, and get started.
The automated leaderboard is still in development. We encourage you to collaborate with other developers on Github.

http://www.prolificidea.com/codeoff.html

#How to Participate?
Fork the Code Off repo and push your solution to your fork.

#Code Off #5 - Maze Runner

##Premise
You're trapped in a maze and need to escape to get to your coffee.

##Challenge
Your goal is to mark a path (.) from the starting point (@) to the coffee (U)

##Legend
```
# = Wall
@ = Start point
U = Coffee
. = Path to exit
```
##Constraints
* The width and height of the grid will always be the same.
* The width and height of the grid may be sized between 21x21 and 1021x1021.

##Details

###Input
An example input text file:

```
###################U#
#             #     #
# # ### # # ### #####
# # # # # #       # #
# # # ### ##### ### #
# # # #       #   # #
##### # ######### # #
#         # #       #
# # # ### # #########
# # #   # #   #     #
# # # ####### ##### #
# # #     @         #
##### ####### # ### #
#   #       # # #   #
# ####### # ### ### #
# # # # # #   #   # #
# # # # ####### ### #
#       #       # # #
##### ##### # # # ###
#           # #     #
#####################
```

###Output
A text file in the following format.

```
###################U#
#        ...  #.....#
# # ### #.#.###.#####
# # # # #.#.....  # #
# # # ###.##### ### #
# # # #...    #   # #
##### #.######### # #
#    ...  # #       #
# # #.### # #########
# # #.  # #   #     #
# # #.####### ##### #
# # #.....@         #
##### ####### # ### #
#   #       # # #   #
# ####### # ### ### #
# # # # # #   #   # #
# # # # ####### ### #
#       #       # # #
##### ##### # # # ###
#           # #     #
#####################
```
Notice that the path from the starting location to the coffee cup is marked using the "." symbol.
