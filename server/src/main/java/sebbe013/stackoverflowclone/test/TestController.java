package sebbe013.stackoverflowclone.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/")
    public String helloWorld() {
        return "Hello World Spring Boot Test";
    }
}
