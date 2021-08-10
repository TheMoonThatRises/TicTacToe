package io.github.rangeremerald.tictactoegammawave.settingsHelper.factQuestionEditHelper;

import io.github.rangeremerald.tictactoegammawave.TicTacToe;
import io.github.rangeremerald.tictactoegammawave.objects.MutipleChoice;
import io.github.rangeremerald.tictactoegammawave.objects.QuestionHolder;
import io.github.rangeremerald.tictactoegammawave.objects.TextHitbox;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class EditMutipleChoice extends MutipleChoice {

    private ImageIcon editIcon = new ImageIcon(getClass().getResource("/images/gameSupport/edit-icon.png"));
    public Rectangle editQuestion, editImageLink;
    public final ArrayList<Rectangle> editAnswerChoices = new ArrayList<>();

    public EditMutipleChoice(QuestionHolder questionHolder) {
        super(questionHolder);
    }

    @Override
    public void drawQuestion(Graphics g, Component c) {
        super.drawQuestion(g, c);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

        editQuestion = new Rectangle(TicTacToe.questionWidth / 2 - g.getFontMetrics(font).stringWidth(questionHolder.question) / 2 - 30, 0, 30, 30);
        g2d.drawImage(editIcon.getImage(), editQuestion.x, editQuestion.y, editQuestion.width, editQuestion.height, null);

        if (questionHolder.questionImage != null) editImageLink = new Rectangle(imageLink.hitBoxOutline.x - 20, imageLink.hitBoxOutline.y - 5, 20, 20);
        else editImageLink = new Rectangle(284, 225, 20, 20);

        g2d.drawImage(editIcon.getImage(), editImageLink.x, editImageLink.y, editImageLink.width, editImageLink.height, null);

        for (TextHitbox textHitbox : questionRectList) {
            editAnswerChoices.add(new Rectangle(textHitbox.checkBox.x + textHitbox.checkBox.width + g.getFontMetrics(font).stringWidth(textHitbox.text) + 15, textHitbox.hitBoxOutline.y, 20, 20));
            g2d.drawImage(editIcon.getImage(), textHitbox.checkBox.x + textHitbox.checkBox.width + g.getFontMetrics(font).stringWidth(textHitbox.text) + 15, textHitbox.hitBoxOutline.y, 20, 20, null);
        }
    }

}
