package com.brampeerdeman.GereedschapsManager.model;

import jakarta.persistence.Entity;

@Entity
public class Medewerker extends Gebruiker
{
    @Override
    public String getRol()
    {
        return "MEDEWERKER";
    }

    @Override
    public boolean magGereedschapToevoegen()
    {
        return true;
    }

    @Override
    public boolean magGebruikerBeheren()
    {
        return false;
    }
}
