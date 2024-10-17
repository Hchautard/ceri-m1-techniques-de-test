package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class IPokemonFactoryTest {

    private IPokemonFactory pokemonFactory;

    @BeforeEach
    public void setUp() {
        pokemonFactory = mock(IPokemonFactory.class);
    }

    @Test
    public void testCreatePokemon() {
        int index = 1;
        int cp = 1500;
        int hp = 120;
        int dust = 2000;
        int candy = 50;

        Pokemon mockPokemon = new Pokemon(index, "Pokemon #1", 50, 50, 50, cp, hp, dust, candy, 100.0);

        when(pokemonFactory.createPokemon(index, cp, hp, dust, candy))
                .thenReturn(mockPokemon);

        Pokemon createdPokemon = pokemonFactory.createPokemon(index, cp, hp, dust, candy);

        assertNotNull(createdPokemon, "Le Pokémon créé ne doit pas être nul");
        assertEquals(index, createdPokemon.getIndex(), "L'index du Pokémon ne correspond pas");
        assertEquals("Pokemon #1", createdPokemon.getName(), "Le nom du Pokémon ne correspond pas");
        assertEquals(cp, createdPokemon.getCp(), "Les CP du Pokémon ne correspondent pas");
        assertEquals(hp, createdPokemon.getHp(), "Les HP du Pokémon ne correspondent pas");
        assertEquals(dust, createdPokemon.getDust(), "La poussière ne correspond pas");
        assertEquals(candy, createdPokemon.getCandy(), "Les bonbons ne correspondent pas");
    }

    @Test
    public void testCreatePokemon_invalidData() {
        int index = -1; 
        int cp = -100; 
        int hp = -50;  
        int dust = -1000;
        int candy = -10; 

        when(pokemonFactory.createPokemon(index, cp, hp, dust, candy))
                .thenThrow(new IllegalArgumentException("Les paramètres sont invalides"));

        assertThrows(IllegalArgumentException.class, () -> {
            pokemonFactory.createPokemon(index, cp, hp, dust, candy);
        });
    }
    
    @Test
    public void testCreatePokemon_maxValues() {
        int index = 150;
        int cp = Integer.MAX_VALUE;
        int hp = Integer.MAX_VALUE;
        int dust = Integer.MAX_VALUE;
        int candy = Integer.MAX_VALUE;

        Pokemon mockPokemon = new Pokemon(index, "Mewtwo", 100, 100, 100, cp, hp, dust, candy, 100.0);

        when(pokemonFactory.createPokemon(index, cp, hp, dust, candy))
                .thenReturn(mockPokemon);

        Pokemon createdPokemon = pokemonFactory.createPokemon(index, cp, hp, dust, candy);

        assertNotNull(createdPokemon, "Le Pokémon créé ne doit pas être nul");
        assertEquals(index, createdPokemon.getIndex(), "L'index du Pokémon ne correspond pas");
        assertEquals("Mewtwo", createdPokemon.getName(), "Le nom du Pokémon ne correspond pas");
        assertEquals(cp, createdPokemon.getCp(), "Les CP du Pokémon ne correspondent pas");
        assertEquals(hp, createdPokemon.getHp(), "Les HP du Pokémon ne correspondent pas");
        assertEquals(dust, createdPokemon.getDust(), "La poussière ne correspond pas");
        assertEquals(candy, createdPokemon.getCandy(), "Les bonbons ne correspondent pas");
    }
    
    @Test
    public void testCreatePokemon_zeroValues() {
        int index = 10;
        int cp = 0;
        int hp = 0;
        int dust = 0;
        int candy = 0;

        Pokemon mockPokemon = new Pokemon(index, "Mew", 10, 10, 10, cp, hp, dust, candy, 10.0);

        when(pokemonFactory.createPokemon(index, cp, hp, dust, candy))
                .thenReturn(mockPokemon);

        Pokemon createdPokemon = pokemonFactory.createPokemon(index, cp, hp, dust, candy);

        assertNotNull(createdPokemon, "Le Pokémon créé ne doit pas être nul");
        assertEquals(index, createdPokemon.getIndex(), "L'index du Pokémon ne correspond pas");
        assertEquals("Mew", createdPokemon.getName(), "Le nom du Pokémon ne correspond pas");
        assertEquals(cp, createdPokemon.getCp(), "Les CP du Pokémon ne correspondent pas");
        assertEquals(hp, createdPokemon.getHp(), "Les HP du Pokémon ne correspondent pas");
        assertEquals(dust, createdPokemon.getDust(), "La poussière ne correspond pas");
        assertEquals(candy, createdPokemon.getCandy(), "Les bonbons ne correspondent pas");
    }

    
    @Test
    public void testCreatePokemon_indexZero() {
        int index = 0;
        int cp = 100;
        int hp = 100;
        int dust = 500;
        int candy = 10;

        Pokemon mockPokemon = new Pokemon(index, "Bulbizarre", 49, 49, 49, cp, hp, dust, candy, 50.0);

        when(pokemonFactory.createPokemon(index, cp, hp, dust, candy))
                .thenReturn(mockPokemon);

        Pokemon createdPokemon = pokemonFactory.createPokemon(index, cp, hp, dust, candy);

        assertNotNull(createdPokemon, "Le Pokémon créé ne doit pas être nul");
        assertEquals(index, createdPokemon.getIndex(), "L'index du Pokémon ne correspond pas");
        assertEquals("Bulbizarre", createdPokemon.getName(), "Le nom du Pokémon ne correspond pas");
        assertEquals(cp, createdPokemon.getCp(), "Les CP du Pokémon ne correspondent pas");
        assertEquals(hp, createdPokemon.getHp(), "Les HP du Pokémon ne correspondent pas");
        assertEquals(dust, createdPokemon.getDust(), "La poussière ne correspond pas");
        assertEquals(candy, createdPokemon.getCandy(), "Les bonbons ne correspondent pas");
    }

    @Test
    public void testCreatePokemon_sameValues() {
        int index = 25;
        int cp = 100;
        int hp = 100;
        int dust = 100;
        int candy = 100;

        Pokemon mockPokemon = new Pokemon(index, "Pikachu", 35, 35, 35, cp, hp, dust, candy, 100.0);

        when(pokemonFactory.createPokemon(index, cp, hp, dust, candy))
                .thenReturn(mockPokemon);

        Pokemon createdPokemon = pokemonFactory.createPokemon(index, cp, hp, dust, candy);

        assertNotNull(createdPokemon, "Le Pokémon créé ne doit pas être nul");
        assertEquals(index, createdPokemon.getIndex(), "L'index du Pokémon ne correspond pas");
        assertEquals("Pikachu", createdPokemon.getName(), "Le nom du Pokémon ne correspond pas");
        assertEquals(cp, createdPokemon.getCp(), "Les CP du Pokémon ne correspondent pas");
        assertEquals(hp, createdPokemon.getHp(), "Les HP du Pokémon ne correspondent pas");
        assertEquals(dust, createdPokemon.getDust(), "La poussière ne correspond pas");
        assertEquals(candy, createdPokemon.getCandy(), "Les bonbons ne correspondent pas");
    }

    @Test
    public void testCreatePokemon_nullValues() {
        int index = 10;
        int cp = 300;
        int hp = 120;
        int dust = 500;
        int candy = 30;

        // Assuming name is nullable, and it may still create the Pokémon.
        Pokemon mockPokemon = new Pokemon(index, null, 10, 10, 10, cp, hp, dust, candy, 50.0);

        when(pokemonFactory.createPokemon(index, cp, hp, dust, candy))
                .thenReturn(mockPokemon);

        Pokemon createdPokemon = pokemonFactory.createPokemon(index, cp, hp, dust, candy);

        assertNotNull(createdPokemon, "Le Pokémon créé ne doit pas être nul");
        assertNull(createdPokemon.getName(), "Le nom du Pokémon devrait être nul");
        assertEquals(cp, createdPokemon.getCp(), "Les CP du Pokémon ne correspondent pas");
        assertEquals(hp, createdPokemon.getHp(), "Les HP du Pokémon ne correspondent pas");
    }


}
	