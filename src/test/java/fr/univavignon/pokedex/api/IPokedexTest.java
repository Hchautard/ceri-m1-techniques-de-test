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
        pokemonMock = mock(Pokemon.class);
    }

    @Test
    public void testSize() {
        // Configuration du comportement du mock pour retourner une taille spécifique
        when(pokedexMock.size()).thenReturn(10);

        // Vérification que la taille est bien celle attendue
        assertEquals(10, pokedexMock.size(), "The size of the Pokedex should be 10.");
    }

    @Test
    public void testAddPokemon() {
        // Configuration du comportement du mock pour retourner un index unique
        when(pokedexMock.addPokemon(pokemonMock)).thenReturn(0);

        // Vérification que l'ajout de pokemon retourne bien l'index
        assertEquals(0, pokedexMock.addPokemon(pokemonMock), "The index of the added Pokemon should be 0.");
    }

    @Test
    public void testGetPokemon() throws PokedexException {
        // Configuration du mock pour retourner un pokemon à un index spécifique
        when(pokedexMock.getPokemon(0)).thenReturn(pokemonMock);

        // Vérification que le pokemon à l'index 0 est bien retourné
        assertNotNull(pokedexMock.getPokemon(0), "Pokemon at index 0 should not be null.");
    }

    @Test
    public void testGetPokemons() {
        // Création d'une liste de pokémons simulée
        List<Pokemon> pokemons = new ArrayList<>();
        pokemons.add(pokemonMock);

        // Configuration du mock pour retourner la liste des pokémons
        when(pokedexMock.getPokemons()).thenReturn(pokemons);

        // Vérification que la méthode retourne bien la liste des pokémons
        assertNotNull(pokedexMock.getPokemons(), "The Pokedex should return a non-null list of Pokemons.");
        assertEquals(1, pokedexMock.getPokemons().size(), "The Pokedex should contain 1 Pokemon.");
    }

}
