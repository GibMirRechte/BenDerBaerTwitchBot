package de.bot.windows;

import de.bot.handler.AccountHandler;
import de.bot.handler.ImageIconHandler;
import de.bot.handler.UpdateHandler;
import de.bot.utils.Account;
import de.bot.utils.Announcement;
import de.bot.utils.CustomCommand;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CustomCommands extends JPanel {

    AccountHandler accountHandler = AccountHandler.getInstance();
    UpdateHandler updateHandler = UpdateHandler.getInstance();

    public CustomCommands() {
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

        JLabel title = new JLabel("Befehle Einstellungen");
        title.setBounds(20, 10, 600, 36);
        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(SwingConstants.LEFT);
        title.setVerticalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.PLAIN, 36));
        add(title);

        JLabel saveButton = new JLabel("Speichern");

        for (int i = 0; i < 10; i++) {
            List<JTextField> list = getField(i, false, account);

            JTextField suffix = list.get(0);
            JTextField message = list.get(1);
        }

        JLabel premiumBorder = new JLabel();
        JLabel background = new JLabel();
        StringBuilder stringBuilder = new StringBuilder();

        HashMap<Integer, CustomCommand> customCommands = account.getCustomCommandSettings().getCommands();

        Border border = BorderFactory.createEmptyBorder(0, 20, 0, 20);

        background.setBackground(new Color(0x1A1A1A));
        background.setVisible(true);
        background.setOpaque(true);
        background.setBorder(BorderFactory.createLineBorder(new Color(0x333333), 1));

        premiumBorder.setBorder(BorderFactory.createLineBorder(new Color(0xFEC23F), 1));
        premiumBorder.setText("  Premium Bereich");
        premiumBorder.setFont(new Font("Arial", Font.BOLD, 18));
        premiumBorder.setForeground(Color.WHITE);
        premiumBorder.setVisible(true);
        premiumBorder.setVerticalAlignment(SwingConstants.TOP);

        for (String msg : account.getAutoMessagesSettings().getMessages()) {
            if (stringBuilder.isEmpty()) {
                stringBuilder.append(msg);
            } else {
                stringBuilder.append("\n").append(msg);
            }
        }

        JLabel saveFeedback = new JLabel("Die Einstellungen wurden erfolgreich gespeichert.");

        saveButton.setBackground(new Color(0x1E5E8B));
        saveButton.setOpaque(true);
        saveButton.setForeground(Color.WHITE);
        saveButton.setHorizontalAlignment(SwingConstants.CENTER);
        saveButton.setVerticalAlignment(SwingConstants.CENTER);
        saveButton.setFont(new Font("Arial", Font.PLAIN, 28));

        saveFeedback.setBackground(new Color(0x05A489));
        saveFeedback.setBounds(200, 120, 625, 40);
        saveFeedback.setForeground(Color.WHITE);
        saveFeedback.setOpaque(true);
        saveFeedback.setVisible(false);
        saveFeedback.setHorizontalAlignment(JLabel.CENTER);
        saveFeedback.setHorizontalTextPosition(JLabel.RIGHT);
        saveFeedback.setFont(new Font("Arial", Font.PLAIN, 14));

        saveButton.setBounds(20, 750, 1000, 50);

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
/*
            @Override
            public void mouseReleased(MouseEvent e) {
                saveFeedback.setVisible(false);
                try {
                    String commandString = "";
                    if(!suffixOne.getText().replace("!command", "").isBlank()){
                        if(messageOne.getText().replace("Nachricht", "").isBlank()){
                            saveFeedback.setBackground(new Color(0xFFD03F3F));
                            saveFeedback.setText("Bitte gebe eine gültige Nachricht bei jedem Suffix an!");
                            saveFeedback.setVisible(true);
                            return;
                        }

                        commandString += suffixOne.getText() + "-&&&-" + messageOne.getText();
                    }

                    if(!suffixTwo.getText().replace("!command", "").isBlank()){
                        if(messageTwo.getText().replace("Nachricht", "").isBlank()){
                            saveFeedback.setBackground(new Color(0xFFD03F3F));
                            saveFeedback.setText("Bitte gebe eine gültige Nachricht bei jedem Suffix an!");
                            saveFeedback.setVisible(true);
                            return;
                        }

                        if(!commandString.isBlank()) commandString += "-&&&-";
                        commandString +=suffixTwo.getText() + "-&&&-" + messageTwo.getText();
                    }

                    if(!suffixThree.getText().replace("!command", "").isBlank()){
                        if(messageThree.getText().replace("Nachricht", "").isBlank()){
                            saveFeedback.setBackground(new Color(0xFFD03F3F));
                            saveFeedback.setText("Bitte gebe eine gültige Nachricht bei jedem Suffix an!");
                            saveFeedback.setVisible(true);
                            return;
                        }

                        if(!commandString.isBlank()) commandString += "-&&&-";
                        commandString += suffixThree.getText() + "-&&&-" + messageThree.getText();
                    }

                    if(!suffixFour.getText().replace("!command", "").isBlank()){
                        if(messageFour.getText().replace("Nachricht", "").isBlank()){
                            saveFeedback.setBackground(new Color(0xFFD03F3F));
                            saveFeedback.setText("Bitte gebe eine gültige Nachricht bei jedem Suffix an!");
                            saveFeedback.setVisible(true);
                            return;
                        }

                        if(!commandString.isBlank()) commandString += "-&&&-";
                        commandString += suffixFour.getText() + "-&&&-" + messageFour.getText();
                    }

                    if(!suffixFive.getText().replace("!command", "").isBlank()){
                        if(messageFive.getText().replace("Nachricht", "").isBlank()){
                            saveFeedback.setBackground(new Color(0xFFD03F3F));
                            saveFeedback.setText("Bitte gebe eine gültige Nachricht bei jedem Suffix an!");
                            saveFeedback.setVisible(true);
                            return;
                        }

                        if(!commandString.isBlank()) commandString += "-&&&-";
                        commandString += suffixFive.getText() + "-&&&-" + messageFive.getText();
                    }

                    /*Socket socket = new Socket("45.93.249.139", 3459);
                    OutputStream outputStream = socket.getOutputStream();
                    PrintStream printStream = new PrintStream(outputStream);
                    printStream.println("SAVE");
                    printStream.println("CustomCommands");
                    printStream.println(account.getName());
                    printStream.println(commandString);

                    account.getCustomCommandSettings().updateCommandString(commandString);
                    saveFeedback.setText("Die Einstellungen wurden erfolgreich gespeichert.");
                    saveFeedback.setBackground(new Color(0x05A489));
                    saveFeedback.setVisible(true);
                } catch (Exception ignored) {
                }
            }*/
        });

        premiumBorder.setBounds(30, 360, 830, 150);
        background.setBounds(20, 150, 850, 370);

        add(saveButton);
        add(saveFeedback);
        add(premiumBorder);

        add(background);
    }

    public List<JTextField> getField(int index, boolean premium, Account account) {
        List<JTextField> list = new ArrayList<>();
        HashMap<Integer, CustomCommand> customCommands = account.getCustomCommandSettings().getCommands();
        ;

        JTextField messageField = new JTextField();
        JTextField suffixField = new JTextField();

        Border border = BorderFactory.createEmptyBorder(0, 20, 0, 20);

        suffixField.setBorder(border);
        suffixField.setVisible(true);
        suffixField.setText("!command");
        suffixField.setForeground(Color.GRAY);
        suffixField.setBackground(new Color(0x262626));
        suffixField.setBorder(border);
        suffixField.setFont(new Font("Arial", Font.BOLD, 16));

        messageField.setBorder(border);
        messageField.setVisible(true);
        messageField.setBackground(new Color(0x262626));
        messageField.setForeground(Color.GRAY);
        messageField.setBorder(border);
        messageField.setText("Nachricht");
        messageField.setFont(new Font("Arial", Font.BOLD, 16));

        ((AbstractDocument) suffixField.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                int currentLength = fb.getDocument().getLength();
                int maxLength = 16;

                if (currentLength - length + text.length() <= maxLength) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });

        ((AbstractDocument) messageField.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                int currentLength = fb.getDocument().getLength();
                int maxLength = 200;

                if (currentLength - length + text.length() <= maxLength) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });

        if (premium && !account.getAccountRank().isVip()) {
            suffixField.setEnabled(false);
            messageField.setEnabled(false);
        } else {
            if (customCommands.containsKey(index)) {
                suffixField.setText(customCommands.get(index).getSuffix());
                suffixField.setForeground(Color.WHITE);
                messageField.setText(customCommands.get(index).getMessage());
                messageField.setForeground(Color.WHITE);
            }
        }

        suffixField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (suffixField.getText().equals("!command")) {
                    suffixField.setText("");
                    suffixField.setForeground(Color.WHITE);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (suffixField.getText().isEmpty()) {
                    suffixField.setText("!command");
                    suffixField.setForeground(Color.GRAY);
                }
            }
        });

        messageField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (messageField.getText().equals("Nachricht")) {
                    messageField.setText("");
                    messageField.setForeground(Color.WHITE);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (messageField.getText().isEmpty()) {
                    messageField.setText("Nachricht");
                    messageField.setForeground(Color.GRAY);
                }
            }
        });

        int y = 170 + (index * 60);

        suffixField.setBounds(40, y, 200, 50);
        messageField.setBounds((int) (suffixField.getBounds().getX() + 10 + suffixField.getBounds().getWidth()), y, 600, 50);

        list.add(suffixField);
        list.add(messageField);
        return list;
    }
}