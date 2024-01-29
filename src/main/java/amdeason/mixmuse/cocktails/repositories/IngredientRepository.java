package amdeason.mixmuse.cocktails.repositories;

import amdeason.mixmuse.cocktails.models.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}
