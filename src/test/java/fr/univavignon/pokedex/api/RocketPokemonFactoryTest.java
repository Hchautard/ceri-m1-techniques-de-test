package fr.univavignon.pokedex.imp;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.univavignon.pokedex.api.Pokemon;

class RocketPokemonFactoryTest {

    private RocketPokemonFactory factory;

    @BeforeEach
    void setUp() {
        factory = new RocketPokemonFactory();
    }

    @Test
    void testCreatePokemonWithValidIndex() {
        // Test with a valid index
        int index = 1; // Bulbasaur
        int cp = 500;
        int hp = 60;
        int dust = 4000;
        int candy = 3;
        
        Pokemon pokemon = factory.createPokemon(index, cp, hp, dust, candy);
        
        assertNotNull(pokemon);
        assertEquals(index, pokemon.getIndex());
        assertEquals("Bulbasaur", pokemon.getName());
        assertTrue(pokemon.getAttack() >= 0 && pokemon.getAttack() <= 100);
        assertTrue(pokemon.getDefense() >= 0 && pokemon.getDefense() <= 100);
        assertTrue(pokemon.getStamina() >= 0 && pokemon.getStamina() <= 100);
        assertEquals(cp, pokemon.getCp());
        assertEquals(hp, pokemon.getHp());
        assertEquals(dust, pokemon.getDust());
        assertEquals(candy, pokemon.getCandy());
        assertEquals(1.0, pokemon.getIv());
    }

    @Test
    void testCreatePokemonWithInvalidIndex() {
        // Test with an invalid index (not in the map)
        int index = 999; 
        int cp = 200;
        int hp = 20;
        int dust = 1000;
        int candy = 1;
        
        Pokemon pokemon = factory.createPokemon(index, cp, hp, dust, candy);
        
        assertNotNull(pokemon);
        assertEquals(index, pokemon.getIndex());
        assertEquals("MISSINGNO", pokemon.getName()); // Because index 999 is not in the map
        assertTrue(pokemon.getAttack() >= 0 && pokemon.getAttack() <= 100);
        assertTrue(pokemon.getDefense() >= 0 && pokemon.getDefense() <= 100);
        assertTrue(pokemon.getStamina() >= 0 && pokemon.getStamina() <= 100);
        assertEquals(cp, pokemon.getCp());
        assertEquals(hp, pokemon.getHp());
        assertEquals(dust, pokemon.getDust());
        assertEquals(candy, pokemon.getCandy());
        assertEquals(1.0, pokemon.getIv());
    }

    @Test
    void testCreatePokemonWithNegativeIndex() {
        // Test with a negative index, which should create Ash's Pikachu
        int index = -1; 
        int cp = 3000;
        int hp = 150;
        int dust = 20000;
        int candy = 100;
        
        Pokemon pokemon = factory.createPokemon(index, cp, hp, dust, candy);
        
        assertNotNull(pokemon);
        assertEquals(index, pokemon.getIndex());
        assertEquals("Ash's Pikachu", pokemon.getName());
        assertEquals(1000, pokemon.getAttack()); // Attack is set to 1000 for negative indices
        assertEquals(1000, pokemon.getDefense()); // Defense is set to 1000 for negative indices
        assertEquals(1000, pokemon.getStamina()); // Stamina is set to 1000 for negative indices
        assertEquals(cp, pokemon.getCp());
        assertEquals(hp, pokemon.getHp());
        assertEquals(dust, pokemon.getDust());
        assertEquals(candy, pokemon.getCandy());
        assertEquals(0.0, pokemon.getIv()); // IV is set to 0 for negative indices
    }

