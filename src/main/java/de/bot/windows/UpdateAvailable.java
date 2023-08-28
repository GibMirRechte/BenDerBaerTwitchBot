package de.bot.windows;

import de.bot.handler.AccountHandler;
import de.bot.handler.ImageIconHandler;
import de.bot.utils.Account;

import javax.swing.*;
import java.awt.*;

public class UpdateAvailable extends JPanel {

    AccountHandler accountHandler = AccountHandler.getInstance();

    public UpdateAvailable() {
        Account account = accountHandler.getAccount();
        setPreferredSize(new Dimension(1280, 720));
        setLayout(null);

        JLabel logo;

        if (account.getAccountType().equals(AccountHandler.AccountType.PREMIUM)) {
            logo = new JLabel(ImageIconHandler.imageType.LOGO_PREMIUM.imageIcon);
        } else if (account.getAccountType().equals(AccountHandler.AccountType.ADMIN) ||
                account.getAccountType().equals(AccountHandler.AccountType.STAFF)) {
            logo = new JLabel(ImageIconHandler.imageType.LOGO_STAFF.imageIcon);
        } else {
            logo = new JLabel(ImageIconHandler.imageType.LOGO_NORMAL.imageIcon);
        }

        add(logo);

        logo.setBounds(5, 10, 245, 180);

        JLabel sidebarBackground = new JLabel("");
        sidebarBackground.setBounds(0, 0, 255, 720);
        sidebarBackground.setOpaque(true);
        add(sidebarBackground);
        sidebarBackground.setBackground(new Color(0x113F67));

        JLabel message = new JLabel("<html><center><b>HINWEIS</b><br><br>Ein Update ist verfügbar!<br>Bitte starte das Programm neu.</center></html>");

        message.setBackground(new Color(0xFFD03F3F));
        message.setOpaque(true);
        message.setForeground(Color.WHITE);
        message.setHorizontalAlignment(SwingConstants.CENTER);
        message.setVerticalAlignment(SwingConstants.CENTER);
        message.setFont(new Font("Trebuchet MS", Font.PLAIN, 25));

        message.setBounds(438, 185, 660, 350);

        add(message);

    }
}