package ru.otus.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class WorkController {

    @GetMapping("/works")
    public List<Map<String, Object>> works() {
        return List.of(
                Map.of("id", 1, "title", "English suite"),
                Map.of("id", 2, "title", "Goldberg Variations")
        );
    }
}
