package fr.ynov.marketpeace.controllers;

import fr.ynov.marketpeace.entities.User;
import fr.ynov.marketpeace.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import fr.ynov.marketpeace.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping(path = "/api/users")
@SecurityRequirement(name = "bearerAuth")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService service){
        this.userService = service;
    }

    @Operation(description = "Find user associated to given id")
    @SecurityRequirement(name = "bearer")
    @GetMapping("/{id}")
    public User findUserById(@PathVariable Long id) throws UserNotFoundException {
        return userService.findUserById(id);
    }

    @Operation(description = "Create new user or update existing one")
    @PutMapping
    public User save(@RequestBody User u){
        if(userService.existsById(u.getId())){
            return userService.save(u);
        }
        return null;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<User> findAll(){
        return userService.findAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        userService.deleteUser(id);
    }
}
