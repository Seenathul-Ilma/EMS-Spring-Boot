package lk.ijse.gdse71.springboot_practice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

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


@RestController
public class HTMLController {

    @GetMapping("/api/v1/event")
    public ResponseEntity<Resource> index() {
        try {
            Path htmlFilePath = Paths.get("FrontEnd/index.html").toAbsolutePath().normalize();
            Resource resource = new UrlResource(htmlFilePath.toUri());
            if (resource.exists()) {
                return ResponseEntity.ok()
                        .contentType(MediaType.TEXT_HTML)
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}

/*@Controller
public class HTMLController {

    @GetMapping("/")
    public String index() {
        return "forward:/index.html"; // Must use forward so Spring serves it from static config
    }
}*/

