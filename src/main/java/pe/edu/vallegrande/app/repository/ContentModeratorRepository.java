package pe.edu.vallegrande.app.repository;

import pe.edu.vallegrande.app.model.ContentModerator;
import org.springframework.data.repository.query.Param;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ContentModeratorRepository extends ReactiveCrudRepository<ContentModerator, Integer> {

    @Query("SELECT * FROM ContentModerator ORDER BY id DESC")
    Flux<ContentModerator> findAll();

    @Query("SELECT * FROM ContentModerator WHERE id = :id ORDER BY id DESC")
    Mono<ContentModerator> findById(@Param("id") Integer id);

    @Query("SELECT * FROM ContentModerator WHERE active = :active ORDER BY id DESC")
    Flux<ContentModerator> findByActive(@Param("active") boolean active);




}
