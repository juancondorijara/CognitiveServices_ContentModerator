package pe.edu.vallegrande.app.rest;

import org.springframework.http.ResponseEntity;
import pe.edu.vallegrande.app.model.ContentModerator;
import pe.edu.vallegrande.app.service.ContentModeratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/ms_content_moderator")
public class ContentModeratorRest {

    private final ContentModeratorService contentModeratorService;

    @Autowired
    public ContentModeratorRest(ContentModeratorService contentModeratorService) {
        this.contentModeratorService = contentModeratorService;
    }

    @GetMapping
    public Flux<ContentModerator> findAll(){
        return contentModeratorService.findAll();
    }

    @GetMapping("/id/{id}")
    public Mono<ContentModerator> findById(@PathVariable Integer id){
        return contentModeratorService.findById(id);
    }

    @GetMapping("/active/{active}")
    public Flux<ContentModerator> findByActive(@PathVariable boolean active) { return contentModeratorService.findByActive(active); }

    @PostMapping("/save")
    public Mono<ContentModerator> save(@RequestBody ContentModerator contentModerator) {
        return contentModeratorService.save(contentModerator);
    }

    @PostMapping("/delete/{id}")
    public Mono<ResponseEntity<ContentModerator>> delete(@PathVariable Integer id) { return contentModeratorService.delete(id); }

    @PostMapping("/restore/{id}")
    public Mono<ResponseEntity<ContentModerator>> restore(@PathVariable Integer id){ return contentModeratorService.restore(id); }

}
