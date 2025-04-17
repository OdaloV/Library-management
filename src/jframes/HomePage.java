/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

package jframes;
import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.Statement;





/**
 *
 * @author pc
 */
public class HomePage extends javax.swing.JFrame {

    Color mouseEnterColor = new Color(0,0,0);
    Color mouseExitColor = new Color (51,0,51);
    /** Creates new form HomePage */
    DefaultTableModel model;
    public HomePage() {
        
        initComponents();
        showPieChart();
        setuserDetails();
         setDetails();
         setdatatocards();
        
    }
    public void setdatatocards(){
        Statement st = null;
        ResultSet rs= null;
        long currentTime = System.currentTimeMillis();
        java.util.Date todaysDate = new java.util.Date(currentTime);
// Convert the java.util.Date object to a java.sql.Date object
        java.sql.Date sqlDate = new java.sql.Date(todaysDate.getTime());
        try{
             Class.forName("com.mysql.cj.jdbc.Driver");
             Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/libsa","root","");
             st=(Statement) con.createStatement();
             rs  = st.executeQuery("SELECT * FROM bookdetail");
             rs.last();
             lbl_booknumber.setText(Integer.toString(rs.getRow()));
             rs= st.executeQuery("SELECT * FROM normaluser");
             rs.last();
             lbl_usersnumber.setText(Integer.toString(rs.getRow()));
             rs= st.executeQuery("SELECT * FROM issue_books where status = 'pending'");
             rs.last();
             lbl_issuedbooksno.setText(Integer.toString(rs.getRow()));
             rs= st.executeQuery("SELECT * FROM issue_books where due_date < '"+sqlDate+"' and status = 'pending'");
             rs.last();
             lbl_dafaultersno.setText(Integer.toString(rs.getRow()));
             
        }catch (Exception e){
            
            e.printStackTrace();
            
}
    }

    public void showPieChart(){
        
        //create dataset
      DefaultPieDataset barDataset = new DefaultPieDataset( );
      try{
          Connection con = DBConnection.getConnection();
          String sql = "SELECT bookname,count(*)as issue_count from issue_books group by book_id";
          Statement st = con.createStatement();
          ResultSet rs = st.executeQuery(sql);
          while(rs.next()){
              barDataset.setValue(rs.getString("bookname"), Double.valueOf(rs.getDouble("issue_count"))); 
          }
      }catch(Exception e){
          e.printStackTrace();
          
      }
          
       
      
      //create chart
       JFreeChart piechart = ChartFactory.createPieChart("Issue books",barDataset, false,true,false);//explain
      
        PiePlot piePlot =(PiePlot) piechart.getPlot();
      
       //changing pie chart blocks colors
       //piePlot.setSectionPaint("IPhone 5s", new Color(255,255,102));
        //piePlot.setSectionPaint("SamSung Grand", new Color(102,255,102));
        //piePlot.setSectionPaint("MotoG", new Color(255,102,153));
        //piePlot.setSectionPaint("Nokia Lumia", new Color(0,204,204));
      
       
        piePlot.setBackgroundPaint(Color.white);
        
        //create chartPanel to display chart(graph)
        ChartPanel barChartPanel = new ChartPanel(piechart);
        PanelPi.removeAll();
        PanelPi.add(barChartPanel, BorderLayout.CENTER);
        PanelPi.validate();
    }
    public void setuserDetails(){
        try{
             Class.forName("com.mysql.cj.jdbc.Driver");
             Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/libsa","root","");
            java.sql.Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM normaluser");

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
    public void setDetails(){
        try{
             Class.forName("com.mysql.cj.jdbc.Driver");
             Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/libsa","root","");
            java.sql.Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM BOOKDETAIL");

            while (rs.next()){
                int bookid=rs.getInt("id");
                String bookname=rs.getString("title");
                String authorname=rs.getString("author");
                String publishername=rs.getString("publisher");
                int quantity=rs.getInt("quantity");
                long isbn = rs.getLong("isbn");
                Object[] obj={ bookid,bookname,authorname,publishername,quantity,isbn   
                };
                model =(DefaultTableModel) tbl_bookdetails.getModel();
                model.addRow(obj);
            }
        }catch (Exception e){
            e.printStackTrace();
            
        }
        
    }
    
   
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel16 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        lbl_booknumber = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        lbl_usersnumber = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        lbl_dafaultersno = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        lbl_issuedbooksno = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_userdetails = new rojerusan.RSTableMetro();
        jLabel38 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_bookdetails = new rojerusan.RSTableMetro();
        jLabel39 = new javax.swing.JLabel();
        PanelPi = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel16.setBackground(new java.awt.Color(102, 0, 102));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel19.setFont(new java.awt.Font("Segoe UI Semibold", 1, 36)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("ELDORET PUBLIC LIBRARY");
        jPanel16.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 450, 70));

        jLabel20.setFont(new java.awt.Font("Segoe UI Semibold", 1, 36)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("ADMIN PAGE");
        jPanel16.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 20, 230, 60));

