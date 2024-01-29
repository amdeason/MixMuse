package amdeason.mixmuse.cocktails.repositories;

import amdeason.mixmuse.cocktails.models.Cocktail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CocktailRepository extends JpaRepository<Cocktail, Long> {
}
