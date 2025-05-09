package sapalomera.model.dao;

public class Brawlers {
    private String id;
    private String nom;
    private String descripcio;
    private String categoria;
    private String tipus;

    public Brawlers(String id, String nom, String descripcio, String categoria, String tipus) {
        this.id = id;
        this.nom = nom;
        this.descripcio = descripcio;
        this.categoria = categoria;
        this.tipus = tipus;
    }

    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }
}
