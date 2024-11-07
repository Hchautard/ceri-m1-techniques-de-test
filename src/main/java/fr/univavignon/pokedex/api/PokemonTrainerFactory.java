package fr.univavignon.pokedex.api;

/**
 * Implementation of IPokemonTrainerFactory to create instances of PokemonTrainer.
 */
public class PokemonTrainerFactory implements IPokemonTrainerFactory {

    /**
     * Creates and returns a PokemonTrainer instance.
     * 
     * @param name Name of the created trainer.
     * @param team Team of the created trainer.
     * @param pokedexFactory Factory to use for creating associated pokedex instance.
     * @return Created trainer instance.
     */
    @Override
    public PokemonTrainer createTrainer(String name, Team team, IPokedexFactory pokedexFactory) {
        // Create a new Pokedex instance using the provided PokedexFactory
        IPokedex pokedex = pokedexFactory.createPokedex(new PokemonMetadataProvider(), new PokemonFactory());
        
        // Return a new PokemonTrainer instance with the given name, team, and created Pokedex
        return new PokemonTrainer(name, team, pokedex);
    }
}
