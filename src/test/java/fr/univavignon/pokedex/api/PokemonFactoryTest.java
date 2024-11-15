package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PokemonFactoryTest {

    private IPokemonMetadataProvider metadataProviderMock;
    private PokemonFactory pokemonFactory;

    @BeforeEach
    public void setUp() {
        metadataProviderMock = mock(IPokemonMetadataProvider.class);
        pokemonFactory = new PokemonFactory(metadataProviderMock);
    }

    @Test
    public void testCreatePokemonWithValidData() throws PokedexException {
        int index = 1;
        int cp = 1500;
        int hp = 120;
        int dust = 2000;
        int candy = 50;

        // Mock metadata for the Pokémon
        PokemonMetadata metadata = new PokemonMetadata(index, "Bulbasaur", 49, 49, 90);
        when(metadataProviderMock.getPokemonMetadata(index)).thenReturn(metadata);

        // Call the factory method
        Pokemon createdPokemon = pokemonFactory.createPokemon(index, cp, hp, dust, candy);

        // Verify the result
        assertNotNull(createdPokemon, "The Pokémon should not be null.");
        assertEquals(index, createdPokemon.getIndex(), "The Pokémon index should match.");
        assertEquals("Bulbasaur", createdPokemon.getName(), "The Pokémon name should match.");
        assertEquals(cp, createdPokemon.getCp(), "The Pokémon CP should match.");
        assertEquals(hp, createdPokemon.getHp(), "The Pokémon HP should match.");
        assertEquals(dust, createdPokemon.getDust(), "The Pokémon dust should match.");
        assertEquals(candy, createdPokemon.getCandy(), "The Pokémon candy should match.");
        assertEquals(50, createdPokemon.getIv(), "The Pokémon IV should be default to 50.");
    }

    @Test
    public void testCreatePokemonWithInvalidIndex() throws PokedexException {
        int invalidIndex = -1;
        int cp = 1500;
        int hp = 120;
        int dust = 2000;
        int candy = 50;

        // Mock metadata provider to throw an exception for invalid index
        when(metadataProviderMock.getPokemonMetadata(invalidIndex))
                .thenThrow(new PokedexException("Invalid Pokemon index: " + invalidIndex));

        // Call the factory method and expect null
        Pokemon createdPokemon = pokemonFactory.createPokemon(invalidIndex, cp, hp, dust, candy);

        // Verify the result
        assertNull(createdPokemon, "The Pokémon should be null for an invalid index.");
    }

    @Test
    public void testCreatePokemonWithEdgeCaseValues() throws PokedexException {
        int index = 0;
        int cp = Integer.MAX_VALUE;
        int hp = Integer.MAX_VALUE;
        int dust = Integer.MAX_VALUE;
        int candy = Integer.MAX_VALUE;

        // Mock metadata for the Pokémon
        PokemonMetadata metadata = new PokemonMetadata(index, "Bulbasaur", 49, 49, 90);
        when(metadataProviderMock.getPokemonMetadata(index)).thenReturn(metadata);

        // Call the factory method
        Pokemon createdPokemon = pokemonFactory.createPokemon(index, cp, hp, dust, candy);

        // Verify the result
        assertNotNull(createdPokemon, "The Pokémon should not be null.");
        assertEquals(cp, createdPokemon.getCp(), "The Pokémon CP should match.");
        assertEquals(hp, createdPokemon.getHp(), "The Pokémon HP should match.");
        assertEquals(dust, createdPokemon.getDust(), "The Pokémon dust should match.");
        assertEquals(candy, createdPokemon.getCandy(), "The Pokémon candy should match.");
    }

    @Test
    public void testCreatePokemonWithZeroValues() throws PokedexException {
        int index = 10;
        int cp = 0;
        int hp = 0;
        int dust = 0;
        int candy = 0;

        // Mock metadata for the Pokémon
        PokemonMetadata metadata = new PokemonMetadata(index, "Charmander", 39, 43, 78);
        when(metadataProviderMock.getPokemonMetadata(index)).thenReturn(metadata);

        // Call the factory method
        Pokemon createdPokemon = pokemonFactory.createPokemon(index, cp, hp, dust, candy);

        // Verify the result
        assertNotNull(createdPokemon, "The Pokémon should not be null.");
        assertEquals(cp, createdPokemon.getCp(), "The Pokémon CP should match.");
        assertEquals(hp, createdPokemon.getHp(), "The Pokémon HP should match.");
        assertEquals(dust, createdPokemon.getDust(), "The Pokémon dust should match.");
        assertEquals(candy, createdPokemon.getCandy(), "The Pokémon candy should match.");
    }

}
