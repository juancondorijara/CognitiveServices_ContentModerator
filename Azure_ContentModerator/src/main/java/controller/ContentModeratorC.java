package controller;

import dao.ContentModeratorImpl;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.ContentModerator;
import lombok.Data;
import org.json.JSONException;
import org.json.JSONObject;
import services.ContentModeratorS;

@Data
@Named(value = "contentmoderatorC")
@SessionScoped
public class ContentModeratorC implements Serializable {

    ContentModerator modelo;
    ContentModeratorImpl dao;
    
    public ContentModeratorC() {
        modelo = new ContentModerator();
        dao = new ContentModeratorImpl();
        modelo.setIMAGEN("images/bueno.png");
        modelo.setURL("https://moderatorsampleimages.blob.core.windows.net/samples/sample.jpg");
    }

    public void obtenerDatosImagen() throws Exception {
        try {
            validarDatosImagen();
//            dao.obtenerDatosImagen();
        } catch (IOException | JSONException e) {
            System.out.println("Error en obtenerDatosIamgenC: " + e.getMessage());
        }
    }
    
    public void validarDatosImagen() throws Exception {
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
            System.out.println("Error en validarDatosImagenC: " + e.getMessage());
        }
    }

}
