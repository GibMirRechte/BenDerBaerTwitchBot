package de.bot.windows;

import de.bot.elements.RoundedJPasswordField;
import de.bot.elements.RoundedJTextField;
import de.bot.handler.ImageIconHandler;
import de.bot.handler.UpdateHandler;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login extends JPanel {

    UpdateHandler updateHandler = new UpdateHandler();

    public Login() {
        setPreferredSize(new Dimension(1280, 720));
        setLayout(null);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel register = new JLabel("Registrieren");
        JLabel logo = new JLabel(ImageIconHandler.imageType.LOGO_NORMAL.imageIcon);
        JLabel login = new JLabel("Login");
        JLabel messageField = new JLabel("<html><center>GESPERRT!<br><br>Dieser Account ist zurzeit gesperrt!<br>Grund: Unerlaubtes Bugusing<br>Dauer: Dauerhaft</center></html>");
        RoundedJPasswordField password = new RoundedJPasswordField(10, new Color(0x272727), true, 40, 40);
        RoundedJTextField username = new RoundedJTextField(10, new Color(0x272727), true, 40, 40);

        login.setBackground(new Color(0x113F67));
        login.setForeground(Color.WHITE);
        login.setHorizontalAlignment(SwingConstants.CENTER);
        login.setVerticalAlignment(SwingConstants.CENTER);
        login.setFont(new Font("Oswald Medium", Font.PLAIN, 36));

        register.setBackground(new Color(0x113F67));
        register.setForeground(Color.WHITE);
        register.setHorizontalAlignment(SwingConstants.CENTER);
        register.setVerticalAlignment(SwingConstants.CENTER);
        register.setFont(new Font("Oswald Medium", Font.PLAIN, 36));

        username.setBackground(new Color(0x464646));
        username.setForeground(Color.GRAY);
        username.setText("Benutzername");
        username.setFont(new Font("Oswald Medium", Font.BOLD, 20));

        ((AbstractDocument) username.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                int currentLength = fb.getDocument().getLength();
                int maxLength = 16;

                if (currentLength - length + text.length() <= maxLength) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });

        ((AbstractDocument) password.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                int currentLength = fb.getDocument().getLength();
                int maxLength = 16;

                if (currentLength - length + text.length() <= maxLength) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });
        username.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (username.getText().equals("Benutzername")) {
                    username.setText("");
                    username.setForeground(Color.WHITE);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (username.getText().isEmpty()) {
                    username.setText("Benutzername");
                    username.setForeground(Color.GRAY);
                }
            }
        });

        password.setBackground(new Color(0x464646));
        password.setForeground(Color.GRAY);
        password.setText("Passwort");
        password.setEchoChar((char) 0);
        password.setFont(new Font("Oswald Medium", Font.BOLD, 20));

        messageField.setForeground(Color.WHITE);
        messageField.setOpaque(true);
        messageField.setBackground(new Color(139, 0, 0));
        messageField.setHorizontalAlignment(SwingConstants.CENTER);
        messageField.setVerticalAlignment(SwingConstants.CENTER);
        messageField.setFont(new Font("Oswald Medium", Font.PLAIN, 20));

        password.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (password.getText().equals("Passwort")) {
                    password.setText("");
                    password.setForeground(Color.WHITE);
                    password.setEchoChar((char) 0x2022);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (password.getPassword().length == 0) {
                    password.setText("Passwort");
                    password.setForeground(Color.GRAY);
                    password.setEchoChar((char) 0);
                }
            }
        });

        register.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                register.setText("<html><u>Registrieren</u></html>");
                register.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                register.setText("Registrieren");
                register.setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mousePressed(MouseEvent e) {
                register.setBackground(new Color(0x113F67));
            }
        });

        login.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                login.setText("<html><u>Login</u></html>");
                login.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                login.setText("Login");
                login.setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mousePressed(MouseEvent e) {
                login.setBackground(new Color(0x113F67));
            }
        });

        if (updateHandler.hasNewUpdate()) {
            JLabel updateAvailable = new JLabel(ImageIconHandler.imageType.UPDATE_NOTIFICATION.imageIcon);
            add(updateAvailable);
            updateAvailable.setBounds(427, 177, 685, 365);
        }

        add(register);
        add(logo);
        add(login);
        add(password);
        add(username);
        add(messageField);

        register.setBounds(5, 280, 245, 70);
        logo.setBounds(5, 10, 245, 180);
        login.setBounds(5, 200, 245, 70);
        username.setBounds(438, 277, 660, 75);
        messageField.setBounds(520, 477, 500, 200);
        password.setBounds(438, 377, 660, 75);

        JLabel sidebarBackground = new JLabel("");
        sidebarBackground.setBounds(0, 0, 255, 720);
        sidebarBackground.setOpaque(true);
        add(sidebarBackground);
        sidebarBackground.setBackground(new Color(0x113F67));
    }

}
