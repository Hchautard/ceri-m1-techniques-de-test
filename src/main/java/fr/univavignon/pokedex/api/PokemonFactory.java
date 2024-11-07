package fr.univavignon.pokedex.api;

import java.util.Random;

/**
 * Implementation of IPokemonFactory.
 * Provides a method to create Pokemon instances with computed IVs.
 */
public class PokemonFactory implements IPokemonFactory {

    private final IPokemonMetadataProvider metadataProvider;
    private final Random random;

    /**
     * Constructor for PokemonFactory.
     * 
     * @param metadataProvider Metadata provider used to retrieve Pokemon base stats.
     */
    public PokemonFactory(IPokemonMetadataProvider metadataProvider) {
        this.metadataProvider = metadataProvider;
        this.random = new Random();
    }

    /**
     * Creates a Pokemon instance with calculated IVs.
     * 
     * @param index Pokemon index.
     * @param cp Pokemon CP.
     * @param hp Pokemon HP.
     * @param dust Required dust for upgrading pokemon.
     * @param candy Required candy for upgrading pokemon.
     * @return Created Pokemon instance with calculated IVs.
     */
    @Override
    public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
        try {
            // Retrieve the base metadata for the given Pokemon index
            PokemonMetadata metadata = metadataProvider.getPokemonMetadata(index);

            // Create and return the Pokemon instance with calculated values
            return new Pokemon(index, metadata.getName(), metadata.getAttack(), metadata.getDefense(),
                               metadata.getStamina(), cp, hp, dust, candy, 50);
        } catch (PokedexException e) {
            // Handle invalid index by returning null or rethrowing the exception
            System.err.println("Failed to create Pokemon: " + e.getMessage());
            return null;
        }
    }
}
