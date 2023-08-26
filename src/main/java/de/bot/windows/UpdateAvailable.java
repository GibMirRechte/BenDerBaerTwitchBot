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
        setPreferredSize(new Dimension(1025, 720));
        setLayout(null);

        JLabel jcomp2 = new JLabel(ImageIconHandler.imageType.UPDATE_NOTIFICATION.imageIcon);
        jcomp2.setVisible(true);
        add(jcomp2);

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
        jcomp2.setBounds(425, 177, 685, 365);

        JLabel sidebarBackground = new JLabel("");
        sidebarBackground.setBounds(0, 0, 255, 720);
        sidebarBackground.setOpaque(true);
        add(sidebarBackground);
        sidebarBackground.setBackground(new Color(0x113F67));

    }
}