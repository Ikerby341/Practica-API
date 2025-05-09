package sapalomera.view;

public class Vista {
    /**
     * Funció per mostrar els titols
     * @param titol El titol a mostrar
     */
    public static void mostrarTitol(String name) {
        String barras = "";
        for (int i = 0; i < (name.length() + 8); i++) barras += "-";

        Vista.mostrarMissatge(barras);
        Vista.mostrarMissatge("--- " + name + " ---");
        Vista.mostrarMissatge(barras);
    }

    /**
     * Funció per mostrar missatges
     * @param message El missatge a mostrar
     */
    public static void mostrarMissatge(String message) {
        System.out.println(message);
    }

    /**
     * Funció per mostrar el menú de l'usuari
     */
    public static void mostrarMenuPrincipal() {
        Vista.mostrarTitol("INICI");
        Vista.mostrarMissatge("0. Finalitzar");
        Vista.mostrarMissatge("1. Llistar tots els personatges");
        Vista.mostrarMissatge("2. Mostrar contingut EndPoint");
        Vista.mostrarMissatge("3. Modificar personatge segons l'endpoint");
        Vista.mostrarMissatge("4. Còpia total de les dades obtingudes");
        Vista.mostrarMissatge("5. Mostrar contingut JSON");
        Vista.mostrarMissatge("6. Modificar personatge segons el JSON");
        Vista.mostrarMissatge("7. Còpia total de les dades obtingudes del JSON");
    }

    /**
     * Funció per mostrar el submenú d'opcions de Còpies.
     */
    public static void mostrarSubMenuCopia() {
        Vista.mostrarTitol("CÒPIA DE DADES");
        Vista.mostrarMissatge("0. Enrere");
        Vista.mostrarMissatge("1. Còpia parcial");
        Vista.mostrarMissatge("2. Còpia completa");
    }
}
