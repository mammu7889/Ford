package com.ford.movies;

import com.ford.movies.model.CatalogItem;
import com.ford.movies.model.Movie;
import com.ford.movies.model.Rating;
import com.ford.movies.model.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RefreshScope
public class MoviesController {

    @Autowired
    RestTemplate restTemplate;

    @Value("${movie: Defualt movie}")
    public String movie;

    @GetMapping("/movies")
    public String movies() {
        return movie;
    }

    @GetMapping("/catalogs/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
        UserRating ratings = restTemplate.getForObject("http://hello-client/api/users/" + userId, UserRating.class);
        return ratings.getRatings().stream().map(rating -> {
            Movie movie = restTemplate.getForObject("http://employee-client/api/movies/" + rating.getMovieId(), Movie.class);
            return new CatalogItem(movie.getMovieName(), "Testing", rating.getRating());
        }).collect(Collectors.toList());
    }
}
