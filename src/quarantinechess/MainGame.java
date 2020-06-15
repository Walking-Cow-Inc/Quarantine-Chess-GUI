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
import javax.swing.JOptionPane;

/**
 *
 * @author Vatsav and Jaymin
 */
public class MainGame {

// This is our OG main method
    /**
     * @param args the command line arguments
     */
    public static char turn = 'w';

    public static void main(String[] args) {
        return;
    }

    // TODO code application logic here
    public static void buttonMain(Board b, int x, int y, int newx, int newy, Set<Coordinate> invalid) {
        Set<Coordinate> possible = new HashSet<>();
        int over = 0;
        Board old;
        switch (turn) {
            case 'w':
                if (b.board[x][y] == null || b.board[x][y].race != turn || b.board[x][y].displayMoves(b).isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No white piece there", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                // It's not empty
                possible = b.board[x][y].displayMoves(b);
                turn = 'b';
                old = new Board(b);
                over = moves(b, x, y, possible, newx, newy, invalid); // TODO: Change the moves function
                if (over != 0) {
                    break;
                }
                // This is not a checkmate	
                if (((King) b.board[b.kingPos[1].x][b.kingPos[1].y]).checkCheck) {
                    // This means the move was invalid
                    b = copy(b, old);
                    JOptionPane.showMessageDialog(null, "Move was invalid", "Illegal Move", JOptionPane.ERROR_MESSAGE);
                    turn = 'w';
                    return;
                }
                break; // Break out of the switch case

            case 'b':
                if (b.board[x][y] == null || b.board[x][y].race != turn || b.board[x][y].displayMoves(b).isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No black piece there", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                // It's not empty
                possible = b.board[x][y].displayMoves(b);
                turn = 'w';
                old = new Board(b);
                over = moves(b, x, y, possible, newx, newy, invalid); // TODO: Change the moves function
                if (over != 0) {
                    break;
                }
                // This is not a checkmate
                if (((King) b.board[b.kingPos[0].x][b.kingPos[0].y]).checkCheck) {
                    // This means the move was invalid
                    b = copy(b, old);
                    JOptionPane.showMessageDialog(null, "Move was invalid", "Illegal Move", JOptionPane.ERROR_MESSAGE);
                    turn = 'b';
                    return;
                }
                break; // Break out of the switch case
        }

        if (over == 1) {
            JOptionPane.showMessageDialog(null, "White wins.");
            System.exit(0);
        } else if (over == 2) {
            JOptionPane.showMessageDialog(null, "Black wins.");
            System.exit(0);
        } else if (over == 3) {
            JOptionPane.showMessageDialog(null, "Stalemate.");
            System.exit(0);
        }
    }

    public static Board copy(Board b, Board old) {
        b.black = new HashSet<Coordinate>(old.black);
        b.white = new HashSet<Coordinate>(old.white);
        b.kingPos = new Coordinate[2];
        b.kingPos[0] = old.kingPos[0];
        b.kingPos[1] = old.kingPos[1];
        b.board = new Piece[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                if (old.board[i][j] instanceof King) {
                    b.board[i][j] = new King((King) old.board[i][j]);
                } else if (old.board[i][j] instanceof Queen) {
                    b.board[i][j] = new Queen((Queen) old.board[i][j]);
                } else if (old.board[i][j] instanceof Bishop) {
                    b.board[i][j] = new Bishop((Bishop) old.board[i][j]);
                } else if (old.board[i][j] instanceof Knight) {
                    b.board[i][j] = new Knight((Knight) old.board[i][j]);
                } else if (old.board[i][j] instanceof Rook) {
                    b.board[i][j] = new Rook((Rook) old.board[i][j]);
                } else if (old.board[i][j] instanceof Pawn) {
                    b.board[i][j] = new Pawn((Pawn) old.board[i][j]);
                }
            }
        }
        return b;
    }

    public static int moves(Board b, int x, int y, Set<Coordinate> possible, int newx, int newy, Set<Coordinate> invalid) {
        int a = 1;
        while (a != 0) {
            if (b.board[x][y] instanceof King) {
                // This part is only to move the Rook
                if (newy - y == 2) {
                    b.board[x][5] = b.board[x][7];
                    b.board[x][5].y = 5;
                    b.board[x][7] = null;
                    switch (x) {
                        case 0:
                            b.black.remove(new Coordinate(0, 7));
                            b.black.add(new Coordinate(0, 5));
                            break;

                        case 7:
                            b.white.remove(new Coordinate(7, 7));
                            b.white.add(new Coordinate(7, 5));
                            break;
                    }
                } else if (y - newy == 2) {
                    b.board[x][3] = b.board[x][0];
                    b.board[x][3].y = 3;
                    b.board[x][0] = null;
                    switch (x) {
                        case 0:
                            b.black.remove(new Coordinate(0, 0));
                            b.black.add(new Coordinate(0, 3));
                            break;

                        case 7:
                            b.white.remove(new Coordinate(7, 0));
                            b.white.add(new Coordinate(7, 3));
                            break;
                    }
                }

                // To add the new King positions to the array
                switch (b.board[x][y].race) {
                    case 'b':
                        b.kingPos[0] = new Coordinate(newx, newy);
                        break;
                    case 'w':
                        b.kingPos[1] = new Coordinate(newx, newy);
                        break;
                }
            }

            //if(possible.contains(new Coordinate(newx, newy))) {
            b.board[newx][newy] = b.board[x][y]; // Changes the position of the old piece on the board
            b.board[newx][newy].x = newx;
            b.board[newx][newy].y = newy;
            b.board[newx][newy].move++;

            if (b.board[x][y].race == 'b') {
                b.black.remove(new Coordinate(x, y));
                b.black.add(new Coordinate(newx, newy));
                b.white.remove(new Coordinate(newx, newy));
            } else {
                b.white.remove(new Coordinate(x, y));
                b.white.add(new Coordinate(newx, newy));
                b.black.remove(new Coordinate(newx, newy));
            }

            if (b.board[newx][newy] instanceof Pawn) {
                ((Pawn) b.board[newx][newy]).convertIfPossible(b);
            }

            b.board[x][y] = null; // Move is complete

            Set<Coordinate> moves = new HashSet<Coordinate>();
            for (Coordinate each : b.black) {
                if (b.board[each.x][each.y] instanceof King) {
                    continue;
                }
                if (b.board[each.x][each.y] instanceof Pawn) {
                    moves.addAll(((Pawn) (b.board[each.x][each.y])).killableMoves(b));
                } else {
                    moves.addAll(b.board[each.x][each.y].displayMoves(b));
                }
            }
            // Now we have all the moves of the black pieces
            if (moves.contains(b.kingPos[1])) {
                JOptionPane.showMessageDialog(null, "Check", "", JOptionPane.WARNING_MESSAGE);
                ((King) b.board[b.kingPos[1].x][b.kingPos[1].y]).checkCheck = true;
            } else {
                ((King) b.board[b.kingPos[1].x][b.kingPos[1].y]).checkCheck = false;
            }

            moves = new HashSet<Coordinate>();

            for (Coordinate each : b.white) {
                if (b.board[each.x][each.y] instanceof King) {
                    continue;
                }
                if (b.board[each.x][each.y] instanceof Pawn) {
                    moves.addAll(((Pawn) (b.board[each.x][each.y])).killableMoves(b));
                } else {
                    moves.addAll(b.board[each.x][each.y].displayMoves(b));
                }
            }
            // Now we have all the moves of the white pieces
            if (moves.contains(b.kingPos[0])) {
                JOptionPane.showMessageDialog(null, "Check", "", JOptionPane.WARNING_MESSAGE);
                ((King) b.board[b.kingPos[0].x][b.kingPos[0].y]).checkCheck = true;
            } else {
                ((King) b.board[b.kingPos[0].x][b.kingPos[0].y]).checkCheck = false;
            }

            a = 0;
            //}
//		else 
//                    System.out.print("");
        }
        return gameNotOver(b, invalid);
    }

    private static List<Coordinate> getKiller(Board b, char race) {
        List<Coordinate> killers = new ArrayList<>();
        Set<Coordinate> opponentPieces = (race == 'b') ? b.white : b.black;
        Coordinate king = (race == 'b') ? b.kingPos[0] : b.kingPos[1];
        Set<Coordinate> opponentMoves = new HashSet<Coordinate>();

        for (Coordinate each : opponentPieces) {
            if (b.board[each.x][each.y] instanceof King) {
                continue;
            }
            if (b.board[each.x][each.y] instanceof Pawn) {
                if ((((Pawn) (b.board[each.x][each.y])).killableMoves(b)).contains(king)) {
                    killers.add(each);
                }
            } else {
                if (b.board[each.x][each.y].displayMoves(b).contains(king)) {
                    killers.add(each);
                }
            }
        }
        return killers;
    }

    // TODO: Add stalemate and stuff
    private static int gameNotOver(Board b, Set<Coordinate> invalid) {
        // For Checkmate: 3 parts
        // Part 1: King is in check and has no available moves

        // This if checks if the black king is in checkmate
        if ((((King) b.board[b.kingPos[0].x][b.kingPos[0].y]).checkCheck) && b.board[b.kingPos[0].x][b.kingPos[0].y].displayMoves(b).size() == 0) {
            // Part 2: Some piece can kill the checking piece
            List<Coordinate> killers = new ArrayList<Coordinate>(getKiller(b, 'b'));
            Set<Coordinate> betweenMoves = new HashSet<Coordinate>();
            // This is if the King can't move and there is more than one piece attacking the King
            // In this case, the King is ded cos he can't move and no other piece can kill or block both
            if (killers.size() > 1) {
                return 1;
            }

            // Now check if the killer can be killed
            for (Coordinate each : b.black) {
                if (b.board[each.x][each.y] instanceof King) {
                    continue;
                }
                if (b.board[each.x][each.y] instanceof Pawn) {
                    if (((Pawn) b.board[each.x][each.y]).killableMoves(b).contains(killers.get(0))) {
                        return 0;
                    }
                } else {
                    if ((b.board[each.x][each.y]).displayMoves(b).contains(killers.get(0))) {
                        return 0;
                    }
                }
            }
            // Part 3: Some piece obstructs the checking piece
            if (b.board[killers.get(0).x][killers.get(0).y] instanceof Bishop) {
                betweenMoves = ((Bishop) b.board[killers.get(0).x][killers.get(0).y]).getBetween(b.kingPos[0]);
            } else if (b.board[killers.get(0).x][killers.get(0).y] instanceof Rook) {
                betweenMoves = ((Rook) b.board[killers.get(0).x][killers.get(0).y]).getBetween(b.kingPos[0]);
            } else if (b.board[killers.get(0).x][killers.get(0).y] instanceof Queen) {
                betweenMoves = ((Queen) b.board[killers.get(0).x][killers.get(0).y]).getBetween(b.kingPos[0]);
            } else {
                return 1;
            }

            Set<Coordinate> allMoves = new HashSet<>();
            for (Coordinate each : b.black) {
                if (b.board[each.x][each.y] instanceof King) {
                    continue;
                } else {
                    allMoves.addAll(b.board[each.x][each.y].displayMoves(b));
                }
            }
            // Checks if the Coordinate is present in betweenMoves
            for (Coordinate each : betweenMoves) {
                if (allMoves.contains(each)) {
                    return 0;
                }
            }
            return 1;
        }

        //This if checks if the white king is in checkmate
        if ((((King) b.board[b.kingPos[1].x][b.kingPos[1].y]).checkCheck) && b.board[b.kingPos[1].x][b.kingPos[1].y].displayMoves(b).size() == 0) {
            // Part 2: Some piece can kill the checking piece
            List<Coordinate> killers = new ArrayList<Coordinate>(getKiller(b, 'w'));
            Set<Coordinate> betweenMoves = new HashSet<Coordinate>();
            // This is if the King can't move and there is more than one piece attacking the King
            // In this case, the King is ded cos he can't move and no other piece can kill or block both
            if (killers.size() > 1) {
                return 2;
            }

            // Now check if the killer can be killed
            for (Coordinate each : b.white) {
                if (b.board[each.x][each.y] instanceof King) {
                    continue;
                }
                if (b.board[each.x][each.y] instanceof Pawn) {
                    if (((Pawn) b.board[each.x][each.y]).killableMoves(b).contains(killers.get(0))) {
                        return 0;
                    }
                } else {
                    if ((b.board[each.x][each.y]).displayMoves(b).contains(killers.get(0))) {
                        return 0;
                    }
                }
            }
            // Part 3: Some piece obstructs the checking piece
            if (b.board[killers.get(0).x][killers.get(0).y] instanceof Bishop) {
                betweenMoves = ((Bishop) b.board[killers.get(0).x][killers.get(0).y]).getBetween(b.kingPos[1]);
            } else if (b.board[killers.get(0).x][killers.get(0).y] instanceof Rook) {
                betweenMoves = ((Rook) b.board[killers.get(0).x][killers.get(0).y]).getBetween(b.kingPos[1]);
            } else if (b.board[killers.get(0).x][killers.get(0).y] instanceof Queen) {
                betweenMoves = ((Queen) b.board[killers.get(0).x][killers.get(0).y]).getBetween(b.kingPos[1]);
            } else {
                return 2;
            }

            Set<Coordinate> allMoves = new HashSet<>();
            for (Coordinate each : b.white) {
                if (b.board[each.x][each.y] instanceof King) {
                    continue;
                } else {
                    allMoves.addAll(b.board[each.x][each.y].displayMoves(b));
                }
            }
            // Checks if the Coordinate is present in betweenMoves
            for (Coordinate each : betweenMoves) {
                if (allMoves.contains(each)) {
                    return 0;
                }
            }

            return 2;
        }
        return stalemateCheck(b, invalid);
    }

    public static Set<Coordinate> kingNoKill(Board b, int x, int y) {
        Set<Coordinate> invalid = new HashSet<Coordinate>();

        if (b.board[x][y] instanceof King) {
            return invalid;
        }

        for (Coordinate each1 : b.board[x][y].displayMoves(b)) {
            char race = (b.board[x][y].race == 'b') ? 'b' : 'w';
            Board fake = new Board();
            fake = copy(fake, b);
            fake.board[each1.x][each1.y] = fake.board[x][y];
            fake.board[x][y].x = each1.x;
            fake.board[x][y].y = each1.y;

            switch (fake.board[x][y].race) {
                case 'b':
                    fake.black.remove(new Coordinate(x, y));
                    fake.black.add(new Coordinate(each1.x, each1.y));
                    break;
                case 'w':
                    fake.white.remove(new Coordinate(x, y));
                    fake.white.add(new Coordinate(each1.x, each1.y));
                    break;
            }

            fake.board[x][y] = null;

            Set<Coordinate> moves = new HashSet<Coordinate>();
            for (Coordinate each : fake.black) {
                if (fake.board[each.x][each.y] instanceof King) {
                    continue;
                }
                if (fake.board[each.x][each.y] instanceof Pawn) {
                    moves.addAll(((Pawn) (fake.board[each.x][each.y])).killableMoves(fake));
                } else {
                    moves.addAll(fake.board[each.x][each.y].displayMoves(fake));
                }
            }
            // Now we have all the moves of the black pieces

            if (moves.contains(fake.kingPos[1])) {
                ((King) fake.board[fake.kingPos[1].x][fake.kingPos[1].y]).checkCheck = true;
            } else {
                if (fake.board[fake.kingPos[1].x][fake.kingPos[1].y] instanceof King) {
                    ((King) fake.board[fake.kingPos[1].x][fake.kingPos[1].y]).checkCheck = false;
                }
            }

            moves = new HashSet<Coordinate>();

            for (Coordinate each : fake.white) {
                if (fake.board[each.x][each.y] instanceof King) {
                    continue;
                }
                if (fake.board[each.x][each.y] instanceof Pawn) {
                    moves.addAll(((Pawn) (fake.board[each.x][each.y])).killableMoves(fake));
                } else {
                    moves.addAll(fake.board[each.x][each.y].displayMoves(fake));
                }
            }
            // Now we have all the moves of the white pieces

            if (moves.contains(fake.kingPos[0])) {
                ((King) fake.board[fake.kingPos[0].x][fake.kingPos[0].y]).checkCheck = true;
            } else {
                if (fake.board[fake.kingPos[0].x][fake.kingPos[0].y] instanceof King) {
                    ((King) fake.board[fake.kingPos[0].x][fake.kingPos[0].y]).checkCheck = false;
                }
            }

            switch (race) {
                case 'b':
                    if (((King) fake.board[fake.kingPos[0].x][fake.kingPos[0].y]).checkCheck) {
                        invalid.add(each1);
                    }
                    break;
                case 'w':
                    if (((King) fake.board[fake.kingPos[1].x][fake.kingPos[1].y]).checkCheck) {
                        invalid.add(each1);
                    }
                    break;
            }
        }
        return invalid;
    }

    public static int stalemateCheck(Board b, Set<Coordinate> invalid) {
        Set<Coordinate> allMoves = new HashSet<Coordinate>();
        for (Coordinate each : b.black) {
            allMoves.addAll(b.board[each.x][each.y].displayMoves(b));
            allMoves.removeAll(kingNoKill(b, each.x, each.y));
        }
        if (allMoves.size() == 0) {
            return 3;
        }

        allMoves = new HashSet<>();
        for (Coordinate each : b.white) {
            allMoves.addAll(b.board[each.x][each.y].displayMoves(b));
            allMoves.removeAll(kingNoKill(b, each.x, each.y));
        }
        if (allMoves.size() == 0) {
            return 3;
        }

        return 0;
    }
}
