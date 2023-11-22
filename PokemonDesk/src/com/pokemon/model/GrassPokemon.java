package com.pokemon.model;


public class GrassPokemon extends Pokemon {

    private static final long serialVersionUID = 1L;
	private String nature;
    private String habitat;

    public GrassPokemon(String name, String type, int level, int experience, String nature, String habitat) {
        super(name, type, level, experience);
        this.nature = nature;
        this.habitat = habitat;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }
    
    @Override
    public void attack() {
        System.out.println("Grass Pokemon " + getName() + " uses Grass-woven Whip!");
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
        System.out.println("Grass Pokemon " + getName() + " uses Grass-Shield Heal!");
    }

    @Override
    public String toString() {
        return "GrassPokemon{" +
                "name='" + getName() + '\'' +
                ", type='" + getType() + '\'' +
                ", level=" + getLevel() +
                ", experience=" + getExperience() +
                ", nature='" + nature + '\'' +
                ", habitat='" + habitat + '\'' +
                '}';
    }
}
