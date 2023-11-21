package com.pokemon.model;


public class WaterPokemon extends Pokemon {

    private static final long serialVersionUID = 1L;
	private String ability;
    private String habitat;

    public WaterPokemon(String name, String type, int level, int experience, String ability, String habitat) {
        super(name, type, level, experience);
        this.ability = ability;
        this.habitat = habitat;
    }

//    @Override
//    public void attack() {
//        System.out.println("Uses Hydro Pump!");
//    }

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    @Override
    public String toString() {
        return "WaterPokemon{" +
                "name='" + getName() + '\'' +
                ", type='" + getType() + '\'' +
                ", level=" + getLevel() +
                ", experience=" + getExperience() +
                ", ability='" + ability + '\'' +
                ", habitat='" + habitat + '\'' +
                '}';
    }
}
