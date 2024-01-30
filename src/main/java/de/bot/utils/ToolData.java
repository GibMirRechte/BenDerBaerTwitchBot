package de.bot.utils;

public class ToolData {

    private boolean registerEnabled;
    private boolean maintenance_autovip;
    private boolean maintenance_autoshout;

    public ToolData(boolean registerEnabled, boolean maintenance_autoshout, boolean maintenance_autovip) {
        this.maintenance_autoshout = maintenance_autoshout;
        this.maintenance_autovip = maintenance_autovip;
    }

    public void setRegisterEnabled(boolean registerEnabled) {
        this.registerEnabled = registerEnabled;
    }

    public boolean isRegisterEnabled() {
        return registerEnabled;
    }

    public void updateMaintenance(boolean autoshout, boolean autovip) {
        this.maintenance_autoshout = autoshout;
        this.maintenance_autovip = autovip;
    }

    public boolean isMaintenance_autoshout() {
        return maintenance_autoshout;
    }

    public boolean isMaintenance_autovip() {
        return maintenance_autovip;
    }
}
