package de.bot.windows;

import de.bot.handler.AccountHandler;
import de.bot.handler.ImageIconHandler;
import de.bot.handler.UpdateHandler;
import de.bot.utils.Account;
import de.bot.utils.Announcement;

import javax.swing.*;
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
        setPreferredSize(new Dimension(1025, 720));
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
        announcement.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));

        Account acc = accountHandler.getAccount();

        JLabel welcomeText = new JLabel("AutoVIP Einstellungen");
        JLabel monthsTitle = new JLabel("Aktivitäts Voraussetzung");
        JLabel monthsUndertitle = new JLabel("Gebe an, wie lange ein Tracking-Intervall gültig ist (In Monaten)");
        JLabel streamsTitle = new JLabel("Benötigte Streamaktivität");
        JLabel streamsUndertitle = new JLabel("Gebe an, in wie vielen Streams ein Nutzer pro Intervall aktiv sein muss. (Pro Tag wird max. 1 Stream gezählt)");
        JLabel saveButton = new JLabel("Speichern");
        JSlider months = new JSlider(1, 12);
        JSlider streamSlider = new JSlider(10, 60);
        JLabel saveFeedback = new JLabel("Die Einstellungen wurden erfolgreich gespeichert.");

        months.setBackground(new Color(0x113F67));
        months.setForeground(Color.WHITE);
        months.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
        months.setMinorTickSpacing(0);
        months.setMajorTickSpacing(1);
        months.setValue(acc.getAutoVIP_months());
        months.setPaintTicks(true);
        months.setPaintLabels(true);

        saveFeedback.setBackground(new Color(0x05A489));
        saveFeedback.setForeground(Color.WHITE);
        saveFeedback.setOpaque(true);
        saveFeedback.setVisible(false);
        saveFeedback.setHorizontalAlignment(JLabel.CENTER);
        saveFeedback.setHorizontalTextPosition(JLabel.RIGHT);
        saveFeedback.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));


        saveButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                saveButton.setText("<html><u>Speichern</u></html>");
                saveButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                saveButton.setText("Speichern");
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
                    accountHandler.getAccount().updateAutoVIPSettings(months.getValue(), streamSlider.getValue());
                } catch (Exception ignored) {

                }
            }
        });


        streamSlider.setBackground(new Color(0x113F67));
        streamSlider.setForeground(Color.WHITE);
        streamSlider.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));

        streamSlider.setMinorTickSpacing(1);
        streamSlider.setMajorTickSpacing(5);
        streamSlider.setPaintTicks(true);
        streamSlider.setValue(acc.getAutoVIP_streams());
        streamSlider.setPaintLabels(true);

        saveButton.setBackground(new Color(0x86BECC));
        saveButton.setOpaque(true);
        saveButton.setForeground(Color.WHITE);
        saveButton.setHorizontalAlignment(SwingConstants.CENTER);
        saveButton.setVerticalAlignment(SwingConstants.CENTER);
        saveButton.setFont(new Font("Trebuchet MS", Font.PLAIN, 28));

        saveButton.setBounds(20, 500, 150, 50);

        add(welcomeText);
        add(monthsTitle);
        add(monthsUndertitle);
        add(months);
        add(saveFeedback);
        add(announcement);
        add(saveButton);
        add(streamSlider);
        add(streamsTitle);
        add(streamsUndertitle);

        announcement.setBounds(183, 112, 660, 40);
        saveFeedback.setBounds(200, 157, 625, 40);
        welcomeText.setBounds(20, 10, 600, 36);
        months.setBounds(20, 270, 600, 55);

        monthsTitle.setBounds(20, 200, 650, 55);
        monthsUndertitle.setBounds(20, 220, 650, 55);

        streamsTitle.setBounds(20, 330, 650, 55);
        streamsUndertitle.setBounds(20, 350, 750, 55);
        streamSlider.setBounds(20, 400, 600, 55);

        streamsTitle.setForeground(Color.WHITE);
        streamsTitle.setHorizontalAlignment(SwingConstants.LEFT);
        streamsTitle.setVerticalAlignment(SwingConstants.CENTER);
        streamsTitle.setFont(new Font("Trebuchet MS", Font.PLAIN, 24));

        streamsUndertitle.setForeground(Color.GRAY);
        streamsUndertitle.setHorizontalAlignment(SwingConstants.LEFT);
        streamsUndertitle.setVerticalAlignment(SwingConstants.CENTER);
        streamsUndertitle.setFont(new Font("Trebuchet MS", Font.ITALIC, 14));

        monthsTitle.setForeground(Color.WHITE);
        monthsTitle.setHorizontalAlignment(SwingConstants.LEFT);
        monthsTitle.setVerticalAlignment(SwingConstants.CENTER);
        monthsTitle.setFont(new Font("Trebuchet MS", Font.PLAIN, 24));

        monthsUndertitle.setForeground(Color.GRAY);
        monthsUndertitle.setHorizontalAlignment(SwingConstants.LEFT);
        monthsUndertitle.setVerticalAlignment(SwingConstants.CENTER);
        monthsUndertitle.setFont(new Font("Trebuchet MS", Font.ITALIC, 14));

        welcomeText.setForeground(Color.WHITE);
        welcomeText.setHorizontalAlignment(SwingConstants.LEFT);
        welcomeText.setVerticalAlignment(SwingConstants.CENTER);
        welcomeText.setFont(new Font("Trebuchet MS", Font.PLAIN, 36));
    }

}
