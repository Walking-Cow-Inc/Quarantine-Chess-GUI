/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quarantinechess;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Vatsav and Jaymin
 */
public class King extends Piece {

    public boolean checkCheck = false;

    public King() {
        super();
    }

    public King(int x, int y, char race) {
        super(x, y, race, 'K');
    }

    public King(King q) {
        x = q.x;
        y = q.y;
        checkCheck = q.checkCheck;
        move = q.move;
        race = q.race;
        type = q.type;
    }

    public Set<Coordinate> displayMoves(Board b) {
        Set<Coordinate> possible = new HashSet<Coordinate>();
        Set<Coordinate> opponentPieces = (race == 'b') ? b.white : b.black;
        Set<Coordinate> opponentMoves = new HashSet<Coordinate>();

        for (Coordinate each : opponentPieces) {
            if (b.board[each.x][each.y] instanceof King) {
                //continue;
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        opponentMoves.add(new Coordinate(each.x + i, each.y + j));
                    }
                }
            } else if (b.board[each.x][each.y] instanceof Pawn) {
                opponentMoves.addAll(((Pawn) (b.board[each.x][each.y])).killableMoves(b));
            } else {
                opponentMoves.addAll(b.board[each.x][each.y].destructiveMoves(b));
            }
        }

        // x remains the same
        if (y + 1 < 8) {
            if (b.board[x][y + 1] == null || b.board[x][y + 1].race != race) {
                possible.add(new Coordinate(x, y + 1));
            }
        }

        if (y - 1 >= 0) {
            if (b.board[x][y - 1] == null || b.board[x][y - 1].race != race) {
                possible.add(new Coordinate(x, y - 1));
            }
        }

        // x increases by one
        if (x + 1 < 8 && y + 1 < 8) {
            if (b.board[x + 1][y + 1] == null || b.board[x + 1][y + 1].race != race) {
                possible.add(new Coordinate(x + 1, y + 1));
            }
        }

        if (x + 1 < 8 && y - 1 >= 0) {
            if (b.board[x + 1][y - 1] == null || b.board[x + 1][y - 1].race != race) {
                possible.add(new Coordinate(x + 1, y - 1));
            }
        }

        if (x + 1 < 8) {
            if (b.board[x + 1][y] == null || b.board[x + 1][y].race != race) {
                possible.add(new Coordinate(x + 1, y));
            }
        }

        // x decreases by one
        if (x - 1 >= 0 && y + 1 < 8) {
            if (b.board[x - 1][y + 1] == null || b.board[x - 1][y + 1].race != race) {
                possible.add(new Coordinate(x - 1, y + 1));
            }
        }

        if (x - 1 >= 0 && y - 1 >= 0) {
            if (b.board[x - 1][y - 1] == null || b.board[x - 1][y - 1].race != race) {
                possible.add(new Coordinate(x - 1, y - 1));
            }
        }

        if (x - 1 >= 0) {
            if (b.board[x - 1][y] == null || b.board[x - 1][y].race != race) {
                possible.add(new Coordinate(x - 1, y));
            }
        }

        possible.removeAll(opponentMoves);

        // TODO: Coolify
        switch (checkCastle(b, race)) {

            // Black Castling
            case 1:
                possible.add(new Coordinate(0, 2));
                break;
            case 10:
                possible.add(new Coordinate(0, 6));
                break;
            case 11:
                possible.add(new Coordinate(0, 2));
                possible.add(new Coordinate(0, 6));
                break;

            // White Castling
            case 21:
                possible.add(new Coordinate(7, 2));
                break;
            case 30:
                possible.add(new Coordinate(7, 6));
                break;
            case 31:
                possible.add(new Coordinate(7, 2));
                possible.add(new Coordinate(7, 6));
                break;
        }

        return possible;
    }

    public int checkCastle(Board b, char race) { // AMAZING IDEA BY JAYMIN

        // Shit Efficiency, try to fix later (Found solution)
        Set<Coordinate> opponentPieces = (race == 'b') ? b.white : b.black;
        Set<Coordinate> opponentMoves = new HashSet<Coordinate>();

        for (Coordinate each : opponentPieces) {
            if (b.board[each.x][each.y] instanceof King) {
                continue;
            }
            if (b.board[each.x][each.y] instanceof Pawn) {
                opponentMoves.addAll(((Pawn) (b.board[each.x][each.y])).killableMoves(b));
            } else {
                opponentMoves.addAll(b.board[each.x][each.y].displayMoves(b));
            }
        }

        switch (race) {
            case 'b':
                if (b.board[0][4] != null && b.board[0][4].move == 0) {
                    int i = 0;
                    if (b.board[0][0] != null && b.board[0][0].move == 0) {
                        if (b.board[0][1] == null && b.board[0][2] == null && b.board[0][3] == null) {
                            if (!opponentMoves.contains(new Coordinate(0, 2)) && !opponentMoves.contains(new Coordinate(0, 3))
                                    && !opponentMoves.contains(new Coordinate(0, 4))) {
                                i++;
                            }
                        }
                    }

                    if (b.board[0][7] != null && b.board[0][7].move == 0) {
                        if (b.board[0][5] == null && b.board[0][6] == null) {
                            if (!opponentMoves.contains(new Coordinate(0, 6)) && !opponentMoves.contains(new Coordinate(0, 5))
                                    && !opponentMoves.contains(new Coordinate(0, 4))) {
                                i += 10;
                            }
                        }
                    }
                    return i;
                }
                break;

            case 'w':
                if (b.board[7][4] != null && b.board[7][4].move == 0) {
                    int i = 20;
                    if (b.board[7][0] != null && b.board[7][0].move == 0) {
                        if (b.board[7][1] == null && b.board[7][2] == null && b.board[7][3] == null) {
                            if (!opponentMoves.contains(new Coordinate(7, 2)) && !opponentMoves.contains(new Coordinate(7, 3))
                                    && !opponentMoves.contains(new Coordinate(7, 4))) {
                                i++;
                            }
                        }
                    }

                    if (b.board[7][7] != null && b.board[7][7].move == 0) {
                        if (b.board[7][5] == null && b.board[7][6] == null) {
                            if (!opponentMoves.contains(new Coordinate(7, 6)) && !opponentMoves.contains(new Coordinate(7, 5))
                                    && !opponentMoves.contains(new Coordinate(7, 4))) {
                                i += 10;
                            }
                        }
                    }
                    return i;
                }
                break;
        }
        return 69;
    }

    public static void main(String args[]) {
        GameBoard.main(args);
    }
}
