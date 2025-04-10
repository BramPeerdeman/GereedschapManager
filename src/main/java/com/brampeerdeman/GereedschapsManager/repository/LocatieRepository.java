package com.brampeerdeman.GereedschapsManager.repository;

import com.brampeerdeman.GereedschapsManager.model.Locatie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocatieRepository extends JpaRepository<Locatie, Long>
{
    Optional<Locatie> findByNaam(String naam);
}