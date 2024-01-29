package amdeason.mixmuse.cocktails.models;

import amdeason.mixmuse.cocktails.controllers.CocktailController;
import amdeason.mixmuse.cocktails.controllers.RecipeController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RecipeModelAssembler implements RepresentationModelAssembler<Recipe, EntityModel<Recipe>> {
    @Override
    public EntityModel<Recipe> toModel(Recipe recipe) {

        return EntityModel.of(recipe,
                linkTo(methodOn(RecipeController.class).getOneRecipe(recipe.getCocktail().getId(), recipe.getId())).withSelfRel(),
                linkTo(methodOn(RecipeController.class).getAllRecipesInCategory(recipe.getCocktail().getId())).withRel("all_"+ recipe.getCocktail().getName()+"_recipes"),
                linkTo(methodOn(CocktailController.class).getCocktail(recipe.getCocktail().getId())).withRel("cocktail_type"));
    }
}
