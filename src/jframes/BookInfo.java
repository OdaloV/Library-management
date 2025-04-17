/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jframes;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;


/**
 *
 * @author pc
 */
public class BookInfo {
    
    OkHttpClient client = new OkHttpClient();

    public String getBookInfo(String isbn, int quantity) throws IOException {
        // Use the ISBN to get book info from the Google Books API
        Request request = new Request.Builder()
            .url("https://www.googleapis.com/books/v1/volumes?q=isbn:" + isbn)
            .build();

        try (Response response = client.newCall(request).execute()) {
            String jsonData = response.body().string();
            JSONObject jsonObject = new JSONObject(jsonData);

            if (!jsonObject.has("items")) {
                return "No items found in the API response";
            }

            JSONObject volumeInfo = jsonObject.getJSONArray("items").getJSONObject(0).getJSONObject("volumeInfo");

            String title = volumeInfo.getString("title");
            String author = volumeInfo.getJSONArray("authors").getString(0); // assuming there's at least one author

            // Check if the publisher field exists before trying to access it
            String publisher = volumeInfo.has("publisher") ? volumeInfo.getString("publisher") : "Publisher not available";

            String publication_date = volumeInfo.getString("publishedDate");
            String info = volumeInfo.has("description") ? volumeInfo.getString("description") : "Description not available";

            // Store the book information in the database
            storeBookInfo(isbn, title, author, publisher, publication_date, info, quantity);

            return title;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    void storeBookInfo(String isbn, String title, String author, String publisher, String publication_date, String info, int quantity) {
        try {
            Connection connection = DBConnection.getConnection();
            // Query to check if the book already exists in the database
            String query = "SELECT COUNT(*) FROM bookdetail WHERE isbn = ?";
            PreparedStatement checkStatement = connection.prepareStatement(query);
            checkStatement.setString(1, isbn);
            ResultSet resultSet = checkStatement.executeQuery();

            // If the book already exists (count > 0), skip insertion
            if (resultSet.next() && resultSet.getInt(1) > 0) {
                System.out.println("Book with ISBN " + isbn + " already exists in the database. Skipping insertion.");
                return;
            }

            // Check if publication_date is only a year
            if (publication_date.length() == 4) {
                // Append "-01-01" to make it a valid date
                publication_date += "-01-01";
            }

            String sql = "INSERT INTO bookdetail (isbn, title, author, publisher, publication_date, info, quantity) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, isbn);
            statement.setString(2, title);
            statement.setString(3, author);
            statement.setString(4, publisher);
            statement.setString(5, publication_date);
            statement.setString(6, info);
            statement.setInt(7, quantity);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
    