    @Test
    void testCreatePokemonWithZeroIndex() {
        // Test with index 0, which should correspond to MISSINGNO
        int index = 0; 
        int cp = 100;
        int hp = 50;
        int dust = 500;
        int candy = 1;
        
        Pokemon pokemon = factory.createPokemon(index, cp, hp, dust, candy);
        
        assertNotNull(pokemon);
        assertEquals(index, pokemon.getIndex());
        assertEquals("MISSINGNO", pokemon.getName());
        assertTrue(pokemon.getAttack() >= 0 && pokemon.getAttack() <= 100);
        assertTrue(pokemon.getDefense() >= 0 && pokemon.getDefense() <= 100);
        assertTrue(pokemon.getStamina() >= 0 && pokemon.getStamina() <= 100);
        assertEquals(cp, pokemon.getCp());
        assertEquals(hp, pokemon.getHp());
        assertEquals(dust, pokemon.getDust());
        assertEquals(candy, pokemon.getCandy());
        assertEquals(1.0, pokemon.getIv());
    }

    @Test
    void testCreatePokemonWithRandomStats() {
        // Test multiple creations to ensure random stats are working properly
        int index = 1; 
        int cp = 300;
        int hp = 60;
        int dust = 1500;
        int candy = 2;
        
        Pokemon pokemon1 = factory.createPokemon(index, cp, hp, dust, candy);
        Pokemon pokemon2 = factory.createPokemon(index, cp, hp, dust, candy);
        
        assertNotEquals(pokemon1.getAttack(), pokemon2.getAttack(), "Random attack values should differ.");
        assertNotEquals(pokemon1.getDefense(), pokemon2.getDefense(), "Random defense values should differ.");
        assertNotEquals(pokemon1.getStamina(), pokemon2.getStamina(), "Random stamina values should differ.");
    }

    @Test
    void testUnmodifiableMap() {
        // Check if the map is unmodifiable
        assertThrows(UnsupportedOperationException.class, () -> {
            RocketPokemonFactory.index2name.put(999, "New Pokemon");
        });
    }

    @Test
    void testPokemonCreationWithExtremeStats() {
        // Check Pokémon with maximum possible stats
        int index = 1; 
        int cp = Integer.MAX_VALUE;
        int hp = Integer.MAX_VALUE;
        int dust = Integer.MAX_VALUE;
        int candy = Integer.MAX_VALUE;
        
        Pokemon pokemon = factory.createPokemon(index, cp, hp, dust, candy);
        
        assertNotNull(pokemon);
        assertEquals(index, pokemon.getIndex());
        assertEquals("Bulbasaur", pokemon.getName());
        assertTrue(pokemon.getAttack() >= 0 && pokemon.getAttack() <= 100);
        assertTrue(pokemon.getDefense() >= 0 && pokemon.getDefense() <= 100);
        assertTrue(pokemon.getStamina() >= 0 && pokemon.getStamina() <= 100);
        assertEquals(cp, pokemon.getCp());
        assertEquals(hp, pokemon.getHp());
        assertEquals(dust, pokemon.getDust());
        assertEquals(candy, pokemon.getCandy());
        assertEquals(1.0, pokemon.getIv());
    }

    @Test
    void testPokemonCreationWithZeroStats() {
        // Check Pokémon with zero stats
        int index = 1; 
        int cp = 0;
        int hp = 0;
        int dust = 0;
        int candy = 0;
        
        Pokemon pokemon = factory.createPokemon(index, cp, hp, dust, candy);
        
        assertNotNull(pokemon);
        assertEquals(index, pokemon.getIndex());
        assertEquals("Bulbasaur", pokemon.getName());
        assertTrue(pokemon.getAttack() >= 0 && pokemon.getAttack() <= 100);
        assertTrue(pokemon.getDefense() >= 0 && pokemon.getDefense() <= 100);
        assertTrue(pokemon.getStamina() >= 0 && pokemon.getStamina() <= 100);
        assertEquals(cp, pokemon.getCp());
        assertEquals(hp, pokemon.getHp());
        assertEquals(dust, pokemon.getDust());
        assertEquals(candy, pokemon.getCandy());
        assertEquals(1.0, pokemon.getIv());
    }
}
