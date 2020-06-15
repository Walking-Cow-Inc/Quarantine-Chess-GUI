/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quarantinechess;

import java.util.Set;

/**
 *
 * @author Vatsav and Jaymin
 */
public class Piece {

    protected int x;
    protected int y;
    protected char race;
    protected char type;
    protected int move;

    public Piece() { // This is never going to happen
        x = 0;
        y = 0;
        race = 'h';
        move = 0;
    }

    public Piece(int x, int y, char race, char type) {
        this.x = x;
        this.y = y;
        this.race = race;
        this.type = type;
    }

    public char getType() {
        return type;
    }

    public Set<Coordinate> displayMoves(Board b) {
        return null;
    }

    public Set<Coordinate> destructiveMoves(Board b) {
        return null;
    }
}
