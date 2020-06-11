package filmuseum.entity;

import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Data
public class Login {

    private String username;
    private String password;

}
