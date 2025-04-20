package com.brampeerdeman.GereedschapsManager.repository;

import com.brampeerdeman.GereedschapsManager.model.Gebruiker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GebruikerRepository extends JpaRepository<Gebruiker, Integer>
{
    Gebruiker findByGebruikersnaam(String gebruiker);
}
