package io.github.rangeremerald.tictactoegammawave.settingsHelper;

import io.github.rangeremerald.tictactoegammawave.objects.Button;
import io.github.rangeremerald.tictactoegammawave.objects.ScreenButtonsHelper;
import io.github.rangeremerald.tictactoegammawave.screens.Settings;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class SwitchTabButtons extends ScreenButtonsHelper {

    {
        buttonNames = new ArrayList<>(Arrays.asList("Questions", "Facts"));

        rectWidth -= 80;
        rectHeight /= 2;
        size /= 2;

        xpos = 0;
        ypos = 0;
        roundedSize = 0;
    }

    @Override
    public void updatePos(int width, int height) {
        xpos = width * namePos - 120;
    }

    @Override
    public void updateAnimSpeed(int speed) {
        for (Button button : buttonHash.values()) button.buttonAnimation.animSpeed = speed;
    }

    @Override
    public boolean initHash(Graphics g) {
        super.initHash(g);
        updateAnimSpeed(3);
        return true;
    }

    @Override
    public void whichButton(Button pressedButton, Runnable waitCursor, Runnable defaultCursor) {
        switch (pressedButton.drawStringTotal.text) {
            case "Questions":
                Settings.SettingsScreen.screen = 0;
                break;
            case "Facts":
                Settings.SettingsScreen.screen = 1;
                break;
        }
        Settings.SettingsScreen.initScrollingHash = false;
        Settings.SettingsScreen.recolour = false;
    }
}
