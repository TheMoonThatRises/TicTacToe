package io.github.rangeremerald.tictactoegammawave.objects;

import io.github.rangeremerald.tictactoegammawave.TicTacToe;
import io.github.rangeremerald.tictactoegammawave.creditHelper.WordWrap;
import io.github.rangeremerald.tictactoegammawave.helper.ReadJsonFile;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;

public class FactHolder {

    public ImageIcon image;
    public TextHitbox imageLink;
    public String fact, questionImageLink;
    public Font imageLinkFont, font;

    public FactHolder(String image, String fact) {
        this.image = new ImageIcon(getClass().getResource("/images/" + image));
        this.fact = fact;
        this.font = new Font(Font.MONOSPACED, Font.PLAIN, 20);
        imageLinkFont = new Font(Font.MONOSPACED, Font.PLAIN, 10);

        try {
            this.questionImageLink = (String) ((JSONObject) new JSONParser().parse(new ReadJsonFile().readJsonFile("imageCitations.json"))).get(image);
        } catch (ParseException ignored) { }
    }

    public void drawFactImage(Graphics g, Component c) {
        if (imageLink == null) {
            int imageLinkWidth = g.getFontMetrics(imageLinkFont).stringWidth(questionImageLink);
            Rectangle imageLinkHitbox = new Rectangle(TicTacToe.questionWidth / 2 - imageLinkWidth / 2, 240 - imageLinkFont.getSize(), imageLinkWidth, imageLinkFont.getSize());
            imageLink = new TextHitbox(imageLinkHitbox, null, questionImageLink);
            imageLink.colour = Color.blue.brighter();
        }

        g.setFont(imageLinkFont);
        g.setColor(imageLink.colour);

        g.drawString(questionImageLink, imageLink.hitBoxOutline.x, imageLink.hitBoxOutline.y + imageLinkFont.getSize());

        image.paintIcon(c, g, TicTacToe.questionWidth / 2 - image.getIconWidth() / 2, 30);

        g.setFont(font);
        g.setColor(Color.black);

        g.drawString("Did you know...", TicTacToe.questionWidth / 2 - g.getFontMetrics().stringWidth("Did you know...") / 2, 25);

        new WordWrap().WordWrap(g, fact, 20, 270, TicTacToe.questionWidth - 40);
    }

}
