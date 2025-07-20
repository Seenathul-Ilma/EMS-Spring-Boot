package lk.ijse.gdse71.springboot_practice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * --------------------------------------------
 * Author: Zeenathul Ilma
 * GitHub: https://github.com/Seenathul-Ilma
 * Website: https://zeenathulilma.vercel.app/
 * --------------------------------------------
 * Created: 7/20/2025 8:12 PM
 * Project: SpringBoot_Practice
 * --------------------------------------------
 **/

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/**") // matches any request
                .addResourceLocations("file:FrontEnd/"); // folder outside src
    }
}
