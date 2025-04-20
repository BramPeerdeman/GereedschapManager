package com.brampeerdeman.GereedschapsManager.service;

import com.brampeerdeman.GereedschapsManager.model.Gebruiker;
import com.brampeerdeman.GereedschapsManager.repository.GebruikerRepository;
import com.brampeerdeman.GereedschapsManager.security.GebruikersPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyGebruikerDetailService implements UserDetailsService
{
    private final GebruikerRepository gebruikerRepository;

    public MyGebruikerDetailService(GebruikerRepository gebruikerRepository)
    {
        this.gebruikerRepository = gebruikerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String gebruikersnaam) throws UsernameNotFoundException
    {
        Gebruiker gebruiker = gebruikerRepository.findByGebruikersnaam(gebruikersnaam);

        if(gebruiker == null)
        {
            throw new UsernameNotFoundException(gebruikersnaam + " does not exist");
        }

        return new GebruikersPrincipal(gebruiker);
    }
}
