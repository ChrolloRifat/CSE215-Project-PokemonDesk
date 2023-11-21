package com.pokemon.model;


public class FirePokemon extends Pokemon {

    private static final long serialVersionUID = 1L;
	private String ability;
    private String weakness;

    public FirePokemon(String name, String type, int level, int experience, String ability, String weakness) {
        super(name, type, level, experience);
        this.ability = ability;
        this.weakness = weakness;
    }

 //   @Override
//    public void attack() {
//        System.out.println("Uses Flamethrower!");
//    }

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    public String getWeakness() {
        return weakness;
    }

    public void setWeakness(String weakness) {
        this.weakness = weakness;
    }

    @Override
    public String toString() {
        return "FirePokemon{" +
                "name='" + getName() + '\'' +
                ", type='" + getType() + '\'' +
                ", level=" + getLevel() +
                ", experience=" + getExperience() +
                ", ability='" + ability + '\'' +
                ", weakness='" + weakness + '\'' +
                '}';
    }
}

