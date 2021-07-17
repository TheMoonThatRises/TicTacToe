package io.github.rangeremerald.tictactoegammawave.screens;

import io.github.rangeremerald.tictactoegammawave.TicTacToe;
import io.github.rangeremerald.tictactoegammawave.boardHelper.BoardButtons;
import io.github.rangeremerald.tictactoegammawave.boardHelper.BotMove;
import io.github.rangeremerald.tictactoegammawave.boardHelper.CheckMove;
import io.github.rangeremerald.tictactoegammawave.helper.ButtonHoverAnim;
import io.github.rangeremerald.tictactoegammawave.helper.FactAdder;
import io.github.rangeremerald.tictactoegammawave.helper.QuestionAdder;
import io.github.rangeremerald.tictactoegammawave.objects.Button;
import io.github.rangeremerald.tictactoegammawave.objects.FactHolder;
import io.github.rangeremerald.tictactoegammawave.objects.Piece;
import io.github.rangeremerald.tictactoegammawave.objects.QuestionHolder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Board extends JPanel implements KeyListener, MouseListener, ActionListener {

    public static final Piece[] pieceArray = new Piece[9];

    public static final int rectSpacing = 80;

    public static final int tictactoeSize = 3;
    public static final int rectThickness = 5;
    public static final int rectLength = rectSpacing * tictactoeSize + rectThickness * (tictactoeSize - 1);
    private final int bound = rectSpacing;

    public static final char userPiece = 'x';
    public static final char botPiece = 'o';

    public static boolean gameDone = false;
    public static boolean userWon = false;
    private ArrayList<Object> winBar;

    private static boolean initHash = false;
    private String addPlayAgain = "false";
    private ButtonHoverAnim buttonHoverAnim = null;

    private final ImageIcon backgroundImage = new ImageIcon(getClass().getResource("/images/gammaray.jpg"));

    protected BoardButtons boardButtons = null;

    List<QuestionHolder> questionPool = new ArrayList<>();
    List<FactHolder> factPool = new ArrayList<>();

    private final Timer timer;
    public static final int delay = 5;

    public Board() {
        initBoard();
        addListeners();
        timer = new Timer(delay, this);
        timer.start();
    }

    private void addListeners() {
        addKeyListener(this);
        addMouseListener(this);
    }

    private void initBoard() {
        boardButtons = new BoardButtons();
        buttonHoverAnim = new ButtonHoverAnim(boardButtons);
        setFocusable(true);

        int amount = 0;

        for (int y = 0; y < tictactoeSize; y++) {
            for (int x = 0; x < tictactoeSize; x++) {
                pieceArray[amount] = new Piece((rectSpacing + rectThickness) * x + bound, (rectSpacing + rectThickness) * y + bound, 'n', Color.red);
                amount++;
            }
        }
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        
        backgroundImage.paintIcon(this, g, -100, 0);
        if (!initHash) initHash = boardButtons.initHash(g);

        if (addPlayAgain.equals("do")) {
            addPlayAgain = boardButtons.addButtonReInit("Restart", g);
            repaint();
        }

        boardButtons.drawButtons(g);

        g.setColor(Color.white);

        for (int i = 1; i < tictactoeSize; i++) {
            int xypos = (rectSpacing + rectThickness) * i + (bound - rectThickness);

            g.fillRect(xypos, bound, rectThickness, rectLength);
            g.fillRect(bound, xypos, rectLength, rectThickness);
        }

        for (Piece piece : pieceArray) {
            switch (piece.piece) {
                case 'o':
                    g.setColor(Color.white);
                    break;
                case 'x':
                    g.setColor(piece.colour);
                    break;
                case '□':
                    g.setColor(Color.orange);
                    break;
            }

            g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, rectSpacing));
            if (piece.piece != 'n') g.drawString(String.valueOf(piece.piece), piece.xpos + rectSpacing / 5, piece.ypos + 3 * rectSpacing / 4);
        }

        if (gameDone || userWon) {
            String end = "You have won!";
            Color endColour = Color.green;
            Font endFont = new Font(Font.MONOSPACED, Font.PLAIN, 20);
            if (!userWon) {
                end = "You have lost!";
                endColour = Color.red;
            }
            g.setColor(endColour);
            g.setFont(endFont);
            if (gameDone) {
                g.drawString(end, rectSpacing + rectLength / 2 - g.getFontMetrics(endFont).stringWidth(end) / 2, 400);

                Rectangle winBarRect = (Rectangle) winBar.get(1);

                if ((Double) winBar.get(0) > 0)
                    winBarRect = new Rectangle(winBarRect.x - winBarRect.height / 2, winBarRect.y - winBarRect.height / 2, (int) Math.ceil(winBarRect.width * Math.sqrt(2)), winBarRect.height);
                else if ((Double) winBar.get(0) < 0)
                    winBarRect = new Rectangle(winBarRect.x, winBarRect.y, (int) Math.ceil(winBarRect.width * Math.sqrt(2)), winBarRect.height);

                g2d.setColor(Color.red);
                g2d.rotate(Math.toRadians((Double) winBar.get(0)), ((Rectangle) winBar.get(1)).x, ((Rectangle) winBar.get(1)).y);
                g2d.draw(winBarRect);
                g2d.fill(winBarRect);

                g2d.rotate(Math.toRadians(-(Double) winBar.get(0)), ((Rectangle) winBar.get(1)).x, ((Rectangle) winBar.get(1)).y);

            } else if (userWon) {
                end = "Draw.";
                g.setColor(Color.orange);
                g.drawString(end, rectSpacing + rectLength / 2 - g.getFontMetrics(endFont).stringWidth(end) / 2, 400);
            }
            String playAgain = "Press enter to play again";
            g.drawString(playAgain, rectSpacing + rectLength / 2 - g.getFontMetrics(endFont).stringWidth(playAgain) / 2, 425);
        }

        g.dispose();
    }

    public void setQuestionScreen(Piece piece) {
        if (piece != null && piece.piece == 'n') {
            piece.piece = '□';
            
            if (TicTacToe.questionScreen != null) {
                TicTacToe.questionScreen.dispose();
                TicTacToe.questionScreen = null;
            }

            if (addPlayAgain.equals("false")) addPlayAgain = "do";

            if (questionPool.size() == 0) questionPool = new QuestionAdder().questionAdder();
            if (factPool.size() == 0) factPool = new FactAdder().factAdder();

            QuestionHolder question = questionPool.get((int) Math.round(Math.random() * (questionPool.size() - 1)));
            FactHolder fact = factPool.get((int) Math.round(Math.random() * (factPool.size() - 1)));
            TicTacToe.questionScreen = new Question(question, fact, piece);
            questionPool.remove(question);
            factPool.remove(fact);
        }
        
        repaint();
    }

    public void setPiece(Piece piece) {
        for (Piece charPiece : pieceArray) if (charPiece.piece.equals('□')) charPiece.piece = 'n';

        piece.piece = userPiece;

        ArrayList<Object> checkUser = CheckMove.checkMove(userPiece);
        if (!checkUser.get(1).equals(0)) {
            userWon = true;
            gameDone = true;
            winBar = checkUser;
        } else if (CheckMove.ifDraw()) {
            userWon = true;
        } else {
            new BotMove();

            ArrayList<Object> checkBot = CheckMove.checkMove(botPiece);
            if (!checkBot.get(1).equals(0)) {
                userWon = false;
                gameDone = true;
                winBar = checkBot;
            } else if (CheckMove.ifDraw()) {
                userWon = true;
            }
        }
        
        repaint();
    }

    private void restartGame() {
        initHash = boardButtons.removeButtonInit("Restart");
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        winBar = new ArrayList<>();
        addPlayAgain = "false";
        gameDone = false;
        userWon = false;
        initBoard();
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Rectangle mouseRect = new Rectangle(e.getX(), e.getY(), 1, 1);

        for (Button button : boardButtons.buttonHash.values()) {
            if (mouseRect.intersects(button.buttonRect)) {
                switch (button.drawStringTotal.text.toLowerCase()) {
                    case "quit":
                        setCursor(new Cursor(Cursor.WAIT_CURSOR));
                        System.exit(0);
                        break;
                    case "credits":
                        TicTacToe.creditScreen = new Credits();
                        TicTacToe.creditScreen.credits();
                        break;
                    case "restart":
                        restartGame();
                        return;
                }
            }
        }

        if (gameDone) return;

        for (Piece piece : pieceArray) {
            if (mouseRect.intersects(piece.rectangle)) {
                setQuestionScreen(piece);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ENTER && (gameDone || userWon)) {
            restartGame();
        } else {
            try {
                HashMap<Integer, Integer> pieceToCode = new HashMap<>();
                int amount = 0;
                for (int pieceCode = 49; pieceCode < 58; pieceCode++) {
                    pieceToCode.put(pieceCode, amount);
                    amount++;
                }
                int key = e.getKeyChar();

                if (key > 48 && key < 58) {
                    setQuestionScreen(pieceArray[pieceToCode.get(key)]);
                }
            } catch (Exception ignored) { }
        }
    }

    //Controls mouse hovers functions

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean isButtonAnimated = false;

        try {
            timer.restart();
            Rectangle mouseRect = new Rectangle(MouseInfo.getPointerInfo().getLocation().x - getLocationOnScreen().x, MouseInfo.getPointerInfo().getLocation().y - getLocationOnScreen().y, 1, 1);

            if (boardButtons != null) isButtonAnimated = buttonHoverAnim.reAnimate(mouseRect, this::repaint, () -> setCursor(new Cursor(Cursor.HAND_CURSOR)), () -> setCursor(new Cursor(Cursor.DEFAULT_CURSOR)));

            if (!isButtonAnimated) {
                //If mouse hovers over an open space
                int pieceAmount = 0;

                for (Piece piece : pieceArray) {
                    if (gameDone || userWon) {
                        pieceAmount = 9;
                        break;
                    } else if (mouseRect.intersects(piece.rectangle) && piece.piece.equals('n')) {
                        setCursor(new Cursor(Cursor.HAND_CURSOR));
                        pieceAmount--;
                        break;
                    } else pieceAmount++;
                }

                if (pieceAmount >= Math.pow(tictactoeSize, 2)) setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        } catch (Exception ignored) { }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        try {
            timer.restart();
            Rectangle mouseRect = new Rectangle(MouseInfo.getPointerInfo().getLocation().x - getLocationOnScreen().x, MouseInfo.getPointerInfo().getLocation().y - getLocationOnScreen().y, 1, 1);

            for (Button button : buttonHoverAnim.toAnimationBack) {
                if (mouseRect.intersects(button.buttonRect)) return;
                button.drawStringTotal.textColour = Color.black;
                if (button.buttonAnimation.width == 0) {
                    buttonHoverAnim.toAnimationBack.remove(button);
                    break;
                }
                button.buttonAnimation.width -= 5;
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                repaint();
            }
        } catch (Exception ignored) { }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

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
    
}