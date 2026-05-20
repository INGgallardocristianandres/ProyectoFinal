package co.edu.uniquindio.poo.proyectofinaljfx.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.logging.Logger;


class CompradorTest {
    private static final Logger LOG = Logger.getLogger(CompradorTest.class.getName());

    @Test
    void realizarOferta() {
        LOG.info("Inicio test Realizar Oferta");
        Comprador c1 = new Comprador("1", "Manuel", "105432", "312342124", "manuelomallama@gmail.com", "1324", null);
        ArrayList <Oferta> realizadas = new ArrayList<>();
        Oferta oferta1 = new Oferta( "1", 50000, "Hagamos negocio con esta oferta", null, null);
        Oferta oferta2 = new Oferta( "2", 50003432, "Hagamos negocio con esta nueva oferta", null, null);
        realizadas.add(oferta1);
        realizadas.add(oferta2);
        c1.realizarOferta(oferta1);
        c1.realizarOferta(oferta2);
        assertIterableEquals(realizadas, c1.getOfertasRealizadas());


        LOG.info("Inicio test Realizar Oferta");
    }
}