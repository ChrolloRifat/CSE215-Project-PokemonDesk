# CSE215-Project-PokemonDesk
##Introduction

The Pokemon Desk project is a Java application designed to manage and interact with a collection of Pokemon entities. The project incorporates a graphical user interface (GUI) for ease of use, allowing users to display, add, search, edit, and delete Pokemon records. Additionally, the application supports Pokemon actions such as attack and heal.
Methodology

The project is structured around several Java classes representing different aspects of Pokemon, including a base Pokemon class and subclasses (FirePokemon, GrassPokemon, and WaterPokemon) that demonstrate polymorphism. The GUI is implemented using Swing, providing a user-friendly interface to interact with the Pokemon data. File handling utilities are incorporated to persist data between program runs.

##Class UML Diagram


+-----------------------------------------+
|                 Pokemon                 |
+-----------------------------------------+
| -name: String                           |
| -type: String                           |
| -level: int                             |
| -experience: int                        |
+-----------------------------------------+
| +Pokemon(name: String, type: String,    |
|    level: int, experience: int)         |
| +getName(): String                      |
| +getType(): String                      |
| +getLevel(): int                        |
| +getExperience(): int                   |
| +setName(name: String): void            |
| +setType(type: String): void            |
| +setLevel(level: int): void             |
| +setExperience(experience: int): void   |
| +attack(): void                         |
| +attack(moveName: String): void         |
| +attack(moveName: String, power: int): void|
| +heal(): void                           |
| +toString(): String                     |
+-----------------------------------------+

+-----------------------------+
|         FirePokemon         |
+-----------------------------+
| -ability: String            |
| -weakness: String            |
+-----------------------------+
| +FirePokemon(name: String,  |
|    type: String, level: int, |
|    experience: int, ability: |
|    String, weakness: String) |
| +getAbility(): String        |
| +setAbility(ability: String):void|
| +getWeakness(): String       |
| +setWeakness(weakness: String): void|
| +attack(): void              |
| +attack(moveName: String): void|
| +attack(moveName: String,    |
|    power: int): void         |
| +heal(): void                |
| +toString(): String          |
+-----------------------------+

+-----------------------------+
|        GrassPokemon         |
+-----------------------------+
| -nature: String             |
| -habitat: String            |
+-----------------------------+
| +GrassPokemon(name: String, |
|    type: String, level: int, |
|    experience: int, nature:  |
|    String, habitat: String)  |
| +getNature(): String         |
| +setNature(nature: String): void|
| +getHabitat(): String        |
| +setHabitat(habitat: String): void|
| +attack(): void              |
| +attack(moveName: String): void|
| +attack(moveName: String,    |
|    power: int): void         |
| +heal(): void                |
| +toString(): String          |
+-----------------------------+

+-----------------------------+
|        WaterPokemon         |
+-----------------------------+
| -ability: String            |
| -habitat: String            |
+-----------------------------+
| +WaterPokemon(name: String, |
|    type: String, level: int, |
|    experience: int, ability: |
|    String, habitat: String)  |
| +getAbility(): String        |
| +setAbility(ability: String): void|
| +getHabitat(): String        |
| +setHabitat(habitat: String): void|
| +attack(): void              |
| +attack(moveName: String): void|
| +attack(moveName: String,    |
|    power: int): void         |
| +heal(): void                |
| +toString(): String          |
+-----------------------------+

+-------------------------------+
|         PokemonActions         |
+-------------------------------+
| +attack(): void               |
| +attack(moveName: String): void|
| +attack(moveName: String,      |
|    power: int): void           |
| +heal(): void                 |
+-------------------------------+

+----------------------------------+
|          PokemonFactory          |
+----------------------------------+
| +createPokemon(name: String,     |
|    type: String, level: int,      |
|    experience: int, subtype1:     |
|    String, subtype2: String): Pokemon|
+----------------------------------+

+--------------------------------+
|          FileHandler             |
+--------------------------------+
| -FILE_NAME: String               |
+--------------------------------+
| +writeToFile(pokemonList: List<Pokemon>): void|
| +readFromFile(): List<Pokemon>   |
| -handleFileReadException(e: Exception): void|
+--------------------------------+

##Uses

The Pokemon Desk application serves as a tool for managing a collection of Pokemon records. Users can:

    Display Pokemon: View a list of Pokemon with details such as name, type, level, experience, and additional information based on the Pokemon's subtype.

    Add Pokemon: Add new Pokemon with attributes such as name, type, level, experience, and subtype details.

    Search Pokemon: Find and display information about a specific Pokemon by entering its name.

    Edit Pokemon: Modify existing Pokemon attributes, including name, type, level, experience, and subtype details.

    Delete Pokemon: Remove a Pokemon record from the collection.

    Attack and Heal: Perform actions on a selected Pokemon, such as attacking or healing.

##Conclusion

The Pokemon Desk project successfully implements a user-friendly GUI for managing Pokemon records. It leverages object-oriented principles such as polymorphism and encapsulation, enhancing code modularity and maintainability. The project provides a foundation for further expansion, such as adding new Pokemon types or actions.

The use of a file handler ensures data persistence, allowing users to maintain a consistent Pokemon collection across different program executions. The class UML diagram provides a visual representation of the relationships between the classes, highlighting the project's structure and design.

In summary, the Pokemon Desk project showcases the effective application of Java programming principles to create a practical and interactive desktop application for Pokemon enthusiasts.
