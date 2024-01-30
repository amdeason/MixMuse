package amdeason.mixmuse.cocktails.models;

import amdeason.mixmuse.cocktails.controllers.CocktailController;
import amdeason.mixmuse.cocktails.controllers.IngredientController;
import amdeason.mixmuse.cocktails.controllers.RecipeController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class IngredientModelAssembler implements RepresentationModelAssembler<Ingredient, EntityModel<Ingredient>> {
    @Override
    public EntityModel<Ingredient> toModel(Ingredient ingredient) {

        return EntityModel.of(ingredient,
                linkTo(methodOn(IngredientController.class).getOneIngredient(ingredient.getId())).withSelfRel());
    }
}

