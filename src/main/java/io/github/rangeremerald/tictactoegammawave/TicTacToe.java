package io.github.rangeremerald.tictactoegammawave;

import io.github.rangeremerald.tictactoegammawave.updater.Updater;
import io.github.rangeremerald.tictactoegammawave.screens.Board;
import io.github.rangeremerald.tictactoegammawave.screens.Credits;
import io.github.rangeremerald.tictactoegammawave.screens.Question;
import io.github.rangeremerald.tictactoegammawave.screens.WelcomeScreen;

import javax.swing.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class TicTacToe extends JFrame {

    public static final int windowWidth = 700;
    public static final int windowHeight = 500;

    public static final int questionWidth = windowWidth + 300;
    public static final int questionHeight = windowHeight - 100;

    public static Credits creditScreen = null;
    public static Question questionScreen = null;
    public static Board board = null;

    public TicTacToe() {
        WelcomeScreen welcomeScreen = new WelcomeScreen();

        welcomeScreen.welcomeScreen();

        board = new Board();

        add(board);
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        setSize(windowWidth, windowHeight);

        welcomeScreen.dispose();
    }

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        if (args.length < 1) new Updater(null);
        else new Updater(args[0]);

        new TicTacToe();
    }
}
