package PFE.eSeminaire.model;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idUser;

    @NotBlank(message = "cannot be blank")
    @Basic(optional = false)
    private String name;

    @NotBlank(message = "cannot be blank")
    @Basic(optional = false)
    private String firstName;

    @NotBlank(message = "cannot be blank")
    @Basic(optional = false)
    @Column(unique = true)
    private String mail;

    @NotBlank(message = "cannot be blank")
    @Basic(optional = false)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    private Team team;

    @ElementCollection(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<String> roles;


}
