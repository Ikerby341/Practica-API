package sapalomera.model;

import com.google.gson.annotations.SerializedName;

public class Gadgets {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    // Constructor, Getters y Setters
    public Gadgets(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

