package io.github.rangeremerald.tictactoegammawave.settingsHelper.factQuestionEditHelper;

import io.github.rangeremerald.tictactoegammawave.TicTacToe;
import io.github.rangeremerald.tictactoegammawave.objects.Button;
import io.github.rangeremerald.tictactoegammawave.objects.HalfSizedButton;
import io.github.rangeremerald.tictactoegammawave.screens.Settings;

public class UpdatePanelButtons extends HalfSizedButton {

    @Override
    public void initValues() {
        buttonNames.add("Back");

        screenWidth = TicTacToe.settingsScreen.getWidth();
        screenHeight = TicTacToe.settingsScreen.getHeight();
        super.initValues();
    }

    @Override
    public void whichButton(Button pressedButton, Runnable waitCursor, Runnable defaultCursor) {
        String pressedButtonName = pressedButton.drawStringTotal.text.toLowerCase();
        if (pressedButtonName.equals("exit")) Settings.SettingsScreen.exitSettings(waitCursor, defaultCursor);
        else if (pressedButtonName.equals("back")) {
            TicTacToe.settingsScreen.setTitle("Settings");
            TicTacToe.settingsScreen.setSize(TicTacToe.windowWidth, TicTacToe.windowHeight);

            Settings.SettingsScreen.updateQuestionPanel = null;
            TicTacToe.settingsScreen.getContentPane().removeAll();
            TicTacToe.settingsScreen.add(new Settings.SettingsScreen());
            TicTacToe.settingsScreen.setVisible(true);
        }
    }
}
