package pe.edu.vallegrande.app.service.impl;

import pe.edu.vallegrande.app.model.ContentModerator;
import pe.edu.vallegrande.app.repository.ContentModeratorRepository;
import pe.edu.vallegrande.app.service.ContentModeratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public Flux<ContentModerator> findAll() {
        log.info("Mostrando datos");
        return contentModeratorRepository.findAll();
    }

    @Override
    public Mono<ContentModerator> findById(Integer id) {
        log.info("Mostrando datos");
        return contentModeratorRepository.findById(id);
    }

    @Override
    public Flux<ContentModerator> findByActive(boolean active) {
        log.info("Personas filtradas por estado = " + active);
        return contentModeratorRepository.findByActive(active);
    }

    @Value("${spring.contentmoderator.apikey}")
    private String apiKey; //"8150ba9e33824e3a9ff48a5f29eeb7ce"

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
                .addHeader("Ocp-Apim-Subscription-Key", apiKey)
                .build();
        Response response = client.newCall(request).execute();
        JSONObject jsonObject = new JSONObject(response.body().string());
        contentModerator.setAdultClassificationScore(jsonObject.getDouble("AdultClassificationScore"));
        contentModerator.setImageAdultClassified(jsonObject.getBoolean("IsImageAdultClassified"));
        contentModerator.setRacyClassificationScore(jsonObject.getDouble("RacyClassificationScore"));
        contentModerator.setImageRacyClassified(jsonObject.getBoolean("IsImageRacyClassified"));
        contentModerator.setResultBoolean(jsonObject.getBoolean("Result"));
        contentModerator.setActive(true);

        if (contentModerator.isResultBoolean()) {
            contentModerator.setImage("https://illustoon.com/photo/951.png");
            contentModerator.setResultString("Imagen MODERADA para ADULTOS u OBSCENA");
        } else {
            contentModerator.setImage("https://illustoon.com/photo/7504.png");
            contentModerator.setResultString("Imagen NO MODERADA");
        }

        } catch (Exception e) {
            log.error("Error en el API: " + e.getMessage());
        }
        log.info("Creado = " + contentModerator.toString());
        return contentModeratorRepository.save(contentModerator);
    }

    @Override
    public Mono<ContentModerator> update(ContentModerator contentModerator) {
        log.info("Actualizado = " + contentModerator.toString());
        contentModerator.setActive(true);
        return contentModeratorRepository.save(contentModerator);
    }

    @Override
    public Mono<ResponseEntity<ContentModerator>> delete(Integer id) {
        log.info("Eliminado = " + id);
        return contentModeratorRepository.findById(id).flatMap(newContentModerator -> {
            newContentModerator.setActive(false);
            return contentModeratorRepository.save(newContentModerator);
        }).map(updatedDocument -> new ResponseEntity<>(updatedDocument, HttpStatus.OK)).defaultIfEmpty(new ResponseEntity<>(HttpStatus.OK));
    }

    @Override
    public Mono<ResponseEntity<ContentModerator>> restore(Integer id) {
        log.info("Restaurado = " + id);
        return contentModeratorRepository.findById(id).flatMap(newContentModerator -> {
            newContentModerator.setActive(true);
            return contentModeratorRepository.save(newContentModerator);
        }).map(updatedDocument -> new ResponseEntity<>(updatedDocument, HttpStatus.OK)).defaultIfEmpty(new ResponseEntity<>(HttpStatus.OK));
    }


}
