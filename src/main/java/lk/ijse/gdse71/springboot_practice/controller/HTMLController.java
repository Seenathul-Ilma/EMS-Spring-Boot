package lk.ijse.gdse71.springboot_practice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * --------------------------------------------
 * Author: Zeenathul Ilma
 * GitHub: https://github.com/Seenathul-Ilma
 * Website: https://zeenathulilma.vercel.app/
 * --------------------------------------------
 * Created: 7/20/2025 8:13 PM
 * Project: SpringBoot_Practice
 * --------------------------------------------
 **/

@Controller
public class HTMLController {

    @GetMapping("/")
    public String index() {
        return "forward:/index.html"; // Must use forward so Spring serves it from static config
    }
}

