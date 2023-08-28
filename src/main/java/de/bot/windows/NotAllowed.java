package de.bot.windows;

import de.bot.handler.AccountHandler;
import de.bot.handler.ImageIconHandler;
import de.bot.handler.UpdateHandler;
import de.bot.utils.Account;
import de.bot.utils.Announcement;

import javax.swing.*;
import java.awt.*;

public class NotAllowed extends JPanel {

    UpdateHandler updateHandler = UpdateHandler.getInstance();
    AccountHandler accountHandler = AccountHandler.getInstance();

    public NotAllowed() {
        Account account = accountHandler.getAccount();
        Announcement an = updateHandler.getAnnouncement();

        setPreferredSize(new Dimension(1025, 720));
        setLayout(null);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

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

        announcement.setBounds(183, 112, 660, 40);

        add(announcement);

        JLabel message = new JLabel("<html><center><b>HINWEIS</b><br><br>Für diese Funktion erfüllt dein Account nicht die benötigten Voraussetzungen.</center></html>");

        message.setBackground(new Color(0xFFD03F3F));
        message.setOpaque(true);
        message.setForeground(Color.WHITE);
        message.setHorizontalAlignment(SwingConstants.CENTER);
        message.setVerticalAlignment(SwingConstants.CENTER);
        message.setFont(new Font("Trebuchet MS", Font.PLAIN, 25));

        message.setBounds(183, 185, 660, 350);

        add(message);

    }
}
