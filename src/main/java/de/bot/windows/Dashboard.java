package de.bot.windows;

import de.bot.handler.ImageIconHandler;
import de.bot.handler.UpdateHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class Dashboard extends JPanel {

    UpdateHandler updateHandler = new UpdateHandler();

    public Dashboard() {
        setPreferredSize(new Dimension(1280, 720));
        setLayout(null);
        File file1 = new File("C:\\Users\\User\\Desktop\\created.png");

        JButton jcomp8 = new JButton("newButton");
        JLabel autoVip = new JLabel(ImageIconHandler.imageType.SIDEBAR_AUTOVIP_NORMAL.imageIcon);
        autoVip.setOpaque(false);
        JLabel autoShout = new JLabel(ImageIconHandler.imageType.SIDEBAR_AUTOSHOUT_NORMAL.imageIcon);
        JLabel logo = new JLabel(ImageIconHandler.imageType.LOGO_STAFF.imageIcon);
        JLabel account = new JLabel(ImageIconHandler.imageType.SIDEBAR_ACCOUNT_NORMAL.imageIcon);
        JButton jcomp6 = new JButton("newButton");
        JButton jcomp7 = new JButton("newButton");
        JLabel jcomp1 = new JLabel(new ImageIcon(file1.getAbsolutePath()));

        new Thread(() -> {
            try {
                int state = 0;
                while (true) {
                    Thread.sleep(3000);
                    if (state == 0) {
                        logo.setIcon(ImageIconHandler.imageType.LOGO_PREMIUM.imageIcon);
                        state = 1;
                    } else {
                        logo.setIcon(ImageIconHandler.imageType.LOGO_STAFF.imageIcon);
                        state = 0;
                    }
                }
            } catch (Exception ignored) {
            }
        }).start();

        autoVip.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                autoVip.setIcon(ImageIconHandler.imageType.SIDEBAR_AUTOVIP_HOVERED.imageIcon);
                autoVip.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                autoVip.setIcon(ImageIconHandler.imageType.SIDEBAR_AUTOVIP_NORMAL.imageIcon);
                autoVip.setCursor(Cursor.getDefaultCursor());
            }
        });

        account.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                account.setIcon(ImageIconHandler.imageType.SIDEBAR_ACCOUNT_HOVERED.imageIcon);
                account.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                account.setIcon(ImageIconHandler.imageType.SIDEBAR_ACCOUNT_NORMAL.imageIcon);
                account.setCursor(Cursor.getDefaultCursor());
            }
        });

        autoShout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                autoShout.setIcon(ImageIconHandler.imageType.SIDEBAR_AUTOSHOUT_HOVERED.imageIcon);
                autoShout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                autoShout.setIcon(ImageIconHandler.imageType.SIDEBAR_AUTOSHOUT_NORMAL.imageIcon);
                autoShout.setCursor(Cursor.getDefaultCursor());
            }
        });

        if (updateHandler.hasNewUpdate()) {
            File file = new File("C:\\Users\\User\\Desktop\\update.png");
            JLabel updateAvailable = new JLabel(new ImageIcon(file.getAbsolutePath()));
            add(updateAvailable);

            updateAvailable.setBounds(427, 177, 685, 365);
        }

        add(jcomp1);
        add(autoVip);
        add(autoShout);
        add(logo);
        add(account);
        add(jcomp6);
        add(jcomp7);
        add(jcomp8);

        jcomp1.setBounds(5, 680, 245, 30);
        autoVip.setBounds(5, 360, 245, 70);
        autoShout.setBounds(5, 280, 245, 70);
        logo.setBounds(5, 10, 245, 180);
        account.setBounds(5, 200, 245, 70);
        jcomp6.setBounds(5, 600, 245, 70);
        jcomp7.setBounds(5, 520, 245, 70);
        jcomp8.setBounds(5, 440, 245, 70);

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
