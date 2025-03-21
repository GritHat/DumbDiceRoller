package projects;

public class Dice {
    private int diceMin;
    private int diceMax;

    public Dice(int diceMin, int diceMax) {
        assert diceMin < diceMax;
        assert diceMin > 0;
        this.diceMin = diceMin;
        this.diceMax = diceMax;
    }

    public int roll() {
        return (int) (Math.random() * (diceMax - diceMin) + diceMin);
    }

    public String roll3D() {
        int rollValue = roll();
        return "Rolling a 3D dice with " + (diceMax - diceMin + 1) + " faces: [" + rollValue + "]";
    }
    
}
