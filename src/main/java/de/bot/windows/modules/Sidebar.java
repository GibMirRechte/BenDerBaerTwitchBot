package de.bot.windows.modules;

import de.bot.handler.AccountHandler;
import de.bot.handler.ImageIconHandler;
import de.bot.handler.UpdateHandler;
import de.bot.handler.WindowHandler;
import de.bot.utils.Account;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Sidebar extends JPanel {

    AccountHandler accountHandler = AccountHandler.getInstance();
    WindowHandler windowHandler = WindowHandler.getInstance();
    UpdateHandler updateHandler = UpdateHandler.getInstance();

    public Sidebar(WindowHandler.WindowType windowType) {
        Account account = accountHandler.getAccount();

        ImageIcon vipLabel = new ImageIcon(ImageIconHandler.imageType.VIP_ICON.imageIcon.getImage().getScaledInstance(37, 17, Image.SCALE_SMOOTH));
        ImageIcon soonLabel = new ImageIcon(ImageIconHandler.imageType.SOON_ICON.imageIcon.getImage().getScaledInstance(50, 17, Image.SCALE_SMOOTH));
        setPreferredSize(new Dimension(256, 720));
        setLayout(null);

        setVisible(true);
        setOpaque(true);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel automessages = new JLabel("AutoMessages");
        JLabel commands = new JLabel("Befehle");
        JLabel autoVip = new JLabel("AutoVIP");
        JLabel autoShout = new JLabel("AutoShout");
        JLabel staffDB = new JLabel("<html>\uD83D\uDC65 Staff-Dashboard</html>");
        JLabel adminDB = new JLabel("<html>\uD83D\uDC64 Admin-Dashboard</html>");
        JLabel sidebarHeader = new JLabel("<html><center>TwitchBot<br><font color=#eba134>Premium</font></center></html>");
        sidebarHeader.setForeground(Color.WHITE);
        sidebarHeader.setHorizontalAlignment(SwingConstants.CENTER);
        sidebarHeader.setVerticalAlignment(SwingConstants.CENTER);
        sidebarHeader.setFont(new Font("Arial", Font.BOLD, 20));
        sidebarHeader.setBackground(new Color(0x1A1A1A));
        sidebarHeader.setOpaque(true);

        if (account.getAccountRank().isTeam()) {
            sidebarHeader.setText("<html><center>TwitchBot<br><font color=red>Staff</font></center></html>");
        } else if (account.getAccountRank().isVip()) {
            sidebarHeader.setText("<html><center>TwitchBot<br><font color=#eba134>Premium</font></center></html>");
        } else {
            sidebarHeader.setText("<html><center>TwitchBot</center></html>");
        }

        JLabel dashboard = new JLabel("Dashboard");
        JLabel feedback = new JLabel("Feedback");
        JLabel privateMessages = new JLabel("Nachrichten");
        JLabel settings = new JLabel("Einstellungen");
        JLabel support = new JLabel("Support");
        JLabel livechat = new JLabel("Livechat");
        JLabel changes = new JLabel("Changes");
        JLabel discord = new JLabel("Discord");
        JLabel streams = new JLabel("Streams");
        JLabel logout = new JLabel("Logout");
        JLabel breakLineOne = new JLabel("<html><hr>⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀</html>");
        JLabel breakLineTwo = new JLabel("<html><hr>⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀</html>");
        JLabel breakLineThree = new JLabel("<html><hr>⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀</html>");

        changes.setForeground(Color.WHITE);
        changes.setHorizontalAlignment(SwingConstants.CENTER);
        changes.setVerticalAlignment(SwingConstants.CENTER);
        changes.setFont(new Font("Arial", Font.PLAIN, 13));
        if (windowType.equals(WindowHandler.WindowType.CHANGELOG)) {
            changes.setBackground(new Color(0x333333));
            changes.setOpaque(true);
        }

        streams.setForeground(Color.WHITE);
        streams.setHorizontalAlignment(SwingConstants.CENTER);
        streams.setVerticalAlignment(SwingConstants.CENTER);
        streams.setFont(new Font("Arial", Font.PLAIN, 13));
        if (windowType.equals(WindowHandler.WindowType.STREAMS)) {
            streams.setBackground(new Color(0x333333));
            streams.setOpaque(true);
        }

        logout.setForeground(Color.WHITE);
        logout.setHorizontalAlignment(SwingConstants.CENTER);
        logout.setVerticalAlignment(SwingConstants.CENTER);
        logout.setFont(new Font("Arial", Font.PLAIN, 13));

        discord.setForeground(Color.WHITE);
        discord.setHorizontalAlignment(SwingConstants.CENTER);
        discord.setVerticalAlignment(SwingConstants.CENTER);
        discord.setFont(new Font("Arial", Font.PLAIN, 13));

        Border emptyBorder = BorderFactory.createEmptyBorder(0, 20, 0, 0);

        livechat.setForeground(Color.WHITE);
        livechat.setIcon(soonLabel);
        livechat.setBorder(emptyBorder);
        livechat.setHorizontalAlignment(SwingConstants.LEFT);
        livechat.setVerticalAlignment(SwingConstants.CENTER);
        livechat.setVerticalTextPosition(SwingConstants.TOP);
        livechat.setHorizontalTextPosition(SwingConstants.LEADING);
        livechat.setFont(new Font("Arial", Font.PLAIN, 16));
        if (windowType.equals(WindowHandler.WindowType.LIVECHAT)) {
            livechat.setBackground(new Color(0x333333));
            livechat.setOpaque(true);
        }

        settings.setForeground(Color.WHITE);
        settings.setBorder(emptyBorder);
        settings.setHorizontalAlignment(SwingConstants.LEFT);
        settings.setVerticalAlignment(SwingConstants.CENTER);
        settings.setFont(new Font("Arial", Font.PLAIN, 16));
        if (windowType.equals(WindowHandler.WindowType.SETTINGS)) {
            settings.setBackground(new Color(0x333333));
            settings.setOpaque(true);
        }

        feedback.setForeground(Color.WHITE);
        feedback.setBorder(emptyBorder);
        feedback.setHorizontalAlignment(SwingConstants.LEFT);
        feedback.setVerticalAlignment(SwingConstants.CENTER);
        feedback.setFont(new Font("Arial", Font.PLAIN, 16));
        if (windowType.equals(WindowHandler.WindowType.FEEDBACK)) {
            feedback.setBackground(new Color(0x333333));
            feedback.setOpaque(true);
        }

        support.setForeground(Color.WHITE);
        support.setIcon(soonLabel);
        support.setBorder(emptyBorder);
        support.setHorizontalAlignment(SwingConstants.LEFT);
        support.setVerticalAlignment(SwingConstants.CENTER);
        support.setVerticalTextPosition(SwingConstants.TOP);
        support.setHorizontalTextPosition(SwingConstants.LEADING);
        support.setFont(new Font("Arial", Font.PLAIN, 16));

        privateMessages.setForeground(Color.WHITE);
        privateMessages.setIcon(soonLabel);
        privateMessages.setBorder(emptyBorder);
        privateMessages.setHorizontalAlignment(SwingConstants.LEFT);
        privateMessages.setVerticalAlignment(SwingConstants.CENTER);
        privateMessages.setVerticalTextPosition(SwingConstants.TOP);
        privateMessages.setHorizontalTextPosition(SwingConstants.LEADING);
        privateMessages.setFont(new Font("Arial", Font.PLAIN, 16));

        automessages.setForeground(Color.WHITE);
        automessages.setIcon(soonLabel);
        automessages.setHorizontalAlignment(SwingConstants.LEFT);
        automessages.setVerticalAlignment(SwingConstants.CENTER);
        automessages.setVerticalTextPosition(SwingConstants.TOP);
        automessages.setHorizontalTextPosition(SwingConstants.LEADING);
        automessages.setFont(new Font("Arial", Font.PLAIN, 16));
        automessages.setBorder(emptyBorder);
        if (windowType.equals(WindowHandler.WindowType.AUTOMESSAGES)) {
            automessages.setBackground(new Color(0x333333));
            automessages.setOpaque(true);
        }

        adminDB.setForeground(Color.WHITE);
        adminDB.setHorizontalAlignment(SwingConstants.LEFT);
        adminDB.setVerticalAlignment(SwingConstants.CENTER);
        adminDB.setFont(new Font("Arial", Font.PLAIN, 16));
        adminDB.setBorder(emptyBorder);
        if (windowType.equals(WindowHandler.WindowType.ADMINDASHBOARD) ||
                windowType.equals(WindowHandler.WindowType.ADMINDASH_USERMANAGEMENT) ||
                windowType.equals(WindowHandler.WindowType.ADMINDASH_CREATENEWS) ||
                windowType.equals(WindowHandler.WindowType.ADMINDASH_NEWSMANAGEMENT) ||
                windowType.equals(WindowHandler.WindowType.ADMINDASH_CREATEUSER) ||
                windowType.equals(WindowHandler.WindowType.ADMINDASH_CREATERANK)) {
            adminDB.setBackground(new Color(0x333333));
            adminDB.setOpaque(true);
        }

        staffDB.setForeground(Color.WHITE);
        staffDB.setHorizontalAlignment(SwingConstants.LEFT);
        staffDB.setVerticalAlignment(SwingConstants.CENTER);
        staffDB.setFont(new Font("Arial", Font.PLAIN, 16));
        staffDB.setBorder(emptyBorder);
        if (windowType.equals(WindowHandler.WindowType.STAFFDASHBOARD) ||
                windowType.equals(WindowHandler.WindowType.BanUser) ||
                windowType.equals(WindowHandler.WindowType.WarnUser) ||
                windowType.equals(WindowHandler.WindowType.UserInfo)) {
            staffDB.setBackground(new Color(0x333333));
            staffDB.setOpaque(true);
        }

        commands.setForeground(Color.WHITE);
        commands.setIcon(soonLabel);
        commands.setHorizontalAlignment(SwingConstants.LEFT);
        commands.setVerticalAlignment(SwingConstants.CENTER);
        commands.setVerticalTextPosition(SwingConstants.TOP);
        commands.setBorder(emptyBorder);
        commands.setHorizontalTextPosition(SwingConstants.LEADING);
        commands.setFont(new Font("Arial", Font.PLAIN, 16));

        dashboard.setForeground(Color.WHITE);
        dashboard.setHorizontalAlignment(SwingConstants.LEFT);
        dashboard.setVerticalAlignment(SwingConstants.CENTER);
        dashboard.setBorder(emptyBorder);
        dashboard.setFont(new Font("Arial", Font.PLAIN, 16));
        if (windowType.equals(WindowHandler.WindowType.DASHBOARD)) {
            dashboard.setBackground(new Color(0x333333));
            dashboard.setOpaque(true);
        }

        autoVip.setForeground(Color.WHITE);
        autoVip.setHorizontalAlignment(SwingConstants.LEFT);
        autoVip.setVerticalAlignment(SwingConstants.CENTER);
        autoVip.setFont(new Font("Arial", Font.PLAIN, 16));
        autoVip.setIcon(vipLabel);
        autoVip.setVerticalTextPosition(SwingConstants.TOP);
        autoVip.setHorizontalTextPosition(SwingConstants.LEADING);
        autoVip.setIconTextGap(5);
        autoVip.setBorder(emptyBorder);
        if (windowType.equals(WindowHandler.WindowType.AUTOVIP)) {
            autoVip.setBackground(new Color(0x333333));
            autoVip.setOpaque(true);
        }

        autoShout.setForeground(Color.WHITE);
        autoShout.setHorizontalAlignment(SwingConstants.LEFT);
        autoShout.setVerticalAlignment(SwingConstants.CENTER);
        autoShout.setFont(new Font("Arial", Font.PLAIN, 16));
        autoShout.setIconTextGap(5);
        autoShout.setBorder(emptyBorder);
        if (windowType.equals(WindowHandler.WindowType.AUTOSHOUT)) {
            autoShout.setBackground(new Color(0x333333));
            autoShout.setOpaque(true);
        }

        livechat.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                livechat.setBackground(new Color(0x333333));
                livechat.setOpaque(true);
                livechat.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!windowType.equals(WindowHandler.WindowType.LIVECHAT)) {
                    livechat.setBackground(null);
                    livechat.setOpaque(false);
                }
                livechat.setCursor(Cursor.getDefaultCursor());
            }
        });

        feedback.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                feedback.setBackground(new Color(0x333333));
                feedback.setOpaque(true);
                feedback.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!windowType.equals(WindowHandler.WindowType.FEEDBACK)) {
                    feedback.setBackground(null);
                    feedback.setOpaque(false);
                }
                feedback.setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mousePressed(MouseEvent e) {
                windowHandler.openWindow(WindowHandler.WindowType.FEEDBACK);
            }
        });

        settings.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                settings.setBackground(new Color(0x333333));
                settings.setOpaque(true);
                settings.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!windowType.equals(WindowHandler.WindowType.SETTINGS)) {
                    settings.setBackground(null);
                    settings.setOpaque(false);
                }
                settings.setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mousePressed(MouseEvent e) {
                windowHandler.openWindow(WindowHandler.WindowType.SETTINGS);
            }
        });

        dashboard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                dashboard.setBackground(new Color(0x333333));
                dashboard.setOpaque(true);
                dashboard.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!windowType.equals(WindowHandler.WindowType.DASHBOARD)) {
                    dashboard.setBackground(null);
                    dashboard.setOpaque(false);
                }
                dashboard.setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mousePressed(MouseEvent e) {
                windowHandler.openWindow(WindowHandler.WindowType.DASHBOARD);
            }
        });

        staffDB.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                staffDB.setBackground(new Color(0x333333));
                staffDB.setOpaque(true);
                staffDB.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!windowType.equals(WindowHandler.WindowType.STAFFDASHBOARD) &&
                        !windowType.equals(WindowHandler.WindowType.UserInfo) &&
                        !windowType.equals(WindowHandler.WindowType.BanUser) &&
                        !windowType.equals(WindowHandler.WindowType.WarnUser)) {
                    staffDB.setBackground(null);
                    staffDB.setOpaque(false);
                }
                staffDB.setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mousePressed(MouseEvent e) {
                windowHandler.openWindow(WindowHandler.WindowType.STAFFDASHBOARD);
            }
        });

        adminDB.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                adminDB.setBackground(new Color(0x333333));
                adminDB.setOpaque(true);
                adminDB.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!windowType.equals(WindowHandler.WindowType.ADMINDASHBOARD) &&
                        !windowType.equals(WindowHandler.WindowType.ADMINDASH_USERMANAGEMENT) &&
                        !windowType.equals(WindowHandler.WindowType.ADMINDASH_CREATENEWS) &&
                        !windowType.equals(WindowHandler.WindowType.ADMINDASH_NEWSMANAGEMENT) &&
                        !windowType.equals(WindowHandler.WindowType.ADMINDASH_CREATEUSER)) {
                    adminDB.setBackground(null);
                    adminDB.setOpaque(false);
                }
                adminDB.setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mousePressed(MouseEvent e) {
                windowHandler.openWindow(WindowHandler.WindowType.ADMINDASHBOARD);
            }
        });

        privateMessages.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                privateMessages.setBackground(new Color(0x333333));
                privateMessages.setOpaque(true);
                privateMessages.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!windowType.equals(WindowHandler.WindowType.PRIVATE_MESSAGES)) {
                    privateMessages.setBackground(null);
                    privateMessages.setOpaque(false);
                }
                privateMessages.setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }
        });

        commands.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                commands.setBackground(new Color(0x333333));
                commands.setOpaque(true);
                commands.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!windowType.equals(WindowHandler.WindowType.COMMANDS)) {
                    commands.setBackground(null);
                    commands.setOpaque(false);
                }
                commands.setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }
        });

        automessages.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                automessages.setBackground(new Color(0x333333));
                automessages.setOpaque(true);
                automessages.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!windowType.equals(WindowHandler.WindowType.AUTOMESSAGES)) {
                    automessages.setBackground(null);
                    automessages.setOpaque(false);
                }
                automessages.setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mousePressed(MouseEvent e) {
                windowHandler.openWindow(WindowHandler.WindowType.AUTOMESSAGES);
            }
        });

        streams.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                streams.setBackground(new Color(0x333333));
                streams.setOpaque(true);
                streams.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!windowType.equals(WindowHandler.WindowType.STREAMS)) {
                    streams.setBackground(null);
                    streams.setOpaque(false);
                }
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
                logout.setBackground(new Color(0x333333));
                logout.setOpaque(true);
                logout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                logout.setBackground(null);
                logout.setOpaque(false);
                logout.setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mousePressed(MouseEvent e) {
                accountHandler.logout();
                windowHandler.openWindow(WindowHandler.WindowType.LOGIN);
            }
        });

        discord.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                discord.setBackground(new Color(0x333333));
                discord.setOpaque(true);
                discord.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                discord.setBackground(null);
                discord.setOpaque(false);
                discord.setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mousePressed(MouseEvent e) {
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
                support.setBackground(new Color(0x333333));
                support.setOpaque(true);
                support.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!windowType.equals(WindowHandler.WindowType.SUPPORT)) {
                    support.setBackground(null);
                    support.setOpaque(false);
                }
                support.setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }
        });

        autoShout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                autoShout.setBackground(new Color(0x333333));
                autoShout.setOpaque(true);
                autoShout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!windowType.equals(WindowHandler.WindowType.AUTOSHOUT)) {
                    autoShout.setBackground(null);
                    autoShout.setOpaque(false);
                }
                autoShout.setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mousePressed(MouseEvent e) {
                windowHandler.openWindow(WindowHandler.WindowType.AUTOSHOUT);
            }
        });

        autoVip.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                autoVip.setBackground(new Color(0x333333));
                autoVip.setOpaque(true);
                autoVip.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!windowType.equals(WindowHandler.WindowType.AUTOVIP)) {
                    autoVip.setBackground(null);
                    autoVip.setOpaque(false);
                }
                autoVip.setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mousePressed(MouseEvent e) {
                windowHandler.openWindow(WindowHandler.WindowType.AUTOVIP);
            }
        });

        changes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                changes.setBackground(new Color(0x333333));
                changes.setOpaque(true);
                changes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!windowType.equals(WindowHandler.WindowType.CHANGELOG)) {
                    changes.setBackground(null);
                    changes.setOpaque(false);
                }
                changes.setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mousePressed(MouseEvent e) {
                windowHandler.openNewsPage("Changelogv" + updateHandler.getCurrentVersion());
            }
        });

        if (account.getAccountRank().isAdmin_team()) {
            add(breakLineThree);
            breakLineThree.setBounds(35, 490, 180, 20);
            add(adminDB);
            adminDB.setBounds(35, 506, 180, 35);
            add(staffDB);
            staffDB.setBounds(35, 542, 180, 35);
        } else if (account.getAccountRank().isTeam()) {
            add(breakLineThree);
            breakLineThree.setBounds(35, 490, 180, 20);
            add(staffDB);
            staffDB.setBounds(35, 506, 180, 35);
        }

        add(breakLineOne);
        add(breakLineTwo);
        add(changes);
        add(logout);
        add(discord);
        add(autoVip);
        add(autoShout);
        add(sidebarHeader);
        add(feedback);
        add(dashboard);
        add(settings);
        add(streams);
        add(support);
        add(automessages);
        add(commands);
        add(privateMessages);
        add(livechat);

        sidebarHeader.setBounds(0, 0, 255, 75);
        dashboard.setBounds(32, 100, 192, 35);
        breakLineOne.setBounds(32, 135, 192, 20);
        autoShout.setBounds(32, 151, 192, 35);
        autoVip.setBounds(32, 187, 192, 35);
        automessages.setBounds(32, 223, 192, 35);
        commands.setBounds(32, 259, 192, 35);
        breakLineTwo.setBounds(32, 295, 192, 20);
        feedback.setBounds(32, 311, 192, 35);
        privateMessages.setBounds(32, 347, 192, 35);
        settings.setBounds(32, 383, 192, 35);
        support.setBounds(32, 419, 192, 35);
        livechat.setBounds(32, 455, 192, 35);
        changes.setBounds(66, 776, 61, 30);
        streams.setBounds(127, 776, 61, 30);
        logout.setBounds(188, 776, 61, 30);
        discord.setBounds(5, 776, 61, 30);

        JLabel sidebarBackground = new JLabel("");
        sidebarBackground.setBounds(0, 0, 256, 816);
        sidebarBackground.setOpaque(true);
        add(sidebarBackground);
        sidebarBackground.setBackground(new Color(0x262626));

    }
}
