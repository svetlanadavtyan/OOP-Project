package ui;

import core.Set;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class GameFrame extends JFrame {
    private Set game;
    private GamePanel gamePanel;
    private PlayerPanel playerPanel;
    protected static int currentPlayerIndex;

    public GameFrame(int numberOfPlayers) {
        super("Set Card Game");
        game = new Set(numberOfPlayers);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        gamePanel = new GamePanel(game);
        playerPanel = new PlayerPanel();

        add(gamePanel, BorderLayout.CENTER);
        add(playerPanel, BorderLayout.EAST);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        updateGamePanel();
        while (!game.gameEnded()) {
            setCurrentPlayer(currentPlayerIndex);
            setPlayerScore(game.getPlayerScore(currentPlayerIndex));
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        displayWinner(game.getWinners());
    }

    public void updateGamePanel() {
        gamePanel.updateTable();
    }

    public void setCurrentPlayer(int playerIndex) {
        playerPanel.setCurrentPlayer(playerIndex);
        repaint();
    }

    public void setPlayerScore(int score) {
        playerPanel.setScore(score);
        repaint();
    }

    public void displayWinner(String winner) {
        playerPanel.setWinner(winner);
    }
}
