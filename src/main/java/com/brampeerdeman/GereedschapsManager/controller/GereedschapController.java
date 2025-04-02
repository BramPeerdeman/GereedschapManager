package com.brampeerdeman.GereedschapsManager.controller;

import com.brampeerdeman.GereedschapsManager.exception.GereedschapNotFoundException;
import com.brampeerdeman.GereedschapsManager.model.Gereedschap;
import com.brampeerdeman.GereedschapsManager.repository.GereedschapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
