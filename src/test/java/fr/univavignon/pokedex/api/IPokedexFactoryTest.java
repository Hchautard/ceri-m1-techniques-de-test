package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class IPokedexFactoryTest {

    private IPokemonMetadataProvider metadataProviderMock;
    private IPokemonFactory pokemonFactoryMock;
    private IPokedexFactory pokedexFactory;

    @BeforeEach
    public void setUp() {
        // Initialisation des mocks
        metadataProviderMock = mock(IPokemonMetadataProvider.class);
        pokemonFactoryMock = mock(IPokemonFactory.class);
        pokedexFactory = new PokedexFactory(); // Utilisation de la classe réelle
    }

    @Test
    public void testCreatePokedexReturnsNonNullInstance() {
        // Appel de la méthode réelle
        IPokedex pokedex = pokedexFactory.createPokedex(metadataProviderMock, pokemonFactoryMock);

        // Vérification que le pokédex n'est pas nul
        assertNotNull(pokedex, "Le pokédex créé ne doit pas être nul.");
        assertTrue(pokedex instanceof Pokedex, "L'objet retourné doit être une instance de Pokedex.");
    }

    @Test
    public void testCreatePokedexWithValidArguments() {
        // Appel de la méthode réelle
        IPokedex pokedex = pokedexFactory.createPokedex(metadataProviderMock, pokemonFactoryMock);

        // Vérification que les mocks sont utilisés
        assertNotNull(pokedex, "Le pokédex créé ne doit pas être nul.");
        assertDoesNotThrow(() -> pokedexFactory.createPokedex(metadataProviderMock, pokemonFactoryMock), 
                           "La création du pokédex ne doit pas lever d'exception.");
    }

    @Test
    public void testCreatePokedexAndAddPokemon() throws PokedexException {
        // Création de l'instance réelle du pokédex
        IPokedex pokedex = pokedexFactory.createPokedex(metadataProviderMock, pokemonFactoryMock);

        // Mock des métadonnées pour un Pokémon
        when(metadataProviderMock.getPokemonMetadata(0)).thenReturn(new PokemonMetadata(0, "Bulbasaur", 49, 49, 90));
        when(pokemonFactoryMock.createPokemon(0, 100, 100, 100, 100)).thenReturn(new Pokemon(0, "Bulbasaur", 49, 49, 90, 100, 100, 100, 100, 50.0));

        // Ajout du Pokémon au pokédex
        int index = pokedex.addPokemon(new Pokemon(0, "Bulbasaur", 49, 49, 90, 100, 100, 100, 100, 50.0));

        // Vérification de l'index et de la taille du pokédex
        assertEquals(0, index, "Le premier Pokémon ajouté devrait avoir l'index 0.");
        assertEquals(1, pokedex.size(), "La taille du pokédex devrait être de 1 après l'ajout.");
    }

    @Test
    public void testCreateMultiplePokedexInstances() {
        // Création de deux pokédex via la factory
        IPokedex pokedex1 = pokedexFactory.createPokedex(metadataProviderMock, pokemonFactoryMock);
        IPokedex pokedex2 = pokedexFactory.createPokedex(metadataProviderMock, pokemonFactoryMock);

        // Vérification que ce sont bien deux instances distinctes
        assertNotNull(pokedex1, "Le premier pokédex ne doit pas être nul.");
        assertNotNull(pokedex2, "Le second pokédex ne doit pas être nul.");
        assertNotSame(pokedex1, pokedex2, "Chaque appel de createPokedex doit retourner une nouvelle instance.");
    }
}
