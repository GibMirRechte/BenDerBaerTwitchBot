package de.bot.windows.admindashboard;

import de.bot.handler.ImageIconHandler;
import de.bot.handler.UpdateHandler;
import de.bot.handler.WindowHandler;
import de.bot.utils.Announcement;
import de.bot.windows.modules.ComboBoxRenderer;
import de.bot.windows.modules.CustomComboBox;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

public class AdminDash_Start extends JPanel {

    UpdateHandler updateHandler = UpdateHandler.getInstance();
    WindowHandler windowHandler = WindowHandler.getInstance();

    public AdminDash_Start() {
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

        JLabel titleLabel = new JLabel("Admin-Dashboard");
        titleLabel.setBounds(20, 10, 600, 36);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
        titleLabel.setVerticalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 36));
        add(titleLabel);


        String[] announcementIcons;

        if (an.typeID == 1) {
            announcementIcons = new String[]{"Warnung", "Info"};
        } else {
            announcementIcons = new String[]{"Info", "Warnung"};
        }

        JTextField announcementText = new JTextField();
        JComboBox announcementIcon = new JComboBox(announcementIcons);
        JLabel announcementColor = new JLabel("Farbe auswählen");
        JLabel saveButton = new JLabel("Speichern");
        JLabel announcementTitle = new JLabel("Ankündigung");
        JCheckBox canUseTool = new JCheckBox("Tool nutzbar");
        JCheckBox isActive = new JCheckBox("Aktiviert");
        JLabel announcementBackground = new JLabel();
        JLabel maintenanceBackground = new JLabel();
        JLabel maintenanceTitle = new JLabel("Wartungen");
        JLabel maintenanceAutoVIP = new JLabel("AutoVIP");
        JLabel maintenanceAutoShout = new JLabel("AutoShout");
        JLabel maintenanceAutoMessages = new JLabel("AutoMessages");
        JLabel maintenanceCommands = new JLabel("Befehle");
        JLabel userListBackground = new JLabel();
        JLabel userListButton = new JLabel("Benutzerverwaltung");
        JLabel newsBackground = new JLabel();
        JLabel newsButton = new JLabel("News verwalten");

        maintenanceCommands.setBackground(new Color(0x464646));
        maintenanceCommands.setText("Befehle (Nicht verfügbar)");
        maintenanceCommands.setVisible(true);
        maintenanceCommands.setOpaque(true);
        maintenanceCommands.setForeground(Color.WHITE);
        maintenanceCommands.setHorizontalAlignment(JLabel.CENTER);
        maintenanceCommands.setHorizontalTextPosition(JLabel.LEFT);
        maintenanceCommands.setFont(new Font("Arial", Font.BOLD, 16));

        maintenanceAutoMessages.setBackground(new Color(0x464646));
        maintenanceAutoMessages.setText("AutoMessages (Nicht verfügbar)");
        maintenanceAutoMessages.setVisible(true);
        maintenanceAutoMessages.setOpaque(true);
        maintenanceAutoMessages.setForeground(Color.WHITE);
        maintenanceAutoMessages.setHorizontalAlignment(JLabel.CENTER);
        maintenanceAutoMessages.setHorizontalTextPosition(JLabel.LEFT);
        maintenanceAutoMessages.setFont(new Font("Arial", Font.BOLD, 16));

        if (updateHandler.getToolData().isMaintenance_autovip()) {
            maintenanceAutoVIP.setBackground(new Color(0xAD4848));
            maintenanceAutoVIP.setText("AutoVIP (Gesperrt)");
        } else {
            maintenanceAutoVIP.setBackground(new Color(0x4C9F6E));
            maintenanceAutoVIP.setText("AutoVIP (Verfügbar)");
        }
        maintenanceAutoVIP.setVisible(true);
        maintenanceAutoVIP.setOpaque(true);
        maintenanceAutoVIP.setForeground(Color.WHITE);
        maintenanceAutoVIP.setHorizontalAlignment(JLabel.CENTER);
        maintenanceAutoVIP.setHorizontalTextPosition(JLabel.LEFT);
        maintenanceAutoVIP.setFont(new Font("Arial", Font.BOLD, 16));

        if (updateHandler.getToolData().isMaintenance_autoshout()) {
            maintenanceAutoShout.setBackground(new Color(0xAD4848));
            maintenanceAutoShout.setText("AutoShout (Gesperrt)");
        } else {
            maintenanceAutoShout.setBackground(new Color(0x4C9F6E));
            maintenanceAutoShout.setText("AutoShout (Verfügbar)");
        }
        maintenanceAutoShout.setVisible(true);
        maintenanceAutoShout.setOpaque(true);
        maintenanceAutoShout.setForeground(Color.WHITE);
        maintenanceAutoShout.setHorizontalAlignment(JLabel.CENTER);
        maintenanceAutoShout.setHorizontalTextPosition(JLabel.LEFT);
        maintenanceAutoShout.setFont(new Font("Arial", Font.BOLD, 16));

        newsBackground.setBackground(new Color(0x1A1A1A));
        newsBackground.setVisible(true);
        newsBackground.setOpaque(true);
        newsBackground.setBorder(BorderFactory.createLineBorder(new Color(0x333333), 1));

        userListBackground.setBackground(new Color(0x1A1A1A));
        userListBackground.setVisible(true);
        userListBackground.setOpaque(true);
        userListBackground.setBorder(BorderFactory.createLineBorder(new Color(0x333333), 1));

        maintenanceBackground.setBackground(new Color(0x1A1A1A));
        maintenanceBackground.setVisible(true);
        maintenanceBackground.setOpaque(true);
        maintenanceBackground.setBorder(BorderFactory.createLineBorder(new Color(0x333333), 1));

        announcementBackground.setBackground(new Color(0x1A1A1A));
        announcementBackground.setVisible(true);
        announcementBackground.setOpaque(true);
        announcementBackground.setBorder(BorderFactory.createLineBorder(new Color(0x333333), 1));

        canUseTool.setForeground(Color.WHITE);
        canUseTool.setOpaque(false);
        canUseTool.setVisible(true);
        canUseTool.setSelected(an.canUseTool);
        canUseTool.setHorizontalAlignment(JLabel.CENTER);
        canUseTool.setHorizontalTextPosition(JLabel.RIGHT);
        canUseTool.setFont(new Font("Arial", Font.BOLD, 16));

        isActive.setForeground(Color.WHITE);
        isActive.setOpaque(false);
        isActive.setVisible(true);
        isActive.setSelected(an.isActive);
        isActive.setHorizontalAlignment(JLabel.CENTER);
        isActive.setHorizontalTextPosition(JLabel.RIGHT);
        isActive.setFont(new Font("Arial", Font.BOLD, 16));

        announcementTitle.setForeground(Color.WHITE);
        announcementTitle.setHorizontalAlignment(SwingConstants.LEFT);
        announcementTitle.setFont(new Font("Arial", Font.PLAIN, 25));

        maintenanceTitle.setForeground(Color.WHITE);
        maintenanceTitle.setHorizontalAlignment(SwingConstants.LEFT);
        maintenanceTitle.setFont(new Font("Arial", Font.PLAIN, 25));

        Border border = BorderFactory.createEmptyBorder(0, 20, 0, 20);

        announcementText.setBorder(border);
        announcementText.setVisible(true);
        announcementText.setBackground(new Color(0x262626));
        announcementText.setForeground(Color.WHITE);
        announcementText.setHorizontalAlignment(JLabel.CENTER);
        announcementText.setText(an.text);
        announcementText.setFont(new Font("Arial", Font.BOLD, 16));

        announcementIcon.setBackground(new Color(0x262626));
        announcementIcon.setFont(new Font("Arial", Font.BOLD, 16));
        announcementIcon.setUI(new CustomComboBox());
        announcementIcon.setRenderer(new ComboBoxRenderer());
        announcementIcon.setFocusable(false);
        announcementIcon.setOpaque(false);
        announcementIcon.setForeground(Color.WHITE);

        announcementColor.setBackground(Color.decode(an.color));
        announcementColor.setOpaque(true);
        announcementColor.setForeground(Color.WHITE);
        announcementColor.setHorizontalAlignment(SwingConstants.CENTER);
        announcementColor.setVerticalAlignment(SwingConstants.CENTER);
        announcementColor.setFont(new Font("Arial", Font.PLAIN, 16));

        maintenanceAutoVIP.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (updateHandler.getToolData().isMaintenance_autovip()) {
                    maintenanceAutoVIP.setBackground(new Color(0xC05050));
                } else {
                    maintenanceAutoVIP.setBackground(new Color(0x53AD78));
                }
                maintenanceAutoVIP.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (updateHandler.getToolData().isMaintenance_autovip()) {
                    maintenanceAutoVIP.setBackground(new Color(0xAD4848));
                } else {
                    maintenanceAutoVIP.setBackground(new Color(0x4C9F6E));
                }
                maintenanceAutoVIP.setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (updateHandler.getToolData().isMaintenance_autovip()) {
                    updateHandler.getToolData().updateMaintenance(updateHandler.getToolData().isMaintenance_autoshout(), false);
                    maintenanceAutoVIP.setText("AutoVIP (Verfügbar)");
                    maintenanceAutoVIP.setBackground(new Color(0x4C9F6E));
                } else {
                    updateHandler.getToolData().updateMaintenance(updateHandler.getToolData().isMaintenance_autoshout(), true);
                    maintenanceAutoVIP.setText("AutoVIP (Gesperrt)");
                    maintenanceAutoVIP.setBackground(new Color(0xAD4848));
                }
            }
        });

        maintenanceAutoShout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (updateHandler.getToolData().isMaintenance_autoshout()) {
                    maintenanceAutoShout.setBackground(new Color(0xC05050));
                } else {
                    maintenanceAutoShout.setBackground(new Color(0x53AD78));
                }
                maintenanceAutoShout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (updateHandler.getToolData().isMaintenance_autoshout()) {
                    maintenanceAutoShout.setBackground(new Color(0xAD4848));
                } else {
                    maintenanceAutoShout.setBackground(new Color(0x4C9F6E));
                }
                maintenanceAutoShout.setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (updateHandler.getToolData().isMaintenance_autoshout()) {
                    updateHandler.getToolData().updateMaintenance(false, updateHandler.getToolData().isMaintenance_autovip());
                    maintenanceAutoShout.setText("AutoShout (Verfügbar)");
                    maintenanceAutoShout.setBackground(new Color(0x4C9F6E));
                } else {
                    updateHandler.getToolData().updateMaintenance(true, updateHandler.getToolData().isMaintenance_autovip());
                    maintenanceAutoShout.setText("AutoShout (Gesperrt)");
                    maintenanceAutoShout.setBackground(new Color(0xAD4848));
                }
            }
        });

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

        newsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                newsButton.setBackground(new Color(0x236C9F));
                newsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                newsButton.setBackground(new Color(0x1E5E8B));
                newsButton.setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                windowHandler.openWindow(WindowHandler.WindowType.ADMINDASH_NEWSMANAGEMENT);
            }
        });

        userListButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                userListButton.setBackground(new Color(0x236C9F));
                userListButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                userListButton.setBackground(new Color(0x1E5E8B));
                userListButton.setCursor(Cursor.getDefaultCursor());
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
                        printStream.println("AdminSettings");
                        printStream.println(newAnnouncement.text);
                        printStream.println(newAnnouncement.color);
                        printStream.println(newAnnouncement.typeID);
                        printStream.println(newAnnouncement.canUseTool);
                        printStream.println(newAnnouncement.isActive);
                        printStream.println(updateHandler.getToolData().isMaintenance_autoshout());
                        printStream.println(updateHandler.getToolData().isMaintenance_autovip());

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

        newsButton.setBackground(new Color(0x1E5E8B));
        newsButton.setOpaque(true);
        newsButton.setForeground(Color.WHITE);
        newsButton.setHorizontalAlignment(SwingConstants.CENTER);
        newsButton.setVerticalAlignment(SwingConstants.CENTER);
        newsButton.setFont(new Font("Arial", Font.PLAIN, 16));

        userListButton.setBackground(new Color(0x1E5E8B));
        userListButton.setOpaque(true);
        userListButton.setForeground(Color.WHITE);
        userListButton.setHorizontalAlignment(SwingConstants.CENTER);
        userListButton.setVerticalAlignment(SwingConstants.CENTER);
        userListButton.setFont(new Font("Arial", Font.PLAIN, 16));

        saveButton.setBackground(new Color(0x1E5E8B));
        saveButton.setOpaque(true);
        saveButton.setForeground(Color.WHITE);
        saveButton.setHorizontalAlignment(SwingConstants.CENTER);
        saveButton.setVerticalAlignment(SwingConstants.CENTER);
        saveButton.setFont(new Font("Arial", Font.PLAIN, 28));

        saveButton.setBounds(20, 750, 1000, 50);
        userListButton.setBounds(700, 200, 300, 35);
        userListBackground.setBounds(680, 180, 340, 75);
        announcementColor.setBounds(340, 285, 300, 35);
        announcementText.setBounds(40, 225, 600, 50);
        announcementBackground.setBounds(20, 180, 640, 185);
        maintenanceBackground.setBounds(20, 385, 640, 140);
        maintenanceTitle.setBounds(40, 395, 300, 35);
        maintenanceAutoShout.setBounds(40, 430, 295, 35);
        maintenanceAutoVIP.setBounds(40, 470, 295, 35);
        maintenanceAutoMessages.setBounds(340, 430, 295, 35);
        maintenanceCommands.setBounds(340, 470, 295, 35);
        isActive.setBounds(40, 325, 250, 35);
        canUseTool.setBounds(340, 325, 300, 35);
        announcementTitle.setBounds(40, 190, 250, 35);
        announcementIcon.setBounds(40, 285, 250, 35);
        newsBackground.setBounds(680, 275, 340, 75);
        newsButton.setBounds(700, 295, 300, 35);

        add(announcementText);
        add(saveButton);
        add(announcementTitle);
        add(announcementIcon);
        add(isActive);
        add(canUseTool);
        add(announcementColor);
        add(maintenanceTitle);
        add(maintenanceAutoShout);
        add(maintenanceAutoVIP);
        add(maintenanceCommands);
        add(maintenanceAutoMessages);
        add(userListButton);
        add(newsButton);

        add(announcementBackground);
        add(maintenanceBackground);
        add(userListBackground);
        add(newsBackground);

    }
}