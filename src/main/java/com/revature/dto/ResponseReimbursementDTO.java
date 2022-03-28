package com.revature.dto;

public class ResponseReimbursementDTO {
    private int id;
    private double amount;
    private String submittedTime;
    private String resolvedTime;
    private String description;
    private String imageLink;
    private String author;
    private String resolver;
    private String status;
    private String type;

    public ResponseReimbursementDTO(){}


    public ResponseReimbursementDTO(int id, double amount, String submittedTime, String resolvedTime, String description,
                                    String imageLink, String author, String resolver, String status, String type) {
        this.id = id;
        this.amount = amount;
        this.submittedTime = submittedTime;
        this.resolvedTime = resolvedTime;
        this.description = description;
        this.imageLink = imageLink;
        this.author = author;
        this.resolver = resolver;
        this.status = status;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getResolver() {
        return resolver;
    }

    public void setResolver(String resolver) {
        this.resolver = resolver;
    }

}
