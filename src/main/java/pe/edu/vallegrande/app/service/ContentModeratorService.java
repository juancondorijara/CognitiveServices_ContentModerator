package pe.edu.vallegrande.app.service;

import pe.edu.vallegrande.app.model.ContentModerator;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface ContentModeratorService {

    Flux<ContentModerator> getAll();

    Mono<ContentModerator> save(ContentModerator contentModerator);

}
