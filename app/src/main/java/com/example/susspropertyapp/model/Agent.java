package com.example.susspropertyapp.model;

import java.io.Serializable;

public class Agent implements Serializable {

    private String name;
    private String rating;
    private String id;
    private String bio;

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getRating(){
        return rating;
    }
    public void setRating(String rating){
        this.rating = rating;
    }
    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id = id;
    }
    public String getBio(){
        return bio;
    }
    public void setBio(String bio){
        this.bio = bio;
    }
}

