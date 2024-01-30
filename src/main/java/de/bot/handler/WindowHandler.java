package de.bot.handler;

import de.bot.main.Main;
import de.bot.utils.ExternalUser;
import de.bot.utils.News;
import de.bot.utils.ToolData;
import de.bot.windows.*;
import de.bot.windows.admindashboard.AdminDash_NewsManagement;
import de.bot.windows.admindashboard.AdminDash_Start;
import de.bot.windows.admindashboard.news.AdminDash_CreateNews;
import de.bot.windows.admindashboard.news.AdminDash_EditNews_Search;
import de.bot.windows.admindashboard.rankmanagement.AdminDash_CreateRank;
import de.bot.windows.admindashboard.usermanagement.*;
import de.bot.windows.modules.Sidebar;
import de.bot.windows.staffdashboard.StaffDash_BanUser;
import de.bot.windows.staffdashboard.StaffDash_Start;
import de.bot.windows.staffdashboard.StaffDash_Userinfo;
import de.bot.windows.staffdashboard.StaffDash_WarnUser;

import javax.swing.*;
import java.awt.*;

public class WindowHandler {

    static JFrame frame = new JFrame();
    static WindowHandler instance;
    static News currentNews;
    static ExternalUser currentExternalUser;
    UpdateHandler updateHandler = UpdateHandler.getInstance();
    AccountHandler accountHandler = AccountHandler.getInstance();

    private Image icon = Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/icon.png"));


    public enum WindowType {
        DASHBOARD(false), AUTOMESSAGES(false), LOGIN(false), AUTOVIP(true), UPDATE(false),
        NOT_ALLOWED(false), AUTOSHOUT(false), STREAMS(false), SETTINGS(false), ADMINDASHBOARD(false), STAFFDASHBOARD(false),
        FEEDBACK(false), UserInfo(false), BanUser(false), WarnUser(false), SUPPORT(false),
        PRIVATE_MESSAGES(false), ADMINDASH_USERMANAGEMENT(false), ADMINDASH_NEWSMANAGEMENT(false), ADMINDASH_CREATENEWS(false), ADMINDASH_CREATERANK(false),
        ADMINDASH_CREATEUSER(false), NEWS(false), ADMINDASH_EDITUSER(false), ADMINDASH_EDITUSER_SEARCH(false),
        ADMINDASH_EDITNEWS_SEARCH(false), NEWEST_NEWS(false), CUSTOMCOMMANDS(false), REGISTER(false), ADMINDASH_REGISTERSETTINGS(false);

        public boolean onlyVIP;

        WindowType(boolean onlyVIP) {
            this.onlyVIP = onlyVIP;
        }
    }

    public static WindowHandler getInstance() {
        if (instance == null)
            instance = new WindowHandler();
        return instance;
    }

    public void openStaffDB(WindowType staffDBType, ExternalUser externalUser) {
        if (accountHandler.getAccount() == null) {
            openWindow(WindowType.LOGIN);
            return;
        }
        if (accountHandler.getAccount().getAccountRank().isTeam()) {
            frame.setVisible(true);

            if (frame != null) {
                frame.getContentPane().removeAll();
            }

            if (updateHandler.hasNewUpdate()) {
                openWindow(WindowType.UPDATE);
                return;
            }

            Sidebar sidebar = new Sidebar(staffDBType);

            switch (staffDBType) {
                case UserInfo ->
                        frame.getContentPane().add(new StaffDash_Userinfo(externalUser)).setBackground(new Color(0x0A0A0C));
                case BanUser ->
                        frame.getContentPane().add(new StaffDash_BanUser(externalUser)).setBackground(new Color(0x0A0A0C));
                case WarnUser ->
                        frame.getContentPane().add(new StaffDash_WarnUser(externalUser)).setBackground(new Color(0x0A0A0C));
            }

            frame.setTitle("TwitchBot " + updateHandler.getCurrentVersion() + " - Userinfo");
            frame.getContentPane().add(sidebar, BorderLayout.WEST);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setIconImage(icon);
            frame.pack();
            frame.setResizable(false);
        } else {
            openWindow(WindowType.DASHBOARD);
        }
    }

