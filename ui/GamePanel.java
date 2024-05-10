package ui;

import core.Card;
import core.Set;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GamePanel extends JPanel {
    private Set game;
    private ArrayList<Card> tableCards;
    private Map<String, ImageIcon> cardImageMap;
    private Map<JLabel, Integer> cardIndexMap;
    private ArrayList<Integer> selectedCardIndices = new ArrayList<>(3);

    public GamePanel(Set game) {
        this.game = game;
        this.tableCards = game.getTableCards();
        this.cardImageMap = loadCardImages();
        this.cardIndexMap = new HashMap<>();

        setPreferredSize(new Dimension(800, 600));
        setLayout(new GridLayout(0, 3, 10, 10));
        updateTable();
    }

    public void updateTable() {
        tableCards = game.getTableCards();
        removeAll();
        for (int i = 0; i < tableCards.size(); i++) {
            ImageIcon cardImage = cardImageMap.get(generateFileName(tableCards.get(i).getColor(), tableCards.get(i).getShape(), tableCards.get(i).getShading(), tableCards.get(i).getNumber()));
            JLabel cardLabel = new JLabel(cardImage);
            cardLabel.setToolTipText(tableCards.get(i).toString());
            cardIndexMap.put(cardLabel, i);

            cardLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JLabel clickedLabel = (JLabel) e.getSource();

                    Integer cardIndex = cardIndexMap.get(clickedLabel);
                    selectedCardIndices.add(cardIndex);
                    cardLabel.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 2));
                    if (selectedCardIndices.size() == 3) {
                        game.playerMove(GameFrame.currentPlayerIndex, selectedCardIndices.get(0), selectedCardIndices.get(1), selectedCardIndices.get(2));
                        GameFrame.currentPlayerIndex = (GameFrame.currentPlayerIndex + 1) % game.getNumberOfPlayers();
                        updateTable();
                        clearSelectedCards();
                    }
                }
            });
            add(cardLabel);
        }
        revalidate();
        repaint();
    }

    private void clearSelectedCards() {
        selectedCardIndices.clear();
    }

    private Map<String, ImageIcon> loadCardImages() {
        Map<String, ImageIcon> imageMap = new HashMap<>();

        for (Card.Color color : Card.Color.values()) {
            for (Card.Shape shape : Card.Shape.values()) {
                for (Card.Shading shading : Card.Shading.values()) {
                    for (Card.Number number : Card.Number.values()) {
                        String fileName = generateFileName(color, shape, shading, number);
                        ImageIcon imageIcon = new ImageIcon("\\src\\cards\\" + fileName);
                        imageMap.put(fileName, imageIcon);
                    }
                }
            }
        }
        return imageMap;
    }

    private String generateFileName(Card.Color color, Card.Shape shape, Card.Shading shading, Card.Number number) {
        String colorCode = color.toString().toLowerCase().substring(0, 1);
        String shapeCode = shape.toString().toLowerCase().substring(0, 1);
        String shadingCode = shading.toString().toLowerCase();
        String numberCode = number.toString().toLowerCase();

        if (shadingCode.equals("open"))
            shadingCode = "o";
        else if (shadingCode.equals("striped"))
            shadingCode = "t";
        else
            shadingCode = "s";

        if (numberCode.equals("one"))
            numberCode = "o";
        else if (numberCode.equals("two"))
            numberCode = "t";
        else
            numberCode = "r";

        return colorCode + shapeCode + shadingCode + numberCode + ".png";
    }
}
