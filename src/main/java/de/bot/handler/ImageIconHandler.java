package de.bot.handler;

import de.bot.main.Main;

import javax.swing.*;

public class ImageIconHandler {

    public enum imageType {

        VIP_ICON(new ImageIcon(Main.class.getResource("/images/vip.png"))),
        SOON_ICON(new ImageIcon(Main.class.getResource("/images/soon.png"))),
        WARN_ICON(new ImageIcon(Main.class.getResource("/images/warnIcon.png"))),
        INFO_ICON(new ImageIcon(Main.class.getResource("/images/infoIcon.png")));
        public final ImageIcon imageIcon;

        imageType(ImageIcon imageIcon) {
            this.imageIcon = imageIcon;
        }
    }
}
