package amdeason.mixmuse.cocktails.controllers;

import amdeason.mixmuse.cocktails.exceptions.CocktailNotFoundException;
import amdeason.mixmuse.cocktails.exceptions.RecipeNotFoundException;
import amdeason.mixmuse.cocktails.models.Cocktail;
import amdeason.mixmuse.cocktails.models.Ingredient;
import amdeason.mixmuse.cocktails.models.Recipe;
import amdeason.mixmuse.cocktails.models.RecipeModelAssembler;
import amdeason.mixmuse.cocktails.repositories.CocktailRepository;
import amdeason.mixmuse.cocktails.repositories.IngredientRepository;
import amdeason.mixmuse.cocktails.repositories.RecipeRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class RecipeController {
    private final RecipeModelAssembler assembler;
    @Autowired
    private final RecipeRepository recipeRepository;
    @Autowired
    private final IngredientRepository ingredientRepository;
    @Autowired
    private final CocktailRepository cocktailRepository;

    RecipeController(RecipeModelAssembler assembler, RecipeRepository recipeRepository, IngredientRepository ingredientRepository,
                     CocktailRepository cocktailRepository){
        this.assembler = assembler;
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.cocktailRepository = cocktailRepository;
    }

    @GetMapping("/api/v1/cocktails/{cocktailId}/recipes/{recipeId}")
    public EntityModel<Recipe> getOneRecipe(@PathVariable Long cocktailId, @PathVariable Long recipeId) {

        Recipe recipe = recipeRepository.findById(recipeId) //
                .orElseThrow(() -> new RecipeNotFoundException(recipeId));

        return assembler.toModel(recipe);
    }

    @GetMapping("api/v1/cocktails/{cocktailId}/recipes")
    public CollectionModel<EntityModel<Recipe>> getAllRecipesInCategory(@PathVariable Long cocktailId) {
        List<EntityModel<Recipe>> recipes = recipeRepository.findByCocktailId(cocktailId).stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(recipes, linkTo(methodOn(RecipeController.class).getAllRecipesInCategory(cocktailId)).withSelfRel());
    }

    @PostMapping("/api/v1/cocktails/{cocktailId}/recipes")
    Recipe createRecipe(@PathVariable Long cocktailId, @RequestBody Recipe newRecipe) {

        Cocktail cocktail = cocktailRepository.findById(cocktailId)
                .orElseThrow(() -> new CocktailNotFoundException(cocktailId));

        cocktail.getRecipes().add(newRecipe);
        newRecipe.setCocktail(cocktail);
        cocktailRepository.save(cocktail);
        //TODO: figure out how to add recipe to ingredient's recipe list (without concurrent modification exception)
        for (Iterator<Ingredient> iterator = newRecipe.getIngredientList().iterator(); iterator.hasNext();) {
            Ingredient ingredient = iterator.next();
            ingredient.getRecipes().add(newRecipe);
        }

        return recipeRepository.save(newRecipe);
    }
}
