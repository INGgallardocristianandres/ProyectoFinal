module co.edu.uniquindio.poo.proyectofinaljfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.edu.uniquindio.poo.proyectofinaljfx to javafx.fxml;
    exports co.edu.uniquindio.poo.proyectofinaljfx;
}