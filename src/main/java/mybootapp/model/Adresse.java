package mybootapp.model;

import lombok.*;
import mybootapp.model.base.BaseData;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;



@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="adresse")
public class Adresse implements Serializable{


    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @NotEmpty(message = "le champ ne doit pas être vide")
    @Basic()
    @Column(name = "ligne")
    private String ligne;


    //autoremplit
   // @Size(max = 30)
    @Basic()
    @Column(name = "numero")
    private int numero;

    @Basic()
    @Column(name = "nom")
    private String nom;


    @Size(max = 50, message = "max 50 caractères")
    @Basic()
    @Column(name = "ligne2")
    private String ligne2;

    @Size(max = 50, message = "max 50 caractères")
    @Basic()
    @Column(name = "ligne3")
    private String ligne3;

    //modifier pour reconnaitre code postal français
    @NotNull(message = "le champ ne doit pas être vide")
    @Max(value = 5, message = "max 5 caractères")
    @Basic()
    @Column(name = "codePostal")
    private int codePostal;


    @Basic()
    @Column(name = "codePays")
    private String codePays;

    @Size(max = 50, message = "max 50 caractères")
    @Basic()
    @Column(name = "ligne5")
    private String ligne5;

    @NotNull(message = "le champ ne doit pas être vide")
  //  @Size(max = 4, message = "entrez un numéro de rue cohérent")
    @Basic()
    @Column(name = "numeroVoie")
    private int numerVoie;

    //liste déroulante (option vide)
    @Basic()
    @Column(name = "indiceRepetition")
    private String indiceRepetition;

    //liste déroulante (option vide)
    @Basic()
    @Column(name = "natureDeVoie")
    private String natureDeVoie;

    @Size(max = 32, message = "max 32 caractères")
    @Basic()
    @Column(name = "libelleVoie")
    private String libelleVoie;

    @Size(max = 250, message = "max 250 caractères")
    @Basic()
    @Column(name = "acessibiliteBatimentaire")
    private String accessibiliteBatimentaire;

    //checkbox non/oui -> 0/1

    @Basic()
    @Column(name = "conformiteReglementaire")
    private int conformiteReglementaire;

    /*
    @NotNull(message = "le champ ne doit pas être vide")
    @OneToOne
    @JoinColumn(name="idCoordonnee")
    private Coordonnee coordonnee;

     */

}
