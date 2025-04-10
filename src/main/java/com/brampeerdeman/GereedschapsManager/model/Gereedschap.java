package com.brampeerdeman.GereedschapsManager.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Gereedschap
{
    @Id
    @GeneratedValue
    private long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Locatie location;

    private Boolean loaned = Boolean.FALSE;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "loaned_status_change")
    private Date loanedStatusChange;

    @ManyToOne
    @JoinColumn(name = "gebruiker_id")
    private Gebruiker gebruiker;

    public Gebruiker getGebruiker() {
        return gebruiker;
    }

    public void setGebruiker(Gebruiker gebruiker) {
        this.gebruiker = gebruiker;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Locatie getLocation() {
        return location;
    }

    public void setLocation(Locatie location) {
        this.location = location;
    }

    public boolean isLoaned() {
        return loaned;
    }

    public void setLoaned(boolean loaned) {
        this.loaned = loaned;
        this.loanedStatusChange = new Date();
    }

    public Date getLoanedStatusChange() {
        return loanedStatusChange;
    }

    public void setLoanedStatusChange(Date loanedStatusChange) {
        this.loanedStatusChange = loanedStatusChange;
    }
}
