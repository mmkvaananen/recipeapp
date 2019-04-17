/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.guides.springboot2.oma.controller;

import java.util.List;
import javax.validation.Valid;
import net.guides.springboot2.oma.exception.ResourceNotFoundException;
import net.guides.springboot2.oma.model.Users;
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
@RequestMapping("/api")
public class UsersController {
    
    @Autowired
    private UsersRepository usersRepository;
    
    @GetMapping("/users")
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }
    
    @GetMapping("/users/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable(value = "id") Long userId)
        throws ResourceNotFoundException {
        try {
            System.out.println("USERID to be searched for: " + userId);

            Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
            System.out.println("USER CONTROLLER user " + user);
            return ResponseEntity.ok().body(user);
        }
        catch(ResourceNotFoundException e) {
            System.out.println("Error finding user: " +e);
            return null;
        }
        
        
    }
    
    @PostMapping("/users")
    public Users createUser(@Valid @RequestBody Users user) {
        return usersRepository.save(user);
    }
    
}

