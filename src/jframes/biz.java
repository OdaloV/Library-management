/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package jframes;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Base64;
import org.json.JSONObject;

/**
 *
 * @author pc
 */
public class biz {

 public static String getAccessToken() {
        String accessToken = null;
        try {
            URL url = new URL("https://sandbox.safaricom.co.ke/oauth/v1/generate?grant_type=client_credentials");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            String keys = "ylNxPxCjjSh9xPdb74CvsZ8vlbAoQmUeM1dANskA1f8tvm1a:iu2gxV7ypb8TNBtZovad0CwPvUZ5BdjtpAYwid22lFZr2yvW7VgcrqhuFegqPuZA";
            String encoded = Base64.getEncoder().encodeToString(keys.getBytes());
            conn.setRequestProperty("Authorization", "Basic " + encoded);

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String output;
            StringBuffer response = new StringBuffer();
            while ((output = in.readLine()) != null) {
                response.append(output);
            }
            in.close();

            // Extract access token from response
            JSONObject jsonResponse = new JSONObject(response.toString());
            accessToken = jsonResponse.getString("access_token");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return accessToken;
    }
}
