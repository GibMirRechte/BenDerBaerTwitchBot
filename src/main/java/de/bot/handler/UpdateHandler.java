package de.bot.handler;

import de.bot.main.Main;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class UpdateHandler {

    private final String currentVersion = "0.1";
    public boolean newUpdateAvailable = false;
    private String newestVersion = "";
    String newUpdateDownloadLink = "https://cdn.discordapp.com/attachments/869203961884844063/1137402711780896788/TwitchBot.jar";

    private void checkForUpdate() {
        try (Socket socket = new Socket("", 3428)) {
            OutputStream outputStream = socket.getOutputStream();
            PrintStream printStream = new PrintStream(outputStream);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            printStream.println("GET_VERSION");
            printStream.println("BenDerBaer_Twitch_VIP_Bot");

            newestVersion = bufferedReader.readLine();
            newUpdateDownloadLink = bufferedReader.readLine();

            if (!currentVersion.equalsIgnoreCase(newestVersion)) {
                newUpdateAvailable = true;
            }
        } catch (Exception ignored) {
        }
    }

    public boolean hasNewUpdate() {
        return newUpdateAvailable;
    }

    public void startUpdateChecker() {
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(60000);
                    checkForUpdate();

                    if (hasNewUpdate()) {
                        downloadNewestVersion();
                    }

                } catch (Exception ignored) {
                }
            }
        }).start();
    }

    public void downloadNewestVersion() throws IOException {
        try (InputStream in = new URL(newUpdateDownloadLink).openStream();
             ReadableByteChannel rbc = Channels.newChannel(in);
             FileOutputStream fos = new FileOutputStream(getFilePath().replace(".jar", "") + "-1.jar")) {
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        }
    }

    public String getFilePath() {
        String jarPath = Main.class.getProtectionDomain().getCodeSource().getLocation().getPath();

        if (jarPath.startsWith("file:")) {
            jarPath = jarPath.substring("file:".length());
        }
        jarPath = jarPath.replaceAll("%20", " ");
        jarPath = jarPath.replace('/', File.separatorChar);
        return jarPath;
    }

}
