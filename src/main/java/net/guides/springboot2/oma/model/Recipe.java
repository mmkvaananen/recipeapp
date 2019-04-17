/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.guides.springboot2.oma.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Mari
 */
@Entity(name="Recipe")
@Table(name= "recipe")
public class Recipe implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    //@JsonBackReference
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="fk_user_id")
    private Users ownerId;
    
    @Column(name="recipe_name")
    private String name;
    
    @Column(name= "portions")
    private int portions;
    
    
    /*@OneToMany(
        mappedBy = "recipe",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )*/
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy="recipe", cascade = CascadeType.ALL)
    private Set<RecipeIngredients> recipes;
    //private List<RecipeIngredients> recipes = new ArrayList<>();
    
    public Recipe() {
        
    }
    
    public Recipe(String name) {
        this.name = name;
    }
    
    public Recipe(String name, int portions) {
        this.name = name;
        this.portions = portions;
    }
    
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public Users getOwnerId() {
        return ownerId;
    }
    
    public void setOwnerId(Users ownerId) {
        this.ownerId = ownerId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getPortions() {
        return portions;
    }
    
    public void setPortions(int portions) {
        this.portions = portions;
    }

    public Set<RecipeIngredients> getRecipes() {
        return recipes;
    }

    public void setRecipes(Set<RecipeIngredients> recipes) {
        this.recipes = recipes;
    }

   
    
    
    /*when new ingredient is added to recipe, it must be added also
      to ingredient List
    */
    /*public Recipe addIngredient(Ingredient ingredient) {
        System.out.println(" RECIPE ADD INGREDIENT");
        System.out.println("INGR TO BE ADDED: " + ingredient);
        RecipeIngredients newIngredient = new RecipeIngredients(this, ingredient);
        List<RecipeIngredients> recipesList = this.getRecipes();
        recipesList.add(newIngredient);
        this.setRecipes(recipesList);
        
        //recipes.add(newIngredient);
        System.out.println("RECIPE AFTER adding ingredient: " + this.recipes);
        return this;
        
        
    }
 */
    public void removeIngredient(Ingredient ingredient) {
        for (Iterator<RecipeIngredients> iterator = recipes.iterator();
             iterator.hasNext(); ) {
            RecipeIngredients oldIngredients = iterator.next();
 
            if (oldIngredients.getRecipe().equals(this) &&
                    oldIngredients.getIngredient().equals(ingredient)) {
                iterator.remove();
                oldIngredients.getIngredient().getRecipes().remove(oldIngredients);
                oldIngredients.setRecipe(null);
                oldIngredients.setIngredient(null);
            }
        }
    }
    
    @Override
    public String toString() {
        return "Recipe [id=" + id + ", owner=" + ownerId + ", name=" + name + ", portions=" + portions + ", recipes=" +recipes +"]";
    }
}
