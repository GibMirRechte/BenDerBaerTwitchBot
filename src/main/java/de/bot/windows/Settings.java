package de.bot.windows;

import de.bot.elements.RoundedJPasswordField;
import de.bot.handler.AccountHandler;
import de.bot.handler.ImageIconHandler;
import de.bot.handler.UpdateHandler;
import de.bot.handler.WindowHandler;
import de.bot.utils.Account;
import de.bot.utils.Announcement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

public class Settings extends JPanel {

    UpdateHandler updateHandler = UpdateHandler.getInstance();
    AccountHandler accountHandler = AccountHandler.getInstance();

    public Settings() {
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
        JLabel changePasswordTitle = new JLabel("Passwort ändern");
        JLabel saveFeedback = new JLabel("Die Einstellungen wurden erfolgreich gespeichert.");

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

        JLabel title = new JLabel("Einstellungen");
        JLabel saveButton = new JLabel("Speichern");

        RoundedJPasswordField resetPasswortCurrent = new RoundedJPasswordField(10, new Color(0x272727), false, 20, 40);
        RoundedJPasswordField resetPasswortNewOne = new RoundedJPasswordField(10, new Color(0x272727), false, 20, 40);
        RoundedJPasswordField resetPasswortNewTwo = new RoundedJPasswordField(10, new Color(0x272727), false, 20, 40);

        add(title);
        add(announcement);
        add(resetPasswortCurrent);
        add(resetPasswortNewOne);
        add(resetPasswortNewTwo);
        add(changePasswordTitle);

        title.setBounds(20, 10, 600, 36);
        changePasswordTitle.setBounds(45, 200, 300, 30);

        resetPasswortCurrent.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (resetPasswortCurrent.getText().equals("Aktuelles Passwort")) {
                    resetPasswortCurrent.setText("");
                    resetPasswortCurrent.setForeground(Color.WHITE);
                    resetPasswortCurrent.setEchoChar((char) 0x2022);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (resetPasswortCurrent.getText().isEmpty()) {
                    resetPasswortCurrent.setText("Aktuelles Passwort");
                    resetPasswortCurrent.setForeground(Color.GRAY);
                    resetPasswortCurrent.setEchoChar((char) 0);
                }
            }
        });
        resetPasswortCurrent.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (resetPasswortCurrent.getText().equals("Neues Passwort")) {
                    resetPasswortCurrent.setText("");
                    resetPasswortCurrent.setForeground(Color.WHITE);
                    resetPasswortCurrent.setEchoChar((char) 0x2022);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (resetPasswortCurrent.getText().isEmpty()) {
                    resetPasswortCurrent.setText("Neues Passwort");
                    resetPasswortCurrent.setForeground(Color.GRAY);
                    resetPasswortCurrent.setEchoChar((char) 0);
                }
            }
        });
        resetPasswortNewOne.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (resetPasswortNewOne.getText().equals("Neues Passwort")) {
                    resetPasswortNewOne.setText("");
                    resetPasswortNewOne.setForeground(Color.WHITE);
                    resetPasswortNewOne.setEchoChar((char) 0x2022);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (resetPasswortNewOne.getText().isEmpty()) {
                    resetPasswortNewOne.setText("Neues Passwort");
                    resetPasswortNewOne.setForeground(Color.GRAY);
                    resetPasswortNewOne.setEchoChar((char) 0);
                }
            }
        });
        resetPasswortNewTwo.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (resetPasswortNewTwo.getText().equals("Passwort wiederholen")) {
                    resetPasswortNewTwo.setText("");
                    resetPasswortNewTwo.setForeground(Color.WHITE);
                    resetPasswortNewTwo.setEchoChar((char) 0x2022);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (resetPasswortNewTwo.getText().isEmpty()) {
                    resetPasswortNewTwo.setText("Passwort wiederholen");
                    resetPasswortNewTwo.setForeground(Color.GRAY);
                    resetPasswortNewTwo.setEchoChar((char) 0);
                }
            }
        });

        changePasswordTitle.setForeground(Color.WHITE);
        changePasswordTitle.setHorizontalAlignment(SwingConstants.LEFT);
        changePasswordTitle.setVerticalAlignment(SwingConstants.CENTER);
        changePasswordTitle.setFont(new Font("Trebuchet MS", Font.PLAIN, 25));

        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(SwingConstants.LEFT);
        title.setVerticalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Trebuchet MS", Font.PLAIN, 36));

        resetPasswortCurrent.setVisible(true);
        resetPasswortCurrent.setBackground(new Color(0x464646));
        resetPasswortCurrent.setForeground(Color.GRAY);
        resetPasswortCurrent.setText("Aktuelles Passwort");
        resetPasswortCurrent.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        resetPasswortCurrent.setBounds(45, 250, 300, 50);
        resetPasswortCurrent.setEchoChar((char) 0);

        saveFeedback.setBackground(new Color(0x05A489));
        saveFeedback.setForeground(Color.WHITE);
        saveFeedback.setOpaque(true);
        saveFeedback.setVisible(false);
        saveFeedback.setHorizontalAlignment(JLabel.CENTER);
        saveFeedback.setHorizontalTextPosition(JLabel.RIGHT);
        saveFeedback.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));

        resetPasswortNewOne.setVisible(true);
        resetPasswortNewOne.setBackground(new Color(0x464646));
        resetPasswortNewOne.setForeground(Color.GRAY);
        resetPasswortNewOne.setText("Neues Passwort");
        resetPasswortNewOne.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        resetPasswortNewOne.setBounds(45, 310, 300, 50);
        resetPasswortNewOne.setEchoChar((char) 0);

        resetPasswortNewTwo.setVisible(true);
        resetPasswortNewTwo.setBackground(new Color(0x464646));
        resetPasswortNewTwo.setForeground(Color.GRAY);
        resetPasswortNewTwo.setText("Passwort wiederholen");
        resetPasswortNewTwo.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        resetPasswortNewTwo.setBounds(45, 370, 300, 50);
        resetPasswortNewTwo.setEchoChar((char) 0);

        saveButton.setBackground(new Color(0x86BECC));
        saveButton.setOpaque(true);
        saveButton.setForeground(Color.WHITE);
        saveButton.setHorizontalAlignment(SwingConstants.CENTER);
        saveButton.setVerticalAlignment(SwingConstants.CENTER);
        saveButton.setFont(new Font("Trebuchet MS", Font.PLAIN, 28));

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
            public void mouseReleased(MouseEvent event) {
                if (!resetPasswortCurrent.getText().equals(account.getPassword())) {
                    saveFeedback.setBackground(new Color(0xFFD03F3F));
                    saveFeedback.setText("Das aktuelle Passwort stimmt nicht überein!");
                    saveFeedback.setVisible(true);
                    saveFeedback.setOpaque(true);
                } else {
                    String newPasswort = resetPasswortNewOne.getText().replace("Neues Passwort", "");
                    String newPasswortTwo = resetPasswortNewTwo.getText().replace("Passwort wiederholen", "");

                    if (newPasswort.replace(" ", "").equals("") || newPasswortTwo.replace(" ", "").equals("")) {
                        saveFeedback.setBackground(new Color(0xFFD03F3F));
                        saveFeedback.setText("Bitte gebe ein gültiges neues Passwort an!");
                        saveFeedback.setVisible(true);
                        return;
                    }

                    if (!newPasswort.equals(newPasswortTwo)) {
                        saveFeedback.setBackground(new Color(0xFFD03F3F));
                        saveFeedback.setText("Die Passwörter stimmen nicht überein!");
                        saveFeedback.setVisible(true);
                        return;
                    }

                    if (newPasswort.equals(account.getPassword())) {
                        saveFeedback.setBackground(new Color(0xFFD03F3F));
                        saveFeedback.setText("Dieses Passwort ist bereits eingestellt!");
                        saveFeedback.setVisible(true);
                        return;
                    }

                    try {
                        Socket socket = new Socket("45.93.249.139", 3459);
                        OutputStream outputStream = socket.getOutputStream();
                        PrintStream printStream = new PrintStream(outputStream);
                        printStream.println("CHANGE_PW");
                        printStream.println(account.getName());
                        printStream.println(newPasswort);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    saveFeedback.setBackground(new Color(0x05A489));
                    saveFeedback.setText("Das Passwort wurde gespeichert. Du wirst abgemeldet.");
                    saveFeedback.setVisible(true);
                    accountHandler.logout();
                    WindowHandler.getInstance().openWindow(WindowHandler.WindowType.LOGIN);
                }
            }
        });

        saveButton.setBounds(20, 650, 985, 50);
        saveFeedback.setBounds(200, 120, 625, 40);

        add(saveButton);
        add(saveFeedback);

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
