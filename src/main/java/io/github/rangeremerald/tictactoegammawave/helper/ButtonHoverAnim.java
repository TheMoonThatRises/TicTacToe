package io.github.rangeremerald.tictactoegammawave.helper;

import io.github.rangeremerald.tictactoegammawave.objects.Button;
import io.github.rangeremerald.tictactoegammawave.objects.ScreenButtonsHelper;

import java.awt.*;
import java.util.ArrayList;

public class ButtonHoverAnim {

    public final ArrayList<Button> toAnimationBack = new ArrayList<>();
    final protected ScreenButtonsHelper buttonsHelper;

    public ButtonHoverAnim(ScreenButtonsHelper buttonsHelper) {
        this.buttonsHelper = buttonsHelper;
    }

    public boolean reAnimate(Rectangle mouseRect, Runnable repaint, Runnable setNewCursor, Runnable setOldCursor) {
        //If mouse hovers over button
        for (Button button : buttonsHelper.buttonHash.values()) {
            if (mouseRect.intersects(button.buttonRect)) {
                button.drawStringTotal.textColour = Color.white;

                setNewCursor.run();
                if (button.buttonAnimation.width < button.width) {
                    if (!toAnimationBack.contains(button)) toAnimationBack.add(button);
                    button.buttonAnimation.width += 5;
                    repaint.run();
                }
            }
        }

        //If mouse is not over a button that was animated
        for (Button button : toAnimationBack) {
            if (mouseRect.intersects(button.buttonRect)) return true;
            button.drawStringTotal.textColour = Color.black;
            if (button.buttonAnimation.width == 0) {
                toAnimationBack.remove(button);
                break;
            }
            button.buttonAnimation.width -= 5;
            setOldCursor.run();
            repaint.run();
        }
        return false;
    }
}
