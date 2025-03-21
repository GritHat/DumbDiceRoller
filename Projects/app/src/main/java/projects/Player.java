package projects;

public class Player {
    public Dice dice;
    public int score;
    public Player(int diceMin, int diceMax) {
        dice = new Dice(diceMin, diceMax);
        score = 0;
    }
}
