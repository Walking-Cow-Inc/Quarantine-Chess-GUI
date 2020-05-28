/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quarantinechess;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;



/**
 *
 * @author Vatsav and Jaymin
 */
public class QuarantineChess {
    
// This is our OG main method
    
    /**
     * @param args the command line arguments
     */
    
    private static char turn = 'w';
    
    public static void main(String[] args) {
        // TODO code application logic here
        Board b = new Board();  // This makes the board
	System.out.println(b);
	int x = 0, y = 0, newx, newy;
	Scanner sc = new Scanner(System.in);
	Set<Coordinate> possible = new HashSet<>();
	int over = 0;
	while(over == 0) {
		switch(turn) {
		case 'w': 
		do {
			System.out.println("White's turn");
			System.out.println("Enter the piece which you want to move");
			x = sc.nextInt();
			y = sc.nextInt();
			if(x > 7 || x < 0 || y > 7 || y < 0) {
				System.out.println("Out of your limits, just like she was\n\n"); // Change before showing
				continue;
			}
			if(b.board[x][y] == null || b.board[x][y].race != turn || b.board[x][y].displayMoves(b).isEmpty()) {
				System.out.println("No chances, in more than one way");
				System.out.print("It's still ");
				continue;
			}
			// It's not empty
			possible = b.board[x][y].displayMoves(b);
			System.out.print("The allowed moves for this piece are: \n| ");
			for(Coordinate each : possible) {
				System.out.print(each + " | ");
			}
			System.out.println("\n");
			turn = 'b';
			Board old = new Board(b);
			over = moves(b, x, y, possible);
			if(over != 0)
				break;
			// This is not a checkmate	
			if(((King)b.board[b.kingPos[1].x][b.kingPos[1].y]).checkCheck) {
					// This means the move was invalid
					b = new Board(old);
					System.out.println("Move was invalid, try not to kill ur King");
					turn = 'w';
				}
			System.out.println(b);
		}while(((King)b.board[b.kingPos[1].x][b.kingPos[1].y]).checkCheck);
		break;
		
		case 'b':
		do {	
			System.out.println("Black's turn");
			System.out.println("Enter the piece which you want to move");
			x = sc.nextInt();
			y = sc.nextInt();
			if(x > 7 || x < 0 || y > 7 || y < 0) {
				System.out.println("Out of your limits, just like she was\n\n"); // Change before showing
				continue;
			}
			if(b.board[x][y] == null || b.board[x][y].race != turn || b.board[x][y].displayMoves(b).isEmpty()) {
				System.out.println("No chances, in more than one way");
				System.out.print("It's still ");
				continue;
			}
			// It's not empty
			possible = b.board[x][y].displayMoves(b);
			System.out.print("The allowed moves for this piece are: \n| ");
			for(Coordinate each : possible) {
				System.out.print(each + " | ");
			}
			System.out.println("\n");
			turn = 'w';
			Board old = new Board(b);
			over = moves(b, x, y, possible);
			if(over != 0)
				break;
			// This is not a checkmate
			if(((King)b.board[b.kingPos[0].x][b.kingPos[0].y]).checkCheck) {
					// This means the move was invalid
					b = new Board(old);
					System.out.println("Move was invalid, try not to kill ur King");
					turn = 'b';
				}
			System.out.println(b);
		} while(((King)b.board[b.kingPos[0].x][b.kingPos[0].y]).checkCheck);
		break;
		}
	}
	
	if(over == 1)
		System.out.println("White wins. I don't have a dream");
	else if(over == 2)
		System.out.println("Black wins. Slavery is no more");
	else
		System.out.println("Well that was pointless. Stalemate");
    }
    
