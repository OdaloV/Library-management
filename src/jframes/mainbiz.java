/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jframes;

/**
 *
 * @author pc
 */
public class mainbiz {
    public static void main(String[] args) {
        String accessToken = biz.getAccessToken();
        if (accessToken != null) {
            System.out.println("Access token: " + accessToken);
            // Now you can use the access token for further operations
        } else {
            System.out.println("Failed to obtain access token");
        }
    }
    
}
