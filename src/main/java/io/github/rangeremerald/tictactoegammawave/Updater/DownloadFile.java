package io.github.rangeremerald.tictactoegammawave.Updater;

import io.github.rangeremerald.tictactoegammawave.TicTacToe;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class DownloadFile {

    public DownloadFile(String fromUrl) {
        try {
            URL url = new URL(fromUrl);
            OutputStream out = new BufferedOutputStream(new FileOutputStream(TicTacToe.class.getProtectionDomain().getCodeSource().getLocation().getPath()));
            URLConnection conn = url.openConnection();
            String encoded = Base64.getEncoder().encodeToString(("username"+":"+"password").getBytes(StandardCharsets.UTF_8));  //Java 8
            conn.setRequestProperty("Authorization", "Basic "+ encoded);
            InputStream in = conn.getInputStream();
            byte[] buffer = new byte[1024];

            int numRead;
            while ((numRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, numRead);
            }

            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
