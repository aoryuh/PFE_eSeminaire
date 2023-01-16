package mybootapp.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="utilisateur")
public class Utilisateur implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Size(max = 250, message = "max 250 caractères")
    @Basic()
    @Column(name = "nom")
    private String nom;


    @Size(max = 250, message = "max 250 caractères")
    @Basic()
    @Column(name = "prenom")
    private String prenom;


    @NotNull
    @Basic()
    @Column(name = "isAdmin")
    private boolean isAdmin;

    @ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinColumn( name="idComposante" )
    private Composante idComposante;


    @Basic
    @Column(name = "idCAS")
    private String idCAS;



}

