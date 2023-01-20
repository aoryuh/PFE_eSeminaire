package PFE.eSeminaire.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Team implements Serializable {

    static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idTeam;

    @NotBlank(message = "cannot be blank")
    @Basic(optional = false)
    private String name;

    @OneToMany(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinColumn
    private List<User> members;

    @OneToMany(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinColumn
    private List<Seminar> seminars;
}
