package amdeason.mixmuse.cocktails.models;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Recipe extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name="cocktail_id")
    private Cocktail cocktail;

    @ManyToMany(cascade = CascadeType.ALL)
    private final List<Ingredient> ingredientList = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime dateTimeAdded;
    private String instructions;

    public Recipe() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Recipe recipe)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(recipe.getId(), this.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.getId());
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "cocktail=" + cocktail +
                ", ingredientList=" + ingredientList +
                ", dateTimeAdded=" + dateTimeAdded +
                ", instructions='" + instructions + '\'' +
                '}';
    }

    public void setCocktail(Cocktail cocktail) {
        this.cocktail = cocktail;
    }

    public List<Ingredient> getIngredientList() {
        return ingredientList;
    }

    public LocalDateTime getDateTimeAdded() {
        return dateTimeAdded;
    }

    public void setDateTimeAdded(LocalDateTime dateTimeAdded) {
        this.dateTimeAdded = dateTimeAdded;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public Cocktail getCocktail() {
        return cocktail;
    }


}
