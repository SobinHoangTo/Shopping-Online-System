/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.Implementations;

import services.Interfaces.IDAO;
import data.DBContext;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author vdqvi
 */
public abstract class GeneralDAO<T> extends DBContext implements IDAO<T> {

    @Override
    public abstract T GetByResultSet(ResultSet rs) throws SQLException;

    @Override
    public abstract ArrayList<T> All();

    @Override
    public abstract T Read(int id);

    @Override
    public abstract boolean Create(T entity);

    @Override
    public abstract boolean Update(T entity);

    @Override
    public abstract boolean Delete(int id);

}
