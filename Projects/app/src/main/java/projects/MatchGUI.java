package projects;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MatchGUI extends JFrame {
    private JTextField diceMinP1Field, diceMaxP1Field, diceMinP2Field, diceMaxP2Field, roundsField;
    private JTextArea resultsArea;
    private DiceRenderer diceRendererP1, diceRendererP2;

    public MatchGUI() {
        setTitle("Dice Match");
        setSize(400, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(6, 2));
        inputPanel.add(new JLabel("Player 1 Dice Min:"));
        diceMinP1Field = new JTextField("1");
        inputPanel.add(diceMinP1Field);

        inputPanel.add(new JLabel("Player 1 Dice Max:"));
        diceMaxP1Field = new JTextField("6");
        inputPanel.add(diceMaxP1Field);

        inputPanel.add(new JLabel("Player 2 Dice Min:"));
        diceMinP2Field = new JTextField("1");
        inputPanel.add(diceMinP2Field);

        inputPanel.add(new JLabel("Player 2 Dice Max:"));
        diceMaxP2Field = new JTextField("6");
        inputPanel.add(diceMaxP2Field);

        inputPanel.add(new JLabel("Number of Rounds:"));
        roundsField = new JTextField("5");
        inputPanel.add(roundsField);

        JButton playButton = new JButton("Play");
        playButton.addActionListener(this::playMatch);
        inputPanel.add(playButton);

        add(inputPanel, BorderLayout.NORTH);

        resultsArea = new JTextArea();
        resultsArea.setRows(16); // Set default height to 5 rows
        resultsArea.setEditable(false);
        add(new JScrollPane(resultsArea), BorderLayout.SOUTH);

        diceRendererP1 = new DiceRenderer();
        diceRendererP2 = new DiceRenderer();

        JPanel dicePanel = new JPanel(new GridLayout(1, 2));
        dicePanel.add(diceRendererP1);
        dicePanel.add(diceRendererP2);

        add(dicePanel, BorderLayout.CENTER);
    }

    private void playMatch(ActionEvent event) {
        try {
            int diceMinP1 = Integer.parseInt(diceMinP1Field.getText());
            int diceMaxP1 = Integer.parseInt(diceMaxP1Field.getText());
            int diceMinP2 = Integer.parseInt(diceMinP2Field.getText());
            int diceMaxP2 = Integer.parseInt(diceMaxP2Field.getText());
            int rounds = Integer.parseInt(roundsField.getText());

            Match match = new Match(diceMinP1, diceMaxP1, diceMinP2, diceMaxP2, rounds);
            StringBuilder results = new StringBuilder();

            SwingWorker<Void, String> worker = new SwingWorker<>() {
                @Override
                protected Void doInBackground() throws Exception {
                    for (int i = 0; i < rounds; i++) {
                        int rollP1 = match.player1.dice.roll();
                        int rollP2 = match.player2.dice.roll();

                        publish("Round " + (i + 1) + ":\nPlayer 1: " + rollP1 + "\nPlayer 2: " + rollP2 + "\n");

                        SwingUtilities.invokeLater(() -> {
                            diceRendererP1.setDiceValue(rollP1);
                            diceRendererP2.setDiceValue(rollP2);
                        });

                        Thread.sleep(1000); // Pause for 1 second to simulate rolling
                    }
                    match.play();
                    publish("\nFinal Results:\n" + match.getResults());
                    return null;
                }

                @Override
                protected void process(java.util.List<String> chunks) {
                    for (String chunk : chunks) {
                        results.append(chunk);
                        resultsArea.setText(results.toString());
                    }
                }
            };

            worker.execute();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
