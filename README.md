# Quarantine Chess GUI
This project contains the graphical version of of our previously created Quaratine Chess which ran solely in the terminal. To create the GUI, we made use of the `Swing` and `AWT` Java Foundational Classes.

# Overview
We wanted to challenge ourselves to learn how to play chess during lockdown, and what better way than to code the entire game from scratch? We're using Object Oriented Programming to model every piece type (the Queen, the King, the Pawn, etc) so that we can use their instances to model the behaviour of each piece with no repeated code. 

# Rules of the Game
In general, there are 2 sides in a chess game, each denoted by the color of the pieces, namely Black and White. Each side has 8 pawns, 2 bishops, 2 knights, 2 rooks, a Queen and a King. All these pieces have a unique way of moving around the board that the player has to strategically take advantage of to complete the objective of the game. The primary objective of the game is to capture the King piece of the other side (Checkmate) using the pieces you have at your disposal.

**There are 3 possible outcomes in every game:**
* The player with the Black pieces wins by capturing the White King
* The player with the White pieces wins by capturing the Black King
* The game ends in a stalemate where there are no possible moves for either of the players.

Chess is quite a complex game and there have been a lot of new rules introduced since its conception. I haven't and definitely can't go over every single one over here. You can find a link to a basic set of rules and how to play over [here.](https://www.chess.com/learn-how-to-play-chess)

For a more comprehensive set of rules, you can check out the FIDE Laws of Chess using this [link.](https://www.fide.com/FIDE/handbook/LawsOfChess.pdf)

# Usage
#### To run this game, you need to have Java installed on your computer along with a Java IDE or a text editor with a Java extension (like Atom or VS Code). We recommend the Eclipse IDE since that was what we used to build this application, but feel free to run it in an editor of your choice.

You can download the appropriate version of Java using this link: https://java.com/en/download/help/download_options.xml

You can download the Eclipse IDE using this link: https://www.eclipse.org/downloads/

After this, you can clone the repository by entering `git clone https://github.com/Walking-Cow-Inc/Quarantine-Chess-GUI.git` in your command line (assuming you have git installed on your computer) and proceed to open this project in the IDE or editor of your choice.


