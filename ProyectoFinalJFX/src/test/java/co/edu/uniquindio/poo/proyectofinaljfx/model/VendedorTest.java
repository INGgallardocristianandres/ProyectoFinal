package co.edu.uniquindio.poo.proyectofinaljfx.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.logging.Logger;


class VendedorTest {
    private static final Logger LOG = Logger.getLogger(VendedorTest.class.getName());

    @Test
    void publicarInmueble() {
        LOG.info("Inicio test Publicar Propiedades");
        Vendedor v1 = new Vendedor("1", "Manuel", "1345423", "131345654", "manuel@gmail.com", "13431", null);
        ArrayList <Inmueble> publicados = new ArrayList<>();
        Inmueble i1 = new Inmueble("212", "Inmueble1", "Armenia", "Calle 12131", TipoInmueble.CASA, TipoOferta.ARRIENDO, 500000, 20, "Casa grande y bonita");
        Inmueble i2 = new Inmueble("213", "Inmueble2", "Armenia", "Calle 43131", TipoInmueble.APARTAMENTO, TipoOferta.VENTA, 50000000, 15, "Apartamento grande y bonita");
        publicados.add(i1);
        publicados.add(i2);
        v1.publicarInmueble(i1);
        v1.publicarInmueble(i2);
        assertIterableEquals(publicados,v1.getListaInmuebles());

        LOG.info("Fin test Publicar Propiedades");


    }
}