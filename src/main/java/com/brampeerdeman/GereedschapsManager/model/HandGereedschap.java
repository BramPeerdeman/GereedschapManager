package com.brampeerdeman.GereedschapsManager.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("hand")
public class HandGereedschap extends Gereedschap
{

    public HandGereedschap()
    {
        this.setType("hand");
    }

    @Override
    public String gebruiksInstructies()
    {
        return "Gebruik met beleid. Geen speciale bescherming vereist.";
    }

    @Override
    public boolean vereistVeiligheidscheck()
    {
        return false;
    }
}
