module co.edu.uniquindio.poo.proyectofinaljfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.edu.uniquindio.poo.proyectofinaljfx to javafx.fxml;
    opens co.edu.uniquindio.poo.proyectofinaljfx.viewController to javafx.fxml;
    opens co.edu.uniquindio.poo.proyectofinaljfx.Controller to javafx.fxml;
    exports co.edu.uniquindio.poo.proyectofinaljfx;
    exports co.edu.uniquindio.poo.proyectofinaljfx.Controller;
    exports co.edu.uniquindio.poo.proyectofinaljfx.viewController;
}
