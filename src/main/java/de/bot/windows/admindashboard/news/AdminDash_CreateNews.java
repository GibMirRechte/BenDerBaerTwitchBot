package de.bot.windows.admindashboard.news;

import de.bot.handler.AccountHandler;
import de.bot.handler.ImageIconHandler;
import de.bot.handler.UpdateHandler;
import de.bot.handler.WindowHandler;
import de.bot.utils.Announcement;
import de.bot.windows.modules.CustomScrollBar;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

public class AdminDash_CreateNews extends JPanel {

    UpdateHandler updateHandler = UpdateHandler.getInstance();
    WindowHandler windowHandler = WindowHandler.getInstance();
    AccountHandler accountHandler = AccountHandler.getInstance();

    public AdminDash_CreateNews() {
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

        JLabel titleLabel = new JLabel("News erstellen");
        titleLabel.setBounds(20, 10, 600, 36);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
        titleLabel.setVerticalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 36));
        add(titleLabel);

        Border emptyBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);

        JTextField newsTitle = new JTextField();
        JTextArea newsText = new JTextArea();
        JTextField newsKey = new JTextField();
        JLabel saveButton = new JLabel("Speichern & Senden");
        JLabel saveFeedback = new JLabel("Die News wird gesendet...");
        JLabel background = new JLabel();

        saveButton.setBackground(new Color(0x1E5E8B));
        saveButton.setOpaque(true);
        saveButton.setForeground(Color.WHITE);
        saveButton.setHorizontalAlignment(SwingConstants.CENTER);
        saveButton.setVerticalAlignment(SwingConstants.CENTER);
        saveButton.setFont(new Font("Arial", Font.PLAIN, 28));

        saveFeedback.setBackground(new Color(0x05A489));
        saveFeedback.setForeground(Color.WHITE);
        saveFeedback.setOpaque(true);
        saveFeedback.setVisible(false);
        saveFeedback.setHorizontalAlignment(JLabel.CENTER);
        saveFeedback.setHorizontalTextPosition(JLabel.RIGHT);
        saveFeedback.setFont(new Font("Arial", Font.PLAIN, 14));

        newsKey.setBackground(new Color(0x262626));
        newsKey.setForeground(Color.GRAY);
        newsKey.setText("NewsID");
        newsKey.setBorder(emptyBorder);
        newsKey.setFont(new Font("Arial", Font.PLAIN, 20));

        newsTitle.setBackground(new Color(0x262626));
        newsTitle.setForeground(Color.GRAY);
        newsTitle.setText("Titel");
        newsTitle.setBorder(emptyBorder);
        newsTitle.setFont(new Font("Arial", Font.PLAIN, 20));

        newsText.setBackground(new Color(0x262626));
        newsText.setForeground(Color.WHITE);
        newsText.setBorder(emptyBorder);
        newsText.setFont(new Font("Arial", Font.PLAIN, 20));

        background.setBackground(new Color(0x1A1A1A));
        background.setVisible(true);
        background.setOpaque(true);
        background.setBorder(BorderFactory.createLineBorder(new Color(0x333333), 1));

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
                if (newsTitle.getText().isBlank() || newsKey.getText().isBlank() || newsText.getText().isBlank() ||
                        newsTitle.getText().equalsIgnoreCase("Titel") || newsKey.getText().equalsIgnoreCase("NewsID")) {
                    saveFeedback.setBackground(new Color(0xFFD03F3F));
                    saveFeedback.setText("Es müssen alle Felder ausgefüllt sein!");
                    saveFeedback.setVisible(true);
                    return;
                }

                try {
                    Socket socket = new Socket("45.93.249.139", 3459);
                    OutputStream outputStream = socket.getOutputStream();
                    PrintStream printStream = new PrintStream(outputStream);
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    printStream.println("News");
                    printStream.println(accountHandler.getAccount().getName());
                    printStream.println("POST");
                    printStream.println(newsKey.getText());
                    printStream.println(newsTitle.getText());
                    printStream.println(newsText.getText().replace("\n", "&&&-"));

                    String response = bufferedReader.readLine();
                    if (response.equalsIgnoreCase("CREATED")) {
                        saveFeedback.setBackground(new Color(0x05A489));
                        saveFeedback.setText("News erfolgreich gespeichert!");
                        saveFeedback.setVisible(true);
                    } else if (response.equalsIgnoreCase("NEWSKEY ALREADY IN USE")) {
                        saveFeedback.setBackground(new Color(0xFFD03F3F));
                        saveFeedback.setText("Die NewsID ist bereits vergeben!");
                        saveFeedback.setVisible(true);
                    }
                    socket.close();
                } catch (Exception exception) {
                }
            }
        });

        newsKey.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (newsKey.getText().equals("NewsID")) {
                    newsKey.setText("");
                    newsKey.setForeground(Color.WHITE);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (newsKey.getText().isEmpty()) {
                    newsKey.setText("NewsID");
                    newsKey.setForeground(Color.GRAY);
                }
            }
        });

        newsTitle.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (newsTitle.getText().equals("Titel")) {
                    newsTitle.setText("");
                    newsTitle.setForeground(Color.WHITE);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (newsTitle.getText().isEmpty()) {
                    newsTitle.setText("Titel");
                    newsTitle.setForeground(Color.GRAY);
                }
            }
        });
        JScrollPane scrollPane = new JScrollPane(newsText);

        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUI(new CustomScrollBar());
        scrollPane.getHorizontalScrollBar().setUI(new CustomScrollBar());
        scrollPane.getViewport().setBackground(Color.BLUE);
        scrollPane.getViewport().setOpaque(true);

        add(newsTitle);
        add(scrollPane);
        add(saveFeedback);
        add(saveButton);
        add(newsKey);
        add(background);

        ((AbstractDocument) newsKey.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                int currentLength = fb.getDocument().getLength();
                int maxLength = 15;

                if (currentLength - length + text.length() <= maxLength) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });

        newsText.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                SwingUtilities.invokeLater(() -> {
                    JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
                    verticalScrollBar.setValue(verticalScrollBar.getMaximum());
                });
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                // Nicht benötigt, aber muss implementiert werden
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Nicht benötigt, aber muss implementiert werden
            }
        });

        newsTitle.setBounds(40, 200, 680, 50);
        saveButton.setBounds(280, 740, 450, 40);
        newsKey.setBounds(750, 200, 250, 50);
        scrollPane.setBounds(40, 270, 960, 450);
        saveFeedback.setBounds(200, 120, 625, 40);
        background.setBounds(20, 180, 1000, 620);
    }
}