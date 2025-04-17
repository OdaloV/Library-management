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


/**
 *
 * @author pc
 */
public class Manageusers extends javax.swing.JFrame {

    /**
     * Creates new form ManageBooks
     */
    String username,email,address,phonenumber;
        int user_id;
    DefaultTableModel model;
    public Manageusers() {
        initComponents();
        setuserDetails();
        
    }
    //set user details to table
    public void setuserDetails(){
        try{
             Class.forName("com.mysql.cj.jdbc.Driver");
             Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/libsa","root","");
            java.sql.Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT user_id, username,email,phonenumber ,address FROM users;");

            while (rs.next()){
                int user_id=rs.getInt("user_id");
                String username=rs.getString("username");
                String email=rs.getString("email");
                String phonenumber=rs.getString("phonenumber");
                String address=rs.getString("address");
                
                Object[] obj={ user_id,username,email,phonenumber,address   
                };
                model =(DefaultTableModel) tbl_userdetails.getModel();
                model.addRow(obj);
            }
        }catch (Exception e){
            e.printStackTrace();
            
        }
        
    }
    //adding books
    public boolean addusers(){
        boolean  isadded = false ;
        user_id=Integer.parseInt(txt_userid.getText());
        username= txt_username.getText();
        email= txt_email.getText();
        phonenumber= txt_phonenumber.getText();
        address= txt_address.getText();
        if (!isValidEmail(email)) {
        JOptionPane.showMessageDialog(this, "Please enter a valid email address");
        return false;
    }

    if (!isValidPhoneNumber(phonenumber)) {
        JOptionPane.showMessageDialog(this, "Please enter a valid 10-digit phone number starting with '07' or '01'");
        return false;
    }
        
        try{
        
            
             Connection con = DBConnection.getConnection();
             String sql ="insert into users values(?,?,?,?,?) ";
             PreparedStatement pst =con.prepareStatement(sql);
             pst.setInt(1, user_id);
             pst.setString(2,username);
             pst.setString(3,email);
             pst.setString(4, phonenumber);
             pst.setString(5,address);
             
             int rowCount=pst.executeUpdate();
             if (rowCount>0){
                 isadded = true;
             } else {
                 isadded=false;
             }
            
        }catch(Exception e){
            e.printStackTrace();
            
        }
        return isadded;
    }
    //update users
    public boolean Updateusers() {
    boolean isUpdated = false;
    user_id = Integer.parseInt(txt_userid.getText());
    username = txt_username.getText();
    email = txt_email.getText();
    phonenumber = txt_phonenumber.getText();
    address = txt_address.getText();
    if (!isValidEmail(email)) {
        JOptionPane.showMessageDialog(this, "Please enter a valid email address");
        return false;
    }

    if (!isValidPhoneNumber(phonenumber)) {
        JOptionPane.showMessageDialog(this, "Please enter a valid 10-digit phone number starting with '07' or '01'");
        return false;
    }

    

    try {
        Connection con = DBConnection.getConnection();
        String sql = "UPDATE users SET username=?, email=?, address=?, phonenumber=? WHERE user_id=?";
        PreparedStatement pst = con.prepareStatement(sql);

        pst.setString(1, username);
        pst.setString(2, email);
        pst.setString(3, address);
        pst.setString(4, phonenumber);
        pst.setInt(5, user_id);

        int rowCount = pst.executeUpdate();

        if (rowCount > 0) {
            isUpdated = true;
        } else {
            isUpdated = false;
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
    return isUpdated;
}
    //Delete user
    public boolean deleteuser() {
     boolean isDeleted=false;
     user_id=Integer.parseInt(txt_userid.getText());
     try{
         Connection con = DBConnection.getConnection();
        String sql = "delete from users WHERE user_id=?";
        PreparedStatement pst = con.prepareStatement(sql);
         pst.setInt(1,user_id);
         int rowCount= pst.executeUpdate();
         if(rowCount>0){
             isDeleted=true;
         }else{
             isDeleted=false;
         }
     }catch (Exception e){
         e.printStackTrace();
         
     }
     return isDeleted;
    }
    //clear table
    public void clearTableuser(){
        DefaultTableModel model=(DefaultTableModel)tbl_userdetails.getModel();
        model.setRowCount(0);
    }
    private boolean isValidEmail(String email) {
    // Use a simple regex pattern for basic email validation
    return email.matches("^.+@.+\\..+$");
}

private boolean isValidPhoneNumber(String phoneNumber) {
    // Check if the phone number is exactly 10 digits and starts with "07" or "01"
    return phoneNumber.matches("^(07|01)\\d{8}$");
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
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txt_userid = new app.bolivia.swing.JCTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txt_username = new app.bolivia.swing.JCTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txt_email = new app.bolivia.swing.JCTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txt_address = new app.bolivia.swing.JCTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txt_phonenumber = new app.bolivia.swing.JCTextField();
        jLabel9 = new javax.swing.JLabel();
        rSMaterialButtonRectangle1 = new rojerusan.RSMaterialButtonRectangle();
        rSMaterialButtonRectangle2 = new rojerusan.RSMaterialButtonRectangle();
        rSMaterialButtonRectangle3 = new rojerusan.RSMaterialButtonRectangle();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_userdetails = new rojerusan.RSTableMetro();
        jLabel3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(102, 0, 102));
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

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Contact_26px.png"))); // NOI18N
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 40, -1));

