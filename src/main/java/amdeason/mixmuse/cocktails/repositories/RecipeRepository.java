package amdeason.mixmuse.cocktails.repositories;

import amdeason.mixmuse.cocktails.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findByCocktailId(Long cocktailId);

}
