package sapalomera.controller;

import com.google.gson.*;
import sapalomera.model.dao.Brawlers;
import sapalomera.model.dao.Gadgets;
import sapalomera.model.dao.Rarity;
import sapalomera.model.dao.StarPowers;
import sapalomera.view.Vista;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class EndPointController {
    public static Scanner scan = new Scanner(System.in);
    static Gson gson = new Gson();
    static String apikey = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiIsImtpZCI6IjI4YTMxOGY3LTAwMDAtYTFlYi03ZmExLTJjNzQzM2M2Y2NhNSJ9.eyJpc3MiOiJzdXBlcmNlbGwiLCJhdWQiOiJzdXBlcmNlbGw6Z2FtZWFwaSIsImp0aSI6ImVmNmNmOTVmLTQ4MWItNDM1YS05Yzk2LTY3ODU5MTZkOWU4MyIsImlhdCI6MTc0NzM4MTM5MCwic3ViIjoiZGV2ZWxvcGVyL2I1NGZlNWMzLTFiN2MtYTJkOC02ZmFkLWNlZTJhOTkyYTlkNiIsInNjb3BlcyI6WyJicmF3bHN0YXJzIl0sImxpbWl0cyI6W3sidGllciI6ImRldmVsb3Blci9zaWx2ZXIiLCJ0eXBlIjoidGhyb3R0bGluZyJ9LHsiY2lkcnMiOlsiODUuNTAuMTYwLjQzIl0sInR5cGUiOiJjbGllbnQifV19.FD8yVxklHugve06aAIQW5jOmmJ2LCLv16LeM26BMLmZDI_a5qMh_5Iqg4w0s5C8MZX4jlobCWd72ZYLn6AmR4A";
    public static String llegirGsonBrawlify() {
        try {
            URL url = new URL("https://api.brawlify.com/v1/brawlers");
            URLConnection conexio = url.openConnection();

            BufferedReader in = new BufferedReader(new InputStreamReader(conexio.getInputStream()));
            String resultat = "";
            String linea;

            while ((linea = in.readLine()) != null) resultat += linea;
            in.close();

            return resultat;

        } catch (Exception e) {
            System.out.println("Error al conectar con la API: " + e.getMessage());
        }
        return "Ha sortit malament algún proces";

    }

    public static String llegirGsonBrawlStars() {
        try {
            URL url = new URL("https://api.brawlstars.com/v1/brawlers");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "Bearer " + apikey);

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String resultat = "";
            String linea;

            while ((linea = in.readLine()) != null) resultat += linea;
            in.close();

            return resultat;

        } catch (Exception e) {
            System.out.println("Error al conectar con la API: " + e.getMessage());
        }
        return "No ha sortit bé";
    }

    public static String llistarBrawlerID(int ID) {
        try {
            URL url = new URL("https://api.brawlstars.com/v1/brawlers/" + ID);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "Bearer " + apikey);

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String resultat = "";
            String linea;

            while ((linea = in.readLine()) != null) resultat += linea;
            in.close();

            return resultat;

        } catch (Exception e) {
            System.out.println("Error al conectar con la API: " + e.getMessage());
        }

        return "Alguna cosa ha sortit malament";
    }

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

    public static ArrayList<Brawlers> convertirObjectesL(String json) {
        Gson gson = new Gson();
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        JsonArray brawlersArray = jsonObject.getAsJsonArray("list");
        ArrayList<Brawlers> brawlersList = new ArrayList<>();

        for (JsonElement element : brawlersArray) {
            try {
                Brawlers brawler = gson.fromJson(element, Brawlers.class);
                brawler.extractIdsFromLists();
                Vista.mostrarMissatge("ID: " + brawler.getId() +
                        " | Nom: " + brawler.getNom() +
                        " | RarityID: " + brawler.getRarityID() +
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

    public static void convertirObjecte(String json) {
        Gson gson = new Gson();
        Brawlers brawler = gson.fromJson(json, Brawlers.class); // Deserializa el JSON

        brawler.extractIdsFromLists();

        Vista.mostrarMissatge("ID: " + brawler.getId() +
                " | Nom: " + brawler.getNom() +
                " | Gadget1ID: " + brawler.getGadgetID() +
                " | Gadget2ID: " + brawler.getGadget2ID() +
                " | StarPower1ID: " + brawler.getStarpower1ID() +
                " | StarPower2ID: " + brawler.getStarpower2ID());

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

    public static ArrayList<Rarity> obtenirRarity(String json) {
        Gson gson = new Gson();
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        JsonArray brawlersArray = jsonObject.getAsJsonArray("list");

        // Utilizem un HashSet per evitar valors duplicats
        HashSet<Rarity> rarezasSet = new HashSet<>();

        for (JsonElement element : brawlersArray) {
            JsonObject brawlerObject = element.getAsJsonObject();
            Rarity rarity = gson.fromJson(brawlerObject.getAsJsonObject("rarity"), Rarity.class);

            rarezasSet.add(rarity);
        }

        return new ArrayList<>(rarezasSet);
    }

    public static ArrayList<Gadgets> obtenirGadgets(String json) {
        Gson gson = new Gson();
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        JsonArray brawlersArray = jsonObject.getAsJsonArray("list");
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

    public static ArrayList<StarPowers> obtenirStarPowers(String json) {
        Gson gson = new Gson();
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        JsonArray brawlersArray = jsonObject.getAsJsonArray("list");
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
