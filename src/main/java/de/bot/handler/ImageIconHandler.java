package de.bot.handler;

import de.bot.main.Main;

import javax.swing.*;

public class ImageIconHandler {

    public enum imageType {

        VIP_ICON(new ImageIcon(Main.class.getResource("/images/vip.png"))),
        UPDATE_NOTIFICATION(new ImageIcon(Main.class.getResource("/images/update.png"))),
        LOGO_NORMAL(new ImageIcon(Main.class.getResource("/images/logoNormal.png"))),
        LOGO_PREMIUM(new ImageIcon(Main.class.getResource("/images/logoPremium.png"))),
        LOGO_STAFF(new ImageIcon(Main.class.getResource("/images/logoStaff.png"))),
        WARN_ICON(new ImageIcon(Main.class.getResource("/images/warnIcon.png"))),
        INFO_ICON(new ImageIcon(Main.class.getResource("/images/infoIcon.png")));
        public final ImageIcon imageIcon;

        imageType(ImageIcon imageIcon) {
            this.imageIcon = imageIcon;
        }
    }

    public ImageIcon getImageIcon(imageType imageType) {
        return imageType.imageIcon;
    }
}
