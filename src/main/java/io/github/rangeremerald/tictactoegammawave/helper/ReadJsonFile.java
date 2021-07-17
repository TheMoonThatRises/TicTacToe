package io.github.rangeremerald.tictactoegammawave.helper;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class ReadJsonFile {

    public String readJsonFile(String fileName) {
        JSONParser jsonParser = new JSONParser();

        try (InputStreamReader reader = new InputStreamReader(Objects.requireNonNull(getClass().getResourceAsStream("/" + fileName)), StandardCharsets.UTF_8)) {

            return String.valueOf(jsonParser.parse(reader));

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        
        return null;
    }

}
