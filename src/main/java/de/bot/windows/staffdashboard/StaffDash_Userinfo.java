package de.bot.windows.staffdashboard;

import de.bot.handler.AccountHandler;
import de.bot.handler.ImageIconHandler;
import de.bot.handler.UpdateHandler;
import de.bot.handler.WindowHandler;
import de.bot.utils.*;
import de.bot.windows.modules.CustomScrollBar;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StaffDash_Userinfo extends JPanel {

    UpdateHandler updateHandler = UpdateHandler.getInstance();
    AccountHandler accountHandler = AccountHandler.getInstance();
    WindowHandler windowHandler = WindowHandler.getInstance();

    public StaffDash_Userinfo(ExternalUser externalUser) {
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

        JLabel titleLabel = new JLabel("Userinfo - " + externalUser.getUsername());
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
        titleLabel.setVerticalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 36));
        add(titleLabel);

        JLabel userDataBackground = new JLabel();
        userDataBackground.setBackground(new Color(0x1A1A1A));
        userDataBackground.setVisible(true);
        userDataBackground.setOpaque(true);
        userDataBackground.setBorder(BorderFactory.createLineBorder(new Color(0x333333), 1));

        JLabel username = new JLabel("Benutzername: " + externalUser.getUsername());


        username.setForeground(Color.WHITE);
        username.setHorizontalAlignment(SwingConstants.LEFT);
        username.setVerticalAlignment(SwingConstants.CENTER);
        username.setFont(new Font("Arial", Font.PLAIN, 16));

        JLabel rank = new JLabel("Rang: ");

        JLabel bansBackground = new JLabel();
        JLabel warnsBackground = new JLabel();
        JLabel modCommentBackground = new JLabel();

        modCommentBackground.setBackground(new Color(0x1A1A1A));
        modCommentBackground.setVisible(true);
        modCommentBackground.setOpaque(true);
        modCommentBackground.setBorder(BorderFactory.createLineBorder(new Color(0x333333), 1));

        bansBackground.setBackground(new Color(0x1A1A1A));
        bansBackground.setVisible(true);
        bansBackground.setOpaque(true);
        bansBackground.setBorder(BorderFactory.createLineBorder(new Color(0x333333), 1));

        warnsBackground.setBackground(new Color(0x1A1A1A));
        warnsBackground.setVisible(true);
        warnsBackground.setOpaque(true);
        warnsBackground.setBorder(BorderFactory.createLineBorder(new Color(0x333333), 1));

        rank.setForeground(Color.WHITE);
        rank.setHorizontalAlignment(SwingConstants.LEFT);
        rank.setHorizontalTextPosition(SwingConstants.LEADING);
        rank.setIcon(new ImageIcon(accountHandler.getBadge(externalUser.getAccountRank())));
        rank.setVerticalAlignment(SwingConstants.CENTER);
        rank.setFont(new Font("Arial", Font.PLAIN, 16));

        JLabel saveFeedback = new JLabel("");
        saveFeedback.setBackground(new Color(0xFFD03F3F));
        saveFeedback.setForeground(Color.WHITE);
        saveFeedback.setOpaque(true);
        saveFeedback.setVisible(false);
        saveFeedback.setHorizontalAlignment(JLabel.CENTER);
        saveFeedback.setHorizontalTextPosition(JLabel.RIGHT);
        saveFeedback.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel bansTitle = new JLabel("Banliste (" + externalUser.getBans().size() + ")");
        bansTitle.setForeground(Color.WHITE);
        bansTitle.setHorizontalAlignment(SwingConstants.LEFT);
        bansTitle.setHorizontalTextPosition(SwingConstants.LEADING);
        bansTitle.setVerticalAlignment(SwingConstants.CENTER);
        bansTitle.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel warnsTitle = new JLabel("Warnliste (" + externalUser.getWarns().size() + ")");
        warnsTitle.setForeground(Color.WHITE);
        warnsTitle.setHorizontalAlignment(SwingConstants.LEFT);
        warnsTitle.setHorizontalTextPosition(SwingConstants.LEADING);
        warnsTitle.setVerticalAlignment(SwingConstants.CENTER);
        warnsTitle.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel commentBackground = new JLabel();
        commentBackground.setBackground(new Color(0x262626));
        commentBackground.setOpaque(true);
        commentBackground.setVisible(true);

        JLabel modCommentCreator = new JLabel("Von: " + externalUser.getModCommentCreator());

        modCommentCreator.setForeground(Color.WHITE);
        modCommentCreator.setHorizontalAlignment(SwingConstants.LEFT);
        modCommentCreator.setHorizontalTextPosition(SwingConstants.LEADING);
        modCommentCreator.setIcon(new ImageIcon(accountHandler.getBadge(externalUser.getModCommentCreatorRank())));
        modCommentCreator.setVerticalAlignment(SwingConstants.CENTER);
        modCommentCreator.setFont(new Font("Arial", Font.PLAIN, 16));

        JTextArea modComment = new JTextArea("Kommentar: " + externalUser.getModComment());

        modComment.setForeground(Color.WHITE);
        modComment.setEditable(false);
        modComment.setLineWrap(true);
        modComment.setWrapStyleWord(true);
        modComment.setBackground(new Color(0x262626));
        modComment.setFont(new Font("Arial", Font.PLAIN, 16));

        JTextField modCommentEditable = new JTextField(externalUser.getModComment());
        modCommentEditable.setForeground(Color.WHITE);
        modCommentEditable.setBackground(new Color(0x262626));
        modCommentEditable.setVisible(false);
        modCommentEditable.setFont(new Font("Arial", Font.PLAIN, 16));

        JLabel noCommentFound = new JLabel("-- Kein Moderationskommentar vorhanden --");

        noCommentFound.setBounds(commentBackground.getBounds());
        noCommentFound.setForeground(Color.WHITE);
        noCommentFound.setHorizontalAlignment(SwingConstants.CENTER);
        noCommentFound.setHorizontalTextPosition(SwingConstants.CENTER);
        noCommentFound.setVerticalAlignment(SwingConstants.CENTER);
        noCommentFound.setVisible(false);
        noCommentFound.setFont(new Font("Arial", Font.PLAIN, 20));

        if (externalUser.getModComment().isBlank()) {
            modComment.setVisible(false);
            modCommentCreator.setVisible(false);
            noCommentFound.setVisible(true);
        }

        JLabel noBansFound = new JLabel("-- Kein Eintrag vorhanden --");
        noBansFound.setForeground(Color.WHITE);
        noBansFound.setHorizontalAlignment(SwingConstants.CENTER);
        noBansFound.setHorizontalTextPosition(SwingConstants.CENTER);
        noBansFound.setVerticalAlignment(SwingConstants.CENTER);
        noBansFound.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel noWarnsFound = new JLabel("-- Kein Eintrag vorhanden --");
        noWarnsFound.setForeground(Color.WHITE);
        noWarnsFound.setHorizontalAlignment(SwingConstants.CENTER);
        noWarnsFound.setHorizontalTextPosition(SwingConstants.CENTER);
        noWarnsFound.setVerticalAlignment(SwingConstants.CENTER);
        noWarnsFound.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel changeCommentButton = new JLabel("Ändern");
        JLabel deleteCommentButton = new JLabel("Löschen");

        changeCommentButton.setBackground(new Color(0x1E5E8B));
        changeCommentButton.setOpaque(true);
        changeCommentButton.setForeground(Color.WHITE);
        changeCommentButton.setHorizontalAlignment(SwingConstants.CENTER);
        changeCommentButton.setVerticalAlignment(SwingConstants.CENTER);
        changeCommentButton.setFont(new Font("Arial", Font.PLAIN, 25));

        final String[] oldModComment = {externalUser.getModComment()};

        changeCommentButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                changeCommentButton.setBackground(new Color(0x236C9F));
                changeCommentButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                changeCommentButton.setBackground(new Color(0x1E5E8B));
                changeCommentButton.setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (changeCommentButton.getText().equalsIgnoreCase("Ändern")) {
                    noCommentFound.setVisible(false);
                    modCommentEditable.setVisible(true);
                    modCommentEditable.setText(modComment.getText().replace("Kommentar: ", ""));
                    modComment.setVisible(false);
                    deleteCommentButton.setText("Abbrechen");
                    changeCommentButton.setText("Speichern");
                } else {

                    noCommentFound.setVisible(false);
                    modCommentEditable.setVisible(false);
                    modComment.setVisible(true);
                    deleteCommentButton.setText("Löschen");
                    changeCommentButton.setText("Ändern");
                    if (!accountHandler.getAccount().getAccountRank().isAdmin_team()) {
                        deleteCommentButton.setVisible(false);
                    }

                    if (!modCommentEditable.getText().isBlank() &&
                            !oldModComment[0].equalsIgnoreCase(modCommentEditable.getText())) {
                        modCommentCreator.setVisible(true);
                        modComment.setText("Kommentar: " + modCommentEditable.getText());
                        modCommentCreator.setText("Von: " + accountHandler.getAccount().getName());
                        modCommentCreator.setIcon(new ImageIcon(accountHandler.getBadge(accountHandler.getAccount().getAccountRank())));
                        modCommentCreator.setHorizontalTextPosition(SwingConstants.LEADING);
                        oldModComment[0] = modCommentEditable.getText();
                        try {
                            Socket socket = new Socket("45.93.249.139", 3459);
                            OutputStream outputStream = socket.getOutputStream();
                            PrintStream printStream = new PrintStream(outputStream);
                            printStream.println("SAVE");
                            printStream.println("MOD_COMMENT");
                            printStream.println(accountHandler.getAccount().getName());
                            printStream.println(externalUser.getUsername());
                            printStream.println(modCommentEditable.getText());

                            externalUser.updateModComment(modCommentEditable.getText(), accountHandler.getAccount().getName(), accountHandler.getAccount().getAccountRank());
                        } catch (Exception ignored) {
                        }

                        return;
                    }

                    if (oldModComment[0].equalsIgnoreCase("")) {
                        noCommentFound.setVisible(true);
                        modComment.setVisible(false);
                    }
                }

            }
        });

        ((AbstractDocument) modCommentEditable.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                int currentLength = fb.getDocument().getLength();
                int maxLength = 50;

                if (currentLength - length + text.length() <= maxLength) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });

        deleteCommentButton.setBackground(new Color(0xD03F3F));
        deleteCommentButton.setOpaque(true);
        deleteCommentButton.setVisible(accountHandler.getAccount().getAccountRank().isAdmin_team());
        deleteCommentButton.setForeground(Color.WHITE);
        deleteCommentButton.setHorizontalAlignment(SwingConstants.CENTER);
        deleteCommentButton.setVerticalAlignment(SwingConstants.CENTER);
        deleteCommentButton.setFont(new Font("Arial", Font.PLAIN, 25));

        deleteCommentButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                deleteCommentButton.setBackground(new Color(0xE04F4F));
                deleteCommentButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                deleteCommentButton.setBackground(new Color(0xD03F3F));
                deleteCommentButton.setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (deleteCommentButton.getText().equalsIgnoreCase("Abbrechen")) {
                    modCommentEditable.setVisible(false);
                    deleteCommentButton.setText("Löschen");
                    changeCommentButton.setText("Ändern");
                    if (!accountHandler.getAccount().getAccountRank().isAdmin_team()) {
                        deleteCommentButton.setVisible(false);
                    }
                    if (oldModComment[0].isBlank()) {
                        noCommentFound.setVisible(true);
                        modCommentEditable.setVisible(false);
                        modCommentCreator.setVisible(false);
                    } else {
                        modComment.setVisible(true);
                        modCommentEditable.setVisible(false);
                        modCommentCreator.setVisible(true);
                    }
                } else if (deleteCommentButton.getText().equalsIgnoreCase("Löschen")) {
                    if (accountHandler.getAccount().getAccountRank().isAdmin_team()) {
                        modComment.setText("Kommentar: ");
                        modCommentEditable.setText("");
                        noCommentFound.setVisible(true);
                        modCommentCreator.setVisible(false);
                        modComment.setVisible(false);
                        oldModComment[0] = "";

                        try {
                            Socket socket = new Socket("45.93.249.139", 3459);
                            OutputStream outputStream = socket.getOutputStream();
                            PrintStream printStream = new PrintStream(outputStream);
                            printStream.println("SAVE");
                            printStream.println("MOD_COMMENT_DELETE");
                            printStream.println(externalUser.getUsername());

                            externalUser.updateModComment("", "", null);
                        } catch (Exception ignored) {
                        }
                    }
                }
            }
        });

        JLabel banButton = new JLabel("Sperren");

        banButton.setBackground(new Color(0xD03F3F));
        banButton.setOpaque(true);
        banButton.setForeground(Color.WHITE);
        banButton.setHorizontalAlignment(SwingConstants.CENTER);
        banButton.setVerticalAlignment(SwingConstants.CENTER);
        banButton.setFont(new Font("Arial", Font.PLAIN, 25));

        banButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                banButton.setBackground(new Color(0xE04F4F));
                banButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                banButton.setBackground(new Color(0xD03F3F));
                banButton.setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (externalUser.getAccountRank().isTeam() && !account.getAccountRank().isAdmin_team()) {
                    saveFeedback.setText("Dieser User kann nicht gesperrt werden.");
                    saveFeedback.setVisible(true);
                } else {
                    windowHandler.openStaffDB(WindowHandler.WindowType.BanUser, externalUser);
                }
            }
        });

        JLabel warnButton = new JLabel("Verwarnen");

        warnButton.setBackground(new Color(0xD03F3F));
        warnButton.setOpaque(true);
        warnButton.setForeground(Color.WHITE);
        warnButton.setHorizontalAlignment(SwingConstants.CENTER);
        warnButton.setVerticalAlignment(SwingConstants.CENTER);
        warnButton.setFont(new Font("Arial", Font.PLAIN, 25));

        warnButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                warnButton.setBackground(new Color(0xE04F4F));
                warnButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                warnButton.setBackground(new Color(0xD03F3F));
                warnButton.setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (externalUser.getAccountRank().isTeam() && !account.getAccountRank().isAdmin_team()) {
                    saveFeedback.setText("Dieser User kann nicht verwarnt werden.");
                    saveFeedback.setVisible(true);
                } else {
                    windowHandler.openStaffDB(WindowHandler.WindowType.WarnUser, externalUser);
                }
            }
        });

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
        editorPaneBans.setText(banText);
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
        editorPaneBans.setCaretPosition(0);

        String warnsText = "<html><head><style>body {font-family: 'Trebuchet MS', sans-serif; font-size: 12px; color: white;}</style></head><body><center><hr>";

        if (externalUser.getWarns().isEmpty()) {
            warnsText = "";
            add(noWarnsFound);
        } else {
            for (Warn warn : externalUser.getWarns()) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
                warnsText += "<br>Verwarnt von: <font color=#" + warn.getOperatorColor() + ">" + warn.getOperator() + "</font><br>" +
                        "Verwarnt am: <font color=#ababab>" + dateFormat.format(new Date(warn.getTimestamp())) + " Uhr </font><br>" +
                        "Grund: <font color=#e04d3d>" + warn.getReason() + "</font><br>";
                if (warn.isCancelled()) {
                    warnsText += "<font color=gray>- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -</font><br>" +
                            "Aufgehoben von: <font color=#" + warn.getCancelledByColor() + ">" + warn.getCancelledBy() + "</font><br>" +
                            "Aufgehoben am: <font color=#ababab>" + dateFormat.format(new Date(warn.getCanncelledAt())) + "</font><br>" +
                            "Aufgebungsgrund: <font color=#e04d3d>" + warn.getCancelledReason() + "</font><br>";
                }

                warnsText += "<br><hr>";
            }
        }

        JEditorPane editorPaneWarns = new JEditorPane();

        editorPaneWarns.setContentType("text/html");
        editorPaneWarns.setText(warnsText);
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
        editorPaneWarns.setCaretPosition(0);

        titleLabel.setBounds(20, 10, 600, 36);
        username.setBounds(40, 150, 440, 20);
        rank.setBounds(40, 170, 440, 20);
        userDataBackground.setBounds(20, 130, 480, 85);
        modCommentCreator.setBounds(45, 265, 440, 20);
        modCommentEditable.setBounds(40, 285, 440, 75);
        scrollPaneWarns.setBounds(550, 510, 450, 210);
        noWarnsFound.setBounds(550, 510, 450, 210);
        scrollPaneBans.setBounds(550, 175, 450, 210);
        noBansFound.setBounds(550, 175, 450, 210);
        modComment.setBounds(45, 285, 435, 75);
        commentBackground.setBounds(40, 260, 440, 100);

        warnButton.setBounds(550, 730, 450, 35);
        banButton.setBounds(550, 395, 450, 35);
        changeCommentButton.setBounds(40, 375, 210, 35);
        deleteCommentButton.setBounds(270, 375, 210, 35);

        bansTitle.setBounds(550, 150, 450, 25);
        warnsTitle.setBounds(550, 485, 450, 25);
        bansBackground.setBounds(530, 130, 490, 315);
        warnsBackground.setBounds(530, 465, 490, 315);
        modCommentBackground.setBounds(20, 235, 480, 200);

        noCommentFound.setBounds(commentBackground.getBounds());

        add(modCommentEditable);
        add(bansTitle);
        add(warnsTitle);
        add(warnButton);
        add(banButton);
        add(changeCommentButton);
        add(deleteCommentButton);
        add(rank);
        add(username);
        add(modCommentCreator);
        add(noCommentFound);
        add(modComment);
        add(commentBackground);
        add(userDataBackground);
        add(bansBackground);
        add(warnsBackground);
        add(modCommentBackground);
    }
}
