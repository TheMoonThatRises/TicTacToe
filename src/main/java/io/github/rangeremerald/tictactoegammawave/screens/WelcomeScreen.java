package io.github.rangeremerald.tictactoegammawave.screens;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class WelcomeScreen extends JFrame {
    public void welcomeScreen() {
        add(new LoadingScreen());

        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        setSize(300, 120);

        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException ignored) {
        }
    }

    private static class LoadingScreen extends JPanel {
        public LoadingScreen() {
            setFocusable(true);
            setBackground(Color.white);
        }

        public void paint(Graphics g) {
            g.setColor(Color.black);
            g.setFont(new Font("monospace", Font.PLAIN, 20));
            g.drawString("Loading Resources...", 50, 50);

            g.dispose();
        }
    }
}
