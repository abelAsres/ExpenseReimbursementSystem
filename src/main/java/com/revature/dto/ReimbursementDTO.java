package com.revature.dto;

public class ReimbursementDTO {
    private double amount;
    private String submittedTime;
    private String resolvedTime;
    private String description;
    private String imageLink;
    private int author;
    private int statusId;
    private int type_id;

    public ReimbursementDTO(){}

    public ReimbursementDTO(double amount, String submittedTime, String resolvedTime, String description, String imageLink,
                            int author, int type_id) {
        this.amount = amount;
        this.submittedTime = submittedTime;
        this.resolvedTime = resolvedTime;
        this.description = description;
        this.imageLink = imageLink;
        this.author = author;
        this.statusId = 1;
        this.type_id = type_id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getSubmittedTime() {
        return submittedTime;
    }

    public void setSubmittedTime(String submittedTime) {
        this.submittedTime = submittedTime;
    }

    public String getResolvedTime() {
        return resolvedTime;
    }

    public void setResolvedTime(String resolvedTime) {
        this.resolvedTime = resolvedTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }
}
