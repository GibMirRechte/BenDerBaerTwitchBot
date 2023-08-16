package de.bot.windows;

import de.bot.handler.ImageIconHandler;
import de.bot.handler.UpdateHandler;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class Dashboard extends JPanel {

    UpdateHandler updateHandler = new UpdateHandler();

    public Dashboard() {
        ImageIcon vipLabel = new ImageIcon(ImageIconHandler.imageType.VIP_ICON.imageIcon.getImage().getScaledInstance(37, 17, Image.SCALE_SMOOTH));
        setPreferredSize(new Dimension(1280, 720));
        setLayout(null);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel soon3 = new JLabel("Coming Soon");
        JLabel autoVip = new JLabel("AutoVIP");
        JLabel autoShout = new JLabel("AutoShout");
        JLabel logo = new JLabel(ImageIconHandler.imageType.LOGO_STAFF.imageIcon);
        JLabel account = new JLabel("Account");
        JLabel soon1 = new JLabel("Coming Soon");
        JLabel support = new JLabel("Support");
        JLabel copyright = new JLabel("Team");
        JLabel discord = new JLabel("Discord");
        JLabel streams = new JLabel("Streams");
        JLabel logout = new JLabel("Logout");

        copyright.setBackground(new Color(0x113F67));
        copyright.setForeground(Color.WHITE);
        copyright.setHorizontalAlignment(SwingConstants.CENTER);
        copyright.setVerticalAlignment(SwingConstants.CENTER);
        copyright.setFont(new Font("Oswald Medium", Font.PLAIN, 15));

        streams.setBackground(new Color(0x113F67));
        streams.setForeground(Color.WHITE);
        streams.setHorizontalAlignment(SwingConstants.CENTER);
        streams.setVerticalAlignment(SwingConstants.CENTER);
        streams.setFont(new Font("Oswald Medium", Font.PLAIN, 15));

        logout.setBackground(new Color(0x113F67));
        logout.setForeground(Color.WHITE);
        logout.setHorizontalAlignment(SwingConstants.CENTER);
        logout.setVerticalAlignment(SwingConstants.CENTER);
        logout.setFont(new Font("Oswald Medium", Font.PLAIN, 15));

        discord.setBackground(new Color(0x113F67));
        discord.setForeground(Color.WHITE);
        discord.setHorizontalAlignment(SwingConstants.CENTER);
        discord.setVerticalAlignment(SwingConstants.CENTER);
        discord.setFont(new Font("Oswald Medium", Font.PLAIN, 15));

        soon1.setBackground(new Color(0x113F67));
        soon1.setForeground(Color.WHITE);
        soon1.setHorizontalAlignment(SwingConstants.CENTER);
        soon1.setVerticalAlignment(SwingConstants.CENTER);
        soon1.setFont(new Font("Oswald Medium", Font.PLAIN, 36));

        support.setBackground(new Color(0x113F67));
        support.setForeground(Color.WHITE);
        support.setHorizontalAlignment(SwingConstants.CENTER);
        support.setVerticalAlignment(SwingConstants.CENTER);
        support.setFont(new Font("Oswald Medium", Font.PLAIN, 36));

        soon3.setBackground(new Color(0x113F67));
        soon3.setForeground(Color.WHITE);
        soon3.setHorizontalAlignment(SwingConstants.CENTER);
        soon3.setVerticalAlignment(SwingConstants.CENTER);
        soon3.setFont(new Font("Oswald Medium", Font.PLAIN, 36));

        account.setBackground(new Color(0x113F67));
        account.setForeground(Color.WHITE);
        account.setHorizontalAlignment(SwingConstants.CENTER);
        account.setVerticalAlignment(SwingConstants.CENTER);
        account.setFont(new Font("Oswald Medium", Font.PLAIN, 36));

        autoVip.setBackground(new Color(0x113F67));
        autoVip.setForeground(Color.WHITE);
        autoVip.setHorizontalAlignment(SwingConstants.CENTER);
        autoVip.setVerticalAlignment(SwingConstants.CENTER);
        autoVip.setFont(new Font("Oswald Medium", Font.PLAIN, 36));

        autoShout.setBackground(new Color(0x113F67));
        autoShout.setForeground(Color.WHITE);
        autoShout.setHorizontalAlignment(SwingConstants.CENTER);
        autoShout.setVerticalAlignment(SwingConstants.CENTER);
        autoShout.setFont(new Font("Oswald Medium", Font.PLAIN, 36));

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
                streams.setBackground(new Color(0x113F67));
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
                logout.setBackground(new Color(0x113F67));
            }
        });

        copyright.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                copyright.setText("<html><u>Team</u></html>");
                copyright.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                copyright.setText("Team");
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
                autoVip.setBackground(new Color(0x113F67));
            }
        });

        account.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                account.setText("<html><u>Account</u></html>");
                account.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                account.setText("Account");
                account.setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mousePressed(MouseEvent e) {
                account.setBackground(new Color(0x113F67));
            }
        });

        autoVip.setIcon(vipLabel);
        autoVip.setVerticalTextPosition(SwingConstants.TOP);
        autoVip.setHorizontalTextPosition(SwingConstants.LEADING);
        autoVip.setIconTextGap(5);

        if (updateHandler.hasNewUpdate()) {
            JLabel updateAvailable = new JLabel(ImageIconHandler.imageType.UPDATE_NOTIFICATION.imageIcon);
            add(updateAvailable);
            updateAvailable.setBounds(427, 177, 685, 365);
        }

        add(copyright);
        add(logout);
        add(discord);
        add(autoVip);
        add(autoShout);
        add(logo);
        add(account);
        add(soon1);
        add(streams);
        add(support);
        add(soon3);

        copyright.setBounds(66, 680, 58, 30);
        streams.setBounds(127, 680, 58, 30);
        logout.setBounds(193, 680, 58, 30);
        discord.setBounds(5, 680, 58, 30);
        autoVip.setBounds(5, 360, 245, 70);
        autoShout.setBounds(5, 280, 245, 70);
        logo.setBounds(5, 10, 245, 180);
        account.setBounds(5, 200, 245, 70);
        soon1.setBounds(5, 520, 245, 70);
        support.setBounds(5, 600, 245, 70);
        soon3.setBounds(5, 440, 245, 70);

        JLabel sidebarBackground = new JLabel("");
        sidebarBackground.setBounds(0, 0, 255, 720);
        sidebarBackground.setOpaque(true);
        add(sidebarBackground);
        sidebarBackground.setBackground(new Color(0x113F67));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("TwitchBot");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new Dashboard()).setBackground(new Color(0x272727));
        frame.setVisible(true);
        frame.pack();
    }
}
