/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package jframes;

import com.mysql.cj.xdevapi.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import java.sql.PreparedStatement;
import java.math.BigDecimal;


/**
 *
 * @author pc
 */
public class Mngmem extends javax.swing.JFrame {

    /**
     * Creates new form ManageBooks
     */
    String username,email,address,phonenumber,subscription;
        int user_id,member_id;
    DefaultTableModel model;
    public Mngmem() {
        initComponents();
        setmemberDetails();
       

        
        
    }
    //set user details to table
    public void setmemberDetails(){
    try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/libsa", "root", "");
        java.sql.Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT member_id, username FROM members");

        while (rs.next()){
            int member_id = rs.getInt("member_id");
            String username = rs.getString("username");

            Object[] obj = { member_id, username };
            DefaultTableModel model = (DefaultTableModel) tbl_memberdetails.getModel();
            model.addRow(obj);
        }
    } catch (Exception e){
        e.printStackTrace();
    }
}

  
// Fetch user details from the normaluser table
private void fetchUserDetails(String username) {
    try {
        Connection con = DBConnection.getConnection();
        String sql = "SELECT username FROM normaluser WHERE username = ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, username);

        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            username = rs.getString("username");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}

        
   private boolean isUserExists(int userId) {
    boolean exists = false;
    try {
        Connection con = DBConnection.getConnection();
        String sql = "SELECT * FROM normaluser WHERE user_id = ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, userId);

        ResultSet rs = pst.executeQuery();
        exists = rs.next(); // Check if any row is returned
    } catch (Exception e) {
        e.printStackTrace();
    }
    return exists;
}

    //adding books
   public boolean addmembers() {
    boolean isadded = false;

    member_id = Integer.parseInt(txt_memberid.getText());
    String username = txt_username.getText();

    try {
        Connection con = DBConnection.getConnection();
        String sql = "insert into members (member_id, username) values (?, ?)"; 
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, member_id);
        pst.setString(2, username);

        int rowCount = pst.executeUpdate();
        if (rowCount > 0) {
            isadded = true;
            // Print receipt or any other action if needed
        } else {
            isadded = false;
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return isadded;
}
   

    private void printReceipt(String selectedSubscription) {
    // Implement the logic to print a receipt based on the selected subscription
    // You can use a switch statement or if-else conditions

    BigDecimal subscriptionPrice = getSubscriptionPrice(selectedSubscription);

    StringBuilder receipt = new StringBuilder();
    receipt.append("Receipt\n");
    receipt.append("----------------------------\n");
    receipt.append("Subscription: ").append(selectedSubscription).append("\n");
    receipt.append("Price: ").append(subscriptionPrice).append("\n");
    receipt.append("----------------------------\n");

    // Add more details to the receipt based on your requirements
    // For example, you can include user details, date, etc.

    receipt.append("User ID: ").append(user_id).append("\n");
    receipt.append("Member ID: ").append(member_id).append("\n");
    receipt.append("Username: ").append(username).append("\n");
    receipt.append("Email: ").append(email).append("\n");
    receipt.append("Phone Number: ").append(phonenumber).append("\n");
    receipt.append("Address: ").append(address).append("\n");

    // You can add more details as needed

    receipt.append("----------------------------\n");

    JOptionPane.showMessageDialog(this, receipt.toString(), "Receipt", JOptionPane.INFORMATION_MESSAGE);
}
    private BigDecimal getSubscriptionPrice(String selectedSubscription) {
    // Implement logic to retrieve subscription price based on the selectedSubscription
    // You can use a switch statement or if-else conditions

    BigDecimal price = BigDecimal.ZERO;

    switch (selectedSubscription) {
        case "Daily":
            price = new BigDecimal("80.00");
            break;
        case "Weekly":
            price = new BigDecimal("420.00");
            break;
        case "Monthly":
            price = new BigDecimal("2400.00");
            break;
        case "Annually":
            price = new BigDecimal("24000.00");
            break;
        // Add more cases if needed

        default:
            // Handle the case when an unknown subscription is selected
            // You can throw an exception, show an error message, or handle it as appropriate for your application
            break;
    }

    return price;
}


    //update users
    public boolean Updatemember() {
    boolean isUpdated = false;
    member_id = Integer.parseInt(txt_memberid.getText());
    String username = txt_username.getText();

    try {
        Connection con = DBConnection.getConnection();
        String sql = "UPDATE members SET username = ? WHERE member_id = ?";
        PreparedStatement pst = con.prepareStatement(sql);

        pst.setString(1, username);
        pst.setInt(2, member_id);

        int rowCount = pst.executeUpdate();

        if (rowCount > 0) {
            isUpdated = true;
            // Print receipt or any other action if needed
        } else {
            isUpdated = false;
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
    return isUpdated;
}

  public boolean deletemember() {
    boolean isDeleted = false;
    member_id = Integer.parseInt(txt_memberid.getText());
    try {
        Connection con = DBConnection.getConnection();
        String sql = "DELETE FROM members WHERE member_id = ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, member_id);
        int rowCount = pst.executeUpdate();
        if (rowCount > 0) {
            isDeleted = true;
        } else {
            isDeleted = false;
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return isDeleted;
}
    public void clearTablemembers() {
        DefaultTableModel model = (DefaultTableModel) tbl_memberdetails.getModel();
        model.setRowCount(0);
    }





    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rSMaterialButtonRectangleBeanInfo1 = new rojerusan.RSMaterialButtonRectangleBeanInfo();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_memberdetails = new rojerusan.RSTableMetro();
        jLabel3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txt_username = new app.bolivia.swing.JCTextField();
        jLabel6 = new javax.swing.JLabel();
        rSMaterialButtonRectangle1 = new rojerusan.RSMaterialButtonRectangle();
        rSMaterialButtonRectangle2 = new rojerusan.RSMaterialButtonRectangle();
        rSMaterialButtonRectangle3 = new rojerusan.RSMaterialButtonRectangle();
        jLabel10 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txt_memberid = new app.bolivia.swing.JCTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(102, 0, 102));

        jLabel2.setFont(new java.awt.Font("Segoe UI Semilight", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("X");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 10, 60, 40));

        tbl_memberdetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Memberid", "UserName"
            }
        ));
        tbl_memberdetails.setColorBackgoundHead(new java.awt.Color(255, 153, 0));
        tbl_memberdetails.setColorBordeHead(new java.awt.Color(102, 0, 102));
        tbl_memberdetails.setRowHeight(30);
        tbl_memberdetails.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_memberdetailsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_memberdetails);
        if (tbl_memberdetails.getColumnModel().getColumnCount() > 0) {
            tbl_memberdetails.getColumnModel().getColumn(0).setResizable(false);
        }

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 680, 340));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 153, 0));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Books_52px_1.png"))); // NOI18N
        jLabel3.setText("Manage Members");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, 270, -1));

        jPanel5.setBackground(new java.awt.Color(255, 153, 0));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 270, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 105, 270, 5));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 0, 680, 820));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jPanel1.setBackground(new java.awt.Color(102, 0, 102));
        jPanel1.setMinimumSize(new java.awt.Dimension(540, 510));
        jPanel1.setPreferredSize(new java.awt.Dimension(540, 900));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 102, 0));

        jLabel1.setFont(new java.awt.Font("Segoe UI Semilight", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Rewind_48px.png"))); // NOI18N
        jLabel1.setText("Back");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 110, 40));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Moleskine_26px.png"))); // NOI18N
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, -1, 30));

        txt_username.setBackground(new java.awt.Color(102, 0, 102));
        txt_username.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_username.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        txt_username.setPlaceholder("Enter User name ...");
        txt_username.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_usernameFocusLost(evt);
            }
        });
        jPanel1.add(txt_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 270, -1, -1));

        jLabel6.setFont(new java.awt.Font("Verdana Pro Cond", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Enter User Name");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 230, 150, 40));

        rSMaterialButtonRectangle1.setBackground(new java.awt.Color(255, 102, 0));
        rSMaterialButtonRectangle1.setText("Update");
        rSMaterialButtonRectangle1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle1ActionPerformed(evt);
            }
        });
        jPanel1.add(rSMaterialButtonRectangle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 410, 90, 60));

        rSMaterialButtonRectangle2.setBackground(new java.awt.Color(255, 102, 0));
        rSMaterialButtonRectangle2.setText("Delete");
        rSMaterialButtonRectangle2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle2ActionPerformed(evt);
            }
        });
        jPanel1.add(rSMaterialButtonRectangle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 410, 90, 60));

        rSMaterialButtonRectangle3.setBackground(new java.awt.Color(255, 102, 0));
        rSMaterialButtonRectangle3.setText("Add");
        rSMaterialButtonRectangle3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle3ActionPerformed(evt);
            }
        });
        jPanel1.add(rSMaterialButtonRectangle3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 410, 90, 60));

        jLabel10.setFont(new java.awt.Font("Verdana Pro Cond", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Enter Member id");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, 140, 40));

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Contact_26px.png"))); // NOI18N
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 40, -1));

        txt_memberid.setBackground(new java.awt.Color(102, 0, 102));
        txt_memberid.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_memberid.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        txt_memberid.setPlaceholder("Enter Member id ...");
        txt_memberid.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_memberidFocusLost(evt);
            }
        });
        jPanel1.add(txt_memberid, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 130, -1, -1));

        jScrollPane1.setViewportView(jPanel1);
        jPanel1.getAccessibleContext().setAccessibleName("");

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, 530));

        setSize(new java.awt.Dimension(1181, 1050));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        System.exit(0);// TODO add your handling code here:
    }//GEN-LAST:event_jLabel2MouseClicked

    private void tbl_memberdetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_memberdetailsMouseClicked
       int rowNo = tbl_memberdetails.getSelectedRow();// TODO add your handling code here:
       TableModel model = tbl_memberdetails.getModel();
       txt_memberid.setText(model.getValueAt(rowNo,0).toString());
      
       txt_username.setText(model.getValueAt(rowNo,1).toString());
       
    }//GEN-LAST:event_tbl_memberdetailsMouseClicked

    private void rSMaterialButtonRectangle2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle2ActionPerformed
        if(deletemember()==true){
            JOptionPane.showMessageDialog(this,"Member record Deleted");
            clearTablemembers();
            setmemberDetails();

        }else{
            JOptionPane.showMessageDialog(this,"Member record Deletion Failed");
        }
    }//GEN-LAST:event_rSMaterialButtonRectangle2ActionPerformed

    private void rSMaterialButtonRectangle1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle1ActionPerformed
        if(Updatemember()==true){
            JOptionPane.showMessageDialog(this,"Member record Updated");
            clearTablemembers();
            setmemberDetails();

        }else{
            JOptionPane.showMessageDialog(this,"Member record Update Failed");
        }// TODO add your handling code here:
    }//GEN-LAST:event_rSMaterialButtonRectangle1ActionPerformed

    private void rSMaterialButtonRectangle3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle3ActionPerformed

        if(addmembers()==true){
            JOptionPane.showMessageDialog(this,"Member Added");
            clearTablemembers();
            setmemberDetails();

        }else{
            JOptionPane.showMessageDialog(this,"Member Addition Failed");
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_rSMaterialButtonRectangle3ActionPerformed

    private void txt_usernameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_usernameFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_usernameFocusLost

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        HomePage home = new HomePage();
        home.setVisible(true);
        dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1MouseClicked

    private void txt_memberidFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_memberidFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_memberidFocusLost

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
            java.util.logging.Logger.getLogger(Mngmem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Mngmem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Mngmem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Mngmem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Mngmem().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle1;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle2;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle3;
    private rojerusan.RSMaterialButtonRectangleBeanInfo rSMaterialButtonRectangleBeanInfo1;
    private rojerusan.RSTableMetro tbl_memberdetails;
    private app.bolivia.swing.JCTextField txt_memberid;
    private app.bolivia.swing.JCTextField txt_username;
    // End of variables declaration//GEN-END:variables
}
