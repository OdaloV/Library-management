/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package jframes;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 *
 * @author pc
 */
public class NewJFrame1 extends javax.swing.JFrame {
    private static String username;
    private int lastBookPositionY = 20;

    /**
     * Creates new form NewJFrame1
     */
   public NewJFrame1(String username) {
       this.username = username;
        initComponents();
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                fetchBookDetails();
            }
        });
    }
private void fetchBookDetails() {
    try {
        // Establish connection to the database
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/libsa", "root", "");

        // Create and execute SQL query to fetch book details
        String query = "SELECT title, author, info, cover_image FROM bookdetail";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        int yPosition = 20; 
        

        // Iterate through the result set to display all rows
        while (resultSet.next()) {
            // Retrieve book details from the result set
            String title = resultSet.getString("title");
            String author = resultSet.getString("author");
            String info = resultSet.getString("info");
            byte[] coverImageBytes = resultSet.getBytes("cover_image");

            // Set the retrieved data to corresponding JLabels
            JLabel titleLabel = new javax.swing.JLabel();
            titleLabel.setFont(new java.awt.Font("Sitka Small", 1, 14));
            titleLabel.setText("Title: " + title);
            jPanel2.add(titleLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, yPosition, 320, 20));
            

            JLabel authorLabel = new javax.swing.JLabel();
            authorLabel.setFont(new java.awt.Font("Sitka Small", 1, 14));
            authorLabel.setText("Author: " + author);
            jPanel2.add(authorLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, yPosition + 40, 180, 20));

            // Create a JTextArea for info
            JTextArea infoTextArea = new javax.swing.JTextArea();
            infoTextArea.setFont(new java.awt.Font("Sitka Small", 1, 14));
            infoTextArea.append("Info: " + info); // Use append() instead of setText()
            infoTextArea.setEditable(false);
            infoTextArea.setLineWrap(true); // Enable line wrapping
            infoTextArea.setWrapStyleWord(true); // Wrap at word boundaries

// Calculate the preferred size based on the length of the text
            jPanel2.add(infoTextArea, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, yPosition + 80, 870, 180));

            JLabel coverLabel = new javax.swing.JLabel();
            coverLabel.setBounds(10, yPosition + 80, 140, 160);
            jPanel2.add(coverLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, yPosition + 80, 140, 160));

            
            if (coverImageBytes != null && coverImageBytes.length > 0) {
                // Create an ImageIcon from the cover image bytes
                ImageIcon imageIcon = new ImageIcon(coverImageBytes);
                Image image = imageIcon.getImage();
                
                // Resize the image to fit the JLabel
                Image scaledImage = image.getScaledInstance(coverLabel.getWidth(), coverLabel.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
                coverLabel.setIcon(scaledIcon);
            } else {
                // Set a default message or icon for the cover JLabel
                coverLabel.setIcon(null); // or setIcon(new javax.swing.ImageIcon(getClass().getResource("/default_cover_image.png")));
            }
            
            yPosition += 250; // Increment y position for the next book
             lastBookPositionY += 250;
        }
            
            // Close resources
            resultSet.close();
            statement.close();
            connection.close();
           // Adjust position of the button below the last book
            jButton1.setBounds(380, lastBookPositionY + 90, 140, 50); 
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQL exception
        }
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
        jPanel5 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        coverimage = new javax.swing.JLabel();
        Titles = new javax.swing.JLabel();
        Author = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(102, 0, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 44, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 40));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("MY BOOKS");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 0, 218, 58));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, -1));

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        coverimage.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jPanel2.add(coverimage, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 170, 180));

        Titles.setFont(new java.awt.Font("Segoe UI Semibold", 3, 18)); // NOI18N
        jPanel2.add(Titles, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 320, 30));

        Author.setFont(new java.awt.Font("Segoe UI Semibold", 3, 18)); // NOI18N
        jPanel2.add(Author, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 370, 40));

        jButton1.setBackground(new java.awt.Color(255, 102, 0));
        jButton1.setFont(new java.awt.Font("Segoe UI Semibold", 3, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("REQUEST BOOK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 2680, 200, 40));

        jScrollPane3.setViewportView(jPanel2);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 1280, 550));

        setBounds(0, 0, 1315, 4089);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
         // TODO add your handling code here:
    }//GEN-LAST:event_jLabel13MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        mybooks2 books2 = new mybooks2(username);
        books2.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(NewJFrame1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame1(username).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Author;
    private javax.swing.JLabel Titles;
    private javax.swing.JLabel coverimage;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables
}
