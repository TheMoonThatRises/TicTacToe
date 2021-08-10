package io.github.rangeremerald.tictactoegammawave.settingsHelper;

import io.github.rangeremerald.tictactoegammawave.TicTacToe;
import io.github.rangeremerald.tictactoegammawave.helper.QuestionAdder;
import io.github.rangeremerald.tictactoegammawave.objects.Button;
import io.github.rangeremerald.tictactoegammawave.objects.ScreenButtonsHelper;
import io.github.rangeremerald.tictactoegammawave.screens.Settings;
import io.github.rangeremerald.tictactoegammawave.settingsHelper.factQuestionEditHelper.QuestionFactUpdatePanel;

import java.awt.*;
import java.util.ArrayList;

public class ChangeQuestionButtons extends ScreenButtonsHelper {

    {
        buttonNames = new ArrayList<>();

        for (int i = 0; i < new QuestionAdder().questionAdder().size(); i++) buttonNames.add("Question " + (i + 1));

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
        Settings.SettingsScreen.updateQuestionPanel = new QuestionFactUpdatePanel(pressedButton.drawStringTotal.text);
        TicTacToe.settingsScreen.getContentPane().removeAll();
        TicTacToe.settingsScreen.add(Settings.SettingsScreen.updateQuestionPanel);
        TicTacToe.settingsScreen.setVisible(true);
    }

    @Override
    public void updateAnimSpeed(int speed) {

    }
}
