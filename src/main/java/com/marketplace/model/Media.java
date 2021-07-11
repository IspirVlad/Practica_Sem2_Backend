package com.marketplace.model;

import javax.persistence.*;

@Entity
@Table(name="media_files")
public class Media {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String path;

    private String name;

    private String type;

    private Long date;

    public Media() {
    }

    public Media(String path, String name, String type, Long date) {
        this.path=path;
        this.name=name;
        this.type=type;
        this.date=date;
    }

    public Media(String name) {
        this.name=name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id=id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path=path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type=type;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date=date;
    }
}
