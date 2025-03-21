package projects;

import javax.swing.*;
import java.awt.*;

public class DiceRenderer extends JPanel {
    private int diceValue;

    public DiceRenderer() {
        this.diceValue = 1; // Default value
    }

    public void setDiceValue(int value) {
        this.diceValue = value;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw dice outline
        g2d.setColor(Color.WHITE);
        g2d.fillRoundRect(50, 50, 100, 100, 20, 20);
        g2d.setColor(Color.BLACK);
        g2d.drawRoundRect(50, 50, 100, 100, 20, 20);

        // Draw dice dots or value based on diceValue
        g2d.setColor(Color.BLACK);
        if (diceValue <= 6) {
            switch (diceValue) {
                case 1 -> g2d.fillOval(90, 90, 20, 20);
                case 2 -> {
                    g2d.fillOval(70, 70, 20, 20);
                    g2d.fillOval(110, 110, 20, 20);
                }
                case 3 -> {
                    g2d.fillOval(70, 70, 20, 20);
                    g2d.fillOval(90, 90, 20, 20);
                    g2d.fillOval(110, 110, 20, 20);
                }
                case 4 -> {
                    g2d.fillOval(70, 70, 20, 20);
                    g2d.fillOval(70, 110, 20, 20);
                    g2d.fillOval(110, 70, 20, 20);
                    g2d.fillOval(110, 110, 20, 20);
                }
                case 5 -> {
                    g2d.fillOval(70, 70, 20, 20);
                    g2d.fillOval(70, 110, 20, 20);
                    g2d.fillOval(90, 90, 20, 20);
                    g2d.fillOval(110, 70, 20, 20);
                    g2d.fillOval(110, 110, 20, 20);
                }
                case 6 -> {
                    g2d.fillOval(70, 70, 20, 20);
                    g2d.fillOval(70, 90, 20, 20);
                    g2d.fillOval(70, 110, 20, 20);
                    g2d.fillOval(110, 70, 20, 20);
                    g2d.fillOval(110, 90, 20, 20);
                    g2d.fillOval(110, 110, 20, 20);
                }
            }
        } else {
            g2d.setFont(new Font("Arial", Font.BOLD, 40));
            String value = String.valueOf(diceValue);
            FontMetrics metrics = g2d.getFontMetrics();
            int x = 100 - metrics.stringWidth(value) / 2;
            int y = 100 + metrics.getAscent() / 2 - 10;
            g2d.drawString(value, x, y);
        }
    }
}
