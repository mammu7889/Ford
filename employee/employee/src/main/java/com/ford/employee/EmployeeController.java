package com.ford.employee;

import com.ford.employee.model.Movie;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RefreshScope
public class EmployeeController {

    @Value("${employee: Default Employee}")
    public String employee;

    @GetMapping("/employee")
    public String employee() {
        return employee;
    }

    @GetMapping("/movies/{movieId}")
    public Movie getMovie(@PathVariable("movieId") String movieId) {
        return new Movie(movieId, "Test Movie");
    }
}
