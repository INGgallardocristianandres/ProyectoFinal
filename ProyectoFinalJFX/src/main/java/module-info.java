module co.edu.uniquindio.poo.proyectofinaljfx {

    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;

    opens co.edu.uniquindio.poo.proyectofinaljfx to javafx.fxml;
    opens co.edu.uniquindio.poo.proyectofinaljfx.model to javafx.fxml;

    exports co.edu.uniquindio.poo.proyectofinaljfx;
    exports co.edu.uniquindio.poo.proyectofinaljfx.model;
}