package io.github.rangeremerald.tictactoegammawave.objects;

import io.github.rangeremerald.tictactoegammawave.TicTacToe;
import io.github.rangeremerald.tictactoegammawave.creditHelper.WordWrap;

import javax.swing.*;
import java.awt.*;

public class FactHolder {

    ImageIcon image;
    String fact;

    public FactHolder(String image, String fact) {
        this.image = new ImageIcon(getClass().getResource("/images/" + image));
        this.fact = fact;
    }

    public void drawFactImage(Graphics g, Component c) {
        Font font = new Font(Font.MONOSPACED, Font.PLAIN, 20);

        image.paintIcon(c, g, TicTacToe.questionWidth / 2 - image.getIconWidth() / 2, 30);

        g.setFont(font);

        g.drawString("Did you know...", TicTacToe.questionWidth / 2 - g.getFontMetrics().stringWidth("Did you know...") / 2, 25);

        new WordWrap().WordWrap(g, fact, 20, 270, TicTacToe.questionWidth - 40);
    }

}
