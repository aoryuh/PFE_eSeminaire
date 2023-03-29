package PFE.eSeminaire.model;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "email"})})
public class User implements Serializable {

    static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idUser;

    @NotBlank(message = "Ce champ ne doit pas rester vide")
    @Basic(optional = false)
    private String name;

    @NotBlank(message = "Ce champ ne doit pas rester vide")
    @Basic(optional = false)
    private String firstName;

    @NotBlank(message = "Ce champ ne doit pas rester vide")
    @Basic(optional = false)
    @Column(unique = true,name = "email")
    private String mail;

    @NotBlank(message = "Ce champ ne doit pas rester vide")
    @Basic(optional = false)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    private Team team;

    @ElementCollection(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<String> roles;


}
