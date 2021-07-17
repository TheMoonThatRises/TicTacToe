package io.github.rangeremerald.tictactoegammawave.creditHelper;

import io.github.rangeremerald.tictactoegammawave.TicTacToe;
import io.github.rangeremerald.tictactoegammawave.objects.ScreenButtonsHelper;

import java.util.ArrayList;
import java.util.Collections;

public class CreditButtons extends ScreenButtonsHelper {

    {
        buttonNames = new ArrayList<>(Collections.singletonList("Exit"));
        xpos = TicTacToe.creditScreen.getWidth() / 2 - rectWidth / 2;
        ypos = TicTacToe.creditScreen.getHeight() - rectHeight - 30;
    }

    @Override
    public void updatePos(int width, int height) {
        xpos = TicTacToe.creditScreen.getWidth() / 2 - rectWidth / 2;
        ypos = TicTacToe.creditScreen.getHeight() - rectHeight - 30;
    }

}