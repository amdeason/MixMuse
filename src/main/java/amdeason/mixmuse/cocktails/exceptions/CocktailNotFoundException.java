package amdeason.mixmuse.cocktails.exceptions;

public class CocktailNotFoundException extends RuntimeException {
    public CocktailNotFoundException(Long id) {
        super("Could not find cocktail " + id);
    }
}
