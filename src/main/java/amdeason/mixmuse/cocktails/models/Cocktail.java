package amdeason.mixmuse.cocktails.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Cocktail extends AbstractEntity {

    private String name;

    @CreationTimestamp
    private LocalDateTime dateTimeAdded;

    @OneToMany(mappedBy="cocktail")
    @JsonIgnore
    private final List<Recipe> recipes = new ArrayList<>();

    public Cocktail() {}
    public Cocktail(String aName) {
      name = aName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDateTimeAdded() {
        return dateTimeAdded;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    @Override
    public String toString() {
        return "Cocktail{" +
                "id=" + this.getId() +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cocktail cocktail)) return false;
        return Objects.equals(getId(), cocktail.getId()) && Objects.equals(getName(), cocktail.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}
