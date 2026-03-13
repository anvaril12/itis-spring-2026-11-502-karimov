package org.example;

public class Transaction {
    private long id;
    private int userIdFrom;
    private int userIdTo;
    private double amount;
    private boolean success;
    private String denyReason;

    public Transaction() {
    }

    public Transaction(long id, int userIdFrom, int userIdTo, double amount, boolean success, String denyReason) {
        this.id = id;
        this.userIdFrom = userIdFrom;
        this.userIdTo = userIdTo;
        this.amount = amount;
        this.success = success;
        this.denyReason = denyReason;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getUserIdFrom() {
        return userIdFrom;
    }

    public void setUserIdFrom(int userIdFrom) {
        this.userIdFrom = userIdFrom;
    }

    public int getUserIdTo() { return userIdTo; }

    public void setUserIdTo(int userIdTo) {
        this.userIdTo = userIdTo;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getDenyReason() {
        return denyReason;
    }

    public void setDenyReason(String denyReason) { this.denyReason = denyReason; }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", senderId=" + userIdFrom +
                ", recipientId=" + userIdTo +
                ", amount=" + amount +
                ", success=" + success +
                ", denyReason='" + denyReason + '\'' +
                '}';
    }
}