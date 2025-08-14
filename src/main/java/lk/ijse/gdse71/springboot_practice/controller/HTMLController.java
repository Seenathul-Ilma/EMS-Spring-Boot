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

    // Serve external FrontEnd/index.html for root
    @GetMapping("/")
    public ResponseEntity<Resource> loadIndex() {
        try {
            Path htmlFilePath = Paths.get(System.getProperty("user.dir"), "FrontEnd", "index.html");
            Resource resource = new UrlResource(htmlFilePath.toUri());
            if (resource.exists() && resource.isReadable()) {
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

    // Optional: keep /api/v1/event if needed
    @GetMapping("/api/v1/event")
    public ResponseEntity<Resource> loadEventPage() {
        try {
            Path htmlFilePath = Paths.get(System.getProperty("user.dir"), "FrontEnd", "index.html");
            Resource resource = new UrlResource(htmlFilePath.toUri());
            if (resource.exists() && resource.isReadable()) {
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

    @GetMapping("/style.css")
    public ResponseEntity<Resource> getStyleCss() throws MalformedURLException {
        Path path = Paths.get(System.getProperty("user.dir"), "FrontEnd", "style.css");
        Resource resource = new UrlResource(path.toUri());
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf("text/css")) // <-- here
                .body(resource);
    }

    @GetMapping("/main.js")
    public ResponseEntity<Resource> getMainJsFile() throws MalformedURLException {
        Path path = Paths.get(System.getProperty("user.dir"), "FrontEnd", "main.js");
        Resource resource = new UrlResource(path.toUri());
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf("application/javascript")) // <-- here
                .body(resource);
    }

    @GetMapping("/lib/normalize.css")
    public ResponseEntity<Resource> getNormalizeCss() throws MalformedURLException {
        Path path = Paths.get(System.getProperty("user.dir"), "FrontEnd", "lib/normalize.css");
        Resource resource = new UrlResource(path.toUri());
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf("text/css"))
                .body(resource);
    }

    @GetMapping("/lib/jquery-3.7.1.min.js")
    public ResponseEntity<Resource> getJqueryJs() throws MalformedURLException {
        Path path = Paths.get(System.getProperty("user.dir"), "FrontEnd", "lib/jquery-3.7.1.min.js");
        Resource resource = new UrlResource(path.toUri());
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf("application/javascript"))
                .body(resource);
    }

}

/*@Controller
public class HTMLController {

    @GetMapping("/")
    public String index() {
        return "forward:/index.html"; // Must use forward so Spring serves it from static config
    }
}*/

