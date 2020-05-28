package filmuseum.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import filmuseum.entity.User;
import filmuseum.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users/")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}/")
    public User getUserById(@PathVariable("id") Long id){
        return userService.getUserById(id);
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @DeleteMapping("/{id}/")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable Long id){
         userService.deleteUser(id);
    }

    @PutMapping("/{id}/")
    public User updateUser(@PathVariable Long id, @RequestBody User user){
        return userService.updateUser(id, user);
    }

}
