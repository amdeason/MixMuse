package amdeason.mixmuse.cocktails.exceptions;

public class RecipeNotFoundException extends RuntimeException {
    public RecipeNotFoundException(Long id) {
        super("Could not find recipe " + id);
    }
}
