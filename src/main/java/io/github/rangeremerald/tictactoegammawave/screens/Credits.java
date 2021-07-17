package io.github.rangeremerald.tictactoegammawave.screens;

import io.github.rangeremerald.tictactoegammawave.TicTacToe;
import io.github.rangeremerald.tictactoegammawave.creditHelper.CreditButtons;
import io.github.rangeremerald.tictactoegammawave.creditHelper.WordWrap;
import io.github.rangeremerald.tictactoegammawave.helper.ButtonHoverAnim;
import io.github.rangeremerald.tictactoegammawave.helper.ReadJsonFile;
import io.github.rangeremerald.tictactoegammawave.objects.Button;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class Credits extends JFrame {

    public void credits() {
        add(new CreditScreen());

        setMinimumSize(new Dimension(TicTacToe.windowWidth * 2, TicTacToe.windowHeight * 2));
        setLocationRelativeTo(null);
        setTitle("Credits");
        setVisible(true);
    }

    private static class CreditScreen extends JPanel implements ActionListener, MouseListener {

        private final ButtonHoverAnim buttonHoverAnim;
        private final CreditButtons creditButtons;

        private boolean inithash = false;

        private final int textSize = 15;

        List<String> creditList;
        
        {
            try {
                creditList = (List<String>) ((JSONObject) new JSONParser().parse(new ReadJsonFile().readJsonFile("citation.json"))).get("citation");
            } catch (ParseException ignored) { }
        }
        
        private final Timer timer;
        public static final int delay = 5;
        
        public CreditScreen() {
            creditButtons = new CreditButtons();
            buttonHoverAnim = new ButtonHoverAnim(creditButtons);

            setFocusable(true);
            setBackground(Color.white);
            
            addMouseListener(this);
            
            timer = new Timer(delay, this);
            timer.start();

            addComponentListener(new ComponentAdapter() {
                public void componentResized(ComponentEvent c) {
                    if (TicTacToe.creditScreen.isDisplayable() && creditButtons != null && buttonHoverAnim != null) {
                        inithash = false;
                        repaint();
                    }
                }
            });
        }

        public void paint(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
            
            if (!inithash) inithash = creditButtons.initHash(g);

            g.setColor(Color.black);
            g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, textSize));

            int nextLineY = 0;

            for (String s : creditList) nextLineY = new WordWrap().WordWrap(g, s, 10, nextLineY + textSize * 2, getWidth() - 10);

            creditButtons.drawButtons(g);

            g.dispose();
        }

        

        @Override
        public void actionPerformed(ActionEvent e) {
            if (TicTacToe.creditScreen != null && TicTacToe.creditScreen.isDisplayable() && creditButtons != null && buttonHoverAnim != null) {
                try {
                    timer.restart();
                    Rectangle mouseRect = new Rectangle(MouseInfo.getPointerInfo().getLocation().x - getLocationOnScreen().x, MouseInfo.getPointerInfo().getLocation().y - getLocationOnScreen().y, 1, 1);

                    buttonHoverAnim.reAnimate(mouseRect, this::repaint, () -> setCursor(new Cursor(Cursor.HAND_CURSOR)), () -> setCursor(new Cursor(Cursor.DEFAULT_CURSOR)));
                } catch (Exception ignored) { }
            }
        }
        
        @Override
        public void mouseReleased(MouseEvent e) {
            Rectangle mouseRect = new Rectangle(e.getX(), e.getY(), 1, 1);

            for (Button button : creditButtons.buttonHash.values()) {
                if (mouseRect.intersects(button.buttonRect)) {
                    if (button.drawStringTotal.text.equalsIgnoreCase("exit")) {
                        setCursor(new Cursor(Cursor.WAIT_CURSOR));
                        TicTacToe.creditScreen.dispose();
                        TicTacToe.creditScreen = null;
                        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    }
                }
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        public void mouseClicked(MouseEvent e) {
            
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }
    }

}
