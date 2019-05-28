package com.ford.movies.model;

import java.util.List;

public class UserRating {


    List<Rating> ratings;

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    UserRating() {

    }

    public UserRating(List<Rating> ratings) {
        this.ratings = ratings;
    }

}
