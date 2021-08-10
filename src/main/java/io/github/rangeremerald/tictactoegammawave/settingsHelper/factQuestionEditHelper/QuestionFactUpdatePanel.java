package io.github.rangeremerald.tictactoegammawave.settingsHelper.factQuestionEditHelper;

import io.github.rangeremerald.tictactoegammawave.TicTacToe;
import io.github.rangeremerald.tictactoegammawave.helper.ButtonHoverAnim;
import io.github.rangeremerald.tictactoegammawave.helper.ReadJsonFile;
import io.github.rangeremerald.tictactoegammawave.objects.MutipleChoice;
import io.github.rangeremerald.tictactoegammawave.objects.QuestionHolder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class QuestionFactUpdatePanel extends JPanel implements ActionListener, MouseListener, KeyListener {

    private JSONObject factJson = null;
    private JSONObject imageCitation = null;
    private JSONObject updateQuestionJson = null;

    private EditMutipleChoice mutipleChoice = null;

    private UpdatePanelButtons utilButtons = new UpdatePanelButtons();
    private final ArrayList<ButtonHoverAnim> buttonAnimList = new ArrayList<>();
    private HoverEditIcon hoverEditIcon = null;

    private boolean initHash = false;

    private Timer timer = null;
    private int delay = 5;

    public QuestionFactUpdatePanel(String questionNumber) {
        setVariables(Integer.parseInt(questionNumber.toLowerCase().replace("question ", "")));

        TicTacToe.settingsScreen.setTitle("Settings - " + questionNumber + " Edit Panel");
        TicTacToe.settingsScreen.setSize(TicTacToe.questionWidth, TicTacToe.questionHeight);

        setFocusable(true);
        setBackground(Color.white);

        addMouseListener(this);
        addKeyListener(this);
    }

    private void setVariables(int questionPos) {
        try {
            factJson = (JSONObject) new JSONParser().parse(new ReadJsonFile().readJsonFile("questions.json"));
            imageCitation = (JSONObject) new JSONParser().parse(new ReadJsonFile().readJsonFile("imageCitations.json"));
        } catch (ParseException ignored) { }

        updateQuestionJson = (JSONObject) ((JSONArray) factJson.get("questionList")).get(questionPos - 1);
        timer = new Timer(delay, this);
        timer.start();

        mutipleChoice = new EditMutipleChoice(new QuestionHolder((String) updateQuestionJson.get("question"), (JSONArray) updateQuestionJson.get("answerPool"), (Long) updateQuestionJson.get("answer"), (String) updateQuestionJson.get("imageName")));
        buttonAnimList.add(new ButtonHoverAnim(utilButtons));
        hoverEditIcon = new HoverEditIcon(mutipleChoice);
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

        if (!initHash) initHash = utilButtons.initHash(g);

        mutipleChoice.drawQuestion(g, this);

        utilButtons.drawButtons(g);

        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (TicTacToe.settingsScreen != null && TicTacToe.settingsScreen.isVisible() && utilButtons != null && !buttonAnimList.isEmpty()) {
                timer.restart();
                Rectangle mouseRect = new Rectangle(MouseInfo.getPointerInfo().getLocation().x - getLocationOnScreen().x, MouseInfo.getPointerInfo().getLocation().y - getLocationOnScreen().y, 1, 1);

                boolean isHover = hoverEditIcon.changeCursor(mouseRect, () -> setCursor(new Cursor(Cursor.HAND_CURSOR)), () -> setCursor(new Cursor(Cursor.DEFAULT_CURSOR)));
                if (!isHover) for (ButtonHoverAnim buttonHoverAnim : buttonAnimList) buttonHoverAnim.animate(mouseRect, this::repaint, () -> setCursor(new Cursor(Cursor.HAND_CURSOR)), () -> setCursor(new Cursor(Cursor.DEFAULT_CURSOR)));
            }
        } catch (IllegalComponentStateException ignored) { }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        try {
            Rectangle mouseRect = new Rectangle(e.getX(), e.getY(), 1, 1);

            utilButtons.buttonPressed(mouseRect, () -> new Cursor(Cursor.WAIT_CURSOR), () -> new Cursor(Cursor.DEFAULT_CURSOR));
        } catch (IllegalComponentStateException ignored) { }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

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
}
