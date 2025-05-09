package sapalomera;

import sapalomera.controller.MenusController;
import sapalomera.view.Vista;

public class Main {
    public static void main(String[] args) {
        Vista.mostrarMenuPrincipal();
        MenusController.scanOpcio(7);
        MenusController.switchPrincipal();
    }
}