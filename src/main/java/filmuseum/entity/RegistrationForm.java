package filmuseum.entity;

import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Data
public class RegistrationForm {

    private String username;
    private String password;
    private String fullname;

    public User toUser(BCryptPasswordEncoder passwordEncoder){
        return new User(username, passwordEncoder.encode(password), fullname);
    }
}
