package de.bot.windows.admindashboard.rankmanagement;

import de.bot.handler.AccountHandler;
import de.bot.handler.ImageIconHandler;
import de.bot.handler.UpdateHandler;
import de.bot.handler.WindowHandler;
import de.bot.utils.Account;
import de.bot.utils.AccountRank;
import de.bot.utils.Announcement;

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
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

public class AdminDash_CreateRank extends JPanel {

    UpdateHandler updateHandler = UpdateHandler.getInstance();
    AccountHandler accountHandler = AccountHandler.getInstance();
    WindowHandler windowHandler = new WindowHandler();

    public AdminDash_CreateRank() {
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

        JLabel titleLabel = new JLabel("Rang erstellen");
        titleLabel.setBounds(20, 10, 600, 36);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
        titleLabel.setVerticalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 36));
        add(titleLabel);

        JTextField title = new JTextField();
        JLabel rankColor = new JLabel("Farbe auswählen");
        JCheckBox isVIP = new JCheckBox("Kann VIP Features nutzen");
        JCheckBox isTeam = new JCheckBox("Ist Teammitglied");
        JCheckBox isHeadTeam = new JCheckBox("Ist Head-Teammitglied");
        JCheckBox isAdmin = new JCheckBox("Ist Administrator");
        JLabel saveButton = new JLabel("Speichern");
        JLabel saveFeedback = new JLabel("Der Rang wurden erfolgreich erstellt.");
        JTextField background = new JTextField();

        Border emptyBorder = BorderFactory.createEmptyBorder(0, 10, 0, 10);

        saveFeedback.setBackground(new Color(0x05A489));
        saveFeedback.setBounds(200, 120, 625, 40);
        saveFeedback.setForeground(Color.WHITE);
        saveFeedback.setOpaque(true);
        saveFeedback.setVisible(false);
        saveFeedback.setHorizontalAlignment(JLabel.CENTER);
        saveFeedback.setHorizontalTextPosition(JLabel.RIGHT);
        saveFeedback.setFont(new Font("Arial", Font.PLAIN, 14));

        title.setBorder(null);
        title.setVisible(true);
        title.setBackground(new Color(0x262626));
        title.setForeground(Color.GRAY);
        title.setText("Rang-Titel");
        title.setBorder(emptyBorder);
        title.setFont(new Font("Arial", Font.PLAIN, 20));

        isVIP.setForeground(Color.WHITE);
        isVIP.setOpaque(false);
        isVIP.setVisible(true);
        isVIP.setHorizontalAlignment(JLabel.LEFT);
        isVIP.setHorizontalTextPosition(JLabel.RIGHT);
        isVIP.setFont(new Font("Arial", Font.PLAIN, 20));

        saveButton.setBackground(new Color(0x1E5E8B));
        saveButton.setOpaque(true);
        saveButton.setForeground(Color.WHITE);
        saveButton.setHorizontalAlignment(SwingConstants.CENTER);
        saveButton.setVerticalAlignment(SwingConstants.CENTER);
        saveButton.setFont(new Font("Arial", Font.PLAIN, 28));

        isTeam.setForeground(Color.WHITE);
        isTeam.setOpaque(false);
        isTeam.setVisible(true);
        isTeam.setHorizontalAlignment(JLabel.LEFT);
        isTeam.setHorizontalTextPosition(JLabel.RIGHT);
        isTeam.setFont(new Font("Arial", Font.PLAIN, 20));

        isHeadTeam.setForeground(Color.WHITE);
        isHeadTeam.setOpaque(false);
        isHeadTeam.setVisible(true);
        isHeadTeam.setHorizontalAlignment(JLabel.LEFT);
        isHeadTeam.setHorizontalTextPosition(JLabel.RIGHT);
        isHeadTeam.setFont(new Font("Arial", Font.PLAIN, 20));

        isAdmin.setForeground(Color.WHITE);
        isAdmin.setOpaque(false);
        isAdmin.setVisible(true);
        isAdmin.setHorizontalAlignment(JLabel.LEFT);
        isAdmin.setHorizontalTextPosition(JLabel.RIGHT);
        isAdmin.setFont(new Font("Arial", Font.PLAIN, 20));

        rankColor.setBackground(account.getAccountRank().getRgbColor());
        rankColor.setOpaque(true);
        rankColor.setForeground(Color.WHITE);
        rankColor.setHorizontalAlignment(SwingConstants.CENTER);
        rankColor.setVerticalAlignment(SwingConstants.CENTER);
        rankColor.setFont(new Font("Arial", Font.PLAIN, 16));

        background.setBackground(new Color(0x1A1A1A));
        background.setVisible(true);
        background.setOpaque(false);
        background.setBorder(BorderFactory.createLineBorder(new Color(0x333333), 1));

        ((AbstractDocument) title.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                int currentLength = fb.getDocument().getLength();
                int maxLength = 16;

                if (currentLength - length + text.length() <= maxLength) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });

        rankColor.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                rankColor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                rankColor.setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                Color selectedColor = JColorChooser.showDialog(null, "Farbe auswählen", Color.WHITE);
                if (selectedColor != null) {
                    rankColor.setBackground(selectedColor);
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
                if (title.getText().equalsIgnoreCase("Rang-Titel") || title.getText().isBlank()) {
                    saveFeedback.setBackground(new Color(0xFFD03F3F));
                    saveFeedback.setText("Bitte gebe einen gültigen Rang-Titel an.");
                    saveFeedback.setVisible(true);
                    return;
                }
                if (accountHandler.getRank(title.getText()) == null) {
                    accountHandler.getAccountRanks().put(title.getText(), new AccountRank(title.getText(), rankColor.getBackground(), String.valueOf(rankColor.getBackground()).replace("0x", ""), isTeam.isSelected(), isHeadTeam.isSelected(), isAdmin.isSelected(), isVIP.isSelected()));
                    saveFeedback.setText("Der Rang wurden erfolgreich erstellt.");
                    saveFeedback.setBackground(new Color(0x05A489));
                    saveFeedback.setVisible(true);
                } else {
                    saveFeedback.setBackground(new Color(0xFFD03F3F));
                    saveFeedback.setText("Der Rang-Titel ist bereits vergeben.");
                    saveFeedback.setVisible(true);
                    return;
                }

                try {
                    Socket socket = new Socket("45.93.249.139", 3459);
                    OutputStream outputStream = socket.getOutputStream();
                    PrintStream printStream = new PrintStream(outputStream);

                    printStream.println("SAVE");
                    printStream.println("Rank");
                    printStream.println(title.getText());
                    printStream.println(rankColor.getBackground().getRGB());
                    printStream.println(isVIP.isSelected());
                    printStream.println(isTeam.isSelected());
                    printStream.println(isHeadTeam.isSelected());
                    printStream.println(isAdmin.isSelected());
                } catch (Exception ignored) {
                }
            }
        });


        title.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (title.getText().equals("Rang-Titel")) {
                    title.setText("");
                    title.setForeground(Color.WHITE);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (title.getText().isEmpty()) {
                    title.setText("Rang-Titel");
                    title.setForeground(Color.GRAY);
                }
            }
        });

        rankColor.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (rankColor.getText().equals("rankColor")) {
                    rankColor.setText("");
                    rankColor.setForeground(Color.WHITE);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (rankColor.getText().isEmpty()) {
                    rankColor.setText("rankColor");
                    rankColor.setForeground(Color.GRAY);
                }
            }
        });

        add(title);
        add(rankColor);
        add(isVIP);
        add(isTeam);
        add(isHeadTeam);
        add(isAdmin);
        add(saveButton);
        add(saveFeedback);
        add(background);

        title.setBounds(40, 170, 400, 35);
        rankColor.setBounds(40, 215, 400, 35);
        isVIP.setBounds(40, 260, 400, 35);
        isTeam.setBounds(40, 305, 400, 35);
        isHeadTeam.setBounds(40, 350, 400, 35);
        saveFeedback.setBounds(200, 120, 625, 40);
        isAdmin.setBounds(40, 395, 400, 35);
        saveButton.setBounds(20, 750, 1000, 50);

        background.setBounds(20, 150, 440, 300);
    }
}
