package com.brampeerdeman.GereedschapsManager.controller;

import com.brampeerdeman.GereedschapsManager.model.Gereedschap;
import com.brampeerdeman.GereedschapsManager.repository.GereedschapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
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
}
