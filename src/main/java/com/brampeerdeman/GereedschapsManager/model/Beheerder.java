package com.brampeerdeman.GereedschapsManager.model;

import jakarta.persistence.Entity;

@Entity
public class Beheerder extends Gebruiker
{
    @Override
    public String getRol()
    {
        return "BEHEERDER";
    }

    @Override
    public boolean magGereedschapToevoegen()
    {
        return true;
    }

    @Override
    public boolean magGebruikerBeheren()
    {
        return true;
    }
}
