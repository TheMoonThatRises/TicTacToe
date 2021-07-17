package io.github.rangeremerald.tictactoegammawave.objects;

import org.json.simple.JSONArray;

import javax.swing.*;
import java.util.List;

public class QuestionHolder {

    public String question;
    public Long answer;
    public ImageIcon questionImage;
    public List<String> answerPool;

    public QuestionHolder(String question, JSONArray answerPool, Long answer, String fileName) {
        this.question = question;
        this.answer = answer;
        this.answerPool = (List<String>) answerPool;
        
        if (fileName != null) this.questionImage = new ImageIcon(getClass().getResource("/images/" + fileName));
        else this.questionImage = null;
    }

}
