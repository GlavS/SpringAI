package ru.otus.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestHttpPostController {

    @PostMapping(value = "/test")
    public void testPost(@RequestBody TestDto dto) { //TODO: спросить, почему json по дефолту, и какие есть альтернативы
        System.out.println(dto);
    }
}


