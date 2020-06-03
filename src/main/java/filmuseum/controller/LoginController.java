package filmuseum.controller;

import filmuseum.entity.Login;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import filmuseum.service.UserServiceImpl;

@RestController
@RequestMapping("/login/")
public class LoginController {

    private UserServiceImpl userServiceImpl;

    @GetMapping
    public ModelAndView getLogin(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login");
        return mv;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView login(@RequestBody Login login) {
        return new ModelAndView("loggedPage");
    }

}
