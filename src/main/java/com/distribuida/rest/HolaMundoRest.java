package com.distribuida.rest;
import com.distribuida.servicios.ServicioHolaMundo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@ApplicationScoped
@Path("/")
public class HolaMundoRest {
    @Inject
    private ServicioHolaMundo servicio;

    @GET
    @Path("/hola")
    public String hola(){
        return servicio.hola();
    }
}