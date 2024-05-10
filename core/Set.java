package core;

import java.util.ArrayList;
import java.util.Collections;

public class Set {
    public static final int DECK_SIZE = 81;
    public static int TABLE_SIZE = 12;
    private int numberOfPlayers;
    private Player[] players;
    private static ArrayList<Card> cards;
    private ArrayList<Card> deck;
    private ArrayList<Card> table;
    private boolean endGame;

    public Set(int numberOfPlayers) {
        generateCards();
        layOutCards();
        this.numberOfPlayers = numberOfPlayers;
        this.players = new Player[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; ++i) {
            players[i] = new Player();
        }
    }

    public ArrayList<Card> getTableCards() {
        return new ArrayList<>(table);
    }

//    public Player[] getPlayers() {
//        Player[] copy = new Player[numberOfPlayers];
//        for (int i = 0; i < numberOfPlayers; ++i) {
//            copy[i] = new Player(players[i]);
//        }
//        return copy;
//    }

    public int getPlayerScore(int playerIndex) {
        return players[playerIndex].getPoints();
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public String getWinners() {
        int maxPoints = 0;
        for (int i = 0; i < numberOfPlayers; ++i) {
            if (players[i].getPoints() > maxPoints) {
                maxPoints = players[i].getPoints();
            }
        }
        StringBuilder winners = new StringBuilder();
        for (int i = 0; i < numberOfPlayers; ++i) {
            if (players[i].getPoints() == maxPoints) {
                winners.append("Player ").append(i + 1).append(" ");
            }
        }
        return winners.toString();
    }

    public void playerMove(int playerIndex, int i, int j, int k) {
        if (isSet(i, j, k)) {
            addCardsUntilSet(i, j, k);
            players[playerIndex].addPoints();
        }
    }

    public boolean isSet(int i, int j, int k) {
        if (i == j || i == k || j == k) {
            return false;
        }
        Card card1 = table.get(i);
        Card card2 = table.get(j);
        Card card3 = table.get(k);
        boolean sameColor = card1.getColor() == card2.getColor() && card1.getColor() == card3.getColor();
        boolean sameShape = card1.getShape() == card2.getShape() && card1.getShape() == card3.getShape();
        boolean sameShading = card1.getShading() == card2.getShading() && card1.getShading() == card3.getShading();
        boolean sameNumber = card1.getNumber() == card2.getNumber() && card1.getNumber() == card3.getNumber();
        boolean differentColor = card1.getColor() != card2.getColor() && card1.getColor() != card3.getColor() && card2.getColor() != card3.getColor();
        boolean differentShape = card1.getShape() != card2.getShape() && card1.getShape() != card3.getShape() && card2.getShape() != card3.getShape();
        boolean differentShading = card1.getShading() != card2.getShading() && card1.getShading() != card3.getShading() && card2.getShading() != card3.getShading();
        boolean differentNumber = card1.getNumber() != card2.getNumber() && card1.getNumber() != card3.getNumber() && card2.getNumber() != card3.getNumber();
        return (sameNumber || differentNumber) && (sameShape || differentShape) && (sameShading || differentShading) && (sameColor || differentColor);
    }

    private static ArrayList<Card> generateCards() {
        cards = new ArrayList<>(DECK_SIZE);
        for (Card.Color color : Card.Color.values()) {
            for (Card.Shape shape : Card.Shape.values()) {
                for (Card.Shading shading : Card.Shading.values()) {
                    for (Card.Number number : Card.Number.values()) {
                        cards.add(new Card(color, shape, shading, number));
                    }
                }
            }
        }
        return cards;
    }

    private boolean tableHasSet() {
        for (int i = 0; i < table.size() - 2; ++i) {
            for (int j = i + 1; j < table.size() - 1; ++j) {
                for (int k = j + 1; k < table.size(); ++k) {
                    if (isSet(i, j, k)) {
                        // this print statement is to make the testing easier, and provide hints for the players if needed
                        System.out.println("Hint: " + (i + 1) + " " + (j + 1) + " " + (k + 1));
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void displayScoreboard() {
        System.out.println("Scoreboard:");
        for (int i = 0; i < numberOfPlayers; ++i) {
            System.out.println("Player " + (i + 1) + ": " + players[i].getPoints());
        }
    }

    public void displayWinners() {
        int maxPoints = 0;
        for (int i = 0; i < numberOfPlayers; ++i) {
            if (players[i].getPoints() > maxPoints) {
                maxPoints = players[i].getPoints();
            }
        }
        System.out.println("Winner(s):");
        for (int i = 0; i < numberOfPlayers; ++i) {
            if (players[i].getPoints() == maxPoints) {
                System.out.println("Player " + (i + 1));
            }
        }
    }

    private void addCardsUntilSet(int i, int j, int k) {
        if (i != -1) {
            table.set(i, deck.get(deck.size() - 1));
            deck.remove(deck.size() - 1);
            table.set(j, deck.get(deck.size() - 1));
            deck.remove(deck.size() - 1);
            table.set(k, deck.get(deck.size() - 1));
            deck.remove(deck.size() - 1);
        }

        while ((!tableHasSet() || table.size() < TABLE_SIZE)) {
            if (deck.isEmpty()) {
                endGame = true;
                return;
            }
            for (int p = 0; p < 3; ++p) {
                table.add(deck.get(deck.size() - 1));
                deck.remove(deck.size() - 1);
            }
        }
    }

    public boolean gameEnded() {
        return endGame;
    }

    private void layOutCards() {
        deck = new ArrayList<>(cards);
        Collections.shuffle(deck);
        table = new ArrayList<>(TABLE_SIZE);

        for (int i = 0; i < TABLE_SIZE; ++i) {
            table.add(deck.get(deck.size() - 1));
            deck.remove(deck.size() - 1);
        }
        addCardsUntilSet(-1, 0, 0);
    }
}



