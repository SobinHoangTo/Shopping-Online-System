/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

/**
 *
 * @author asus
 */
public class VNPayConfig {

    public static final String VNP_PAY_URL = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html";
    public static final String VNP_RETURN_URL = "http://localhost:8080/OSS_G1_SE1820_SWD392/vnpay_return";
    public static final String VNP_TMN_CODE = "WVH3SLHT"; // Mã website từ VNPay
    public static final String SECRET_KEY = "KB6S7C20YB0K2BLUA8O46ONZUTKJSX4C";   // Chuỗi bí mật từ VNPay
    public static final String VNP_API_URL = "https://sandbox.vnpayment.vn/merchant_webapi/api/transaction";
}
