package com.pokemon.gui;



import javax.swing.*;

import com.pokemon.fileutil.FileHandler;
import com.pokemon.model.FirePokemon;
import com.pokemon.model.GrassPokemon;
import com.pokemon.model.Pokemon;
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
		            Pokemon pokemonToEdit = pokemonOptional.get();

		            // Prompt for the new values of each attribute
		            String editedName = JOptionPane.showInputDialog("Enter the new name:", pokemonToEdit.getName());
		            String editedType = JOptionPane.showInputDialog("Enter the new type:", pokemonToEdit.getType());
		            int editedLevel = Integer.parseInt(JOptionPane.showInputDialog("Enter the new level:", pokemonToEdit.getLevel()));
		            int editedExperience = Integer.parseInt(JOptionPane.showInputDialog("Enter the new experience:", pokemonToEdit.getExperience()));

		            // Update the Pokemon with the new values
		            pokemonToEdit.setName(editedName);
		            pokemonToEdit.setType(editedType);
		            pokemonToEdit.setLevel(editedLevel);
		            pokemonToEdit.setExperience(editedExperience);

		            // Check the type and update subtype values accordingly
		            if (pokemonToEdit instanceof FirePokemon) {
		                ((FirePokemon) pokemonToEdit).setAbility(JOptionPane.showInputDialog("Enter the new ability:", ((FirePokemon) pokemonToEdit).getAbility()));
		                ((FirePokemon) pokemonToEdit).setWeakness(JOptionPane.showInputDialog("Enter the new weakness:", ((FirePokemon) pokemonToEdit).getWeakness()));
		            } else if (pokemonToEdit instanceof GrassPokemon) {
		                ((GrassPokemon) pokemonToEdit).setNature(JOptionPane.showInputDialog("Enter the new nature:", ((GrassPokemon) pokemonToEdit).getNature()));
		                ((GrassPokemon) pokemonToEdit).setHabitat(JOptionPane.showInputDialog("Enter the new habitat:", ((GrassPokemon) pokemonToEdit).getHabitat()));
		            } else if (pokemonToEdit instanceof WaterPokemon) {
		                ((WaterPokemon) pokemonToEdit).setAbility(JOptionPane.showInputDialog("Enter the new ability:", ((WaterPokemon) pokemonToEdit).getAbility()));
		                ((WaterPokemon) pokemonToEdit).setHabitat(JOptionPane.showInputDialog("Enter the new habitat:", ((WaterPokemon) pokemonToEdit).getHabitat()));
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
}
