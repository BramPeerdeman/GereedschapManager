package com.brampeerdeman.GereedschapsManager.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;

import java.util.Date;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = HandGereedschap.class, name = "hand"),
        @JsonSubTypes.Type(value = ElektrischGereedschap.class, name = "elektrisch")
})
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public abstract class Gereedschap
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

    private String gebruiksInstructies;
    private boolean vereistVeiligheidscheck;

    @Column(insertable = false, updatable = false)
    private String type;

    public abstract String gebruiksInstructies();
    public abstract boolean vereistVeiligheidscheck();

    public String getGebruiksInstructies() {
        return gebruiksInstructies;
    }

    public void setGebruiksInstructies(String gebruiksInstructies) {
        this.gebruiksInstructies = gebruiksInstructies;
    }

    public boolean isVereistVeiligheidscheck() {
        return vereistVeiligheidscheck;
    }

    public void setVereistVeiligheidscheck(boolean vereistVeiligheidscheck) {
        this.vereistVeiligheidscheck = vereistVeiligheidscheck;
    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
