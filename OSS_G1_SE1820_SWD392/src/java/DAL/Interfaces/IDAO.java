package DAL.Interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface IDAO<T> {

    T GetByResultSet(ResultSet rs) throws SQLException;

    ArrayList<T> All(String tableName);

    T Read(String tableName, int id);

    boolean Create(T entity);

    boolean Update(T entity);

    boolean Delete(int id);
}
