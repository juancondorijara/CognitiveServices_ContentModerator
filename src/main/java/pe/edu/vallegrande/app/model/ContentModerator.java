package pe.edu.vallegrande.app.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table(name = "ContentModerator")
public class ContentModerator {

    @Id
    private Integer id;

    @Column(value = "adultclassificationscore")
    private double adultClassificationScore;

    @Column(value = "isimageadultclassified")
    private boolean isImageAdultClassified;

    @Column(value = "racyclassificationscore")
    private double racyClassificationScore;

    @Column(value = "isimageracyclassified")
    private boolean isImageRacyClassified;

    @Column(value = "resultboolean")
    private boolean resultBoolean;

    @Column(value = "url")
    private String url;

    @Column(value = "image")
    private String image;

    @Column(value = "resultstring")
    private String resultString;

    @Column(value = "active")
    private boolean active;

}
