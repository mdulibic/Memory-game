# Memory-game

## General about the application

The application is an online Memory game.
The goal of the game is to beat your opponent in the Memory game. The application displays 8x8 fields with different
pictures and the goal is to open fields and guess the position of two identical fields, after which the fields
they close. The winner is the player who hits all the squares first.

## Functionalities
- registration on the system, entry of name, surname, username, e-mail address and
passwords, sending a link to confirm registration to an e-mail address
- logging in to the system: entering a username and password
- after successful registration of the application, display the list of registered players (opponents)
- by selecting an opponent and accepting his participation in the game, start the game and after
after the end of the game, the winner is recorded
- the application has the ability to display a list of unsuccessful players

## Backend

The application has a server component consisting of REST services for registration, login,
retrieving registered players, joining players and starting the game, record of winners and retrieving
lists of the most successful players.
Spring Boot and local h2 database were used.
