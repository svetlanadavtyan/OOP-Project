package core;

public class Card {
    public enum Color {
        RED, GREEN, PURPLE
    }

    public enum Shape {
        DIAMOND, SQUIGGLE, OVAL
    }

    public enum Shading {
        SOLID, STRIPED, OPEN
    }

    public enum Number {
        ONE, TWO, THREE
    }

    private Color color;
    private Shape shape;
    private Shading shading;
    private Number number;

    public Card(Color color, Shape shape, Shading shading, Number number) {
        this.color = color;
        this.shape = shape;
        this.shading = shading;
        this.number = number;
    }

    public Card(Card that) {
        this.color = that.color;
        this.shape = that.shape;
        this.shading = that.shading;
        this.number = that.number;
    }

    public Color getColor() {
        return color;
    }

    public Shape getShape() {
        return shape;
    }

    public Shading getShading() {
        return shading;
    }

    public Number getNumber() {
        return number;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public void setShading(Shading shading) {
        this.shading = shading;
    }

    public void setNumber(Number number) {
        this.number = number;
    }
}