package sapalomera.model.dao;

import com.google.gson.annotations.SerializedName;

public class Rarity {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("color")
    private String color;

    public Rarity(int id, String name, String color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }

    // Constructor vacío requerido para Gson
    public Rarity() {
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }
}
