package fr.univavignon.pokedex.api;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IPokemonMetadataProviderTest {
	
	private IPokemonMetadataProvider mockPokemonMetadataProvider;
	private PokemonMetadataProvider pokemonMetadataProvider;
	
	@BeforeEach
    public void setUp() {
		mockPokemonMetadataProvider = mock(IPokemonMetadataProvider.class);
		pokemonMetadataProvider = new PokemonMetadataProvider();
    }
	
	@Test
    public void ValidetPokemonMetadataTest_Zero() throws PokedexException{

        PokemonMetadata PokemonEspece = new PokemonMetadata(0,"Bulbizarre",126,126,90);
        
        when(mockPokemonMetadataProvider.getPokemonMetadata(0)).thenReturn(PokemonEspece);
        mockPokemonMetadataProvider.getPokemonMetadata(PokemonEspece.getIndex());
        
        verify(mockPokemonMetadataProvider).getPokemonMetadata(PokemonEspece.getIndex());

    }
	
	@Test
    public void ValidetPokemonMetadataEqualityTest_getAttack() throws PokedexException{
		
        PokemonMetadata PokemonEspece = new PokemonMetadata(0,"Bulbizarre",126,126,90);
        
        when(mockPokemonMetadataProvider.getPokemonMetadata(0)).thenReturn(PokemonEspece);
        PokemonMetadata PokemonMetaData = mockPokemonMetadataProvider.getPokemonMetadata(PokemonEspece.getIndex());
        
        verify(mockPokemonMetadataProvider).getPokemonMetadata(PokemonEspece.getIndex());
        assertEquals(PokemonEspece.getAttack(), PokemonMetaData.getAttack()); 

    }
	
	@Test
    public void ValidetPokemonMetadataEqualityTest_Zero() throws PokedexException{
		
        PokemonMetadata PokemonEspece = new PokemonMetadata(0,"Bulbizarre",126,126,90);
        
        when(mockPokemonMetadataProvider.getPokemonMetadata(0)).thenReturn(PokemonEspece);
        PokemonMetadata PokemonMetaData = mockPokemonMetadataProvider.getPokemonMetadata(PokemonEspece.getIndex());
        
        verify(mockPokemonMetadataProvider).getPokemonMetadata(PokemonEspece.getIndex());
        assertEquals(PokemonEspece, PokemonMetaData);
	}
	
	@Test
	public void validatePokemonMetadataEqualityTest_getDefense() throws PokedexException {
	    PokemonMetadata pokemonEspece = new PokemonMetadata(1, "Ivysaur", 156, 158, 120);

	    when(mockPokemonMetadataProvider.getPokemonMetadata(1)).thenReturn(pokemonEspece);
	    PokemonMetadata pokemonMetaData = mockPokemonMetadataProvider.getPokemonMetadata(pokemonEspece.getIndex());

	    verify(mockPokemonMetadataProvider).getPokemonMetadata(pokemonEspece.getIndex());
	    assertEquals(pokemonEspece.getDefense(), pokemonMetaData.getDefense());
	}

	@Test
	public void validatePokemonMetadataEqualityTest_getStamina() throws PokedexException {
	    PokemonMetadata pokemonEspece = new PokemonMetadata(2, "Venusaur", 198, 200, 160);

	    when(mockPokemonMetadataProvider.getPokemonMetadata(2)).thenReturn(pokemonEspece);
	    PokemonMetadata pokemonMetaData = mockPokemonMetadataProvider.getPokemonMetadata(pokemonEspece.getIndex());

	    verify(mockPokemonMetadataProvider).getPokemonMetadata(pokemonEspece.getIndex());
	    assertEquals(pokemonEspece.getStamina(), pokemonMetaData.getStamina());
	}

	@Test
	public void validatePokemonMetadataForInvalidIndex() {
	    assertThrows(PokedexException.class, () -> {
	        when(mockPokemonMetadataProvider.getPokemonMetadata(-1))
	            .thenThrow(new PokedexException("Invalid Pokemon index"));
	        mockPokemonMetadataProvider.getPokemonMetadata(-1);
	    });
	}

	@Test
	public void validatePokemonMetadata_NameConsistency() throws PokedexException {
	    PokemonMetadata pokemonEspece = new PokemonMetadata(3, "Charmander", 128, 108, 78);

	    when(mockPokemonMetadataProvider.getPokemonMetadata(3)).thenReturn(pokemonEspece);
	    PokemonMetadata pokemonMetaData = mockPokemonMetadataProvider.getPokemonMetadata(pokemonEspece.getIndex());

	    verify(mockPokemonMetadataProvider).getPokemonMetadata(pokemonEspece.getIndex());
	    assertEquals(pokemonEspece.getName(), pokemonMetaData.getName());
	}

	@Test
	public void validatePokemonMetadata_IndexConsistency() throws PokedexException {
	    PokemonMetadata pokemonEspece = new PokemonMetadata(4, "Squirtle", 112, 142, 88);

	    when(mockPokemonMetadataProvider.getPokemonMetadata(4)).thenReturn(pokemonEspece);
	    PokemonMetadata pokemonMetaData = mockPokemonMetadataProvider.getPokemonMetadata(pokemonEspece.getIndex());

	    verify(mockPokemonMetadataProvider).getPokemonMetadata(pokemonEspece.getIndex());
	    assertEquals(pokemonEspece.getIndex(), pokemonMetaData.getIndex());
	}

	@Test
	public void validatePokemonMetadataForLargeIndex() {
	    assertThrows(PokedexException.class, () -> {
	        when(mockPokemonMetadataProvider.getPokemonMetadata(9999))
	            .thenThrow(new PokedexException("Invalid Pokemon index"));
	        mockPokemonMetadataProvider.getPokemonMetadata(9999);
	    });
	}
	

    @Test
    public void testGetPokemonMetadata_InvalidNegativeIndex() {
        int invalidIndex = -1;

        PokedexException exception = assertThrows(PokedexException.class, () -> {
            pokemonMetadataProvider.getPokemonMetadata(invalidIndex);
        });

        assertEquals("Invalid Pokemon index: -1", exception.getMessage(),
                "Exception message should indicate invalid index.");
    }

    @Test
    public void testGetPokemonMetadata_IndexOutOfBounds() {
        int invalidIndex = 9999; // Index out of bounds

        PokedexException exception = assertThrows(PokedexException.class, () -> {
            pokemonMetadataProvider.getPokemonMetadata(invalidIndex);
        });

        assertEquals("Invalid Pokemon index: 9999", exception.getMessage(),
                "Exception message should indicate index is out of bounds.");
    }

}
