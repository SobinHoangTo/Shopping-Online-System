/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import jakarta.servlet.http.Cookie;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import org.json.JSONObject;

/**
 *
 * @author vdqvi
 */
public class CartService {

    public JSONObject getCartItemsFromCookies(Cookie[] cookies) {
        try {
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("cartItems".equals(cookie.getName())) {
                        String cartItemsJson = java.net.URLDecoder.decode(cookie.getValue(), "UTF-8");
                        return new JSONObject(cartItemsJson);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Integer> getIdListFromCartItems(JSONObject cartItemsJson) {
        ArrayList<Integer> idList = new ArrayList<>();
        if (cartItemsJson != null && !cartItemsJson.isEmpty()) {
            for (Iterator<String> iterator = cartItemsJson.keys(); iterator.hasNext();) {
                idList.add(Integer.valueOf(iterator.next()));
            }
        }
        return idList;
    }

}
