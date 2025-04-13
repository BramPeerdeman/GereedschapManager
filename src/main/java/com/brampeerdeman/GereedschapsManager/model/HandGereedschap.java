package com.brampeerdeman.GereedschapsManager.model;

import jakarta.persistence.Entity;

@Entity
public class HandGereedschap extends Gereedschap
{
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
