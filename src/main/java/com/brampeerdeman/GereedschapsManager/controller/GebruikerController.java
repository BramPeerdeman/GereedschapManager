package com.brampeerdeman.GereedschapsManager.controller;

import com.brampeerdeman.GereedschapsManager.model.Gebruiker;
import com.brampeerdeman.GereedschapsManager.model.LoginRequest;
import com.brampeerdeman.GereedschapsManager.service.GebruikerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

import java.util.List;

@RestController
public class GebruikerController
{
    private final GebruikerService gebruikerService;

    @Autowired
    public GebruikerController(GebruikerService gebruikerService)
    {
        this.gebruikerService = gebruikerService;
    }

    @GetMapping("/gebruikers")
    public List<Gebruiker> getGebruikers()
    {
        return gebruikerService.getGebruikers();
    }

    @GetMapping("/gebruiker/{id}")
    public Gebruiker getGebruiker(@PathVariable("id") Integer id)
    {
        return gebruikerService.getGebruiker(id);
    }

    @PutMapping("/gebruiker/{id}")
    public Gebruiker updateGebruiker(@RequestBody Gebruiker gebruiker, @PathVariable("id") Long id)
    {
        gebruiker.setId(id);
        return gebruikerService.updateGebruiker(gebruiker);
    }

    @PostMapping("/register")
    public ResponseEntity<Gebruiker> newGebruiker(@RequestBody Gebruiker gebruiker)
    {
        Gebruiker newGebruiker = gebruikerService.addGebruiker(gebruiker);
        return ResponseEntity.status(HttpStatus.CREATED).body(newGebruiker);
    }

    @DeleteMapping("/gebruiker/{id}")
    public void deleteGebruiker(@PathVariable("id") Integer id)
    {
        gebruikerService.deleteGebruiker(id);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest, HttpSession session)
    {
        try {
            boolean isAuthenticated = gebruikerService.authenticate(loginRequest.getGebruikersnaam(), loginRequest.getWachtwoord());

            if (isAuthenticated) {
                session.setAttribute("gebruikersnaam", loginRequest.getGebruikersnaam());
                return ResponseEntity.ok("Login was successful!");
            } else
            {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
            }
        } catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unknown error occurred");
        }
    }


}
