package de.bot.windows;

import de.bot.handler.AccountHandler;
import de.bot.handler.ImageIconHandler;
import de.bot.handler.UpdateHandler;
import de.bot.utils.Announcement;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Changelog extends JPanel {

    UpdateHandler updateHandler = UpdateHandler.getInstance();
    AccountHandler accountHandler = AccountHandler.getInstance();

    public Changelog() {
        Announcement an = updateHandler.getAnnouncement();

        setPreferredSize(new Dimension(1025, 720));
        setLayout(null);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

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

        JLabel title = new JLabel("Changelog v0.2");
        JLabel autor = new JLabel("<html>Gepostet von <font color=red>GibMirRechte</font></html>");
        JLabel changelog = new JLabel();
        add(title);
        add(changelog);
        add(autor);

        String labelText = """            
                # AutoShout #
                - Ein schwerwiegender Fehler wurde entdeckt und zügig behoben. Deine Shoutouts laufen jetzt wieder reibungslos!
                """;

        JTextArea label = new JTextArea(labelText);
        label.setEditable(false);
        label.setLineWrap(true);
        label.setWrapStyleWord(true);
        label.setForeground(Color.WHITE);
        label.setBackground(new Color(0x272727));
        label.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));

        JScrollPane scrollPane = new JScrollPane(label);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        scrollPane.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
        scrollPane.getViewport().setBackground(Color.BLUE);
        scrollPane.getViewport().setOpaque(true);

        add(scrollPane);

        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setVerticalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Trebuchet MS", Font.PLAIN, 25));

        changelog.setForeground(Color.WHITE);
        changelog.setVerticalAlignment(SwingConstants.TOP);
        changelog.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));

        autor.setForeground(Color.GRAY);
        autor.setHorizontalAlignment(SwingConstants.CENTER);
        autor.setVerticalAlignment(SwingConstants.CENTER);
        autor.setFont(new Font("Trebuchet MS", Font.ITALIC, 15));
        autor.setIcon(new ImageIcon(accountHandler.getBadge(AccountHandler.AccountType.ADMIN)));
        autor.setHorizontalTextPosition(SwingConstants.LEADING);
        autor.setIconTextGap(5);

        Border border = BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        );
        title.setBorder(border);

        title.setBounds(338, 138, 350, 30);
        autor.setBounds(312, 173, 400, 20);
        scrollPane.setBounds(10, 225, 1000, 487);
    }

    class CustomScrollBarUI extends BasicScrollBarUI {
        @Override
        protected void configureScrollBarColors() {
            this.thumbColor = new Color(0xC4C4C4);
            this.thumbHighlightColor = new Color(0xC4C4C4);
            this.trackColor = new Color(0x464646);
            this.trackHighlightColor = new Color(0x464646);
        }

        @Override
        protected JButton createDecreaseButton(int orientation) {
            return createZeroButton();
        }

        @Override
        protected JButton createIncreaseButton(int orientation) {
            return createZeroButton();
        }

        private JButton createZeroButton() {
            JButton button = new JButton();
            Dimension zeroDim = new Dimension(0, 0);
            button.setPreferredSize(zeroDim);
            button.setMinimumSize(zeroDim);
            button.setMaximumSize(zeroDim);
            return button;
        }
    }

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(1336);
            Socket socket = serverSocket.accept();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            StringBuilder receivedTextBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                receivedTextBuilder.append(line).append("\n");
            }

            String receivedText = receivedTextBuilder.toString();
            System.out.println(receivedText);
            socket.close();
            serverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
