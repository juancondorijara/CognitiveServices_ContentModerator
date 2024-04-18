package com.vg.ms_content_moderator.repository;

import com.vg.ms_content_moderator.model.ContentModerator;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux; //List
//import reactor.core.publisher.Mono; //Individual

@Repository
public interface ContentModeratorRepository extends ReactiveCrudRepository<ContentModerator, Integer> {

    @Query("SELECT * FROM ContentModerator ORDER BY id DESC")
    Flux<ContentModerator> getAll();

}
