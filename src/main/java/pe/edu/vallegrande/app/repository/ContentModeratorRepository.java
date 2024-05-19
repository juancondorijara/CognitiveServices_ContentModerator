package pe.edu.vallegrande.app.repository;

import pe.edu.vallegrande.app.model.ContentModerator;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ContentModeratorRepository extends ReactiveCrudRepository<ContentModerator, Integer> {

    @Query("SELECT * FROM ContentModerator ORDER BY id DESC")
    Flux<ContentModerator> getAll();

}
