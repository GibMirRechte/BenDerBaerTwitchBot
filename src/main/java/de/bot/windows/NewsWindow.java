package de.bot.windows;

import de.bot.handler.AccountHandler;
import de.bot.handler.ImageIconHandler;
import de.bot.handler.UpdateHandler;
import de.bot.utils.Announcement;
import de.bot.utils.News;
import de.bot.windows.modules.CustomScrollBar;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.PrintStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewsWindow extends JPanel {

    UpdateHandler updateHandler = UpdateHandler.getInstance();
    AccountHandler accountHandler = AccountHandler.getInstance();

    public NewsWindow(News news) {
        Announcement an = updateHandler.getAnnouncement();

        setPreferredSize(new Dimension(1040, 816));
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
        announcement.setFont(new Font("Arial", Font.PLAIN, 14));

        announcement.setBounds(183, 75, 660, 40);
        add(announcement);

        JLabel title = new JLabel(news.getTitle());
        JLabel autor = new JLabel("<html>Gepostet von <font color=" + news.getCreatorAccountRank().getHtmlColor() + ">" + news.getCreator() + "</font></html>");
        JLabel background = new JLabel();
        JLabel date = new JLabel();

        Border emptyBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);

        add(title);
        add(autor);

        String formattedDate = new SimpleDateFormat("dd.MM.yyyy HH:mm 'Uhr'").format(new Date(news.getCreated()));

        date.setText(formattedDate);
        date.setForeground(Color.GRAY);
        date.setVerticalAlignment(SwingConstants.CENTER);
        date.setHorizontalAlignment(SwingConstants.CENTER);
        date.setFont(new Font("Arial", Font.ITALIC, 15));

        background.setBackground(new Color(0x1A1A1A));
        background.setVisible(true);
        background.setOpaque(true);
        background.setBorder(BorderFactory.createLineBorder(new Color(0x333333), 1));


        String labelText = news.getText();

        JTextArea changelog = new JTextArea(labelText);
        changelog.setEditable(false);
        changelog.setLineWrap(true);
        changelog.setWrapStyleWord(true);
        changelog.setForeground(Color.WHITE);
        changelog.setBorder(emptyBorder);
        changelog.setBackground(new Color(0x272727));
        changelog.setFont(new Font("Arial", Font.PLAIN, 18));

        JScrollPane scrollPane = new JScrollPane(changelog);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUI(new CustomScrollBar());
        scrollPane.getHorizontalScrollBar().setUI(new CustomScrollBar());
        scrollPane.getViewport().setBackground(Color.BLUE);
        scrollPane.getViewport().setOpaque(true);

        add(scrollPane);

        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setVerticalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.PLAIN, 25));

        autor.setForeground(Color.GRAY);
        autor.setHorizontalAlignment(SwingConstants.CENTER);
        autor.setVerticalAlignment(SwingConstants.CENTER);
        autor.setFont(new Font("Arial", Font.ITALIC, 15));
        autor.setIcon(new ImageIcon(accountHandler.getBadge(news.getCreatorAccountRank())));
        autor.setHorizontalTextPosition(SwingConstants.LEADING);
        autor.setIconTextGap(5);

        Border border = BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        );
        title.setBorder(border);

        title.setBounds(338, 158, 350, 30);
        autor.setBounds(312, 193, 400, 20);
        date.setBounds(412, 215, 200, 20);
        scrollPane.setBounds(40, 245, 960, 530);
        background.setBounds(20, 133, 1000, 662);

        if (accountHandler.getAccount().getAccountRank().isAdmin_team()) {
            JLabel editButton = new JLabel("Bearbeiten");
            editButton.setBounds(788, 158, 200, 35);
            editButton.setBackground(new Color(0x1E5E8B));
            editButton.setOpaque(true);
            editButton.setForeground(Color.WHITE);
            editButton.setHorizontalAlignment(SwingConstants.CENTER);
            editButton.setVerticalAlignment(SwingConstants.CENTER);
            editButton.setFont(new Font("Arial", Font.PLAIN, 28));

            JLabel cancelButton = new JLabel("Abbrechen");
            cancelButton.setBackground(new Color(0xD03F3F));
            cancelButton.setBounds(788, 198, 200, 35);
            cancelButton.setOpaque(true);
            cancelButton.setVisible(false);
            cancelButton.setForeground(Color.WHITE);
            cancelButton.setHorizontalAlignment(SwingConstants.CENTER);
            cancelButton.setVerticalAlignment(SwingConstants.CENTER);
            cancelButton.setFont(new Font("Arial", Font.PLAIN, 28));

            JTextArea editArea = new JTextArea("<html>" + changelog.getText() + "</html>");
            editArea.setBounds(scrollPane.getBounds());
            editArea.setForeground(Color.WHITE);
            editArea.setBackground(new Color(0x262626));
            editArea.setVisible(false);
            editArea.setEditable(true);
            editArea.setBorder(emptyBorder);
            editArea.setFont(new Font("Arial", Font.PLAIN, 18));

            JScrollPane scrollPaneEditable = new JScrollPane(editArea);
            scrollPaneEditable.setBounds(scrollPane.getBounds());
            scrollPaneEditable.setBorder(null);
            scrollPaneEditable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPaneEditable.getVerticalScrollBar().setUI(new CustomScrollBar());
            scrollPaneEditable.getHorizontalScrollBar().setUI(new CustomScrollBar());
            scrollPaneEditable.getViewport().setBackground(Color.BLUE);
            scrollPaneEditable.getViewport().setOpaque(true);

            cancelButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    cancelButton.setBackground(new Color(0xE04F4F));
                    cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    cancelButton.setBackground(new Color(0xD03F3F));
                    cancelButton.setCursor(Cursor.getDefaultCursor());
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    editButton.setText("Bearbeiten");
                    editArea.setVisible(false);
                    scrollPane.setVisible(true);
                    cancelButton.setVisible(false);
                }
            });

            editButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    editButton.setBackground(new Color(0x236C9F));
                    editButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    editButton.setBackground(new Color(0x1E5E8B));
                    editButton.setCursor(Cursor.getDefaultCursor());
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    if (editArea.isVisible()) {
                        new Thread(() -> {
                            try {
                                Socket socket = new Socket("45.93.249.139", 3459);
                                PrintStream printStream = new PrintStream(socket.getOutputStream());
                                String text = editArea.getText();
                                printStream.println("NEWS");
                                printStream.println(accountHandler.getAccount().getName());
                                printStream.println("EDIT");
                                printStream.println(news.getNewsKey());
                                printStream.println(title.getText());
                                printStream.println(text.replace("\n", "&&&-"));
                                socket.close();
                                news.setTitle(title.getText());
                                news.setText(text);
                            } catch (Exception exc) {
                                exc.printStackTrace();
                            }
                        }).start();
                        editButton.setText("Bearbeiten");
                        editArea.setVisible(false);
                        scrollPane.setVisible(true);
                        changelog.setText(editArea.getText());
                        cancelButton.setVisible(false);
                    } else {
                        editButton.setText("Speichern");
                        editArea.setText(changelog.getText());
                        editArea.setVisible(true);
                        scrollPane.setVisible(false);
                        cancelButton.setVisible(true);
                    }
                }
            });

            add(cancelButton);
            add(editButton);
            add(scrollPaneEditable);
        }

        add(date);
        add(background);
    }
}
