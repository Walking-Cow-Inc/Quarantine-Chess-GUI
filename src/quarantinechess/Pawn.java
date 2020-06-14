/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quarantinechess;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import javax.swing.JOptionPane;

/**
 *
 * @author Vatsav and Jaymin
 */
public class Pawn extends Piece{
    public Pawn() {
		super();
	}
	
	public Pawn(int x, int y, char race) {
		super(x, y, race, 'P'); // x and y coordinates, the race and the type
	}
	
	public Pawn(Pawn p) {
		x = p.x;
		y = p.y;
		move = p.move;
		race = p.race;
		type = p.type;
	}
	
	// TODO: We need to check the edgecases
	// TODO: (Low priority) Pawn to Queen conversion
	public Set<Coordinate> displayMoves(Board b) {
		Set<Coordinate> possible = new HashSet<Coordinate>();
		int sign = race == 'b' ? 1 : -1;
		
		// This is to check for KILLABLE MOVES
		if((x+sign >= 0) && (x+sign < 8) && (y-1 >= 0) && (y-1 < 8)) {
			if(b.board[x + sign][y-1] != null) {
				if(b.board[x + sign][y-1].race != race)
					possible.add(new Coordinate(x + sign, y - 1));
			}
		}
		
		if((x+sign >= 0) && (x+sign < 8) && (y+1 >= 0) && (y+1 < 8)) {
			if(b.board[x + sign][y+1] != null) {
				if(b.board[x + sign][y+1].race != race)
					possible.add(new Coordinate(x + sign, y + 1));
			}
		}
		
		// This is to check for MOVABLE MOVES
		
		if((x+sign >= 0) && (x+sign < 8)) {
			if(b.board[x + sign][y] == null) {
				possible.add(new Coordinate(x + sign, y));
			}
			else // This means there is a piece in front
				return possible;
		}
		
		// If first move
		if(move == 0) {
			if(b.board[x + 2*sign][y] == null)
				possible.add(new Coordinate(x + 2*sign, y));
			// TODO: Remove the next line and add when we move the piece
			//move++;
		}
		
            // Made a new board
            // Go through all the possible moves
            // Do the possible move on the pseudo-board
            // Checked the value of checkCheck for the King
            // If the value is true then remove that move                
		
            return possible;
	}
	
	public Set<Coordinate> killableMoves(Board b){
		Set<Coordinate> possible = new HashSet<Coordinate>();
		int sign = race == 'b' ? 1 : -1;
		
		if((x+sign >= 0) && (x+sign < 8) && (y-1 >= 0) && (y-1 < 8)) {
			possible.add(new Coordinate(x + sign, y - 1));
		}
		
		if((x+sign >= 0) && (x+sign < 8) && (y+1 >= 0) && (y+1 < 8)) {
			possible.add(new Coordinate(x + sign, y + 1));
		}
		
		return possible;
	}
	
	public boolean checkConversion() {
		switch(race) {
		case 'b': if(x == 7)
			return true;
		case 'w': if(x == 0)
			return true;
		}
		return false;
	}
	
	public void convertIfPossible(Board b) {
		if(checkConversion()) {
			int  q = 0;
			System.out.println();
			while(q == 0){
                            int choice;
                            choice = Integer.parseInt(JOptionPane.showInputDialog(null, "Conversion of pawn is possible."
                                    + "\nEnter one of the following to change pawn to: (1)Rook (2)Bishop "
                                    + "(3)Knight (4)Queen."));
				
				q++;
				switch(choice) {
				case 1: b.board[x][y] = new Rook(x, y, race);
				b.board[x][y].move = 3;
				break;
				case 2: b.board[x][y] = new Bishop(x, y, race);
				break;
				case 3: b.board[x][y] = new Knight(x, y, race);
				break;
				case 4: b.board[x][y] = new Queen(x, y, race);
				break;
				default: JOptionPane.showMessageDialog(null, "Enter a valid number", "Invalid Input", JOptionPane.ERROR_MESSAGE);
				q=0;
				}
			}
		}
	}
}
