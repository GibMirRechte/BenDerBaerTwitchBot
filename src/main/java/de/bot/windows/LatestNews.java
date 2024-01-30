package de.bot.windows;

import de.bot.handler.*;
import de.bot.utils.Account;
import de.bot.utils.Announcement;
import de.bot.utils.News;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class LatestNews extends JPanel {

    UpdateHandler updateHandler = UpdateHandler.getInstance();
    AccountHandler accountHandler = AccountHandler.getInstance();
    NewsHandler newsHandler = NewsHandler.getInstance();
    WindowHandler windowHandler = WindowHandler.getInstance();

    public LatestNews() {
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

        JLabel newsBackground = new JLabel();
        newsBackground.setBackground(new Color(0x1A1A1A));
        newsBackground.setVisible(true);
        newsBackground.setOpaque(true);
        newsBackground.setBorder(BorderFactory.createLineBorder(new Color(0x333333), 1));

        JLabel titleLabel = new JLabel("Aktuelle Nachrichten");
        titleLabel.setBounds(20, 10, 600, 36);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
        titleLabel.setVerticalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 36));
        add(titleLabel);

        List<News> latestNews = newsHandler.getNewestNews(6);

        for (int i = 0; i < latestNews.size(); i++) {
            if (!latestNews.get(i).isPublic()) continue;
            JLabel newsLabel = getNewsObject(latestNews.get(i));

            add(newsLabel);
            newsLabel.setBounds(getBounds(i + 1));
        }

        newsBackground.setBounds(20, 150, 980, 480);

        add(newsBackground);
    }

    public Rectangle getBounds(int newsIndex) {
        switch (newsIndex) {
            case 1:
                return new Rectangle(40, 170, 300, 210);
            case 2:
                return new Rectangle(360, 170, 300, 210);
            case 3:
                return new Rectangle(680, 170, 300, 210);
            case 4:
                return new Rectangle(40, 400, 300, 210);
            case 5:
                return new Rectangle(360, 400, 300, 210);
            case 6:
                return new Rectangle(680, 400, 300, 210);
            case 7:
                return new Rectangle(40, 600, 300, 210);
            case 8:
                return new Rectangle(360, 600, 300, 210);
            case 9:
                return new Rectangle(680, 600, 300, 210);
            default:
                return null;
        }
    }

    private JLabel getNewsObject(News news) {
        Border emptyBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);

        String formattedDate = new SimpleDateFormat("dd.MM.yyyy HH:mm 'Uhr'").format(new Date(news.getCreated()));
        JLabel newsLabel = new JLabel("<html><h2>" + news.getTitle() + "</h2><h3><font color=gray>" + formattedDate + "</font></h3><br>" + news.getNewsTeaser() + "<br><br><font color=#34c0eb>>> Klicke zum weiterlesen</font></html>");

        newsLabel.setForeground(Color.WHITE);
        newsLabel.setOpaque(true);
        newsLabel.setBorder(emptyBorder);
        newsLabel.setBackground(new Color(0x262626));
        newsLabel.setHorizontalAlignment(SwingConstants.LEFT);
        newsLabel.setVerticalAlignment(SwingConstants.CENTER);
        newsLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        newsLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                newsLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                newsLabel.setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                windowHandler.openNewsPage(news.getNewsKey());
            }
        });

        return newsLabel;
    }
}
