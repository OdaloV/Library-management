/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jframes;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.OutputStream;
/**
 *
 * @author pc
 */
public class MpesaIntegration {
    public void startMpesa(String amount, String msisdn) {
        try {
            // Get access token from biz class
            String accessToken = biz.getAccessToken();
            if (accessToken != null) {
                URL url = new URL("https://sandbox.safaricom.co.ke/mpesa/c2b/v1/simulate");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestProperty("Authorization", "Bearer " + accessToken);
                conn.setDoOutput(true);

                String jsonInputString = "{\"ShortCode\":\"174379\", \"CommandID\":\"CustomerPayBillOnline\", \"Amount\":\"" + amount + "\", \"Msisdn\":\"" + msisdn + "\", \"BillRefNumber\":\"123456\"}";

                try (OutputStream os = conn.getOutputStream()) {
                    byte[] input = jsonInputString.getBytes("utf-8");
                    os.write(input, 0, input.length);
                }

                int code = conn.getResponseCode();
                System.out.println(code);
            } else {
                System.out.println("Failed to obtain access token");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     /*public static void main(String[] args) {
        try {
            // Get access token from biz class
            String accessToken = biz.getAccessToken();
            if (accessToken != null) {
                URL url = new URL("https://sandbox.safaricom.co.ke/mpesa/c2b/v1/simulate");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestProperty("Authorization", "Bearer " + accessToken);
                conn.setDoOutput(true);

                /*String jsonInputString = "{\"ShortCode\":\" \", \"CommandID\":\"CustomerPayBillOnline\", \"Amount\":\" \", \"Msisdn\":\" \", \"BillRefNumber\":\" \"}";*/
                /*String jsonInputString = "{\"ShortCode\":\"77777\", \"CommandID\":\"CustomerPayBillOnline\", \"Amount\":\""+ Amount+"\", \"Msisdn\":\""+Msisdn+"\", \"BillRefNumber\":\"123456\"}";


                try(OutputStream os = conn.getOutputStream()) {
                    byte[] input = jsonInputString.getBytes("utf-8");
                    os.write(input, 0, input.length);           
                }

                int code = conn.getResponseCode();
                System.out.println(code);
            } else {
                System.out.println("Failed to obtain access token");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
    }
    
   
    

