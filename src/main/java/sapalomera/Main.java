package sapalomera;

import sapalomera.controller.EndPointController;
import sapalomera.controller.JsonController;
import sapalomera.controller.MenusController;
import sapalomera.model.dao.DBConnection;
import sapalomera.view.Vista;

import java.io.FileNotFoundException;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Connection conexio = DBConnection.connectar();
        if (conexio == null) {
            Vista.mostrarMissatge("[Error] La base de dades no existeix o no s'ha trobat!");
            return;
        }
        try {
            JsonController.crearJson(EndPointController.llegirGsonBrawlStars());
            Vista.mostrarMenuPrincipal();
            MenusController.scanOpcio(8);
            MenusController.switchPrincipal(conexio);
        } catch (FileNotFoundException e) {
            Vista.mostrarMissatge("[Error] No s'ha pogut llegir el fitxer JSON.");
        } catch (Exception e) {
            Vista.mostrarMissatge("[Error] S'ha produït un error inesperat: " + e.getMessage());
        } finally {
            DBConnection.desconectar(conexio);
        }
    }
}
