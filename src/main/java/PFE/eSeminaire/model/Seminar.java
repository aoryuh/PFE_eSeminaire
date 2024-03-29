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
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@Getter
@Setter
public class Seminar implements Serializable {


    static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idSeminar;

    @NotBlank(message = "Le champ doit etre rempli")
    @Basic(optional = false)
    @Column(unique = true)
    private String title;

    @Basic(optional = false)
    @Lob
    @Column(length = 5000)
    private String description;

    @Basic(optional = false)
    @DateTimeFormat(pattern= "yyyy-MM-dd'T'HH:mm")
    private Date date;

    @NotBlank(message = "Le champ doit etre rempli")
    @Basic(optional = false)
    private String location;

    @ManyToMany
    private List<User> authors;

    @ManyToOne(optional = false)
    private Team team;

    @ElementCollection
    private List<String> optionalContentLinks;

    private boolean isOK;

    private String errorDescription;

    public Seminar() {
        isOK = true;
    }

    public UpdateSeminar createUpdateSeminar(){
        return new UpdateSeminar(this.idSeminar, this.getDate(), this.getLocation() );
    }


    public void setDate(Date date) {
        date.setYear(date.getYear()-1900);
        this.date = date;
    }

    @Override
    public String toString() {
        return "Seminar{" +
                "idSeminar=" + idSeminar +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", location='" + location + '\'' +
                ", authors=" + authors +
                ", team=" + team +
                ", optionalContentLinks=" + optionalContentLinks +
                '}';
    }
}