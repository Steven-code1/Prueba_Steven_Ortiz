package com.distribuida.servicios;

import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDateTime;

@ApplicationScoped
public class ServicioHolaMundoImpl implements ServicioHolaMundo{

    public String hola(){
        return "Hola " + LocalDateTime.now();
    }
}

