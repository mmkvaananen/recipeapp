/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.guides.springboot2.oma.repository;


import net.guides.springboot2.oma.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Mari
 */
@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    
}
