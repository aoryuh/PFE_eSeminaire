package PFE.eSeminaire.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Seminar {


    static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idSeminar;

    @NotBlank(message = "cannot be blank")
    @Basic(optional = false)
    private Date date;

    @NotBlank(message = "cannot be blank")
    @Basic(optional = false)
    private String location;

    @NotBlank(message = "cannot be blank")
    @ManyToOne(optional = false)
    private User author;

    @NotBlank(message = "cannot be blank")
    @ManyToOne(optional = false)
    private Team team;

    @ElementCollection(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<String> optionalContentLinks;
}