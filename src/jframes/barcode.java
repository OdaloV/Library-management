/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jframes;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 *
 * @author pc
 */
public class barcode {
    private static Set<String> scannedISBNs = new HashSet<>();
    public static String []scanBarcode() throws IOException {
        String excelFilePath = "C:\\Users\\pc\\Downloads\\Book1.xlsx";
        FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet firstSheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = firstSheet.iterator();
        List<String> isbnList = new ArrayList<>();
 // to collect all ISBN values
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            Cell cell = nextRow.getCell(0); // assuming ISBN is in the first column
             Cell quantityCell = nextRow.getCell(1); 

            String isbn;
            int quantity=0;
            if (cell != null) {
                if (cell.getCellType() == CellType.STRING) {
                    isbn = cell.getStringCellValue();
                } else if (cell.getCellType() == CellType.NUMERIC) {
                    isbn = String.valueOf((long) cell.getNumericCellValue());
                } else {
                    continue; // Skip cells that are neither STRING nor NUMERIC
                }
                if (quantityCell != null && quantityCell.getCellType() == CellType.NUMERIC) {
            quantity = (int) quantityCell.getNumericCellValue();
                }

            isbnList.add(isbn); // add the ISBN value to the list
            isbnList.add(String.valueOf(quantity)); // add the quantity value to the list
        }
            
        }

        workbook.close();
        inputStream.close();

        return isbnList.toArray(new String[0]);
        // return the list of ISBN values
    }
}
