package de.bot.windows;

import de.bot.handler.ImageIconHandler;
import de.bot.handler.UpdateHandler;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
        JLabel soon2 = new JLabel("Coming Soon");
        JLabel copyright = new JLabel("Created with ❤ by GibMirRechte");

        copyright.setBackground(new Color(0x113F67));
        copyright.setForeground(Color.WHITE);
        copyright.setHorizontalAlignment(SwingConstants.CENTER);
        copyright.setVerticalAlignment(SwingConstants.CENTER);
        copyright.setFont(new Font("Oswald Medium", Font.PLAIN, 15));

        soon1.setBackground(new Color(0x113F67));
        soon1.setForeground(Color.WHITE);
        soon1.setHorizontalAlignment(SwingConstants.CENTER);
        soon1.setVerticalAlignment(SwingConstants.CENTER);
        soon1.setFont(new Font("Oswald Medium", Font.PLAIN, 36));

        soon2.setBackground(new Color(0x113F67));
        soon2.setForeground(Color.WHITE);
        soon2.setHorizontalAlignment(SwingConstants.CENTER);
        soon2.setVerticalAlignment(SwingConstants.CENTER);
        soon2.setFont(new Font("Oswald Medium", Font.PLAIN, 36));

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

        // Erstelle ein abgerundetes Border
        Border roundedBorder = BorderFactory.createSoftBevelBorder(15);

        autoShout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                autoShout.setText("<html><u>AutoShout</u></html>");
                autoShout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                autoShout.setBorder(roundedBorder);
                autoShout.setText("AutoShout");
                autoShout.setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mousePressed(MouseEvent e) {
                autoShout.setBorder(null);
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
                autoVip.setBorder(roundedBorder);
                autoVip.setText("AutoVIP");
                autoVip.setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mousePressed(MouseEvent e) {
                autoVip.setBorder(null);
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
                account.setBorder(roundedBorder);
                account.setText("Account");
                account.setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mousePressed(MouseEvent e) {
                account.setBorder(null);
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
        add(autoVip);
        add(autoShout);
        add(logo);
        add(account);
        add(soon1);
        add(soon2);
        add(soon3);

        copyright.setBounds(5, 680, 245, 30);
        autoVip.setBounds(5, 360, 245, 70);
        autoShout.setBounds(5, 280, 245, 70);
        logo.setBounds(5, 10, 245, 180);
        account.setBounds(5, 200, 245, 70);
        soon1.setBounds(5, 600, 245, 70);
        soon2.setBounds(5, 520, 245, 70);
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
