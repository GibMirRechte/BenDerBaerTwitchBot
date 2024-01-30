package de.bot.windows;

import de.bot.handler.AccountHandler;
import de.bot.handler.ImageIconHandler;
import de.bot.handler.UpdateHandler;
import de.bot.utils.Account;
import de.bot.utils.Announcement;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

public class AutoVIP extends JPanel {

    UpdateHandler updateHandler = UpdateHandler.getInstance();
    AccountHandler accountHandler = AccountHandler.getInstance();

    public AutoVIP() {
        setPreferredSize(new Dimension(1040, 816));
        setLayout(null);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

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
        announcement.setFont(new Font("Arial", Font.PLAIN, 14));

        Account acc = accountHandler.getAccount();

        JLabel title = new JLabel("AutoVIP Einstellungen");
        JLabel monthsTitle = new JLabel("Aktivitäts Voraussetzung");
        JLabel monthsUndertitle = new JLabel("Gebe an, wie lange ein Tracking-Intervall gültig ist (In Monaten)");
        JLabel streamsTitle = new JLabel("Benötigte Streamaktivität");
        JLabel streamsUndertitle = new JLabel("Gebe an, in wie vielen Streams ein Nutzer pro Intervall aktiv sein muss. (Pro Tag wird max. 1 Stream gezählt)");
        JLabel saveButton = new JLabel("Speichern");
        JSlider months = new JSlider(1, 12);
        JSlider streamSlider = new JSlider(10, 60);
        JLabel saveFeedback = new JLabel("Die Einstellungen wurden erfolgreich gespeichert.");
        JTextArea sliderBackground = new JTextArea("");

        Border emptyBorder = BorderFactory.createEmptyBorder(0, 10, 0, 10);

        months.setBackground(new Color(0x262626));
        months.setForeground(Color.WHITE);
        months.setFont(new Font("Arial", Font.PLAIN, 15));
        months.setMinorTickSpacing(0);
        months.setMajorTickSpacing(1);
        months.setBorder(emptyBorder);
        months.setValue(acc.getAutoVIPSettings().getMonths());
        months.setPaintTicks(true);
        months.setPaintLabels(true);

        saveFeedback.setBackground(new Color(0x05A489));
        saveFeedback.setForeground(Color.WHITE);
        saveFeedback.setOpaque(true);
        saveFeedback.setVisible(false);
        saveFeedback.setHorizontalAlignment(JLabel.CENTER);
        saveFeedback.setHorizontalTextPosition(JLabel.RIGHT);
        saveFeedback.setFont(new Font("Arial", Font.PLAIN, 14));


        saveButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                saveButton.setBackground(new Color(0x236C9F));
                saveButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                saveButton.setBackground(new Color(0x1E5E8B));
                saveButton.setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                saveFeedback.setVisible(true);
                try {
                    Socket socket = new Socket("45.93.249.139", 3459);
                    OutputStream outputStream = socket.getOutputStream();
                    PrintStream printStream = new PrintStream(outputStream);
                    printStream.println("SAVE");
                    printStream.println("AutoVIP");
                    printStream.println(acc.getName());
                    printStream.println(months.getValue());
                    printStream.println(streamSlider.getValue());

                    saveFeedback.setVisible(true);
                    acc.getAutoVIPSettings().updateAutoVIPSettings(months.getValue(), streamSlider.getValue());
                } catch (Exception ignored) {
                }
            }
        });

        streamSlider.setBackground(new Color(0x262626));
        streamSlider.setForeground(Color.WHITE);
        streamSlider.setFont(new Font("Arial", Font.PLAIN, 15));

        streamSlider.setMinorTickSpacing(1);
        streamSlider.setBorder(emptyBorder);
        streamSlider.setMajorTickSpacing(5);
        streamSlider.setPaintTicks(true);
        streamSlider.setValue(acc.getAutoVIPSettings().getStreams());
        streamSlider.setPaintLabels(true);

        sliderBackground.setBackground(new Color(0x1A1A1A));
        sliderBackground.setVisible(true);
        sliderBackground.setOpaque(true);
        sliderBackground.setBorder(BorderFactory.createLineBorder(new Color(0x333333), 1));

        saveButton.setBackground(new Color(0x1E5E8B));
        saveButton.setOpaque(true);
        saveButton.setForeground(Color.WHITE);
        saveButton.setHorizontalAlignment(SwingConstants.CENTER);
        saveButton.setVerticalAlignment(SwingConstants.CENTER);
        saveButton.setFont(new Font("Arial", Font.PLAIN, 28));

        saveButton.setBounds(20, 750, 1000, 50);

        add(title);
        add(monthsTitle);
        add(monthsUndertitle);
        add(months);
        add(saveFeedback);
        add(announcement);
        add(saveButton);
        add(streamSlider);
        add(streamsTitle);
        add(streamsUndertitle);
        add(sliderBackground);
        //add(whitelistArea);
        //add(whitelistTitle);
        //add(whitelistUndertitle);
        //add(blacklistTitle);
        //add(blacklistUndertitle);
        //add(blacklistArea);

        announcement.setBounds(183, 75, 660, 40);
        saveFeedback.setBounds(200, 120, 625, 40);
        title.setBounds(40, 10, 600, 36);

        monthsTitle.setBounds(40, 175, 650, 20);
        monthsUndertitle.setBounds(40, 195, 650, 14);
        months.setBounds(40, 220, 550, 55);

        streamsTitle.setBounds(40, 280, 650, 20);
        streamsUndertitle.setBounds(40, 300, 600, 14);
        streamSlider.setBounds(40, 325, 550, 55);

        sliderBackground.setBounds(20, 155, 640, 245);

        streamsTitle.setForeground(Color.WHITE);
        streamsTitle.setHorizontalAlignment(SwingConstants.LEFT);
        streamsTitle.setVerticalAlignment(SwingConstants.CENTER);
        streamsTitle.setFont(new Font("Arial", Font.PLAIN, 18));

        streamsUndertitle.setForeground(Color.GRAY);
        streamsUndertitle.setHorizontalAlignment(SwingConstants.LEFT);
        streamsUndertitle.setVerticalAlignment(SwingConstants.CENTER);
        streamsUndertitle.setFont(new Font("Arial", Font.ITALIC, 12));

        monthsTitle.setForeground(Color.WHITE);
        monthsTitle.setHorizontalAlignment(SwingConstants.LEFT);
        monthsTitle.setVerticalAlignment(SwingConstants.CENTER);
        monthsTitle.setFont(new Font("Arial", Font.PLAIN, 18));

        monthsUndertitle.setForeground(Color.GRAY);
        monthsUndertitle.setHorizontalAlignment(SwingConstants.LEFT);
        monthsUndertitle.setVerticalAlignment(SwingConstants.CENTER);
        monthsUndertitle.setFont(new Font("Arial", Font.ITALIC, 12));

        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(SwingConstants.LEFT);
        title.setVerticalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.PLAIN, 36));
    }

}
