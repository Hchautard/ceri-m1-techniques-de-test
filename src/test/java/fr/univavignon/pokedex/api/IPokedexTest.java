package fr.univavignon.pokedex.api;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class IPokedexTest {

    private IPokedex pokedex;
    private IPokemonMetadataProvider metadataProviderMock;
    private IPokemonFactory pokemonFactoryMock;
    private Pokemon pokemonMock;

    @BeforeEach
    public void setUp() {
        // Mock des dépendances
        metadataProviderMock = mock(IPokemonMetadataProvider.class);
        pokemonFactoryMock = mock(IPokemonFactory.class);
        
        // Initialisation de l'instance Pokedex avec des mocks
        pokedex = new Pokedex(metadataProviderMock, pokemonFactoryMock);
        
        // Création d'un mock de Pokémon
        int index = 133;
        String name = "Aquali";
        int attack = 186;
        int defense = 168;
        int stamina = 260;
        int cp = 2729;
        int hp = 202;
        int dust = 5000;
        int candy = 4;
        double iv = 100.0;

        pokemonMock = new Pokemon(index, name, attack, defense, stamina, cp, hp, dust, candy, iv);
    }

    @Test
    public void testSize() {
        assertEquals(0, pokedex.size(), "The Pokedex should initially be empty.");

        pokedex.addPokemon(pokemonMock);
        assertEquals(1, pokedex.size(), "The Pokedex size should be 1 after adding a Pokemon.");
    }

    @Test
    public void testAddPokemon() {
        int index = pokedex.addPokemon(pokemonMock);
        assertEquals(0, index, "The index of the first added Pokemon should be 0.");
        
        int secondIndex = pokedex.addPokemon(pokemonMock);
        assertEquals(1, secondIndex, "The index of the second added Pokemon should be 1.");
    }

    @Test
    public void testGetPokemonValidIndex() throws PokedexException {
        pokedex.addPokemon(pokemonMock);
        
        Pokemon retrievedPokemon = pokedex.getPokemon(0);
        assertNotNull(retrievedPokemon, "Pokemon at index 0 should not be null.");
        assertEquals("Aquali", retrievedPokemon.getName(), "The retrieved Pokemon should have the name 'Aquali'.");
    }

    @Test
    public void testGetPokemonInvalidIndex() {
        assertThrows(PokedexException.class, () -> pokedex.getPokemon(0), 
                     "Requesting a Pokemon with an invalid index should throw a PokedexException.");
    }

    @Test
    public void testGetPokemons() {
        pokedex.addPokemon(pokemonMock);

        List<Pokemon> retrievedPokemons = pokedex.getPokemons();
        assertEquals(1, retrievedPokemons.size(), "The Pokedex should contain 1 Pokemon.");
        assertEquals("Aquali", retrievedPokemons.get(0).getName(), "The Pokemon in the Pokedex should be 'Aquali'.");

        // Attempt to modify the returned list should throw an exception
        assertThrows(UnsupportedOperationException.class, () -> retrievedPokemons.add(pokemonMock),
                     "The returned Pokemon list should be unmodifiable.");
    }

    @Test
    public void testGetPokemonsWithOrder() {
        // Adding multiple Pokemon to test ordering
        Pokemon pokemon1 = new Pokemon(133, "Aquali", 186, 168, 260, 2729, 202, 5000, 4, 100.0);
        Pokemon pokemon2 = new Pokemon(1, "Bulbasaur", 118, 118, 90, 1115, 128, 2500, 3, 56.7);
        pokedex.addPokemon(pokemon1);
        pokedex.addPokemon(pokemon2);

        // Sort by name in ascending order
        List<Pokemon> sortedPokemons = pokedex.getPokemons(Comparator.comparing(Pokemon::getName));
        
        assertEquals("Aquali", sortedPokemons.get(0).getName(), "The first Pokemon should be 'Aquali' when sorted by name.");
        assertEquals("Bulbasaur", sortedPokemons.get(1).getName(), "The second Pokemon should be 'Bulbasaur' when sorted by name.");
    }

    @Test
    public void testGetPokemonMetadata() throws PokedexException {
        // Mock the metadata provider to return specific metadata
        PokemonMetadata metadata = new PokemonMetadata(133, "Aquali", 186, 168, 260);
        when(metadataProviderMock.getPokemonMetadata(133)).thenReturn(metadata);

        PokemonMetadata retrievedMetadata = pokedex.getPokemonMetadata(133);

        assertNotNull(retrievedMetadata, "Metadata for Pokemon index 133 should not be null.");
        assertEquals("Aquali", retrievedMetadata.getName(), "The metadata name should be 'Aquali'.");
        assertEquals(186, retrievedMetadata.getAttack(), "The metadata attack should be 186.");
    }

    @Test
    public void testCreatePokemon() {
        // Mock the pokemon factory to return a specific Pokemon
        when(pokemonFactoryMock.createPokemon(133, 2729, 202, 5000, 4)).thenReturn(pokemonMock);

        Pokemon createdPokemon = pokedex.createPokemon(133, 2729, 202, 5000, 4);

        assertNotNull(createdPokemon, "The created Pokemon should not be null.");
        assertEquals("Aquali", createdPokemon.getName(), "The created Pokemon should have the name 'Aquali'.");
        assertEquals(2729, createdPokemon.getCp(), "The created Pokemon should have a CP of 2729.");
    }
}
