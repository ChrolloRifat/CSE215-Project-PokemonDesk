package com.pokemon.model;


import java.io.Serializable;
public class Pokemon implements Serializable, PokemonActions {
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
    
    //interface methods
    @Override
    public void attack() {
        System.out.println(getName() + " uses Default Attack!");
    }
    // Overloaded attack method with a different signature
    public void attack(String moveName) {
        System.out.println(getName() + " uses " + moveName + "!");
    }

    // Overloaded attack method with different parameters
    public void attack(String moveName, int power) {
        System.out.println(getName() + " uses " + moveName + " with power " + power + "!");
    }

    @Override
    public void heal() {
        System.out.println(getName() + " uses Default Heal!");
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
