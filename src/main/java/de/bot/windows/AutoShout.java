package de.bot.windows;

import de.bot.handler.AccountHandler;
import de.bot.handler.ImageIconHandler;
import de.bot.handler.UpdateHandler;
import de.bot.handler.WindowHandler;
import de.bot.utils.Account;
import de.bot.utils.Announcement;

import javax.swing.*;
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

    public AutoShout() {
        Account account = accountHandler.getAccount();
        setPreferredSize(new Dimension(1025, 720));
        setLayout(null);

        Announcement an = updateHandler.getAnnouncement();
        JLabel announcement = new JLabel("<html><center>" + an.text + "</center></html>");
        JLabel saveButton = new JLabel("Speichern");
        JLabel saveFeedback = new JLabel("Die Einstellungen wurden erfolgreich gespeichert.");
        JLabel userlistTitle = new JLabel("Userliste");
        JLabel userlistUndertitle = new JLabel("Welche User sollen automatische Shoutouts erhalten? (Mit Komma trennen)");
        JTextArea userlistArea = new JTextArea("");

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
        announcement.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
        announcement.setBounds(183, 75, 660, 40);
        add(announcement);

        JLabel title = new JLabel("AutoShout Einstellungen");
        title.setBounds(20, 10, 600, 36);
        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(SwingConstants.LEFT);
        title.setVerticalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Trebuchet MS", Font.PLAIN, 36));

        userlistTitle.setForeground(Color.WHITE);
        userlistTitle.setHorizontalAlignment(SwingConstants.LEFT);
        userlistTitle.setVerticalAlignment(SwingConstants.CENTER);
        userlistTitle.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));

        userlistUndertitle.setForeground(Color.GRAY);
        userlistUndertitle.setHorizontalAlignment(SwingConstants.LEFT);
        userlistUndertitle.setVerticalAlignment(SwingConstants.CENTER);
        userlistUndertitle.setFont(new Font("Trebuchet MS", Font.ITALIC, 12));


        saveButton.setBackground(new Color(0x86BECC));
        saveButton.setOpaque(true);
        saveButton.setForeground(Color.WHITE);
        saveButton.setHorizontalAlignment(SwingConstants.CENTER);
        saveButton.setVerticalAlignment(SwingConstants.CENTER);
        saveButton.setFont(new Font("Trebuchet MS", Font.PLAIN, 28));

        saveFeedback.setBackground(new Color(0x05A489));
        saveFeedback.setForeground(Color.WHITE);
        saveFeedback.setOpaque(true);
        saveFeedback.setVisible(false);
        saveFeedback.setHorizontalAlignment(JLabel.CENTER);
        saveFeedback.setHorizontalTextPosition(JLabel.RIGHT);
        saveFeedback.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));

        saveButton.setBounds(20, 650, 985, 50);
        saveFeedback.setBounds(200, 120, 625, 40);

        userlistArea.setBackground(new Color(0x464646));
        userlistArea.setForeground(Color.WHITE);
        userlistArea.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));

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

        saveButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                saveButton.setText("<html><u>Speichern</u></html>");
                saveButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                saveButton.setText("Speichern");
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
        add(userlistArea);
        add(title);
        add(userlistTitle);
        add(userlistUndertitle);

        userlistTitle.setBounds(20, 175, 650, 20);
        userlistUndertitle.setBounds(20, 195, 650, 14);
        userlistArea.setBounds(20, 220, 400, 200);

    }

    public static void main(String[] args) {
        try {
            WindowHandler.getInstance().openWindow(WindowHandler.WindowType.LOGIN);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
