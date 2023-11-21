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