    private static int moves(Board b, int x, int y, Set<Coordinate> possible) throws InputMismatchException{
	int newx, newy;
	Scanner sc = new Scanner(System.in);
	int a = 1;
	while(a != 0) {
		System.out.println("Enter the position where you want to move this piece to");
		newx = sc.nextInt();
		newy = sc.nextInt();
		
		if(b.board[x][y] instanceof King) {
			
			// This part is only to move the Rook
			if(newy - y == 2) {
				b.board[x][5] = b.board[x][7];
				b.board[x][5].y = 5;
				b.board[x][7] = null;
				switch(x) {
				case 0: b.black.remove(new Coordinate(0, 7));
				b.black.add(new Coordinate(0, 5));
				break;
				
				case 7: b.white.remove(new Coordinate(7, 7));
				b.white.add(new Coordinate(7, 5));
				break;
				}
			}
			else if(y - newy == 2) {
				b.board[x][3] = b.board[x][0];
				b.board[x][3].y = 3;
				b.board[x][0] = null;
				switch(x) {
				case 0: b.black.remove(new Coordinate(0, 0));
				b.black.add(new Coordinate(0, 3));
				break;
				
				case 7: b.white.remove(new Coordinate(7, 0));
				b.white.add(new Coordinate(7, 3));
				break;
				}
			}
			
			// To add the new King positions to the array
			switch(b.board[x][y].race) {
			case 'b': b.kingPos[0] = new Coordinate(newx, newy);
			break;
			case 'w': b.kingPos[1] = new Coordinate(newx, newy);
			break;
			}
		}
		
		if(possible.contains(new Coordinate(newx, newy))) {
			
			b.board[newx][newy] = b.board[x][y]; // Changes the position of the old piece on the board
			b.board[newx][newy].x = newx;
			b.board[newx][newy].y = newy;
			b.board[newx][newy].move++;
			
			if(b.board[x][y].race == 'b') {
				b.black.remove(new Coordinate(x, y));
				b.black.add(new Coordinate(newx, newy));
				b.white.remove(new Coordinate(newx, newy));
			}
			
			else {
				b.white.remove(new Coordinate(x, y));
				b.white.add(new Coordinate(newx, newy));
				b.black.remove(new Coordinate(newx, newy));
			}
			
			if(b.board[newx][newy] instanceof Pawn)
				((Pawn)b.board[newx][newy]).convertIfPossible(b);
			
			b.board[x][y] = null; // Move is complete
			
			Set<Coordinate> moves = new HashSet<Coordinate>();
		for(Coordinate each : b.black) {
				if(b.board[each.x][each.y] instanceof King)
					continue;
				if(b.board[each.x][each.y] instanceof Pawn)
					moves.addAll(((Pawn)(b.board[each.x][each.y])).killableMoves(b));
				else
					moves.addAll(b.board[each.x][each.y].displayMoves(b));
			}
			// Now we have all the moves of the black pieces
			if(moves.contains(b.kingPos[1])){
				System.out.println("Check");
				((King)b.board[b.kingPos[1].x][b.kingPos[1].y]).checkCheck = true;
			}
			else 
				((King)b.board[b.kingPos[1].x][b.kingPos[1].y]).checkCheck = false;
			
			for(Coordinate each : b.white) {
				if(b.board[each.x][each.y] instanceof King)
					continue;
				if(b.board[each.x][each.y] instanceof Pawn)
					moves.addAll(((Pawn)(b.board[each.x][each.y])).killableMoves(b));
				else
					moves.addAll(b.board[each.x][each.y].displayMoves(b));
			}
			// Now we have all the moves of the white pieces
			if(moves.contains(b.kingPos[0])){
				System.out.println("Check");
				((King)b.board[b.kingPos[0].x][b.kingPos[0].y]).checkCheck = true;
			}
			else 
				((King)b.board[b.kingPos[0].x][b.kingPos[0].y]).checkCheck = false;
			
			a = 0;
		}
		else 
			System.out.print("Re-");
	}
	return gameNotOver(b);
}
    private static List<Coordinate> getKiller(Board b, char race){
	List<Coordinate> killers = new ArrayList<>();
	Set<Coordinate> opponentPieces = (race == 'b') ? b.white : b.black;
	Coordinate king = (race == 'b') ? b.kingPos[0] : b.kingPos[1];
	Set<Coordinate> opponentMoves = new HashSet<Coordinate>();
	
	for(Coordinate each : opponentPieces) {
		if(b.board[each.x][each.y] instanceof King)
			continue;
		if(b.board[each.x][each.y] instanceof Pawn) {
			if ((((Pawn)(b.board[each.x][each.y])).killableMoves(b)).contains(king))
				killers.add(each);
		}
		else {
			if(b.board[each.x][each.y].displayMoves(b).contains(king))
				killers.add(each);
		}
	}
	return killers;
    }

