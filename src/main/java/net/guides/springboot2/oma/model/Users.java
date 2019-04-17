/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.guides.springboot2.oma.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Mari
 */
@Entity(name="Users")
@Table(name= "users")
public class Users implements Serializable {
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "first_name", nullable=false)
    private String firstName;
     
    @Column(name = "last_name", nullable=false)
    private String lastName;
     
    @Column(name = "password", nullable=false)
    private String password;
     
    @Column(name = "user_rights", nullable=false)
    private int userRights;
    
    /*@JsonManagedReference
    @OneToMany(
        fetch = FetchType.LAZY,
        mappedBy = "ownerId",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private Set<Recipe> recipes = new HashSet<>();*/
    
    public Users() {
    
    }
    
    public Users(String firstName, String lastName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.userRights = 0;
    }
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
     public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
     public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getPassword() {
        return password;
    }
    
     public void setPassword(String password) {
        this.password = password;
    }
    
    public int getUserRights() {
        return userRights;
    }
    
     public void setUserRights(int rights) {
        this.userRights = rights;
    }

   /* public Set<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(Set<Recipe> recipes) {
        this.recipes = recipes;
    }
    
    public Recipe addRecipe(Recipe recipe) {
        System.out.println("Add recipe" + recipe);
        recipes.add(recipe);
        recipe.setOwnerId(this);
        return recipe;
    }
 
    public void removeRecipe(Recipe recipe) {
        recipes.remove(recipe);
        recipe.setOwnerId(null);
    }*/
     
   /* @Override
    public String toString() {
        return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", rights=" + userRights
       + ", recipes=" +recipes +"]";
    }*/
    @Override
    public String toString() {
        return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", rights=" + userRights
       + "]";
    }
}

