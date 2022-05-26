package com.s3.movieflex.model;

import java.io.Serializable;

public class Cast implements Serializable {
    private int id;
    private String name;
    private String character;
    private String profile_path;
    private String department;
    private String job;

    public Cast(int id, String name, String character, String profile_path, String department, String job) {
        this.id = id;
        this.name = name;
        this.character = character;
        this.profile_path = profile_path;
        this.department = department;
        this.job = job;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getProfile_path() {
        return profile_path;
    }

    public void setProfile_path(String profile_path) {
        this.profile_path = profile_path;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
