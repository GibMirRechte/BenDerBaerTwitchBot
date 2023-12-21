package de.bot.utils;

public class BanData {

    private String reason;
    private String operator;
    private String operatorColor;
    private long created;
    private long bannedUntil;
    private boolean cancelled;
    private String cancelledBy;
    private String cancelledByColor;
    private String cancelledReason;
    private long cancelledAt;

    public BanData(String reason, String operator, String operatorColor, long created, long bannedUntil, boolean cancelled, String cancelledBy, String cancelledbyColor, String cancelledReason, long cancelledAt) {
        this.reason = reason;
        this.operator = operator;
        this.operatorColor = operatorColor;
        this.created = created;
        this.bannedUntil = bannedUntil;
        this.cancelled = cancelled;
        this.cancelledBy = cancelledBy;
        this.cancelledByColor = cancelledbyColor;
        this.cancelledReason = cancelledReason;
        this.cancelledAt = cancelledAt;
    }

    public String getCancelledByColor() {
        return cancelledByColor;
    }

    public String getOperatorColor() {
        return operatorColor;
    }

    public String getCancelledReason() {
        return cancelledReason;
    }

    public String getOperator() {
        return operator;
    }

    public String getCancelledBy() {
        return cancelledBy;
    }

    public long getCancelledAt() {
        return cancelledAt;
    }

    public long getCreated() {
        return created;
    }

    public boolean isActive() {
        return !this.cancelled && (this.bannedUntil == -1 || this.bannedUntil > System.currentTimeMillis());
    }

    public String getReason() {
        return reason;
    }

    public long getBannedUntil() {
        return bannedUntil;
    }

    public boolean isCancelled() {
        return cancelled;
    }
}
