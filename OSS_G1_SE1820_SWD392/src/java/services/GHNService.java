/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import config.GHNConfig;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import models.Entities.Product;
import models.Enums.PaymentMethod;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author vdqvi
 */
public class GHNService {

    public JSONObject CreateOrder(String name, String phone, String address, String ward, String district, String paymentMethod, double totalPrice, ArrayList<Product> productList, JSONObject cartItemsJson) {
        try {
            URL url = new URL(GHNConfig.CREATE_ORDER_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("ShopId", GHNConfig.SHOP_ID);
            connection.setRequestProperty("Token", GHNConfig.TOKEN_API);
            connection.setDoOutput(true);

            JSONObject requestBody = new JSONObject();
            requestBody.put("to_name", name);
            requestBody.put("to_phone", phone);
            requestBody.put("to_address", address);
            requestBody.put("to_ward_code", ward);
            requestBody.put("to_district_id", Integer.parseInt(district));
            requestBody.put("service_id", 53321);
            requestBody.put("service_type_id", 2);
            requestBody.put("payment_type_id", 2);
            requestBody.put("cod_amount", paymentMethod.equals(PaymentMethod.COD) ? totalPrice : 0);
            requestBody.put("weight", 500);
            requestBody.put("length", 20);
            requestBody.put("width", 20);
            requestBody.put("height", 20);
            requestBody.put("required_note", "CHOXEMHANGKHONGTHU");

            JSONArray itemsArray = new JSONArray();
            for (Product p : productList) {
                JSONObject item = new JSONObject();
                item.put("name", p.getName());
                item.put("quantity", cartItemsJson.getInt(p.getId() + ""));
                itemsArray.put(item);
            }

            requestBody.put("items", itemsArray);

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = requestBody.toString().getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            BufferedReader reader;
            StringBuilder response = new StringBuilder();

            if (responseCode == 200) {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream(), "utf-8"));
            }

            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line.trim());
            }
            reader.close();

            JSONObject jsonResponse = new JSONObject(response.toString());

            // Print error details if the response code is not 200
            if (responseCode != 200) {
                System.err.println("Error: " + jsonResponse.toString(2)); // Pretty print JSON error
            }

            return jsonResponse;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
