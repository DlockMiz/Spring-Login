package project.school.springreacttemplate.Models.ReservationData;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class Reservations {
    @Id
    @GeneratedValue
    private Integer id;
    private String siteSpotId;
    private Integer site_id;
    private Date reservationDateStart;
    private Date reservationDateEnd;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSiteSpotId() {
        return siteSpotId;
    }

    public void setSiteSpotId(String siteSpotId) {
        this.siteSpotId = siteSpotId;
    }

    public Integer getSite_id() {
        return site_id;
    }

    public void setSite_id(Integer site_id) {
        this.site_id = site_id;
    }

    public Date getReservationDateStart() {
        return reservationDateStart;
    }

    public void setReservationDateStart(Date reservationDateStart) {
        this.reservationDateStart = reservationDateStart;
    }

    public Date getReservationDateEnd() {
        return reservationDateEnd;
    }

    public void setReservationDateEnd(Date reservationDateEnd) {
        this.reservationDateEnd = reservationDateEnd;
    }
}
