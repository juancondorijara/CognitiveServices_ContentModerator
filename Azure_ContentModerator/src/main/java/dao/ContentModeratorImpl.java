package dao;

import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.ContentModerator;
import org.json.JSONObject;
import services.ContentModeratorS;
import lombok.Data;
import org.json.JSONException;

@Data
public class ContentModeratorImpl {

    ContentModerator modelo;

    public ContentModeratorImpl() {
        modelo = new ContentModerator();
    }

    public void obtenerDatosImagen() throws Exception {
        try {
            JSONObject cadenaJson = ContentModeratorS.obtenerJSonImagen(modelo);
            modelo.setPUNTAJE_ADULTO(cadenaJson.getDouble("AdultClassificationScore"));
            modelo.setCLASIFICACION_ADULTO(cadenaJson.getBoolean("IsImageAdultClassified"));
            modelo.setPUNTAJE_PICANTE(cadenaJson.getDouble("RacyClassificationScore"));
            modelo.setCLASIFICACION_PICANTE(cadenaJson.getBoolean("IsImageRacyClassified"));

            if (modelo.isCLASIFICACION_ADULTO() == true && modelo.isCLASIFICACION_PICANTE() == true) {
                modelo.setRESULTADO("MUY OFENSIVO, la IMAGEN es subido de tono y para adultos");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "MUY OFENSIVO", "La IMAGEN es subido de tono y para adultos"));
                modelo.setIMAGEN("images/malo.png");
            } else if (modelo.isCLASIFICACION_ADULTO() == true || modelo.isCLASIFICACION_PICANTE() == true) {
                modelo.setRESULTADO("OFENSIVO, la IMAGEN es subido de tono o para adultos");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "OFENSIVO", "La IMAGEN es subido de tono o para adultos"));
                modelo.setIMAGEN("images/malo.png");
            } else {
                modelo.setRESULTADO("NADA OFENSIVO, la IMAGEN no es subido de tono ni para adultos");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "NADA OFENSIVO", "La IMAGEN no es subido de tono ni para adultos"));
                modelo.setIMAGEN("images/bueno.png");
            }
        } catch (IOException | JSONException e) {
            System.out.println("Error en obtenerDatosIamgenImpl: " + e.getMessage());
        }
    }

}
