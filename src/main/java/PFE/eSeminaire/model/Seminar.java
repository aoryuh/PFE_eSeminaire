package PFE.eSeminaire.model;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Seminar {

    static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idSeminar;


    @Basic()
    @Column
    private String title;

    /**
     * Sur le type Date on retrouve aussi l'heure
     */
    @Basic()
    @Column
    private Date date;


    @Basic()
    @Column
    private String location;


    @ManyToOne()
    private User author;


    @ManyToOne()
    private Team team;

    @ElementCollection(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<String> optionalContentLinks;

    @Basic
    @Column
    private String description;


}