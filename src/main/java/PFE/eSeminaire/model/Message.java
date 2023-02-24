package PFE.eSeminaire.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Message {

    static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "cannot be blank")
    @Basic(optional = false)
    private String subject;

    @ManyToOne()
    private User user;

    @NotBlank(message = "cannot be blank")
    @Basic(optional = false)
    private String content;

    @Basic(optional = false)
    @DateTimeFormat(pattern= "yyyy-MM-dd'T'HH:mm")
    private Date date;
}
