package de.bot.windows.admindashboard.usermanagement;

import de.bot.handler.AccountHandler;
import de.bot.handler.ImageIconHandler;
import de.bot.handler.UpdateHandler;
import de.bot.handler.WindowHandler;
import de.bot.utils.Account;
import de.bot.utils.AccountRank;
import de.bot.utils.Announcement;
import de.bot.windows.modules.ComboBoxRenderer;
import de.bot.windows.modules.CustomComboBox;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

public class AdminDash_CreateUser extends JPanel {

    UpdateHandler updateHandler = UpdateHandler.getInstance();
    AccountHandler accountHandler = AccountHandler.getInstance();
    WindowHandler windowHandler = new WindowHandler();

    public AdminDash_CreateUser() {
        Account account = accountHandler.getAccount();
        setPreferredSize(new Dimension(1040, 816));
        setLayout(null);

        Announcement an = updateHandler.getAnnouncement();
        JLabel announcement = new JLabel("<html><center>" + an.text + "</center></html>");

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
        announcement.setBounds(183, 75, 660, 40);
        add(announcement);

        JLabel titleLabel = new JLabel("Benutzer erstellen");
        titleLabel.setBounds(20, 10, 600, 36);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
        titleLabel.setVerticalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 36));
        add(titleLabel);

        JTextField username = new JTextField();
        JTextField channelID = new JTextField();
        JTextField accessToken = new JTextField();
        JTextField refreshToken = new JTextField();
        JPasswordField password = new JPasswordField();
        JLabel saveButton = new JLabel("Speichern");
        JLabel saveFeedback = new JLabel("Der Benutzer wurden erfolgreich erstellt.");
        JCheckBox hasToChangePw = new JCheckBox("Muss Passwort bei Erstanmeldung ändern");
        JTextField background = new JTextField();

        Border emptyBorder = BorderFactory.createEmptyBorder(0, 10, 0, 10);

        username.setBorder(null);
        username.setVisible(true);
        username.setBackground(new Color(0x262626));
        username.setForeground(Color.GRAY);
        username.setText("Benutzername");
        username.setBorder(emptyBorder);
        username.setFont(new Font("Arial", Font.PLAIN, 20));

        channelID.setBorder(null);
        channelID.setVisible(true);
        channelID.setBackground(new Color(0x262626));
        channelID.setForeground(Color.GRAY);
        channelID.setText("ChannelID");
        channelID.setBorder(emptyBorder);
        channelID.setFont(new Font("Arial", Font.PLAIN, 20));

        saveFeedback.setBackground(new Color(0x05A489));
        saveFeedback.setBounds(200, 120, 625, 40);
        saveFeedback.setForeground(Color.WHITE);
        saveFeedback.setOpaque(true);
        saveFeedback.setVisible(false);
        saveFeedback.setHorizontalAlignment(JLabel.CENTER);
        saveFeedback.setHorizontalTextPosition(JLabel.RIGHT);
        saveFeedback.setFont(new Font("Arial", Font.PLAIN, 14));

        saveButton.setBackground(new Color(0x1E5E8B));
        saveButton.setOpaque(true);
        saveButton.setForeground(Color.WHITE);
        saveButton.setHorizontalAlignment(SwingConstants.CENTER);
        saveButton.setVerticalAlignment(SwingConstants.CENTER);
        saveButton.setFont(new Font("Arial", Font.PLAIN, 28));

        password.setBackground(new Color(0x262626));
        password.setForeground(Color.GRAY);
        password.setBorder(emptyBorder);
        password.setText("Passwort");
        password.setEchoChar((char) 0);
        password.setFont(new Font("Arial", Font.PLAIN, 20));

        hasToChangePw.setForeground(Color.WHITE);
        hasToChangePw.setOpaque(false);
        hasToChangePw.setVisible(true);
        hasToChangePw.setHorizontalAlignment(JLabel.CENTER);
        hasToChangePw.setHorizontalTextPosition(JLabel.RIGHT);
        hasToChangePw.setSelected(true);
        hasToChangePw.setFont(new Font("Arial", Font.PLAIN, 20));

        accessToken.setBorder(null);
        accessToken.setVisible(true);
        accessToken.setBackground(new Color(0x262626));
        accessToken.setForeground(Color.GRAY);
        accessToken.setText("AccessToken");
        accessToken.setBorder(emptyBorder);
        accessToken.setFont(new Font("Arial", Font.PLAIN, 20));

        refreshToken.setBorder(null);
        refreshToken.setVisible(true);
        refreshToken.setBackground(new Color(0x262626));
        refreshToken.setForeground(Color.GRAY);
        refreshToken.setText("RefreshToken");
        refreshToken.setBorder(emptyBorder);
        refreshToken.setFont(new Font("Arial", Font.PLAIN, 20));

        String[] ranks = new String[accountHandler.getAccountRanks().size()];

        int i = 0;

        for (AccountRank accountRank : accountHandler.getAccountRanks().values()) {
            ranks[i] = accountRank.getTitle();
            i++;
        }

        JComboBox selectRank = new JComboBox(ranks);
        selectRank.setBackground(new Color(0x262626));
        selectRank.setFont(new Font("Arial", Font.PLAIN, 20));
        selectRank.setBounds(106, 450, 400, 35);
        selectRank.setUI(new CustomComboBox());
        selectRank.setRenderer(new ComboBoxRenderer());
        selectRank.setFocusable(false);
        selectRank.setOpaque(false);
        selectRank.setSelectedIndex(ranks.length - 1);
        selectRank.setForeground(Color.WHITE);

        background.setBackground(new Color(0x1A1A1A));
        background.setVisible(true);
        background.setOpaque(false);
        background.setBorder(BorderFactory.createLineBorder(new Color(0x333333), 1));

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

        ((AbstractDocument) channelID.getDocument()).setDocumentFilter(new DocumentFilter() {
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

        channelID.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (channelID.getText().equals("ChannelID")) {
                    channelID.setText("");
                    channelID.setForeground(Color.WHITE);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (channelID.getText().isEmpty()) {
                    channelID.setText("ChannelID");
                    channelID.setForeground(Color.GRAY);
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

        saveButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                saveButton.setBackground(new Color(0x236C9F));
                saveButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                saveButton.setBackground(new Color(0x1E5E8B));
                saveButton.setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                saveFeedback.setVisible(false);
                if (username.getText().equalsIgnoreCase("Benutzername") || username.getText().isBlank() ||
                        password.getText().equalsIgnoreCase("Passwort") || password.getText().isBlank()) {
                    saveFeedback.setBackground(new Color(0xFFD03F3F));
                    saveFeedback.setText("Bitte gebe ein gültigen Benutzername und Passwort an.");
                    saveFeedback.setVisible(true);
                    return;
                }

                try {
                    Socket socket = new Socket("45.93.249.139", 3459);
                    OutputStream outputStream = socket.getOutputStream();
                    PrintStream printStream = new PrintStream(outputStream);
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    printStream.println("CREATE_USER");
                    printStream.println(username.getText());
                    printStream.println(password.getText());
                    printStream.println(channelID.getText());
                    printStream.println(selectRank.getSelectedItem().toString());
                    printStream.println(accessToken.getText());
                    printStream.println(refreshToken.getText());

                    String respone = bufferedReader.readLine();

                    if (respone.equalsIgnoreCase("CREATED")) {
                        saveFeedback.setText("Der Benutzer wurden erfolgreich erstellt.");
                        saveFeedback.setBackground(new Color(0x05A489));
                        saveFeedback.setVisible(true);
                    } else if (respone.equalsIgnoreCase("NAME NOT AVAILABLE")) {
                        saveFeedback.setBackground(new Color(0xFFD03F3F));
                        saveFeedback.setText("Der Benutzername ist bereits vergeben.");
                        saveFeedback.setVisible(true);
                    }
                } catch (Exception ignored) {
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

        add(username);
        add(password);
        add(selectRank);
        add(hasToChangePw);
        add(accessToken);
        add(refreshToken);
        add(saveFeedback);
        add(saveButton);
        add(channelID);
        add(background);

        username.setBounds(40, 170, 400, 35);
        password.setBounds(40, 215, 400, 35);
        selectRank.setBounds(40, 260, 400, 35);
        channelID.setBounds(40, 305, 400, 35);
        accessToken.setBounds(40, 350, 400, 35);
        refreshToken.setBounds(40, 395, 400, 35);
        hasToChangePw.setBounds(40, 440, 400, 35);
        saveButton.setBounds(20, 750, 1000, 50);
        saveFeedback.setBounds(200, 120, 625, 40);
        background.setBounds(20, 150, 440, 335);
    }
}
