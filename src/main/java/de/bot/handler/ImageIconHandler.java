package de.bot.handler;

import de.bot.main.Main;

import javax.swing.*;

public class ImageIconHandler {

    public enum imageType {

        VIP_ICON(new ImageIcon(Main.class.getResource("/images/vip.png"))),
        UPDATE_NOTIFICATION(new ImageIcon(Main.class.getResource("/images/update.png"))),
        LOGO_PREMIUM(new ImageIcon(Main.class.getResource("/images/logoPremium.png"))),
        LOGO_STAFF(new ImageIcon(Main.class.getResource("/images/logoStaff.png")));
        public final ImageIcon imageIcon;

        imageType(ImageIcon imageIcon) {
            this.imageIcon = imageIcon;
        }
    }

    public ImageIcon getImageIcon(imageType imageType) {
        return imageType.imageIcon;
    }
}
