/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quarantinechess;

/**
 *
 * @author Vatsav and Jaymin
 */
public class Coordinate {

    public int x;
    public int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public boolean equals(Object o) {
        if (o instanceof Coordinate) {
            Coordinate toCheck = (Coordinate) o;
            return toCheck.x == x && toCheck.y == y;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return x * 1000 + y;
    }
}
