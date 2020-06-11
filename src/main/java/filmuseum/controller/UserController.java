package filmuseum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import filmuseum.entity.User;
import filmuseum.service.UserServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/users/")
public class UserController {

    private UserServiceImpl userServiceImpl;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/{id}/")
    public User getUserById(@PathVariable("id") Long id){
        return userServiceImpl.getUserById(id);
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userServiceImpl.getAllUsers();
    }

    @PostMapping
    public User addUser(@RequestBody User user){
        return userServiceImpl.addUser(user);
    }

    @DeleteMapping("/{id}/")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable Long id){
         userServiceImpl.deleteUser(id);
    }

    @PutMapping("/{id}/")
    public User updateUser(@PathVariable Long id, @RequestBody User user){
        return userServiceImpl.updateUser(id, user);
    }

}
