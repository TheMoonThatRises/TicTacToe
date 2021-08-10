package io.github.rangeremerald.tictactoegammawave.objects;

import java.awt.*;
import java.util.*;

public abstract class HalfSizedButton extends ScreenButtonsHelper {

    public int screenWidth, screenHeight;

    {
        buttonNames = new ArrayList<>(Collections.singletonList("Exit"));
    }

    public void initValues() {
        rectWidth /= 2;
        rectHeight /= 2;
        size /= 2;

        xpos = screenWidth / 2 - rectWidth / 2;
        ypos = screenHeight - rectHeight - 30;
    }

    @Override
    public void updatePos() {
        int b = (screenWidth - (rectWidth * buttonNames.size())) / (buttonNames.size() + 1);
        xpos = (namePos - 1) * rectWidth + namePos * b;
        ypos = screenHeight - rectHeight - 30;
    }

    @Override
    public void updateAnimSpeed(int speed) {
        for (Map.Entry<String, Button> entry : buttonHash.entrySet()) entry.getValue().buttonAnimation.animSpeed = speed;
    }

    @Override
    public boolean initHash(Graphics g) {
        initValues();
        super.initHash(g);
        updateAnimSpeed(3);
        return true;
    }

    public abstract void whichButton(Button pressedButton, Runnable waitCursor, Runnable defaultCursor);

}
