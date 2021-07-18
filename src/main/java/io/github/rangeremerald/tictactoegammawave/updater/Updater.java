package io.github.rangeremerald.tictactoegammawave.updater;

import io.github.rangeremerald.tictactoegammawave.Private;

import java.security.MessageDigest;

public class Updater {

    public Updater() throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA");
        String shaGame = new CheckSum().checkSum(Updater.class.getProtectionDomain().getCodeSource().getLocation().getPath(), md);

        String newGame = new HttpUrlConnection().httpUrlConnection(new Private().getServerUrl() + shaGame);

        if (newGame != null) {
            Process runtime = Runtime.getRuntime().exec("java -jar " + Updater.class.getProtectionDomain().getCodeSource().getLocation().getPath());
            if (runtime == null) throw new Exception("Updated jar file did not run.");

            System.exit(0);
        }

    }

}