package sapalomera.controller;

import sapalomera.model.Brawlers;
import sapalomera.model.Gadgets;
import sapalomera.model.Rarity;
import sapalomera.model.StarPowers;
import sapalomera.model.dao.*;
import sapalomera.model.dao.SQLite.SQLiteBrawlersDAO;
import sapalomera.model.dao.SQLite.SQLiteGadgetsDAO;
import sapalomera.model.dao.SQLite.SQLiteRarityDAO;
import sapalomera.model.dao.SQLite.SQLiteStarPowersDAO;
import sapalomera.view.Vista;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
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
                tornarAMostrarMenu();
                break;
            case 2:
                try {
                    EndPointController.convertirObjectesL(EndPointController.llegirGsonBrawlify());
                } catch (Exception e){
                    Vista.mostrarMissatge("Error al llistar els brawlers: " + e.getMessage());
                }
                Vista.mostrarMissatge("Pulsa enter per continuar...");
                scan.nextLine();
                tornarAMostrarMenu();
                break;
            case 3:
                try {
                    Vista.mostrarMissatge("Digues la ID del Brawler");
                    int ID = scan.nextInt();
                    scan.nextLine();
                    int existeix = SQLiteBrawlersDAO.existeix(conexio, ID);
                    if (existeix == 0) {
                        Vista.mostrarMissatge("No existeix el brawler amb ID a la BBDD: " + ID);
                        Vista.mostrarMissatge("Pulsa enter per continuar...");
                        scan.nextLine();
                        tornarAMostrarMenu();
                        break;
                    }
                    EndPointController.convertirObjecte(EndPointController.llistarBrawlerID(ID));
                    Vista.mostrarMissatge("Vols modificar aquestes dades? (s o n)");
                    String sn = scan.nextLine();
                    if (sn.trim().equalsIgnoreCase("s")){
                        Vista.mostrarMissatge("Que vols modificar?");
                        String quequiero = scan.nextLine();
                        Vista.mostrarMissatge("Que vols posar?");
                        String comoquiero = scan.nextLine();
                        SQLiteBrawlersDAO.actualitzar(conexio, ID, quequiero, comoquiero);

                    }

                } catch (Exception e){
                    Vista.mostrarMissatge("Error al llistar els brawlers: " + e.getMessage());
                }
                Vista.mostrarMissatge("Pulsa enter per continuar...");
                scan.nextLine();
                tornarAMostrarMenu();
                break;
            case 4:
                Vista.mostrarSubMenuCopia();
                scanOpcio(2);
                switchCopia();
                tornarAMostrarMenu();
                break;
            case 5:
                try {
                    EndPointController.convertirObjectesI(EndPointController.llegirJson());
                } catch (Exception e){
                    Vista.mostrarMissatge("Error al llistar els brawlers: " + e.getMessage());
                }
                Vista.mostrarMissatge("Pulsa enter per continuar...");
                scan.nextLine();
                tornarAMostrarMenu();
                break;
            case 6:
                try {
                    Vista.mostrarMissatge("Digues la ID del Brawler");
                    int ID = scan.nextInt();
                    scan.nextLine();
                    int existeix = SQLiteBrawlersDAO.existeix(conexio, ID);
                    if (existeix == 0) {
                        Vista.mostrarMissatge("No existeix el brawler amb ID a la BBDD: " + ID);
                        Vista.mostrarMissatge("Pulsa enter per continuar...");
                        scan.nextLine();
                        tornarAMostrarMenu();
                        break;
                    }
                    EndPointController.llistarBrawlerIDJ(EndPointController.llegirJson(),ID);
                    Vista.mostrarMissatge("Vols modificar aquestes dades? (s o n)");
                    String sn = scan.nextLine();
                    if (sn.trim().equalsIgnoreCase("s")){
                        Vista.mostrarMissatge("Que vols modificar?");
                        String quequiero = scan.nextLine();
                        Vista.mostrarMissatge("Que vols posar?");
                        String comoquiero = scan.nextLine();
                        SQLiteBrawlersDAO.actualitzar(conexio, ID, quequiero, comoquiero);

                    }

                } catch (Exception e){
                    Vista.mostrarMissatge("Error al llistar els brawlers: " + e.getMessage());
                }
                Vista.mostrarMissatge("Pulsa enter per continuar...");
                scan.nextLine();
                tornarAMostrarMenu();
                break;
            case 7:
                Vista.mostrarSubMenuCopia();
                scanOpcio(2);
                switchCopiaJson();
                tornarAMostrarMenu();
                break;
            case 8:
                Vista.mostrarMissatge(EndPointController.llegirGsonBrawlStars());
                try {
                    EndPointController.crearJson(EndPointController.llegirGsonBrawlStars());
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                Vista.mostrarMissatge("Pulsa enter per continuar...");
                scan.nextLine();
                tornarAMostrarMenu();
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

    public static void tornarAMostrarMenu(){
        Connection conexio = DBConnection.getConnexio();
        Vista.mostrarMenuPrincipal();
        MenusController.scanOpcio(8);
        MenusController.switchPrincipal(conexio);
    }

    public static void switchCopia(){
        Connection conexio = DBConnection.getConnexio();
        switch (opcio) {
            case 0:
                tornarAMostrarMenu();
                break;
            case 1:
                try {
                    ArrayList<Brawlers> brawlersArrayList = new ArrayList<>(Objects.requireNonNull(EndPointController.convertirObjectesL(EndPointController.llegirGsonBrawlify())));
                    SQLiteBrawlersDAO.crearParcial(conexio, brawlersArrayList);
                    ArrayList<Rarity> rarityArrayList = new ArrayList<>(Objects.requireNonNull(EndPointController.obtenirRarity(EndPointController.llegirGsonBrawlify())));
                    SQLiteRarityDAO.crearParcial(conexio, rarityArrayList);
                    ArrayList<Gadgets> gadgetsArrayList = new ArrayList<>(Objects.requireNonNull(EndPointController.obtenirGadgets(EndPointController.llegirGsonBrawlify())));
                    SQLiteGadgetsDAO.crearParcial(conexio, gadgetsArrayList);
                    ArrayList<StarPowers> starPowersArrayList = new ArrayList<>(Objects.requireNonNull(EndPointController.obtenirStarPowers(EndPointController.llegirGsonBrawlify())));
                    SQLiteStarPowersDAO.crearParcial(conexio, starPowersArrayList);
                    Vista.mostrarMissatge("Pulsa enter per continuar...");
                    scan.nextLine();
                } catch (Exception e){
                    Vista.mostrarMissatge("Error al copiar les taules: " + e.getMessage());
                }
                tornarAMostrarMenu();
                break;
            case 2:
                try {
                    ArrayList<Brawlers> brawlersArrayList = new ArrayList<>(Objects.requireNonNull(EndPointController.convertirObjectesL(EndPointController.llegirGsonBrawlify())));
                    SQLiteBrawlersDAO.crearTotal(conexio, brawlersArrayList);
                    ArrayList<Rarity> rarityArrayList = new ArrayList<>(Objects.requireNonNull(EndPointController.obtenirRarity(EndPointController.llegirGsonBrawlify())));
                    SQLiteRarityDAO.crearTotal(conexio, rarityArrayList);
                    ArrayList<Gadgets> gadgetsArrayList = new ArrayList<>(Objects.requireNonNull(EndPointController.obtenirGadgets(EndPointController.llegirGsonBrawlify())));
                    SQLiteGadgetsDAO.crearTotal(conexio, gadgetsArrayList);
                    ArrayList<StarPowers> starPowersArrayList = new ArrayList<>(Objects.requireNonNull(EndPointController.obtenirStarPowers(EndPointController.llegirGsonBrawlify())));
                    SQLiteStarPowersDAO.crearTotal(conexio, starPowersArrayList);
                    Vista.mostrarMissatge("Pulsa enter per continuar...");
                    scan.nextLine();
                } catch (Exception e){
                    Vista.mostrarMissatge("Error al copiar les taules: " + e.getMessage());
                }
                tornarAMostrarMenu();
                break;
        }
    }

    public static void switchCopiaJson(){
        Connection conexio = DBConnection.getConnexio();
        switch (opcio) {
            case 0:
                tornarAMostrarMenu();
                break;
            case 1:
                try {
                    ArrayList<Brawlers> brawlersArrayList = new ArrayList<>(Objects.requireNonNull(EndPointController.convertirObjectesI(EndPointController.llegirJson())));
                    SQLiteBrawlersDAO.crearParcialJson(conexio, brawlersArrayList);
                    ArrayList<Gadgets> gadgetsArrayList = new ArrayList<>(Objects.requireNonNull(EndPointController.obtenirGadgetsI(EndPointController.llegirJson())));
                    SQLiteGadgetsDAO.crearParcial(conexio, gadgetsArrayList);
                    ArrayList<StarPowers> starPowersArrayList = new ArrayList<>(Objects.requireNonNull(EndPointController.obtenirStarPowersI(EndPointController.llegirJson())));
                    SQLiteStarPowersDAO.crearParcial(conexio, starPowersArrayList);
                    Vista.mostrarMissatge("Pulsa enter per continuar...");
                    scan.nextLine();
                } catch (Exception e){
                    Vista.mostrarMissatge("Error al copiar les taules: " + e.getMessage());
                }
                tornarAMostrarMenu();
                break;
            case 2:
                try {
                    ArrayList<Brawlers> brawlersArrayList = new ArrayList<>(Objects.requireNonNull(EndPointController.convertirObjectesI(EndPointController.llegirJson())));
                    SQLiteBrawlersDAO.crearTotalJson(conexio, brawlersArrayList);
                    ArrayList<Gadgets> gadgetsArrayList = new ArrayList<>(Objects.requireNonNull(EndPointController.obtenirGadgetsI(EndPointController.llegirJson())));
                    SQLiteGadgetsDAO.crearTotal(conexio, gadgetsArrayList);
                    ArrayList<StarPowers> starPowersArrayList = new ArrayList<>(Objects.requireNonNull(EndPointController.obtenirStarPowersI(EndPointController.llegirJson())));
                    SQLiteStarPowersDAO.crearTotal(conexio, starPowersArrayList);
                    Vista.mostrarMissatge("Pulsa enter per continuar...");
                    scan.nextLine();
                } catch (Exception e){
                    Vista.mostrarMissatge("Error al copiar les taules: " + e.getMessage());
                }
                tornarAMostrarMenu();
                break;
        }
    }
}
