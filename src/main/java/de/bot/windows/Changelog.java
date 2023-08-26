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

        announcement.setBounds(183, 112, 660, 40);
        add(announcement);

        JLabel title = new JLabel("Changelog v0.2");
        JLabel autor = new JLabel("<html>Gespostet von <font color=red>GibMirRechte</font></html>");
        JLabel changelog = new JLabel();
        add(title);
        add(changelog);
        add(autor);

        String labelText = """
                # Allgemeines #
                - Neue Schriftart im Haus! Sie sieht nicht nur schick aus, sondern verbessert auch die Kompatibilität.
                - Die Sidebar hat sich schick gemacht und ist jetzt auf kommende Updates vorbereitet.
                - Im Sidebar-Menü haben wir den Team-Reiter durch "Changes" ersetzt.
                - Du wirst es nicht übersehen: Der Update-Hinweis erstrahlt im neuen Design!
                - Natürlich haben wir die Sicherheit nicht vergessen – einige sicherheitstechnische Änderungen wurden vorgenommen!
                                
                # AutoShout ist da! #
                - Du kannst jetzt automatische Shoutouts an bestimmte User senden, wenn sie den Stream betreten.
                                
                # Anmeldung (Login) #
                - Keine Mini-Hänger mehr nach einem erfolgreichen Login – alles läuft jetzt butterweich!
                - Du kannst jetzt nicht nur einloggen, sondern auch registrieren!
                - Keine Sorgen mehr um gesperrte Accounts – wir haben klare Hinweise hinzugefügt!.
                - Wir haben ein paar kleine, verwirrende Fehler beseitigt. Nichts soll zwischen dir und deinem Stream-Spaß stehen!
                - Logindaten können ab sofort gespeichert werden. Bequemlichkeit, wir kommen!
                                
                # Dashboard #
                - Ab jetzt kannst du dein Passwort eigenhändig ändern. Du hast das Ruder in der Hand!
                - Hol dir die Neuigkeiten direkt ins Dashboard: Der News-Banner informiert dich jetzt über aktuelle Geschehnisse!
                                
                # AutoVIP #
                - Mal keine VIP-Behandlung? Kein Problem! AutoVIP deaktivieren – du hast die Kontrolle!
                - Kompaktere Slider, genauso leistungsstark!
                - Feinabstimmung leicht gemacht: Die Slider zeigen jetzt Einzelschritte für präzise Optimierung.
                - Die neue Whitelist verleiht VIP-Status unabhängig von Aktivität!
                - Die Blacklist verhindert VIP-Verleihungen. Aktuelle VIPs auf der Blacklist verabschieden sich leider.""";

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

        // Erstelle ein benutzerdefiniertes Border
        Border border = BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        );
        title.setBorder(border);

        title.setBounds(338, 175, 350, 30);
        autor.setBounds(312, 210, 400, 20);
        scrollPane.setBounds(10, 250, 1000, 450);
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
