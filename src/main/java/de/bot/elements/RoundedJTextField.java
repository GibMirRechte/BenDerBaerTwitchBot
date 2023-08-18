package de.bot.elements;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class RoundedJTextField extends JTextField {
    private Color borderColor;
    private boolean borderRaised;
    private int arcWidth;
    private int arcHeight;

    public RoundedJTextField(int columns, Color borderColor, boolean borderRaised, int arcWidth, int arcHeight) {
        super(columns);
        this.borderColor = borderColor;
        this.borderRaised = borderRaised;
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
        setOpaque(false);
        setBorder(new EmptyBorder(0, 5, 0, 5));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arcWidth, arcHeight);
        super.paintComponent(g2);
        g2.dispose();
        paintBorder(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        if (borderRaised) {
            g.setColor(borderColor);
            g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arcWidth, arcHeight);
        }
    }
}


