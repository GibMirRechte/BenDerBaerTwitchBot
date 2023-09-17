package de.bot.windows;

import de.bot.elements.RoundedJTextField;
import de.bot.handler.AccountHandler;
import de.bot.handler.ImageIconHandler;
import de.bot.handler.UpdateHandler;
import de.bot.utils.Announcement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

public class AdminDashboard extends JPanel {

    AccountHandler accountHandler = AccountHandler.getInstance();
    UpdateHandler updateHandler = UpdateHandler.getInstance();

    public AdminDashboard() {
        setPreferredSize(new Dimension(1025, 720));
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
        announcement.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
        announcement.setBounds(183, 75, 660, 40);
        add(announcement);

        JLabel titleLabel = new JLabel("Admin-Dashboard");
        titleLabel.setBounds(20, 10, 600, 36);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
        titleLabel.setVerticalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 36));
        add(titleLabel);

        String[] announcementIcons;

        if (an.typeID == 1) {
            announcementIcons = new String[]{"Warnung", "Info"};
        } else {
            announcementIcons = new String[]{"Info", "Warnung"};
        }

        RoundedJTextField announcementText = new RoundedJTextField(10, new Color(0x272727), false, 20, 40);
        JComboBox announcementIcon = new JComboBox(announcementIcons);
        JLabel announcementColor = new JLabel("Farbe auswählen");
        JLabel saveButton = new JLabel("Speichern");
        JLabel announcementTitle = new JLabel("Ankündigung");
        JCheckBox canUseTool = new JCheckBox("Tool nutzbar");
        JCheckBox isActive = new JCheckBox("Aktiviert");

        canUseTool.setForeground(Color.WHITE);
        canUseTool.setOpaque(false);
        canUseTool.setVisible(true);
        canUseTool.setSelected(an.canUseTool);
        canUseTool.setHorizontalAlignment(JLabel.CENTER);
        canUseTool.setHorizontalTextPosition(JLabel.RIGHT);
        canUseTool.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
        canUseTool.setBounds(320, 325, 300, 35);

        isActive.setForeground(Color.WHITE);
        isActive.setOpaque(false);
        isActive.setVisible(true);
        isActive.setSelected(an.isActive);
        isActive.setHorizontalAlignment(JLabel.CENTER);
        isActive.setHorizontalTextPosition(JLabel.RIGHT);
        isActive.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
        isActive.setBounds(20, 325, 250, 35);

        announcementTitle.setForeground(Color.WHITE);
        announcementTitle.setHorizontalAlignment(SwingConstants.LEFT);
        announcementTitle.setFont(new Font("Trebuchet MS", Font.PLAIN, 25));
        announcementTitle.setBounds(20, 190, 250, 35);

        announcementText.setVisible(true);
        announcementText.setBackground(new Color(0x464646));
        announcementText.setForeground(Color.WHITE);
        announcementText.setHorizontalAlignment(JLabel.CENTER);
        announcementText.setText(an.text);
        announcementText.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
        announcementText.setBounds(20, 225, 600, 50);

        announcementIcon.setBounds(20, 285, 250, 35);
        announcementIcon.setBackground(new Color(0x464646));
        announcementIcon.setFont(new Font("Trebuchet MS", Font.BOLD, 16));

        announcementColor.setBackground(Color.decode(an.color));
        announcementColor.setOpaque(true);
        announcementColor.setForeground(Color.WHITE);
        announcementColor.setHorizontalAlignment(SwingConstants.CENTER);
        announcementColor.setVerticalAlignment(SwingConstants.CENTER);
        announcementColor.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
        announcementColor.setBounds(320, 285, 300, 35);

        announcementColor.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                announcementColor.setText("<html><u>Farbe auswählen</u></html>");
                announcementColor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                announcementColor.setText("Farbe auswählen");
                announcementColor.setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                Color selectedColor = JColorChooser.showDialog(null, "Farbe auswählen", Color.WHITE);
                if (selectedColor != null) {
                    announcementColor.setBackground(selectedColor);
                }
            }
        });

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
                new Thread(() -> {
                    try {
                        int typeID;
                        if (((String) announcementIcon.getSelectedItem()).equalsIgnoreCase("Warnung")) {
                            typeID = 1;
                        } else {
                            typeID = 2;
                        }
                        Announcement newAnnouncement = new Announcement(announcementText.getText(), typeID, announcementColor.getBackground().getRGB() + "", canUseTool.isSelected(), isActive.isSelected());

                        Socket socket = new Socket("45.93.249.139", 3459);
                        OutputStream outputStream = socket.getOutputStream();
                        PrintStream printStream = new PrintStream(outputStream);
                        printStream.println("SAVE");
                        printStream.println("Announcement");
                        printStream.println(newAnnouncement.text);
                        printStream.println(newAnnouncement.color);
                        printStream.println(newAnnouncement.typeID);
                        printStream.println(newAnnouncement.canUseTool);
                        printStream.println(newAnnouncement.isActive);

                        updateHandler.updateAnnouncement(newAnnouncement);

                        announcement.setBackground(Color.decode(String.format("0x%06X", announcementColor.getBackground().getRGB() & 0x00FFFFFF)));
                        announcement.setForeground(Color.WHITE);
                        announcement.setText(newAnnouncement.text);
                        announcement.setVisible(newAnnouncement.isActive);
                        announcement.setHorizontalAlignment(JLabel.CENTER);
                        if (newAnnouncement.typeID == 1) {
                            announcement.setIcon(ImageIconHandler.imageType.WARN_ICON.imageIcon);
                        } else {
                            announcement.setIcon(ImageIconHandler.imageType.INFO_ICON.imageIcon);
                        }
                    } catch (Exception ignored) {

                    }
                }).start();
            }
        });

        saveButton.setBackground(new Color(0x86BECC));
        saveButton.setOpaque(true);
        saveButton.setForeground(Color.WHITE);
        saveButton.setHorizontalAlignment(SwingConstants.CENTER);
        saveButton.setVerticalAlignment(SwingConstants.CENTER);
        saveButton.setFont(new Font("Trebuchet MS", Font.PLAIN, 28));

        saveButton.setBounds(20, 650, 985, 50);

        add(announcementText);
        add(saveButton);
        add(announcementTitle);
        add(announcementIcon);
        add(isActive);
        add(canUseTool);
        add(announcementColor);

    }
}