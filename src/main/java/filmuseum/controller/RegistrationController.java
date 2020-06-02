package filmuseum.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import filmuseum.DAO.UserRepository;
import filmuseum.entity.RegistrationForm;

@RestController
@RequestMapping("/register")
public class RegistrationController {
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public RegistrationController(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @GetMapping
    public ModelAndView registerForm(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("registration");
        return mv;
    }

    @PostMapping
    public ModelAndView processRegistration(RegistrationForm form){
        ModelAndView mv = new ModelAndView();
        mv.addObject("username", form.getUsername());
        mv.addObject("password", form.getPassword());
        mv.addObject("fullname", form.getFullname());
        userRepository.save(form.toUser(passwordEncoder));
        return new ModelAndView("login");
    }
}
