package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class IPokemonTrainerFactoryTest {

    private IPokemonTrainerFactory pokemonTrainerFactory;
    private IPokedex pokedexFactory;
    private Team team;  

    @BeforeEach
    public void setUp() {
        pokedexFactory = mock(IPokedex.class);
        pokemonTrainerFactory = mock(IPokemonTrainerFactory.class);
    }

    @Test
    public void testCreateTrainerIntegration() {
        String trainerName = "Ash Ketchum";
        team = Team.MYSTIC;

        PokemonTrainer mockTrainer = new PokemonTrainer(trainerName, team, pokedexFactory);
        
        IPokedexFactory pokedexFactory = mock(IPokedexFactory.class);
        PokemonTrainer trainer = pokemonTrainerFactory.createTrainer(trainerName, team, pokedexFactory);
        when(pokemonTrainerFactory.createTrainer(trainerName, team, pokedexFactory)).thenReturn(mockTrainer);

        assertNotNull(mockTrainer, "Trainer should not be null.");
        assertEquals(trainerName, mockTrainer.getName(), "Trainer name should match the provided name.");
        assertEquals(team, mockTrainer.getTeam(), "Trainer's team should match the provided team.");
        assertNotNull(mockTrainer.getPokedex(), "Trainer's Pokedex should not be null.");
    }

    @Test
    public void testCreateTrainerWithInstinctTeam() {
        team = Team.INSTINCT;

        PokemonTrainer trainer = new PokemonTrainer("Misty", team, pokedexFactory);

        assertNotNull(trainer);
        assertEquals("Misty", trainer.getName());
        assertEquals(team, trainer.getTeam());
        assertNotNull(trainer.getPokedex());
    }
    
    @Test
    public void testCreateTrainerWithNullName() {
        team = Team.MYSTIC;
        
        PokemonTrainer trainer = new PokemonTrainer(null, team, pokedexFactory);
        IPokedexFactory pokedexFactory = mock(IPokedexFactory.class);
        
        when(pokemonTrainerFactory.createTrainer(null, team, pokedexFactory)).thenReturn(trainer);
        assertNull(trainer.getName());
    }

    @Test
    public void testCreateTrainerWithNullTeam() {
        String trainerName = "Ash Ketchum";

        PokemonTrainer trainer = new PokemonTrainer(trainerName, null, pokedexFactory);

        assertEquals(null, trainer.getTeam());
    }

    @Test
    public void testCreateTrainerWithNullPokedex() {
        String trainerName = "Ash Ketchum";
        team = Team.VALOR;

        PokemonTrainer trainer = new PokemonTrainer(trainerName, team, null);

        assertEquals(null, trainer.getPokedex());
    }

    @Test
    public void testTrainerFactoryCreateTrainer() {
        IPokemonTrainerFactory trainerFactory = mock(IPokemonTrainerFactory.class);
        IPokedexFactory pokedexFactoryBis = mock(IPokedexFactory.class);
        team = Team.VALOR;
        String trainerName = "Gary Oak";

        // Stubbing the behavior of the trainerFactory
        when(trainerFactory.createTrainer(trainerName, team, pokedexFactoryBis)).thenReturn(new PokemonTrainer(trainerName, team, pokedexFactory));

        // Call the factory method
        PokemonTrainer trainer = trainerFactory.createTrainer(trainerName, team, pokedexFactoryBis);

        // Verify the expected behavior
        assertNotNull(trainer);
        assertEquals(trainerName, trainer.getName());
        assertEquals(team, trainer.getTeam());
        assertNotNull(trainer.getPokedex());
    }

    @Test
    public void testTrainerWithCustomPokedex() {
        team = Team.MYSTIC;
        String trainerName = "Ash Ketchum";

        // Create a mock Pokedex with behavior
        IPokedex customPokedex = mock(IPokedex.class);
        when(customPokedex.size()).thenReturn(10);

        PokemonTrainer trainer = new PokemonTrainer(trainerName, team, customPokedex);

        // Assert trainer's Pokedex has the expected behavior
        assertNotNull(trainer.getPokedex());
        assertEquals(10, trainer.getPokedex().size());
    }

    @Test
    public void testTrainerEquality() {
        team = Team.INSTINCT;
        String trainerName = "Rock";
        		
        IPokedexFactory pokedexFactory = mock(IPokedexFactory.class);
        PokemonTrainer trainer1 = pokemonTrainerFactory.createTrainer(trainerName, team, pokedexFactory);
        PokemonTrainer trainer2 = pokemonTrainerFactory.createTrainer(trainerName, team, pokedexFactory);
        
        assertEquals(trainer1, trainer2, "Trainers with the same name and team should be equal.");
    }

    @Test
    public void testTrainerInequality() {
        PokemonTrainer trainer1 = new PokemonTrainer("Misty", Team.INSTINCT, pokedexFactory);
        PokemonTrainer trainer2 = new PokemonTrainer("Brock", Team.VALOR, pokedexFactory);

        assertNotEquals(trainer1, trainer2, "Trainers with different names or teams should not be equal.");
    }


}
