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

public class Register extends JPanel {

    UpdateHandler updateHandler = UpdateHandler.getInstance();
    AccountHandler accountHandler = AccountHandler.getInstance();
    WindowHandler windowHandler = WindowHandler.getInstance();

    public Register() {
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
        JLabel registerButton = new JLabel("Registrieren");
        JPasswordField password = new JPasswordField();
        JTextField username = new JTextField();
        JTextField accessToken = new JTextField();
        JTextField refreshToken = new JTextField();
        JTextField inviteCode = new JTextField();
        JLabel elementBackground = new JLabel();

        Border emptyBorder = BorderFactory.createEmptyBorder(0, 20, 0, 0);
        password.setBorder(emptyBorder);
        username.setBorder(emptyBorder);
        accessToken.setBorder(emptyBorder);
        refreshToken.setBorder(emptyBorder);
        inviteCode.setBorder(emptyBorder);

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
        login.setBorder(emptyBorder);

        registerButton.setBackground(new Color(0x1E5E8B));
        registerButton.setOpaque(true);
        registerButton.setForeground(Color.WHITE);
        registerButton.setHorizontalAlignment(SwingConstants.CENTER);
        registerButton.setVerticalAlignment(SwingConstants.CENTER);
        registerButton.setFont(new Font("Arial", Font.PLAIN, 25));

        register.setForeground(Color.WHITE);
        register.setBackground(new Color(0x333333));
        register.setOpaque(true);
        register.setHorizontalAlignment(SwingConstants.LEFT);
        register.setVerticalAlignment(SwingConstants.CENTER);
        register.setFont(new Font("Arial", Font.PLAIN, 16));
        register.setBorder(emptyBorder);

        username.setBackground(new Color(0x262626));
        username.setForeground(Color.GRAY);
        username.setText("Benutzername");
        username.setFont(new Font("Arial", Font.PLAIN, 18));

        accessToken.setBackground(new Color(0x262626));
        accessToken.setForeground(Color.GRAY);
        accessToken.setText("AccessToken");
        accessToken.setFont(new Font("Arial", Font.PLAIN, 18));

        refreshToken.setBackground(new Color(0x262626));
        refreshToken.setForeground(Color.GRAY);
        refreshToken.setText("RefreshToken");
        refreshToken.setFont(new Font("Arial", Font.PLAIN, 18));

        inviteCode.setBackground(new Color(0x262626));
        inviteCode.setForeground(Color.GRAY);
        inviteCode.setText("InviteCode (optional)");
        inviteCode.setFont(new Font("Arial", Font.PLAIN, 18));

        ((AbstractDocument) inviteCode.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                int currentLength = fb.getDocument().getLength();
                int maxLength = 21;

                if (currentLength - length + text.length() <= maxLength) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });

