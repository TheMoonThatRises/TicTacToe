package io.github.rangeremerald.tictactoegammawave.objects;

import java.awt.*;

public class Button {

    public final Rectangle buttonRect;
    public final DrawString drawStringTotal;
    public Color rectColour;
    public final ButtonAnimation buttonAnimation;
    public final int xpos;
    public final int ypos;
    public final int height;
    public final int width;
    public final int rounded;

    public static class DrawString {

        public final int xpos;
        public final int ypos;
        public final String text;
        public Color textColour;
        public final Font font;

        public DrawString(int xrectpos, int ypos, String text, int size, Graphics g, int width) {
            this.font = new Font(Font.MONOSPACED, Font.PLAIN, size);
            this.textColour = Color.black;
            this.xpos = xrectpos + width / 2 - g.getFontMetrics(this.font).stringWidth(text) / 2;
            this.ypos = ypos;
            this.text = text;
        }

    }

    public static class ButtonAnimation {

        public final int xpos;
        public final int ypos;
        public int length;
        public int width;
        public int animSpeed;
        public final int rounded;
        public final Color animationColour;

        public ButtonAnimation(int xpos, int ypos, int length, int width, int rounded) {
            this.xpos = xpos;
            this.ypos = ypos;
            this.length = length;
            this.width = width;
            this.rounded = rounded;
            this.animationColour = Color.blue;
            this.animSpeed = 5;
        }

    }

    public Button(int xpos, int ypos, int height, int width, String text, int size, int rounded, Graphics g) {
        this.drawStringTotal = new DrawString(xpos, ypos + height / 2 + size / 2, text, size, g, width);
        this.buttonRect = new Rectangle(xpos, ypos, width, height);
        this.buttonAnimation = new ButtonAnimation(xpos, ypos, height, 0, rounded);
        this.rectColour = Color.red;
        this.xpos = xpos;
        this.ypos = ypos;
        this.rounded = rounded;
        this.height = height;
        this.width = width;
    }

    public void drawButton(Graphics g) {
        g.setColor(rectColour);
        g.fillRoundRect(xpos, ypos, width, height, rounded, rounded);

        g.setColor(buttonAnimation.animationColour);
        g.fillRoundRect(buttonAnimation.xpos, buttonAnimation.ypos, buttonAnimation.width, buttonAnimation.length, buttonAnimation.rounded, buttonAnimation.rounded);

        g.setColor(drawStringTotal.textColour);
        g.setFont(drawStringTotal.font);
        g.drawString(drawStringTotal.text, drawStringTotal.xpos, drawStringTotal.ypos);
    }
}