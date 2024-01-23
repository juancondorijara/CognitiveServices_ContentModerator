package com.vg.ms_content_moderator.controller;

import com.vg.ms_content_moderator.model.ContentModerator;
import com.vg.ms_content_moderator.service.ContentModeratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/ms_content_moderator")
public class ContentModeratorController {

    private final ContentModeratorService contentModeratorService;

    @Autowired
    public ContentModeratorController(ContentModeratorService contentModeratorService) {
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
