package io.github.rangeremerald.tictactoegammawave.screens;

import io.github.rangeremerald.tictactoegammawave.TicTacToe;
import io.github.rangeremerald.tictactoegammawave.objects.FactHolder;
import io.github.rangeremerald.tictactoegammawave.objects.MutipleChoice;
import io.github.rangeremerald.tictactoegammawave.objects.MutipleChoice.RectColourHolder;
import io.github.rangeremerald.tictactoegammawave.objects.Piece;
import io.github.rangeremerald.tictactoegammawave.objects.QuestionHolder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Question extends JFrame {

    public Timer runTimer = new Timer(5_000, (event) ->  this.dispose());
    
    public Question(QuestionHolder question, FactHolder fact, Piece piece) {
        add(new QuestionScreen(question, fact, piece));

        setResizable(false);
        setSize(TicTacToe.questionWidth, TicTacToe.questionHeight);
        setLocationRelativeTo(null);
        setTitle("Question Panel");
        setVisible(true);
    }

    private class QuestionScreen extends JPanel implements ActionListener, MouseListener {
        
        MutipleChoice mutipleChoice;
        FactHolder factHolder;

        boolean showFact = false;

        public Timer factTimer = new Timer(2_000, (event) -> {
            showFact = true;
            repaint();
            runTimer.setRepeats(false);
            runTimer.start();
        });

        private Piece userSetPiece;

        public char isCorrect = 'z';

        private final Timer timer;
        public static final int delay = 5;

        public QuestionScreen(QuestionHolder question, FactHolder fact, Piece piece) {
            userSetPiece = piece;
            factHolder = fact;
            mutipleChoice = new MutipleChoice(question);

            setFocusable(true);
            setBackground(Color.white);

            addMouseListener(this);
            
            timer = new Timer(delay, this);
            timer.start();
        }

        public void paint(Graphics g) {
            if (!showFact) {
                if (mutipleChoice != null) mutipleChoice.drawQuestion(g, this);

                if (isCorrect != 'z') {
                    Font font = new Font(Font.MONOSPACED, Font.PLAIN, 40);
                    g.setFont(font);
                    if (isCorrect == 'y') {
                        g.setColor(Color.green);
                        g.drawString("CORRECT", TicTacToe.questionWidth / 2 - g.getFontMetrics(font).stringWidth("CORRECT") / 2, TicTacToe.questionHeight / 2 - 40);
                    } else {
                        g.setColor(Color.red);
                        g.drawString("WRONG", TicTacToe.questionWidth / 2 - g.getFontMetrics(font).stringWidth("WRONG") / 2, TicTacToe.questionHeight / 2 - 40);
                    }
                }
            } else {
                setTitle("Fact Panel");
                factHolder.drawFactImage(g, this);
            }

            g.dispose();
        }

        private void userAnswer(boolean answeredCorrectly) {
            if (answeredCorrectly) TicTacToe.board.setPiece(userSetPiece);
            else {
                boolean allFull = true;
                for (Piece piece : Board.pieceArray)
                    if (piece.piece == 'n') {
                        allFull = false;
                        break;
                    }

                if (allFull) {
                    Board.userWon = true;
                    TicTacToe.board.setQuestionScreen(null);
                }
            }

            factTimer.setRepeats(false);
            factTimer.start();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (isCorrect == 'y' || isCorrect == 'n') return;
            try {
                timer.restart();

                Rectangle mouseRect = new Rectangle(MouseInfo.getPointerInfo().getLocation().x - getLocationOnScreen().x, MouseInfo.getPointerInfo().getLocation().y - getLocationOnScreen().y, 1, 1);

                boolean handReached = false;

                for (RectColourHolder questionRect : mutipleChoice.questionRectList)
                    if (mouseRect.intersects(questionRect.hitBoxOutline)) {
                        handReached = true;
                        setCursor(new Cursor(Cursor.HAND_CURSOR));
                        questionRect.colour = Color.orange;
                    } else questionRect.colour = Color.black;

                if (!handReached) setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                repaint();
            } catch (Exception ignored) { }
        }
        
        @Override
        public void mouseReleased(MouseEvent e) {
            if (isCorrect == 'y' || isCorrect == 'n') return;

            Rectangle mouseRect = new Rectangle(MouseInfo.getPointerInfo().getLocation().x - getLocationOnScreen().x, MouseInfo.getPointerInfo().getLocation().y - getLocationOnScreen().y, 1, 1);
            
            for (RectColourHolder questionRect : mutipleChoice.questionRectList) if (mouseRect.intersects(questionRect.hitBoxOutline)) {
                boolean userCorrect = questionRect.answer.equals(mutipleChoice.questionHolder.answerPool.get(Math.toIntExact(mutipleChoice.questionHolder.answer)));
                if (userCorrect) isCorrect = 'y';
                else isCorrect = 'n';

                if (isCorrect == 'y') questionRect.colour = Color.green;
                else questionRect.colour = Color.red;

                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                
                repaint();

                userAnswer(userCorrect);
            }
        }


        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

}
