package amdeason.mixmuse.cocktails.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

import java.util.List;

@Entity
public class Ingredient extends AbstractEntity {

    private String name;

    Ingredient(){}

    Ingredient(String name) {
        this.name = name;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Recipe> recipes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
