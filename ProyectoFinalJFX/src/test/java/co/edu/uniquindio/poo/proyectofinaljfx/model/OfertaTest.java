package co.edu.uniquindio.poo.proyectofinaljfx.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.logging.Logger;

class OfertaTest {
    private static final Logger LOG = Logger.getLogger(OfertaTest.class.getName());

    @Test
    void aceptar() {
        LOG.info("Inicio test Comprar Inmueble");
        InmoSmart empresa = new InmoSmart("InmoSmart");
        Comprador c1 = new Comprador("1", "Manuel", "105432", "312342124", "manuelomallama@gmail.com", "1324", empresa);
        Vendedor v1 = new Vendedor("1", "Manuel", "1345423", "131345654", "manuel@gmail.com", "13431", empresa);
        empresa.registrarUsuario(c1);
        empresa.registrarUsuario(v1);
        Inmueble i1 = new Inmueble("212", "Inmueble1", "Armenia", "Calle 12131", TipoInmueble.CASA, TipoOferta.VENTA, 500000, 20, "Casa grande y bonita");
        v1.publicarInmueble(i1);
        empresa.agregarInmuebleAlInventario(i1);
        Oferta oferta1 = new Oferta( "1", 50000, "Hagamos negocio con esta oferta", c1, i1);
        c1.realizarOferta(oferta1);
        i1.registrarOferta(oferta1);
        oferta1.aceptar();
        assertTrue(oferta1.getEstadoOferta().equals(EstadoOferta.ACEPTADA));

        Transaccion t1 = new Transaccion("23425", oferta1.getMontoOfrecido(), oferta1.calcularComisionInmobiliaria(0.03), TipoOferta.VENTA, MetodoPago.EFECTIVO, c1, v1, i1);
        empresa.registrarTransaccion(t1);
        v1.finalizarVenta(i1);
        assertEquals(EstadoInmueble.VENDIDO, i1.getEstado());

        LOG.info("Fin test Comprar Inmueble");

    }
}