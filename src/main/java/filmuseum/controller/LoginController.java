package filmuseum.controller;

import filmuseum.entity.Login;
import filmuseum.entity.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    @RequestMapping(value = "/processLogin", method = RequestMethod.POST)
    public ModelAndView processLogin(@ModelAttribute("login") User user) {

//        String userName = user.getUsername();
//        String password = user.getPassword();
//        String passwordDB = userService.getByUsername(userName).getPassword();
//        String usernameDB = userService.getUserById(userService.getByUsername(userName).getId()).getUsername();

//        if (usernameDB.equalsIgnoreCase(userName)
//                && passwordDB.equalsIgnoreCase(password)) {
            ModelAndView mv = new ModelAndView("loggedPage");
            return mv;
//        } else {
//            throw new UsernameNotFoundException("Try again");
//        }

    }

}
