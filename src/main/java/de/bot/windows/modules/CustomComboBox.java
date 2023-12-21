package de.bot.windows.modules;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;

public class CustomComboBox extends BasicComboBoxUI {
    @Override
    protected JButton createArrowButton() {
        return new BasicArrowButton(BasicArrowButton.SOUTH, // Dropdown-Pfeil unten
                new Color(0x262626), // Hintergrundfarbe #272727
                new Color(0x262626),
                Color.WHITE, // Schattenfarbe weiß
                new Color(0x262626));
    }
}