        ((AbstractDocument) refreshToken.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                int currentLength = fb.getDocument().getLength();
                int maxLength = 150;

                if (currentLength - length + text.length() <= maxLength) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });

        ((AbstractDocument) accessToken.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                int currentLength = fb.getDocument().getLength();
                int maxLength = 150;

                if (currentLength - length + text.length() <= maxLength) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });

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

        accessToken.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (accessToken.getText().equals("AccessToken")) {
                    accessToken.setText("");
                    accessToken.setForeground(Color.WHITE);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (accessToken.getText().isEmpty()) {
                    accessToken.setText("AccessToken");
                    accessToken.setForeground(Color.GRAY);
                }
            }
        });

        refreshToken.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (refreshToken.getText().equals("RefreshToken")) {
                    refreshToken.setText("");
                    refreshToken.setForeground(Color.WHITE);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (refreshToken.getText().isEmpty()) {
                    refreshToken.setText("RefreshToken");
                    refreshToken.setForeground(Color.GRAY);
                }
            }
        });

        inviteCode.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (inviteCode.getText().equals("InviteCode (optional)")) {
                    inviteCode.setText("");
                    inviteCode.setForeground(Color.WHITE);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (inviteCode.getText().isEmpty()) {
                    inviteCode.setText("InviteCode (optional)");
                    inviteCode.setForeground(Color.GRAY);
                }
            }
        });

        registerButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                registerButton.setBackground(new Color(0x236C9F));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                registerButton.setCursor(Cursor.getDefaultCursor());
                registerButton.setBackground(new Color(0x1E5E8B));
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
                        loginFeedback.setVisible(false);
                        Socket socket = new Socket("45.93.249.139", 3459);
                        OutputStream outputStream = socket.getOutputStream();
                        PrintStream printStream = new PrintStream(outputStream);
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                        printStream.println("REGISTER");
                        printStream.println(name);
                        printStream.println(pw);
                        printStream.println(accessToken.getText());
                        printStream.println(refreshToken.getText());
                        if (inviteCode.getForeground().equals(Color.GRAY)) {
                            printStream.println("null");
                        } else {
                            printStream.println(inviteCode.getText());
                        }

                        String response = bufferedReader.readLine();

                        if (response.equalsIgnoreCase("REGISTER INACTIVE")) {
                            loginFeedback.setBackground(new Color(0x0D03F3F, true));
                            loginFeedback.setText("Die Registrier-Funktion ist zurzeit gesperrt.");
                            loginFeedback.setVisible(true);
                            return;
                        }

                        if (response.equalsIgnoreCase("INVALID INVITECODE")) {
                            loginFeedback.setBackground(new Color(0x0D03F3F, true));
                            loginFeedback.setText("Der InviteCode ist ungültig.");
                            loginFeedback.setVisible(true);
                            return;
                        }

                        if (response.equalsIgnoreCase("NAME ALREADY TAKEN")) {
                            loginFeedback.setBackground(new Color(0x0D03F3F, true));
                            loginFeedback.setText("Der Benutzername ist bereits vergeben.");
                            loginFeedback.setVisible(true);
                            return;
                        }

                        if (response.equalsIgnoreCase("CREATED")) {
                            String channelID = bufferedReader.readLine();
                            String accessToken = bufferedReader.readLine();
                            String refreshToken = bufferedReader.readLine();
                            AccountRank accountRank = accountHandler.getRank(bufferedReader.readLine());
                            int autoVIP_months = Integer.parseInt(bufferedReader.readLine());
                            int autoVIP_streams = Integer.parseInt(bufferedReader.readLine());
                            String autoShout_userList = bufferedReader.readLine();
                            String autoShout_shoutoutMessage = bufferedReader.readLine();
                            String automessages_messages = bufferedReader.readLine();
                            int automessages_delay = Integer.parseInt(bufferedReader.readLine());
                            CustomCommandSettings customCommandSettings = new CustomCommandSettings(bufferedReader.readLine());
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

                            Account acc = new Account(username.getText(), pw, channelID, accessToken, refreshToken, accountRank, new AutoVIPSettings(autoVIP_months, autoVIP_streams), new AutoShoutSettings(autoShout_userList, autoShout_shoutoutMessage), new AutoMessagesSettings(automessages_messages, automessages_delay), customCommandSettings, new BanData(bannedReason, bannedOperator, bannedOperatorColor, bannedAt, bannedUntil, banCancelled, cancelledBy, cancelledByColor, cancelledReason, cancelledAt));
                            accountHandler.setAccount(acc);
                            windowHandler.openWindow(WindowHandler.WindowType.DASHBOARD);
                        }
                    } catch (Exception ignored) {
                    }
                }
            }
        });

        password.setBackground(new Color(0x262626));
        password.setForeground(Color.GRAY);
        password.setText("Passwort");
        password.setEchoChar((char) 0);
        password.setFont(new Font("Arial", Font.PLAIN, 18));

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

        loginFeedback.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentHidden(ComponentEvent e) {
                elementBackground.setBounds(518, 200, 500, 447);
                registerButton.setBounds(798, 570, 200, 50);
            }

            @Override
            public void componentShown(ComponentEvent e) {
                elementBackground.setBounds(518, 200, 500, 520);
                registerButton.setBounds(798, 640, 200, 50);
            }
        });

        login.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                login.setBackground(new Color(0x333333));
                login.setOpaque(true);
                login.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                login.setBackground(null);
                login.setOpaque(false);
                login.setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                windowHandler.openWindow(WindowHandler.WindowType.LOGIN);
            }
        });

        register.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                register.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                register.setCursor(Cursor.getDefaultCursor());
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
        add(registerButton);
        add(login);
        add(password);
        add(username);
        add(loginFeedback);
        add(accessToken);
        add(refreshToken);
        add(inviteCode);

        add(elementBackground);

        sidebarHeader.setBounds(0, 0, 255, 75);
        elementBackground.setBounds(518, 200, 500, 450);
        announcement.setBounds(438, 75, 660, 40);
        loginFeedback.setBounds(538, 570, 460, 40);
        registerButton.setBounds(798, 570, 200, 50);
        register.setBounds(32, 136, 192, 35);
        login.setBounds(32, 100, 192, 35);
        username.setBounds(538, 220, 460, 50);
        password.setBounds(538, 290, 460, 50);
        accessToken.setBounds(538, 360, 460, 50);
        refreshToken.setBounds(538, 430, 460, 50);
        inviteCode.setBounds(538, 500, 460, 50);

        JLabel sidebarBackground = new JLabel("");
        sidebarBackground.setBounds(0, 0, 256, 816);
        sidebarBackground.setOpaque(true);
        add(sidebarBackground);
        sidebarBackground.setBackground(new Color(0x262626));
    }
}