package com.keyin.binarytreevisalizerbackend.entity;
import jakarta.persistence.*;

@Entity
public class Graph {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String date;

    private String title;

    @Lob
    private String content; // JSON string representation of TreeNode

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}