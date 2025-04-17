/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jframes;

import java.io.IOException;

/**
 *
 * @author pc
 */


public class test {
    public static void main(String[] args) {
        try {
            // Scan the barcode to get the ISBN
            String[] isbnAndQuantityList = barcode.scanBarcode();
            for (int i = 0; i < isbnAndQuantityList.length; i += 2) {
                String isbn = isbnAndQuantityList[i];
                int quantity = Integer.parseInt(isbnAndQuantityList[i + 1]);

                System.out.println("Scanned ISBN: " + isbn);
                System.out.println("Quantity: " + quantity);

                // Use the ISBN to get book info
                BookInfo bookInfo = new BookInfo();
                String info = bookInfo.getBookInfo(isbn,quantity);
                System.out.println("Book Info: " + info);
            }
           
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
    

    
    
