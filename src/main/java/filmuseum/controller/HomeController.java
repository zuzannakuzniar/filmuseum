package filmuseum.controller;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping("/home/")
public class HomeController {

    @GetMapping
    public ModelAndView getHomePage(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("home");
        return mv;
    }

//    @ResponseBody
////    @PostMapping(name = "/films/")
////    public String processToFilms(){
////        ModelAndView mv = new ModelAndView();
////        mv.setViewName("films");
////        return "redirect:/films/";
////    }
////
////
////    @ResponseBody
////    @PostMapping(name = "/reviews/")
////    public String processToReviews(){
////        ModelAndView mv = new ModelAndView();
////        mv.setViewName("reviews");
////        return "redirect:/reviews/";
////    }

    @ResponseBody
    @PostMapping(name = "/login/")
    public String processToLogin(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login");
        return "redirect:/login/";
    }

}
