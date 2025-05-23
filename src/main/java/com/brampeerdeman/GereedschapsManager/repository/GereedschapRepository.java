package com.brampeerdeman.GereedschapsManager.repository;

import com.brampeerdeman.GereedschapsManager.model.Gereedschap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GereedschapRepository extends JpaRepository<Gereedschap, Long>
{
    List<Gereedschap> findAll();
}
