package PFE.eSeminaire.model;

import PFE.eSeminaire.model.updateClass.UpdateSeminar;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Seminar implements Serializable {


    static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idSeminar;

    @NotBlank(message = "cannot be blank")
    @Basic(optional = false)
    private String title;

    @NotBlank(message = "cannot be blank")
    @Basic(optional = false)
    private String description;

    @Basic(optional = false)
    @DateTimeFormat(pattern= "yyyy-MM-dd'T'HH:mm")
    private Date date;

    @NotBlank(message = "cannot be blank")
    @Basic(optional = false)
    private String location;

    @ManyToMany
    private List<User> authors;

    @ManyToOne(optional = false)
    private Team team;

    @ElementCollection
    private List<String> optionalContentLinks;

    public UpdateSeminar createUpdateSeminar(){
        return new UpdateSeminar(this.idSeminar, this.getDate(), this.getLocation() );
    }
}