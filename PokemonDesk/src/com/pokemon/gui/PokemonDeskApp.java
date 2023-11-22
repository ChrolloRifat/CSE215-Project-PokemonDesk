package com.pokemon.gui;

import javax.swing.*;

import com.pokemon.fileutil.FileHandler;
import com.pokemon.model.FirePokemon;
import com.pokemon.model.GrassPokemon;
import com.pokemon.model.Pokemon;
import com.pokemon.model.PokemonFactory;
import com.pokemon.model.WaterPokemon;

import java.awt.*;
import java.util.List;
import java.util.Optional;

public class PokemonDeskApp {

 private JTextArea displayArea;
 private List<Pokemon> pokemonList;

 public static void main(String[] args) {
     SwingUtilities.invokeLater(() -> {
         PokemonDeskApp app = new PokemonDeskApp();
         app.createAndShowGUI();
     });
 }

 private void createAndShowGUI() {
	 JFrame frame = new JFrame("Pokemon Desk");
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

     displayArea = new JTextArea(10, 30);
     displayArea.setEditable(false);

     JButton displayButton = new JButton("Display Pokemon");
     displayButton.addActionListener(e -> displayPokemon());

     JButton addButton = new JButton("Add Pokemon");
     addButton.addActionListener(e -> addPokemon());

     JButton searchButton = new JButton("Search Pokemon");
     searchButton.addActionListener(e -> searchPokemon());

     JButton editButton = new JButton("Edit Pokemon");
     editButton.addActionListener(e -> editPokemon());

     JButton deleteButton = new JButton("Delete Pokemon");
     deleteButton.addActionListener(e -> deletePokemon());

     JButton attackButton = new JButton("Attack");
     attackButton.addActionListener(e -> performAction("Attack"));

     JButton healButton = new JButton("Heal");
     healButton.addActionListener(e -> performAction("Heal"));

     JPanel panel = new JPanel();
     panel.setLayout(new BorderLayout());
     panel.add(new JScrollPane(displayArea), BorderLayout.CENTER);

     // Add buttons to a separate panel to ensure they are all visible
     JPanel buttonPanel = new JPanel();
     buttonPanel.add(displayButton);
     buttonPanel.add(addButton);
     buttonPanel.add(searchButton);
     buttonPanel.add(editButton);
     buttonPanel.add(deleteButton);
     buttonPanel.add(attackButton);
     buttonPanel.add(healButton);

     panel.add(buttonPanel, BorderLayout.SOUTH);

     frame.getContentPane().add(panel);
     frame.pack();
     frame.setLocationRelativeTo(null);
     frame.setVisible(true);

     // Load existing data from file
     pokemonList = FileHandler.readFromFile();
 }

