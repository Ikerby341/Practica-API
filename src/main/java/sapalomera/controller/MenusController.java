package sapalomera.controller;

import sapalomera.view.Vista;

import java.util.Scanner;

/**
 * Classe per separar els switch de la classe principal
 */
public class MenusController {
    public static Scanner scan = new Scanner(System.in);
    static int opcio;

    public static void switchPrincipal(){
        switch (opcio) {
            case 0:
                break;
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
            case 5:

                break;
            case 6:

                break;
            case 7:
                Vista.mostrarSubMenuCopia();
                scanOpcio(2);
                switchCopia();
                break;
        }
    }

    public static void switchCopia(){
        switch (opcio) {
            case 0:
                Vista.mostrarMenuPrincipal();
                scanOpcio(7);
                switchPrincipal();
                break;
            case 1:
                // Accions per a valor2
                break;
            case 2:
                // Accions per a valor3
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
}
