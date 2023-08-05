package de.bot.windows;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class UpdateAvailable extends JPanel {

    public UpdateAvailable() {
        File file = new File("C:\\Users\\User\\Desktop\\update.png");
        setPreferredSize(new Dimension(1280, 720));
        setLayout(null);

        JLabel jcomp2 = new JLabel(new ImageIcon(file.getAbsolutePath()));
        add(jcomp2);

        jcomp2.setBounds(320, 177, 685, 365);
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("MyPanel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new UpdateAvailable()).setBackground(new Color(0x272727));
        frame.pack();
        frame.setVisible(true);
    }
}