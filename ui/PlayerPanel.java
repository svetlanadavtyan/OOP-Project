package ui;

import javax.swing.*;
import java.awt.*;

public class PlayerPanel extends JPanel {
    private JLabel currentPlayerLabel;
    private JLabel scoreLabel;

    public PlayerPanel() {
        setPreferredSize(new Dimension(200, 600));
        setLayout(new BorderLayout());

        currentPlayerLabel = new JLabel("Current Player: ");
        scoreLabel = new JLabel("Score: ");

        add(currentPlayerLabel, BorderLayout.NORTH);
        add(scoreLabel, BorderLayout.CENTER);
    }

    public void setCurrentPlayer(int playerIndex) {
        currentPlayerLabel.setText("Current Player: " + (playerIndex + 1));
    }

    public void setScore(int score) {
        scoreLabel.setText("Score: " + score);
    }

    public void setWinner(String winner) {
        currentPlayerLabel.setText("Winner: " + winner);
        scoreLabel.setText("");
    }
}
