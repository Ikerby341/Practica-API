package sapalomera.model.dao.interfaces;

import java.util.List;

/**
 * Classe per gestionar el DAO de diferents tipus de base de dades
 * @param <T> El paràmetre "Objecte" (Object)
 * @param <K> El paràmetre "Claú" (Key)
 */
public interface CRUD<T,K> {
    void crear(T t);
    T llistarID(K id);
    List<T> llistarTot();
    void actualitzar(T t);
    boolean esborrar(K id);
}