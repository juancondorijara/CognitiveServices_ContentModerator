package services;

import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONObject;
import model.ContentModerator;

public class ContentModeratorS {
    
    ContentModerator modelo;
    
    public ContentModeratorS() {
        modelo = new ContentModerator();
    }

    public static void main(String[] args) throws Exception {
        ContentModerator modelo = new ContentModerator();
        modelo.setURL("https://moderatorsampleimages.blob.core.windows.net/samples/sample.jpg");
        JSONObject cadenaJson = obtenerJSonImagen(modelo);
        System.out.println("PUNTAJE_ADULTO = " + cadenaJson.getDouble("AdultClassificationScore"));
        System.out.println("CLASIFICACION_ADULTO = " + cadenaJson.getBoolean("IsImageAdultClassified"));
        System.out.println("PUNTAJE_PICANTE = " + cadenaJson.getDouble("RacyClassificationScore"));
        System.out.println("CLASIFICACION_PICANTE = " + cadenaJson.getBoolean("IsImageRacyClassified"));
    }
    
    public static JSONObject obtenerJSonImagen(ContentModerator modelo) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\r\n  \"DataRepresentation\":\"URL\",\r\n  \"Value\":\"" + modelo.getURL() + "\"\r\n}");

        Request request = new Request.Builder()
                .url("https://eastus.api.cognitive.microsoft.com/contentmoderator/moderate/v1.0/ProcessImage/Evaluate")
                .method("POST", body)
                .addHeader("Host", "eastus.api.cognitive.microsoft.com")
                .addHeader("Content-Type", "application/json")
                .addHeader("Ocp-Apim-Subscription-Key", "ccf85ae7b68e4d1badd7c65151f950a0")
                .build();
        Response response = client.newCall(request).execute();
//        System.out.println("LISTA = " + response.body().string());
        if (!response.isSuccessful()) {
            throw new IOException("Unexpected code " + response);
        }
        
        JSONObject jsonObject = new JSONObject(response.body().string());
        return jsonObject;
    }
    
}
