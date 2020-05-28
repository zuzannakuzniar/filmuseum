package filmuseum.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import filmuseum.service.UserService;

@RestController
@RequestMapping("/login/")
public class LoginController {

    private UserService userService;

    @GetMapping
    public ModelAndView getLogin(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login");
        return mv;
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @PostMapping
    public String logginIn(){
        return "redirect:/logged/";

    }

}
