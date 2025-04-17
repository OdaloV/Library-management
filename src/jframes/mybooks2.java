/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package jframes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import static jframes.menu1.retrieveUsername;

/**
 *
 * @author pc
 */
public class mybooks2 extends javax.swing.JFrame {
    private Connection connection;
     private String username;
   

    /**
     * Creates new form mybooks2
     */
    public mybooks2(String username) {
        this.username = username;
        initComponents();
        try {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/libsa", "root", "");
        // Fetch the book titles and authors from the database
        String query = "SELECT title, author FROM bookdetail";
        PreparedStatement pstmt = connection.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        // Add each book title and author to the combo boxes
        while (rs.next()) {
            String title = rs.getString("title");
            String author = rs.getString("author");
            boxbookname.addItem(title);
            boxauthor.addItem(author);
        }

        rs.close();
        pstmt.close();
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
        
    }
    private void checkAvailability() {
    String title = (String) boxbookname.getSelectedItem();
    String author = (String) boxauthor.getSelectedItem();

    try {
        // Check how many books the user has already borrowed
        String countQuery = "SELECT books_borrowed FROM users WHERE username = ?";
        PreparedStatement countStmt = connection.prepareStatement(countQuery);
        countStmt.setString(1, username);
        ResultSet countRs = countStmt.executeQuery();

        if (countRs.next()) {
            int booksBorrowed = countRs.getInt("books_borrowed");

            // Check if the user is a member and has borrowed less than 5 books,
            // or if the user is not a member and has borrowed less than 1 book
            if ((isMember(username) && booksBorrowed < 5) || (!isMember(username) && booksBorrowed < 1)) {
                // The user can borrow a book

                String query = "SELECT quantity FROM bookdetail WHERE title = ? AND author = ?";
                PreparedStatement pstmt = connection.prepareStatement(query);
                pstmt.setString(1, title);
                pstmt.setString(2, author);

                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    int quantity = rs.getInt("quantity");
                    if (quantity > 0) {
                        lblstatus.setText("Request granted");
                        insertBookDetails(title, author);
                        updateBookCount(title, author, quantity - 1);
                        updateBooksBorrowed(username, booksBorrowed + 1); // Update the number of books the user has borrowed
                    } else {
                        lblstatus.setText("Request pending");
                    }
                } 

                rs.close(); // Close ResultSet
                pstmt.close(); // Close PreparedStatement
            } else {
                // The user cannot borrow a book
                lblstatus.setText("You have reached your borrowing limit.");
            }
        } 

        countRs.close(); // Close ResultSet
        countStmt.close(); // Close PreparedStatement
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}
    // Method to check if a user is a member
public boolean isMember(String username) {
    boolean isMember = false;
    try {
        String query = "SELECT * FROM members WHERE username = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, username);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            isMember = true;
        }
        rs.close();
        pstmt.close();
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return isMember;
}

// Method to update the number of books a user has borrowed
public void updateBooksBorrowed(String username, int newCount) {
    try {
        String updateQuery = "UPDATE users SET books_borrowed = ? WHERE username = ?";
        PreparedStatement updateStmt = connection.prepareStatement(updateQuery);
        updateStmt.setInt(1, newCount);
        updateStmt.setString(2, username);
        updateStmt.executeUpdate();
        updateStmt.close();
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}


   private void updateBookCount(String title, String author, int newQuantity) {
    try {
        String updateQuery = "UPDATE bookdetail SET quantity = ? WHERE title = ? AND author = ?";
        PreparedStatement updateStmt = connection.prepareStatement(updateQuery);
        updateStmt.setInt(1, newQuantity);
        updateStmt.setString(2, title);
        updateStmt.setString(3, author);
        
        int rowsAffected = updateStmt.executeUpdate();
        
        if (rowsAffected > 0) {
            System.out.println("Book count updated successfully.");
        } else {
            System.out.println("Failed to update book count.");
        }
        
        updateStmt.close(); // Close PreparedStatement
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}
     
    private void insertBookDetails(String title, String author) {
        try {
            String insertQuery = "INSERT INTO mybooks (title, author, duration) VALUES (?, ?, ?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertQuery);

             insertStatement.setString(1, username);
            insertStatement.setString(2, title);
            insertStatement.setString(3, author);

            LocalDate currentDate = LocalDate.now();
            LocalDate dueDate = currentDate.plusDays(7);
            insertStatement.setString(3, dueDate.toString());

            insertStatement.executeUpdate();
            insertStatement.close();

            lblbkname.setText("Title: " + title);
            lblbkauthor.setText("Author: " + author);
            lbldue.setText("Due Date: " + dueDate.toString());

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

      // Method to store book details in the "mybooks" table
    public static String retrieveUsername(String enteredUsername, String enteredPassword) {
        String username = ""; // Initialize to an empty string
        
        // JDBC URL, username, and password of MySQL server
        String url = "jdbc:mysql://localhost:3306/libsa";
        String user = "root";
        String password = "";
        
        // SQL query to retrieve the username
        String sql = "SELECT username FROM users WHERE username = ? AND password = ?";
        
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Set the parameters in the prepared statement
            pstmt.setString(1, enteredUsername);
            pstmt.setString(2, enteredPassword);
            
            // Execute the query
            ResultSet rs = pstmt.executeQuery();
            
            // Check if a result was returned
            if (rs.next()) {
                // Retrieve the username from the result set
                username = rs.getString("username");
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving username: " + e.getMessage());
        }
        
        return username; // Return the retrieved username
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        lblstatus = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        btnsubmit = new javax.swing.JButton();
        boxbookname = new javax.swing.JComboBox<>();
        boxauthor = new javax.swing.JComboBox<>();
        lblbkname = new javax.swing.JLabel();
        lblbkauthor = new javax.swing.JLabel();
        lbldue = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(102, 0, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("MY BOOKS");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(266, 36, 218, 58));

        jPanel5.setBackground(new java.awt.Color(255, 102, 0));

        jLabel13.setFont(new java.awt.Font("Segoe UI Semilight", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Rewind_48px.png"))); // NOI18N
        jLabel13.setText("Back");
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 44, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, -1));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(102, 0, 102));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Literature_100px_1.png"))); // NOI18N
        jLabel14.setText("REQUEST BOOK");
        jPanel4.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 320, 80));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 4, Short.MAX_VALUE)
        );

        jPanel4.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 320, 4));

        jLabel15.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Book Name");
        jPanel4.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 110, -1));

        lblstatus.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        lblstatus.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.add(lblstatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 420, 260, 30));

        jLabel18.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Author");
        jPanel4.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 70, -1));

        jLabel21.setFont(new java.awt.Font("Segoe UI Semibold", 1, 30)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("   X");
        jLabel21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel21MouseClicked(evt);
            }
        });
        jPanel4.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 10, 60, 50));

        btnsubmit.setBackground(new java.awt.Color(102, 0, 102));
        btnsubmit.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        btnsubmit.setForeground(new java.awt.Color(255, 255, 255));
        btnsubmit.setText("SUBMIT");
        btnsubmit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnsubmitMouseClicked(evt);
            }
        });
        btnsubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsubmitActionPerformed(evt);
            }
        });
        jPanel4.add(btnsubmit, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 510, 130, 40));

        boxbookname.setToolTipText("");
        jPanel4.add(boxbookname, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 200, 270, 50));

        boxauthor.setToolTipText("");
        jPanel4.add(boxauthor, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 310, 270, 50));

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 420, 590));

        lblbkname.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jPanel2.add(lblbkname, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 60, 230, 50));

        lblbkauthor.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jPanel2.add(lblbkauthor, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 130, 200, 60));

        lbldue.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jPanel2.add(lbldue, new org.netbeans.lib.awtextra.AbsoluteConstraints(437, 220, 210, 50));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 780, 600));

        setSize(new java.awt.Dimension(838, 704));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        NewJFrame1 frame = new NewJFrame1(username);
        frame.setVisible(true);
        dispose();  // TODO add your handling code here:
    }//GEN-LAST:event_jLabel13MouseClicked

    private void jLabel21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel21MouseClicked
        System.exit(0);      // TODO add your handling code here:
    }//GEN-LAST:event_jLabel21MouseClicked

    private void btnsubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsubmitActionPerformed
     checkAvailability();
    }//GEN-LAST:event_btnsubmitActionPerformed

    private void btnsubmitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnsubmitMouseClicked
    
    }//GEN-LAST:event_btnsubmitMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(mybooks2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mybooks2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mybooks2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mybooks2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                String username = retrieveUsername("", "");
                new mybooks2(username).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> boxauthor;
    private javax.swing.JComboBox<String> boxbookname;
    private javax.swing.JButton btnsubmit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JLabel lblbkauthor;
    private javax.swing.JLabel lblbkname;
    private javax.swing.JLabel lbldue;
    private javax.swing.JLabel lblstatus;
    // End of variables declaration//GEN-END:variables
}
