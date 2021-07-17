package io.github.rangeremerald.tictactoegammawave.helper;

import io.github.rangeremerald.tictactoegammawave.objects.FactHolder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;

public class FactAdder {

    public List<FactHolder> factAdder() {
        List<FactHolder> factPool = new ArrayList<>();

        JSONArray factArray = null;
        try {
            factArray = (JSONArray) ((JSONObject) new JSONParser().parse(new ReadJsonFile().readJsonFile("didyouknowfacts.json"))).get("facts");
        } catch (ParseException ignored) { }

        assert factArray != null;
        for (Object object : factArray) {
            if (object instanceof JSONObject) {
                JSONObject newObject = (JSONObject) object;
                factPool.add(new FactHolder((String) newObject.get("image"), (String) newObject.get("fact")));
            }
        }

        return factPool;
    }

}
