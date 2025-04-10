package com.brampeerdeman.GereedschapsManager.security;

import com.brampeerdeman.GereedschapsManager.model.Gebruiker;
import com.brampeerdeman.GereedschapsManager.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService
{
    private final UserRepository userRepository;

    public MyUserDetailService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String gebruikersnaam) throws UsernameNotFoundException
    {
        Gebruiker gebruiker = userRepository.findByGebruikersnaam(gebruikersnaam);

        if(gebruiker == null)
        {
            throw new UsernameNotFoundException(gebruikersnaam + " does not exist");
        }

        return new GebruikersPrincipal(gebruiker);
    }
}
