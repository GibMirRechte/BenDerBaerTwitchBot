package de.bot.handler;

import de.bot.main.Main;
import de.bot.windows.Dashboard;

import javax.swing.*;
import java.awt.*;

public class WindowHandler {

    private static final JFrame frame = new JFrame();
    public static WindowType currentWindow;
    UpdateHandler updateHandler = new UpdateHandler();

    public enum WindowType {
        DASHBOARD;
    }


    public void openWindow(WindowType windowType) {
        Image icon = Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/Screenshot_88.png"));

        frame.setVisible(true);

        switch (windowType) {
            case DASHBOARD:
                currentWindow = WindowType.DASHBOARD;
                frame.setTitle("TwitchBot " + updateHandler.getCurrentVersion() + " - Dashboard");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setIconImage(icon);
                frame.getContentPane().add(new Dashboard()).setBackground(new Color(0x272727));
                frame.pack();
                frame.setResizable(false);
                break;
            default:
                break;
        }
    }
}
