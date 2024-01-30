package amdeason.mixmuse.cocktails.controllers;

import amdeason.mixmuse.cocktails.exceptions.CocktailNotFoundException;
import amdeason.mixmuse.cocktails.exceptions.IngredientNotFoundException;
import amdeason.mixmuse.cocktails.exceptions.RecipeNotFoundException;
import amdeason.mixmuse.cocktails.models.*;
import amdeason.mixmuse.cocktails.repositories.CocktailRepository;
import amdeason.mixmuse.cocktails.repositories.IngredientRepository;
import amdeason.mixmuse.cocktails.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

@RestController
public class IngredientController {
    private final IngredientModelAssembler assembler;
    @Autowired
    private final IngredientRepository ingredientRepository;

    IngredientController(IngredientModelAssembler assembler, IngredientRepository ingredientRepository) {
        this.assembler = assembler;
        this.ingredientRepository= ingredientRepository;
    }

    @GetMapping("/api/v1/cocktails/ingredients/{ingredientId}")
    public EntityModel<Ingredient> getOneIngredient(@PathVariable Long ingredientId) {

        Ingredient ingredient = ingredientRepository.findById(ingredientId) //
                .orElseThrow(() -> new IngredientNotFoundException(ingredientId));

        return assembler.toModel(ingredient);
    }

    @PostMapping("/api/v1/cocktails/ingredients")
    public Ingredient createIngredient(@RequestBody Ingredient newIngredient) {
        return ingredientRepository.save(newIngredient);
    }
}
