package com.ford.hello;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ford.hello.model.Rating;
import com.ford.hello.model.UserRating;

@RestController
@RequestMapping("/api")
@RefreshScope
public class HelloController {

	@Value("${message: Default message}")
	public String helloMessage;

	@GetMapping("/message")
	public String message() {
		return helloMessage;
	}

	@GetMapping("/ratingData/{movieId}")
	public Rating getratings(@PathVariable("movieId") String movieId) {
		return new Rating(movieId, 5);

	}
	@GetMapping("/users/{userId}")
	public UserRating getAllMovies(@PathVariable("userId") String userId) {
		UserRating userRating = new UserRating();
		java.util.List<Rating> ratings = Arrays.asList(new Rating("1234", 4), new Rating("5678", 5),
                new Rating("6789", 5));
		userRating.setRatings(ratings);
		return userRating;

	}

}
