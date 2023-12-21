package de.bot.windows;

import de.bot.handler.AccountHandler;
import de.bot.handler.ImageIconHandler;
import de.bot.handler.UpdateHandler;
import de.bot.handler.WindowHandler;
import de.bot.utils.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

public class Login extends JPanel {

    UpdateHandler updateHandler = UpdateHandler.getInstance();
    AccountHandler accountHandler = AccountHandler.getInstance();
    WindowHandler windowHandler = WindowHandler.getInstance();
    long lastLoginAttempt = System.currentTimeMillis();

    public Login() {
        setPreferredSize(new Dimension(1296, 816));

        setLayout(null);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Announcement an = updateHandler.getAnnouncement();

        JLabel register = new JLabel("Registrieren");
        JLabel announcement = new JLabel("<html><center>" + an.text + "</center></html>");
        JLabel loginFeedback = new JLabel("loginFeedback");
        JLabel login = new JLabel("Login");
        JLabel loginButton = new JLabel("Anmelden");
        JLabel messageField = new JLabel("<html><center>GESPERRT!<br><br>Dieser Account ist zurzeit gesperrt!<br>Grund: Nicht angegeben<br>Dauer: Dauerhaft</center></html>");
        JCheckBox saveData = new JCheckBox("<html>Logindaten<br>speichern</html>");
        JPasswordField password = new JPasswordField();
        JTextField username = new JTextField();
        JLabel elementBackground = new JLabel();

        Border emptyBorder = BorderFactory.createEmptyBorder(0, 20, 0, 0);
        password.setBorder(emptyBorder);
        username.setBorder(emptyBorder);

        elementBackground.setBackground(new Color(0x1A1A1A));
        elementBackground.setVisible(true);
        elementBackground.setOpaque(true);
        elementBackground.setBorder(BorderFactory.createLineBorder(new Color(0x333333), 1));

        announcement.setBackground(Color.decode(an.color));
        announcement.setForeground(Color.WHITE);
        announcement.setOpaque(true);
        announcement.setVisible(an.isActive);
        announcement.setHorizontalAlignment(JLabel.CENTER);
        if (an.typeID == 1) {
            announcement.setIcon(ImageIconHandler.imageType.WARN_ICON.imageIcon);
        } else {
            announcement.setIcon(ImageIconHandler.imageType.INFO_ICON.imageIcon);
        }
        announcement.setHorizontalTextPosition(JLabel.RIGHT);
        announcement.setFont(new Font("Arial", Font.PLAIN, 14));

        loginFeedback.setBackground(new Color(0x0D03F3F, true));
        loginFeedback.setBorder(BorderFactory.createLineBorder(new Color(0xFFD03F3F), 2));
        loginFeedback.setForeground(Color.WHITE);
        loginFeedback.setOpaque(false);
        loginFeedback.setVisible(false);
        loginFeedback.setHorizontalAlignment(JLabel.CENTER);
        loginFeedback.setHorizontalTextPosition(JLabel.RIGHT);
        loginFeedback.setFont(new Font("Arial", Font.PLAIN, 14));

        login.setForeground(Color.WHITE);
        login.setHorizontalAlignment(SwingConstants.LEFT);
        login.setVerticalAlignment(SwingConstants.CENTER);
        login.setFont(new Font("Arial", Font.PLAIN, 16));
        login.setBackground(new Color(0x333333));
        login.setOpaque(true);
        login.setBorder(emptyBorder);

        loginButton.setBackground(new Color(0x1E5E8B));
        loginButton.setOpaque(true);
        loginButton.setForeground(Color.WHITE);
        loginButton.setHorizontalAlignment(SwingConstants.CENTER);
        loginButton.setVerticalAlignment(SwingConstants.CENTER);
        loginButton.setFont(new Font("Arial", Font.PLAIN, 28));

        register.setForeground(Color.WHITE);
        register.setHorizontalAlignment(SwingConstants.LEFT);
        register.setVerticalAlignment(SwingConstants.CENTER);
        register.setFont(new Font("Arial", Font.PLAIN, 16));
        register.setBorder(emptyBorder);

        username.setBackground(new Color(0x262626));
        username.setForeground(Color.GRAY);
        username.setText("Benutzername");
        username.setFont(new Font("Arial", Font.PLAIN, 20));


        ((AbstractDocument) username.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                int currentLength = fb.getDocument().getLength();
                int maxLength = 16;

                if (currentLength - length + text.length() <= maxLength) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });

