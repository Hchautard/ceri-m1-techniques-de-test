package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class IPokemonTrainerFactoryTest {

    private IPokemonTrainerFactory pokemonTrainerFactory;
    private IPokedexFactory pokedexFactory;
    private Team team;  // Utilisation d'une valeur réelle de l'énum Team

    @BeforeEach
    public void setUp() {
        // Mocks des dépendances
        pokedexFactory = mock(IPokedexFactory.class);  // Mock de l'interface IPokedexFactory

        // Création d'un mock de l'interface IPokemonTrainerFactory
        pokemonTrainerFactory = mock(IPokemonTrainerFactory.class); 

        // Initialisation de Team avec une valeur réelle
        team = Team.MYSTIC;  // Utilisation d'une valeur réelle de l'énum Team (MYSTIC, INSTINCT, VALOR)

        // Mock de PokemonTrainer retourné par la méthode createTrainer
        PokemonTrainer trainerMock = mock(PokemonTrainer.class);

        // On configure le comportement du mock pour que createTrainer retourne trainerMock
        when(pokemonTrainerFactory.createTrainer(anyString(), any(Team.class), any(IPokedexFactory.class)))
            .thenReturn(trainerMock);
        
        // Configuration du Pokedex pour le PokemonTrainer mocké
        IPokedex pokedexMock = mock(IPokedex.class);
        when(trainerMock.getPokedex()).thenReturn(pokedexMock);
    }

    @Test
    public void testCreateTrainerIntegration() {
        String trainerName = "Ash Ketchum";

        // Appel de la méthode à tester
        PokemonTrainer trainer = pokemonTrainerFactory.createTrainer(trainerName, team, pokedexFactory);

        // Vérifications des propriétés de l'objet PokemonTrainer retourné
        assertNotNull(trainer, "Trainer should not be null.");
        assertEquals(trainerName, trainer.getName(), "Trainer name should match the provided name.");
        assertEquals(team, trainer.getTeam(), "Trainer's team should match the provided team.");
        assertNotNull(trainer.getPokedex(), "Trainer's Pokedex should not be null.");
    }

    @Test
    public void testCreateTrainerWithInstinctTeam() {
        team = Team.INSTINCT;

        PokemonTrainer trainer = pokemonTrainerFactory.createTrainer("Misty", team, pokedexFactory);

        assertNotNull(trainer);
        assertEquals("Misty", trainer.getName());
        assertEquals(team, trainer.getTeam());
        assertNotNull(trainer.getPokedex());
    }

    @Test
    public void testCreateTrainerWithValorTeam() {
        team = Team.VALOR;

        PokemonTrainer trainer = pokemonTrainerFactory.createTrainer("Brock", team, pokedexFactory);

        assertNotNull(trainer);
        assertEquals("Brock", trainer.getName());
        assertEquals(team, trainer.getTeam());
        assertNotNull(trainer.getPokedex());
    }

}
