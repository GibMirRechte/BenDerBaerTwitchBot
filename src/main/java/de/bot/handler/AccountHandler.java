package de.bot.handler;

import de.bot.utils.*;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountHandler {

    private static Account account;
    static AccountHandler instance;
    private HashMap<String, AccountRank> accountRanks = new HashMap<>();

    public Image getBadge(AccountRank accountRank) {
        BufferedImage tempImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2dTemp = tempImage.createGraphics();
        g2dTemp.setFont(new Font("Arial", Font.BOLD, 14));
        FontMetrics fm = g2dTemp.getFontMetrics();
        int textWidth = fm.stringWidth(accountRank.getTitle());
        int textHeight = fm.getHeight();
        g2dTemp.dispose();

        int width = textWidth + 20;
        int height = 20;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        RoundRectangle2D roundedRectangle = new RoundRectangle2D.Double(0, 0, width, height, 0, 0);
        g2d.setColor(accountRank.getRgbColor());
        g2d.fill(roundedRectangle);

        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 14));

        int x = (width - textWidth) / 2;
        int y = (height - textHeight) / 2 + fm.getAscent();

        g2d.drawString(accountRank.getTitle(), x, y);
        g2d.dispose();

        return image;
    }


    public Account getAccount() {
        return account;
    }

    public void logout() {
        account = null;
    }

    public static AccountHandler getInstance() {
        if (instance == null)
            instance = new AccountHandler();
        return instance;
    }

    public void setAccountRanks(HashMap<String, AccountRank> list) {
        accountRanks.clear();
        for (String s : list.keySet()) {
            accountRanks.put(s, list.get(s));
        }
    }

    public enum ConfigType {
        LOGIN_SAVEDATA("login.savedata"),
        LOGIN_USERNAME("login.username"),
        LOGIN_PASSWORD("login.password");

        public final String index;

        ConfigType(String index) {
            this.index = index;
        }
    }

    private final String filePath = System.getenv("APPDATA") + "\\TwitchTool\\config.txt";
    ;
    private final Map<String, String> configMap = new HashMap<>();

    public void loadConfig() {
        if (!new File(filePath).exists()) createConfig();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] keyValue = line.split("=");
                if (keyValue.length == 2) {
                    String key = keyValue[0];
                    String value = keyValue[1];
                    configMap.put(key, value);
                }
            }
        } catch (IOException e) {
            createConfig();
        }
    }

    public ExternalUser getExternalUser(String username) {
        try {
            Socket socket = new Socket("45.93.249.139", 3459);
            OutputStream outputStream = socket.getOutputStream();
            PrintStream printStream = new PrintStream(outputStream);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            printStream.println("GET_USER");
            printStream.println("StaffDB");
            printStream.println(username);

            boolean validUser = Boolean.parseBoolean(bufferedReader.readLine());

            if (validUser) {
                String name = bufferedReader.readLine();
                String password = bufferedReader.readLine();
                String accessToken = bufferedReader.readLine();
                String refreshToken = bufferedReader.readLine();
                String channelID = bufferedReader.readLine();
                AccountRank accountRank = getRank(bufferedReader.readLine());

                int bans = Integer.parseInt(bufferedReader.readLine());
                java.util.List<BanData> banList = new ArrayList<>();

                for (int i = 0; i < bans; i++) {
                    String reason = bufferedReader.readLine();
                    String operator = bufferedReader.readLine();
                    String operatorColor = bufferedReader.readLine();
                    long bannedAt = Long.parseLong(bufferedReader.readLine());
                    long bannedUntil = Long.parseLong(bufferedReader.readLine());
                    boolean cancelled = Boolean.parseBoolean(bufferedReader.readLine());
                    String cancelledBy = bufferedReader.readLine();
                    String cancelledByColor = bufferedReader.readLine();
                    String cancelledReason = bufferedReader.readLine();
                    long cancelledAt = Long.parseLong(bufferedReader.readLine());

                    BanData banData = new BanData(reason, operator, operatorColor, bannedAt, bannedUntil, cancelled, cancelledBy, cancelledByColor, cancelledReason, cancelledAt);
                    banList.add(banData);
                }

                int warns = Integer.parseInt(bufferedReader.readLine());
                List<Warn> warnList = new ArrayList<>();
                for (int i = 0; i < warns; i++) {
                    String reason = bufferedReader.readLine();
                    String operator = bufferedReader.readLine();
                    String operatorColor = bufferedReader.readLine();
                    long timestamp = Long.parseLong(bufferedReader.readLine());
                    boolean cancelled = Boolean.parseBoolean(bufferedReader.readLine());
                    String cancelledBy = bufferedReader.readLine();
                    String cancelledByColor = bufferedReader.readLine();
                    String cancelledReason = bufferedReader.readLine();
                    long cancelledAt = Long.parseLong(bufferedReader.readLine());
                    Warn warn = new Warn(reason, operator, operatorColor, timestamp, cancelled, cancelledBy, cancelledByColor, cancelledReason, cancelledAt);
                    warnList.add(warn);
                }
                String modCommentCreator = bufferedReader.readLine();
                AccountRank creatorRank = getRank(bufferedReader.readLine());

                String modComment = bufferedReader.readLine();

                ExternalUser externalUser = new ExternalUser(name, password, accessToken, refreshToken, channelID, accountRank, modCommentCreator, creatorRank, modComment, banList, warnList);
                return externalUser;
            } else {
                throw new Exception("User " + username + " existiert nicht.");
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public AccountRank getRank(String name) {
        if (accountRanks.containsKey(name))
            return accountRanks.get(name);

        for (AccountRank accountRank : accountRanks.values()) {
            if (accountRank.isDefaultRank()) return accountRank;
        }

        return null;
    }

    public HashMap<String, AccountRank> getAccountRanks() {
        return accountRanks;
    }

    public String getData(ConfigType configType) {
        if (configMap.isEmpty()) loadConfig();
        return configMap.get(configType.index);
    }

    public void createConfig() {
        configMap.put("login.savedata", "false");
        configMap.put("login.username", "null");
        configMap.put("login.password", "null");
        saveConfig();
    }

    public void setConfig(ConfigType configType, String value) {
        configMap.put(configType.index, value);
        saveConfig();
    }

    private void saveConfig() {
        File file = new File(filePath);
        try {
            File directory = new File("C:\\Users\\User\\AppData\\Roaming\\TwitchTool");
            if (!directory.exists()) {
                directory.mkdirs();
            }

            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (Exception ignored) {
        }
        try (FileWriter fileWriter = new FileWriter(file)) {
            for (Map.Entry<String, String> entry : configMap.entrySet()) {
                fileWriter.write(entry.getKey() + "=" + entry.getValue() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void setAccount(Account acc) {
        account = acc;
    }

}
