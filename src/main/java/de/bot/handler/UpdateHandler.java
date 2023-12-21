package de.bot.handler;

import de.bot.main.Main;
import de.bot.utils.AccountRank;
import de.bot.utils.Announcement;
import de.bot.utils.ToolData;

import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.HashMap;

public class UpdateHandler {

    private final String currentVersion = "0.3";
    private boolean newUpdateAvailable = false;
    static UpdateHandler instance;
    private String newestVersion = "";
    String newUpdateDownloadLink = "";
    Announcement announcement;
    private static ToolData toolData = new ToolData(true, true, "");

    AccountHandler accountHandler = AccountHandler.getInstance();

    public ToolData getToolData() {
        return toolData;
    }

    public static UpdateHandler getInstance() {
        if (instance == null) {
            instance = new UpdateHandler();
        }

        return instance;
    }

    public void updateAnnouncement(Announcement announcement) {
        this.announcement = announcement;
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
            printStream.println(currentVersion);

            newestVersion = bufferedReader.readLine();
            newUpdateDownloadLink = bufferedReader.readLine();

            boolean maintenance_autoshout = Boolean.parseBoolean(bufferedReader.readLine());
            boolean maintenance_autovip = Boolean.parseBoolean(bufferedReader.readLine());
            String changelog = bufferedReader.readLine();

            getToolData().updateMaintenance(maintenance_autoshout, maintenance_autovip);
            getToolData().setChangelog(changelog);

            int ranks = Integer.parseInt(bufferedReader.readLine());
            HashMap<String, AccountRank> accountRanks = new HashMap<>();
            for (int i = 0; i < ranks; i++) {
                String title = bufferedReader.readLine();
                Color rgbColor = Color.decode(bufferedReader.readLine());
                String htmlColor = bufferedReader.readLine();
                boolean team = Boolean.parseBoolean(bufferedReader.readLine());
                boolean headTeam = Boolean.parseBoolean(bufferedReader.readLine());
                boolean adminTeam = Boolean.parseBoolean(bufferedReader.readLine());
                boolean vip = Boolean.parseBoolean(bufferedReader.readLine());
                accountRanks.put(title, new AccountRank(title, rgbColor, htmlColor, team, headTeam, adminTeam, vip));
            }

            accountHandler.setAccountRanks(accountRanks);

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