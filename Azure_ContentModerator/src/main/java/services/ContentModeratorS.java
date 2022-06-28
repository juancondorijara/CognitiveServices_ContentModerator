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

    public static void main(String[] args) throws Exception {
        //IMAGEN
        ContentModerator modelo = new ContentModerator();
        modelo.setURL("https://moderatorsampleimages.blob.core.windows.net/samples/sample.jpg");
        JSONObject cadenaJson = obtenerJSonImagen(modelo);
        System.out.println("PUNTAJE_ADULTO = " + cadenaJson.getDouble("AdultClassificationScore"));
        System.out.println("CLASIFICACION_ADULTO = " + cadenaJson.getBoolean("IsImageAdultClassified"));
        System.out.println("PUNTAJE_PICANTE = " + cadenaJson.getDouble("RacyClassificationScore"));
        System.out.println("CLASIFICACION_PICANTE = " + cadenaJson.getBoolean("IsImageRacyClassified"));
        
        //TEXTO
//        modelo.setTEXTO("fuck, dumb, crap");
//        JSONObject cadenaJsonTexto = obtenerJsonTexto(modelo);
//        System.out.println("CATEGORIA 1 = " + cadenaJsonTexto.getDouble("AdultClassificationScore"));
    }

    //IMAGEN
    public static JSONObject obtenerJSonImagen(ContentModerator modelo) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/json");
        //RequestBody body = RequestBody.create(mediaType, "{\r\n  \"DataRepresentation\":\"URL\",\r\n  \"Value\":\"https://www.nintenderos.com/wp-content/uploads/2018/06/maxresdefault-4-2.jpg\"\r\n}");
        RequestBody body = RequestBody.create(mediaType, "{\r\n  \"DataRepresentation\":\"URL\",\r\n  \"Value\":\"" + modelo.getURL() + "\"\r\n}");

        Request request = new Request.Builder()
                .url("https://eastus.api.cognitive.microsoft.com/contentmoderator/moderate/v1.0/ProcessImage/Evaluate")
                .method("POST", body)
                .addHeader("Host", "eastus.api.cognitive.microsoft.com")
                .addHeader("Content-Type", "application/json")
                .addHeader("Ocp-Apim-Subscription-Key", "9f5da5d1c39a44ac83427b58fdcb128e")
                .build();
        Response response = client.newCall(request).execute();
//        System.out.println("LISTA = " + response.body().string());
        if (!response.isSuccessful()) {
            throw new IOException("Unexpected code " + response);
        }
        
        JSONObject jsonObject = new JSONObject(response.body().string());
        return jsonObject;
    }
    
    public static JSONObject obtenerJsonTexto(ContentModerator modelo) throws IOException, Exception {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, modelo.getTEXTO());
        Request request = new Request.Builder()
                .url("https://eastus.api.cognitive.microsoft.com/contentmoderator/moderate/v1.0/ProcessImage/Evaluate")
                .method("POST", body)
                .addHeader("Host", "eastus.api.cognitive.microsoft.com")
                .addHeader("Content-Type", "text/plain")
                .addHeader("Ocp-Apim-Subscription-Key", "9f5da5d1c39a44ac83427b58fdcb128e")
                .build();
        Response response = client.newCall(request).execute();
        System.out.println("LISTA = " + response.body().string());
        if (!response.isSuccessful()) {
            throw new IOException("Unexpected code " + response);
        }
        
        JSONObject jsonObject = new JSONObject(response.body().string());
        return jsonObject;
    }
    
}
