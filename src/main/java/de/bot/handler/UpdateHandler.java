package de.bot.handler;

import de.bot.main.Main;
import de.bot.utils.Announcement;
import de.bot.utils.ToolData;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class UpdateHandler {

    private final String currentVersion = "0.2";
    private boolean newUpdateAvailable = false;
    static UpdateHandler instance;
    private String newestVersion = "";
    String newUpdateDownloadLink = "";
    Announcement announcement;
    private final ToolData toolData = new ToolData(false, true);

    public ToolData getToolData() {
        return toolData;
    }

    public static UpdateHandler getInstance() {
        if (instance == null) {
            instance = new UpdateHandler();
        }

        return instance;
    }

    public Announcement getAnnouncement() {

        if (announcement == null) {
            try {
                Socket socket = new Socket("45.93.249.139", 3459);
                OutputStream outputStream = socket.getOutputStream();
                PrintStream printStream = new PrintStream(outputStream);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                printStream.println("GET_ANNOUNCEMENT");
                String text = bufferedReader.readLine();
                int typeID = Integer.parseInt(bufferedReader.readLine());
                String color = bufferedReader.readLine();
                boolean canUseTool = Boolean.parseBoolean(bufferedReader.readLine());
                boolean isActive = Boolean.parseBoolean(bufferedReader.readLine());

                announcement = new Announcement(text, typeID, color, canUseTool, isActive);
            } catch (Exception e) {
                announcement = new Announcement("Es konnte keine Verbindung zum Server aufgebaut werden. Bitte versuche es später erneut.", 1, "0xD03F3F", false, true);
            }
        }

        return announcement;
    }

    public String getCurrentVersion() {
        return currentVersion;
    }

    public void checkForUpdate() {
        try (Socket socket = new Socket("45.93.249.139", 3459)) {
            OutputStream outputStream = socket.getOutputStream();
            PrintStream printStream = new PrintStream(outputStream);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            printStream.println("GET_VERSION");

            newestVersion = bufferedReader.readLine();
            newUpdateDownloadLink = bufferedReader.readLine();

            boolean maintenance_autoshout = Boolean.parseBoolean(bufferedReader.readLine());
            boolean maintenance_autovip = Boolean.parseBoolean(bufferedReader.readLine());
            getToolData().updateMaintenance(maintenance_autoshout, maintenance_autovip);

            if (!currentVersion.equalsIgnoreCase(newestVersion)) {
                newUpdateAvailable = true;
                new Thread(() -> {
                    try {
                        downloadNewestVersion();
                    } catch (IOException ignored) {
                    }
                }).start();
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
             FileOutputStream fos = new FileOutputStream(getFilePath())) {
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
