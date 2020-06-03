package filmuseum.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/login/").setViewName("login");
        registry.addViewController("/logged/").setViewName("logged");
        registry.addViewController("/register/").setViewName("register");
        registry.addRedirectViewController("/successful/", "/logged/");
    }
}
