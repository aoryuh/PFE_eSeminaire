package mybootapp.model.base;

import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Data
@MappedSuperclass
public abstract class BaseData {

    @Column(updatable = false)
    @CreationTimestamp
    private Date dateCrea;
    @UpdateTimestamp
    private Date dateMaj;

    private String dateCreaString;
    private String dateMajString;

    @PostConstruct
    public void DateCreaToString() {
        SimpleDateFormat DateFor = new SimpleDateFormat("yyyy/MM/dd hh:mm");
        dateCreaString = DateFor.format(this.dateCrea);
    }

    @PostConstruct
    public void DateMajToString() {
        SimpleDateFormat DateFor = new SimpleDateFormat("yyyy/MM/dd hh:mm");
        dateMajString = DateFor.format(this.dateCrea);
    }
}