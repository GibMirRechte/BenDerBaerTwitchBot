package de.bot.windows.modules;

import de.bot.handler.AccountHandler;
import de.bot.handler.ImageIconHandler;
import de.bot.handler.WindowHandler;
import de.bot.utils.Account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Sidebar extends JPanel {

    AccountHandler accountHandler = AccountHandler.getInstance();
    WindowHandler windowHandler = WindowHandler.getInstance();

    public Sidebar() {
        Account account = accountHandler.getAccount();

        ImageIcon vipLabel = new ImageIcon(ImageIconHandler.imageType.VIP_ICON.imageIcon.getImage().getScaledInstance(37, 17, Image.SCALE_SMOOTH));
        ImageIcon soonLabel = new ImageIcon(ImageIconHandler.imageType.SOON_ICON.imageIcon.getImage().getScaledInstance(50, 17, Image.SCALE_SMOOTH));
        setPreferredSize(new Dimension(255, 720));
        setLayout(null);

        setVisible(true);
        setOpaque(true);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel soon3 = new JLabel("Coming Soon");
        JLabel autoVip = new JLabel("AutoVIP");
        JLabel autoShout = new JLabel("AutoShout");
        JLabel logo;

        if (account.getAccountType().equals(AccountHandler.AccountType.PREMIUM)) {
            logo = new JLabel(ImageIconHandler.imageType.LOGO_PREMIUM.imageIcon);
        } else if (account.getAccountType().equals(AccountHandler.AccountType.ADMIN) ||
                account.getAccountType().equals(AccountHandler.AccountType.STAFF)) {
            logo = new JLabel(ImageIconHandler.imageType.LOGO_STAFF.imageIcon);
        } else {
            logo = new JLabel(ImageIconHandler.imageType.LOGO_NORMAL.imageIcon);
        }

        JLabel dashboard = new JLabel("Dashboard");
        JLabel soon1 = new JLabel("Coming Soon");
        JLabel support = new JLabel("Support");
        JLabel copyright = new JLabel("Changes");
        JLabel discord = new JLabel("Discord");
        JLabel streams = new JLabel("Streams");
        JLabel logout = new JLabel("Logout");

        copyright.setBackground(new Color(0x113F67));
        copyright.setForeground(Color.WHITE);
        copyright.setHorizontalAlignment(SwingConstants.CENTER);
        copyright.setVerticalAlignment(SwingConstants.CENTER);
        copyright.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));

        streams.setBackground(new Color(0x113F67));
        streams.setForeground(Color.WHITE);
        streams.setHorizontalAlignment(SwingConstants.CENTER);
        streams.setVerticalAlignment(SwingConstants.CENTER);
        streams.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));

        logout.setBackground(new Color(0x113F67));
        logout.setForeground(Color.WHITE);
        logout.setHorizontalAlignment(SwingConstants.CENTER);
        logout.setVerticalAlignment(SwingConstants.CENTER);
        logout.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));

        discord.setBackground(new Color(0x113F67));
        discord.setForeground(Color.WHITE);
        discord.setHorizontalAlignment(SwingConstants.CENTER);
        discord.setVerticalAlignment(SwingConstants.CENTER);
        discord.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));

        soon1.setBackground(new Color(0x113F67));
        soon1.setForeground(Color.WHITE);
        soon1.setHorizontalAlignment(SwingConstants.CENTER);
        soon1.setVerticalAlignment(SwingConstants.CENTER);
        soon1.setFont(new Font("Trebuchet MS", Font.PLAIN, 25));

        support.setBackground(new Color(0x113F67));
        support.setForeground(Color.WHITE);
        support.setHorizontalAlignment(SwingConstants.CENTER);
        support.setVerticalAlignment(SwingConstants.CENTER);
        support.setFont(new Font("Trebuchet MS", Font.PLAIN, 25));

        soon3.setBackground(new Color(0x113F67));
        soon3.setForeground(Color.WHITE);
        soon3.setHorizontalAlignment(SwingConstants.CENTER);
        soon3.setVerticalAlignment(SwingConstants.CENTER);
        soon3.setFont(new Font("Trebuchet MS", Font.PLAIN, 25));

        dashboard.setBackground(new Color(0x113F67));
        dashboard.setForeground(Color.WHITE);
        dashboard.setHorizontalAlignment(SwingConstants.CENTER);
        dashboard.setVerticalAlignment(SwingConstants.CENTER);
        dashboard.setFont(new Font("Trebuchet MS", Font.PLAIN, 25));

        autoVip.setBackground(new Color(0x113F67));
        autoVip.setForeground(Color.WHITE);
        autoVip.setHorizontalAlignment(SwingConstants.CENTER);
        autoVip.setVerticalAlignment(SwingConstants.CENTER);
        autoVip.setFont(new Font("Trebuchet MS", Font.PLAIN, 25));
        autoVip.setIcon(vipLabel);
        autoVip.setVerticalTextPosition(SwingConstants.TOP);
        autoVip.setHorizontalTextPosition(SwingConstants.LEADING);
        autoVip.setIconTextGap(5);

        autoShout.setBackground(new Color(0x113F67));
        autoShout.setForeground(Color.WHITE);
        autoShout.setHorizontalAlignment(SwingConstants.CENTER);
        autoShout.setVerticalAlignment(SwingConstants.CENTER);
        autoShout.setFont(new Font("Trebuchet MS", Font.PLAIN, 25));
        autoShout.setIcon(soonLabel);
        autoShout.setVerticalTextPosition(SwingConstants.TOP);
        autoShout.setHorizontalTextPosition(SwingConstants.LEADING);
        autoShout.setIconTextGap(5);

        dashboard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                dashboard.setText("<html><u>Dashboard</u></html>");
                dashboard.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                dashboard.setText("Dashboard");
                dashboard.setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mousePressed(MouseEvent e) {
                windowHandler.openWindow(WindowHandler.WindowType.DASHBOARD);
            }
        });

        streams.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                streams.setText("<html><u>Streams</u></html>");
                streams.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                streams.setText("Streams");
                streams.setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mousePressed(MouseEvent e) {
                windowHandler.openWindow(WindowHandler.WindowType.STREAMS);
            }
        });

        logout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                logout.setText("<html><u>Logout</u></html>");
                logout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                logout.setText("Logout");
                logout.setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mousePressed(MouseEvent e) {
                accountHandler.logout();
                windowHandler.openWindow(WindowHandler.WindowType.LOGIN);
            }
        });

        copyright.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                copyright.setText("<html><u>Changes</u></html>");
                copyright.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                copyright.setText("Changes");
                copyright.setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mousePressed(MouseEvent e) {
                copyright.setBackground(new Color(0x113F67));
            }
        });

        discord.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                discord.setText("<html><u>Discord</u></html>");
                discord.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                discord.setText("Discord");
                discord.setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mousePressed(MouseEvent e) {
                discord.setBackground(new Color(0x113F67));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("https://discord.gg/r4FqG5E"));
                } catch (IOException | URISyntaxException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        support.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                support.setText("<html><u>Support</u></html>");
                support.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                support.setText("Support");
                support.setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mousePressed(MouseEvent e) {
                support.setBackground(new Color(0x113F67));
            }
        });

        autoShout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                autoShout.setText("<html><u>AutoShout</u></html>");
                autoShout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                autoShout.setText("AutoShout");
                autoShout.setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mousePressed(MouseEvent e) {
                autoShout.setBackground(new Color(0x113F67));
            }
        });

        autoVip.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                autoVip.setText("<html><u>AutoVIP</u></html>");
                autoVip.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                autoVip.setText("AutoVIP");
                autoVip.setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mousePressed(MouseEvent e) {
                windowHandler.openWindow(WindowHandler.WindowType.AUTOVIP);
            }
        });

        copyright.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                copyright.setText("<html><u>Changes</u></html>");
                copyright.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                copyright.setText("Changes");
                copyright.setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mousePressed(MouseEvent e) {
                windowHandler.openWindow(WindowHandler.WindowType.CHANGELOG);
            }
        });

        add(copyright);
        add(logout);
        add(discord);
        add(autoVip);
        add(autoShout);
        add(logo);
        add(dashboard);
        add(soon1);
        add(streams);
        add(support);
        add(soon3);

        logo.setBounds(5, 10, 245, 125);
        dashboard.setBounds(5, 150, 245, 50);
        autoShout.setBounds(5, 210, 245, 50);
        autoVip.setBounds(5, 270, 245, 50);
        soon3.setBounds(5, 330, 245, 50);
        soon1.setBounds(5, 390, 245, 50);
        support.setBounds(5, 450, 245, 50);
        copyright.setBounds(66, 680, 58, 30);
        streams.setBounds(127, 680, 58, 30);
        logout.setBounds(193, 680, 58, 30);
        discord.setBounds(5, 680, 58, 30);

        JLabel sidebarBackground = new JLabel("");
        sidebarBackground.setBounds(0, 0, 255, 720);
        sidebarBackground.setOpaque(true);
        add(sidebarBackground);
        sidebarBackground.setBackground(new Color(0x113F67));

    }
}
