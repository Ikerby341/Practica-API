package sapalomera.controller;

import com.google.gson.*;
import sapalomera.model.Brawlers;
import sapalomera.model.Gadgets;
import sapalomera.model.StarPowers;
import sapalomera.view.Vista;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class JsonController {
    public static void llistarBrawlerIDJ(String json, int ID) {
        Gson gson = new Gson();
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        JsonArray brawlersArray = jsonObject.getAsJsonArray("items");

        for (JsonElement element : brawlersArray) {
            try {
                Brawlers brawler = gson.fromJson(element, Brawlers.class);
                brawler.extractIdsFromLists();

                if (brawler.getId() == ID) {
                    Vista.mostrarMissatge("ID: " + brawler.getId() +
                            " | Nom: " + brawler.getNom() +
                            " | Gadget1ID: " + brawler.getGadgetID() +
                            " | Gadget2ID: " + brawler.getGadget2ID() +
                            " | StarPower1ID: " + brawler.getStarpower1ID() +
                            " | StarPower2ID: " + brawler.getStarpower2ID());
                }

            } catch (JsonSyntaxException e) {
                Vista.mostrarMissatge("Error al deserialitzar el brawler: " + e.getMessage());
            } catch (NullPointerException e) {
                Vista.mostrarMissatge("Error: un dels camps es null.");
            }
        }
    }

    public static ArrayList<Brawlers> convertirObjectesI(String json) {
        Gson gson = new Gson();
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        JsonArray brawlersArray = jsonObject.getAsJsonArray("items");
        ArrayList<Brawlers> brawlersList = new ArrayList<>();

        for (JsonElement element : brawlersArray) {
            try {
                Brawlers brawler = gson.fromJson(element, Brawlers.class);
                brawler.extractIdsFromLists();
                Vista.mostrarMissatge("ID: " + brawler.getId() +
                        " | Nom: " + brawler.getNom() +
                        " | Gadget1ID: " + brawler.getGadgetID() +
                        " | Gadget2ID: " + brawler.getGadget2ID() +
                        " | StarPower1ID: " + brawler.getStarpower1ID() +
                        " | StarPower2ID: " + brawler.getStarpower2ID());
                brawlersList.add(brawler);
            } catch (JsonSyntaxException e) {
                Vista.mostrarMissatge("Error al deserialitzar el brawler: " + e.getMessage());
            } catch (NullPointerException e) {
                Vista.mostrarMissatge("Error: un dels camps es null.");
            }
        }
        return brawlersList;
    }

    public static void crearJson(String json) throws FileNotFoundException {
        File file = new File("brawlers.json");
        PrintStream writer = new PrintStream(file);
        boolean isWriting = false;

        try {
            file.createNewFile();
            writer.println(json);
            isWriting = true;
            Vista.mostrarMissatge("Fitxer creat correctament: " + file.getAbsolutePath());
        } catch (IOException e) {
            Vista.mostrarMissatge("Error al crear el fitxer: " + e.getMessage());
        }

        if (isWriting) {
            isWriting = false;
            writer.close();
        }

    }

    public static String llegirJson() throws FileNotFoundException {
        File file = new File("brawlers.json");
        Scanner reader = new Scanner(file);
        String devolucio = "";

        while (reader.hasNextLine()) {
            devolucio = reader.nextLine();
        }

        reader.close();
        return devolucio;

    }

    public static ArrayList<Gadgets> obtenirGadgetsI(String json) {
        Gson gson = new Gson();
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        JsonArray brawlersArray = jsonObject.getAsJsonArray("items");
        ArrayList<Gadgets> gadgets = new ArrayList<>();

        for (JsonElement element : brawlersArray) {
            JsonArray gadgetsArray = element.getAsJsonObject().getAsJsonArray("gadgets");

            for (JsonElement gadgetElement : gadgetsArray) {
                Gadgets gadget = gson.fromJson(gadgetElement, Gadgets.class);

                // Evitar duplicats
                if (!gadgets.contains(gadget)) {
                    gadgets.add(gadget);
                }
            }
        }

        return gadgets;
    }

    public static ArrayList<StarPowers> obtenirStarPowersI(String json) {
        Gson gson = new Gson();
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        JsonArray brawlersArray = jsonObject.getAsJsonArray("items");
        ArrayList<StarPowers> starPowers = new ArrayList<>();

        for (JsonElement element : brawlersArray) {
            JsonArray starPowersArray = element.getAsJsonObject().getAsJsonArray("starPowers");

            for (JsonElement starPowerElement : starPowersArray) {
                StarPowers starPower = gson.fromJson(starPowerElement, StarPowers.class);

                // Evitar duplicats
                if (!starPowers.contains(starPower)) {
                    starPowers.add(starPower);
                }
            }
        }

        return starPowers;
    }
}
