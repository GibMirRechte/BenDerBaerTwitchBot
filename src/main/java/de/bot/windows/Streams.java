package de.bot.windows;

import de.bot.handler.WindowHandler;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;

public class Streams extends JPanel {

    public Streams() {
        setPreferredSize(new Dimension(1025, 720));
        setLayout(null);

        JLabel title = new JLabel("Empfohlene Streams");

        title.setVerticalAlignment(SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(SwingConstants.CENTER);

        add(title);

        title.setBounds(338, 50, 350, 30);

        title.setFont(new Font("Trebuchet MS", Font.PLAIN, 25));

        Border border = BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        );
        title.setBorder(border);

        JLabel benDerBaer = new JLabel("<html><center><img src=https://static-cdn.jtvnw.net/jtv_user_pictures/23d9df34-f956-4f8c-bba9-361e19edd4a6-profile_image-70x70.png><br>" +
                "<br><h1>BenDerBaer</h1>● Gaming<br>● 3D-Design<br><br></center></html>");

        benDerBaer.setBackground(new Color(0x1E1E1E));
        benDerBaer.setOpaque(true);
        benDerBaer.setVerticalAlignment(SwingConstants.CENTER);
        benDerBaer.setForeground(Color.WHITE);
        benDerBaer.setHorizontalAlignment(SwingConstants.CENTER);
        benDerBaer.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));

        add(benDerBaer);

        benDerBaer.setBounds(410, 100, 200, 250);
        benDerBaer.addMouseListener(addMouseAdapter(benDerBaer, "BenDerBaer"));
/*
        JLabel hakuInu = new JLabel("<html><center><img src=https://static-cdn.jtvnw.net/jtv_user_pictures/23d9df34-f956-4f8c-bba9-361e19edd4a6-profile_image-70x70.png><br>" +
                "<br><h1>HAKUINU</h1>● 3D-Design<br><br></center></html>");

        hakuInu.setBackground(new Color(0x1E1E1E));
        hakuInu.setOpaque(true);
        hakuInu.setVerticalAlignment(SwingConstants.CENTER);
        hakuInu.setForeground(Color.WHITE);
        hakuInu.setHorizontalAlignment(SwingConstants.CENTER);
        hakuInu.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));

        add(hakuInu);

        hakuInu.setBounds(220, 10, 200, 250);
        hakuInu.addMouseListener(addMouseAdapter(hakuInu, "hakuInu"));*/
    }

    public static void main(String[] args) {
        WindowHandler.getInstance().openWindow(WindowHandler.WindowType.STREAMS);
    }

    public MouseAdapter addMouseAdapter(JLabel jLabel, String twitchName) {
        return new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                jLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jLabel.setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("https://www.twitch.tv/" + twitchName));
                } catch (Exception ignored) {
                }
            }
        };
    }
}
