package com.revature.dto;

import io.javalin.http.UploadedFile;

import java.io.InputStream;

public class AddReimbursementDTO {
    private double amount;
    private String userId;
    private String typeId;
    private String description;
    private String author;
    private UploadedFile image;

    public AddReimbursementDTO(){}
    public AddReimbursementDTO(double amount, String userId, String typeId, String description, String author, UploadedFile image) {
        this.amount = amount;
        this.userId = userId;
        this.typeId = typeId;
        this.description = description;
        this.author = author;
        this.image = image;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UploadedFile getImage() {
        return image;
    }

    public void setImage(UploadedFile image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "AddReimbursementDTO{" +
                "amount=" + amount +
                ", userId=" + userId +
                ", typeId=" + typeId +
                ", description='" + description + '\'' +
                ", image=" + image +
                '}';
    }
}
