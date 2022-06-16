package fr.ynov.marketpeace.controllers;

import java.util.List;
import fr.ynov.marketpeace.entities.User;
import fr.ynov.marketpeace.services.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.NonNull;
import org.springframework.web.bind.annotation.*;
import fr.ynov.marketpeace.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping(path = "/api/users")
@SecurityRequirement(name = "bearerAuth")
public class UserController {
    private final UserService userService;

    @Autowired
    UserController(UserService service){
        this.userService = service;
    }

    @GetMapping
    public List<User> findAll(){
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User findUserById(@PathVariable Long id) throws UserNotFoundException {
        return userService.findUserById(id).orElseThrow(() -> new UserNotFoundException(id.toString()));
    }

    @PutMapping
    public User save(@RequestBody User u){
        if(userService.existsById(u.getId())){
            return userService.save(u);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@NonNull @PathVariable Long id){
        userService.delete(id);
    }
}
