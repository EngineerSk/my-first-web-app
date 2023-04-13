package com.oriadesoftdev.springboot.myfirstwebapp.todo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Todo {

    @Id
    @GeneratedValue
    private int id;
    private String username;

    @Size(min=10, message = "Enter at least 10 characters")
    private String description;
    private LocalDate targetDate;
    private boolean completed;

    public Todo(){

    }

    public Todo(int id, String username, String description, LocalDate targetDate, boolean isCompleted) {
        this.id = id;
        this.username = username;
        this.description = description;
        this.targetDate = targetDate;
        this.completed = isCompleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", description='" + description + '\'' +
                ", isCompleted=" + completed +
                ", targetDate=" + targetDate +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Todo) obj;
        return this.id == that.id &&
                Objects.equals(this.username, that.username) &&
                Objects.equals(this.description, that.description) &&
                Objects.equals(this.targetDate, that.targetDate) &&
                this.completed == that.completed;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, description, targetDate, completed);
    }

}
