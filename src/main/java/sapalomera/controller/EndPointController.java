package sapalomera.controller;

import com.google.gson.*;
import sapalomera.model.dao.Brawlers;
import sapalomera.view.Vista;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class EndPointController {
    public static Scanner scan = new Scanner(System.in);
    static Gson gson = new Gson();

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
        String apikey = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiIsImtpZCI6IjI4YTMxOGY3LTAwMDAtYTFlYi03ZmExLTJjNzQzM2M2Y2NhNSJ9.eyJpc3MiOiJzdXBlcmNlbGwiLCJhdWQiOiJzdXBlcmNlbGw6Z2FtZWFwaSIsImp0aSI6ImVjM2UwYmU2LTYwNWItNDlmMC05YTZkLWEyYmRiOGUwY2RmNSIsImlhdCI6MTc0Njc4MjA0OCwic3ViIjoiZGV2ZWxvcGVyL2E2YjA2OTRlLTJkZjQtZmJmYy1jYjFjLTc5ZDFlZTZlNGMxZiIsInNjb3BlcyI6WyJicmF3bHN0YXJzIl0sImxpbWl0cyI6W3sidGllciI6ImRldmVsb3Blci9zaWx2ZXIiLCJ0eXBlIjoidGhyb3R0bGluZyJ9LHsiY2lkcnMiOlsiODUuMTkyLjczLjQzIl0sInR5cGUiOiJjbGllbnQifV19.LraSBBqQRD8GDsb9QO9YRN2XL0Z-lejeReCANhLXWNca69zULDcDOyaCaxespDAZyF3ExJSzMuhIDIjgZ3_mcA";
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
        String apikey = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiIsImtpZCI6IjI4YTMxOGY3LTAwMDAtYTFlYi03ZmExLTJjNzQzM2M2Y2NhNSJ9.eyJpc3MiOiJzdXBlcmNlbGwiLCJhdWQiOiJzdXBlcmNlbGw6Z2FtZWFwaSIsImp0aSI6ImVjM2UwYmU2LTYwNWItNDlmMC05YTZkLWEyYmRiOGUwY2RmNSIsImlhdCI6MTc0Njc4MjA0OCwic3ViIjoiZGV2ZWxvcGVyL2E2YjA2OTRlLTJkZjQtZmJmYy1jYjFjLTc5ZDFlZTZlNGMxZiIsInNjb3BlcyI6WyJicmF3bHN0YXJzIl0sImxpbWl0cyI6W3sidGllciI6ImRldmVsb3Blci9zaWx2ZXIiLCJ0eXBlIjoidGhyb3R0bGluZyJ9LHsiY2lkcnMiOlsiODUuMTkyLjczLjQzIl0sInR5cGUiOiJjbGllbnQifV19.LraSBBqQRD8GDsb9QO9YRN2XL0Z-lejeReCANhLXWNca69zULDcDOyaCaxespDAZyF3ExJSzMuhIDIjgZ3_mcA";

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

    public static void convertirObjectesI(String json) {
        Gson gson = new Gson();
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        JsonArray brawlersArray = jsonObject.getAsJsonArray("items");

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
            } catch (JsonSyntaxException e) {
                Vista.mostrarMissatge("Error al deserialitzar el brawler: " + e.getMessage());
            } catch (NullPointerException e) {
                Vista.mostrarMissatge("Error: un dels camps es null.");
            }
        }
    }

    public static void convertirObjectesL(String json) {
        Gson gson = new Gson();
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        JsonArray brawlersArray = jsonObject.getAsJsonArray("list");

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
            } catch (JsonSyntaxException e) {
                Vista.mostrarMissatge("Error al deserialitzar el brawler: " + e.getMessage());
            } catch (NullPointerException e) {
                Vista.mostrarMissatge("Error: un dels camps es null.");
            }
        }
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

}
