package de.bot.windows.admindashboard.usermanagement;

import de.bot.handler.AccountHandler;
import de.bot.handler.ImageIconHandler;
import de.bot.handler.UpdateHandler;
import de.bot.handler.WindowHandler;
import de.bot.utils.Account;
import de.bot.utils.Announcement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

public class AdminDash_RegisterSettings extends JPanel {

    UpdateHandler updateHandler = UpdateHandler.getInstance();
    AccountHandler accountHandler = AccountHandler.getInstance();
    WindowHandler windowHandler = new WindowHandler();

    public AdminDash_RegisterSettings() {
        Account account = accountHandler.getAccount();
        setPreferredSize(new Dimension(1040, 816));
        setLayout(null);

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
        announcement.setBounds(183, 75, 660, 40);
        add(announcement);

        JLabel titleLabel = new JLabel("Registriereinstellungen");
        titleLabel.setBounds(20, 10, 600, 36);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
        titleLabel.setVerticalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 36));
        add(titleLabel);

        JCheckBox enableRegister = new JCheckBox("Registrierungen aktivieren");
        JLabel saveButton = new JLabel("Speichern");
        JLabel saveFeedback = new JLabel("Die Einstellungen wurden erfolgreich gespeichert.");
        JTextField background = new JTextField();
        JLabel backButton = new JLabel("<html>\uD83D\uDEAA</html>");

        enableRegister.setForeground(Color.WHITE);
        enableRegister.setOpaque(false);
        enableRegister.setVisible(true);
        enableRegister.setHorizontalAlignment(JLabel.CENTER);
        enableRegister.setHorizontalTextPosition(JLabel.RIGHT);
        enableRegister.setFont(new Font("Arial", Font.PLAIN, 18));
        enableRegister.setSelected(updateHandler.getToolData().isRegisterEnabled());

        saveFeedback.setBackground(new Color(0x05A489));
        saveFeedback.setBounds(200, 120, 625, 40);
        saveFeedback.setForeground(Color.WHITE);
        saveFeedback.setOpaque(true);
        saveFeedback.setVisible(false);
        saveFeedback.setHorizontalAlignment(JLabel.CENTER);
        saveFeedback.setHorizontalTextPosition(JLabel.RIGHT);
        saveFeedback.setFont(new Font("Arial", Font.PLAIN, 14));

        saveButton.setBackground(new Color(0x1E5E8B));
        saveButton.setOpaque(true);
        saveButton.setForeground(Color.WHITE);
        saveButton.setHorizontalAlignment(SwingConstants.CENTER);
        saveButton.setVerticalAlignment(SwingConstants.CENTER);
        saveButton.setFont(new Font("Arial", Font.PLAIN, 28));

        background.setBackground(new Color(0x1A1A1A));
        background.setVisible(true);
        background.setOpaque(true);
        background.setBorder(BorderFactory.createLineBorder(new Color(0x333333), 1));

        backButton.setBackground(new Color(0xD03F3F));
        backButton.setOpaque(true);
        backButton.setForeground(Color.WHITE);
        backButton.setHorizontalAlignment(SwingConstants.CENTER);
        backButton.setVerticalAlignment(SwingConstants.CENTER);
        backButton.setFont(new Font("Arial", Font.PLAIN, 28));


        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                backButton.setBackground(new Color(0xE04F4F));
                backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                backButton.setToolTipText("Zurück");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                backButton.setBackground(new Color(0xD03F3F));
                backButton.setCursor(Cursor.getDefaultCursor());
                backButton.setToolTipText(null);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                windowHandler.openWindow(WindowHandler.WindowType.ADMINDASH_USERMANAGEMENT);
            }
        });


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
                saveFeedback.setVisible(false);

                try {
                    Socket socket = new Socket("45.93.249.139", 3459);
                    OutputStream outputStream = socket.getOutputStream();
                    PrintStream printStream = new PrintStream(outputStream);

                    printStream.println("EDIT_REGISTERSETTINGS");
                    printStream.println(enableRegister.isSelected());

                    updateHandler.getToolData().setRegisterEnabled(enableRegister.isSelected());

                    saveFeedback.setBackground(new Color(0x05A489));
                    saveFeedback.setVisible(true);
                } catch (Exception ignored) {
                }
            }
        });

        add(saveFeedback);
        add(saveButton);
        add(enableRegister);
        add(backButton);
        add(background);

        enableRegister.setBounds(40, 170, 400, 35);
        saveButton.setBounds(20, 750, 885, 50);
        backButton.setBounds(925, 750, 90, 50);
        saveFeedback.setBounds(200, 120, 625, 40);
        background.setBounds(20, 150, 440, 75);
    }
}
