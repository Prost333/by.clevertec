package by.clevertec.Repository;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public interface Dao <E,D>{
    D get(String id) throws SQLException;
    void save(E t);
    void delete(String id);
    D update(E e);
    List<D>finaAll(Integer page, Integer pageSize);
}
