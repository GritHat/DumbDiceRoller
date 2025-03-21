package projects;

public class Match {
    public Player player1;
    public Player player2;
    public int rounds;
    public Match(int diceMinP1, int diceMaxP1, int diceMinP2, int diceMaxP2, int rounds) {
        player1 = new Player(diceMinP1, diceMaxP1);
        player2 = new Player(diceMinP2, diceMaxP2);
        this.rounds = rounds;
    }
    public void play() {
        for (int i = 0; i < rounds; i++) {
            System.out.println("Player 1: " + player1.dice.roll3D());
            player1.score += player1.dice.roll();
            System.out.println("Player 2: " + player2.dice.roll3D());
            player2.score += player2.dice.roll();
        }
    }
    public String getResults() {
        return "Player 1: " + player1.score + "\nPlayer 2: " + player2.score;
    }
}