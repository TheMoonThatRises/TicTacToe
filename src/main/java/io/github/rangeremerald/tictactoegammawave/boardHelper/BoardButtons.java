package io.github.rangeremerald.tictactoegammawave.boardHelper;

import io.github.rangeremerald.tictactoegammawave.TicTacToe;
import io.github.rangeremerald.tictactoegammawave.objects.ScreenButtonsHelper;

import java.util.ArrayList;
import java.util.Arrays;

public class BoardButtons extends ScreenButtonsHelper {

    {
        buttonNames = new ArrayList<>(Arrays.asList("Credits", "Quit"));
        xpos = TicTacToe.windowWidth * 3 / 4 - rectWidth / 2;
        ypos = TicTacToe.windowHeight / buttonNames.size() * namePos + rectHeight / 2;
    }

    @Override
    public void updatePos(int width, int height) {
        int b = (TicTacToe.windowHeight - (height * buttonNames.size())) / (buttonNames.size() + 1);
        ypos = (namePos - 1) * height + namePos * b;
    }

}