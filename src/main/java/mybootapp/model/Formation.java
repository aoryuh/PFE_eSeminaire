package mybootapp.model;

import lombok.*;
import mybootapp.model.base.BaseData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@Getter
@Setter
@Table(name="formation")
public class Formation extends BaseData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Pattern(regexp="^[0-9]{1,6}$", message = "le champ ne contient que des chiffres et a une longueur de 6 caractères maximum")
    @NotBlank(message = "le champ ne peut pas être vide")
    @Basic()
    @Column(name = "codeFormation")
    private String code;

    @NotBlank(message = "le champ ne peut pas être vide")
    @Basic()
    @Column(name = "etatEdition")
    private String etatEdition;

    @NotBlank(message = "le champ ne doit pas être vide")
    @Size(max = 255, message = "max 255 caractères")
    @Basic()
    @Column(name = "intitule")
    private String intitule;

    @NotBlank(message = "le champ ne doit pas être vide")
    @Size(max = 3000, message = "max 3000 caractères")
    @Basic()
    @Column(name = "objectif")
    private String objectif;

    @NotBlank(message = "le champ ne doit pas être vide")
    @Size(max = 3000, message = "max 3000 caractères")
    @Basic()
    @Column(name = "resultatsAttendus")
    private String resultatsAttendus;

    @NotBlank(message = "le champ ne doit pas être vide")
    @Size(max = 3000, message = "max 3000 caractères")
    @Basic()
    @Column(name = "contenu")
    private String contenu;

    @NotBlank(message = "le champ ne peut pas être vide")
    @Basic()
    @Column(name = "typeParcours")
    private String typeParcours;

    @Pattern(regexp="^[0-9]{1}$", message = "le champ ne contient que des chiffres et a une longueur de 1")
    @NotBlank(message = "le champ ne peut pas être vide")
    @Basic()
    @Column(name = "objectifGeneral")
    private String objectifGeneral;

    @Pattern(regexp="^[0-9]{1,6}$", message = "le champ ne contient que des chiffres et a une longueur de 6")
    @NotBlank(message = "le champ ne peut pas être vide")
    @Basic()
    @Column(name = "CERTIFINFO")
    private String CERTIFINFO;

    @Embedded
    @JoinColumn( name="action" )
    private Action action;

    @ManyToOne
    @JoinColumn( name="adresse" )
    private Adresse adresse;


    @ManyToOne
    @JoinColumn( name="composante" )
    private Composante composante;

    public Formation() {
        this.action = new Action();
    }

    public void init(String composante, String typeFormation){
        this.setCode("0000");
        this.setCERTIFINFO("000000");
        this.setEtatEdition("brouillon");
        this.setIntitule(typeFormation + " " + composante);
        this.setObjectif("objectif " + typeFormation + " " + composante);
        this.setContenu("contenu " + typeFormation + " " + composante);
        this.setResultatsAttendus("resultats " + typeFormation + " " + composante);
        this.setObjectifGeneral("0");
        this.setTypeParcours("0");
        this.getAction().init(composante, typeFormation);
    }

    public void finishCreation(){
        this.setEtatEdition("brouillon");
        this.setObjectif("|");
        this.setContenu("|");
        this.setResultatsAttendus("|");
        this.setObjectifGeneral("0");
        this.setTypeParcours("0");
        this.getAction().finishCreation();
    }
}
