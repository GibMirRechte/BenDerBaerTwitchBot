package de.bot.handler;

import de.bot.main.Main;

import javax.swing.*;

public class ImageIconHandler {

    public enum imageType {
        LOGO_PREMIUM(new ImageIcon(Main.class.getResource("/images/logoPremium.png"))),
        LOGO_STAFF(new ImageIcon(Main.class.getResource("/images/logoStaff.png"))),
        SIDEBAR_ACCOUNT_NORMAL(new ImageIcon(Main.class.getResource("/images/accountN.png"))),
        SIDEBAR_ACCOUNT_HOVERED(new ImageIcon(Main.class.getResource("/images/accountH.png"))),
        SIDEBAR_AUTOSHOUT_NORMAL(new ImageIcon(Main.class.getResource("/images/autoShoutN.png"))),
        SIDEBAR_AUTOSHOUT_HOVERED(new ImageIcon(Main.class.getResource("/images/autoShoutH.png"))),
        SIDEBAR_AUTOVIP_NORMAL(new ImageIcon(Main.class.getResource("/images/autoVIPN.png"))),
        SIDEBAR_AUTOVIP_HOVERED(new ImageIcon(Main.class.getResource("/images/autoVIPH.png"))),
        SIDEBAR_BLACKLIST_NORMAL(new ImageIcon(Main.class.getResource("/images/blacklistN.png"))),
        SIDEBAR_BLACKLIST_HOVERED(new ImageIcon(Main.class.getResource("/images/blacklistH.png"))),
        SIDEBAR_WHITELIST_NORMAL(new ImageIcon(Main.class.getResource("/images/whitelistN.png"))),
        SIDEBAR_WHITELIST_HOVERED(new ImageIcon(Main.class.getResource("/images/whitelistH.png")));

        public final ImageIcon imageIcon;

        imageType(ImageIcon imageIcon) {
            this.imageIcon = imageIcon;
        }
    }

    public ImageIcon getImageIcon(imageType imageType) {
        return imageType.imageIcon;
    }
}
