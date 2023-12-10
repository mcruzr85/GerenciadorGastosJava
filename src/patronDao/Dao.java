package patronDao;

import java.util.List;

import entities.Gasto;

public interface Dao<T, K>{

    void insert(T t);
    void update(T t);
    void delete(K id);  //verificar si es asi void delete(T t);
    T findGastoById(K id);

    List<T> getAllGastos();
}
