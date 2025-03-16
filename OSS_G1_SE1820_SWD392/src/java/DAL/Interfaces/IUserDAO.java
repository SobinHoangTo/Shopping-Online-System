/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAL.Interfaces;

import models.Entities.User;

/**
 *
 * @author Lenono
 */
public interface IUserDAO {
    User findUserByEmail(String email);
}