    public void openWindow(WindowType windowType) {
        ToolData toolData = updateHandler.getToolData();

        frame.setVisible(true);

        if (frame != null) {
            frame.getContentPane().removeAll();
        }

        Sidebar sidebar = null;

        if (updateHandler.hasNewUpdate())
            windowType = WindowType.UPDATE;

        if (accountHandler.getAccount() == null && windowType != WindowType.UPDATE && windowType != WindowType.REGISTER) {
            windowType = WindowType.LOGIN;
        } else {
            if (accountHandler.getAccount() != null) {
                sidebar = new Sidebar(windowType);
                sidebar.setVisible(true);
                sidebar.setBounds(0, 0, 255, 720);

                if (!accountHandler.getAccount().getAccountRank().isVip() && windowType.onlyVIP) {
                    windowType = WindowType.NOT_ALLOWED;
                }
            }
        }

        switch (windowType) {
            case DASHBOARD -> {
                frame.getContentPane().add(sidebar, BorderLayout.WEST);
                frame.setTitle("TwitchBot " + updateHandler.getCurrentVersion() + " - Dashboard");
                frame.getContentPane().add(new Dashboard()).setBackground(new Color(0x0A0A0C));
            }
            case LOGIN -> {
                frame.setTitle("TwitchBot " + updateHandler.getCurrentVersion() + " - Login");
                frame.getContentPane().add(new Login()).setBackground(new Color(0x0A0A0C));
            }
            case AUTOVIP -> {
                frame.getContentPane().add(sidebar, BorderLayout.WEST);
                frame.setTitle("TwitchBot " + updateHandler.getCurrentVersion() + " - AutoVIP");
                if (toolData.isMaintenance_autovip()) {
                    frame.getContentPane().add(new Maintenance("AutoVIP")).setBackground(new Color(0x0A0A0C));
                } else {
                    frame.getContentPane().add(new AutoVIP()).setBackground(new Color(0x0A0A0C));
                }
            }
            case UPDATE -> {
                frame.setTitle("TwitchBot " + updateHandler.getCurrentVersion() + " - Update verfügbar");
                frame.getContentPane().add(new UpdateAvailable()).setBackground(new Color(0x0A0A0C));
            }
            case NOT_ALLOWED -> {
                frame.getContentPane().add(sidebar, BorderLayout.WEST);
                frame.setTitle("TwitchBot " + updateHandler.getCurrentVersion() + " - Verboten");
                frame.getContentPane().add(new NotAllowed()).setBackground(new Color(0x0A0A0C));
            }
            case STREAMS -> {
                frame.getContentPane().add(sidebar, BorderLayout.WEST);
                frame.setTitle("TwitchBot " + updateHandler.getCurrentVersion() + " - Featured Streams");
                frame.getContentPane().add(new Streams()).setBackground(new Color(0x0A0A0C));
            }
            case AUTOSHOUT -> {
                frame.getContentPane().add(sidebar, BorderLayout.WEST);
                frame.setTitle("TwitchBot " + updateHandler.getCurrentVersion() + " - AutoShout");
                if (toolData.isMaintenance_autoshout()) {
                    frame.getContentPane().add(new Maintenance("AutoShout")).setBackground(new Color(0x0A0A0C));
                } else {
                    frame.getContentPane().add(new AutoShout()).setBackground(new Color(0x0A0A0C));
                }
            }
            case SETTINGS -> {
                frame.getContentPane().add(sidebar, BorderLayout.WEST);
                frame.setTitle("TwitchBot " + updateHandler.getCurrentVersion() + " - Einstellungen");
                frame.getContentPane().add(new Settings()).setBackground(new Color(0x0A0A0C));
            }
            case AUTOMESSAGES -> {
                frame.getContentPane().add(sidebar, BorderLayout.WEST);
                frame.setTitle("TwitchBot " + updateHandler.getCurrentVersion() + " - AutoMessages");
                frame.getContentPane().add(new AutoMessages()).setBackground(new Color(0x0A0A0C));
            }

            case FEEDBACK -> {
                frame.getContentPane().add(sidebar, BorderLayout.WEST);
                frame.setTitle("TwitchBot " + updateHandler.getCurrentVersion() + " - Feedback");
                frame.getContentPane().add(new Feedback()).setBackground(new Color(0x0A0A0C));
            }
            case ADMINDASHBOARD -> {
                if (!accountHandler.getAccount().getAccountRank().isAdmin_team()) {
                    openWindow(WindowType.DASHBOARD);
                } else {
                    frame.getContentPane().add(sidebar, BorderLayout.WEST);
                    frame.setTitle("TwitchBot " + updateHandler.getCurrentVersion() + " - Admin-Dashboard");
                    frame.getContentPane().add(new AdminDash_Start()).setBackground(new Color(0x0A0A0C));
                }
            }
            case STAFFDASHBOARD -> {
                if (!accountHandler.getAccount().getAccountRank().isTeam()) {
                    openWindow(WindowType.DASHBOARD);
                } else {
                    frame.getContentPane().add(sidebar, BorderLayout.WEST);
                    frame.setTitle("TwitchBot " + updateHandler.getCurrentVersion() + " - Staff-Dashboard");
                    frame.getContentPane().add(new StaffDash_Start()).setBackground(new Color(0x0A0A0C));
                }
            }
            case ADMINDASH_USERMANAGEMENT -> {
                if (!accountHandler.getAccount().getAccountRank().isAdmin_team()) {
                    openWindow(WindowType.DASHBOARD);
                } else {
                    frame.getContentPane().add(sidebar, BorderLayout.WEST);
                    frame.setTitle("TwitchBot " + updateHandler.getCurrentVersion() + " - Benutzerverwaltung");
                    frame.getContentPane().add(new AdminDash_UserManagement()).setBackground(new Color(0x0A0A0C));
                }
            }
            case ADMINDASH_NEWSMANAGEMENT -> {
                if (!accountHandler.getAccount().getAccountRank().isAdmin_team()) {
                    openWindow(WindowType.DASHBOARD);
                } else {
                    frame.getContentPane().add(sidebar, BorderLayout.WEST);
                    frame.setTitle("TwitchBot " + updateHandler.getCurrentVersion() + " - Newsverwaltung");
                    frame.getContentPane().add(new AdminDash_NewsManagement()).setBackground(new Color(0x0A0A0C));
                }
            }
            case ADMINDASH_CREATENEWS -> {
                if (!accountHandler.getAccount().getAccountRank().isAdmin_team()) {
                    openWindow(WindowType.DASHBOARD);
                } else {
                    frame.getContentPane().add(sidebar, BorderLayout.WEST);
                    frame.setTitle("TwitchBot " + updateHandler.getCurrentVersion() + " - News erstellen");
                    frame.getContentPane().add(new AdminDash_CreateNews()).setBackground(new Color(0x0A0A0C));
                }
            }
            case ADMINDASH_CREATEUSER -> {
                if (!accountHandler.getAccount().getAccountRank().isAdmin_team()) {
                    openWindow(WindowType.DASHBOARD);
                } else {
                    frame.getContentPane().add(sidebar, BorderLayout.WEST);
                    frame.setTitle("TwitchBot " + updateHandler.getCurrentVersion() + " - Benutzer erstellen");
                    frame.getContentPane().add(new AdminDash_CreateUser()).setBackground(new Color(0x0A0A0C));
                }
            }
            case ADMINDASH_CREATERANK -> {
                if (!accountHandler.getAccount().getAccountRank().isAdmin_team()) {
                    openWindow(WindowType.DASHBOARD);
                } else {
                    frame.getContentPane().add(sidebar, BorderLayout.WEST);
                    frame.setTitle("TwitchBot " + updateHandler.getCurrentVersion() + " - Rang erstellen");
                    frame.getContentPane().add(new AdminDash_CreateRank()).setBackground(new Color(0x0A0A0C));
                }
            }
            case NEWS -> {
                frame.getContentPane().add(sidebar, BorderLayout.WEST);
                frame.setTitle("TwitchBot " + updateHandler.getCurrentVersion() + " - " + currentNews.getTitle());
                frame.getContentPane().add(new NewsWindow(currentNews)).setBackground(new Color(0x0A0A0C));
            }
            case ADMINDASH_EDITUSER -> {
                if (!accountHandler.getAccount().getAccountRank().isAdmin_team()) {
                    openWindow(WindowType.DASHBOARD);
                } else {
                    frame.getContentPane().add(sidebar, BorderLayout.WEST);
                    frame.setTitle("TwitchBot " + updateHandler.getCurrentVersion() + " - Benutzer bearbeiten");
                    frame.getContentPane().add(new AdminDash_EditUser(currentExternalUser)).setBackground(new Color(0x0A0A0C));
                }
            }
            case ADMINDASH_EDITUSER_SEARCH -> {
                if (!accountHandler.getAccount().getAccountRank().isAdmin_team()) {
                    openWindow(WindowType.DASHBOARD);
                } else {
                    frame.getContentPane().add(sidebar, BorderLayout.WEST);
                    frame.setTitle("TwitchBot " + updateHandler.getCurrentVersion() + " - Benutzer suchen");
                    frame.getContentPane().add(new AdminDash_EditUser_Search()).setBackground(new Color(0x0A0A0C));
                }
            }
            case ADMINDASH_EDITNEWS_SEARCH -> {
                if (!accountHandler.getAccount().getAccountRank().isAdmin_team()) {
                    openWindow(WindowType.DASHBOARD);
                } else {
                    frame.getContentPane().add(sidebar, BorderLayout.WEST);
                    frame.setTitle("TwitchBot " + updateHandler.getCurrentVersion() + " - News suchen");
                    frame.getContentPane().add(new AdminDash_EditNews_Search()).setBackground(new Color(0x0A0A0C));
                }
            }
            case NEWEST_NEWS -> {
                frame.getContentPane().add(sidebar, BorderLayout.WEST);
                frame.setTitle("TwitchBot " + updateHandler.getCurrentVersion() + " - Aktuelle Nachrichten");
                frame.getContentPane().add(new LatestNews()).setBackground(new Color(0x0A0A0C));
            }
            case CUSTOMCOMMANDS -> {
                frame.getContentPane().add(sidebar, BorderLayout.WEST);
                frame.setTitle("TwitchBot " + updateHandler.getCurrentVersion() + " - Aktuelle Nachrichten");
                frame.getContentPane().add(new Maintenance("Custom Commands")).setBackground(new Color(0x0A0A0C));
            }
            case REGISTER -> {
                frame.setTitle("TwitchBot " + updateHandler.getCurrentVersion() + " - Registrieren");
                frame.getContentPane().add(new Register()).setBackground(new Color(0x0A0A0C));
            }
            case ADMINDASH_REGISTERSETTINGS -> {
                frame.getContentPane().add(sidebar, BorderLayout.WEST);
                frame.setTitle("TwitchBot " + updateHandler.getCurrentVersion() + " - Registriereinstellungen");
                frame.getContentPane().add(new AdminDash_RegisterSettings()).setBackground(new Color(0x0A0A0C));
            }
            default -> {
                System.err.println("Folgender Window-Type ist nicht definiert: " + windowType + "!");
            }
        }

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(icon);
        frame.pack();
        frame.setResizable(false);
    }

    public void openEditUser(ExternalUser externalUser) {
        currentExternalUser = externalUser;
        openWindow(WindowType.ADMINDASH_EDITUSER);
    }


    public void openNewsPage(String newsKey) {
        currentNews = NewsHandler.getInstance().getNews(newsKey);
        openWindow(WindowType.NEWS);
    }
}