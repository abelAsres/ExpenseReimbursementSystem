package com.revature.model;

import java.util.Objects;

public class ReimbursementRole{
    private int id;
    private String userRole;

    public ReimbursementRole(int id, String userRole) {
        this.id = id;
        this.userRole = userRole;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReimbursementRole that = (ReimbursementRole) o;
        return id == that.id && Objects.equals(userRole, that.userRole);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userRole);
    }

    @Override
    public String toString() {
        return "ReimbursementRole{" +
                "id=" + id +
                ", userRole='" + userRole + '\'' +
                '}';
    }
}
