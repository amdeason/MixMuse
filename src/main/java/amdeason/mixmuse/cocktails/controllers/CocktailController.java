package amdeason.mixmuse.cocktails.controllers;

import amdeason.mixmuse.cocktails.exceptions.CocktailNotFoundException;
import amdeason.mixmuse.cocktails.models.Cocktail;
import amdeason.mixmuse.cocktails.models.CocktailModelAssembler;
import amdeason.mixmuse.cocktails.models.Recipe;
import amdeason.mixmuse.cocktails.repositories.CocktailRepository;
import jakarta.persistence.ElementCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class CocktailController {

    private final CocktailModelAssembler assembler;
    @Autowired
    private final CocktailRepository cocktailRepository;

    CocktailController(CocktailModelAssembler assembler, CocktailRepository cocktailRepository) {
        this.assembler = assembler;
        this.cocktailRepository = cocktailRepository;
    }

    @GetMapping("/api/v1/cocktails")
    public CollectionModel<EntityModel<Cocktail>> getAllCocktails() {
        List<EntityModel<Cocktail>> cocktails = cocktailRepository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(cocktails, linkTo(methodOn(CocktailController.class).getAllCocktails()).withSelfRel());
    }

    @GetMapping("/api/v1/cocktails/{id}")
    public EntityModel<Cocktail> getCocktail(@PathVariable Long id) {

        Cocktail cocktail = cocktailRepository.findById(id) //
                .orElseThrow(() -> new CocktailNotFoundException(id));

        return assembler.toModel(cocktail);

    }
    @PostMapping("/api/v1/cocktails")
    Cocktail createCocktail(@RequestBody Cocktail newCocktail) {
        return cocktailRepository.save(newCocktail);
    }

    @PutMapping
    Optional<Cocktail> replaceCocktail(@RequestBody Cocktail newCocktail, @PathVariable Long id) {
        return Optional.of(cocktailRepository.findById(id)
                .map(cocktail -> {
                    cocktail.setName(newCocktail.getName());
                    return cocktailRepository.save(cocktail);
                })
                .orElseGet(() -> {
                    newCocktail.setId(id);
                    return cocktailRepository.save(newCocktail);
                }));
    }

    @DeleteMapping("/api/v1/cocktails/{id}")
    void deleteCocktail(@PathVariable Long id) {
        cocktailRepository.deleteById(id);
    }
}
