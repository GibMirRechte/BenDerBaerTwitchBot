package de.bot.windows.admindashboard;

import de.bot.handler.ImageIconHandler;
import de.bot.handler.UpdateHandler;
import de.bot.handler.WindowHandler;
import de.bot.utils.Announcement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdminDash_NewsManagement extends JPanel {

    UpdateHandler updateHandler = UpdateHandler.getInstance();
    WindowHandler windowHandler = WindowHandler.getInstance();

    public AdminDash_NewsManagement() {
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

        JLabel createNewsButton = new JLabel("<html><center><h1>➕</h1><br>News erstellen</center></html>");
        JLabel searchNewsButton = new JLabel("<html><center><h1>\uD83D\uDD0E</h1><br>News suchen</center></html>");
        JLabel background = new JLabel();
        JLabel backButton = new JLabel("<html><center><h1>\uD83D\uDEAA</h1><br>Zurück</center></html>");

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

        createNewsButton.setBackground(new Color(0x1E5E8B));
        createNewsButton.setOpaque(true);
        createNewsButton.setForeground(Color.WHITE);
        createNewsButton.setHorizontalAlignment(SwingConstants.CENTER);
        createNewsButton.setVerticalAlignment(SwingConstants.CENTER);
        createNewsButton.setFont(new Font("Arial", Font.PLAIN, 15));

        searchNewsButton.setBackground(new Color(0x1E5E8B));
        searchNewsButton.setOpaque(true);
        searchNewsButton.setForeground(Color.WHITE);
        searchNewsButton.setHorizontalAlignment(SwingConstants.CENTER);
        searchNewsButton.setVerticalAlignment(SwingConstants.CENTER);
        searchNewsButton.setFont(new Font("Arial", Font.PLAIN, 15));

        createNewsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                createNewsButton.setBackground(new Color(0x236C9F));
                createNewsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                createNewsButton.setBackground(new Color(0x1E5E8B));
                createNewsButton.setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                windowHandler.openWindow(WindowHandler.WindowType.ADMINDASH_CREATENEWS);
            }
        });

        searchNewsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                searchNewsButton.setBackground(new Color(0x236C9F));
                searchNewsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                searchNewsButton.setBackground(new Color(0x1E5E8B));
                searchNewsButton.setCursor(Cursor.getDefaultCursor());
            }
        });

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

        add(searchNewsButton);
        add(createNewsButton);
        add(backButton);

        add(background);

        createNewsButton.setBounds(190, 200, 200, 200);
        searchNewsButton.setBounds(410, 200, 200, 200);
        backButton.setBounds(630, 200, 200, 200);
        background.setBounds(170, 180, 680, 240);
    }

}