 private void displayPokemon() {
	 StringBuilder displayText = new StringBuilder(String.format("%-20s%-15s%-10s%-15s%-25s%-25s\n",
	            "Name", "Type", "Level", "Experience", "Additional Info 1", "Additional Info 2"));

	    for (Pokemon pokemon : pokemonList) {
	        String additionalInfo1 = "";
	        String additionalInfo2 = "";

	        if (pokemon instanceof FirePokemon) {
	            additionalInfo1 = ((FirePokemon) pokemon).getAbility();
	            additionalInfo2 = ((FirePokemon) pokemon).getWeakness();
	        } else if (pokemon instanceof GrassPokemon) {
	            additionalInfo1 = ((GrassPokemon) pokemon).getNature();
	            additionalInfo2 = ((GrassPokemon) pokemon).getHabitat();
	        } else if (pokemon instanceof WaterPokemon) {
	            additionalInfo1 = ((WaterPokemon) pokemon).getAbility();
	            additionalInfo2 = ((WaterPokemon) pokemon).getHabitat();
	        }

	        displayText.append(String.format("%-20s%-15s%-10s%-15s%-25s%-25s\n",
	                pokemon.getName(), pokemon.getType(), pokemon.getLevel(), pokemon.getExperience(),
	                additionalInfo1, additionalInfo2));
	    }

	    displayArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12)); // Set monospaced font
	    displayArea.setText(displayText.toString());
	}

 private void addPokemon() {
     String name = JOptionPane.showInputDialog("Enter the name of the Pokemon:");
     if (name != null && !name.isEmpty()) {
         String type = JOptionPane.showInputDialog("Enter the type of the Pokemon:");
         if (type != null && !type.isEmpty()) {
             int level = Integer.parseInt(JOptionPane.showInputDialog("Enter the level of the Pokemon:"));
             int experience = Integer.parseInt(JOptionPane.showInputDialog("Enter the experience of the Pokemon:"));

             String subtype = JOptionPane.showInputDialog("Enter the subtype I of the Pokemon (Ability or Nature):");
             String subvalue = JOptionPane.showInputDialog("Enter the subtype II of the Pokemon(Habitat or Weakness):");

             Pokemon newPokemon;

             switch (type.toLowerCase()) {
                 case "fire":
                     newPokemon = new FirePokemon(name, type, level, experience, subtype, subvalue);
                     break;
                 case "grass":
                     newPokemon = new GrassPokemon(name, type, level, experience, subtype, subvalue);
                     break;
                 case "water":
                     newPokemon = new WaterPokemon(name, type, level, experience, subtype, subvalue);
                     break;
                 default:
                     newPokemon = new Pokemon(name, type, level, experience);
                     break;
             }

             pokemonList.add(newPokemon);

             // Update the display and save to file
             displayPokemon();
             FileHandler.writeToFile(pokemonList);
         } else {
             JOptionPane.showMessageDialog(null, "Type cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
         }
     } else {
         JOptionPane.showMessageDialog(null, "Name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
     }
 }

 private void searchPokemon() {
	    String name = JOptionPane.showInputDialog("Enter the name of the Pokemon:");
	    if (name != null && !name.isEmpty()) {
	        for (Pokemon pokemon : pokemonList) {
	            if (pokemon.getName().equalsIgnoreCase(name)) {
	                displayArea.setText(pokemon.toString());
	                return;
	            }
	        }
	        JOptionPane.showMessageDialog(null, "No Pokemon found with the name: " + name, "Pokemon not found", JOptionPane.INFORMATION_MESSAGE);
	    }
	}

 private void editPokemon() {
	    String name = JOptionPane.showInputDialog("Enter the name of the Pokemon to edit:");
	    if (name != null && !name.isEmpty()) {
	        Optional<Pokemon> pokemonOptional = pokemonList.stream()
	                .filter(pokemon -> pokemon.getName().equalsIgnoreCase(name))
	                .findFirst();

	        if (pokemonOptional.isPresent()) {
	            Pokemon oldPokemon = pokemonOptional.get();

	            // Prompt for the new values of each attribute
	            String editedName = JOptionPane.showInputDialog("Enter the new name:", oldPokemon.getName());
	            String editedType = JOptionPane.showInputDialog("Enter the new type:", oldPokemon.getType());
	            int editedLevel = Integer.parseInt(JOptionPane.showInputDialog("Enter the new level:", oldPokemon.getLevel()));
	            int editedExperience = Integer.parseInt(JOptionPane.showInputDialog("Enter the new experience:", oldPokemon.getExperience()));
	            String editedSubtype1 = JOptionPane.showInputDialog("Enter the subtype I of the Pokemon (Ability or Nature):");
	            String editedSubtype2 = JOptionPane.showInputDialog("Enter the subtype II of the Pokemon(Habitat or Weakness):");

	            // Create a new Pokemon instance based on the updated type
	            Pokemon newPokemon = PokemonFactory.createPokemon(editedName, editedType, editedLevel, editedExperience, editedSubtype1, editedSubtype2);

	            // Replace the old Pokemon with the new one in the list
	            int index = pokemonList.indexOf(oldPokemon);
	            if (index != -1) {
	                pokemonList.set(index, newPokemon);
	            }

	            // Update the display and save to file
	            displayPokemon();
	            FileHandler.writeToFile(pokemonList);
	        } else {
	            JOptionPane.showMessageDialog(null, "No Pokemon found with the name: " + name, "Pokemon not found", JOptionPane.INFORMATION_MESSAGE);
	        }
	    }
	}

	private void deletePokemon() {
	    String name = JOptionPane.showInputDialog("Enter the name of the Pokemon to delete:");
	    if (name != null && !name.isEmpty()) {
	        Optional<Pokemon> pokemonOptional = pokemonList.stream()
	                .filter(pokemon -> pokemon.getName().equalsIgnoreCase(name))
	                .findFirst();

	        if (pokemonOptional.isPresent()) {
	            Pokemon pokemonToDelete = pokemonOptional.get();

	            // Confirm deletion
	            int confirmation = JOptionPane.showConfirmDialog(null,
	                    "Are you sure you want to delete " + name + "?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);

	            if (confirmation == JOptionPane.YES_OPTION) {
	                pokemonList.remove(pokemonToDelete);

	                // Update the display and save to file
	                displayPokemon();
	                FileHandler.writeToFile(pokemonList);
	            }
	        } else {
	            JOptionPane.showMessageDialog(null, "No Pokemon found with the name: " + name, "Pokemon not found", JOptionPane.INFORMATION_MESSAGE);
	        }
	    }
	}
	private void performAction(String action) {
	    String name = JOptionPane.showInputDialog("Enter the name of the Pokemon to " + action + ":");
	    if (name != null && !name.isEmpty()) {
	        Optional<Pokemon> pokemonOptional = pokemonList.stream()
	                .filter(pokemon -> pokemon.getName().equalsIgnoreCase(name))
	                .findFirst();

	        if (pokemonOptional.isPresent()) {
	            Pokemon pokemonToAct = pokemonOptional.get();

	            if (action.equals("Attack")) {
	                // Prompt the user for attack details
	                String moveName = JOptionPane.showInputDialog("Enter the name of the move:");
	                String powerInput = JOptionPane.showInputDialog("Enter the power of the move (leave blank for default):");

	                int power;
	                if (powerInput != null && !powerInput.isEmpty()) {
	                    // Use the provided power
	                    power = Integer.parseInt(powerInput);
	                } else {
	                    // Use the default power
	                    power = -1; // or any default value you want
	                }

	                // Check the type and call the appropriate overloaded attack method
	                if (pokemonToAct instanceof FirePokemon) {
	                    if (moveName != null && !moveName.isEmpty()) {
	                        if (power != -1) {
	                            ((FirePokemon) pokemonToAct).attack(moveName, power);
	                        } else {
	                            ((FirePokemon) pokemonToAct).attack(moveName);
	                        }
	                    }
	                } else if (pokemonToAct instanceof GrassPokemon) {
	                    if (moveName != null && !moveName.isEmpty()) {
	                        if (power != -1) {
	                            ((GrassPokemon) pokemonToAct).attack(moveName, power);
	                        } else {
	                            ((GrassPokemon) pokemonToAct).attack(moveName);
	                        }
	                    }
	                } else if (pokemonToAct instanceof WaterPokemon) {
	                    if (moveName != null && !moveName.isEmpty()) {
	                        if (power != -1) {
	                            ((WaterPokemon) pokemonToAct).attack(moveName, power);
	                        } else {
	                            ((WaterPokemon) pokemonToAct).attack(moveName);
	                        }
	                    }
	                } else {
	                    // Default attack for Pokemon class
	                    if (moveName != null && !moveName.isEmpty()) {
	                        if (power != -1) {
	                            pokemonToAct.attack(moveName, power);
	                        } else {
	                            pokemonToAct.attack(moveName);
	                        }
	                    }
	                }
	            } else if (action.equals("Heal")) {
	                pokemonToAct.heal();
	            }

	            // Update the display
	            displayPokemon();
	        } else {
	            JOptionPane.showMessageDialog(null, "No Pokemon found with the name: " + name, "Pokemon not found", JOptionPane.INFORMATION_MESSAGE);
	        }
	    }
	}



}
