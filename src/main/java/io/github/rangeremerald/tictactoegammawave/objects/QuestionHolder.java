package io.github.rangeremerald.tictactoegammawave.objects;

import io.github.rangeremerald.tictactoegammawave.helper.ReadJsonFile;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.util.List;

public class QuestionHolder {

    public String question;
    public Long answer;
    public ImageIcon questionImage;
    public String questionImageLink;
    public List<String> answerPool;

    public QuestionHolder(String question, JSONArray answerPool, Long answer, String fileName) {
        this.question = question;
        this.answer = answer;
        this.answerPool = (List<String>) answerPool;
        
        if (fileName != null) {
            this.questionImage = new ImageIcon(getClass().getResource("/images/" + fileName));
            try {
                this.questionImageLink = (String) ((JSONObject) new JSONParser().parse(new ReadJsonFile().readJsonFile("imageCitations.json"))).get(fileName);
            } catch (ParseException ignored) { }
        } else this.questionImage = null;
    }

}
