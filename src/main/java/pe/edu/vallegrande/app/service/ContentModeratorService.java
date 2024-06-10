package pe.edu.vallegrande.app.service;

import pe.edu.vallegrande.app.model.ContentModerator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface ContentModeratorService {

    Flux<ContentModerator> findAll();

    Mono<ContentModerator> findById(Integer id);

    Flux<ContentModerator> findByActive(boolean active);

    Mono<ContentModerator> save(ContentModerator contentModerator);

    Mono<ContentModerator> update(ContentModerator contentModerator);

    Mono<ResponseEntity<ContentModerator>> delete(Integer id);

    Mono<ResponseEntity<ContentModerator>> restore(Integer id);

}
