package sapalomera.controller;

import sapalomera.Main;
import sapalomera.model.dao.DBConnection;
import sapalomera.model.dao.SQLite.SQLiteBrawlersDAO;
import sapalomera.view.Vista;
import java.util.Scanner;
import java.sql.Connection;

/**
 * Classe per separar els switch de la classe principal
 */
public class MenusController {
    public static Scanner scan = new Scanner(System.in);
    static int opcio;

    public static void switchPrincipal(Connection conexio){
        switch (opcio) {
            case 0:
                break;
            case 1:
                try {
                    Vista.mostrarMissatge(SQLiteBrawlersDAO.llistarTot(conexio));
                } catch (Exception e){
                    Vista.mostrarMissatge("Error al llistar els brawlers: " + e.getMessage());
                }
                Vista.mostrarMissatge("Pulsa enter per continuar...");
                scan.nextLine();
                toranaramostrarmenu();
                break;
            case 2:
                try {
                    EndPointController.convertirObjectesL(EndPointController.llegirGsonBrawlify());
                } catch (Exception e){
                    Vista.mostrarMissatge("Error al llistar els brawlers: " + e.getMessage());
                }
                Vista.mostrarMissatge("Pulsa enter per continuar...");
                scan.nextLine();
                toranaramostrarmenu();
                break;
            case 3:
                try {
                    Vista.mostrarMissatge("Digues la ID del Brawler");
                    int ID = scan.nextInt();
                    scan.nextLine();
                    EndPointController.convertirObjecte(EndPointController.llistarBrawlerID(ID));
                    Vista.mostrarMissatge("Vols modificar aquestes dades? (s o n)");
                    String sn = scan.nextLine();
                    if (sn.equals("s")){

                        toranaramostrarmenu();
                    }
                    else {
                        toranaramostrarmenu();
                        break;
                    }

                } catch (Exception e){
                    Vista.mostrarMissatge("Error al llistar els brawlers: " + e.getMessage());
                }
                Vista.mostrarMissatge("Pulsa enter per continuar...");
                scan.nextLine();
                toranaramostrarmenu();
                break;
            case 4:
                toranaramostrarmenu();
                break;
            case 5:
                try {
                    EndPointController.convertirObjectesI(EndPointController.llegirGsonBrawlStars());
                } catch (Exception e){
                    Vista.mostrarMissatge("Error al llistar els brawlers: " + e.getMessage());
                }
                Vista.mostrarMissatge("Pulsa enter per continuar...");
                scan.nextLine();
                toranaramostrarmenu();
                break;
            case 6:
                toranaramostrarmenu();
                break;
            case 7:
                Vista.mostrarSubMenuCopia();
                scanOpcio(2);
                toranaramostrarmenu();
                break;
            case 8:
                try {
                    EndPointController.convertirObjectesL(EndPointController.llegirGsonBrawlify());
                } catch (Exception e){
                    Vista.mostrarMissatge("Error al llistar els brawlers: " + e.getMessage());
                }
                Vista.mostrarMissatge("Pulsa enter per continuar...");
                scan.nextLine();
                toranaramostrarmenu();
                break;
        }
    }

    public static void scanOpcio(int op) {
        opcio = scan.nextInt();
        scan.nextLine();
        comprovarOpcio(op);
    }

    public static void comprovarOpcio(int op) {
        while (opcio < 0 || opcio > op) {
            Vista.mostrarMissatge("Opció incorrecta, torna a escollir!");
            Vista.mostrarMissatge("Selecciona una opció (0-" + op + "): ");
            opcio = scan.nextInt();
            scan.nextLine();
        }
    }

    public static void toranaramostrarmenu(){
        Connection conexio = DBConnection.getConnexio();
        Vista.mostrarMenuPrincipal();
        MenusController.scanOpcio(8);
        MenusController.switchPrincipal(conexio);
    }
}
