/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quarantinechess;

import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Set;
import javax.swing.*;

/**
 *
 * @author Vatsav and Jaymin
 */
public class GameBoard extends JFrame {
    private JButton[][] board = new JButton[8][8];
    private Container cont;
    Board b;
    int irow, icol;
    boolean firstMove;
    
    Icon bpawn = new ImageIcon("pictures\\bpawn.png");
    Icon brook = new ImageIcon("pictures\\brook.png");
    Icon bknight = new ImageIcon("pictures\\bknight.png");
    Icon bbishop = new ImageIcon("pictures\\bbishop.png");
    Icon bking = new ImageIcon("pictures\\bking.png");
    Icon bqueen = new ImageIcon("pictures\\bqueen.png");
    Icon wpawn = new ImageIcon("pictures\\wpawn.png");
    Icon wrook = new ImageIcon("pictures\\wrook.png");
    Icon wknight = new ImageIcon("pictures\\wknight.png");
    Icon wbishop = new ImageIcon("pictures\\wbishop.png");
    Icon wking = new ImageIcon("pictures\\wking.png");
    Icon wqueen = new ImageIcon("pictures\\wqueen.png");

    /**
     * Creates new form Test
     */
    public GameBoard() {
        super("Quarantine Chess");
        cont = getContentPane();
        cont.setLayout(new GridLayout(8,8));
        
        irow = 0;
        icol = 0;
        
//        Color white = new Color(225, 200, 160);
//        Color black = new Color(164, 110, 57); 
        Color white = new Color(180, 180, 255);
        Color black = new Color(90, 90, 255); 
        
        b = new Board();
        
        firstMove = true;
        
        // Create a Button Handler for the Actions of all the Buttons
        ButtonHandler bh = new ButtonHandler();
        
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                board[i][j] = new JButton();
                cont.add(board[i][j]);
                if((i+j)%2 == 0)
                    board[i][j].setBackground(white);
                else
                    board[i][j].setBackground(black);
                setImages(i, j);
                board[i][j].addActionListener(bh);
            }
        }
        
        setSize(600,600);
        setVisible(true);
        initComponents();
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 589, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 565, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GameBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameBoard().setVisible(true);
            }
        });
    }
    
    private void setImages(int i, int j){
        if(b.board[i][j] == null){
            board[i][j].setIcon(null);
            board[i][j].setDisabledIcon(null);
        }
        if(b.board[i][j] instanceof Pawn){
            if(b.board[i][j].race == 'b'){
                board[i][j].setIcon(bpawn);
                board[i][j].setDisabledIcon(bpawn);
            }
            else{
                board[i][j].setIcon(wpawn);
                board[i][j].setDisabledIcon(wpawn);
            }
        }
        else if(b.board[i][j] instanceof Bishop){
            if(b.board[i][j].race == 'b'){
                board[i][j].setIcon(bbishop);
                board[i][j].setDisabledIcon(bbishop);
            }
            else{
                board[i][j].setIcon(wbishop);
                board[i][j].setDisabledIcon(wbishop);
            }
        }
        else if(b.board[i][j] instanceof King){
            if(b.board[i][j].race == 'b'){
                board[i][j].setIcon(bking);
                board[i][j].setDisabledIcon(bking);
            }
            else{
                board[i][j].setIcon(wking);
                board[i][j].setDisabledIcon(wking);
            }
        }
        else if(b.board[i][j] instanceof Knight){
            if(b.board[i][j].race == 'b'){
                board[i][j].setIcon(bknight);
                board[i][j].setDisabledIcon(bknight);
            }
            else{
                board[i][j].setIcon(wknight);
                board[i][j].setDisabledIcon(wknight);
            }
        }
        else if(b.board[i][j] instanceof Rook){
            if(b.board[i][j].race == 'b'){
                board[i][j].setIcon(brook);
                board[i][j].setDisabledIcon(brook);
            }
            else{
                board[i][j].setIcon(wrook);
                board[i][j].setDisabledIcon(wrook);
            }
        }
        else if(b.board[i][j] instanceof Queen){
            if(b.board[i][j].race == 'b'){
                board[i][j].setIcon(bqueen);
                board[i][j].setDisabledIcon(bqueen);
            }
            else{
                board[i][j].setIcon(wqueen);
                board[i][j].setDisabledIcon(wqueen);
            }
        }
    }

    private class ButtonHandler implements ActionListener{
        public void actionPerformed(ActionEvent e){
            Object o = e.getSource();
            int row = 0, col = 0;
            for(int i = 0; i < 8; i++){
                for(int j = 0; j < 8; j++){
                    if(o == board[i][j]){
                        row = i;
                        col = j;
                    }
                    else
                        board[i][j].setEnabled(false);
                }
            }
            
            // Selects piece and does something based on click number
            if(firstMove){
                irow = row;
                icol = col;
                //JOptionPane.showMessageDialog(null, "Turn " + QuarantineChess.turn);
                Set<Coordinate> moves = new HashSet<Coordinate>();
                if(b.board[row][col] != null)
                    moves = b.board[row][col].displayMoves(b);
                else{
                    JOptionPane.showMessageDialog(null, "No piece there.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    for(int i = 0; i < 8; i++){
                        for(int j = 0; j < 8; j++){
                            board[i][j].setEnabled(true);
                        }
                    }
                    return;
                }
                
                moves.removeAll(MainGame.kingNoKill(b, row, col));
                
                for(Coordinate each : moves)
                    board[each.x][each.y].setEnabled(true);
            }
            
            
            // Turn won't change because the same button is pressed twice
            else if(row == irow && col == icol){
                //JOptionPane.showMessageDialog(null, "Same button twice");
                for(int i = 0; i < 8; i++){
                    for(int j = 0; j < 8; j++){
                        board[i][j].setEnabled(true);
                    }
                }
            }
            
            else{
                MainGame.buttonMain(b, irow, icol, row, col, MainGame.kingNoKill(b, irow, icol));
                for(int i = 0; i < 8; i++){
                    for(int j = 0; j < 8; j++){
                        setImages(i, j);
                        board[i][j].setEnabled(true);
                    }
                }
            }
            firstMove = !firstMove;
        }
    }
    
    

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
