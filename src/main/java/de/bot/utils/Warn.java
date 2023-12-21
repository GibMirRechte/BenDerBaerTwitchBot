package de.bot.utils;

public class Warn {

    private long timestamp;
    private String reason;
    private String operator;
    private String operatorColor;
    private boolean cancelled;
    private String cancelledBy;
    private String cancelledByColor;
    private String cancelledReason;
    private long canncelledAt;

    public Warn(String reason, String operator, String operatorColor, long timestamp, boolean cancelled, String cancelledBy, String cancelledByColor, String cancelledReason, long canncelledAt) {
        this.reason = reason;
        this.operator = operator;
        this.operatorColor = operatorColor;
        this.timestamp = timestamp;
        this.cancelled = cancelled;
        this.cancelledBy = cancelledBy;
        this.cancelledByColor = cancelledByColor;
        this.cancelledReason = cancelledReason;
        this.canncelledAt = canncelledAt;
    }

    public void cancel(String operator, String cancelledReason) {
        this.cancelled = true;
        this.cancelledBy = operator;
        this.cancelledReason = cancelledReason;
        this.canncelledAt = System.currentTimeMillis();
    }

    public String getCancelledByColor() {
        return cancelledByColor;
    }

    public String getOperatorColor() {
        return operatorColor;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public long getCanncelledAt() {
        return canncelledAt;
    }

    public String getCancelledBy() {
        return cancelledBy;
    }

    public String getCancelledReason() {
        return cancelledReason;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getReason() {
        return reason;
    }

    public String getOperator() {
        return operator;
    }

}
