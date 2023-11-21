package com.pokemon.fileutil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import com.pokemon.model.Pokemon;

public class FileHandler {
 private static final String FILE_NAME = "pokemon_data.ser";

 public static void writeToFile(List<Pokemon> pokemonList) {
     try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
         oos.writeObject(pokemonList);
         System.out.println("Data written to file successfully.");
     } catch (IOException e) {
         e.printStackTrace();
     }
 }

 public static List<Pokemon> readFromFile() {
     List<Pokemon> pokemonList = new ArrayList<>();
     try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
         Object obj = ois.readObject();
         if (obj instanceof List<?>) {
             pokemonList = (List<Pokemon>) obj;
         }
     } catch (IOException | ClassNotFoundException e) {
         handleFileReadException(e);
     }
     return pokemonList;
 }

 private static void handleFileReadException(Exception e) {
     // Handle file not found or class not found exceptions
     System.out.println("No existing data found. Starting with an empty list.");
 }
}
