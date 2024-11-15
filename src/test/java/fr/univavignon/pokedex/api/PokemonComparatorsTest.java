package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PokemonComparatorsTest {

    private Pokemon pokemon1;
    private Pokemon pokemon2;
    private Pokemon pokemon3;
    private List<Pokemon> pokemonList;

    @BeforeEach
    public void setUp() {
        pokemon1 = new Pokemon(1, "Bulbasaur", 49, 49, 90, 1200, 100, 500, 50, 80.0);
        pokemon2 = new Pokemon(2, "Ivysaur", 62, 63, 120, 1500, 110, 800, 100, 85.0);
        pokemon3 = new Pokemon(3, "Venusaur", 82, 83, 150, 2000, 130, 1000, 150, 95.0);

        pokemonList = new ArrayList<>();
        pokemonList.add(pokemon3);
        pokemonList.add(pokemon1);
        pokemonList.add(pokemon2);
    }

    @Test
    public void testComparatorByName() {
        Collections.sort(pokemonList, PokemonComparators.NAME);

        assertEquals("Bulbasaur", pokemonList.get(0).getName());
        assertEquals("Ivysaur", pokemonList.get(1).getName());
        assertEquals("Venusaur", pokemonList.get(2).getName());
    }

    @Test
    public void testComparatorByIndex() {
        Collections.sort(pokemonList, PokemonComparators.INDEX);

        assertEquals(1, pokemonList.get(0).getIndex());
        assertEquals(2, pokemonList.get(1).getIndex());
        assertEquals(3, pokemonList.get(2).getIndex());
    }

    @Test
    public void testComparatorByCp() {
        Collections.sort(pokemonList, PokemonComparators.CP);

        assertEquals(1200, pokemonList.get(0).getCp());
        assertEquals(1500, pokemonList.get(1).getCp());
        assertEquals(2000, pokemonList.get(2).getCp());
    }

    @Test
    public void testComparatorEquality() {
        Pokemon sameAsPokemon1 = new Pokemon(1, "Bulbasaur", 49, 49, 90, 1200, 100, 500, 50, 80.0);

        assertEquals(0, PokemonComparators.NAME.compare(pokemon1, sameAsPokemon1));
        assertEquals(0, PokemonComparators.INDEX.compare(pokemon1, sameAsPokemon1));
        assertEquals(0, PokemonComparators.CP.compare(pokemon1, sameAsPokemon1));
    }

    @Test
    public void testComparatorInequality() {
        assertTrue(PokemonComparators.NAME.compare(pokemon1, pokemon2) < 0);
        assertTrue(PokemonComparators.INDEX.compare(pokemon1, pokemon2) < 0);
        assertTrue(PokemonComparators.CP.compare(pokemon1, pokemon2) < 0);

        assertTrue(PokemonComparators.NAME.compare(pokemon3, pokemon1) > 0);
        assertTrue(PokemonComparators.INDEX.compare(pokemon3, pokemon1) > 0);
        assertTrue(PokemonComparators.CP.compare(pokemon3, pokemon1) > 0);
    }
}
