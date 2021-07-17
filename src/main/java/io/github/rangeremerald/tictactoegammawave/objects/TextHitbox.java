package io.github.rangeremerald.tictactoegammawave.objects;

import java.awt.*;

public class TextHitbox {

        public Rectangle hitBoxOutline, checkBox;
        public String text;
        public Color colour;

        public TextHitbox(Rectangle hitBoxOutline, Rectangle checkBox, String text) {
            this.hitBoxOutline = hitBoxOutline;
            this.checkBox = checkBox;
            this.text = text;
            this.colour = null;
        }

}
