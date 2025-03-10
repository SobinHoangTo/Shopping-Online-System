/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL.Interfaces;

import Model.Entity.Sample;

/**
 *
 * @author vdqvi
 */
public interface ISampleDAO extends IDAO<Sample> {

    Sample ReadByTitle(String title);

}
