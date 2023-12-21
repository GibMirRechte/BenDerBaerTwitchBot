package de.bot.windows;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;

public class Streams extends JPanel {

    public Streams() {
        setPreferredSize(new Dimension(1040, 816));
        setLayout(null);

        JLabel title = new JLabel("Empfohlene Streams");
        JLabel background = new JLabel();

        title.setVerticalAlignment(SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(SwingConstants.CENTER);

        background.setBackground(new Color(0x1A1A1A));
        background.setVisible(true);
        background.setOpaque(true);
        background.setBorder(BorderFactory.createLineBorder(new Color(0x333333), 1));

        add(title);

        title.setFont(new Font("Arial", Font.PLAIN, 25));

        Border border = BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        );
        title.setBorder(border);

        JLabel benDerBaer = new JLabel("<html><center><img src=https://static-cdn.jtvnw.net/jtv_user_pictures/23d9df34-f956-4f8c-bba9-361e19edd4a6-profile_image-70x70.png><br>" +
                "<br><font size=5>BenDerBaer</font><br><font size=3>● Gaming<br>● 3D-Design</font><br><br></center></html>");

        benDerBaer.setBackground(new Color(0x1E1E1E));
        benDerBaer.setOpaque(true);
        benDerBaer.setVerticalAlignment(SwingConstants.CENTER);
        benDerBaer.setForeground(Color.WHITE);
        benDerBaer.setHorizontalAlignment(SwingConstants.CENTER);
        benDerBaer.setFont(new Font("Arial", Font.PLAIN, 16));
        benDerBaer.setBorder(BorderFactory.createLineBorder(new Color(0x333333), 1));

        add(benDerBaer);

        benDerBaer.addMouseListener(addMouseAdapter(benDerBaer, "BenDerBaer"));

        JLabel coder2k = new JLabel("<html><center><img src=https://static-cdn.jtvnw.net/jtv_user_pictures/aa58e6a1-5b04-43e4-be45-fe0e4a15ebe3-profile_image-70x70.png><br>" +
                "<br><font size=5>coder2k</font><br><font size=3>● Softwareentwicklung</font><br><br></center></html>");

        coder2k.setBackground(new Color(0x1E1E1E));
        coder2k.setOpaque(true);
        coder2k.setVerticalAlignment(SwingConstants.CENTER);
        coder2k.setForeground(Color.WHITE);
        coder2k.setHorizontalAlignment(SwingConstants.CENTER);
        coder2k.setFont(new Font("Arial", Font.PLAIN, 16));
        coder2k.setBorder(BorderFactory.createLineBorder(new Color(0x333333), 1));

        add(coder2k);

        coder2k.setBounds(375, 120, 150, 200);
        coder2k.addMouseListener(addMouseAdapter(coder2k, "coder2k"));
        benDerBaer.setBounds(555, 120, 150, 200);
        title.setBounds(338, 70, 390, 30);

        background.setBounds(20, 50, 1000, 420);

        add(background);
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
