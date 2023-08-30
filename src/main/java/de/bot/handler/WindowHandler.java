package de.bot.handler;

import de.bot.main.Main;
import de.bot.windows.*;
import de.bot.windows.modules.Sidebar;

import javax.swing.*;
import java.awt.*;

public class WindowHandler {

    public static WindowType currentWindow;
    static JFrame frame = new JFrame();
    static WindowHandler instance;
    UpdateHandler updateHandler = UpdateHandler.getInstance();
    AccountHandler accountHandler = AccountHandler.getInstance();

    public enum WindowType {
        DASHBOARD(false), LOGIN(false), AUTOVIP(true), UPDATE(false), CHANGELOG(false),
        NOT_ALLOWED(false), AUTOSHOUT(false), STREAMS(false);

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

    public void openWindow(WindowType windowType) {
        Image icon = Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/icon.png"));

        frame.setVisible(true);

        if (frame != null) {
            frame.getContentPane().removeAll();
        }

        if (updateHandler.hasNewUpdate())
            windowType = WindowType.UPDATE;

        if (accountHandler.getAccount() == null) {
            windowType = WindowType.LOGIN;
        } else {
            if (accountHandler.getAccount().getAccountType().equals(AccountHandler.AccountType.NORMAL) && windowType.onlyVIP) {
                windowType = WindowType.NOT_ALLOWED;
            }
        }

        Sidebar sidebar = new Sidebar();
        sidebar.setVisible(true);
        sidebar.setBounds(0, 0, 255, 720);

        switch (windowType) {
            case DASHBOARD:
                currentWindow = WindowType.DASHBOARD;
                frame.getContentPane().add(sidebar, BorderLayout.WEST);
                frame.setTitle("TwitchBot " + updateHandler.getCurrentVersion() + " - Dashboard");
                frame.getContentPane().add(new Dashboard()).setBackground(new Color(0x272727));
                break;
            case LOGIN:
                currentWindow = WindowType.LOGIN;
                frame.setTitle("TwitchBot " + updateHandler.getCurrentVersion() + " - Login");
                frame.getContentPane().add(new Login()).setBackground(new Color(0x272727));
                break;
            case AUTOVIP:
                currentWindow = WindowType.AUTOVIP;
                frame.getContentPane().add(sidebar, BorderLayout.WEST);
                frame.setTitle("TwitchBot " + updateHandler.getCurrentVersion() + " - AutoVIP");
                frame.getContentPane().add(new AutoVIP()).setBackground(new Color(0x272727));
                break;
            case UPDATE:
                currentWindow = WindowType.UPDATE;
                frame.setTitle("TwitchBot " + updateHandler.getCurrentVersion() + " - Update vorhanden");
                frame.getContentPane().add(new UpdateAvailable()).setBackground(new Color(0x272727));
                break;
            case CHANGELOG:
                currentWindow = WindowType.CHANGELOG;
                frame.getContentPane().add(sidebar, BorderLayout.WEST);
                frame.setTitle("TwitchBot " + updateHandler.getCurrentVersion() + " - Changelog");
                frame.getContentPane().add(new Changelog()).setBackground(new Color(0x272727));
                break;
            case NOT_ALLOWED:
                currentWindow = WindowType.NOT_ALLOWED;
                frame.getContentPane().add(sidebar, BorderLayout.WEST);
                frame.setTitle("TwitchBot " + updateHandler.getCurrentVersion() + " - Verboten");
                frame.getContentPane().add(new NotAllowed()).setBackground(new Color(0x272727));
                break;
            case STREAMS:
                currentWindow = WindowType.STREAMS;
                frame.getContentPane().add(sidebar, BorderLayout.WEST);
                frame.setTitle("TwitchBot " + updateHandler.getCurrentVersion() + " - Featured Streams");
                frame.getContentPane().add(new Streams()).setBackground(new Color(0x272727));
                break;
            default:
                break;
        }

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(icon);
        frame.pack();
        frame.setResizable(false);
    }
}
