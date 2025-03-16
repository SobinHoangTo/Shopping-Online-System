/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import config.PayOSConfig;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;
import org.json.JSONObject;

/**
 *
 * @author asus
 */
public class PayOSService {
       public static String createPaymentUrl(int orderId, double amount) throws Exception {
        URL url = new URL(PayOSConfig.PAYOS_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Authorization", "Bearer " + PayOSConfig.PAYOS_API_KEY);
        conn.setDoOutput(true);

        // Tạo payload JSON
        JSONObject payload = new JSONObject();
        payload.put("order_code", UUID.randomUUID().toString()); // Mã đơn hàng duy nhất
        payload.put("amount", (int) (amount * 100)); // PayOS yêu cầu đơn vị VNĐ (x100)
        payload.put("description", "Thanh toán đơn hàng: " + orderId);
        payload.put("return_url", PayOSConfig.PAYOS_RETURN_URL);

        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = payload.toString().getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        if (conn.getResponseCode() == 200) {
            try (java.util.Scanner scanner = new java.util.Scanner(conn.getInputStream())) {
                String responseBody = scanner.useDelimiter("\\A").next();
                JSONObject responseJson = new JSONObject(responseBody);
                return responseJson.getString("checkout_url");
            }
        } else {
            throw new RuntimeException("Lỗi khi tạo URL thanh toán với PayOS");
        }
    }
}