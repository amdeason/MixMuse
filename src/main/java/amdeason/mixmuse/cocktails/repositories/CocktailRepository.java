package amdeason.mixmuse.cocktails.repositories;

import amdeason.mixmuse.cocktails.models.Cocktail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CocktailRepository extends JpaRepository<Cocktail, Long> {
}
