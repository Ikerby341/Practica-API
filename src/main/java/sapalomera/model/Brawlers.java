package sapalomera.model;

import com.google.gson.annotations.SerializedName;


import java.util.List;

public class Brawlers {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String nom;

    @SerializedName("rarity")
    private Rarity rarity; // Clase Rarity que representa la rareza

    private int rarityID;
    private int gadgetID;
    private int gadget2ID;
    private int starpower1ID;
    private int starpower2ID;

    @SerializedName("starPowers")
    private List<StarPowers> starPowers; // Mantener el campo de star powers

    @SerializedName("gadgets")
    private List<Gadgets> gadgets; // Mantener el campo de gadgets

    // Constructor modificado
    public Brawlers(int id, String nom, int rarityID, int gadgetID, int gadget2ID, int starpower1ID, int starpower2ID) {
        this.id = id;
        this.nom = nom;
        extractIdsFromLists();
        this.rarityID = getRarityID();
        this.gadgetID = getGadgetID();
        this.gadget2ID = getGadget2ID();
        this.starpower1ID = getStarpower1ID();
        this.starpower2ID = getStarpower2ID();
    }

    // Constructor vacío requerido para Gson
    public Brawlers() {
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public int getRarityID() {
        return rarity.getId();
    }

    public List<StarPowers> getStarPowers() {
        return starPowers;
    }

    public List<Gadgets> getGadgets() {
        return gadgets;
    }

    public int getGadgetID() {
        return gadgetID;
    }
    public int getGadget2ID() {
        return gadget2ID;
    }
    public int getStarpower1ID() {
        return starpower1ID;
    }
    public int getStarpower2ID() {
        return starpower2ID;
    }

    public void extractIdsFromLists() {
        if (gadgets != null) {
            gadgetID = gadgets.size() > 0 ? gadgets.get(0).getId() : -1;
            gadget2ID = gadgets.size() > 1 ? gadgets.get(1).getId() : -1;
        } else {
            gadgetID = -1;
            gadget2ID = -1;
        }
        if (starPowers != null) {
            starpower1ID = starPowers.size() > 0 ? starPowers.get(0).getId() : -1;
            starpower2ID = starPowers.size() > 1 ? starPowers.get(1).getId() : -1;
        } else {
            starpower1ID = -1;
            starpower2ID = -1;
        }
    }
}
