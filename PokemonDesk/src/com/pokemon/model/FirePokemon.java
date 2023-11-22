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
    public void attack() {
        System.out.println("Fire Pokemon " + getName() + " uses Flamethrower!");
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
        System.out.println("Fire Pokemon " + getName() + " uses Fire-ball Heal!");
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

