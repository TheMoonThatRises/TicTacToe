package io.github.rangeremerald.tictactoegammawave.settingsHelper;

import io.github.rangeremerald.tictactoegammawave.helper.ButtonHoverAnim;
import io.github.rangeremerald.tictactoegammawave.objects.Button;
import io.github.rangeremerald.tictactoegammawave.objects.ScreenButtonsHelper;

import java.awt.*;

public class ButtonHoverAnimTabs extends ButtonHoverAnim {
    public ButtonHoverAnimTabs(ScreenButtonsHelper buttonsHelper) {
        super(buttonsHelper);
    }

    @Override
    public boolean reAnimate(Rectangle mouseRect, Runnable repaint, Runnable setNewCursor) {
        try {
            boolean isAnimated = false;

            for (Button button : buttonsHelper.buttonHash.values()) {
                if (mouseRect.intersects(button.buttonRect)) {
                    if (button.rectColour == Color.blue) continue;
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

    @Override
    public boolean unAnimate(Rectangle mouseRect, Runnable repaint, Runnable setOldCursor) {
        try {
            boolean isAnimated = false;

            for (Button button : toAnimationBack) {
                if (mouseRect.intersects(button.buttonRect)) return false;
                else if (button.rectColour == Color.blue) continue;

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