/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.DTOs;

import java.util.ArrayList;

/**
 *
 * @author vdqvi
 */
public class DataPublics<T> {

    public ArrayList<T> data;
    public int count;

    public DataPublics(ArrayList<T> data, int count) {
        this.data = data;
        this.count = count;
    }

}
