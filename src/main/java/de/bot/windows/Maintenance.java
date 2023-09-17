package de.bot.windows;

import de.bot.handler.ImageIconHandler;
import de.bot.handler.UpdateHandler;
import de.bot.utils.Announcement;

import javax.swing.*;
import java.awt.*;

public class Maintenance extends JPanel {

    UpdateHandler updateHandler = UpdateHandler.getInstance();

    public Maintenance(String title) {
        setPreferredSize(new Dimension(1025, 720));
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
        announcement.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
        announcement.setBounds(183, 75, 660, 40);
        add(announcement);

        JLabel titleLabel = new JLabel(title + " Einstellungen");
        titleLabel.setBounds(20, 10, 600, 36);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
        titleLabel.setVerticalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 36));
        add(titleLabel);

        JLabel message = new JLabel("<html><center><b>HINWEIS</b><br><br>" + title + " ist zurzeit im Wartungsmodus.<br>Bitte versuche es später erneut.</center></html>");

        message.setBackground(new Color(0xFFD03F3F));
        message.setOpaque(true);
        message.setForeground(Color.WHITE);
        message.setHorizontalAlignment(SwingConstants.CENTER);
        message.setVerticalAlignment(SwingConstants.CENTER);
        message.setFont(new Font("Trebuchet MS", Font.PLAIN, 25));

        message.setBounds(182, 185, 660, 350);

        add(message);


    }

}