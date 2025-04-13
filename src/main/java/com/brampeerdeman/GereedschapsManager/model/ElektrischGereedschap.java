package com.brampeerdeman.GereedschapsManager.model;

import jakarta.persistence.Entity;

@Entity
public class ElektrischGereedschap extends Gereedschap
{
    private int vermogen;

    @Override
    public String gebruiksInstructies()
    {
        return "Draag gehoorbescherming en gebruik een veiligheidsbril.";
    }

    @Override
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
