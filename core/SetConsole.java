package core;

import java.util.ArrayList;
import java.util.Scanner;

public class SetConsole {
    private Set game;

    public void play() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please input the number of players:");
        int numberOfPlayers = sc.nextInt();
        this.game = new Set(numberOfPlayers);
        print();
        int playerIndex = 0;
        while (!game.gameEnded()) {
            System.out.println("Player " + (playerIndex + 1) + " turn:");
            int i = sc.nextInt();
            int j = sc.nextInt();
            int k = sc.nextInt();
            game.playerMove(playerIndex, i, j, k);
            playerIndex = (playerIndex + 1) % numberOfPlayers;
            print(); //display cards
            game.displayScoreboard(); //display scoreboard
        }

        game.displayScoreboard();
        game.displayWinners();
    }

    public void print() {
        //initial way to print the Set game cards on the table
        ArrayList<Card> cards = game.getTableCards();
        for (int i = 0; i < cards.size(); ++i) {
            System.out.println("Card " + i + ": " + cards.get(i).getColor() + ", " + cards.get(i).getShape() + ", " + cards.get(i).getShading() + ", " + cards.get(i).getNumber());
        }
    }
}