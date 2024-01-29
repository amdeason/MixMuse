package amdeason.mixmuse.cocktails.models;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.ArrayList;
import java.util.List;

public class User extends AbstractEntity {

    private String userName;

    public User() {}
    public User(String userName) {
        this.userName = userName;
    }

//    @OneToMany(mappedBy = "user")
//    private final List<Recipe> createdRecipes = new ArrayList<>();

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
