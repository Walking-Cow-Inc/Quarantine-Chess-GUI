# Quarantine Chess GUI
This project contains the graphical version of of our previously created Quaratine Chess which ran solely in the terminal. To create the GUI, we made use of the `Swing` and `AWT` Java Foundational Classes.

## Overview
We wanted to challenge ourselves to learn how to play chess during lockdown, and what better way than to code the entire game from scratch? We're using Object Oriented Programming to model every piece type (the Queen, the King, the Pawn, etc) so that we can use their instances to model the behaviour of each piece with no repeated code. 

## Rules of the Game
In general, there are 2 sides in a chess game, each denoted by the color of the pieces, namely Black and White. Each side has 8 pawns, 2 bishops, 2 knights, 2 rooks, a Queen and a King. All these pieces have a unique way of moving around the board that the player has to strategically take advantage of to complete the objective of the game. The primary objective of the game is to capture the King piece of the other side (Checkmate) using the pieces you have at your disposal.

**There are 3 possible outcomes in every game:**
* The player with the Black pieces wins by capturing the White King
* The player with the White pieces wins by capturing the Black King
* The game ends in a stalemate where there are no possible moves for either of the players.

Chess is quite a complex game and there have been a lot of new rules introduced since its conception. I haven't and definitely can't go over every single one over here. You can find a link to a basic set of rules and how to play over [here.](https://www.chess.com/learn-how-to-play-chess)

For a more comprehensive set of rules, you can check out the FIDE Laws of Chess using this [link.](https://www.fide.com/FIDE/handbook/LawsOfChess.pdf)

## Usage
### Prerequisites
To run this game, you need to have Java installed on your computer along with a Java IDE or a text editor with a Java extension (like Atom or VS Code). We recommend the Eclipse IDE since that was what we used to build this application, but feel free to run it in an editor of your choice.

You can download the appropriate version of Java using this link: https://java.com/en/download/help/download_options.xml

You can download the Eclipse IDE using this link: https://www.eclipse.org/downloads/

### Installing

You can clone the repository by entering the following line in your terminal (assuming you have git installed on your computer). 
```
git clone https://github.com/Walking-Cow-Inc/Quarantine-Chess-GUI.git
```
Finally, you can continue to open this project in the IDE or editor of your choice.

## Demonstration
### Displaying the positions where each piece can move to
When you click on a piece to move it, you can see the positions to which it is allowed to move to. When you click on a block which has no piece, a pop up box appears with an warning message. This also occurs when you try to move a piece when it is not your turn.

![Display possible moves](https://github.com/Vatsav14/Project-Pictures/blob/master/Chess/chess-demo1.gif)

### Capturing pieces
A piece can capture an opposing piece by moving to the block in which it is positioned. This eliminates the piece from the game.

![Capturing pieces](https://github.com/Vatsav14/Project-Pictures/blob/master/Chess/chess-capture.gif)

### Checkmate
As mentioned before, the objective of the game is to induce a checmate on the opposing team's King piece. When the game reaches this situation, the player who was able to capture the King wins the match. The checkmate shown in this animation is popularly called **Fool's Mate.** This is the fastest possible checkmate in the game of chess. You can read more about this [here.](https://www.chess.com/article/view/the-fastest-possible-checkmate-in-chess)

![Fool's mate checkmate](https://github.com/Vatsav14/Project-Pictures/blob/master/Chess/chess-checkmate.gif)

### Stalemate
A stalemate arises when a player has no possible moves that they can play that doesn't put their King piece in danger. When such a situation arises, the game is tied and both players walk away with a draw. The stalemate shown here is the fastest possible stalemate in the game of chess. Again, you can learn more about this [here.](https://www.chess.com/forum/view/game-showcase/fastest-stalemate-known-in-chess)

![Stalemate](https://github.com/Vatsav14/Project-Pictures/blob/master/Chess/chess-stalemate.gif)

### Castling
Castling is a special kind of move in which the King piece is allowed to move more than one position in a single move. This is the only move which allows the King to move in this fashion. In King-side castling, the Rook piece moves 2 blocks across, compared to Queen-side castling where it can move 3 blocks.

#### King-side castling

![King-side castling](https://github.com/Vatsav14/Project-Pictures/blob/master/Chess/chess-kcastling.gif)

#### Queen-side castling

![Queen-side castling](https://github.com/Vatsav14/Project-Pictures/blob/master/Chess/chess-qcastling.gif)

### Pawn-conversion
This is a move that allows a Pawn piece to convert into any other piece (except a King) when it reaches the final row of the opposing size.

![Pawn conversion](https://github.com/Vatsav14/Project-Pictures/blob/master/Chess/chess-convert.gif)


