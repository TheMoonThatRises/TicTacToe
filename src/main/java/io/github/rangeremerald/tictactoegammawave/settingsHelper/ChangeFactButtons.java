package io.github.rangeremerald.tictactoegammawave.settingsHelper;

import io.github.rangeremerald.tictactoegammawave.TicTacToe;
import io.github.rangeremerald.tictactoegammawave.helper.FactAdder;
import io.github.rangeremerald.tictactoegammawave.objects.Button;
import io.github.rangeremerald.tictactoegammawave.objects.ScreenButtonsHelper;
import io.github.rangeremerald.tictactoegammawave.screens.Settings;

import java.awt.*;
import java.util.ArrayList;

public class ChangeFactButtons extends ScreenButtonsHelper {

    {
        buttonNames = new ArrayList<>();

        for (int i = 0; i < new FactAdder().factAdder().size(); i++) buttonNames.add("Fact " + (i + 1));

        rectWidth = TicTacToe.windowWidth - 20;
        rectHeight /= 2;

        size = 20;

        xpos = TicTacToe.settingsScreen.getWidth() / 2 - rectWidth / 2;
    }

    @Override
    public void updatePos() {
        xpos = TicTacToe.settingsScreen.getWidth() / 2 - rectWidth / 2;
        ypos = (rectHeight * namePos) + scrollDisplace + 30;
    }

    @Override
    public void drawButtons(Graphics g) {
        for (Button button : buttonHash.values()) button.drawButton(g);
    }

    @Override
    public void whichButton(Button pressedButton, Runnable waitCursor, Runnable defaultCursor) {

    }

    @Override
    public void updateAnimSpeed(int speed) {

    }
}
