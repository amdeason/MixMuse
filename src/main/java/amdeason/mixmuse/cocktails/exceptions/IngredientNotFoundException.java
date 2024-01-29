package amdeason.mixmuse.cocktails.exceptions;

public class IngredientNotFoundException extends RuntimeException {
    public IngredientNotFoundException(Long id) {
        super("Could not find ingredient" + id);
    }
}

