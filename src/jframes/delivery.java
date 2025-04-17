/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package jframes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.Date;
import javax.swing.JOptionPane;



/**
 *
 * @author pc
 */
public class delivery extends javax.swing.JFrame {

   

   

    /**
     * Creates new form issue books
     */
    public delivery() {
        initComponents();
    }
    public void getBookDetails() {
    int bookid = Integer.parseInt(txt_bookid.getText());
    try {
         Class.forName("com.mysql.cj.jdbc.Driver");
             Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/libsa","root","");
            java.sql.Statement st = con.createStatement();
        PreparedStatement pst = con.prepareStatement("SELECT * FROM book_details WHERE bookid = ?");
        pst.setInt(1, bookid);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            String bookname = rs.getString("bookname");
            String authorname = rs.getString("authorname");
            String categoryname = rs.getString("categoryname");
            int quantity = rs.getInt("quantity");

            // Set the label texts
            lbl_bookid.setText(String.valueOf(bookid));
            lbl_bookname.setText(bookname);
            lbl_author.setText(authorname);
            lbl_category.setText(categoryname);
            lbl_quantity.setText(String.valueOf(quantity));
        }else{
            lbl_bookerror.setText("invalid book id");
            
             
        }
    } catch (Exception e) {
        e.printStackTrace(); // Handle exceptions properly in your application
    }
}
  public void getuserDetails() {
    int member_id = Integer.parseInt(txt_memberid.getText());
    try {
         Class.forName("com.mysql.cj.jdbc.Driver");
             Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/libsa","root","");
            java.sql.Statement st = con.createStatement();
        PreparedStatement pst = con.prepareStatement("SELECT * FROM members WHERE member_id = ?");
        pst.setInt(1, member_id);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            String username = rs.getString("username");
            String email = rs.getString("email");
            String phonenumber = rs.getString("phonenumber");
            String address = rs.getString("address");

            // Set the label texts
            lbl_memberid.setText(String.valueOf(member_id));
            lbl_username.setText(username);
            lbl_email.setText(email);
            lbl_phonenumber.setText(phonenumber);
            lbl_address.setText(address);
        } else{
            lbl_usererror.setText("invalid User id");
        }
    } catch (Exception e) {
        e.printStackTrace(); // Handle exceptions properly in your application
    }
}
  public boolean issuebook() {
    boolean isIssued = false;

    int bookid = Integer.parseInt(txt_bookid.getText());
    int member_id = Integer.parseInt(txt_memberid.getText());
    String bookname = lbl_bookname.getText();
    String username = lbl_username.getText();

    try {
        Connection con = DBConnection.getConnection();
        // Check if the book is already allocated and pending
        if (duplicated()) {
            JOptionPane.showMessageDialog(this, "Book is already delivered to the user.", "Already Delivered", JOptionPane.WARNING_MESSAGE);
            return false; // Book is already delivered, no need to proceed
        }

        String sql = "INSERT INTO deliveries (member_id, username, book_id, bookname, delivery_status) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pst = con.prepareStatement(sql);

        
        pst.setInt(1, member_id);
        pst.setString(2, username);
        pst.setInt(3, bookid);
        pst.setString(4, bookname);
        pst.setString(5, "pending");

        int rowCount = pst.executeUpdate();

        if (rowCount > 0) {
            isIssued = true;
        } else {
            isIssued = false;
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
    return isIssued;
}

  
  public boolean duplicated(){
      boolean isallocated=false;
        int bookid=Integer.parseInt(txt_bookid.getText());
      int user_id= Integer.parseInt(txt_memberid.getText());
      try{
          Connection con = DBConnection.getConnection();
          String sql ="SELECT* FROM issue_books where book_id=? and user_id=? and status=?";
          PreparedStatement pst = con.prepareStatement(sql);
          pst.setInt(1,bookid);
          pst.setInt(2,user_id);
          pst.setString(3,"pending");
          ResultSet rs = pst.executeQuery();
          
          if (rs.next()) {
              isallocated=true;
        
    } else {
              isallocated=false;
        
    }

      }catch (Exception e){
          e.printStackTrace();
          
      }
      return isallocated;
  }
  

 

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainpanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lbl_usererror = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lbl_address = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lbl_username = new javax.swing.JLabel();
        lbl_phonenumber = new javax.swing.JLabel();
        lbl_email = new javax.swing.JLabel();
        lbl_memberid = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        lbl_bookerror = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        lbl_quantity = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        lbl_bookname = new javax.swing.JLabel();
        lbl_category = new javax.swing.JLabel();
        lbl_author = new javax.swing.JLabel();
        lbl_bookid = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        txt_bookid = new app.bolivia.swing.JCTextField();
        txt_memberid = new app.bolivia.swing.JCTextField();
        jLabel29 = new javax.swing.JLabel();
        rSMaterialButtonRectangle4 = new rojerusan.RSMaterialButtonRectangle();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        mainpanel.setBackground(new java.awt.Color(255, 255, 255));
        mainpanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 51, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 134, 320, 4));
        jPanel3.getAccessibleContext().setAccessibleName("");

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("User Name");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 110, -1));

        lbl_usererror.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        lbl_usererror.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lbl_usererror, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 620, 270, 30));

        jLabel5.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Phone Number");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 450, 130, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Email");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 70, -1));

        lbl_address.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        lbl_address.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lbl_address, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 540, 220, 30));

        jLabel8.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Member id");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 100, -1));

        lbl_username.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        lbl_username.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lbl_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 260, 200, 30));

        lbl_phonenumber.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        lbl_phonenumber.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lbl_phonenumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 440, 210, 30));

        lbl_email.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        lbl_email.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lbl_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 350, 200, 30));

        lbl_memberid.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        lbl_memberid.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lbl_memberid, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 190, 210, 30));

        jLabel10.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/adminIcons/male_user_50px.png"))); // NOI18N
        jLabel10.setText("MEMBER DETAILS");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 320, 80));

        jLabel7.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Address");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 550, 90, -1));

        mainpanel.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 0, 370, 790));

        jPanel4.setBackground(new java.awt.Color(102, 0, 102));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 44, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 110, 40));

        jLabel14.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Literature_100px_1.png"))); // NOI18N
        jLabel14.setText("BOOK DETAILS");
        jPanel4.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 320, 80));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel4.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 320, 4));

        jLabel15.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Book Name");
        jPanel4.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 110, -1));

        lbl_bookerror.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        lbl_bookerror.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.add(lbl_bookerror, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 620, 260, 30));

        jLabel17.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Category");
        jPanel4.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 470, 90, -1));

        jLabel18.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Author");
        jPanel4.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 70, -1));

        lbl_quantity.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        lbl_quantity.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.add(lbl_quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 550, 220, 30));

        jLabel20.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Book id");
        jPanel4.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 70, -1));

        lbl_bookname.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        lbl_bookname.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.add(lbl_bookname, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 280, 200, 30));

        lbl_category.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        lbl_category.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.add(lbl_category, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 460, 210, 30));

        lbl_author.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        lbl_author.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.add(lbl_author, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 370, 200, 30));

        lbl_bookid.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        lbl_bookid.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.add(lbl_bookid, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 190, 210, 30));

        jLabel21.setFont(new java.awt.Font("Segoe UI Semibold", 1, 30)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("   X");
        jLabel21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel21MouseClicked(evt);
            }
        });
        jPanel4.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 10, 60, 50));

        jLabel19.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Quantity");
        jPanel4.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 550, 90, -1));

        mainpanel.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 360, 790));

        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 102, 0));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Book_50px_1.png"))); // NOI18N
        jLabel2.setText("ISSUE BOOK");
        mainpanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 100, 320, 60));

        jPanel2.setBackground(new java.awt.Color(255, 102, 0));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        mainpanel.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 160, 330, 4));

        jPanel13.setBackground(new java.awt.Color(102, 0, 102));

        jLabel27.setFont(new java.awt.Font("Segoe UI Semilight", 1, 24)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("X");
        jLabel27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel27MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        mainpanel.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 10, 60, 40));

        jLabel24.setFont(new java.awt.Font("Verdana Pro Cond", 0, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 51, 0));
        jLabel24.setText("Enter Book id");
        mainpanel.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 200, 120, 40));

        txt_bookid.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_bookid.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        txt_bookid.setPlaceholder("Enter Book id ...");
        txt_bookid.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_bookidFocusLost(evt);
            }
        });
        txt_bookid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_bookidActionPerformed(evt);
            }
        });
        mainpanel.add(txt_bookid, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 200, -1, -1));

        txt_memberid.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_memberid.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        txt_memberid.setPlaceholder("Enter User id ...");
        txt_memberid.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_memberidFocusLost(evt);
            }
        });
        txt_memberid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_memberidActionPerformed(evt);
            }
        });
        mainpanel.add(txt_memberid, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 280, 200, 60));

        jLabel29.setFont(new java.awt.Font("Verdana Pro Cond", 0, 18)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 102, 0));
        jLabel29.setText("Enter Member id");
        mainpanel.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 290, 140, 40));

        rSMaterialButtonRectangle4.setBackground(new java.awt.Color(255, 102, 0));
        rSMaterialButtonRectangle4.setText("Issue Book");
        rSMaterialButtonRectangle4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle4ActionPerformed(evt);
            }
        });
        mainpanel.add(rSMaterialButtonRectangle4, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 480, 320, 60));

        getContentPane().add(mainpanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 1130, 790));

        setSize(new java.awt.Dimension(1157, 811));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel27MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel27MouseClicked
        System.exit(0);// TODO add your handling code here:
    }//GEN-LAST:event_jLabel27MouseClicked

    private void txt_bookidFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_bookidFocusLost
        if(!txt_bookid.getText().equals("")){
            getBookDetails(); 
        }
       
       
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_bookidFocusLost

    private void txt_memberidFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_memberidFocusLost

        if(!txt_memberid.getText().equals("")){
            getuserDetails(); }// TODO add your handling code here:
    }//GEN-LAST:event_txt_memberidFocusLost

    private void txt_memberidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_memberidActionPerformed
         
    }//GEN-LAST:event_txt_memberidActionPerformed

    private void txt_bookidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_bookidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_bookidActionPerformed

    private void rSMaterialButtonRectangle4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle4ActionPerformed
      if(lbl_quantity.getText().equals("0")){
         JOptionPane.showMessageDialog(this,"book is not available");  
      }else{
          if( duplicated()== false){
            if( issuebook()== true){
           JOptionPane.showMessageDialog(this,"book issued successfully"); 
          
           
       }else{
           JOptionPane.showMessageDialog(this,"can't issue book"); 
       }

           
       }else{
           JOptionPane.showMessageDialog(this,"book already allocated"); 
           
       }
          
      }
        
        // TODO add your handling code here:
    }//GEN-LAST:event_rSMaterialButtonRectangle4ActionPerformed

    private void jLabel21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel21MouseClicked
        System.exit(0);      // TODO add your handling code here:
    }//GEN-LAST:event_jLabel21MouseClicked

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
       HomePage home = new HomePage();
       home.setVisible(true);
       dispose();  // TODO add your handling code here:
    }//GEN-LAST:event_jLabel13MouseClicked

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
            java.util.logging.Logger.getLogger(delivery.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(delivery.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(delivery.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(delivery.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new delivery().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JLabel lbl_address;
    private javax.swing.JLabel lbl_author;
    private javax.swing.JLabel lbl_bookerror;
    private javax.swing.JLabel lbl_bookid;
    private javax.swing.JLabel lbl_bookname;
    private javax.swing.JLabel lbl_category;
    private javax.swing.JLabel lbl_email;
    private javax.swing.JLabel lbl_memberid;
    private javax.swing.JLabel lbl_phonenumber;
    private javax.swing.JLabel lbl_quantity;
    private javax.swing.JLabel lbl_usererror;
    private javax.swing.JLabel lbl_username;
    private javax.swing.JPanel mainpanel;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle4;
    private app.bolivia.swing.JCTextField txt_bookid;
    private app.bolivia.swing.JCTextField txt_memberid;
    // End of variables declaration//GEN-END:variables
}
