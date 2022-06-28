package model;

import lombok.Data;

@Data

public class ContentModerator {

    //TEXTO
    double CATEGORIA1;
    double CATEGORIA2;
    double CATEGORIA3;
    
    String CATEGORIA11;
    String CATEGORIA22;
    String CATEGORIA33;
    
    String TEXTO;
    String LENGUAJE;
    
    //IMAGEN
    String URL;
    String IMAGEN;
    String RESULTADO;
    
    Double PUNTAJE_ADULTO;
    boolean CLASIFICACION_ADULTO;
    
    Double PUNTAJE_PICANTE;
    boolean CLASIFICACION_PICANTE;
    
}
