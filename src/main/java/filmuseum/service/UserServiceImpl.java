package filmuseum.service;

import filmuseum.dao.repository.RoleRepository;
import filmuseum.dao.entity.Role;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import filmuseum.dao.repository.UserRepository;
import filmuseum.dao.entity.Login;
import filmuseum.dao.entity.User;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                       RoleRepository roleRepository,
                       BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
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
        Role userRole = roleRepository.findByName("ROLE_USER");
        Set<Role> roles = userRepository.findById(user.getId()).get().getRoles();
        roles.add(userRole);



        resultUser.setId(user.getId());
        resultUser.setUsername(user.getUsername());
        resultUser.setPassword(passwordEncoder.encode(user.getPassword()));
        resultUser.setFullname(user.getFullname());
        resultUser.setLikedFilmes(user.getLikedFilmes());
        resultUser.setUsersReviews(user.getUsersReviews());
        resultUser.setRoles(roles);
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


    public User validateUser(Login login){
        String password = userRepository.findUserByUsername(login.getUsername()).getPassword();
        if(password == login.getPassword()){
            return userRepository.findUserByUsername(login.getUsername());
        } else { return null;}


    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(s);
        if (user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                getAuthorities(user));
    }

    public User findByUsername(String username){
        User user = userRepository.findUserByUsername(username);
        return user;
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(User user) {
        String[] userRoles = user.getRoles().stream().map((role) -> role.getName()).toArray(String[]::new);
        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
        return authorities;
    }
}
