package filmuseum.entity;

import filmuseum.constraint.FieldMatch;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@FieldMatch.List({
        @FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match"),
        @FieldMatch(first = "email", second = "confirmEmail", message = "The email fields must match")
})

public class RegistrationForm {

    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    @NotEmpty
    private String confirmPassword;
    @Email
    @NotEmpty
    private String email;
    @Email
    @NotEmpty
    private String confirmEmail;
    @NotEmpty
    private String fullname;

    public User toUser(BCryptPasswordEncoder passwordEncoder){
        return new User(username, passwordEncoder.encode(password), fullname);
    }
}
