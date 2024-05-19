package pe.edu.vallegrande.app.rest;

import pe.edu.vallegrande.app.model.ContentModerator;
import pe.edu.vallegrande.app.service.ContentModeratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/ms_content_moderator")
public class ContentModeratorRest {

    private final ContentModeratorService contentModeratorService;

    @Autowired
    public ContentModeratorRest(ContentModeratorService contentModeratorService) {
        this.contentModeratorService = contentModeratorService;
    }

    @GetMapping
    public Flux<ContentModerator> getAll(){
        return contentModeratorService.getAll();
    }

    @PostMapping("/save")
    public Mono<ContentModerator> save(@RequestBody ContentModerator contentModerator) {
        return contentModeratorService.save(contentModerator);
    }

}
