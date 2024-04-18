package com.vg.ms_content_moderator.service.impl;

import com.vg.ms_content_moderator.model.ContentModerator;
import com.vg.ms_content_moderator.repository.ContentModeratorRepository;
import com.vg.ms_content_moderator.service.ContentModeratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONObject;

@Slf4j
@Service
public class ContentModeratorServiceImpl implements ContentModeratorService {

    private final ContentModeratorRepository contentModeratorRepository;

    @Autowired
    public ContentModeratorServiceImpl(ContentModeratorRepository contentModeratorRepository) {
        this.contentModeratorRepository = contentModeratorRepository;
    }

    @Override
    public Flux<ContentModerator> getAll() {
        log.info("Mostrando datos");
        return contentModeratorRepository.getAll();
    }

    @Override
    public Mono<ContentModerator> save(ContentModerator contentModerator) {
        try {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/json");
        @SuppressWarnings("deprecation")
        RequestBody body = RequestBody.create(mediaType, "{\r\n  \"DataRepresentation\":\"URL\",\r\n  \"Value\":\"" + contentModerator.getUrl() + "\"\r\n}");
        Request request = new Request.Builder()
                .url("https://eastus.api.cognitive.microsoft.com/contentmoderator/moderate/v1.0/ProcessImage/Evaluate")
                .method("POST", body)
                .addHeader("Host", "eastus.api.cognitive.microsoft.com")
                .addHeader("Content-Type", "application/json")
                .addHeader("Ocp-Apim-Subscription-Key", "8150ba9e33824e3a9ff48a5f29eeb7ce")
                .build();
        Response response = client.newCall(request).execute();
        JSONObject jsonObject = new JSONObject(response.body().string());
        contentModerator.setAdultClassificationScore(jsonObject.getDouble("AdultClassificationScore"));
        contentModerator.setImageAdultClassified(jsonObject.getBoolean("IsImageAdultClassified"));
        contentModerator.setRacyClassificationScore(jsonObject.getDouble("RacyClassificationScore"));
        contentModerator.setImageRacyClassified(jsonObject.getBoolean("IsImageRacyClassified"));
        contentModerator.setResultBoolean(jsonObject.getBoolean("Result"));

        if (contentModerator.isResultBoolean()) {
            contentModerator.setImage("https://drive.google.com/file/d/114tPPHGydO4lBw-X9DR2JYqXSMncvaYg/view?usp=drive_link");
            contentModerator.setResultString("Imagen MODERADA para ADULTOS u OBSCENA");
        } else {
            contentModerator.setImage("https://drive.google.com/file/d/1AvwuhGqrgZyA14rFG_8uCVbgaLD3_32H/view?usp=drive_link");
            contentModerator.setResultString("Imagen NO MODERADA");
        }

        } catch (Exception e) {
            log.error("Error en el API: " + e.getMessage());
            log.error("Error en el API: " + e.getMessage());
            log.error("Error en el API: " + e.getMessage());
        }
        return contentModeratorRepository.save(contentModerator);
    }

}
