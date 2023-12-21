package de.bot.utils;

public class ToolData {

    private boolean maintenance_autovip;
    private boolean maintenance_autoshout;
    private String changelog;

    public ToolData(boolean maintenance_autoshout, boolean maintenance_autovip, String changelog) {
        this.maintenance_autoshout = maintenance_autoshout;
        this.maintenance_autovip = maintenance_autovip;
        this.changelog = changelog.replace("%bN%", "\\n");
    }

    public void setChangelog(String changelog) {
        this.changelog = changelog;
    }

    public String getChangelog() {
        return changelog.replace("%bN%", "\n");
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
