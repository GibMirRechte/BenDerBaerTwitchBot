package de.bot.handler;

import de.bot.main.Main;
import de.bot.windows.Dashboard;
import de.bot.windows.Login;

import javax.swing.*;
import java.awt.*;

public class WindowHandler {

    private static final JFrame frame = new JFrame();
    public static WindowType currentWindow;
    static WindowHandler instance;
    UpdateHandler updateHandler = UpdateHandler.getInstance();

    public enum WindowType {
        DASHBOARD, LOGIN;
    }

    public static WindowHandler getInstance() {
        if (instance == null)
            instance = new WindowHandler();
        return instance;
    }


    public void openWindow(WindowType windowType) {
        Image icon = Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/Screenshot_88.png"));

        frame.setVisible(true);

        switch (windowType) {
            case DASHBOARD:
                currentWindow = WindowType.DASHBOARD;
                frame.setTitle("TwitchBot " + updateHandler.getCurrentVersion() + " - Dashboard");
                frame.getContentPane().add(new Dashboard()).setBackground(new Color(0x272727));
                break;
            case LOGIN:
                currentWindow = WindowType.DASHBOARD;
                frame.setTitle("TwitchBot " + updateHandler.getCurrentVersion() + " - Login");
                frame.getContentPane().add(new Login()).setBackground(new Color(0x272727));
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
