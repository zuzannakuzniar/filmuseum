package filmuseum.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/logged/")
public class LoggedPageController {


    @GetMapping("/logged/")
    public String getLoggedPage(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("logged");
        return "logged";
    }

    @PostMapping("/logged/")
    public String getRedirectPage(){
        ModelAndView mv = new ModelAndView();
       if(mv.getModel().containsValue("/films/")){
           return "redirect:/films/";
        } else {
           return "redirect:/reviews/";
       }
    }
}
