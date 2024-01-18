package amdeason.mixmuse.cocktails.models;

import amdeason.mixmuse.cocktails.controllers.CocktailController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CocktailModelAssembler  implements RepresentationModelAssembler<Cocktail, EntityModel<Cocktail>> {

    @Override
    public EntityModel<Cocktail> toModel(Cocktail cocktail) {

        return EntityModel.of(cocktail,
                linkTo(methodOn(CocktailController.class).getCocktail(cocktail.getId())).withSelfRel(),
                linkTo(methodOn(CocktailController.class).getAllCocktails()).withRel("/api/v1/cocktails"));    }

}
