package com.example.tictactoefx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Controller {


    // github.com/EmirXK
    // Broke the DRY rule like a billion times but \_/=(O_o)=\_/ not the worst spaghetti code I've written


    static int[][] game = new int[3][3];

    static int player = 1; // 1:X 2:O
    
    static int scoreX = 0;
    static int scoreY = 0;
    
    static int totalNumOfMoves = 0;

    @FXML
    private Label playerLbl;

    @FXML
    private Label lbl;

    @FXML
    private Button BL;

    @FXML
    private Button BR;

    @FXML
    private Button D;

    @FXML
    private Button L;

    @FXML
    private Button M;

    @FXML
    private Button R;

    @FXML
    private Button TL;

    @FXML
    private Button TR;

    @FXML
    private Button U;

    @FXML
    void bottomLeft() {
        game[2][0] = player;
        BL .setText( (player == 1) ? "X" : "O" );
        BL.setDisable(true);
        flipPlayer();
        totalNumOfMoves++;
        checkWinner();
    }

    @FXML
    void bottomRight() {
        game[2][2] = player;
        BR .setText( (player == 1) ? "X" : "O" );
        BR.setDisable(true);
        flipPlayer();
        totalNumOfMoves++;
        checkWinner();
    }

    @FXML
    void down() {
        game[2][1] = player;
        D .setText( (player == 1) ? "X" : "O" );
        D.setDisable(true);
        flipPlayer();
        totalNumOfMoves++;
        checkWinner();
    }

    @FXML
    void left() {
        game[1][0] = player;
        L .setText( (player == 1) ? "X" : "O" );
        L.setDisable(true);
        totalNumOfMoves++;
        flipPlayer();
        checkWinner();
    }

    @FXML
    void middle() {
        game[1][1] = player;
        M .setText( (player == 1) ? "X" : "O" );
        M.setDisable(true);
        flipPlayer();
        totalNumOfMoves++;
        checkWinner();
    }

    @FXML
    void right() {
        game[1][2] = player;
        R .setText( (player == 1) ? "X" : "O" );
        R.setDisable(true);
        totalNumOfMoves++;
        flipPlayer();
        checkWinner();
    }

    @FXML
    void topLeft() {
        game[0][0] = player;
        TL .setText( (player == 1) ? "X" : "O" );
        TL.setDisable(true);
        totalNumOfMoves++;
        flipPlayer();
        checkWinner();
    }

    @FXML
    void topRight() {
        game[0][2] = player;
        TR.setText( (player == 1) ? "X" : "O" );
        TR.setDisable(true);
        totalNumOfMoves++;
        flipPlayer();
        checkWinner();
    }

    @FXML
    void up() {
        game[0][1] = player;
        U .setText( (player == 1) ? "X" : "O" );
        U.setDisable(true);
        totalNumOfMoves++;
        flipPlayer();
        checkWinner();
    }


    @FXML
    void restart() {
        TL.setDisable(false);
        U.setDisable(false);
        TR.setDisable(false);
        L.setDisable(false);
        M.setDisable(false);
        BL.setDisable(false);
        BR.setDisable(false);
        D.setDisable(false);
        R.setDisable(false);
        TL.setText("");
        U.setText("");
        TR.setText("");
        L.setText("");
        M.setText("");
        BL.setText("");
        BR.setText("");
        D.setText("");
        R.setText("");
        TL.setText("");
        lbl.setText("Tic Tac Toe");
        playerLbl.setText("Player: X");
        player = 1;
        totalNumOfMoves = 0;
        for (int i=0; i< game.length; i++)
            for (int j=0; j< game.length;j++)
                game[i][j] = 0;
    }



    void flipPlayer() {
        player = (player == 1) ? 2 : 1;
        playerLbl.setText( (player == 1) ? "Player: X" : "Player: O" );
    }



    void checkWinner() {
        int countX;
        int countY;

        boolean win = false;

        // don't bother checking for winners if that is impossible.
        if (totalNumOfMoves > 4)
            for (int i=0; i< game.length; i++) {
                // reset every iteration
                countX = 0;
                countY = 0;
                // check rows
                for (int j=0; j<game.length; j++) {
                    if (game[i][j] == 1) countX++;
                    else if (game[i][j] == 2) countY++;
                }
                if (countX == 3) {
                    endgame(1);
                    win = true;
                    break;
                }
                else if (countY == 3) {
                    endgame(2);
                    win = true;
                    break;
                }

                countX = 0;
                countY = 0;

                //check cols
                for (int[] ints : game) {
                    if (ints[i] == 1) countX++;
                    else if (ints[i] == 2) countY++;
                }
                if (countX == 3) {
                    endgame(1);
                    win = true;
                    break;
                }
                else if (countY == 3) {
                    endgame(2);
                    win = true;
                    break;
                }

                countX = 0;
                countY = 0;

                //check diagonals
                for (int j=0; j<game.length; j++) {
                    for (int k = 0; k< game.length; k++) {
                        if (j == k) {
                            if (game[j][k] == 1) countX++;
                            else if (game[j][k] == 2) countY++;
                        }
                    }
                }
                if (countX == 3) {
                    endgame(1);
                    win = true;
                    break;
                }
                else if (countY == 3) {
                    endgame(2);
                    win = true;
                    break;
                }

                countX = 0;
                countY = 0;

                //reverse diagonal
                for (int j=0; j<game.length; j++) {
                    for (int k = 0; k< game.length; k++) {
                        if (j == 2-k) {
                            if (game[j][k] == 1) countX++;
                            else if (game[j][k] == 2) countY++;
                        }
                    }
                }

                if (countX == 3) {
                    endgame(1);
                    win = true;
                    break;
                }
                else if (countY == 3) {
                    endgame(2);
                    win = true;
                    break;
                }
            }
        
        // it's a draw!
        if (!win && totalNumOfMoves == 9) {
            endgame(3);
        }

    }
    
     void endgame(int i) {
        if (i == 1) scoreX++;
        else if (i == 2) scoreY++;
        playerLbl.setText("");
        if (i == 3)  {
            lbl.setText("It's a Draw! Score: X: %d, O: %d".formatted(scoreX,scoreY));
        }
        else lbl.setText( (i == 1) ? "X Wins! Score: X: %d, O: %d".formatted(scoreX,scoreY) : "O Wins! Score: X: %d, O: %d".formatted(scoreX,scoreY) );

        TL.setDisable(true);
        U.setDisable(true);
        TR.setDisable(true);
        L.setDisable(true);
        M.setDisable(true);
        BL.setDisable(true);
        BR.setDisable(true);
        D.setDisable(true);
        R.setDisable(true);
    }

}
