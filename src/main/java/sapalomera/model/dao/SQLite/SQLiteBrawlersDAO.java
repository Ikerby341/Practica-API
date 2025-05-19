package sapalomera.model.dao.SQLite;

import sapalomera.model.dao.Brawlers;
import sapalomera.view.Vista;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static java.time.LocalDate.now;

public class SQLiteBrawlersDAO {
    public static void crearTotal(Connection con, ArrayList<Brawlers> o) {
        if (o.get(0) != null) {
            try (Statement stmt = con.createStatement()) {
                int rowsAffected = stmt.executeUpdate("DELETE FROM brawlers");
                if (rowsAffected > 0) {
                    System.out.println("La taula brawlers ha sigut eliminada amb éxit.");
                } else {
                    System.out.println("No s'ha pogut eliminar la taula brawlers.");
                }
            } catch (SQLException e) {
                throw new RuntimeException("Error al eliminar la taula", e);
            }
            for(int i = 0; i < o.size(); i++) {
                String sql = "INSERT INTO brawlers (brawler_id, nom, rarity_id, gadget1_id, gadget2_id, starpower1_id, starpower2_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                    pstmt.setInt(1, o.get(i).getId());
                    pstmt.setString(2, o.get(i).getNom());
                    pstmt.setInt(3, o.get(i).getRarityID());
                    pstmt.setInt(4, o.get(i).getGadgetID());
                    pstmt.setInt(5, o.get(i).getGadget2ID());
                    pstmt.setInt(6, o.get(i).getStarpower1ID());
                    pstmt.setInt(7, o.get(i).getStarpower2ID());
                    pstmt.executeUpdate();
                    Vista.mostrarMissatge("Registre insertat correctament.");
                } catch (SQLException e) {
                    System.err.println("Error al insertar en la base de dades: " + e.getMessage());
                }
            }
        } else {
            throw new IllegalArgumentException("L'objecte proporcionat no es una instancia de Brawlers.");
        }
    }

    public static void crearTotalJson(Connection con, ArrayList<Brawlers> o) {
        if (o.get(0) != null) {
            try (Statement stmt = con.createStatement()) {
                int rowsAffected = stmt.executeUpdate("DELETE FROM brawlers");
                if (rowsAffected > 0) {
                    System.out.println("La taula brawlers ha sigut eliminada amb éxit.");
                } else {
                    System.out.println("No s'ha pogut eliminar la taula brawlers.");
                }
            } catch (SQLException e) {
                throw new RuntimeException("Error al eliminar la taula", e);
            }
            for(int i = 0; i < o.size(); i++) {
                String sql = "INSERT INTO brawlers (brawler_id, nom, gadget1_id, gadget2_id, starpower1_id, starpower2_id) VALUES (?, ?, ?, ?, ?, ?)";
                try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                    pstmt.setInt(1, o.get(i).getId());
                    pstmt.setString(2, o.get(i).getNom());
                    pstmt.setInt(3, o.get(i).getGadgetID());
                    pstmt.setInt(4, o.get(i).getGadget2ID());
                    pstmt.setInt(5, o.get(i).getStarpower1ID());
                    pstmt.setInt(6, o.get(i).getStarpower2ID());
                    pstmt.executeUpdate();
                    Vista.mostrarMissatge("Registre insertat correctament.");
                } catch (SQLException e) {
                    System.err.println("Error al insertar en la base de dades: " + e.getMessage());
                }
            }
        } else {
            throw new IllegalArgumentException("L'objecte proporcionat no es una instancia de Brawlers.");
        }
    }

    public static void crearParcial(Connection con, ArrayList<Brawlers> o) {
        int ou;
        for (int i = 0; i < o.size(); i++) {
            if (o.get(i) != null) {
                try (Statement stmt = con.createStatement()) {
                    String sql = "SELECT COUNT(*) FROM brawlers WHERE brawler_id = " + o.get(i).getId();
                    ResultSet rs = stmt.executeQuery(sql);
                    ou = rs.getInt(1);
                } catch (SQLException e) {
                    throw new RuntimeException("Error al actualitzar la base de dades", e);
                }
                if (ou == 0) {
                    String sql = "INSERT INTO brawlers (brawler_id, nom, rarity_id, gadget1_id, gadget2_id, starpower1_id, starpower2_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
                    try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                        pstmt.setInt(1, o.get(i).getId());
                        pstmt.setString(2, o.get(i).getNom());
                        pstmt.setInt(3, o.get(i).getRarityID());
                        pstmt.setInt(4, o.get(i).getGadgetID());
                        pstmt.setInt(5, o.get(i).getGadget2ID());
                        pstmt.setInt(6, o.get(i).getStarpower1ID());
                        pstmt.setInt(7, o.get(i).getStarpower2ID());
                        pstmt.executeUpdate();
                        Vista.mostrarMissatge("Registre insertat correctament.");
                    } catch (SQLException e) {
                        System.err.println("Error al insertar en la base de dades: " + e.getMessage());
                    }
                }
            } else {
                throw new IllegalArgumentException("L'objecte proporcionat no es una instancia de Brawlers.");
            }
        }
    }

    public static void crearParcialJson(Connection con, ArrayList<Brawlers> o) {
        int ou;
        for (int i = 0; i < o.size(); i++) {
            if (o.get(i) != null) {
                try (Statement stmt = con.createStatement()) {
                    String sql = "SELECT COUNT(*) FROM brawlers WHERE brawler_id = " + o.get(i).getId();
                    ResultSet rs = stmt.executeQuery(sql);
                    ou = rs.getInt(1);
                } catch (SQLException e) {
                    throw new RuntimeException("Error al actualitzar la base de dades", e);
                }
                if (ou == 0) {
                    String sql = "INSERT INTO brawlers (brawler_id, nom, gadget1_id, gadget2_id, starpower1_id, starpower2_id) VALUES (?, ?, ?, ?, ?, ?)";
                    try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                        pstmt.setInt(1, o.get(i).getId());
                        pstmt.setString(2, o.get(i).getNom());
                        pstmt.setInt(3, o.get(i).getGadgetID());
                        pstmt.setInt(4, o.get(i).getGadget2ID());
                        pstmt.setInt(5, o.get(i).getStarpower1ID());
                        pstmt.setInt(6, o.get(i).getStarpower2ID());
                        pstmt.executeUpdate();
                        Vista.mostrarMissatge("Registre insertat correctament.");
                    } catch (SQLException e) {
                        System.err.println("Error al insertar en la base de dades: " + e.getMessage());
                    }
                }
            } else {
                throw new IllegalArgumentException("L'objecte proporcionat no es una instancia de Brawlers.");
            }
        }
    }

    public static String llistarTot(Connection con) {
        String fi = "";
        try (PreparedStatement stmt = con.prepareStatement("SELECT * FROM brawlers")) {
            ResultSet rs = stmt.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                String nomCol = metaData.getColumnName(i);
                String nomColumna = nomCol.substring(0, 1).toUpperCase() + nomCol.substring(1).replaceAll("_", " ");
                fi += String.format("%-15s", nomColumna);

            }
            fi += "\n";

            while (rs.next()) {
                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    fi += String.format("%-15s", rs.getString(i));

                }
                fi += "\n";
            }

            if (fi.isEmpty()) fi = "No hi ha dades";
        } catch (SQLException e) {
            throw new RuntimeException("Error al llistar tot", e);
        }
        return fi;
    }

    public static void actualitzar(Connection con,int id, String quequiero, String comoquiero) {
        try (Statement stmt = con.createStatement()) {
            LocalDate avui = LocalDate.now();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String resultat = avui.format(format);
            String sql = "UPDATE brawlers SET " + quequiero.toLowerCase() + " = '" + comoquiero + "', data_modificacio = '" + resultat + "' WHERE brawler_id = " + id;
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualitzar la base de dades", e);
        }
    }

    public static int existeix(Connection con,int id) {
        try (Statement stmt = con.createStatement()) {
            String sql = "SELECT COUNT(*) FROM brawlers WHERE brawler_id = " + id;
            ResultSet rs = stmt.executeQuery(sql);
            return rs.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualitzar la base de dades", e);
        }
    }

}
