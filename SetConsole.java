import java.util.*;

public class SetConsole {
    private Set game;
    public void play() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please input the number of players:");
        int numberOfPlayers = sc.nextInt();
        this.game = new Set(numberOfPlayers);

        print();

        //TODO: write the play logic
    }

    public void print() {
        //initial way to print the Set game cards on the table
        Card[] cards = game.getTableCards();
        for(int i = 0; i < cards.length; ++i) {
            System.out.println("Card " + i + ": " + cards[i].getColor() + ", " + cards[i].getShape() + ", " + cards[i].getShading() + ", "+ cards[i].getNumber());
        }
    }
}