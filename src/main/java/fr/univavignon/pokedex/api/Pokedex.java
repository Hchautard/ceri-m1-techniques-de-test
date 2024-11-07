package fr.univavignon.pokedex.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the IPokedex interface.
 * A Pokedex stores information about captured Pokemon and their metadata.
 */
public class Pokedex implements IPokedex {

	private final IPokemonMetadataProvider metadataProvider;
	private final IPokemonFactory pokemonFactory;
    private final List<Pokemon> pokemons;

    public Pokedex(IPokemonMetadataProvider metadataProvider, IPokemonFactory pokemonFactory) {
    	this.metadataProvider = metadataProvider;
    	this.pokemonFactory = pokemonFactory;
        this.pokemons = new ArrayList<>();
    }

    @Override
    public int size() {
        return pokemons.size();
    }

    @Override
    public int addPokemon(Pokemon pokemon) {
        pokemons.add(pokemon);
        return pokemons.size() - 1;  // Returns the index of the newly added pokemon
    }

    @Override
    public Pokemon getPokemon(int id) throws PokedexException {
        if (id < 0 || id >= pokemons.size()) {
            throw new PokedexException("Invalid Pokemon ID: " + id);
        }
        return pokemons.get(id);
    }

    @Override
    public List<Pokemon> getPokemons() {
        return Collections.unmodifiableList(pokemons);
    }

    @Override
    public List<Pokemon> getPokemons(Comparator<Pokemon> order) {
        return pokemons.stream()
                       .sorted(order)
                       .collect(Collectors.toUnmodifiableList());
    }

    // Methods from IPokemonMetadataProvider interface
    @Override
    public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
        return metadataProvider.getPokemonMetadata(index);
    }

    // Methods from IPokemonFactory interface
    @Override
    public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
        return pokemonFactory.createPokemon(index, cp, hp, dust, candy);
    }
}

