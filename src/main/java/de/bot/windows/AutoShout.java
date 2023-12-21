package de.bot.windows;

import de.bot.handler.AccountHandler;
import de.bot.handler.ImageIconHandler;
import de.bot.handler.UpdateHandler;
import de.bot.handler.WindowHandler;
import de.bot.utils.Account;
import de.bot.utils.Announcement;
import de.bot.windows.modules.CustomScrollBar;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

public class AutoShout extends JPanel {

    AccountHandler accountHandler = AccountHandler.getInstance();
    UpdateHandler updateHandler = UpdateHandler.getInstance();
    WindowHandler windowHandler = WindowHandler.getInstance();

    public AutoShout() {
        Account account = accountHandler.getAccount();
        setPreferredSize(new Dimension(1040, 816));
        setLayout(null);

        Announcement an = updateHandler.getAnnouncement();
        JLabel announcement = new JLabel("<html><center>" + an.text + "</center></html>");
        JLabel saveButton = new JLabel("Speichern");
        JLabel saveFeedback = new JLabel("Die Einstellungen wurden erfolgreich gespeichert.");
        JLabel userlistTitle = new JLabel("Userliste");
        JLabel userlistUndertitle = new JLabel("Welche User sollen automatische Shoutouts erhalten? (Mit Komma trennen)");
        JLabel messageTitle = new JLabel("Shoutout-Nachricht");
        JLabel messageUndertitle = new JLabel("Welche Nachricht als Shoutout gesendet wird. ({@user} ist Platzhalter für den Nutzernamen)");
        JTextArea messageArea = new JTextArea("");
        JTextArea userlistArea = new JTextArea("");
        JLabel background = new JLabel();
        JLabel notice = new JLabel("<html>Ein wichtiger Hinweis für AutoShout ist verfügbar.<br>Klicke zum ansehen</html>");

        background.setBackground(new Color(0x1A1A1A));
        background.setVisible(true);
        background.setOpaque(true);
        background.setBorder(BorderFactory.createLineBorder(new Color(0x333333), 1));

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

        Border emptyBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);

