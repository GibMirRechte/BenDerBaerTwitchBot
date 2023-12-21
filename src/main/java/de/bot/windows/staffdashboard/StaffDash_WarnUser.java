package de.bot.windows.staffdashboard;

import de.bot.handler.AccountHandler;
import de.bot.handler.ImageIconHandler;
import de.bot.handler.UpdateHandler;
import de.bot.handler.WindowHandler;
import de.bot.utils.Account;
import de.bot.utils.Announcement;
import de.bot.utils.ExternalUser;
import de.bot.utils.Warn;
import de.bot.windows.modules.ComboBoxRenderer;
import de.bot.windows.modules.CustomComboBox;
import de.bot.windows.modules.CustomScrollBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StaffDash_WarnUser extends JPanel {

    UpdateHandler updateHandler = UpdateHandler.getInstance();
    AccountHandler accountHandler = AccountHandler.getInstance();
    WindowHandler windowHandler = new WindowHandler();

    public StaffDash_WarnUser(ExternalUser externalUser) {
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

        JLabel titleLabel = new JLabel("User verwarnen - " + externalUser.getUsername());
        titleLabel.setBounds(20, 10, 600, 36);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
        titleLabel.setVerticalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 36));
        add(titleLabel);

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

        JLabel warnHistoryBackground = new JLabel();
        warnHistoryBackground.setBounds(605, 175, 400, 450);
        warnHistoryBackground.setBackground(new Color(0x262626));
        warnHistoryBackground.setOpaque(true);
        warnHistoryBackground.setVisible(true);

        JLabel warnHistoryTitle = new JLabel("Warnliste (" + externalUser.getInvalidWarnSize() + " | " + externalUser.getValidWarnSize() + " | " + externalUser.getWarns().size() + ")");
        warnHistoryTitle.setForeground(Color.WHITE);
        warnHistoryTitle.setHorizontalAlignment(SwingConstants.LEFT);
        warnHistoryTitle.setHorizontalTextPosition(SwingConstants.LEADING);
        warnHistoryTitle.setVerticalAlignment(SwingConstants.CENTER);
        warnHistoryTitle.setFont(new Font("Arial", Font.PLAIN, 20));

        warnHistoryTitle.setBounds(605, 150, 400, 25);

        JLabel noWarnsFound = new JLabel("-- Kein Eintrag vorhanden --");
        noWarnsFound.setBounds(warnHistoryBackground.getBounds());
        noWarnsFound.setForeground(Color.WHITE);
        noWarnsFound.setHorizontalAlignment(SwingConstants.CENTER);
        noWarnsFound.setHorizontalTextPosition(SwingConstants.CENTER);
        noWarnsFound.setVerticalAlignment(SwingConstants.CENTER);
        noWarnsFound.setFont(new Font("Arial", Font.PLAIN, 20));

        StringBuilder banText = new StringBuilder("<html><head><style>body {font-family: 'Trebuchet MS', sans-serif; font-size: 12px; color: white;}</style></head><body><center><hr>");

        if (externalUser.getWarns().isEmpty()) {
            banText = new StringBuilder();
            add(noWarnsFound);
        } else {
            for (Warn warn : externalUser.getWarns()) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
                banText.append("<br>Verwarnt von: <font color=#" + warn.getOperatorColor() + ">").append(warn.getOperator()).append("</font><br>").append("Verwarnt am: <font color=#ababab>").append(dateFormat.format(new Date(warn.getTimestamp()))).append(" Uhr </font><br>").append("Grund: <font color=#e04d3d>").append(warn.getReason()).append("</font><br>");
                if (warn.isCancelled()) {
                    banText.append("<font color=gray>- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -</font><br>" + "Aufgehoben von: <font color=#" + warn.getCancelledByColor() + ">").append(warn.getCancelledBy()).append("</font><br>").append("Aufgehoben am: <font color=#ababab>").append(dateFormat.format(new Date(warn.getCanncelledAt()))).append("</font><br>").append("Aufgebungsgrund: <font color=#e04d3d>").append(warn.getCancelledReason()).append("</font><br>");
                }

                banText.append("<br><hr>");
            }
        }

        JEditorPane editorPaneWarns = new JEditorPane();

        editorPaneWarns.setContentType("text/html");
        editorPaneWarns.setText(banText.toString());
        editorPaneWarns.setEditable(false);
        editorPaneWarns.setForeground(Color.WHITE);
        editorPaneWarns.setBackground(new Color(0x262626));
        editorPaneWarns.setFont(new Font("Arial", Font.PLAIN, 18));

        JScrollPane scrollPaneWarns = new JScrollPane(editorPaneWarns);
        scrollPaneWarns.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPaneWarns.setBorder(null);
        scrollPaneWarns.getVerticalScrollBar().setUI(new CustomScrollBar());
        scrollPaneWarns.getHorizontalScrollBar().setUI(new CustomScrollBar());
        scrollPaneWarns.getViewport().setBackground(Color.BLUE);
        scrollPaneWarns.getViewport().setOpaque(true);

        add(scrollPaneWarns);
        scrollPaneWarns.setBounds(warnHistoryBackground.getBounds());
        editorPaneWarns.setCaretPosition(0);

        JTextField customReason = new JTextField();

        customReason.setVisible(false);
        customReason.setBackground(new Color(0x262626));
        customReason.setForeground(Color.WHITE);
        customReason.setHorizontalAlignment(JLabel.CENTER);
        customReason.setFont(new Font("Arial", Font.BOLD, 16));
        customReason.setBounds(170, 390, 265, 50);

        String[] reasons;

        if (account.getAccountRank().isAdmin_team()) {
            reasons = new String[]{"-- Grund auswählen --", "Unerlaubtes Bugusing", "Unangemessenes Verhalten", "Missachtung von Anweisungen", "Missbrauch von Meldefunktionen",
                    "Verbreitung von Falschinformationen", "Missbrauch von Rechten oder Privilegien", "Eigener Grund"};
        } else {
            reasons = new String[]{"-- Grund auswählen --", "Unerlaubtes Bugusing", "Unangemessenes Verhalten", "Missachtung von Anweisungen", "Missbrauch von Meldefunktionen",
                    "Verbreitung von Falschinformationen", "Missbrauch von Rechten oder Privilegien"};
        }

        JComboBox selectReason = new JComboBox(reasons);
        selectReason.setBackground(new Color(0x262626));
        selectReason.setFont(new Font("Arial", Font.BOLD, 16));
        selectReason.setBounds(148, 450, 315, 35);
        selectReason.setUI(new CustomComboBox());
        selectReason.setRenderer(new ComboBoxRenderer());
        selectReason.setFocusable(false);
        selectReason.setOpaque(false);
        selectReason.setForeground(Color.WHITE);

        selectReason.addActionListener(e -> {
            String selectedOption = (String) selectReason.getSelectedItem();
            assert selectedOption != null;
            customReason.setVisible(selectedOption.equalsIgnoreCase("Eigener Grund"));
        });

        JLabel commentBackground = new JLabel();
        commentBackground.setBounds(50, 175, 500, 115);
        commentBackground.setBackground(new Color(0x1A1A1A));
        commentBackground.setVisible(true);
        commentBackground.setOpaque(true);
        commentBackground.setBorder(BorderFactory.createLineBorder(new Color(0x333333), 1));


        JLabel modCommentCreator = new JLabel("Von: " + externalUser.getModCommentCreator());

        modCommentCreator.setBounds(70, 195, 500, 20);
        modCommentCreator.setForeground(Color.WHITE);
        modCommentCreator.setHorizontalAlignment(SwingConstants.LEFT);
        modCommentCreator.setHorizontalTextPosition(SwingConstants.LEADING);
        modCommentCreator.setIcon(new ImageIcon(accountHandler.getBadge(externalUser.getModCommentCreatorRank())));
        modCommentCreator.setVerticalAlignment(SwingConstants.CENTER);
        modCommentCreator.setFont(new Font("Arial", Font.PLAIN, 16));

        JLabel modComment = new JLabel("Moderations-Kommentar: " + externalUser.getModComment());

        modComment.setBounds(70, 215, 500, 20);
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

        add(modComment);
        add(modCommentCreator);
        add(noCommentFound);
        add(commentBackground);

        JLabel warnButton = new JLabel("Verwarnen");
        JLabel saveFeedback = new JLabel("Verwarnen");

        saveFeedback.setBackground(new Color(0x05A489));
        saveFeedback.setForeground(Color.WHITE);
        saveFeedback.setOpaque(true);
        saveFeedback.setVisible(false);
        saveFeedback.setHorizontalAlignment(JLabel.CENTER);
        saveFeedback.setHorizontalTextPosition(JLabel.RIGHT);
        saveFeedback.setFont(new Font("Arial", Font.PLAIN, 14));

        saveFeedback.setBounds(200, 120, 625, 40);
        add(saveFeedback);

        warnButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                warnButton.setText("<html><u>Verwarnen</u></html>");
                warnButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                warnButton.setText("Verwarnen");
                warnButton.setCursor(Cursor.getDefaultCursor());
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

                try {
                    Socket socket = new Socket("45.93.249.139", 3459);
                    OutputStream outputStream = socket.getOutputStream();
                    PrintStream printStream = new PrintStream(outputStream);
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    printStream.println("CREATE_WARN");
                    printStream.println(accountHandler.getAccount().getName());
                    printStream.println(externalUser.getUsername());
                    printStream.println(reason);
                    externalUser.getWarns().add(0, new Warn(reason, account.getName(), account.getAccountRank().getHtmlColor(), System.currentTimeMillis(), false, "", "", "", 0));
                    windowHandler.openStaffDB(WindowHandler.WindowType.UserInfo, externalUser);
                } catch (Exception ignored) {
                    saveFeedback.setBackground(new Color(0xFFD03F3F));
                    saveFeedback.setText("Es konnte keine Verbindung zum Server aufgebaut werden! Bitte versuche es später erneut.");
                    saveFeedback.setVisible(true);
                }
            }
        });

        warnButton.setBackground(new Color(0xAD4848));
        warnButton.setOpaque(true);
        warnButton.setForeground(Color.WHITE);
        warnButton.setHorizontalAlignment(SwingConstants.CENTER);
        warnButton.setVerticalAlignment(SwingConstants.CENTER);
        warnButton.setFont(new Font("Arial", Font.PLAIN, 28));

        backButton.setBounds(925, 750, 90, 50);
        warnButton.setBounds(20, 750, 885, 50);
        add(warnButton);
        add(backButton);

        add(selectReason);
        if (accountHandler.getAccount().getAccountRank().isAdmin_team())
            add(customReason);
        add(warnHistoryTitle);
        add(warnHistoryBackground);
    }
}