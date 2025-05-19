package sapalomera.model;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Rarity rarity = (Rarity) obj;
        return id == rarity.id && name.equals(rarity.name) && color.equals(rarity.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, color);
    }
}
