import java.util.List;

public class GameRules {
    // Check if a set of three cards forms a valid set
    public static boolean isValidSet(Card card1, Card card2, Card card3) {
        // Implement logic to check if the cards form a valid set
        // For the set to be valid, each attribute must be all the same or all different across the three cards
        return (checkSameOrDifferent(card1.getNumber(), card2.getNumber(), card3.getNumber())
                && checkSameOrDifferent(card1.getColor(), card2.getColor(), card3.getColor())
                && checkSameOrDifferent(card1.getShading(), card2.getShading(), card3.getShading())
                && checkSameOrDifferent(card1.getShape(), card2.getShape(), card3.getShape()));
    }

    // Helper method to check if three values are all the same or all different
    private static boolean checkSameOrDifferent(Object value1, Object value2, Object value3) {
        return (value1 == value2 && value2 == value3) || (value1 != value2 && value2 != value3 && value1 != value3);
    }

    // Other game rules methods can be added here
}
