package de.bot.handler;

import de.bot.main.Main;

import javax.swing.*;

public class ImageIconHandler {

    public enum imageType {

        VIP_ICON(new ImageIcon(Main.class.getResource("/images/vip.png"))),
        SOON_ICON(new ImageIcon(Main.class.getResource("/images/soon.png"))),
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
}
