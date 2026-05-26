package com.SerratecFlix.trabalhoApi.Dto.Integration;

import java.util.List;

public record TmdbSearchResponse(List<TmdbMovieResult> results) {
    
    public record TmdbMovieResult(
        String title, 
        String overview, 
        String release_date
    ) {}
}