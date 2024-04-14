import java.util.*;

public class Set {
    public static final int DECK_SIZE = 81;
    public static final int TABLE_SIZE = 12;
    private int numberOfPlayers;
    private int[] points;

    private static Card[] deck; // static?
    private int[] table = new int[TABLE_SIZE];

    public Set(int numberOfPlayers){
        generateCards();
        layOutCards();
        this.numberOfPlayers = numberOfPlayers;
        this.points = new int[numberOfPlayers];
    }

    public Card[] getTableCards() {
        Card[] copy = new Card[TABLE_SIZE];
        for(int i = 0; i < table.length; ++i){
            copy[i] = new Card(deck[table[i]]);
        }
        return copy;
    }

    public boolean isSet (int i, int j, int k){
        Card card1 = deck[table[i]];
        Card card2 = deck[table[j]];
        Card card3 = deck[table[k]];
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

    private static void generateCards() {
        deck = new Card[DECK_SIZE];
        int i = 0;
        for(Card.Color color : Card.Color.values()) {
            for(Card.Shape shape : Card.Shape.values()) {
                for(Card.Shading shading : Card.Shading.values()) {
                    for(Card.Number number : Card.Number.values()) {
                        deck[i] = new Card(color, shape, shading, number);
                        i++;
                    }
                }
            }
        }
    }


    private void layOutCards() { // we can use Collection's shuffle
        Random rand = new Random();
        int i = 0;
        while(i < TABLE_SIZE) {
            int randNum = rand.nextInt(DECK_SIZE);
            boolean isUnique = true;
            for (int j = 0; j < i; ++j) {
                if (table[j] == randNum) {
                    isUnique = false;
                    break;
                }
            }
            if (isUnique) {
                table[i] = randNum;
                i++;
            }
        }
    }

}