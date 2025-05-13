package sapalomera;

import sapalomera.controller.EndPointController;
import sapalomera.controller.MenusController;
import sapalomera.model.dao.DBConnection;
import sapalomera.view.Vista;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Connection conexio = DBConnection.connectar();
        if (conexio == null) {
            Vista.mostrarMissatge("[Error] La base de dades no existeix o no s'ha trobat!");
            return;
        }

        Vista.mostrarMenuPrincipal();
        MenusController.scanOpcio(8);
        MenusController.switchPrincipal(conexio);

    }
}
