package com.revature.model;

import java.util.Objects;

public class Reimbursement {
    private int id;
    private double amount;
    private String submittedTime;
    private String resolvedTime;
    private String description;
    private String imageLink;
    private String author;
    private String resolver;
    private int statusId;
    private int type_id;

    public Reimbursement(int id, double amount, String submittedTime, String resolvedTime,
                         String description, String imageLink, String author, String resolver,
                         int statusId, int type_id) {
        this.id = id;
        this.amount = amount;
        this.submittedTime = submittedTime;
        this.resolvedTime = resolvedTime;
        this.description = description;
        this.imageLink = imageLink;
        this.author = author;
        this.resolver = resolver;
        this.statusId = statusId;
        this.type_id = type_id;
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

    public String getResolver() {
        return resolver;
    }

    public void setResolver(String resolver) {
        this.resolver = resolver;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reimbursement that = (Reimbursement) o;
        return id == that.id && Double.compare(that.amount, amount) == 0 && statusId == that.statusId && type_id == that.type_id && Objects.equals(submittedTime, that.submittedTime) && Objects.equals(resolvedTime, that.resolvedTime) && Objects.equals(description, that.description) && Objects.equals(imageLink, that.imageLink) && Objects.equals(author, that.author) && Objects.equals(resolver, that.resolver);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, submittedTime, resolvedTime, description, imageLink, author, resolver, statusId, type_id);
    }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "id=" + id +
                ", amount=" + amount +
                ", submittedTime='" + submittedTime + '\'' +
                ", resolvedTime='" + resolvedTime + '\'' +
                ", description='" + description + '\'' +
                ", imageLink='" + imageLink + '\'' +
                ", author='" + author + '\'' +
                ", resolver='" + resolver + '\'' +
                ", statusId=" + statusId +
                ", type_id=" + type_id +
                '}';
    }
}
