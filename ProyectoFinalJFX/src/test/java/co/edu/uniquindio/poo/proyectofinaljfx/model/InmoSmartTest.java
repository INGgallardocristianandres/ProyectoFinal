package co.edu.uniquindio.poo.proyectofinaljfx.model;

import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class InmoSmartTest {
    private static final Logger LOG = Logger.getLogger(InmoSmartTest.class.getName());

    @Test
    void registrarUsuario() {
        LOG.info("Inicio test Registrar Usuarios");
        InmoSmart empresa = new InmoSmart("InmoSmart");
        ArrayList<Comprador> registrados = new ArrayList<>();
        Comprador c1 = new Comprador("1", "Manuel", "1085915590", "3185454373", "manuelsantiagomallama@gmail.com", "1234", null);
        Comprador c2 = new Comprador("2", "Cristian", "234567321", "318544532", "cristianandres@gmail.com", "34231", null);
        registrados.add(c1);
        registrados.add(c2);
        empresa.registrarUsuario(c1);
        empresa.registrarUsuario(c2);
        assertIterableEquals(registrados, empresa.getListaUsuarios());
        LOG.info("Fin test Registrar Usuarios");
    }

    @Test
    void registrarTransaccion() {
        LOG.info("Inicio test Compra Inmuebles");


    }

    @Test
    void generarReporteMensual() {
    }
}