        ((AbstractDocument) password.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                int currentLength = fb.getDocument().getLength();
                int maxLength = 32;

                if (currentLength - length + text.length() <= maxLength) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });
        username.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (username.getText().equals("Benutzername")) {
                    username.setText("");
                    username.setForeground(Color.WHITE);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (username.getText().isEmpty()) {
                    username.setText("Benutzername");
                    username.setForeground(Color.GRAY);
                }
            }
        });

        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                loginButton.setBackground(new Color(0x236C9F));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                loginButton.setCursor(Cursor.getDefaultCursor());
                loginButton.setBackground(new Color(0x1E5E8B));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (!an.canUseTool) {
                    loginFeedback.setText("Zurzeit ist eine Anmeldung nicht möglich. Bitte versuche es später erneut.");
                    loginFeedback.setVisible(true);
                    return;
                }

                if (!username.getText().equalsIgnoreCase("Benutzername") && !password.getText().equalsIgnoreCase("Passwort")) {
                    String name = username.getText();
                    String pw = password.getText();
                    try {
                        messageField.setVisible(false);
                        loginFeedback.setVisible(false);
                        Socket socket = new Socket("45.93.249.139", 3459);
                        OutputStream outputStream = socket.getOutputStream();
                        PrintStream printStream = new PrintStream(outputStream);
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                        printStream.println("LOGIN");
                        printStream.println(name);
                        printStream.println(pw);

                        boolean canLogin = Boolean.parseBoolean(bufferedReader.readLine());

                        if (canLogin) {
                            loginFeedback.setBorder(BorderFactory.createLineBorder(new Color(0x05A489), 2));
                            loginFeedback.setText("Session wird erstellt...");
                            loginFeedback.setVisible(true);

                            String channelID = bufferedReader.readLine();
                            String accessToken = bufferedReader.readLine();
                            String refreshToken = bufferedReader.readLine();
                            AccountRank accountRank = accountHandler.getRank(bufferedReader.readLine());
                            int autoVIP_months = Integer.parseInt(bufferedReader.readLine());
                            int autoVIP_streams = Integer.parseInt(bufferedReader.readLine());
                            String autoVIP_whitelist = bufferedReader.readLine();
                            String autoVIP_blacklist = bufferedReader.readLine();
                            String autoShout_userList = bufferedReader.readLine();
                            String automessages_messages = bufferedReader.readLine();
                            int automessages_delay = Integer.parseInt(bufferedReader.readLine());
                            boolean isBanned = Boolean.parseBoolean(bufferedReader.readLine());
                            long bannedUntil = 0;
                            String bannedReason = "";
                            String bannedOperator = "";
                            String bannedOperatorColor = "";
                            long bannedAt = 0;
                            boolean banCancelled = false;
                            String cancelledReason = "";
                            String cancelledBy = "";
                            String cancelledByColor = "";
                            long cancelledAt = 0;
                            if (isBanned) {
                                bannedUntil = Long.parseLong(bufferedReader.readLine());
                                bannedReason = bufferedReader.readLine();
                            }

                            Account acc = new Account(username.getText(), pw, channelID, accessToken, refreshToken, accountRank, new AutoVIPSettings(autoVIP_months, autoVIP_streams, autoVIP_whitelist, autoVIP_blacklist), new AutoShoutSettings(autoShout_userList), new AutoMessagesSettings(automessages_messages, automessages_delay), new BanData(bannedReason, bannedOperator, bannedOperatorColor, bannedAt, bannedUntil, banCancelled, cancelledBy, cancelledByColor, cancelledReason, cancelledAt));
                            new Thread(() -> {
                                if (!acc.getBanData().isActive()) {
                                    accountHandler.setAccount(acc);
                                    accountHandler.setConfig(AccountHandler.ConfigType.LOGIN_SAVEDATA, saveData.isSelected() + "");
                                    if (saveData.isSelected()) {
                                        accountHandler.setConfig(AccountHandler.ConfigType.LOGIN_USERNAME, username.getText());
                                        accountHandler.setConfig(AccountHandler.ConfigType.LOGIN_PASSWORD, password.getText());
                                    }
                                    windowHandler.openWindow(WindowHandler.WindowType.DASHBOARD);
                                } else {
                                    String remainingTime = "";
                                    if (acc.getBanData().getBannedUntil() == -1) {
                                        remainingTime = "Dauerhaft";
                                    } else {
                                        long remainingMillis = (acc.getBanData().getBannedUntil() - System.currentTimeMillis());
                                        long days = remainingMillis / (24 * 60 * 60 * 1000);
                                        remainingMillis %= (24 * 60 * 60 * 1000);
                                        long hours = remainingMillis / (60 * 60 * 1000);
                                        remainingMillis %= (60 * 60 * 1000);
                                        long minutes = remainingMillis / (60 * 1000);
                                        remainingMillis %= (60 * 1000);
                                        long seconds = remainingMillis / 1000;

                                        remainingTime = String.format("%d Tage, %dh %dmin %dsek", days, hours, minutes, seconds);
                                    }
                                    messageField.setText("<html><center>Dieser Account ist zurzeit gesperrt!<br>Grund: " + acc.getBanData().getReason() + "<br>Verbleibende Zeit: " + remainingTime + "</center></html>");
                                    messageField.setVisible(true);
                                }
                            }).start();
                        } else {
                            String reason = bufferedReader.readLine();
                            if (reason.equalsIgnoreCase("WRONG_DATA")) {
                                loginFeedback.setText("Ungültiger Username oder Passwort.");
                                loginFeedback.setVisible(true);
                                if (password.isFocusOwner()) {
                                    password.setText("");
                                } else {
                                    password.setForeground(Color.GRAY);
                                    password.setText("Passwort");
                                    password.setEchoChar((char) 0);
                                }
                            } else if (reason.equalsIgnoreCase("ALREADY_ONLINE")) {
                                loginFeedback.setText("Dieser Account ist bereits an einem anderen Standort angemeldet.");
                            }
                        }

                    } catch (Exception ignored) {
                    }
                }
            }
        });

        loginFeedback.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentHidden(ComponentEvent e) {
                if (!messageField.isVisible()) elementBackground.setBounds(500, 200, 536, 340);
            }

            @Override
            public void componentShown(ComponentEvent e) {
                if (!messageField.isVisible()) elementBackground.setBounds(500, 200, 536, 390);
            }
        });

        messageField.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentHidden(ComponentEvent e) {
                loginFeedback.setVisible(false);
                elementBackground.setBounds(500, 200, 536, 340);
            }

            @Override
            public void componentShown(ComponentEvent e) {
                loginFeedback.setVisible(false);
                elementBackground.setBounds(500, 200, 536, 490);
            }
        });

        password.setBackground(new Color(0x262626));
        password.setForeground(Color.GRAY);
        password.setText("Passwort");
        password.setEchoChar((char) 0);
        password.setFont(new Font("Arial", Font.PLAIN, 20));

        saveData.setForeground(Color.WHITE);
        saveData.setOpaque(false);
        saveData.setVisible(true);
        saveData.setHorizontalAlignment(JLabel.CENTER);
        saveData.setHorizontalTextPosition(JLabel.RIGHT);
        saveData.setFont(new Font("Arial", Font.PLAIN, 18));

        if (Boolean.parseBoolean(accountHandler.getData(AccountHandler.ConfigType.LOGIN_SAVEDATA))) {
            saveData.setSelected(true);
            username.setText(accountHandler.getData(AccountHandler.ConfigType.LOGIN_USERNAME));
            username.setForeground(Color.WHITE);
            password.setText(accountHandler.getData(AccountHandler.ConfigType.LOGIN_PASSWORD));
            password.setEchoChar((char) 0x2022);
            password.setForeground(Color.WHITE);
        }

        messageField.setVisible(false);
        messageField.setForeground(Color.WHITE);
        messageField.setBorder(BorderFactory.createLineBorder(new Color(0xD03F3F), 2));
        messageField.setOpaque(false);
        messageField.setHorizontalAlignment(SwingConstants.CENTER);
        messageField.setVerticalAlignment(SwingConstants.CENTER);
        messageField.setFont(new Font("Arial", Font.PLAIN, 18));

        password.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (password.getText().equals("Passwort")) {
                    password.setText("");
                    password.setForeground(Color.WHITE);
                    password.setEchoChar((char) 0x2022);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (password.getPassword().length == 0) {
                    password.setText("Passwort");
                    password.setForeground(Color.GRAY);
                    password.setEchoChar((char) 0);
                }
            }
        });

        register.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                register.setOpaque(true);
                register.setBackground(new Color(0x333333));
                register.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                register.setBackground(null);
                register.setOpaque(false);
                register.setCursor(Cursor.getDefaultCursor());
            }
        });

        login.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                login.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                login.setCursor(Cursor.getDefaultCursor());
            }
        });

        JLabel sidebarHeader = new JLabel("<html><center>TwitchBot</center></html>");
        sidebarHeader.setForeground(Color.WHITE);
        sidebarHeader.setHorizontalAlignment(SwingConstants.CENTER);
        sidebarHeader.setVerticalAlignment(SwingConstants.CENTER);
        sidebarHeader.setFont(new Font("Arial", Font.BOLD, 20));
        sidebarHeader.setBackground(new Color(0x1A1A1A));
        sidebarHeader.setOpaque(true);

        add(sidebarHeader);
        add(announcement);
        add(register);
        add(loginButton);
        add(login);
        add(password);
        add(username);
        add(loginFeedback);
        add(messageField);
        add(saveData);
        add(elementBackground);

        sidebarHeader.setBounds(0, 0, 255, 75);
        elementBackground.setBounds(500, 200, 536, 340);
        announcement.setBounds(438, 75, 660, 40);
        loginFeedback.setBounds(538, 522, 460, 40);
        loginButton.setBounds(780, 440, 200, 50);
        register.setBounds(32, 136, 192, 35);
        login.setBounds(32, 100, 192, 35);
        username.setBounds(538, 227, 460, 75);
        messageField.setBounds(538, 522, 460, 150);
        password.setBounds(538, 327, 460, 75);
        saveData.setBounds(550, 427, 150, 75);

        JLabel sidebarBackground = new JLabel("");
        sidebarBackground.setBounds(0, 0, 256, 816);
        sidebarBackground.setOpaque(true);
        add(sidebarBackground);
        sidebarBackground.setBackground(new Color(0x262626));
    }
}