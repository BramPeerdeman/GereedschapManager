package com.brampeerdeman.GereedschapsManager.exception;

public class GereedschapNotFoundException extends RuntimeException
{
    public GereedschapNotFoundException(Long id)
    {
        super("Gereedschap with id " + id + " not found");
    }
}
