/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

/**
 *
 * @author vdqvi
 */
public class AppConfig {

    public static final String DB_USER = "sa";
    public static final String DB_PASS = "123";
    public static final String DB_NAME = "SWD392_SE1820_SP25_G1";
    public static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=" + DB_NAME;
    
    public static final String GOOGLE_CLIENT_ID="483761590621-einkggqe7csa8ru06j1j48nm8ijjg89c.apps.googleusercontent.com" ;
    public static final String GOOGLE_CLIENT_SECRET="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" ;
    public static final String GOOGLE_REDIRECT_URI="http://localhost:9999/OSS_G1_SE1820_SWD392/Login" ;
    public static final String GOOGLE_GRANT_TYPE ="authorization_code";
    public static final String GOOGLE_LINK_GET_TOKEN="https://accounts.google.com/o/oauth2/token" ;
    public static final String GOOGLE_LINK_GET_USER_INFO="https://www.googleapis.com/oauth2/v1/userinfo?access_token=" ;
}
