package com.brampeerdeman.GereedschapsManager.service;

import com.brampeerdeman.GereedschapsManager.model.Gebruiker;
import com.brampeerdeman.GereedschapsManager.repository.UserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GebruikerService
{
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;

    public GebruikerService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder)
    {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public List<Gebruiker> getGebruikers()
    {
        return userRepository.findAll();
    }

    public Gebruiker getGebruiker(Integer id)
    {
        return userRepository.findById(id).orElse(null);
    }

    public Gebruiker addGebruiker(Gebruiker gebruiker)
    {
        gebruiker.setWachtwoord(bCryptPasswordEncoder.encode(gebruiker.getWachtwoord()));
        return userRepository.save(gebruiker);
    }

    public Gebruiker updateGebruiker(Gebruiker gebruiker)
    {
        gebruiker.setWachtwoord(bCryptPasswordEncoder.encode(gebruiker.getWachtwoord()));
        return userRepository.save(gebruiker);
    }

    public void deleteGebruiker(Integer id)
    {
        userRepository.deleteById(id);
    }

    public boolean authenticate(String gebruikersnaam, String wachtwoord)
    {
        Gebruiker gebruiker = userRepository.findByGebruikersnaam(gebruikersnaam);
        if (gebruiker == null)
        {
            throw new UsernameNotFoundException("User does not exist in the database");
        }

        if (!bCryptPasswordEncoder.matches(wachtwoord, gebruiker.getWachtwoord()))
        {
            throw new BadCredentialsException("The password is incorrect");
        }

        return true;
    }
}
