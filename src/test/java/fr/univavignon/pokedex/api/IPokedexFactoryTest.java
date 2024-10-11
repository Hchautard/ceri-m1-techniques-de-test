package fr.univavignon.pokedex.api;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

public class IPokedexFactoryTest {

    // Mocks
    private IPokemonMetadataProvider metadataProviderMock;
    private IPokemonFactory pokemonFactoryMock;
    private IPokedexFactory pokedexFactoryMock;
    private IPokedex pokedexMock;

    @BeforeEach
    public void setUp() {
        // Initialisation des mocks avec Mockito
        metadataProviderMock = mock(IPokemonMetadataProvider.class);
        pokemonFactoryMock = mock(IPokemonFactory.class);
        pokedexFactoryMock = mock(IPokedexFactory.class);
        pokedexMock = mock(IPokedex.class);
        
        // Comportement attendu du mock de la factory
        when(pokedexFactoryMock.createPokedex(metadataProviderMock, pokemonFactoryMock)).thenReturn(pokedexMock);
    }

    @Test
    public void testCreatePokedex() {
        // Appel de la méthode à tester
        IPokedex pokedex = pokedexFactoryMock.createPokedex(metadataProviderMock, pokemonFactoryMock);

        // Vérification que la méthode a bien été appelée
        verify(pokedexFactoryMock).createPokedex(metadataProviderMock, pokemonFactoryMock);

        // Vérification que l'objet retourné est bien une instance de IPokedex
        assertNotNull(pokedex);
        assertEquals(pokedexMock, pokedex);
    }

    @Test
    public void testCreatePokedexWithCorrectArguments() {
        // Appel de la méthode à tester
        IPokedex pokedex = pokedexFactoryMock.createPokedex(metadataProviderMock, pokemonFactoryMock);

        // Vérification que createPokedex est appelée avec les bons paramètres
        verify(pokedexFactoryMock).createPokedex(metadataProviderMock, pokemonFactoryMock);

        // Test que le comportement est correct pour ces arguments
        assertSame(pokedexMock, pokedex);
    }

    @Test
    public void testCreatePokedexWhenExceptionThrown() {
        // Simuler une exception lors de l'appel de createPokedex
        when(pokedexFactoryMock.createPokedex(metadataProviderMock, pokemonFactoryMock))
            .thenThrow(new RuntimeException("Factory error"));

        // Vérification que l'exception est bien lancée
        assertThrows(RuntimeException.class, () -> {
            pokedexFactoryMock.createPokedex(metadataProviderMock, pokemonFactoryMock);
        });
    }

    @Test
    public void testCreatePokedexReturnsNonNull() {
        // Appel de la méthode à tester
        IPokedex pokedex = pokedexFactoryMock.createPokedex(metadataProviderMock, pokemonFactoryMock);

        // Vérification que l'objet retourné est non nul
        assertNotNull(pokedex);
    }

    @Test
    public void testCreatePokedexReturnSameInstance() {
        // Appel de la méthode à tester plusieurs fois
        IPokedex pokedex1 = pokedexFactoryMock.createPokedex(metadataProviderMock, pokemonFactoryMock);
        IPokedex pokedex2 = pokedexFactoryMock.createPokedex(metadataProviderMock, pokemonFactoryMock);

        // Vérification que la même instance est retournée à chaque fois
        assertSame(pokedex1, pokedex2);
    }

    @Test
    public void testCreatePokedexMultipleTimes() {
        // Vérification que createPokedex peut être appelée plusieurs fois sans problème
        IPokedex pokedex1 = pokedexFactoryMock.createPokedex(metadataProviderMock, pokemonFactoryMock);
        IPokedex pokedex2 = pokedexFactoryMock.createPokedex(metadataProviderMock, pokemonFactoryMock);

        // Vérification que la méthode a été appelée deux fois
        verify(pokedexFactoryMock, times(2)).createPokedex(metadataProviderMock, pokemonFactoryMock);

        // Vérification que les deux appels retournent la même instance mockée
        assertSame(pokedex1, pokedex2);
    }
}