        txt_userid.setBackground(new java.awt.Color(102, 0, 102));
        txt_userid.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_userid.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        txt_userid.setPlaceholder("Enter User id ...");
        txt_userid.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_useridFocusLost(evt);
            }
        });
        jPanel1.add(txt_userid, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, -1, -1));

        jLabel5.setFont(new java.awt.Font("Verdana Pro Cond", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Enter User id");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, 120, 40));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Moleskine_26px.png"))); // NOI18N
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 206, -1, 30));

        txt_username.setBackground(new java.awt.Color(102, 0, 102));
        txt_username.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_username.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        txt_username.setPlaceholder("Enter User name ...");
        txt_username.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_usernameFocusLost(evt);
            }
        });
        jPanel1.add(txt_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 230, -1, -1));

        jLabel6.setFont(new java.awt.Font("Verdana Pro Cond", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Enter User Name");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 190, 150, 40));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons/icons8_Account_50px.png"))); // NOI18N
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, -1, -1));

        txt_email.setBackground(new java.awt.Color(102, 0, 102));
        txt_email.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_email.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        txt_email.setPlaceholder("Enter email ...");
        txt_email.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_emailFocusLost(evt);
            }
        });
        jPanel1.add(txt_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 320, -1, -1));

        jLabel7.setFont(new java.awt.Font("Verdana Pro Cond", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Email");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 280, 110, 40));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Unit_26px.png"))); // NOI18N
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 510, 40, 50));

        txt_address.setBackground(new java.awt.Color(102, 0, 102));
        txt_address.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_address.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        txt_address.setPlaceholder("Enter  Address");
        txt_address.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_addressFocusLost(evt);
            }
        });
        txt_address.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_addressActionPerformed(evt);
            }
        });
        jPanel1.add(txt_address, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 540, -1, 30));

        jLabel8.setFont(new java.awt.Font("Verdana Pro Cond", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Address");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 500, 100, 30));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Moleskine_26px.png"))); // NOI18N
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, -1, -1));

        txt_phonenumber.setBackground(new java.awt.Color(102, 0, 102));
        txt_phonenumber.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_phonenumber.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        txt_phonenumber.setPlaceholder("Enter phone number");
        txt_phonenumber.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_phonenumberFocusLost(evt);
            }
        });
        jPanel1.add(txt_phonenumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 420, -1, -1));

        jLabel9.setFont(new java.awt.Font("Verdana Pro Cond", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Phone Number");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 380, 120, 40));

        rSMaterialButtonRectangle1.setBackground(new java.awt.Color(255, 102, 0));
        rSMaterialButtonRectangle1.setText("Update");
        rSMaterialButtonRectangle1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle1ActionPerformed(evt);
            }
        });
        jPanel1.add(rSMaterialButtonRectangle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 670, 90, 60));

        rSMaterialButtonRectangle2.setBackground(new java.awt.Color(255, 102, 0));
        rSMaterialButtonRectangle2.setText("Delete");
        rSMaterialButtonRectangle2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle2ActionPerformed(evt);
            }
        });
        jPanel1.add(rSMaterialButtonRectangle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 670, 90, 60));

        rSMaterialButtonRectangle3.setBackground(new java.awt.Color(255, 102, 0));
        rSMaterialButtonRectangle3.setText("Add");
        rSMaterialButtonRectangle3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle3ActionPerformed(evt);
            }
        });
        jPanel1.add(rSMaterialButtonRectangle3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 670, 90, 60));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 490, 830));
        jPanel1.getAccessibleContext().setAccessibleName("");

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

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 0, 60, 40));

        tbl_userdetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "UserId", "UserName", "Email", "Phone No", "Address"
            }
        ));
        tbl_userdetails.setColorBackgoundHead(new java.awt.Color(255, 153, 0));
        tbl_userdetails.setColorBordeHead(new java.awt.Color(102, 0, 102));
        tbl_userdetails.setRowHeight(30);
        tbl_userdetails.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_userdetailsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_userdetails);
        if (tbl_userdetails.getColumnModel().getColumnCount() > 0) {
            tbl_userdetails.getColumnModel().getColumn(0).setResizable(false);
        }

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 530, 340));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 153, 0));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Books_52px_1.png"))); // NOI18N
        jLabel3.setText("Manage Users");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 220, -1));

        jPanel5.setBackground(new java.awt.Color(255, 153, 0));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 270, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 105, 270, 5));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 0, 540, 820));

        setSize(new java.awt.Dimension(1035, 824));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
       HomePage home = new HomePage();
       home.setVisible(true);
       dispose();
