package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class IPokemonFactoryTest {

    private IPokemonFactory pokemonFactory;

    @BeforeEach
    public void setUp() {
        // Création d'un mock de l'interface IPokemonFactory
        pokemonFactory = mock(IPokemonFactory.class);
    }

    @Test
    public void testCreatePokemon() {
        // Données pour créer un Pokémon
        int index = 1;
        int cp = 1500;
        int hp = 120;
        int dust = 2000;
        int candy = 50;

        // Création du Pokémon mocké avec les valeurs passées en paramètre
        Pokemon mockPokemon = new Pokemon(index, "Pokemon #1", 50, 50, 50, cp, hp, dust, candy, 100.0);

        // Comportement simulé pour la méthode createPokemon
        when(pokemonFactory.createPokemon(index, cp, hp, dust, candy))
                .thenReturn(mockPokemon);

        // Appel de la méthode createPokemon
        Pokemon createdPokemon = pokemonFactory.createPokemon(index, cp, hp, dust, candy);

        // Vérification que l'objet renvoyé est bien celui attendu
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
        // Données invalides pour créer un Pokémon
        int index = -1; // Index invalide
        int cp = -100;  // CP invalide (négatif)
        int hp = -50;   // HP invalide (négatif)
        int dust = -1000; // Dust invalide (négatif)
        int candy = -10; // Candy invalide (négatif)

        // Comportement simulé : lorsque les paramètres sont invalides, une exception est levée
        when(pokemonFactory.createPokemon(index, cp, hp, dust, candy))
                .thenThrow(new IllegalArgumentException("Les paramètres sont invalides"));

        // Test que l'exception est levée
        assertThrows(IllegalArgumentException.class, () -> {
            pokemonFactory.createPokemon(index, cp, hp, dust, candy);
        });
    }
}
	