/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.Interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author vdqvi
 */
public interface IDAO<T> {

    T GetByResultSet(ResultSet rs) throws SQLException;

    ArrayList<T> All();

    T Read(int id);

    boolean Create(T entity);

    boolean Update(T entity);

    boolean Delete(int id);
}
