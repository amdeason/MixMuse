package amdeason.mixmuse.cocktails.models;

import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.Objects;

@Entity
public class Cocktail extends AbstractEntity {
    private String name;

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
