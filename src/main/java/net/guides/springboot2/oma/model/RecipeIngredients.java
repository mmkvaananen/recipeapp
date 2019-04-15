/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.guides.springboot2.oma.model;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

/**
 *
 * @author Mari
 */
@Entity(name="RecipeIngredients")
@Table(name= "recipe_ingredients")
public class RecipeIngredients {
    
    @EmbeddedId
    private RecipeIngredientsId id;
    
    @ManyToOne
    @MapsId("recipeId")
    private Recipe recipe;
    
    @ManyToOne
    @MapsId("ingredientId")
    private Ingredient ingredient;
    
    @Column(name="step")
    private int step;
    
    @Column(name="amount")
    private int amount;

    private RecipeIngredients() {
    }
    
    public RecipeIngredients(Recipe recipe, Ingredient ingredient) {
        this.recipe = recipe;
        this.ingredient = ingredient;
        this.id = new RecipeIngredientsId(recipe.getId(), ingredient.getId());
    }
    
    public Recipe getRecipe() {
        return recipe;
    }
    
    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
    
    public Ingredient getIngredient() {
        return ingredient;
    }
    
    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }
    
    public int getStep() {
        return step;
    }
    
    public void setStep(int step) {
        this.step = step;
    }
    
    public int getAmount() {
        return amount;
    }
    
    public void setAmount(int amount) {
        this.amount = amount;
    }
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        RecipeIngredients that = (RecipeIngredients) o;
        return Objects.equals(recipe, that.recipe) &&
               Objects.equals(ingredient, that.ingredient) &&
               Objects.equals(step, that.step) &&
               Objects.equals(amount, that.amount);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(recipe, ingredient, step, amount);
    }
    
    @Override
    public String toString() {
        return "Recipe Ingredients [id="  + id + ", step=" + step + ", amount=" + amount
       + "]";
    }
    
}
