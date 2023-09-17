package de.bot.windows;

import de.bot.handler.AccountHandler;
import de.bot.handler.ImageIconHandler;
import de.bot.handler.UpdateHandler;
import de.bot.utils.Account;
import de.bot.utils.Announcement;

import javax.swing.*;
import java.awt.*;
import java.io.PrintStream;
import java.net.Socket;

public class Dashboard extends JPanel {

    UpdateHandler updateHandler = UpdateHandler.getInstance();
    AccountHandler accountHandler = AccountHandler.getInstance();

    public Dashboard() {
        Account account = accountHandler.getAccount();
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

        JLabel welcomeText = new JLabel("Willkommen " + account.getName() + "!");

        add(welcomeText);
        add(announcement);
        welcomeText.setBounds(20, 10, 600, 36);
        welcomeText.setForeground(Color.WHITE);
        welcomeText.setHorizontalAlignment(SwingConstants.LEFT);
        welcomeText.setVerticalAlignment(SwingConstants.CENTER);
        welcomeText.setFont(new Font("Trebuchet MS", Font.PLAIN, 36));
    }

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.2", 1336);
            PrintStream printStream = new PrintStream(socket.getOutputStream());
            String text = """
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

            printStream.println(text);
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
