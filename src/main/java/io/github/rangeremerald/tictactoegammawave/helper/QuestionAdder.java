package io.github.rangeremerald.tictactoegammawave.helper;

import io.github.rangeremerald.tictactoegammawave.objects.QuestionHolder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;

public class QuestionAdder {

    public List<QuestionHolder> questionAdder() {
        List<QuestionHolder> questionPool = new ArrayList<>();

        JSONArray questionArray = null;
        try {
            questionArray = (JSONArray) ((JSONObject) new JSONParser().parse(new ReadJsonFile().readJsonFile("questions.json"))).get("questionList");
        } catch (ParseException ignored) { }

        assert questionArray != null;
        for (Object object : questionArray) {
            if (object instanceof JSONObject) {
                JSONObject newObject = (JSONObject) object;
                questionPool.add(new QuestionHolder((String) newObject.get("question"), (JSONArray) newObject.get("answerPool"), (Long) newObject.get("answer"), (String) newObject.get("imageName")));
            }
        }
        
        return questionPool;
    }

}
