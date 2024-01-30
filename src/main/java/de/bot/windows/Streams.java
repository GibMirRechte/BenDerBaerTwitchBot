package de.bot.windows;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

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
        Border descriptionBorder = BorderFactory.createEmptyBorder(10, 20, 10, 20);

        JLabel benDerBaer_leftbox = new JLabel("<html><center><img src=https://static-cdn.jtvnw.net/jtv_user_pictures/23d9df34-f956-4f8c-bba9-361e19edd4a6-profile_image-70x70.png><br>" +
                "<br><font size=5>BenDerBaer</font><br><font size=3>● Gaming<br>● 3D-Design</font><br><br></center></html>");

        benDerBaer_leftbox.setBackground(new Color(0x1E1E1E));
        benDerBaer_leftbox.setOpaque(true);
        benDerBaer_leftbox.setVerticalAlignment(SwingConstants.CENTER);
        benDerBaer_leftbox.setForeground(Color.WHITE);
        benDerBaer_leftbox.setHorizontalAlignment(SwingConstants.CENTER);
        benDerBaer_leftbox.setFont(new Font("Arial", Font.PLAIN, 16));
        benDerBaer_leftbox.setBorder(BorderFactory.createLineBorder(new Color(0x333333), 1));

        JTextArea benDerBaer_description = new JTextArea("BenDerBaer nimmt euch mit auf aufregende Abenteuer in Spielen wie Sons of the Forest und zeigt euch die besten Gaming-Momente.\n" +
                "\n" +
                "Aber das ist noch nicht alles! BenDerBaer überrascht euch auch mit seiner kreativen Ader, indem er in Blender kleine Kunstwerke erschafft – hier gibt es mehr als nur Gaming!\n" +
                "\n" +
                "Also schnappt euch eure Controller und Pinsel, denn in BenDerBaer's Stream erlebt ihr die perfekte Mischung aus Gaming-Action und künstlerischer Inspiration.");
        benDerBaer_description.setForeground(Color.WHITE);
        benDerBaer_description.setEditable(false);
        benDerBaer_description.setBackground(new Color(0x1E1E1E));
        benDerBaer_description.setLineWrap(true);
        benDerBaer_description.setWrapStyleWord(true);
        benDerBaer_description.setBorder(descriptionBorder);
        benDerBaer_description.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel benDerBaer_border = new JLabel();
        benDerBaer_border.setBackground(new Color(0x1A1A1A));
        benDerBaer_border.setVisible(true);
        benDerBaer_border.setOpaque(true);
        benDerBaer_border.setBorder(BorderFactory.createLineBorder(new Color(0x333333), 1));

        JLabel coder2k_leftbox = new JLabel("<html><center><img src=https://static-cdn.jtvnw.net/jtv_user_pictures/aa58e6a1-5b04-43e4-be45-fe0e4a15ebe3-profile_image-70x70.png><br>" +
                "<br><font size=5>coder2k</font><br><font size=3>● Softwareentwicklung</font><br><br></center></html>");

        coder2k_leftbox.setBackground(new Color(0x1E1E1E));
        coder2k_leftbox.setOpaque(true);
        coder2k_leftbox.setVerticalAlignment(SwingConstants.CENTER);
        coder2k_leftbox.setForeground(Color.WHITE);
        coder2k_leftbox.setHorizontalAlignment(SwingConstants.CENTER);
        coder2k_leftbox.setFont(new Font("Arial", Font.PLAIN, 16));
        coder2k_leftbox.setBorder(BorderFactory.createLineBorder(new Color(0x333333), 1));

        JTextArea coder2k_description = new JTextArea("Hier entwickelt coder2k mit einem Hang zur Softwareentwicklung seine eigene Programmiersprache und häuft eine beeindruckende Sammlung von Compilern an.\nUnd bevor ein Compiler fertig ist, wird bereits der nächste in Angriff genommen – eine echte Tradition!\n\nAber keine Sorge, hier herrscht nie Mangel an Humor und lehrreichen Inhalten!\nOb Anfänger oder Profi, hier ist für jeden etwas dabei.");
        coder2k_description.setForeground(Color.WHITE);
        coder2k_description.setEditable(false);
        coder2k_description.setBackground(new Color(0x1E1E1E));
        coder2k_description.setLineWrap(true);
        coder2k_description.setWrapStyleWord(true);
        coder2k_description.setBorder(descriptionBorder);
        coder2k_description.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel coder2k_border = new JLabel();
        coder2k_border.setBackground(new Color(0x1A1A1A));
        coder2k_border.setVisible(true);
        coder2k_border.setOpaque(true);
        coder2k_border.setBorder(BorderFactory.createLineBorder(new Color(0x333333), 1));

        coder2k_leftbox.setBounds(60, 140, 150, 200);
        coder2k_description.setBounds(250, 140, 500, 200);
        benDerBaer_leftbox.setBounds(60, 400, 150, 200);
        benDerBaer_description.setBounds(250, 400, 500, 200);
        title.setBounds(338, 70, 390, 30);

        background.setBounds(20, 50, 1000, 590);
        coder2k_border.setBounds(40, 120, 960, 240);
        benDerBaer_border.setBounds(40, 380, 960, 240);

        add(coder2k_leftbox);
        add(coder2k_description);
        add(getButton("coder2k", 780, 140));
        add(getButton("benderbaer", 780, 400));
        add(coder2k_border);
        add(benDerBaer_leftbox);
        add(benDerBaer_description);
        add(benDerBaer_border);

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

    public JLabel getButton(String username, int x, int y) {
        JLabel streamButton = new JLabel("<html>Zum Stream ➔</html>");
        streamButton.setBackground(new Color(0x1E5E8B));
        streamButton.setOpaque(true);
        streamButton.setForeground(Color.WHITE);
        streamButton.setHorizontalAlignment(SwingConstants.CENTER);
        streamButton.setVerticalAlignment(SwingConstants.CENTER);
        streamButton.setFont(new Font("Arial", Font.PLAIN, 18));

        streamButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                streamButton.setBackground(new Color(0x236C9F));
                streamButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                streamButton.setBackground(new Color(0x1E5E8B));
                streamButton.setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("https://twitch.tv/" + username));
                } catch (IOException | URISyntaxException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        streamButton.setBounds(x, y, 200, 30);

        return streamButton;
    }
}
