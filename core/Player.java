package core;

public class Player {
    private int points;

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void addPoints() {
        this.points++;
    }

    public Player() {
        this.points = 0;
    }

    //copy constructor
    public Player(Player player) {
        this.points = player.points;
    }
}
