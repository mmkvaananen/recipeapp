/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.guides.springboot2.oma.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;
import javax.validation.Valid;
import net.guides.springboot2.oma.exception.ResourceNotFoundException;
import net.guides.springboot2.oma.model.Ingredient;
import net.guides.springboot2.oma.model.Recipe;
import net.guides.springboot2.oma.model.RecipeIngredients;
import net.guides.springboot2.oma.repository.IngredientRepository;
import net.guides.springboot2.oma.repository.RecipeIngredientsRepository;
import net.guides.springboot2.oma.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Mari
 */
@RestController
@RequestMapping("/api/recipeingredient")
public class RecipeIngredientsController {
    
    @Autowired
    private RecipeIngredientsRepository recipeIngredientRepository;
    
    @Autowired
    private RecipeRepository recipeRepository;
    
    @Autowired
    private IngredientRepository ingredientRepository;
    
    @PostMapping("/recipeingredients")
    public RecipeIngredients addIngredients(@Valid @RequestBody ObjectNode objectNode) throws ResourceNotFoundException {
        ObjectMapper objectMapper = new ObjectMapper();
        Recipe recipe;
        Ingredient ingredient;
        String objectRecipe = objectNode.findValue("recipe").toString();
        try {
            recipe = objectMapper.readValue(objectRecipe, Recipe.class);
            long reciId = recipe.getId();
            try {
                Recipe reci = recipeRepository.findById(reciId)
                    .orElseThrow(() -> new ResourceNotFoundException("Recipe not found for this id :: " + reciId));
                String objectIngredient = objectNode.findValue("ingredient").toString();
                try {
                    ingredient = objectMapper.readValue(objectIngredient, Ingredient.class);
                    long ingrId = ingredient.getId();
                    try {
                        Ingredient ingr = ingredientRepository.findById(ingrId)
                            .orElseThrow(() -> new ResourceNotFoundException("Ingredient not found for this id ::" + ingrId));
                        
                        RecipeIngredients newIngredient = new RecipeIngredients(reci, ingr);
                        System.out.println("NEWINGREDIENT: " + newIngredient);
                        return recipeIngredientRepository.save(newIngredient);
                        
                    }
                    catch (ResourceNotFoundException e) {
                        System.out.println(e);
                    }

                }
                catch (IOException e) {
                System.out.println("Ingredient could not be read from requestBody: " + e);
                recipe = null;
                }
            }
            catch(ResourceNotFoundException e) {
                System.out.println(e);
            }
            
        }
        catch (IOException e) {
            System.out.println("Recipe could not be read from requestBody: " + e);
            
        }
        return null;
    }
    
}
