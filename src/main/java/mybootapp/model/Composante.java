package mybootapp.model;

import lombok.*;
import mybootapp.model.base.BaseData;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;


@Entity
@Data
@AllArgsConstructor
@Getter
@Setter
@Table(name="composante")
public class Composante implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    //@NotBlank(message = "le champ ne doit pas être vide")
    @Size(max = 250, message = "max 250 caractères")
    @Basic()
    @Column(name = "intitule")
    private String intitule;

    @OneToOne
    @JoinColumn( name="correspondant" )
    private Utilisateur correspondant;

    @OneToMany
    @JoinColumn( name="formations" )
    private Collection<Formation> formations;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn( name="adresses" )
    private Collection<Adresse> adresses;

    public void addFormation(Formation f){
        formations.add(f);
    }

    public void addAdresse(Adresse a){
        adresses.add(a);
    }

    public void removeAdress(Adresse a){
        adresses.remove(a);
    }

    public Composante() {
        this.setAdresses(new ArrayList<Adresse>());
        this.setFormations(new ArrayList<Formation>());

    }

    /*
    public void addUser( String prenom, String nom){
        correspondant.setNom(nom);
        correspondant.setPrenom(prenom);

    }*/







}