        jPanel17.setBackground(new java.awt.Color(102, 0, 102));
        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel42.setFont(new java.awt.Font("Segoe UI Semilight", 1, 24)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(255, 255, 255));
        jLabel42.setText("X");
        jLabel42.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel42MouseClicked(evt);
            }
        });
        jPanel17.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel16.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(1280, 20, 60, 40));

        getContentPane().add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1380, 80));

        jPanel14.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(255, 102, 0)));
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel18.setText("Users");
        jPanel14.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 136, 70, 30));

        lbl_booknumber.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        lbl_booknumber.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/adminIcons/icons8_Book_Shelf_50px.png"))); // NOI18N
        lbl_booknumber.setText("20");
        jPanel14.add(lbl_booknumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 100, 50));

        getContentPane().add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 140, 180, 100));

        jLabel23.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel23.setText("No.of Books");
        getContentPane().add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 110, 120, 30));

        jPanel15.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(255, 102, 0)));
        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel24.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel24.setText("Users");
        jPanel15.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 136, 70, 30));

        lbl_usersnumber.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        lbl_usersnumber.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/adminIcons/icons8_Conference_26px.png"))); // NOI18N
        lbl_usersnumber.setText("20");
        jPanel15.add(lbl_usersnumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 80, 60));

        getContentPane().add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 140, 180, 100));

        jLabel26.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel26.setText("Users");
        getContentPane().add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 100, 70, 30));

        jPanel21.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(255, 102, 0)));
        jPanel21.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel29.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel29.setText("Users");
        jPanel21.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 136, 70, 30));

        jLabel30.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/adminIcons/icons8_Book_Shelf_50px.png"))); // NOI18N
        jLabel30.setText("20");
        jPanel21.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 100, 50));

        getContentPane().add(jPanel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 170, 180, 100));

        jPanel22.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(255, 102, 0)));
        jPanel22.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel32.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel32.setText("Users");
        jPanel22.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 136, 70, 30));

        lbl_dafaultersno.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        lbl_dafaultersno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/adminIcons/icons8_Conference_26px.png"))); // NOI18N
        lbl_dafaultersno.setText("20");
        jPanel22.add(lbl_dafaultersno, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 80, 60));

        getContentPane().add(jPanel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 130, 180, 100));

        jPanel23.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(255, 102, 0)));
        jPanel23.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel34.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel34.setText("Users");
        jPanel23.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 136, 70, 30));

        lbl_issuedbooksno.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        lbl_issuedbooksno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/adminIcons/icons8_Book_Shelf_50px.png"))); // NOI18N
        lbl_issuedbooksno.setText("20");
        jPanel23.add(lbl_issuedbooksno, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 100, 50));

        getContentPane().add(jPanel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 130, 180, 100));

        jLabel36.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel36.setText("Defaulters");
        getContentPane().add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 100, 120, 30));

        jLabel37.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel37.setText("Issued Books");
        getContentPane().add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 100, 120, 30));

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

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 300, 490, 160));

        jLabel38.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel38.setText("Book Details");
        getContentPane().add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 480, 120, 30));

        tbl_bookdetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "BookId", "BookName", "Author", "Publisher", "Quanity", "ISBN"
            }
        ));
        tbl_bookdetails.setColorBackgoundHead(new java.awt.Color(255, 153, 0));
        tbl_bookdetails.setColorBordeHead(new java.awt.Color(102, 0, 102));
        tbl_bookdetails.setRowHeight(30);
        tbl_bookdetails.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_bookdetailsMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbl_bookdetails);
        if (tbl_bookdetails.getColumnModel().getColumnCount() > 0) {
            tbl_bookdetails.getColumnModel().getColumn(3).setResizable(false);
            tbl_bookdetails.getColumnModel().getColumn(5).setResizable(false);
        }

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 510, 490, 200));

        jLabel39.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel39.setText("User Details");
        getContentPane().add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 270, 120, 30));

        PanelPi.setLayout(new java.awt.BorderLayout());
        getContentPane().add(PanelPi, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 310, 450, 360));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jPanel2.setBackground(new java.awt.Color(102, 0, 102));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(102, 0, 102));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Segoe UI Semibold", 1, 20)); // NOI18N
        jLabel5.setText("DASHBOARD");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 160, -1));

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 290, 70));

        jPanel5.setBackground(new java.awt.Color(102, 0, 102));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Segoe UI Semibold", 1, 20)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/adminIcons/home_24px.png"))); // NOI18N
        jLabel7.setText("Manage Members");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });
        jPanel5.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 220, -1));

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 290, 70));

        jPanel6.setBackground(new java.awt.Color(102, 0, 102));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Segoe UI Semibold", 1, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/adminIcons/home_24px.png"))); // NOI18N
        jLabel6.setText("Manage Users");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        jPanel6.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 160, -1));

        jPanel2.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 290, 70));

        jPanel10.setBackground(new java.awt.Color(255, 102, 0));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Segoe UI Semibold", 1, 20)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/adminIcons/home_24px.png"))); // NOI18N
        jLabel11.setText("Home Page");
        jPanel10.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 160, -1));

        jPanel2.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 290, 70));

        jPanel7.setBackground(new java.awt.Color(102, 0, 102));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel7.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-50, 90, 1070, 60));

        jLabel17.setFont(new java.awt.Font("Segoe UI Semibold", 1, 20)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/adminIcons/home_24px.png"))); // NOI18N
        jLabel17.setText("Manage Books");
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel17MouseClicked(evt);
            }
        });
        jPanel7.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 190, -1));

        jPanel2.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 290, 70));

        jPanel8.setBackground(new java.awt.Color(102, 0, 102));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Segoe UI Semibold", 1, 20)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/adminIcons/home_24px.png"))); // NOI18N
        jLabel9.setText("Issue Books");
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });
        jPanel8.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 190, -1));

        jPanel2.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 450, 290, 70));

        jPanel9.setBackground(new java.awt.Color(102, 0, 102));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Segoe UI Semibold", 1, 20)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/adminIcons/home_24px.png"))); // NOI18N
        jLabel10.setText("Defaulter list");
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });
        jPanel9.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 210, -1));

        jPanel2.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 750, 290, 70));

        jPanel11.setBackground(new java.awt.Color(102, 0, 102));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Segoe UI Semibold", 1, 20)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/adminIcons/home_24px.png"))); // NOI18N
        jLabel12.setText("Return Books");
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });
        jPanel11.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 160, -1));

        jPanel2.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 540, 290, 70));

        jPanel13.setBackground(new java.awt.Color(102, 0, 102));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setFont(new java.awt.Font("Segoe UI Semibold", 1, 20)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/adminIcons/home_24px.png"))); // NOI18N
        jLabel14.setText("View Issued Books");
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });
        jPanel13.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 210, -1));

        jPanel2.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 650, 290, 70));

        jLabel15.setFont(new java.awt.Font("Segoe UI Semibold", 1, 20)); // NOI18N
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/adminIcons/home_24px.png"))); // NOI18N
        jLabel15.setText("Pickup and Delivery");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 210, -1));

        jLabel16.setFont(new java.awt.Font("Segoe UI Semibold", 1, 20)); // NOI18N
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/adminIcons/home_24px.png"))); // NOI18N
        jLabel16.setText("Logout");
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 890, 210, -1));

        jScrollPane1.setViewportView(jPanel2);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 300, 630));

        setSize(new java.awt.Dimension(1335, 1155));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
