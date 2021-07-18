package io.github.rangeremerald.tictactoegammawave.updater;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class HttpUrlConnection {

    public String httpUrlConnection(String url) {
        try {
            URLConnection connection = new URL(url).openConnection();
            connection.setRequestProperty("Content-Type", "application/json");
            InputStream webresponse = connection.getInputStream();

            String response = "";
            JSONObject jsonResponse;

            try (Scanner scanner = new Scanner(webresponse)) { response += scanner.useDelimiter("\\A").next(); }

            jsonResponse = (JSONObject) new JSONParser().parse(response);
            JSONObject data;

            try {
                data = (JSONObject) new JSONParser().parse(String.valueOf(jsonResponse.get("data")));
            } catch (Exception exception) {
                return null;
            }

            if ((boolean) jsonResponse.get("success") && !((boolean) data.get("upToDate"))) {
                new DownloadFile(String.valueOf(data.get("curlLink")));
                return String.valueOf(data.get("jarName"));
            }
        } catch (IOException | ParseException exception) { exception.printStackTrace(); }
        return null;
    }

}
