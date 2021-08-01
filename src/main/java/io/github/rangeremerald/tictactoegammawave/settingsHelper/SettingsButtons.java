package io.github.rangeremerald.tictactoegammawave.settingsHelper;

import io.github.rangeremerald.tictactoegammawave.TicTacToe;
import io.github.rangeremerald.tictactoegammawave.objects.Button;
import io.github.rangeremerald.tictactoegammawave.objects.ScreenButtonsHelper;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class SettingsButtons extends ScreenButtonsHelper {

    {
        buttonNames = new ArrayList<>(Collections.singletonList("Exit"));

        rectWidth /= 2;
        rectHeight /= 2;
        size /= 2;

        xpos = TicTacToe.settingsScreen.getWidth() / 2 - rectWidth / 2;
        ypos = TicTacToe.settingsScreen.getHeight() - rectHeight - 30;

    }

    @Override
    public void updatePos(int width, int height) {
        xpos = TicTacToe.settingsScreen.getWidth() / 2 - rectWidth / 2;
        ypos = TicTacToe.settingsScreen.getHeight() - rectHeight - 30;
    }

    @Override
    public void updateAnimSpeed(int speed) {
        buttonHash.get("Exit").buttonAnimation.animSpeed = speed;
    }

    @Override
    public boolean initHash(Graphics g) {
        super.initHash(g);
        updateAnimSpeed(3);
        return true;
    }

    @Override
    public void whichButton(Button pressedButton, Runnable waitCursor, Runnable defaultCursor) {
        if (pressedButton.drawStringTotal.text.equalsIgnoreCase("exit")) {
            waitCursor.run();
            TicTacToe.settingsScreen.dispose();
            TicTacToe.settingsScreen = null;
            defaultCursor.run();
        }
    }
}