// TODO add your handling code here:
    }//GEN-LAST:event_jLabel1MouseClicked

    private void txt_useridFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_useridFocusLost

        // TODO add your handling code here:
    }//GEN-LAST:event_txt_useridFocusLost

    private void txt_usernameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_usernameFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_usernameFocusLost

    private void txt_emailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_emailFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_emailFocusLost

    private void txt_addressFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_addressFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_addressFocusLost

    private void txt_addressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_addressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_addressActionPerformed

    private void txt_phonenumberFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_phonenumberFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_phonenumberFocusLost

    private void rSMaterialButtonRectangle3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle3ActionPerformed
    
        if(addusers()==true){
            JOptionPane.showMessageDialog(this,"User Added");
            clearTableuser();
            setuserDetails();
   
        }else{
            JOptionPane.showMessageDialog(this,"User Addition Failed");
        }
// TODO add your handling code here:
    }//GEN-LAST:event_rSMaterialButtonRectangle3ActionPerformed

    private void rSMaterialButtonRectangle2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle2ActionPerformed
        if(deleteuser()==true){
          JOptionPane.showMessageDialog(this,"User record Deleted");
          clearTableuser();
        setuserDetails();
        
        }else{
            JOptionPane.showMessageDialog(this,"User ecord Deletion Failed");
        }
    }//GEN-LAST:event_rSMaterialButtonRectangle2ActionPerformed

    private void rSMaterialButtonRectangle1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle1ActionPerformed
        if(Updateusers()==true){
            JOptionPane.showMessageDialog(this,"User record Updated");
            clearTableuser();
            setuserDetails();
   
        }else{
            JOptionPane.showMessageDialog(this,"User record Update Failed");
        }// TODO add your handling code here:
    }//GEN-LAST:event_rSMaterialButtonRectangle1ActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        System.exit(0);// TODO add your handling code here:
    }//GEN-LAST:event_jLabel2MouseClicked

    private void tbl_userdetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_userdetailsMouseClicked
       int rowNo = tbl_userdetails.getSelectedRow();// TODO add your handling code here:
       TableModel model = tbl_userdetails.getModel();
       txt_userid.setText(model.getValueAt(rowNo,0).toString());
       txt_username.setText(model.getValueAt(rowNo,1).toString());
       txt_email.setText(model.getValueAt(rowNo,2).toString());
       txt_phonenumber.setText(model.getValueAt(rowNo,3).toString());
       txt_address.setText(model.getValueAt(rowNo,4).toString());
    }//GEN-LAST:event_tbl_userdetailsMouseClicked

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
            java.util.logging.Logger.getLogger(Manageusers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Manageusers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Manageusers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Manageusers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Manageusers().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle1;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle2;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle3;
    private rojerusan.RSMaterialButtonRectangleBeanInfo rSMaterialButtonRectangleBeanInfo1;
    private rojerusan.RSTableMetro tbl_userdetails;
    private app.bolivia.swing.JCTextField txt_address;
    private app.bolivia.swing.JCTextField txt_email;
    private app.bolivia.swing.JCTextField txt_phonenumber;
    private app.bolivia.swing.JCTextField txt_userid;
    private app.bolivia.swing.JCTextField txt_username;
    // End of variables declaration//GEN-END:variables
}
