package filmuseum.controller;

import filmuseum.entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import filmuseum.DAO.UserRepository;
import filmuseum.entity.RegistrationForm;

import javax.validation.Valid;

@RestController
@RequestMapping("/register/")
public class RegistrationController {

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;


    public RegistrationController(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @ModelAttribute("form")
    public RegistrationForm registrationForm(){
        return new RegistrationForm();
    }

    @GetMapping
    public ModelAndView registerForm(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("registration");
        return mv;
    }

    @ResponseBody
    @PostMapping
    public String processRegistration(@ModelAttribute("form") @Valid RegistrationForm form, BindingResult result){
        User existingByUsername = userRepository.findUserByUsername(form.getUsername());
        User existingByEmail = userRepository.findByEmail(form.getEmail());

        if(existingByUsername != null) {
            result.reject("username", null, "Username already exists.");
    }
        if(existingByEmail != null){
            result.reject("email", null, "This email is already registered.");
        }
        userRepository.save(form.toUser(passwordEncoder));
        return "login";
    }

    @PostMapping("/login")
    public ModelAndView login(){
        return new ModelAndView("login");
    }
}
