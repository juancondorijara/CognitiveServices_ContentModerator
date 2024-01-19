package com.vg.ms_content_moderator.service;

import com.vg.ms_content_moderator.model.ContentModerator;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface ContentModeratorService {

    Flux<ContentModerator> getAll();

    Mono<ContentModerator> save(ContentModerator contentModerator);

}
