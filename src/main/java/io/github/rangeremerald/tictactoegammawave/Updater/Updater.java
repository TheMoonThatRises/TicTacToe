package io.github.rangeremerald.tictactoegammawave.Updater;

import io.github.rangeremerald.tictactoegammawave.Private;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Updater {

    public Updater(String oldGameFile) throws IOException, NoSuchAlgorithmException {
        if (oldGameFile != null) {
            try {
                File oldGame = new File(oldGameFile);
                oldGame.delete();
            } catch (SecurityException exception) { exception.printStackTrace(); }
        }

        MessageDigest md = MessageDigest.getInstance("SHA");
        String shaGame = new CheckSum().checkSum(Updater.class.getProtectionDomain().getCodeSource().getLocation().getPath(), md);

        String newGame = new HttpUrlConnection().httpUrlConnection(new Private().getServerUrl() + shaGame);

        if (newGame != null) {
            Runtime.getRuntime().exec("java -jar " + newGame + " " + Updater.class.getProtectionDomain().getCodeSource().getLocation().getPath());
            System.exit(0);
        }

    }

}