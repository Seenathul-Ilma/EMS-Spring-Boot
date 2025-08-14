package lk.ijse.gdse71.springboot_practice.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
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

//@CrossOrigin(origins = "https://eventms-abc077b65d00.herokuapp.com")
@CrossOrigin
@RestController
public class HTMLController {

    // Serve external FrontEnd/index.html for root
    /*@GetMapping("/")
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
    }*/

    @GetMapping({"/", "/**"})
    public ResponseEntity<Resource> serveFrontEnd(HttpServletRequest request) throws IOException {
        String path = request.getRequestURI(); // e.g., "/style.css" or "/lib/jquery.js"

        // Default to index.html for root
        if (path.equals("/")) path = "/index.html";

        Path filePath = Paths.get(System.getProperty("user.dir"), "FrontEnd", path.substring(1));
        Resource resource = new UrlResource(filePath.toUri());

        if (resource.exists() && resource.isReadable()) {
            String mimeType = Files.probeContentType(filePath);
            if (mimeType == null) mimeType = "application/octet-stream";
            return ResponseEntity.ok().contentType(MediaType.valueOf(mimeType)).body(resource);
        } else {
            return ResponseEntity.notFound().build();
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

