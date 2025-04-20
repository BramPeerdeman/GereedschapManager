package com.brampeerdeman.GereedschapsManager.controller;

import com.brampeerdeman.GereedschapsManager.exception.GereedschapNotFoundException;
import com.brampeerdeman.GereedschapsManager.model.ElektrischGereedschap;
import com.brampeerdeman.GereedschapsManager.model.Gereedschap;
import com.brampeerdeman.GereedschapsManager.model.HandGereedschap;
import com.brampeerdeman.GereedschapsManager.repository.GereedschapRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class GereedschapController
{
    @Autowired
    private GereedschapRepository gereedschapRepository;

    @PostMapping("/gereedschap")
    Gereedschap newGereedschap(@RequestBody Gereedschap newGereedschap)
    {
        if ("elektrisch".equals(newGereedschap.getType())) {
            newGereedschap.setGebruiksInstructies("Draag gehoorbescherming en gebruik een veiligheidsbril.");
            newGereedschap.setVereistVeiligheidscheck(true);
        } else if ("hand".equals(newGereedschap.getType())) {
            newGereedschap.setGebruiksInstructies("Gebruik altijd het gereedschap volgens de handleiding.");
            newGereedschap.setVereistVeiligheidscheck(false);
        }

        return gereedschapRepository.save(newGereedschap);
    }

    @GetMapping("/gereedschappen")
    List<Gereedschap> getAllGereedschappen()
    {
        return gereedschapRepository.findAll();
    }

    @GetMapping("/gereedschap/{id}")
    Gereedschap getGereedschapById(@PathVariable Long id)
    {
        return gereedschapRepository.findById(id)
                .orElseThrow(() -> new GereedschapNotFoundException(id));
    }

    @PutMapping("/gereedschap/{id}")
    Gereedschap updateGereedschap(@RequestBody Gereedschap newGereedschap, @PathVariable Long id)
    {
        return gereedschapRepository.findById(id)
                .map(gereedschap -> {
                    gereedschap.setName(newGereedschap.getName());
                    gereedschap.setLocation(newGereedschap.getLocation());
                    gereedschap.setGebruiker(newGereedschap.getGebruiker());
                    return gereedschapRepository.save(gereedschap);
                }).orElseThrow(() -> new GereedschapNotFoundException(id));
    }

    @DeleteMapping("/gereedschap/{id}")
    String deleteGereedschap(@PathVariable Long id)
    {
        if(!gereedschapRepository.existsById(id))
        {
            throw new GereedschapNotFoundException(id);
        }
        gereedschapRepository.deleteById(id);

        return "Gereedschap with id " + id + " was deleted successfully";
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("jsonFile") MultipartFile file) throws IOException
    {
        String jsonContent = new String(file.getBytes());

        ObjectMapper objectMapper = new ObjectMapper();
        List<Gereedschap> gereedschappen = objectMapper.readValue(jsonContent, objectMapper.getTypeFactory().constructCollectionType(List.class, Gereedschap.class));

        gereedschapRepository.saveAll(gereedschappen);

        return "File uploaded and tools added successfully!";
    }

    @PutMapping("/gereedschap/{id}/loan-status")
    public Gereedschap changeLoanStatus(@PathVariable Long id, @RequestParam boolean loaned)
    {
        return gereedschapRepository.findById(id).map(gereedschap -> {
            gereedschap.setLoaned(loaned);
            return gereedschapRepository.save(gereedschap);
        }).orElseThrow(() -> new GereedschapNotFoundException(id));
    }
}
