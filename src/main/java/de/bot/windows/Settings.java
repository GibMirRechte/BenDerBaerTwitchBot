package de.bot.windows;

import de.bot.handler.AccountHandler;
import de.bot.handler.ImageIconHandler;
import de.bot.handler.UpdateHandler;
import de.bot.handler.WindowHandler;
import de.bot.utils.Account;
import de.bot.utils.Announcement;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

public class Settings extends JPanel {

    UpdateHandler updateHandler = UpdateHandler.getInstance();
    AccountHandler accountHandler = AccountHandler.getInstance();

    public Settings() {
        Account account = accountHandler.getAccount();
        Announcement an = updateHandler.getAnnouncement();

        setPreferredSize(new Dimension(1040, 816));
        setLayout(null);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel announcement = new JLabel("<html><center>" + an.text + "</center></html>");
        JLabel changePasswordTitle = new JLabel("Passwort ändern");
        JLabel saveFeedback = new JLabel("Die Einstellungen wurden erfolgreich gespeichert.");

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

        JLabel title = new JLabel("Einstellungen");
        JLabel saveButton = new JLabel("Speichern");
        JCheckBox showPasswords = new JCheckBox("Passwörter anzeigen");
        Border border = BorderFactory.createEmptyBorder(0, 10, 0, 0);

        JPasswordField resetPasswortCurrent = new JPasswordField();
        JPasswordField resetPasswortNewOne = new JPasswordField();
        JPasswordField resetPasswortNewTwo = new JPasswordField();
        JLabel passwortBackground = new JLabel();

        passwortBackground.setBackground(new Color(0x1A1A1A));
        passwortBackground.setVisible(true);
        passwortBackground.setOpaque(true);
        passwortBackground.setBorder(BorderFactory.createLineBorder(new Color(0x333333), 1));

        resetPasswortCurrent.setBorder(border);
        resetPasswortNewOne.setBorder(border);
        resetPasswortNewTwo.setBorder(border);

        add(title);
        add(announcement);
        add(resetPasswortCurrent);
        add(resetPasswortNewOne);
        add(resetPasswortNewTwo);
        add(changePasswordTitle);

        showPasswords.setForeground(Color.WHITE);
        showPasswords.setOpaque(false);
        showPasswords.setVisible(true);
        showPasswords.setHorizontalAlignment(JLabel.CENTER);
        showPasswords.setHorizontalTextPosition(JLabel.RIGHT);
        showPasswords.setFont(new Font("Arial", Font.PLAIN, 18));

        showPasswords.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                resetPasswortNewOne.setEchoChar((char) 0);
                resetPasswortNewTwo.setEchoChar((char) 0);
            } else {
                if (!resetPasswortNewOne.getText().equalsIgnoreCase("Neues Passwort")) {
                    resetPasswortNewOne.setEchoChar((char) 0x2022);
                }
                if (!resetPasswortNewTwo.getText().equalsIgnoreCase("Passwort wiederholen")) {
                    resetPasswortNewTwo.setEchoChar((char) 0x2022);
                }
            }
        });

        resetPasswortCurrent.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (resetPasswortCurrent.getText().equals("Aktuelles Passwort")) {
                    resetPasswortCurrent.setText("");
                    resetPasswortCurrent.setForeground(Color.WHITE);
                    resetPasswortCurrent.setEchoChar((char) 0x2022);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (resetPasswortCurrent.getText().isEmpty()) {
                    resetPasswortCurrent.setText("Aktuelles Passwort");
                    resetPasswortCurrent.setForeground(Color.GRAY);
                    resetPasswortCurrent.setEchoChar((char) 0);
                }
            }
        });
        resetPasswortCurrent.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (resetPasswortCurrent.getText().equals("Aktuelles Passwort")) {
                    resetPasswortCurrent.setText("");
                    resetPasswortCurrent.setForeground(Color.WHITE);
                    resetPasswortCurrent.setEchoChar((char) 0x2022);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (resetPasswortCurrent.getText().isEmpty()) {
                    resetPasswortCurrent.setText("Aktuelles Passwort");
                    resetPasswortCurrent.setForeground(Color.GRAY);
                    resetPasswortCurrent.setEchoChar((char) 0);
                }
            }
        });
        resetPasswortNewOne.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (resetPasswortNewOne.getText().equals("Neues Passwort")) {
                    resetPasswortNewOne.setText("");
                    resetPasswortNewOne.setForeground(Color.WHITE);
                    if (!showPasswords.isSelected()) resetPasswortNewOne.setEchoChar((char) 0x2022);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (resetPasswortNewOne.getText().isEmpty()) {
                    resetPasswortNewOne.setText("Neues Passwort");
                    resetPasswortNewOne.setForeground(Color.GRAY);
                    resetPasswortNewOne.setEchoChar((char) 0);
                }
            }
        });
        resetPasswortNewTwo.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (resetPasswortNewTwo.getText().equals("Passwort wiederholen")) {
                    resetPasswortNewTwo.setText("");
                    resetPasswortNewTwo.setForeground(Color.WHITE);
                    if (!showPasswords.isSelected()) resetPasswortNewTwo.setEchoChar((char) 0x2022);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (resetPasswortNewTwo.getText().isEmpty()) {
                    resetPasswortNewTwo.setText("Passwort wiederholen");
                    resetPasswortNewTwo.setForeground(Color.GRAY);
                    resetPasswortNewTwo.setEchoChar((char) 0);
                }
            }
        });

        changePasswordTitle.setForeground(Color.WHITE);
        changePasswordTitle.setHorizontalAlignment(SwingConstants.LEFT);
        changePasswordTitle.setVerticalAlignment(SwingConstants.CENTER);
        changePasswordTitle.setFont(new Font("Arial", Font.PLAIN, 18));

        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(SwingConstants.LEFT);
        title.setVerticalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.PLAIN, 36));

        resetPasswortCurrent.setVisible(true);
        resetPasswortCurrent.setBackground(new Color(0x262626));
        resetPasswortCurrent.setForeground(Color.GRAY);
        resetPasswortCurrent.setText("Aktuelles Passwort");
        resetPasswortCurrent.setFont(new Font("Arial", Font.PLAIN, 18));
        resetPasswortCurrent.setEchoChar((char) 0);

        saveFeedback.setBackground(new Color(0x05A489));
        saveFeedback.setForeground(Color.WHITE);
        saveFeedback.setOpaque(true);
        saveFeedback.setVisible(false);
        saveFeedback.setHorizontalAlignment(JLabel.CENTER);
        saveFeedback.setHorizontalTextPosition(JLabel.RIGHT);
        saveFeedback.setFont(new Font("Arial", Font.PLAIN, 14));

        resetPasswortNewOne.setVisible(true);
        resetPasswortNewOne.setBackground(new Color(0x262626));
        resetPasswortNewOne.setForeground(Color.GRAY);
        resetPasswortNewOne.setText("Neues Passwort");
        resetPasswortNewOne.setFont(new Font("Arial", Font.PLAIN, 18));
        resetPasswortNewOne.setEchoChar((char) 0);

        resetPasswortNewTwo.setVisible(true);
        resetPasswortNewTwo.setBackground(new Color(0x262626));
        resetPasswortNewTwo.setForeground(Color.GRAY);
        resetPasswortNewTwo.setText("Passwort wiederholen");
        resetPasswortNewTwo.setFont(new Font("Arial", Font.PLAIN, 18));
        resetPasswortNewTwo.setEchoChar((char) 0);

        ((AbstractDocument) resetPasswortCurrent.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                int currentLength = fb.getDocument().getLength();
                int maxLength = 32;

                if (currentLength - length + text.length() <= maxLength) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });

        ((AbstractDocument) resetPasswortNewOne.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                int currentLength = fb.getDocument().getLength();
                int maxLength = 32;

                if (currentLength - length + text.length() <= maxLength) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });

        ((AbstractDocument) resetPasswortNewTwo.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                int currentLength = fb.getDocument().getLength();
                int maxLength = 32;

                if (currentLength - length + text.length() <= maxLength) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });

        saveButton.setBackground(new Color(0x1E5E8B));
        saveButton.setOpaque(true);
        saveButton.setForeground(Color.WHITE);
        saveButton.setHorizontalAlignment(SwingConstants.CENTER);
        saveButton.setVerticalAlignment(SwingConstants.CENTER);
        saveButton.setFont(new Font("Arial", Font.PLAIN, 28));

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
            public void mouseReleased(MouseEvent event) {
                if (!resetPasswortCurrent.getText().equals(account.getPassword())) {
                    saveFeedback.setBackground(new Color(0xFFD03F3F));
                    saveFeedback.setText("Das aktuelle Passwort stimmt nicht überein!");
                    saveFeedback.setVisible(true);
                    saveFeedback.setOpaque(true);
                } else {
                    String newPasswort = resetPasswortNewOne.getText().replace("Neues Passwort", "");
                    String newPasswortTwo = resetPasswortNewTwo.getText().replace("Passwort wiederholen", "");

                    if (newPasswort.replace(" ", "").equals("") || newPasswortTwo.replace(" ", "").equals("")) {
                        saveFeedback.setBackground(new Color(0xFFD03F3F));
                        saveFeedback.setText("Bitte gebe ein gültiges neues Passwort an!");
                        saveFeedback.setVisible(true);
                        return;
                    }

                    if (!newPasswort.equals(newPasswortTwo)) {
                        saveFeedback.setBackground(new Color(0xFFD03F3F));
                        saveFeedback.setText("Die Passwörter stimmen nicht überein!");
                        saveFeedback.setVisible(true);
                        return;
                    }

                    if (newPasswort.equals(account.getPassword())) {
                        saveFeedback.setBackground(new Color(0xFFD03F3F));
                        saveFeedback.setText("Dieses Passwort ist bereits eingestellt!");
                        saveFeedback.setVisible(true);
                        return;
                    }

                    try {
                        Socket socket = new Socket("45.93.249.139", 3459);
                        OutputStream outputStream = socket.getOutputStream();
                        PrintStream printStream = new PrintStream(outputStream);
                        printStream.println("CHANGE_PW");
                        printStream.println(account.getName());
                        printStream.println(newPasswort);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    saveFeedback.setBackground(new Color(0x05A489));
                    saveFeedback.setText("Das Passwort wurde gespeichert. Du wirst abgemeldet.");
                    saveFeedback.setVisible(true);
                    accountHandler.logout();
                    WindowHandler.getInstance().openWindow(WindowHandler.WindowType.LOGIN);
                }
            }
        });

        title.setBounds(20, 10, 600, 36);

        changePasswordTitle.setBounds(40, 185, 650, 20);
        resetPasswortCurrent.setBounds(40, 210, 280, 35);
        resetPasswortNewOne.setBounds(40, 255, 280, 35);
        resetPasswortNewTwo.setBounds(40, 300, 280, 35);
        saveButton.setBounds(20, 750, 1000, 50);
        saveFeedback.setBounds(200, 120, 625, 40);
        showPasswords.setBounds(40, 335, 200, 40);
        passwortBackground.setBounds(20, 165, 320, 220);

        add(saveButton);
        add(saveFeedback);
        add(showPasswords);
        add(passwortBackground);

    }
}
