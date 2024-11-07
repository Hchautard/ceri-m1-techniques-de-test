package fr.univavignon.pokedex.api;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of IPokemonMetadataProvider.
 * Provides PokemonMetadata for a given pokemon index.
 */
public class PokemonMetadataProvider implements IPokemonMetadataProvider {

    // Static list of metadata for each Pokemon as an example database.
    // In a real application, this data might be retrieved from an external database or API.
    private static final List<PokemonMetadata> POKEMON_METADATA = new ArrayList<>();

    /*static {
        // Example data initialization (id, name, attack, defense, stamina)
        POKEMON_METADATA.add(new PokemonMetadata(0, "Bulbasaur", 126, 126, 90));
        POKEMON_METADATA.add(new PokemonMetadata(1, "Ivysaur", 156, 158, 120));
        POKEMON_METADATA.add(new PokemonMetadata(2, "Venusaur", 198, 200, 160));
        // Add more Pokemon metadata here as needed
    }*/

    /**
     * Retrieves and returns the metadata for the pokemon
     * denoted by the given index.
     * 
     * @param index Index of the pokemon to retrieve metadata for.
     * @return Metadata of the pokemon.
     * @throws PokedexException If the given index is not valid.
     */
    @Override
    public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
        if (index < 0 || index >= POKEMON_METADATA.size()) {
            throw new PokedexException("Invalid Pokemon index: " + index);
        }
        return POKEMON_METADATA.get(index);
    }
}
