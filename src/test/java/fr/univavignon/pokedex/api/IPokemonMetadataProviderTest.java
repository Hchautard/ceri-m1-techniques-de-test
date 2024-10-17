package fr.univavignon.pokedex.api;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IPokemonMetadataProviderTest {
	
	private IPokemonMetadataProvider mockPokemonMetadataProvider;
	
	@BeforeEach
    public void setUp() {
		mockPokemonMetadataProvider = mock(IPokemonMetadataProvider.class);
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
}
