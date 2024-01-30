package de.bot.windows.admindashboard.usermanagement;

import de.bot.handler.ImageIconHandler;
import de.bot.handler.UpdateHandler;
import de.bot.handler.WindowHandler;
import de.bot.utils.Announcement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdminDash_UserManagement extends JPanel {

    UpdateHandler updateHandler = UpdateHandler.getInstance();
    WindowHandler windowHandler = WindowHandler.getInstance();

    public AdminDash_UserManagement() {
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

        JLabel titleLabel = new JLabel("Benutzerverwaltung");
        titleLabel.setBounds(20, 10, 600, 36);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
        titleLabel.setVerticalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 36));
        add(titleLabel);

        JLabel createUserButton = new JLabel("<html><center><h1>➕</h1><br>Benutzer anlegen</center></html>");
        JLabel editUserButton = new JLabel("<html><center><h1>✎</h1><br>Benutzer bearbeiten</center></html>");
        JLabel registerOptionsButton = new JLabel("<html><center><h1>\uD83D\uDD27</h1><br>Registrierungs-Einstellungen</center></html>");
        JLabel createRankButton = new JLabel("<html><center><h1>➕</h1><br>Rang erstellen</center></html>");
        JLabel editRankButton = new JLabel("<html><center><h1>✎</h1><br>Rang bearbeiten</center></html>");
        JLabel backButton = new JLabel("<html><center><h1>\uD83D\uDEAA</h1><br>Zurück</center></html>");
        JLabel background = new JLabel();

        background.setBackground(new Color(0x1A1A1A));
        background.setVisible(true);
        background.setOpaque(true);
        background.setBorder(BorderFactory.createLineBorder(new Color(0x333333), 1));

        backButton.setBackground(new Color(0xD03F3F));
        backButton.setOpaque(true);
        backButton.setForeground(Color.WHITE);
        backButton.setHorizontalAlignment(SwingConstants.CENTER);
        backButton.setVerticalAlignment(SwingConstants.CENTER);
        backButton.setFont(new Font("Arial", Font.PLAIN, 15));

        createUserButton.setBackground(new Color(0x1E5E8B));
        createUserButton.setOpaque(true);
        createUserButton.setForeground(Color.WHITE);
        createUserButton.setHorizontalAlignment(SwingConstants.CENTER);
        createUserButton.setVerticalAlignment(SwingConstants.CENTER);
        createUserButton.setFont(new Font("Arial", Font.PLAIN, 15));

        editRankButton.setBackground(new Color(0x1E5E8B));
        editRankButton.setOpaque(true);
        editRankButton.setForeground(Color.WHITE);
        editRankButton.setHorizontalAlignment(SwingConstants.CENTER);
        editRankButton.setVerticalAlignment(SwingConstants.CENTER);
        editRankButton.setFont(new Font("Arial", Font.PLAIN, 15));

        createRankButton.setBackground(new Color(0x1E5E8B));
        createRankButton.setOpaque(true);
        createRankButton.setForeground(Color.WHITE);
        createRankButton.setHorizontalAlignment(SwingConstants.CENTER);
        createRankButton.setVerticalAlignment(SwingConstants.CENTER);
        createRankButton.setFont(new Font("Arial", Font.PLAIN, 15));

        editUserButton.setBackground(new Color(0x1E5E8B));
        editUserButton.setOpaque(true);
        editUserButton.setForeground(Color.WHITE);
        editUserButton.setHorizontalAlignment(SwingConstants.CENTER);
        editUserButton.setVerticalAlignment(SwingConstants.CENTER);
        editUserButton.setFont(new Font("Arial", Font.PLAIN, 15));

        registerOptionsButton.setBackground(new Color(0x1E5E8B));
        registerOptionsButton.setOpaque(true);
        registerOptionsButton.setForeground(Color.WHITE);
        registerOptionsButton.setHorizontalAlignment(SwingConstants.CENTER);
        registerOptionsButton.setVerticalAlignment(SwingConstants.CENTER);
        registerOptionsButton.setFont(new Font("Arial", Font.PLAIN, 15));

        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                backButton.setBackground(new Color(0xE04F4F));
                backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                backButton.setBackground(new Color(0xD03F3F));
                backButton.setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                windowHandler.openWindow(WindowHandler.WindowType.ADMINDASHBOARD);
            }
        });

        createRankButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                createRankButton.setBackground(new Color(0x236C9F));
                createRankButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                createRankButton.setBackground(new Color(0x1E5E8B));
                createRankButton.setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                windowHandler.openWindow(WindowHandler.WindowType.ADMINDASH_CREATERANK);
            }
        });

        editRankButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                editRankButton.setBackground(new Color(0x236C9F));
                editRankButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                editRankButton.setBackground(new Color(0x1E5E8B));
                editRankButton.setCursor(Cursor.getDefaultCursor());
            }
        });

        createUserButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                createUserButton.setBackground(new Color(0x236C9F));
                createUserButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                createUserButton.setBackground(new Color(0x1E5E8B));
                createUserButton.setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                windowHandler.openWindow(WindowHandler.WindowType.ADMINDASH_CREATEUSER);
            }
        });

        editUserButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                editUserButton.setBackground(new Color(0x236C9F));
                editUserButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                editUserButton.setBackground(new Color(0x1E5E8B));
                editUserButton.setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                windowHandler.openWindow(WindowHandler.WindowType.ADMINDASH_EDITUSER_SEARCH);
            }
        });

        registerOptionsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                registerOptionsButton.setBackground(new Color(0x236C9F));
                registerOptionsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                registerOptionsButton.setBackground(new Color(0x1E5E8B));
                registerOptionsButton.setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                windowHandler.openWindow(WindowHandler.WindowType.ADMINDASH_REGISTERSETTINGS);
            }
        });

        add(editUserButton);
        add(createUserButton);
        add(registerOptionsButton);
        add(editRankButton);
        add(backButton);
        add(createRankButton);

        add(background);

        createUserButton.setBounds(190, 200, 200, 200);
        editUserButton.setBounds(190, 420, 200, 200);
        createRankButton.setBounds(410, 200, 200, 200);
        editRankButton.setBounds(410, 420, 200, 200);
        registerOptionsButton.setBounds(630, 200, 200, 200);
        backButton.setBounds(630, 420, 200, 200);
        background.setBounds(170, 180, 680, 460);
    }

}
