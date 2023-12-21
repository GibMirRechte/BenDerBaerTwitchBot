package de.bot.windows.modules;

import javax.swing.*;
import java.awt.*;

public class ComboBoxRenderer extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        // Ändere die Hintergrundfarbe, wenn das Element ausgewählt ist
        setForeground(Color.WHITE);
        if (isSelected) {
            setBackground(new Color(76, 76, 76)); // Hier kannst du die gewünschte Farbe setzen
        } else {
            setBackground(new Color(0x27, 0x27, 0x27));
        }

        return this;
    }
}