    // TODO: Add stalemate and stuff
    private static int gameNotOver(Board b) {
	// For Checkmate: 3 parts
	// Part 1: King is in check and has no available moves
	
	// This if checks if the black king is in checkmate
	if((((King)b.board[b.kingPos[0].x][b.kingPos[0].y]).checkCheck)&&b.board[b.kingPos[0].x][b.kingPos[0].y].displayMoves(b).size() == 0){
		// Part 2: Some piece can kill the checking piece
		List<Coordinate> killers = new ArrayList<Coordinate>(getKiller(b, 'b'));
		Set<Coordinate> betweenMoves = new HashSet<Coordinate>();
		// This is if the King can't move and there is more than one piece attacking the King
		// In this case, the King is ded cos he can't move and no other piece can kill or block both
		if(killers.size() > 1)
			return 1;
		
		// Now check if the killer can be killed
		for(Coordinate each : b.black) {
			if(b.board[each.x][each.y] instanceof King)
				continue;
			if(b.board[each.x][each.y] instanceof Pawn) {
				if(((Pawn)b.board[each.x][each.y]).killableMoves(b).contains(killers.get(0)))
						return 0;
			}
			else {
				if((b.board[each.x][each.y]).displayMoves(b).contains(killers.get(0)))
						return 0;
			}
		}
		// Part 3: Some piece obstructs the checking piece
		if (b.board[killers.get(0).x][killers.get(0).y] instanceof Bishop)
			betweenMoves = ((Bishop)b.board[killers.get(0).x][killers.get(0).y]).getBetween(b.kingPos[0]);
		
		else if (b.board[killers.get(0).x][killers.get(0).y] instanceof Rook)
			betweenMoves = ((Rook)b.board[killers.get(0).x][killers.get(0).y]).getBetween(b.kingPos[0]);
		
		else if (b.board[killers.get(0).x][killers.get(0).y] instanceof Queen)
			betweenMoves = ((Queen)b.board[killers.get(0).x][killers.get(0).y]).getBetween(b.kingPos[0]);
		
		else
			return 1;
		
		Set<Coordinate> allMoves = new HashSet<>();
		for(Coordinate each : b.black) {
			if(b.board[each.x][each.y] instanceof King)
				continue;
			else
				allMoves.addAll(b.board[each.x][each.y].displayMoves(b));
		}
		// Checks if the Coordinate is present in betweenMoves
		for(Coordinate each : betweenMoves) {
			if(allMoves.contains(each))
				return 0;
		}	
		return 1;
	}
	
	//This if checks if the white king is in checkmate
	if((((King)b.board[b.kingPos[1].x][b.kingPos[1].y]).checkCheck)&&b.board[b.kingPos[1].x][b.kingPos[1].y].displayMoves(b).size() == 0){
		// Part 2: Some piece can kill the checking piece
		List<Coordinate> killers = new ArrayList<Coordinate>(getKiller(b, 'w'));
		Set<Coordinate> betweenMoves = new HashSet<Coordinate>();
		// This is if the King can't move and there is more than one piece attacking the King
		// In this case, the King is ded cos he can't move and no other piece can kill or block both
		if(killers.size() > 1)
			return 2;
		
		// Now check if the killer can be killed
		for(Coordinate each : b.white) {
			if(b.board[each.x][each.y] instanceof King)
				continue;
			if(b.board[each.x][each.y] instanceof Pawn) {
				if(((Pawn)b.board[each.x][each.y]).killableMoves(b).contains(killers.get(0)))
						return 0;
			}
			else {
				if((b.board[each.x][each.y]).displayMoves(b).contains(killers.get(0)))
						return 0;
			}				
		}
		// Part 3: Some piece obstructs the checking piece
		if (b.board[killers.get(0).x][killers.get(0).y] instanceof Bishop)
			betweenMoves = ((Bishop)b.board[killers.get(0).x][killers.get(0).y]).getBetween(b.kingPos[1]);
		
		else if (b.board[killers.get(0).x][killers.get(0).y] instanceof Rook)
			betweenMoves = ((Rook)b.board[killers.get(0).x][killers.get(0).y]).getBetween(b.kingPos[1]);
		
		else if (b.board[killers.get(0).x][killers.get(0).y] instanceof Queen)
			betweenMoves = ((Queen)b.board[killers.get(0).x][killers.get(0).y]).getBetween(b.kingPos[1]);
		
		else
			return 2;
		
		Set<Coordinate> allMoves = new HashSet<>();
		for(Coordinate each : b.white) {
			if(b.board[each.x][each.y] instanceof King)
				continue;
			else
				allMoves.addAll(b.board[each.x][each.y].displayMoves(b));
		}
		// Checks if the Coordinate is present in betweenMoves
		for(Coordinate each : betweenMoves) {
			if(allMoves.contains(each))
				return 0;
		}
		
		return 2;
	}
	return stalemateCheck(b);
    }

    public static int stalemateCheck(Board b) {
	Set<Coordinate> allMoves = new HashSet<Coordinate>();
	for(Coordinate each : b.black) {
		allMoves.addAll(b.board[each.x][each.y].displayMoves(b));
	}
	System.out.println("Size = " + allMoves.size());
	if(allMoves.size() == 0)
		return 69;
	
	allMoves = new HashSet<>();
	for(Coordinate each : b.white) {
		allMoves.addAll(b.board[each.x][each.y].displayMoves(b));
	}
	System.out.println(allMoves.size());
	if(allMoves.size() == 0)
		return 69;
	
	return 0;
    }
}