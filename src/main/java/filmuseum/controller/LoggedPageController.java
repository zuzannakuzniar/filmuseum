package filmuseum.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/logged/")
public class LoggedPageController {

    private ModelAndView mv = new ModelAndView();

    @GetMapping
    public ModelAndView getLoggedPage(){

        mv.setViewName("loggedPage");
        return mv;
    }

    @ResponseBody
    @PostMapping
    public String getRedirectPage(){
       if(mv.getModel().containsValue("/films/")){
           return "redirect:/films/";
        } else {
           return "redirect:/reviews/";
       }
    }
}
