package fr.univavignon.pokedex.api;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class IPokedexTest {

    private IPokedex pokedexMock;
    private Pokemon pokemonMock;

    @BeforeEach
    public void setUp() {
        // Mock de l'interface IPokedex
        pokedexMock = mock(IPokedex.class);
        
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
        when(pokedexMock.size()).thenReturn(10);

        assertEquals(10, pokedexMock.size(), "The size of the Pokedex should be 10.");
    }

    @Test
    public void testAddPokemon() {
        when(pokedexMock.addPokemon(pokemonMock)).thenReturn(0);

        assertEquals(0, pokedexMock.addPokemon(pokemonMock), "The index of the added Pokemon should be 0.");
    }

    @Test
    public void testGetPokemon() throws PokedexException {
        when(pokedexMock.getPokemon(0)).thenReturn(pokemonMock);

        assertNotNull(pokedexMock.getPokemon(0), "Pokemon at index 0 should not be null.");
    }

    @Test
    public void testGetPokemons() {
        List<Pokemon> pokemons = new ArrayList<>();
        pokemons.add(pokemonMock);

        when(pokedexMock.getPokemons()).thenReturn(pokemons);

        assertNotNull(pokedexMock.getPokemons(), "The Pokedex should return a non-null list of Pokemons.");
        assertEquals(1, pokedexMock.getPokemons().size(), "The Pokedex should contain 1 Pokemon.");
    }

}