System.exit(0);      // TODO add your handling code here:
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel42MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel42MouseClicked
        System.exit(0);// TODO add your handling code here:
    }//GEN-LAST:event_jLabel42MouseClicked

    private void jLabel19MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseExited
        jPanel16.setBackground(mouseExitColor);// TODO add your handling code here:
    }//GEN-LAST:event_jLabel19MouseExited

    private void jLabel19MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseEntered
        jPanel16.setBackground(mouseEnterColor);// TODO add your handling code here:
    }//GEN-LAST:event_jLabel19MouseEntered

    private void tbl_userdetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_userdetailsMouseClicked
        int rowNo = tbl_userdetails.getSelectedRow();// TODO add your handling code here:
        TableModel model = tbl_userdetails.getModel();
       
    }//GEN-LAST:event_tbl_userdetailsMouseClicked

    private void tbl_bookdetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_bookdetailsMouseClicked
        int rowNo = tbl_bookdetails.getSelectedRow();// TODO add your handling code here:
        TableModel model = tbl_bookdetails.getModel();
    }//GEN-LAST:event_tbl_bookdetailsMouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
       Mngmem mem= new Mngmem();
       mem.setVisible(true);
       dispose();
// TODO add your handling code here:
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        Manageusers users= new Manageusers();
       users.setVisible(true);
       dispose();// TODO add your handling code here:
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseClicked
       ManageBooks_1 Books_1 = new ManageBooks_1 ();
       Books_1.setVisible(true);
       dispose();// TODO add your handling code here:
    }//GEN-LAST:event_jLabel17MouseClicked

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        issuebooks sbooks = new issuebooks();
       sbooks.setVisible(true);
       dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel9MouseClicked

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        returnbook rbook = new returnbook();
       rbook.setVisible(true);
       dispose();// TODO add your handling code here:
    }//GEN-LAST:event_jLabel12MouseClicked

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        // TODO add your handling code here:
        Viewrecords records = new Viewrecords();
       records.setVisible(true);
       dispose();
    }//GEN-LAST:event_jLabel14MouseClicked

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        Defaulters defaulters = new Defaulters();
       defaulters.setVisible(true);
       dispose();// TODO add your handling code here:
    }//GEN-LAST:event_jLabel10MouseClicked

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
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomePage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelPi;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbl_booknumber;
    private javax.swing.JLabel lbl_dafaultersno;
    private javax.swing.JLabel lbl_issuedbooksno;
    private javax.swing.JLabel lbl_usersnumber;
    private rojerusan.RSTableMetro tbl_bookdetails;
    private rojerusan.RSTableMetro tbl_userdetails;
    // End of variables declaration//GEN-END:variables

}
