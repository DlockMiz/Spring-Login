package project.school.springreacttemplate.Models.SiteSpotData;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class SiteSpots {
    @Id
    @GeneratedValue
    private Integer id;
    private String siteSpotNumber;
    private Integer siteId;
    private String spotType;
    private String amountOfUpcharge;
    private Integer closed;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSiteSpotNumber() {
        return siteSpotNumber;
    }

    public void setSiteSpotNumber(String siteSpotNumber) {
        this.siteSpotNumber = siteSpotNumber;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public String getSpotType() {
        return spotType;
    }

    public void setSpotType(String spotType) {
        this.spotType = spotType;
    }

    public String getAmountOfUpcharge() {
        return amountOfUpcharge;
    }

    public void setAmountOfUpcharge(String amountOfUpcharge) {
        this.amountOfUpcharge = amountOfUpcharge;
    }

    public Integer getClosed() {
        return closed;
    }

    public void setClosed(Integer closed) {
        this.closed = closed;
    }
}
