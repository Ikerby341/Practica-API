package sapalomera.model.dao.SQLite;

import sapalomera.model.dao.StarPowers;
import sapalomera.view.Vista;

import java.sql.*;
import java.util.ArrayList;

public class SQLiteStarPowersDAO {
    public static void crearTotal(Connection con, ArrayList<StarPowers> o) {
        if (o.get(0) != null) {
            try (Statement stmt = con.createStatement()) {
                int rowsAffected = stmt.executeUpdate("DELETE FROM starpowers");
                if (rowsAffected > 0) {
                    System.out.println("La taula starpowers ha sigut eliminada amb éxit.");
                } else {
                    System.out.println("No s'ha pogut eliminar la taula starpowers.");
                }
            } catch (SQLException e) {
                throw new RuntimeException("Error al eliminar la taula", e);
            }
            for(int i = 0; i < o.size(); i++) {
                String sql = "INSERT INTO starpowers (starpower_id, nom) VALUES (?, ?)";
                try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                    pstmt.setInt(1, o.get(i).getId());
                    pstmt.setString(2, o.get(i).getName());
                    pstmt.executeUpdate();
                    Vista.mostrarMissatge("Registre insertat correctament.");
                } catch (SQLException e) {
                    System.err.println("Error al insertar en la base de dades: " + e.getMessage());
                }
            }
        } else {
            throw new IllegalArgumentException("L'objecte proporcionat no es una instancia d'Escaladors.");
        }
    }

    public static void crearParcial(Connection con, ArrayList<StarPowers> o) {
        int ou;
        for (int i = 0; i < o.size(); i++) {
            if (o.get(i) != null) {
                try (Statement stmt = con.createStatement()) {
                    String sql = "SELECT COUNT(*) FROM starpowers WHERE starpower_id = " + o.get(i).getId();
                    ResultSet rs = stmt.executeQuery(sql);
                    ou = rs.getInt(1);
                } catch (SQLException e) {
                    throw new RuntimeException("Error al actualitzar la base de dades", e);
                }
                if (ou == 0) {
                    String sql = "INSERT INTO starpowers (starpower_id, nom) VALUES (?, ?)";
                    try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                        pstmt.setInt(1, o.get(i).getId());
                        pstmt.setString(2, o.get(i).getName());
                        pstmt.executeUpdate();
                        Vista.mostrarMissatge("Registre insertat correctament.");
                    } catch (SQLException e) {
                        System.err.println("Error al insertar en la base de dades: " + e.getMessage());
                    }
                }

            } else {
                throw new IllegalArgumentException("L'objecte proporcionat no es una instancia de StarPowers.");
            }
        }
    }
}
