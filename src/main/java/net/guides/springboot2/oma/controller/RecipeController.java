/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.guides.springboot2.oma.controller;

import java.util.List;
import javax.validation.Valid;
import net.guides.springboot2.oma.exception.ResourceNotFoundException;
import net.guides.springboot2.oma.model.Recipe;
import net.guides.springboot2.oma.model.Users;
import net.guides.springboot2.oma.repository.RecipeRepository;
import net.guides.springboot2.oma.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Mari
 */
@RestController
@RequestMapping("/api/recipe")
public class RecipeController {
    
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private UsersRepository usersRepository;
    
    @GetMapping("/recipes")
    public List<Recipe> getAllRecipes() {
        System.out.println("get");
        return recipeRepository.findAll();
    }
   

    @GetMapping("/recipes/{id}")
    public ResponseEntity<Recipe> getEmployeeById(@PathVariable(value = "id") Long recipeId)
        throws ResourceNotFoundException {
        Recipe recipe = recipeRepository.findById(recipeId)
          .orElseThrow(() -> new ResourceNotFoundException("Recipe not found for this id :: " + recipeId));
        return ResponseEntity.ok().body(recipe);
    }
    
    @PostMapping("/recipes/{userid}")
    public Recipe createRecipe(@Valid @RequestBody Recipe recipe, @PathVariable(value="userid") Long userId) throws ResourceNotFoundException {
        System.out.println("POST");
        System.out.println(recipe);
        System.out.println("USERID:" + userId);
        //User object is needed so that recipe can be added to users recipe list and ownerid can be added to recipe
        Users user = usersRepository.findById(userId) 
          .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
        System.out.println("USER " + user);
        Recipe res = user.addRecipe(recipe);
        return recipeRepository.save(res);
    }
    
}
