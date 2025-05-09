package sapalomera.controller;

import com.google.gson.Gson;
import sapalomera.model.dao.Brawlers;

import java.util.Scanner;

public class EndPointController {
    public static Scanner scan = new Scanner(System.in);
    static Gson gson = new Gson();
    static String json = "https://api.brawlify.com/v1/brawlers";

    public static void llegirGson() {
        Brawlers brawler = gson.fromJson(json, Brawlers.class);
        System.out.println(brawler.getNom());
        System.out.println(brawler.getDescripcio());
        System.out.println(brawler.getCategoria());
        System.out.println(brawler.getTipus());
    }

}
