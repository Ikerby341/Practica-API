package sapalomera.model.dao;

public class Rarity {
    private int id;
    private String nom;
    private String color;

    public Rarity(int id, String nom, String color) {
        this.id = id;
        this.nom = nom;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
