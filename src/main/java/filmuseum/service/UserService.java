package filmuseum.service;

import filmuseum.entity.RegistrationForm;
import org.hibernate.ObjectNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import filmuseum.DAO.UserRepository;
import filmuseum.entity.Login;
import filmuseum.entity.User;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User getUserById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id, User.class.getName()));
    }

    public List<User> getAllUsers(){
        return (List<User>) userRepository.findAll();
    }

    public User addUser(User user){
        return userRepository.save(saveUser(user));
    }

    private User saveUser(User user) {
        User resultUser = new User();
        resultUser.setId(user.getId());
        resultUser.setUsername(user.getUsername());
        resultUser.setPassword(passwordEncoder.encode(user.getPassword()));
        resultUser.setFullname(user.getFullname());
        resultUser.setLikedFilmes(user.getLikedFilmes());
        resultUser.setUsersReviews(user.getUsersReviews());
        return resultUser;
    }

    public User updateUser(Long id, User user){
        Optional<User> toBeUpdated = userRepository.findById(id);
        if(toBeUpdated.isPresent()){
            User updatedUser = toBeUpdated.get();
            updatedUser.setUsername(user.getUsername());
            updatedUser.setPassword(passwordEncoder.encode(user.getPassword()));
            updatedUser.setFullname(user.getFullname());
            return updatedUser;
        } else { throw new ObjectNotFoundException(id, User.class.getName()); }
    }

    public void deleteUser(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id, User.class.getName()));
        userRepository.delete(user);

    }

    public User getByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    public User validateUser(Login login){
        String password = userRepository.findUserByUsername(login.getUsername()).getPassword();
        if(password == login.getPassword()){
            return userRepository.findUserByUsername(login.getUsername());
        } else { return null;}


    }
}
