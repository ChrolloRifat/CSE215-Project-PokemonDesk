package com.pokemon.model;


import java.io.Serializable;
public class Pokemon implements Serializable {
	private static final long serialVersionUID = 1L;
    private String name;
    private String type;
    private int level;
    private int experience;


    public Pokemon(String name, String type, int level, int experience) {
        this.name = name;
        this.type = type;
        this.level = level;
        this.experience = experience;

    }
    //getter methods
    public String getName() {
        return name;
    }
    public String getType() {
        return type;
    }
    public int getLevel() {
        return level;
    }
    public int getExperience() {
        return experience;
    }
    
    //setter methods
    public void setName(String name) {
        this.name = name;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public void setLevel(int level) {
        this.level = level;
    }
    
    public void setExperience(int experience) {
        this.experience = experience;
    }
    
    @Override
    public String toString() {
        return "Pokemon{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", level=" + level +
                ", experience=" + experience +
                '}';
    }
}
