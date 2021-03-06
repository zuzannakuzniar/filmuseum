package filmuseum.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public ViewResolver myViewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/main/resources/templates/");
        viewResolver.setSuffix(".html");
        return viewResolver;
    }



    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/login/").setViewName("login");
        registry.addViewController("/logged/").setViewName("logged");
        registry.addViewController("/register/").setViewName("register");
        registry.addViewController("/films/").setViewName("films");
        registry.addViewController("/all/").setViewName("allFilms");
        registry.addViewController("/films/{id}/").setViewName("filmDetails");
        registry.addViewController("/films/add/").setViewName("addFilm");
        registry.addViewController("/reviews/").setViewName("reviews");
        registry.addViewController("/reviews/add/").setViewName("addReview");
        registry.addRedirectViewController("/login/", "/login/");
    }
}