        JLabel title = new JLabel("AutoShout Einstellungen");
        title.setBounds(20, 10, 600, 36);
        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(SwingConstants.LEFT);
        title.setVerticalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.PLAIN, 36));

        messageTitle.setForeground(Color.WHITE);
        messageTitle.setHorizontalAlignment(SwingConstants.LEFT);
        messageTitle.setVerticalAlignment(SwingConstants.CENTER);
        messageTitle.setFont(new Font("Arial", Font.PLAIN, 18));

        userlistTitle.setForeground(Color.WHITE);
        userlistTitle.setHorizontalAlignment(SwingConstants.LEFT);
        userlistTitle.setVerticalAlignment(SwingConstants.CENTER);
        userlistTitle.setFont(new Font("Arial", Font.PLAIN, 18));

        userlistUndertitle.setForeground(Color.GRAY);
        userlistUndertitle.setHorizontalAlignment(SwingConstants.LEFT);
        userlistUndertitle.setVerticalAlignment(SwingConstants.CENTER);
        userlistUndertitle.setFont(new Font("Arial", Font.ITALIC, 12));

        messageUndertitle.setForeground(Color.GRAY);
        messageUndertitle.setHorizontalAlignment(SwingConstants.LEFT);
        messageUndertitle.setVerticalAlignment(SwingConstants.CENTER);
        messageUndertitle.setFont(new Font("Arial", Font.ITALIC, 12));

        saveButton.setBackground(new Color(0x1E5E8B));
        saveButton.setOpaque(true);
        saveButton.setForeground(Color.WHITE);
        saveButton.setHorizontalAlignment(SwingConstants.CENTER);
        saveButton.setVerticalAlignment(SwingConstants.CENTER);
        saveButton.setFont(new Font("Arial", Font.PLAIN, 28));

        saveFeedback.setBackground(new Color(0x05A489));
        saveFeedback.setForeground(Color.WHITE);
        saveFeedback.setOpaque(true);
        saveFeedback.setVisible(false);
        saveFeedback.setHorizontalAlignment(JLabel.CENTER);
        saveFeedback.setHorizontalTextPosition(JLabel.RIGHT);
        saveFeedback.setFont(new Font("Arial", Font.PLAIN, 14));

        notice.setBackground(new Color(0xAF3333));
        notice.setBorder(BorderFactory.createLineBorder(new Color(0xFFD03F3F), 2));
        notice.setForeground(Color.WHITE);
        notice.setOpaque(true);
        notice.setHorizontalAlignment(JLabel.CENTER);
        notice.setIcon(ImageIconHandler.imageType.WARN_ICON.imageIcon);
        notice.setVerticalAlignment(JLabel.CENTER);
        notice.setHorizontalTextPosition(JLabel.TRAILING);
        notice.setFont(new Font("Arial", Font.PLAIN, 14));

        userlistArea.setBackground(new Color(0x262626));
        userlistArea.setForeground(Color.WHITE);
        userlistArea.setBorder(emptyBorder);
        userlistArea.setFont(new Font("Arial", Font.PLAIN, 18));

        String defaultMessage = "Schaut doch mal bei {@user} vorbei! https://twitch.tv/{@user}";

        messageArea.setBackground(new Color(0x262626));
        messageArea.setBorder(emptyBorder);
        messageArea.setForeground(Color.GRAY);
        messageArea.setFont(new Font("Arial", Font.PLAIN, 18));
        messageArea.setText(defaultMessage);

        JScrollPane userlistScrollArea = new JScrollPane(userlistArea);
        userlistScrollArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        userlistScrollArea.setBorder(null);
        userlistScrollArea.getVerticalScrollBar().setUI(new CustomScrollBar());
        userlistScrollArea.getHorizontalScrollBar().setUI(new CustomScrollBar());
        userlistScrollArea.getViewport().setBackground(Color.BLUE);
        userlistScrollArea.getViewport().setOpaque(true);

        JScrollPane messageScrollArea = new JScrollPane(messageArea);
        messageScrollArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        messageScrollArea.getVerticalScrollBar().setUI(new CustomScrollBar());
        messageScrollArea.getHorizontalScrollBar().setUI(new CustomScrollBar());
        messageScrollArea.setBorder(null);
        messageScrollArea.getViewport().setBackground(Color.BLUE);
        messageScrollArea.getViewport().setOpaque(true);


        if (account.getAutoShoutSettings().getUserList().isEmpty()) {
            userlistArea.setText("ExampleUser1,ExampleUser2");
            userlistArea.setForeground(Color.GRAY);
        } else {
            userlistArea.setText(account.getAutoShoutSettings().getUserListString());
        }

        userlistArea.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (userlistArea.getText().equals("ExampleUser1,ExampleUser2")) {
                    userlistArea.setText("");
                    userlistArea.setForeground(Color.WHITE);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (userlistArea.getText().isEmpty()) {
                    userlistArea.setText("ExampleUser1,ExampleUser2");
                    userlistArea.setForeground(Color.GRAY);
                }
            }
        });

        messageArea.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (messageArea.getText().equals(defaultMessage)) {
                    messageArea.setText("");
                    messageArea.setForeground(Color.WHITE);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (messageArea.getText().isEmpty()) {
                    messageArea.setText(defaultMessage);
                    messageArea.setForeground(Color.GRAY);
                }
            }
        });

        notice.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                notice.setBackground(new Color(0xC93B3B));
                notice.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                notice.setBackground(new Color(0xAF3333));
                notice.setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                windowHandler.openNewsPage("AS92034");
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
                saveFeedback.setVisible(true);
                String userList = userlistArea.getText().replace(" ", "").replace("ExampleUser1,ExampleUser2", "");
                try {
                    Socket socket = new Socket("45.93.249.139", 3459);
                    OutputStream outputStream = socket.getOutputStream();
                    PrintStream printStream = new PrintStream(outputStream);
                    printStream.println("SAVE");
                    printStream.println("AutoShout");
                    printStream.println(account.getName());
                    printStream.println(userList);

                    saveFeedback.setVisible(true);
                    account.getAutoShoutSettings().updateUserList(userList);
                } catch (Exception ignored) {
                }
            }
        });

        add(saveButton);
        add(saveFeedback);
        add(notice);
        add(userlistScrollArea);
        add(title);
        add(userlistTitle);
        add(userlistUndertitle);
        add(messageTitle);
        add(messageUndertitle);
        add(messageScrollArea);
        add(background);

        userlistTitle.setBounds(40, 185, 620, 20);
        userlistUndertitle.setBounds(40, 205, 620, 14);
        userlistScrollArea.setBounds(40, 230, 620, 100);
        messageTitle.setBounds(40, 350, 620, 20);
        messageUndertitle.setBounds(40, 370, 620, 14);
        messageScrollArea.setBounds(40, 395, 620, 150);
        notice.setBounds(270, 690, 500, 50);
        background.setBounds(20, 165, 660, 399);
        saveButton.setBounds(20, 750, 1000, 50);
        saveFeedback.setBounds(200, 120, 625, 40);


    }

}
