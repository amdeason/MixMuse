package amdeason.mixmuse.cocktails.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Ingredient extends AbstractEntity {

    private String name;

    @CreationTimestamp
    private LocalDateTime dateAdded;

    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private final List<Recipe> recipes = new ArrayList<>();
    Ingredient(){}

    Ingredient(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }
}
