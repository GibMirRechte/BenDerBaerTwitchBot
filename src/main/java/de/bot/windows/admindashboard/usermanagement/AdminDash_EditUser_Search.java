package de.bot.windows.admindashboard.usermanagement;

import de.bot.handler.AccountHandler;
import de.bot.handler.ImageIconHandler;
import de.bot.handler.UpdateHandler;
import de.bot.handler.WindowHandler;
import de.bot.utils.Account;
import de.bot.utils.Announcement;
import de.bot.utils.ExternalUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdminDash_EditUser_Search extends JPanel {

    AccountHandler accountHandler = AccountHandler.getInstance();
    UpdateHandler updateHandler = UpdateHandler.getInstance();
    WindowHandler windowHandler = WindowHandler.getInstance();

    public AdminDash_EditUser_Search() {
        Account account = accountHandler.getAccount();
        setPreferredSize(new Dimension(1040, 816));
        setLayout(null);

        Announcement an = updateHandler.getAnnouncement();
        JLabel announcement = new JLabel("<html><center>" + an.text + "</center></html>");
        JLabel searchButton = new JLabel("Suchen");
        JLabel searchFeedback = new JLabel("Dieser User wurde nicht gefunden.");

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

        JLabel background = new JLabel();
        background.setBackground(new Color(0x1A1A1A));
        background.setVisible(true);
        background.setOpaque(true);
        background.setBorder(BorderFactory.createLineBorder(new Color(0x333333), 1));

        JTextField username = new JTextField();
        username.setBorder(null);
        username.setVisible(true);
        username.setBackground(new Color(0x262626));
        username.setForeground(Color.WHITE);
        username.setHorizontalAlignment(JLabel.CENTER);
        username.setFont(new Font("Arial", Font.PLAIN, 20));

        searchButton.setBackground(new Color(0x1E5E8B));
        searchButton.setOpaque(true);
        searchButton.setForeground(Color.WHITE);
        searchButton.setHorizontalAlignment(SwingConstants.CENTER);
        searchButton.setVerticalAlignment(SwingConstants.CENTER);
        searchButton.setFont(new Font("Arial", Font.PLAIN, 28));

        searchFeedback.setBackground(new Color(0xFFD03F3F));
        searchFeedback.setForeground(Color.WHITE);
        searchFeedback.setOpaque(true);
        searchFeedback.setVisible(false);
        searchFeedback.setHorizontalAlignment(JLabel.CENTER);
        searchFeedback.setHorizontalTextPosition(JLabel.RIGHT);
        searchFeedback.setFont(new Font("Arial", Font.PLAIN, 14));

        username.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    for (MouseListener mouseListener : searchButton.getMouseListeners()) {
                        MouseEvent simulateClick = new MouseEvent(
                                searchButton, MouseEvent.MOUSE_RELEASED, System.currentTimeMillis(), 0, 0, 0, 1, false);
                        mouseListener.mouseReleased(simulateClick);
                    }
                }
            }
        });

        searchButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                searchButton.setBackground(new Color(0x236C9F));
                searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                searchButton.setCursor(Cursor.getDefaultCursor());
                searchButton.setBackground(new Color(0x1E5E8B));
                searchButton.setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mouseReleased(MouseEvent event) {
                searchFeedback.setVisible(false);
                if (username.getText().replace(" ", "").equalsIgnoreCase("")) {
                    searchFeedback.setText("Bitte gebe einen gültigen Namen an.");
                    searchFeedback.setVisible(true);
                } else {
                    searchFeedback.setVisible(false);
                    ExternalUser externalUser = accountHandler.getExternalUser(username.getText());

                    if (externalUser == null) {
                        searchFeedback.setText("Dieser User wurde nicht gefunden.");
                        searchFeedback.setVisible(true);
                    } else {
                        if (externalUser.getAccountRank().isAdmin_team()) {
                            if (!account.getAccountRank().isAdmin_team()) {
                                searchFeedback.setText("Du hast keine Rechte, um diesen Nutzer einzusehen.");
                                searchFeedback.setVisible(true);
                                return;
                            }
                        } else if (externalUser.getAccountRank().isTeam()) {
                            if (!account.getAccountRank().isHead_team()) {
                                searchFeedback.setText("Du hast keine Rechte, um diesen Nutzer einzusehen.");
                                searchFeedback.setVisible(true);
                                return;
                            }
                        }
                        windowHandler.openEditUser(externalUser);
                    }

                }
            }
        });

        JLabel title = new JLabel("Benutzer suchen");
        title.setBounds(20, 10, 600, 36);
        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(SwingConstants.LEFT);
        title.setVerticalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.PLAIN, 36));
        add(title);

        JLabel usersearchTitle = new JLabel("User suchen");
        usersearchTitle.setForeground(Color.WHITE);
        usersearchTitle.setHorizontalAlignment(SwingConstants.LEFT);
        usersearchTitle.setFont(new Font("Arial", Font.PLAIN, 25));
        usersearchTitle.setBounds(20, 190, 250, 35);

        username.setBounds(363, 355, 300, 50);
        usersearchTitle.setBounds(363, 321, 250, 35);
        searchButton.setBounds(415, 415, 200, 50);
        searchFeedback.setBounds(200, 120, 625, 40);
        background.setBounds(343, 306, 340, 174);

        add(username);
        add(searchButton);
        add(searchFeedback);
        add(usersearchTitle);
        add(background);
    }
}
