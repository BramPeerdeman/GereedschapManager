package com.brampeerdeman.GereedschapsManager.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("elektrisch")
public class ElektrischGereedschap extends Gereedschap
{
    public ElektrischGereedschap()
    {
        this.setType("elektrisch");
    }

    private int vermogen;

    @Override
    @JsonProperty("gebruiksInstructies")
    public String gebruiksInstructies()
    {
        return "Draag gehoorbescherming en gebruik een veiligheidsbril.";
    }

    @Override
    @JsonProperty("vereistVeiligheidscheck")
    public boolean vereistVeiligheidscheck()
    {
        return true;
    }

    public int getVermogen()
    {
        return vermogen;
    }

    public void setVermogen(int vermogen)
    {
        this.vermogen = vermogen;
    }
}
