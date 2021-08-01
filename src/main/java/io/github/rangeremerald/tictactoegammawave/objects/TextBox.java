package io.github.rangeremerald.tictactoegammawave.objects;

import java.awt.*;
import java.awt.event.KeyEvent;

public class TextBox {

    public String text;
    public final HitBox textBox;

    public static class HitBox {

        public final Rectangle hitBoxRect;

        public HitBox(int xpos, int ypos, int width, int height) {
            this.hitBoxRect = new Rectangle(xpos, ypos, width, height);
        }

    }

    public TextBox(int xpos, int ypos, int width, int height) {
        this.text = "";
        this.textBox = new HitBox(xpos, ypos, width, height);
    }

    public void editTextBox(KeyEvent e, boolean ifIsUpperCase) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) text += " ";
        else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE && text.length() > 0) text = text.substring(0, text.length() - 1);
        else {
            String inputKey = String.valueOf(e.getKeyChar());
            int inputKeyInt = Integer.parseInt(inputKey);
            if (inputKeyInt >= 'a' && inputKeyInt <= 'z')
                if (ifIsUpperCase)  text += inputKey.toUpperCase();
                else text += inputKey;
        }
    }

}
