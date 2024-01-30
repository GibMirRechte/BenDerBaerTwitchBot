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

public class Dashboard extends JPanel {

    UpdateHandler updateHandler = UpdateHandler.getInstance();
    AccountHandler accountHandler = AccountHandler.getInstance();
    WindowHandler windowHandler = WindowHandler.getInstance();
    NewsHandler newsHandler = NewsHandler.getInstance();

    public Dashboard() {
        News news = newsHandler.getNewestNews(1).get(0);
        Account account = accountHandler.getAccount();
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

        JLabel welcomeText = new JLabel("Willkommen " + account.getName() + "!");
        JLabel newsBackground = new JLabel();
        JLabel accountInfoBackground = new JLabel();
        JLabel newsTitle = new JLabel("Aktuelle News");
        JLabel latestNews = new JLabel("<html><h2>" + news.getTitle() + "</h2><br>" + getNewsTeaser(news) + "<br><br><font color=#34c0eb>>> Klicke zum weiterlesen</font></html>");

        newsTitle.setForeground(Color.WHITE);
        newsTitle.setHorizontalAlignment(SwingConstants.CENTER);
        newsTitle.setVerticalAlignment(SwingConstants.CENTER);
        newsTitle.setFont(new Font("Arial", Font.PLAIN, 24));

        Border emptyBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);

        latestNews.setForeground(Color.WHITE);
        latestNews.setOpaque(true);
        latestNews.setBorder(emptyBorder);
        latestNews.setBackground(new Color(0x262626));
        latestNews.setHorizontalAlignment(SwingConstants.LEFT);
        latestNews.setVerticalAlignment(SwingConstants.CENTER);
        latestNews.setFont(new Font("Arial", Font.PLAIN, 14));

        newsBackground.setBackground(new Color(0x1A1A1A));
        newsBackground.setVisible(true);
        newsBackground.setOpaque(true);
        newsBackground.setBorder(BorderFactory.createLineBorder(new Color(0x333333), 1));

        accountInfoBackground.setBackground(new Color(0x1A1A1A));
        accountInfoBackground.setVisible(true);
        accountInfoBackground.setOpaque(true);
        accountInfoBackground.setBorder(BorderFactory.createLineBorder(new Color(0x333333), 1));

        add(welcomeText);
        add(announcement);
        welcomeText.setBounds(20, 10, 600, 36);
        welcomeText.setForeground(Color.WHITE);
        welcomeText.setHorizontalAlignment(SwingConstants.LEFT);
        welcomeText.setVerticalAlignment(SwingConstants.CENTER);
        welcomeText.setFont(new Font("Arial", Font.PLAIN, 36));

        JLabel accountInfo = new JLabel("<html>Benutzername: " + account.getName() + "</html>");
        JLabel rank = new JLabel("Rang: ");
        JLabel channelID = new JLabel("Channel-ID: " + account.getChannelID());

        rank.setForeground(Color.WHITE);
        rank.setBackground(new Color(0x262626));
        rank.setOpaque(true);
        rank.setBorder(emptyBorder);
        rank.setHorizontalAlignment(SwingConstants.LEFT);
        rank.setHorizontalTextPosition(SwingConstants.LEADING);
        rank.setIcon(new ImageIcon(accountHandler.getBadge(account.getAccountRank())));
        rank.setVerticalAlignment(SwingConstants.CENTER);
        rank.setFont(new Font("Arial", Font.PLAIN, 16));

        accountInfo.setForeground(Color.WHITE);
        accountInfo.setBackground(new Color(0x262626));
        accountInfo.setOpaque(true);
        accountInfo.setBorder(emptyBorder);
        accountInfo.setHorizontalAlignment(SwingConstants.LEFT);
        accountInfo.setVerticalAlignment(SwingConstants.CENTER);
        accountInfo.setFont(new Font("Arial", Font.PLAIN, 16));

        channelID.setForeground(Color.WHITE);
        channelID.setBackground(new Color(0x262626));
        channelID.setOpaque(true);
        channelID.setBorder(emptyBorder);
        channelID.setHorizontalAlignment(SwingConstants.LEFT);
        channelID.setVerticalAlignment(SwingConstants.CENTER);
        channelID.setFont(new Font("Arial", Font.PLAIN, 16));

        add(accountInfo);
        add(rank);
        add(newsTitle);
        add(latestNews);
        add(channelID);

        add(newsBackground);
        add(accountInfoBackground);

        accountInfo.setBounds(40, 170, 300, 20);
        channelID.setBounds(40, 190, 300, 20);
        rank.setBounds(40, 210, 300, 20);
        accountInfoBackground.setBounds(20, 150, 340, 100);

        newsTitle.setBounds(40, 556, 300, 30);
        latestNews.setBounds(40, 596, 300, 180);
        newsBackground.setBounds(20, 430, 340, 260);
        newsBackground.setBounds(20, 536, 340, 260);

        latestNews.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                latestNews.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                latestNews.setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                windowHandler.openNewsPage(news.getNewsKey());
            }
        });
    }

    public static String getNewsTeaser(News news) {
        if (news.getText().length() <= 100) {
            return news.getText().substring(0, news.getText().length() / 2) + "...";
        } else {
            return news.getText().substring(0, 100) + "...";
        }
    }
}
