package controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.ContentModerator;
import org.json.JSONObject;
import services.ContentModeratorS;
import lombok.Data;

@Data
@Named(value = "contentmoderatorC")
@SessionScoped
public class ContentModeratorC implements Serializable {

    ContentModerator modelo;
    
    boolean CLASIFICACION_ADULTO;

    boolean CLASIFICACION_PICANTE;

    public ContentModeratorC() {
        modelo = new ContentModerator();
        modelo.setIMAGEN("images/bueno.png");
        modelo.setURL("https://moderatorsampleimages.blob.core.windows.net/samples/sample.jpg");
    }

    public  void obtenerDatosImagen() throws Exception {
        try {            
            JSONObject cadenaJson = ContentModeratorS.obtenerJSonImagen(modelo);
            modelo.setPUNTAJE_ADULTO(cadenaJson.getDouble("AdultClassificationScore"));
            modelo.setCLASIFICACION_ADULTO(cadenaJson.getBoolean("IsImageAdultClassified"));
            modelo.setPUNTAJE_PICANTE(cadenaJson.getDouble("RacyClassificationScore"));
            modelo.setCLASIFICACION_PICANTE(cadenaJson.getBoolean("IsImageRacyClassified"));
            
            CLASIFICACION_ADULTO = cadenaJson.getBoolean("IsImageAdultClassified");
            CLASIFICACION_PICANTE = cadenaJson.getBoolean("IsImageRacyClassified");
            
            if (CLASIFICACION_ADULTO == false && CLASIFICACION_PICANTE == false) {
                modelo.setRESULTADO("NADA OFENSIVO, la IMAGEN no es subido de tono ni para adultos");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "NADA OFENSIVO", "La IMAGEN no es subido de tono ni para adultos"));
                modelo.setIMAGEN("images/bueno.png");
            } 
            if (CLASIFICACION_ADULTO == true || CLASIFICACION_PICANTE == true) {
                modelo.setRESULTADO("OFENSIVO, la IMAGEN es subido de tono O para adultos");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "OFENSIVO", "La IMAGEN es subido de tono O para adultos"));
                modelo.setIMAGEN("images/malo.png");
            } 
            if (CLASIFICACION_ADULTO == true && CLASIFICACION_PICANTE == true) {
                modelo.setRESULTADO("MUY OFENSIVO, la IMAGEN es subido de tono y para adultos");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "MUY OFENSIVO", "La IMAGEN es subido de tono y para adultos"));
                modelo.setIMAGEN("images/malo.png");
            }
        } catch (Exception e) {
            System.out.println("Error en obtenerDatosIamgen: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
