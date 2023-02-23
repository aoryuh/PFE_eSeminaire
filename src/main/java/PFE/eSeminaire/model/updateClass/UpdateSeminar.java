package PFE.eSeminaire.model.updateClass;

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

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateSeminar implements Serializable {


    static final long serialVersionUID = 1L;

    private Long idSeminar;

    @Basic(optional = false)
    @DateTimeFormat(pattern= "yyyy-MM-dd'T'HH:mm")
    private Date date;

    @NotBlank(message = "cannot be blank")
    @Basic(optional = false)
    private String location;
}