package com.brampeerdeman.GereedschapsManager;

import com.brampeerdeman.GereedschapsManager.model.Gebruiker;
import com.brampeerdeman.GereedschapsManager.repository.GebruikerRepository;
import com.brampeerdeman.GereedschapsManager.service.GebruikerService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static com.jayway.jsonpath.internal.path.PathCompiler.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class GebruikerControllerTest
{

    private GebruikerRepository gebruikerRepository = mock(GebruikerRepository.class);
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    private GebruikerService gebruikerService = new GebruikerService(gebruikerRepository, bCryptPasswordEncoder);

    @Test
    void testAuthenticateSuccess()
    {
        Gebruiker gebruiker = new Gebruiker()
        {
            @Override
            public String getRol()
            {
                return "";
            }
        };
        gebruiker.setGebruikersnaam("testUser");
        gebruiker.setWachtwoord(bCryptPasswordEncoder.encode("password"));

        when(gebruikerRepository.findByGebruikersnaam("testUser")).thenReturn(gebruiker);

        System.out.println("Running test: Authenticate Success");

        System.out.println("Searching for user 'testUser' in the repository...");

        boolean result = gebruikerService.authenticate("testUser", "password");

        System.out.println("Authentication result: " + result);

        assertTrue(result);
    }

    @Test
    void testAuthenticateFailure_UserNotFound()
    {
        System.out.println("Running test: Authenticate Failure - User Not Found");

        when(gebruikerRepository.findByGebruikersnaam("testUser")).thenReturn(null);

        System.out.println("Searching for user 'testUser' in the repository...");

        try
        {
            gebruikerService.authenticate("testUser", "password");
            fail("Expected UsernameNotFoundException");
        } catch (UsernameNotFoundException ex)
        {
            System.out.println("Caught exception: " + ex.getMessage());
            assertTrue(ex.getMessage().contains("User does not exist in the database"));
        }
    }

    @Test
    void testAuthenticateFailure_WrongPassword()
    {
        Gebruiker gebruiker = new Gebruiker()
        {
            @Override
            public String getRol()
            {
                return "";
            }
        };
        gebruiker.setGebruikersnaam("testUser");
        gebruiker.setWachtwoord(bCryptPasswordEncoder.encode("correctPassword"));

        when(gebruikerRepository.findByGebruikersnaam("testUser")).thenReturn(gebruiker);

        System.out.println("Running test: Authenticate Failure - Wrong Password");

        System.out.println("Searching for user 'testUser' in the repository...");
        System.out.println("User found. Now validating password...");

        try
        {
            gebruikerService.authenticate("testUser", "wrongPassword");
            fail("Expected BadCredentialsException");
        } catch (BadCredentialsException ex)
        {
            System.out.println("Caught exception: " + ex.getMessage());
            assertTrue(ex.getMessage().contains("The password is incorrect"));
        }
    }

    private void logUserInfo(Gebruiker gebruiker)
    {
        if (gebruiker != null)
        {
            System.out.println("User info: Gebruikersnaam = " + gebruiker.getGebruikersnaam() + ", Wachtwoord (encoded) = " + gebruiker.getWachtwoord());
        } else
        {
            System.out.println("User not found in the database.");
        }
    }
}
