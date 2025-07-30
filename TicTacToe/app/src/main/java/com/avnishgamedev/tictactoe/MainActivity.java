package com.avnishgamedev.tictactoe;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    // Represents the board state: 0 for empty, 1 for X, 2 for O
    private final int[][] board = new int[3][3];
    private boolean playerXTurn = true; // True if it's player X's turn, false for player O
    private int playerXScore = 0;
    private int playerOScore = 0;
    private int movesCount = 0; // To check for a draw
    private boolean gameActive = true; // To stop the game after a win or draw

    private final ImageView[][] imageViews = new ImageView[3][3];
    private TextView tvPlayerXScore, tvPlayerOScore, tvTurnStatus;
    private Button btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        tvPlayerXScore = findViewById(R.id.tvPlayerX);
        tvPlayerOScore = findViewById(R.id.tvPlayerO);
        tvTurnStatus = findViewById(R.id.tvTurn);
        btnReset = findViewById(R.id.btnReset);

        // Initialize ImageViews and set OnClickListener
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String id = "iv" + i + j;
                int resId = getResources().getIdentifier(id, "id", getPackageName());
                imageViews[i][j] = findViewById(resId);
                imageViews[i][j].setOnClickListener(this);
            }
        }

        btnReset.setOnClickListener(v -> resetGame());

        updateScoreDisplay();
        updateTurnStatus();
    }

    @Override
    public void onClick(View v) {
        if (!gameActive) {
            return;
        }

        ImageView clickedImage = (ImageView) v;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (clickedImage.getId() == imageViews[i][j].getId()) {
                    if (board[i][j] == 0) { // Check if the cell is empty
                        board[i][j] = playerXTurn ? 1 : 2;
                        clickedImage.setImageResource(playerXTurn ? R.drawable.ic_x : R.drawable.ic_o);
                        movesCount++;

                        if (checkForWin()) {
                            gameActive = false;
                            String winner = playerXTurn ? "Player X" : "Player O";
                            if (playerXTurn) {
                                playerXScore++;
                            } else {
                                playerOScore++;
                            }
                            updateScoreDisplay();
                            updateTurnStatus(); // Update status to show winner
                            highlightWinningCells();
                            Toast.makeText(this, winner + " wins!", Toast.LENGTH_LONG).show();
                        } else if (movesCount == 9) { // It's a draw
                            gameActive = false;
                            updateTurnStatus();
                            Toast.makeText(this, "It's a Draw!", Toast.LENGTH_LONG).show();
                        } else {
                            playerXTurn = !playerXTurn; // Switch turns
                            updateTurnStatus();
                        }
                    }
                    return; // Exit once the clicked cell is found and processed
                }
            }
        }
    }

    private boolean checkForWin() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != 0 && board[i][0] == board[i][1] && board[i][0] == board[i][2]) {
                return true;
            }
        }
        // Check columns
        for (int j = 0; j < 3; j++) {
            if (board[0][j] != 0 && board[0][j] == board[1][j] && board[0][j] == board[2][j]) {
                return true;
            }
        }
        // Check diagonals
        if (board[0][0] != 0 && board[0][0] == board[1][1] && board[0][0] == board[2][2]) {
            return true;
        }
        return board[0][2] != 0 && board[0][2] == board[1][1] && board[0][2] == board[2][0];
    }

    private void updateScoreDisplay() {
        tvPlayerXScore.setText("Player X: " + playerXScore);
        tvPlayerOScore.setText("Player O: " + playerOScore);
    }

    private void updateTurnStatus() {
        if (gameActive) {
            tvTurnStatus.setText(playerXTurn ? "Turn: Player X" : "Turn: Player O");
        } else {
            // After game ends, determine the final status text
            if (checkForWin()) {
                tvTurnStatus.setText(playerXTurn ? "Player X Wins!" : "Player O Wins!");
            } else {
                tvTurnStatus.setText("It's a Draw!");
            }
        }
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = 0;
                imageViews[i][j].setImageResource(0); // Clear the image
                imageViews[i][j].setBackgroundColor(Color.TRANSPARENT); // Use android.graphics.Color
            }
        }
        movesCount = 0;
        gameActive = true;
        playerXTurn = true;
        updateTurnStatus();
    }

    private void resetGame() {
        updateScoreDisplay();
        resetBoard();
    }

    private void highlightWinningCells() {
        // This method is called after checkForWin is true, so we find the winning line
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != 0 && board[i][0] == board[i][1] && board[i][0] == board[i][2]) {
                imageViews[i][0].setBackgroundColor(Color.LTGRAY);
                imageViews[i][1].setBackgroundColor(Color.LTGRAY);
                imageViews[i][2].setBackgroundColor(Color.LTGRAY);
                return;
            }
        }
        // Check columns
        for (int j = 0; j < 3; j++) {
            if (board[0][j] != 0 && board[0][j] == board[1][j] && board[0][j] == board[2][j]) {
                imageViews[0][j].setBackgroundColor(Color.LTGRAY);
                imageViews[1][j].setBackgroundColor(Color.LTGRAY);
                imageViews[2][j].setBackgroundColor(Color.LTGRAY);
                return;
            }
        }
        // Check diagonals
        if (board[0][0] != 0 && board[0][0] == board[1][1] && board[0][0] == board[2][2]) {
            imageViews[0][0].setBackgroundColor(Color.LTGRAY);
            imageViews[1][1].setBackgroundColor(Color.LTGRAY);
            imageViews[2][2].setBackgroundColor(Color.LTGRAY);
        } else if (board[0][2] != 0 && board[0][2] == board[1][1] && board[0][2] == board[2][0]) {
            imageViews[0][2].setBackgroundColor(Color.LTGRAY);
            imageViews[1][1].setBackgroundColor(Color.LTGRAY);
            imageViews[2][0].setBackgroundColor(Color.LTGRAY);
        }
    }
}