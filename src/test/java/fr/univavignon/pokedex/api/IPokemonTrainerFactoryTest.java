package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class IPokemonTrainerFactoryTest {

    private IPokemonTrainerFactory pokemonTrainerFactory;
    private IPokedexFactory pokedexFactory;

    @BeforeEach
    public void setUp() {
        pokedexFactory = mock(IPokedexFactory.class);
        pokemonTrainerFactory = new PokemonTrainerFactory();
    }

    @Test
    public void testCreateTrainerWithValidData() {
        String trainerName = "Ash Ketchum";
        Team team = Team.MYSTIC;

        IPokedex mockPokedex = mock(IPokedex.class);
        when(pokedexFactory.createPokedex(any(PokemonMetadataProvider.class), any(PokemonFactory.class))).thenReturn(mockPokedex);

        PokemonTrainer trainer = pokemonTrainerFactory.createTrainer(trainerName, team, pokedexFactory);

        assertNotNull(trainer, "Le trainer ne doit pas être nul.");
        assertEquals(trainerName, trainer.getName(), "Le nom du trainer doit correspondre.");
        assertEquals(team, trainer.getTeam(), "L'équipe du trainer doit correspondre.");
        assertNotNull(trainer.getPokedex(), "Le Pokedex du trainer ne doit pas être nul.");
    }

    @Test
    public void testCreateTrainerWithNullName() {
        Team team = Team.VALOR;

        IPokedex mockPokedex = mock(IPokedex.class);
        when(pokedexFactory.createPokedex(any(PokemonMetadataProvider.class), any(PokemonFactory.class))).thenReturn(mockPokedex);

        PokemonTrainer trainer = pokemonTrainerFactory.createTrainer(null, team, pokedexFactory);

        assertNotNull(trainer, "Le trainer ne doit pas être nul.");
        assertNull(trainer.getName(), "Le nom du trainer doit être nul.");
        assertEquals(team, trainer.getTeam(), "L'équipe du trainer doit correspondre.");
        assertNotNull(trainer.getPokedex(), "Le Pokedex du trainer ne doit pas être nul.");
    }

    @Test
    public void testCreateTrainerWithNullTeam() {
        String trainerName = "Misty";

        IPokedex mockPokedex = mock(IPokedex.class);
        when(pokedexFactory.createPokedex(any(PokemonMetadataProvider.class), any(PokemonFactory.class))).thenReturn(mockPokedex);

        PokemonTrainer trainer = pokemonTrainerFactory.createTrainer(trainerName, null, pokedexFactory);

        assertNotNull(trainer, "Le trainer ne doit pas être nul.");
        assertEquals(trainerName, trainer.getName(), "Le nom du trainer doit correspondre.");
        assertNull(trainer.getTeam(), "L'équipe du trainer doit être nulle.");
        assertNotNull(trainer.getPokedex(), "Le Pokedex du trainer ne doit pas être nul.");
    }

    @Test
    public void testCreateTrainerWithNullPokedexFactory() {
        String trainerName = "Gary Oak";
        Team team = Team.INSTINCT;

        Exception exception = assertThrows(NullPointerException.class, () -> {
            pokemonTrainerFactory.createTrainer(trainerName, team, null);
        });

        assertNotNull(exception, "Une exception devrait être levée pour une PokedexFactory nulle.");
    }

    @Test
    public void testIntegrationWithCustomPokedexFactory() {
        String trainerName = "Brock";
        Team team = Team.VALOR;

        // Mocking a custom PokedexFactory
        IPokedex mockPokedex = mock(IPokedex.class);
        when(mockPokedex.size()).thenReturn(5);
        when(pokedexFactory.createPokedex(any(PokemonMetadataProvider.class), any(PokemonFactory.class))).thenReturn(mockPokedex);

        PokemonTrainer trainer = pokemonTrainerFactory.createTrainer(trainerName, team, pokedexFactory);

        assertNotNull(trainer, "Le trainer ne doit pas être nul.");
        assertEquals(trainerName, trainer.getName(), "Le nom du trainer doit correspondre.");
        assertEquals(team, trainer.getTeam(), "L'équipe du trainer doit correspondre.");
        assertNotNull(trainer.getPokedex(), "Le Pokedex du trainer ne doit pas être nul.");
        assertEquals(5, trainer.getPokedex().size(), "Le Pokedex devrait contenir 5 éléments.");
    }
}
