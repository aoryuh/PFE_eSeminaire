package PFE.eSeminaire.model;

import lombok.*;

import javax.persistence.*;
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

    @Basic
    @Column
    private String subject;

    @ManyToOne()
    private User user;

    @Basic
    @Column
    private String content;

    @Basic
    @Column
    private Date date;
}
