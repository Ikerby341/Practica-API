package sapalomera.model.dao;

public class Brawlers {
    private String id;
    private String nom;
    private int gadgetID;
    private int gadget2ID;
    private int starpower1ID;
    private int starpower2ID;

    public Brawlers(String id, String nom, int gadgetID, int gadget2ID, int starpower1ID, int starpower2ID) {
        this.id = id;
        this.nom = nom;
        this.gadgetID = gadgetID;
        this.gadget2ID = gadget2ID;
        this.starpower1ID = starpower1ID;
        this.starpower2ID = starpower2ID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getGadgetID() {
        return gadgetID;
    }

    public void setGadgetID(int gadgetID) {
        this.gadgetID = gadgetID;
    }

    public int getGadget2ID() {
        return gadget2ID;
    }

    public void setGadget2ID(int gadget2ID) {
        this.gadget2ID = gadget2ID;
    }

    public int getStarpower1ID() {
        return starpower1ID;
    }

    public void setStarpower1ID(int starpower1ID) {
        this.starpower1ID = starpower1ID;
    }

    public int getStarpower2ID() {
        return starpower2ID;
    }

    public void setStarpower2ID(int starpower2ID) {
        this.starpower2ID = starpower2ID;
    }
}
