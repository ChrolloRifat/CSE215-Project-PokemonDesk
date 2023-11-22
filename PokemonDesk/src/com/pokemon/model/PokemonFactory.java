package com.pokemon.model;

public class PokemonFactory {
    public static Pokemon createPokemon(String name, String type, int level, int experience, String subtype1, String subtype2) {
        switch (type.toLowerCase()) {
            case "fire":
                return new FirePokemon(name, type, level, experience, subtype1, subtype2);
            case "grass":
                return new GrassPokemon(name, type, level, experience, subtype1, subtype2);
            case "water":
                return new WaterPokemon(name, type, level, experience, subtype1, subtype2);
            default:
                return new Pokemon(name, type, level, experience);
        }
    }
}
