package de.bot.windows;

import de.bot.handler.AccountHandler;
import de.bot.handler.ImageIconHandler;
import de.bot.handler.UpdateHandler;
import de.bot.utils.Account;
import de.bot.utils.Announcement;
import de.bot.windows.modules.CustomScrollBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

public class Feedback extends JPanel {

    AccountHandler accountHandler = AccountHandler.getInstance();
    UpdateHandler updateHandler = UpdateHandler.getInstance();

    public Feedback() {
        Account account = accountHandler.getAccount();
        setPreferredSize(new Dimension(1040, 816));
        setLayout(null);

        Announcement an = updateHandler.getAnnouncement();
        JLabel announcement = new JLabel("<html><center>" + an.text + "</center></html>");
        JLabel saveFeedback = new JLabel("Das Feedback wurde erfolgreich gesendet. Vielen Dank!");

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

        JLabel title = new JLabel("Feedback");
        title.setBounds(20, 10, 600, 36);
        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(SwingConstants.LEFT);
        title.setVerticalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.PLAIN, 36));
        add(title);

        JLabel sendButton = new JLabel("Absenden");
        JLabel messageTitle = new JLabel("Nachricht");
        JLabel messageUndertitle = new JLabel("Dein Feedback an uns. Die Nachricht wird anonymisiert!");
        JTextArea messageArea = new JTextArea("");
        JScrollPane scrollPane = new JScrollPane(messageArea);
        JLabel background = new JLabel();

        background.setBackground(new Color(0x1A1A1A));
        background.setVisible(true);
        background.setOpaque(true);
        background.setBorder(BorderFactory.createLineBorder(new Color(0x333333), 1));

        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUI(new CustomScrollBar());
        scrollPane.getHorizontalScrollBar().setUI(new CustomScrollBar());
        scrollPane.getViewport().setBackground(Color.BLUE);
        scrollPane.getViewport().setOpaque(true);

        sendButton.setBackground(new Color(0x1E5E8B));
        sendButton.setOpaque(true);
        sendButton.setForeground(Color.WHITE);
        sendButton.setHorizontalAlignment(SwingConstants.CENTER);
        sendButton.setVerticalAlignment(SwingConstants.CENTER);
        sendButton.setFont(new Font("Arial", Font.PLAIN, 28));

        saveFeedback.setBackground(new Color(0x05A489));
        saveFeedback.setBounds(200, 120, 625, 40);
        saveFeedback.setForeground(Color.WHITE);
        saveFeedback.setOpaque(true);
        saveFeedback.setVisible(false);
        saveFeedback.setHorizontalAlignment(JLabel.CENTER);
        saveFeedback.setHorizontalTextPosition(JLabel.RIGHT);
        saveFeedback.setFont(new Font("Arial", Font.PLAIN, 14));

        sendButton.setBounds(20, 750, 1000, 50);

        sendButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                sendButton.setBackground(new Color(0x236C9F));
                sendButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                sendButton.setBackground(new Color(0x1E5E8B));
                sendButton.setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                saveFeedback.setVisible(false);
                if (!account.canSendNewFeedback()) {
                    saveFeedback.setBackground(new Color(0xFFD03F3F));
                    saveFeedback.setText("Bitte warte einige Zeit, bis du ein weiteres Feedback absenden kannst.");
                    saveFeedback.setVisible(true);
                    return;
                }
                try {
                    Socket socket = new Socket("45.93.249.139", 3459);
                    OutputStream outputStream = socket.getOutputStream();
                    PrintStream printStream = new PrintStream(outputStream);
                    printStream.println("SAVE");
                    printStream.println("Feedback");
                    printStream.println(account.getName());
                    printStream.println(messageArea.getText().replace("\n", " "));

                    saveFeedback.setText("Das Feedback wurde erfolgreich gesendet. Vielen Dank!");
                    saveFeedback.setBackground(new Color(0x05A489));
                    saveFeedback.setVisible(true);
                    account.updateLastFeedback();
                } catch (Exception ignored) {
                }
            }
        });

        messageTitle.setForeground(Color.WHITE);
        messageTitle.setHorizontalAlignment(SwingConstants.LEFT);
        messageTitle.setVerticalAlignment(SwingConstants.CENTER);
        messageTitle.setFont(new Font("Arial", Font.PLAIN, 18));

        messageUndertitle.setForeground(Color.GRAY);
        messageUndertitle.setHorizontalAlignment(SwingConstants.LEFT);
        messageUndertitle.setVerticalAlignment(SwingConstants.CENTER);
        messageUndertitle.setFont(new Font("Arial", Font.ITALIC, 12));

        messageArea.setBackground(new Color(0x262626));
        messageArea.setForeground(Color.WHITE);
        messageArea.setFont(new Font("Arial", Font.PLAIN, 18));

        add(sendButton);
        add(messageTitle);
        add(messageUndertitle);
        add(scrollPane);
        add(saveFeedback);
        add(background);

        background.setBounds(93, 175, 840, 485);
        messageTitle.setBounds(113, 195, 650, 20);
        messageUndertitle.setBounds(113, 215, 650, 14);
        scrollPane.setBounds(113, 240, 800, 400);
        sendButton.setBounds(20, 750, 1000, 50);
    }
}
