package com.brampeerdeman.GereedschapsManager.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public abstract class Gebruiker
{
    @Id
    @GeneratedValue
    private Long id;

    @JsonProperty("username")
    private String gebruikersnaam;

    @JsonProperty("password")
    private String wachtwoord;

    public abstract String getRol();

    public boolean magGereedschapToevoegen()
    {
        return false;
    }

    public boolean magGebruikerBeheren()
    {
        return false;
    }

    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    public void setGebruikersnaam(String gebruikersnaam) {
        this.gebruikersnaam = gebruikersnaam;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
