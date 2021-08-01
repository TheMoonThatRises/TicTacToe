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

    public boolean animate(Rectangle mouseRect, Runnable repaint, Runnable setNewCursor, Runnable setOldCursor) {
        //If mouse hovers over button
        boolean reAnimated = reAnimate(mouseRect, repaint, setNewCursor);

        //If mouse is not over a button that was animated
        boolean unAnimated = unAnimate(mouseRect, repaint, setOldCursor);

        return reAnimated || unAnimated;
    }

    public boolean reAnimate(Rectangle mouseRect, Runnable repaint, Runnable setNewCursor) {
        try {
            boolean isAnimated = false;

            for (Button button : buttonsHelper.buttonHash.values()) {
                if (mouseRect.intersects(button.buttonRect)) {
                    isAnimated = true;
                    button.drawStringTotal.textColour = Color.white;

                    setNewCursor.run();
                    if (button.buttonAnimation.width < button.width) {
                        if (!toAnimationBack.contains(button)) toAnimationBack.add(button);
                        button.buttonAnimation.width += button.buttonAnimation.animSpeed;
                        repaint.run();
                    }
                }
            }

            return isAnimated;
        } catch (Exception ignored) { }
        return false;
    }

    public boolean unAnimate(Rectangle mouseRect, Runnable repaint, Runnable setOldCursor) {
        try {
            boolean isAnimated = false;

            for (Button button : toAnimationBack) {
                if (mouseRect.intersects(button.buttonRect)) return false;

                isAnimated = true;
                button.drawStringTotal.textColour = Color.black;
                if (button.buttonAnimation.width == 0) {
                    toAnimationBack.remove(button);
                    break;
                }
                button.buttonAnimation.width -= button.buttonAnimation.animSpeed;
                setOldCursor.run();
                repaint.run();
            }

            return isAnimated;
        } catch (Exception ignored) { }
        return false;
    }
}
