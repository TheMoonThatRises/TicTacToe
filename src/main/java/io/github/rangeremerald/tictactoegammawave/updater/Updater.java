package io.github.rangeremerald.tictactoegammawave.updater;

import io.github.rangeremerald.tictactoegammawave.Private;

import java.security.MessageDigest;

public class Updater {

    public Updater() throws Exception {
        String selfFilePath = Updater.class.getProtectionDomain().getCodeSource().getLocation().getPath();

        MessageDigest md = MessageDigest.getInstance("SHA");
        String shaGame = new CheckSum().checkSum(selfFilePath, md);

        String url = new Private().getServerUrl() + "interacts/updater.php?user_sha1=" + shaGame;

        try {
            String newGame = new HttpUrlConnection().httpUrlConnection(url);

            if (newGame.contains(".jar")) {
                Process runtime = Runtime.getRuntime().exec("java -jar " + selfFilePath);
                if (runtime == null) throw new Exception("Updated jar file did not run.");

                System.exit(0);
            } else throw new Exception(newGame);
        } catch (Exception exception) { exception.printStackTrace(); }

    }

}