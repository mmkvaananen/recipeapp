/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.guides.springboot2.oma.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Mari
 */
@Embeddable
public class RecipeIngredientsId implements Serializable {
    
    @Column(name="recipe_id")
    private long recipeId;
    
    @Column(name="ingredient_id")
    private long ingredientId;
    
    private RecipeIngredientsId() {}
 
    public RecipeIngredientsId( long recipeId, Long ingredientId) {
        this.recipeId = recipeId;
        this.ingredientId = ingredientId;
    }

    public long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(long recipeId) {
        this.recipeId = recipeId;
    }
    
    public long getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(long ingredientId) {
        this.ingredientId = ingredientId;
    }
 
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        RecipeIngredientsId that = (RecipeIngredientsId) o;
        return Objects.equals(recipeId, that.recipeId) &&
               Objects.equals(ingredientId, that.ingredientId);
    }
 
    
    @Override
    public int hashCode() {
        return Objects.hash(recipeId, ingredientId);
    }
}
