package filmuseum.controller;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping("/home/")
public class HomeController {

    @GetMapping("/")
    public String root() {
        return "home";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/logged")
    public String logged() {
        return "loggedPage";
    }

//    @GetMapping("/")
//    public ModelAndView getHomePage(){
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("home");
//        return mv;
//    }
//
//    @ResponseBody
//    @PostMapping()
//    public String processToLogin(){
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("login");
//        return "login";
//    }

}
