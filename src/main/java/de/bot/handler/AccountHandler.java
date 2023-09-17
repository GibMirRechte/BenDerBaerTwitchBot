package de.bot.handler;

import de.bot.utils.Account;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class AccountHandler {

    private Account account;
    static AccountHandler instance;

    public enum AccountType {
        ADMIN(new Color(0xBE0000), "Admin"),
        STAFF(new Color(0xFE3F3F), "Team"),
        PREMIUM(new Color(0xEFC210), "Premium"),
        NORMAL(new Color(0x5D5D5D), "User");

        public final Color badgeColor;
        public final String badgeName;

        AccountType(Color badgeColor, String badgeName) {
            this.badgeColor = badgeColor;
            this.badgeName = badgeName;
        }
    }

    public Image getBadge(AccountType accountType) {
        BufferedImage tempImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2dTemp = tempImage.createGraphics();
        g2dTemp.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
        FontMetrics fm = g2dTemp.getFontMetrics();
        int textWidth = fm.stringWidth(accountType.badgeName);
        int textHeight = fm.getHeight();
        g2dTemp.dispose();

        int width = textWidth + 20;
        int height = 20;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        RoundRectangle2D roundedRectangle = new RoundRectangle2D.Double(0, 0, width, height, 20, 20);
        g2d.setColor(accountType.badgeColor);
        g2d.fill(roundedRectangle);

        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Trebuchet MS", Font.BOLD, 16));

        int x = (width - textWidth) / 2;
        int y = (height - textHeight) / 2 + fm.getAscent();

        g2d.drawString(accountType.badgeName, x, y);
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
