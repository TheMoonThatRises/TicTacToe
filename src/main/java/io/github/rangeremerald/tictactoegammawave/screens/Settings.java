package io.github.rangeremerald.tictactoegammawave.screens;

import io.github.rangeremerald.tictactoegammawave.TicTacToe;
import io.github.rangeremerald.tictactoegammawave.objects.Button;
import io.github.rangeremerald.tictactoegammawave.objects.ScreenButtonsHelper;
import io.github.rangeremerald.tictactoegammawave.objects.TextBox;
import io.github.rangeremerald.tictactoegammawave.settingsHelper.*;
import io.github.rangeremerald.tictactoegammawave.helper.ButtonHoverAnim;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Settings extends JFrame {

    public void settings() {
        add(new SettingsScreen());

        setSize(TicTacToe.windowWidth, TicTacToe.windowHeight);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Settings");
        setVisible(true);
    }

    public static class SettingsScreen extends JPanel implements ActionListener, MouseListener, MouseWheelListener {

        private final ArrayList<ButtonHoverAnim> buttonAnimList = new ArrayList<>();
        public HashMap<String, ScreenButtonsHelper> buttonsList = new HashMap<>();
        public static int screen = 0;
        public static boolean recolour = false;

        private boolean inithash = false;
        public static boolean initScrollingHash = false;

        private final Timer timer;
        public final int delay = 5;

        public SettingsScreen() {
            buttonsList.put("settingsButtons", new SettingsButtons());
            buttonsList.put("switchTabButtons", new SwitchTabButtons());
            buttonsList.put("changeQuestionButtons", new ChangeQuestionButtons());
            buttonsList.put("changeFactButtons", new ChangeFactButtons());

            buttonAnimList.add(new ButtonHoverAnim(buttonsList.get("settingsButtons")));
            buttonAnimList.add(new ButtonHoverAnimTabs(buttonsList.get("switchTabButtons")));
            buttonAnimList.add(new ButtonHoverAnim(buttonsList.get("changeQuestionButtons")));
            buttonAnimList.add(new ButtonHoverAnim(buttonsList.get("changeFactButtons")));

            setFocusable(true);
            setBackground(Color.white);

            addMouseListener(this);
            addMouseWheelListener(this);

            timer = new Timer(delay, this);
            timer.start();
        }

        public void paint(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

            g.setColor(Color.white);
            g.fillRect(0, 0, getWidth(), getHeight());

            if (!inithash) {
                inithash = buttonsList.get("settingsButtons").initHash(g);
                buttonsList.get("switchTabButtons").initHash(g);
            }

            Button questionButton = buttonsList.get("switchTabButtons").buttonHash.get("Questions");
            Button factButton = buttonsList.get("switchTabButtons").buttonHash.get("Facts");

            switch (screen) {
                case 0:
                    if (!initScrollingHash) initScrollingHash = buttonsList.get("changeQuestionButtons").initHash(g);

                    buttonsList.get("changeQuestionButtons").drawButtons(g);

                    if (!recolour) recolour = switchColours(questionButton, factButton);

                    break;
                case 1:
                    if (!initScrollingHash) initScrollingHash = buttonsList.get("changeFactButtons").initHash(g);

                    buttonsList.get("changeFactButtons").drawButtons(g);

                    if (!recolour) recolour = switchColours(factButton, questionButton);

                    break;
            }

            g.setColor(Color.white);
            g.fillRect(0, 0, getWidth(), buttonsList.get("switchTabButtons").rectHeight + 10);
            g.fillRect(0, buttonsList.get("settingsButtons").ypos - 10, getWidth(), buttonsList.get("settingsButtons").rectHeight + 10);

            buttonsList.get("settingsButtons").drawButtons(g);
            buttonsList.get("switchTabButtons").drawButtons(g);

            g.dispose();
        }

        public boolean switchColours(Button button1, Button button2) {
            button1.rectColour = Color.blue;
            button1.drawStringTotal.textColour = Color.white;

            button2.rectColour = Color.red;
            button2.drawStringTotal.textColour = Color.black;

            return true;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (TicTacToe.settingsScreen != null && TicTacToe.settingsScreen.isDisplayable() && buttonsList.get("settingsButtons") != null && buttonAnimList != null) {
                    timer.restart();
                    Rectangle mouseRect = new Rectangle(MouseInfo.getPointerInfo().getLocation().x - getLocationOnScreen().x, MouseInfo.getPointerInfo().getLocation().y - getLocationOnScreen().y, 1, 1);

                    for (ButtonHoverAnim buttonHoverAnim : buttonAnimList) buttonHoverAnim.animate(mouseRect, this::repaint, () -> setCursor(new Cursor(Cursor.HAND_CURSOR)), () -> setCursor(new Cursor(Cursor.DEFAULT_CURSOR)));
                }
            } catch (IllegalComponentStateException ignored) { }
        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            Map<Integer, ScreenButtonsHelper> changeButtons = new HashMap<Integer, ScreenButtonsHelper>(){{
                put(0, buttonsList.get("changeQuestionButtons"));
                put(1, buttonsList.get("changeFactButtons"));
            }};
            Map<Integer, String> screenToName = new HashMap<Integer, String>() {{
               put(0, "Question");
               put(1, "Fact");
            }};

            ScreenButtonsHelper screenButton = changeButtons.get(screen);

            Button lastButton = screenButton.buttonHash.get(screenToName.get(screen) + " " + screenButton.buttonHash.size());
            Button exitButton = buttonsList.get("settingsButtons").buttonHash.get("Exit");

            if (lastButton != null) {
                if (e.getWheelRotation() < 0 && screenButton.scrollDisplace < 0) screenButton.scrollDisplace += 10;
                else if (e.getWheelRotation() > 0 && lastButton.ypos + lastButton.height > exitButton.ypos - 10) screenButton.scrollDisplace -= 10;
            }

            initScrollingHash = false;

            repaint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            try {
                Rectangle mouseRect = new Rectangle(e.getX(), e.getY(), 1, 1);

                for (ScreenButtonsHelper screenButtonsHelper : buttonsList.values()) screenButtonsHelper.buttonPressed(mouseRect, () -> new Cursor(Cursor.WAIT_CURSOR), () -> new Cursor(Cursor.DEFAULT_CURSOR));
            } catch (IllegalComponentStateException ignored) { }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            try {
                timer.restart();
                Rectangle mouseRect = new Rectangle(MouseInfo.getPointerInfo().getLocation().x - getLocationOnScreen().x, MouseInfo.getPointerInfo().getLocation().y - getLocationOnScreen().y, 1, 1);

                for (ButtonHoverAnim buttonHoverAnim : buttonAnimList) buttonHoverAnim.reAnimate(mouseRect, this::repaint, () -> setCursor(new Cursor(Cursor.HAND_CURSOR)));
            } catch (IllegalComponentStateException ignored) { }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            try {
                timer.restart();
                Rectangle mouseRect = new Rectangle(MouseInfo.getPointerInfo().getLocation().x - getLocationOnScreen().x, MouseInfo.getPointerInfo().getLocation().y - getLocationOnScreen().y, 1, 1);

                for (ButtonHoverAnim buttonHoverAnim : buttonAnimList) buttonHoverAnim.unAnimate(mouseRect, this::repaint, () -> setCursor(new Cursor(Cursor.DEFAULT_CURSOR)));
            } catch (IllegalComponentStateException ignored) { }
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }
    }

    public static class ChangeQuestionFact extends JPanel implements ActionListener, MouseListener, KeyListener {

        private final ArrayList<ButtonHoverAnim> buttonAnimList = new ArrayList<>();
        public static HashMap<String, ScreenButtonsHelper> buttonsList = new HashMap<>();

        private boolean inithash = false;
        private TextBox activeTextBox = null;

        private final Timer timer;
        public static final int delay = 5;

        boolean isUpperCase = false;

        public ChangeQuestionFact() {
            setFocusable(true);
            setBackground(Color.white);

            addMouseListener(this);
            addKeyListener(this);

            timer = new Timer(delay, this);
            timer.start();
        }

        public void paint(Graphics g) {

        }

        @Override
        public void actionPerformed(ActionEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (activeTextBox != null)
                if (e.getKeyCode() == KeyEvent.VK_SHIFT) isUpperCase = true;
                else activeTextBox.editTextBox(e, isUpperCase);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if (activeTextBox != null && e.getKeyCode() == KeyEvent.VK_SHIFT) isUpperCase = false;
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        public void keyTyped(KeyEvent e) {

        }
    }

}