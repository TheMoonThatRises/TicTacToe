package io.github.rangeremerald.tictactoegammawave.settingsHelper.factQuestionEditHelper;

import java.awt.*;

public class HoverEditIcon {

    private final EditMutipleChoice editMutipleChoice;

    public HoverEditIcon(EditMutipleChoice editMutipleChoice) {
        this.editMutipleChoice = editMutipleChoice;
    }

    public boolean changeCursor(Rectangle mouseRect, Runnable handCursor, Runnable defaultCursor) {
        try {
            boolean isHand = mouseRect.intersects(editMutipleChoice.editQuestion) || mouseRect.intersects(editMutipleChoice.editImageLink);
            for (Rectangle rect : editMutipleChoice.editAnswerChoices) if (mouseRect.intersects(rect)) isHand = true;

            if (isHand) handCursor.run();
            else defaultCursor.run();

            return isHand;
        } catch (NullPointerException ignored) { }
        return false;
    }

}
