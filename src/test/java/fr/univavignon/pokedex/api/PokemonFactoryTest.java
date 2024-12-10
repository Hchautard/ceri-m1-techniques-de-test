package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import fr.univavignon.pokedex.api.RocketPokemonFactory;

public class PokemonFactoryTest {

    private IPokemonMetadataProvider metadataProviderMock;
    private PokemonFactory pokemonFactory;
    private RocketPokemonFactory rocketFactory;

    @BeforeEach
    public void setUp() {
        metadataProviderMock = mock(IPokemonMetadataProvider.class);
        pokemonFactory = new PokemonFactory(metadataProviderMock);
        rocketFactory = new RocketPokemonFactory();
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

    // Test with RocketPokemonFactory

    @Test
    public void testRocketCreatePokemonWithValidData() {
        int index = 1;
        int cp = 1500;
        int hp = 120;
        int dust = 2000;
        int candy = 50;

        Pokemon createdPokemon = rocketFactory.createPokemon(index, cp, hp, dust, candy);

        assertNotNull(createdPokemon);
        assertEquals(index, createdPokemon.getIndex());
        assertEquals("Bulbasaur", createdPokemon.getName());
        assertTrue(createdPokemon.getAttack() >= 0 && createdPokemon.getAttack() <= 100);
        assertTrue(createdPokemon.getDefense() >= 0 && createdPokemon.getDefense() <= 100);
        assertTrue(createdPokemon.getStamina() >= 0 && createdPokemon.getStamina() <= 100);
        assertEquals(cp, createdPokemon.getCp());
        assertEquals(hp, createdPokemon.getHp());
        assertEquals(dust, createdPokemon.getDust());
        assertEquals(candy, createdPokemon.getCandy());
    }

    @Test
    public void testRocketCreatePokemonWithNegativeIndex() {
        int index = -1;
        int cp = 3000;
        int hp = 150;
        int dust = 20000;
        int candy = 100;

        Pokemon createdPokemon = rocketFactory.createPokemon(index, cp, hp, dust, candy);

        assertNotNull(createdPokemon);
        assertEquals(index, createdPokemon.getIndex());
        assertEquals("Ash's Pikachu", createdPokemon.getName());
        assertEquals(1000, createdPokemon.getAttack());
        assertEquals(1000, createdPokemon.getDefense());
        assertEquals(1000, createdPokemon.getStamina());
    }

    @Test
    public void testRocketCreatePokemonWithUnknownIndex() {
        int index = 999;
        int cp = 500;
        int hp = 60;
        int dust = 1500;
        int candy = 10;

        Pokemon createdPokemon = rocketFactory.createPokemon(index, cp, hp, dust, candy);

        assertNotNull(createdPokemon);
        assertEquals("MISSINGNO", createdPokemon.getName());
    }

    @Test
    public void testRocketCreatePokemonWithEdgeValues() {
        int index = 1;
        int cp = Integer.MAX_VALUE;
        int hp = Integer.MAX_VALUE;
        int dust = Integer.MAX_VALUE;
        int candy = Integer.MAX_VALUE;

        Pokemon createdPokemon = rocketFactory.createPokemon(index, cp, hp, dust, candy);

        assertNotNull(createdPokemon);
        assertEquals(cp, createdPokemon.getCp());
        assertEquals(hp, createdPokemon.getHp());
        assertEquals(dust, createdPokemon.getDust());
        assertEquals(candy, createdPokemon.getCandy());
    }

    @Test
    public void testRandomStatGenerationRange() {
        int stat = RocketPokemonFactory.generateRandomStat();
        assertTrue(stat >= 0 && stat <= 100, "The random stat should be in range 0-100.");
    }

    @Test
    public void testUnmodifiableMapInRocketFactory() {
        assertThrows(UnsupportedOperationException.class, () -> {
            RocketPokemonFactory.index2name.put(100, "New Pokemon");
        });
    }

    @Test
    public void testMultipleRandomStatsAreDifferent() {
        int stat1 = RocketPokemonFactory.generateRandomStat();
        int stat2 = RocketPokemonFactory.generateRandomStat();
        assertNotEquals(stat1, stat2, "Two random stats should be different.");
    }

    @Test
    public void testRocketCreatePokemonWithZeroStats() {
        int index = 1;
        int cp = 0;
        int hp = 0;
        int dust = 0;
        int candy = 0;

        Pokemon createdPokemon = rocketFactory.createPokemon(index, cp, hp, dust, candy);

        assertNotNull(createdPokemon);
        assertEquals(cp, createdPokemon.getCp());
        assertEquals(hp, createdPokemon.getHp());
        assertEquals(dust, createdPokemon.getDust());
        assertEquals(candy, createdPokemon.getCandy());
    }

}
