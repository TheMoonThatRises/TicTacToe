package io.github.rangeremerald.tictactoegammawave.settingsHelper;

import io.github.rangeremerald.tictactoegammawave.TicTacToe;
import io.github.rangeremerald.tictactoegammawave.objects.Button;
import io.github.rangeremerald.tictactoegammawave.objects.HalfSizedButton;
import io.github.rangeremerald.tictactoegammawave.screens.Settings;

public class SettingsButtons extends HalfSizedButton {

    @Override
    public void initValues() {
        screenWidth = TicTacToe.settingsScreen.getWidth();
        screenHeight = TicTacToe.settingsScreen.getHeight();
        super.initValues();
    }

    @Override
    public void whichButton(Button pressedButton, Runnable waitCursor, Runnable defaultCursor) {
        if (pressedButton.drawStringTotal.text.equalsIgnoreCase("exit")) {
            Settings.SettingsScreen.exitSettings(waitCursor, defaultCursor);
        }
    }
}