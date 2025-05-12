package sapalomera.controller;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class EndPointController {
    public static Scanner scan = new Scanner(System.in);
    static Gson gson = new Gson();

    public static void llegirGsonBrawlify() {
        try {
            URL url = new URL("https://api.brawlify.com/v1/brawlers");
            URLConnection conexio = url.openConnection();

            BufferedReader in = new BufferedReader(new InputStreamReader(conexio.getInputStream()));
            String resultat = "";
            String linea;

            while ((linea = in.readLine()) != null) resultat += linea;
            in.close();

            System.out.println("JSON recibido:");
            System.out.println(resultat.toString());

        } catch (Exception e) {
            System.out.println("Error al conectar con la API: " + e.getMessage());
        }

    }

    public static void llegirGsonBrawlStars() {
        String apikey = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiIsImtpZCI6IjI4YTMxOGY3LTAwMDAtYTFlYi03ZmExLTJjNzQzM2M2Y2NhNSJ9.eyJpc3MiOiJzdXBlcmNlbGwiLCJhdWQiOiJzdXBlcmNlbGw6Z2FtZWFwaSIsImp0aSI6ImVjM2UwYmU2LTYwNWItNDlmMC05YTZkLWEyYmRiOGUwY2RmNSIsImlhdCI6MTc0Njc4MjA0OCwic3ViIjoiZGV2ZWxvcGVyL2E2YjA2OTRlLTJkZjQtZmJmYy1jYjFjLTc5ZDFlZTZlNGMxZiIsInNjb3BlcyI6WyJicmF3bHN0YXJzIl0sImxpbWl0cyI6W3sidGllciI6ImRldmVsb3Blci9zaWx2ZXIiLCJ0eXBlIjoidGhyb3R0bGluZyJ9LHsiY2lkcnMiOlsiODUuMTkyLjczLjQzIl0sInR5cGUiOiJjbGllbnQifV19.LraSBBqQRD8GDsb9QO9YRN2XL0Z-lejeReCANhLXWNca69zULDcDOyaCaxespDAZyF3ExJSzMuhIDIjgZ3_mcA";
        try {
            URL url = new URL("https://api.brawlstars.com/v1/brawlers");
            URLConnection conexio = url.openConnection();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "Bearer " + apikey);

            int responseCode = conn.getResponseCode();
            System.out.println("Código de respuesta: " + responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(conexio.getInputStream()));
            String resultat = "";
            String linea;

            while ((linea = in.readLine()) != null) resultat += linea;
            in.close();

            System.out.println("JSON recibido:");
            System.out.println(resultat.toString());

        } catch (Exception e) {
            System.out.println("Error al conectar con la API: " + e.getMessage());
        }

    }

    public static String llistarBrawlerID(int ID) {
        try {
            URL url = new URL("https://api.brawlify.com/v1/brawlers/" + ID);
            URLConnection conexio = url.openConnection();

            BufferedReader in = new BufferedReader(new InputStreamReader(conexio.getInputStream()));
            String resultat = "";
            String linea;

            while ((linea = in.readLine()) != null) resultat += linea;
            in.close();

            return resultat.toString();

        } catch (Exception e) {
            System.out.println("Error al conectar con la API: " + e.getMessage());
        }

        return "Alguna cosa ha sortit malament";
    }

}
