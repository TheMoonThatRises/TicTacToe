package io.github.rangeremerald.tictactoegammawave.objects;

import io.github.rangeremerald.tictactoegammawave.TicTacToe;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MutipleChoice {

    public QuestionHolder questionHolder;
    public int[] imageCoords;
    public int textSize;
    public Font font;
    public List<TextHitbox> questionRectList = new ArrayList<>();
    public TextHitbox imageLink;
    public Font imageLinkFont;

    private void initQuestion() {
        for (int position = 0; position < questionHolder.answerPool.size(); position++) {
            Rectangle hitbox = new Rectangle(0, 250 + textSize * position, TicTacToe.questionWidth, 10);
            Rectangle checkbox = new Rectangle(hitbox.x, hitbox.y, 10, 10);
            
            questionRectList.add(new TextHitbox(hitbox, checkbox, questionHolder.answerPool.get(position)));
        }
        if (questionHolder.questionImage != null) imageCoords = new int[]{TicTacToe.questionWidth / 2 - questionHolder.questionImage.getIconWidth() / 2, 30};
    }

    public MutipleChoice(QuestionHolder questionHolder) {
        this.questionHolder = questionHolder;
        this.textSize = 20;
        this.font = new Font(Font.MONOSPACED, Font.PLAIN, textSize);
        this.imageLinkFont = new Font(Font.MONOSPACED, Font.PLAIN, 10);

        initQuestion();
    }

    public void drawQuestion(Graphics g, Component c) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

        if (questionHolder.questionImage != null) {
            if (imageLink == null) {
                int imageLinkWidth = g.getFontMetrics(imageLinkFont).stringWidth(questionHolder.questionImageLink);
                Rectangle imageLinkHitbox = new Rectangle(TicTacToe.questionWidth / 2 - imageLinkWidth / 2, imageCoords[1] + 210 - imageLinkFont.getSize(), imageLinkWidth, imageLinkFont.getSize());
                imageLink = new TextHitbox(imageLinkHitbox, null, questionHolder.questionImageLink);
                imageLink.colour = Color.blue.brighter();
            }
            g.setFont(imageLinkFont);
            g.setColor(imageLink.colour);

            questionHolder.questionImage.paintIcon(c, g, imageCoords[0], imageCoords[1]);
            g.drawString(imageLink.text, imageLink.hitBoxOutline.x, imageLink.hitBoxOutline.y + imageLinkFont.getSize());
        }

        g.setColor(Color.black);
        g.setFont(font);
        
        g.drawString(questionHolder.question, TicTacToe.questionWidth / 2 - g.getFontMetrics(font).stringWidth(questionHolder.question) / 2, 20);

        for (TextHitbox colourHolder : questionRectList) {
            g2d.setStroke(new BasicStroke(1.5F));

            g.setColor(colourHolder.colour);

            g.drawRect(colourHolder.checkBox.x, colourHolder.checkBox.y, colourHolder.checkBox.width, colourHolder.checkBox.height);

            g2d.setStroke(new BasicStroke());

            g.setFont(font);

            g.drawString(colourHolder.text, colourHolder.checkBox.x + 20, colourHolder.checkBox.y + 15);
        }

    }

}
