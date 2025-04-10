package com.brampeerdeman.GereedschapsManager.controller;

import com.brampeerdeman.GereedschapsManager.model.Locatie;
import com.brampeerdeman.GereedschapsManager.repository.LocatieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locaties")
@CrossOrigin("http://localhost:3000")
public class LocatieController
{
    @Autowired
    private LocatieRepository locatieRepository;

    @GetMapping
    public List<Locatie> getAllLocaties()
    {
        return locatieRepository.findAll();
    }

    @PostMapping
    public Locatie createLocatie(@RequestBody Locatie locatie)
    {
        return locatieRepository.save(locatie);
    }
}
