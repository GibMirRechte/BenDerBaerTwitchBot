package de.bot.windows;

import de.bot.handler.AccountHandler;

import javax.swing.*;
import java.awt.*;

public class UpdateAvailable extends JPanel {

    AccountHandler accountHandler = AccountHandler.getInstance();

    public UpdateAvailable() {
        setPreferredSize(new Dimension(1295, 816));
        setLayout(null);

        JLabel sidebarHeader = new JLabel("<html><center>TwitchBot</center></html>");

        sidebarHeader.setForeground(Color.WHITE);
        sidebarHeader.setHorizontalAlignment(SwingConstants.CENTER);
        sidebarHeader.setVerticalAlignment(SwingConstants.CENTER);
        sidebarHeader.setFont(new Font("Arial", Font.BOLD, 20));
        sidebarHeader.setBackground(new Color(0x1A1A1A));
        sidebarHeader.setOpaque(true);

        JLabel sidebarBackground = new JLabel("");
        sidebarBackground.setBounds(0, 0, 255, 816);
        sidebarBackground.setOpaque(true);
        sidebarBackground.setBackground(new Color(0x262626));

        JLabel message = new JLabel("<html><center><b>HINWEIS</b><br><br>Ein Update ist verfügbar!<br>Bitte starte das Programm neu.</center></html>");

        message.setBackground(new Color(0xFFD03F3F));
        message.setOpaque(true);
        message.setForeground(Color.WHITE);
        message.setHorizontalAlignment(SwingConstants.CENTER);
        message.setVerticalAlignment(SwingConstants.CENTER);
        message.setFont(new Font("Arial", Font.PLAIN, 25));

        sidebarHeader.setBounds(0, 0, 255, 75);
        message.setBounds(445, 233, 660, 350);

        add(sidebarHeader);
        add(sidebarBackground);
        add(message);

    }
}