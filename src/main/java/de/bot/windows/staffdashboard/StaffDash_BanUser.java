package de.bot.windows.staffdashboard;

import de.bot.handler.AccountHandler;
import de.bot.handler.ImageIconHandler;
import de.bot.handler.UpdateHandler;
import de.bot.handler.WindowHandler;
import de.bot.utils.Account;
import de.bot.utils.Announcement;
import de.bot.utils.BanData;
import de.bot.utils.ExternalUser;
import de.bot.windows.modules.ComboBoxRenderer;
import de.bot.windows.modules.CustomComboBox;
import de.bot.windows.modules.CustomScrollBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class StaffDash_BanUser extends JPanel {

    UpdateHandler updateHandler = UpdateHandler.getInstance();
    AccountHandler accountHandler = AccountHandler.getInstance();
    WindowHandler windowHandler = new WindowHandler();

    public StaffDash_BanUser(ExternalUser externalUser) {
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

        JLabel titleLabel = new JLabel("User sperren - " + externalUser.getUsername());
        titleLabel.setBounds(20, 10, 600, 36);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
        titleLabel.setVerticalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 36));
        add(titleLabel);

        JLabel banHistoryBackground = new JLabel();
        banHistoryBackground.setBounds(605, 175, 400, 450);
        banHistoryBackground.setBackground(new Color(0x262626));
        banHistoryBackground.setOpaque(true);
        banHistoryBackground.setVisible(true);

        JLabel banHistoryTitle = new JLabel("Banliste (" + externalUser.getInvalidBanSize() + " | " + externalUser.getValidBanSize() + " | " + externalUser.getBans().size() + ")");
        banHistoryTitle.setForeground(Color.WHITE);
        banHistoryTitle.setHorizontalAlignment(SwingConstants.LEFT);
        banHistoryTitle.setHorizontalTextPosition(SwingConstants.LEADING);
        banHistoryTitle.setVerticalAlignment(SwingConstants.CENTER);
        banHistoryTitle.setFont(new Font("Arial", Font.PLAIN, 20));

        banHistoryTitle.setBounds(605, 150, 400, 25);

        JLabel noBansFound = new JLabel("-- Kein Eintrag vorhanden --");
        noBansFound.setBounds(banHistoryBackground.getBounds());
        noBansFound.setForeground(Color.WHITE);
        noBansFound.setHorizontalAlignment(SwingConstants.CENTER);
        noBansFound.setHorizontalTextPosition(SwingConstants.CENTER);
        noBansFound.setVerticalAlignment(SwingConstants.CENTER);
        noBansFound.setFont(new Font("Arial", Font.PLAIN, 20));

        String banText = "<html><head><style>body {font-family: 'Trebuchet MS', sans-serif; font-size: 12px; color: white;}</style></head><body><center><hr>";

        if (externalUser.getBans().isEmpty()) {
            banText = "";
            add(noBansFound);
        } else {
            for (BanData banData : externalUser.getBans()) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
                banText += "<br>Gebannt von: <font color=#" + banData.getOperatorColor() + ">" + banData.getOperator() + "</font><br>" +
                        "Gebannt am: <font color=#ababab>" + dateFormat.format(new Date(banData.getCreated())) + " Uhr </font><br>" +
                        "Ende: <font color=#ababab>" + dateFormat.format(new Date(banData.getBannedUntil())) + " Uhr</font><br>" +
                        "Grund: <font color=#e04d3d>" + banData.getReason() + "</font><br>";
                if (banData.isCancelled()) {
                    banText += "<font color=gray>- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -</font><br>" +
                            "Aufgehoben von: <font color=#" + banData.getCancelledByColor() + ">" + banData.getCancelledBy() + "</font><br>" +
                            "Aufgehoben am: <font color=#ababab>" + dateFormat.format(new Date(banData.getCancelledAt())) + "</font><br>" +
                            "Aufgebungsgrund: <font color=#e04d3d>" + banData.getCancelledReason() + "</font><br>";
                }

                banText += "<br><hr>";
            }
        }

        JEditorPane editorPaneBans = new JEditorPane();

        editorPaneBans.setContentType("text/html");
        editorPaneBans.setText(banText.toString());
        editorPaneBans.setEditable(false);
        editorPaneBans.setForeground(Color.WHITE);
        editorPaneBans.setBackground(new Color(0x262626));
        editorPaneBans.setFont(new Font("Arial", Font.PLAIN, 18));

        JScrollPane scrollPaneBans = new JScrollPane(editorPaneBans);
        scrollPaneBans.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPaneBans.setBorder(null);
        scrollPaneBans.getVerticalScrollBar().setUI(new CustomScrollBar());
        scrollPaneBans.getHorizontalScrollBar().setUI(new CustomScrollBar());
        scrollPaneBans.getViewport().setBackground(Color.BLUE);
        scrollPaneBans.getViewport().setOpaque(true);

        add(scrollPaneBans);
        scrollPaneBans.setBounds(banHistoryBackground.getBounds());
        editorPaneBans.setCaretPosition(0);

        JTextField customReason = new JTextField();

        customReason.setVisible(false);
        customReason.setBackground(new Color(0x262626));
        customReason.setForeground(Color.WHITE);
        customReason.setHorizontalAlignment(JLabel.CENTER);
        customReason.setFont(new Font("Arial", Font.BOLD, 16));
        customReason.setBounds(170, 390, 265, 50);

        String[] reasons;

        if (account.getAccountRank().isAdmin_team()) {
            reasons = new String[]{"-- Grund auswählen --", "Betrug oder illegale Aktivitäten", "Gewalttätige oder extremistische Inhalte", "Hassrede oder Diskriminierung",
                    "Missbrauch von Meldefunktionen", "Sexuelle Belästigung oder Kinderpornografie", "Identitätsdiebstahl", "Malware-Verbreitung oder Hacking",
                    "Anstiftung zu Gewalt oder illegalen Handlungen", "Missbrauch von Bewertungssystemen", "Hacking-Versuch", "Altersbeschränkungsverletzungen",
                    "Veröffentlichung von persönlichen Daten", "Unberechtigter Zugriff auf Konten", "Massive Störung der Community",
                    "Verbreitung von Falschinformationen", "Missbrauch von Rechten oder Privilegien", "Eigener Grund"};
        } else {
            reasons = new String[]{"-- Grund auswählen --", "Betrug oder illegale Aktivitäten", "Gewalttätige oder extremistische Inhalte", "Hassrede oder Diskriminierung",
                    "Missbrauch von Meldefunktionen", "Sexuelle Belästigung oder Kinderpornografie", "Identitätsdiebstahl", "Malware-Verbreitung oder Hacking",
                    "Anstiftung zu Gewalt oder illegalen Handlungen", "Missbrauch von Bewertungssystemen", "Hacking-Versuch", "Altersbeschränkungsverletzungen",
                    "Veröffentlichung von persönlichen Daten", "Unberechtigter Zugriff auf Konten", "Massive Störung der Community",
                    "Verbreitung von Falschinformationen", "Missbrauch von Rechten oder Privilegien"};
        }

        JComboBox selectReason = new JComboBox(reasons);
        selectReason.setBackground(new Color(0x262626));
        selectReason.setFont(new Font("Arial", Font.BOLD, 16));
        selectReason.setBounds(106, 450, 400, 35);
        selectReason.setUI(new CustomComboBox());
        selectReason.setRenderer(new ComboBoxRenderer());
        selectReason.setFocusable(false);
        selectReason.setOpaque(false);
        selectReason.setForeground(Color.WHITE);

        String[] lengthArray = new String[]{"Stunden", "Tage", "Wochen", "Monate", "Permanent"};

        JComboBox selectBanLength = new JComboBox(lengthArray);
        selectBanLength.setBackground(new Color(0x262626));
        selectBanLength.setFont(new Font("Arial", Font.BOLD, 16));
        selectBanLength.setBounds(286, 500, 125, 35);
        selectBanLength.setUI(new CustomComboBox());
        selectBanLength.setRenderer(new ComboBoxRenderer());
        selectBanLength.setFocusable(false);
        selectBanLength.setOpaque(false);
        selectBanLength.setForeground(Color.WHITE);
        add(selectBanLength);

        JTextField lengthNumber = new JTextField();
        lengthNumber.setBackground(new Color(0x262626));
        lengthNumber.setForeground(Color.WHITE);
        lengthNumber.setHorizontalAlignment(JLabel.CENTER);
        lengthNumber.setFont(new Font("Arial", Font.BOLD, 16));
        lengthNumber.setBounds(180, 500, 100, 35);

        add(lengthNumber);

        JLabel commentBackground = new JLabel();
        commentBackground.setBounds(50, 175, 500, 115);
        commentBackground.setBackground(new Color(0x262626));
        commentBackground.setOpaque(true);
        commentBackground.setVisible(true);

        JLabel modCommentCreator = new JLabel("Von: " + externalUser.getModCommentCreator());

        modCommentCreator.setBounds(55, 180, 500, 20);
        modCommentCreator.setForeground(Color.WHITE);
        modCommentCreator.setHorizontalAlignment(SwingConstants.LEFT);
        modCommentCreator.setHorizontalTextPosition(SwingConstants.LEADING);
        modCommentCreator.setIcon(new ImageIcon(accountHandler.getBadge(externalUser.getModCommentCreatorRank())));
        modCommentCreator.setVerticalAlignment(SwingConstants.CENTER);
        modCommentCreator.setFont(new Font("Arial", Font.PLAIN, 16));

        JLabel modComment = new JLabel("Moderations-Kommentar: " + externalUser.getModComment());

        modComment.setBounds(55, 200, 500, 20);
        modComment.setForeground(Color.WHITE);
        modComment.setHorizontalAlignment(SwingConstants.LEFT);
        modComment.setHorizontalTextPosition(SwingConstants.LEADING);
        modComment.setVerticalAlignment(SwingConstants.CENTER);
        modComment.setFont(new Font("Arial", Font.PLAIN, 16));

        JLabel noCommentFound = new JLabel("-- Kein Moderationskommentar vorhanden --");

        noCommentFound.setBounds(commentBackground.getBounds());
        noCommentFound.setForeground(Color.WHITE);
        noCommentFound.setHorizontalAlignment(SwingConstants.CENTER);
        noCommentFound.setHorizontalTextPosition(SwingConstants.CENTER);
        noCommentFound.setVerticalAlignment(SwingConstants.CENTER);
        noCommentFound.setVisible(false);
        noCommentFound.setFont(new Font("Arial", Font.PLAIN, 20));

        if (externalUser.getModComment().equalsIgnoreCase("")) {
            modComment.setVisible(false);
            modCommentCreator.setVisible(false);
            noCommentFound.setVisible(true);
        }

        selectReason.addActionListener(e -> {
            String selectedOption = (String) selectReason.getSelectedItem();
            assert selectedOption != null;
            customReason.setVisible(selectedOption.equalsIgnoreCase("Eigener Grund"));
        });

        selectBanLength.addActionListener(e -> {
            String selectedOption = (String) selectBanLength.getSelectedItem();
            assert selectedOption != null;
            lengthNumber.setVisible(!selectedOption.equalsIgnoreCase("Permanent"));
        });

        add(customReason);
        add(modComment);
        add(modCommentCreator);
        add(noCommentFound);
        add(commentBackground);

        lengthNumber.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                    e.consume(); // Verhindert die Eingabe von Nicht-Zahlen
                }
                if (lengthNumber.getText().length() >= 2 && !(c == KeyEvent.VK_BACK_SPACE) && !(c == KeyEvent.VK_DELETE)) {
                    e.consume(); // Verhindert die Eingabe von mehr als zwei Zeichen
                }
            }
        });


        JLabel backButton = new JLabel("<html>\uD83D\uDEAA</html>");

        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                backButton.setText("<html><u>\uD83D\uDEAA</u></html>");
                backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                backButton.setToolTipText("Zurück");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                backButton.setText("<html>\uD83D\uDEAA</html>");
                backButton.setCursor(Cursor.getDefaultCursor());
                backButton.setToolTipText(null);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                windowHandler.openStaffDB(WindowHandler.WindowType.UserInfo, externalUser);
            }
        });

        backButton.setBackground(new Color(0x1E5E8B));
        backButton.setOpaque(true);
        backButton.setForeground(Color.WHITE);
        backButton.setHorizontalAlignment(SwingConstants.CENTER);
        backButton.setVerticalAlignment(SwingConstants.CENTER);
        backButton.setFont(new Font("Arial", Font.PLAIN, 28));

        JLabel banButton = new JLabel("Sperren");
        JLabel saveFeedback = new JLabel("Sperren");

        saveFeedback.setBackground(new Color(0x05A489));
        saveFeedback.setForeground(Color.WHITE);
        saveFeedback.setOpaque(true);
        saveFeedback.setVisible(false);
        saveFeedback.setHorizontalAlignment(JLabel.CENTER);
        saveFeedback.setHorizontalTextPosition(JLabel.RIGHT);
        saveFeedback.setFont(new Font("Arial", Font.PLAIN, 14));

        saveFeedback.setBounds(200, 120, 625, 40);

        banButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                banButton.setText("<html><u>Sperren</u></html>");
                banButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                banButton.setText("Sperren");
                banButton.setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                saveFeedback.setVisible(false);
                if (selectReason.getSelectedItem().toString().equalsIgnoreCase("Eigener Grund")) {
                    if (customReason.getText().isBlank()) {
                        saveFeedback.setBackground(new Color(0xFFD03F3F));
                        saveFeedback.setText("Bitte gebe ein gültigen Grund an!");
                        saveFeedback.setVisible(true);
                        return;
                    }
                }
                String reason;

                if (selectReason.getSelectedItem().toString().equalsIgnoreCase("Eigener Grund")) {
                    reason = customReason.getText();
                } else {
                    reason = selectReason.getSelectedItem().toString();
                }

                if (reason.equalsIgnoreCase("-- Grund auswählen --")) {
                    saveFeedback.setBackground(new Color(0xFFD03F3F));
                    saveFeedback.setText("Bitte gebe ein gültigen Grund an!");
                    saveFeedback.setVisible(true);
                    return;
                }

                long duration = 0;
                if (selectBanLength.getSelectedItem().toString().equalsIgnoreCase("Stunden")) {
                    duration = TimeUnit.HOURS.toMillis(Long.parseLong(lengthNumber.getText()));
                } else if (selectBanLength.getSelectedItem().toString().equalsIgnoreCase("Tage")) {
                    duration = TimeUnit.DAYS.toMillis(Long.parseLong(lengthNumber.getText()));
                } else if (selectBanLength.getSelectedItem().toString().equalsIgnoreCase("Wochen")) {
                    duration = TimeUnit.DAYS.toMillis(Long.parseLong(lengthNumber.getText()) * 7);
                } else if (selectBanLength.getSelectedItem().toString().equalsIgnoreCase("Monate")) {
                    duration = TimeUnit.DAYS.toMillis(Long.parseLong(lengthNumber.getText()) * 30);
                } else if (selectBanLength.getSelectedItem().toString().equalsIgnoreCase("Permanent")) {
                    duration = -1;
                }

                long end = 0;

                if (duration == -1) {
                    end = -1;
                } else {
                    end = (System.currentTimeMillis() + duration);
                }

                try {
                    Socket socket = new Socket("45.93.249.139", 3459);
                    OutputStream outputStream = socket.getOutputStream();
                    PrintStream printStream = new PrintStream(outputStream);

                    printStream.println("CREATE_BAN");
                    printStream.println(accountHandler.getAccount().getName());
                    printStream.println(externalUser.getUsername());
                    printStream.println(reason);
                    printStream.println(end);
                    externalUser.getBans().add(0, new BanData(reason, account.getName(), account.getAccountRank().getHtmlColor(), System.currentTimeMillis(), end, false, "", "", "", 0));
                    windowHandler.openStaffDB(WindowHandler.WindowType.UserInfo, externalUser);
                } catch (Exception ignored) {
                    saveFeedback.setBackground(new Color(0xFFD03F3F));
                    saveFeedback.setText("Es konnte keine Verbindung zum Server aufgebaut werden! Bitte versuche es später erneut.");
                    saveFeedback.setVisible(true);
                }
            }
        });

        add(saveFeedback);

        banButton.setBackground(new Color(0xAD4848));
        banButton.setOpaque(true);
        banButton.setForeground(Color.WHITE);
        banButton.setHorizontalAlignment(SwingConstants.CENTER);
        banButton.setVerticalAlignment(SwingConstants.CENTER);
        banButton.setFont(new Font("Arial", Font.PLAIN, 28));

        backButton.setBounds(925, 750, 75, 50);
        banButton.setBounds(20, 750, 885, 50);
        add(banButton);
        add(backButton);

        add(selectReason);
        add(banHistoryTitle);
        add(banHistoryBackground);
    }
}
