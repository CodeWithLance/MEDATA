package my.medata;

import static java.awt.Color.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.SwingConstants.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/*
 * Authors:
 * Arcega, Lance Angelo P.
 * Muñoz, Nathan Sheary G.
 * Pare, Neo Jezer A.
 */
public class Patient extends javax.swing.JFrame {

    Connection con;
    DefaultTableModel model = new DefaultTableModel();

    /**
     * Creates new form Patient
     */
    public Patient() {
        initComponents();
        setLocationRelativeTo(null);
        jLayeredPane1.setVisible(false);
        welcomePage.setVisible(true);
        SetIcon.SetIcon(this);
        pack();  
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/medata", "root", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
         showInfo();
    }

   int xMouse, yMouse;
   String loggedInUser;

    public void setDisplay(JPanel Panel) {
        for (int i = 0; i < jLayeredPane1.getComponentCount(); i++) {
            JComponent component = (JComponent) jLayeredPane1.getComponent(i);
            component.setVisible(false);
        }
        jLayeredPane1.setVisible(true);
        Panel.setVisible(true);
    }

    public void showInfo() {
        loggedInUser = dataBox.pullUserData();
        try {
            String query = "SELECT username, firstName, contact, lastName, email, middleName FROM userinfo WHERE username = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, loggedInUser);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String retrievedUsername = resultSet.getString("username");
                String firstName = resultSet.getString("firstName");
                String contact = resultSet.getString("contact");
                String lastName = resultSet.getString("lastName");
                String middleName = resultSet.getString("middleName");
                String email = resultSet.getString("email");

                iplblUN1.setText(retrievedUsername);
                iplblFN1.setText(firstName);
                iplblLN1.setText(lastName);
                iplblMN1.setText(middleName);
                iplblContact1.setText(contact);
                
                iplblUN.setText(retrievedUsername);
                iplblFN.setText(firstName);
                iplblLN.setText(lastName);
                iplblMN.setText(middleName);
                iplblContact.setText(contact);
                iplblEmail.setText(email);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
//        public void showProfile() {
//        loggedInUser = dataBox.pullUserData();
//        try {
//            String query = "SELECT username, firstName, contact, lastName, email, middleName FROM userinfo WHERE username = ?";
//            PreparedStatement statement = con.prepareStatement(query);
//            statement.setString(1, loggedInUser);
//            ResultSet resultSet = statement.executeQuery();
//
//            if (resultSet.next()) {
//                String retrievedUsername = resultSet.getString("username");
//                String firstName = resultSet.getString("firstName");
//                String contact = resultSet.getString("contact");
//                String lastName = resultSet.getString("lastName");
//                String middleName = resultSet.getString("middleName");
//                String email = resultSet.getString("email");
//
//                iplblUN.setText(retrievedUsername);
//                iplblFN.setText(firstName);
//                iplblLN.setText(lastName);
//                iplblMN.setText(middleName);
//                iplblContact.setText(contact);
//                iplblEmail.setText(email);
//            }
//
//            resultSet.close();
//            statement.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
    
//    private Object[] getComboBoxData() {
//        List<Object> comboData = new ArrayList<>();
//        int currentDoctorSno = getCurrentDoctorSnoFromDatabase2();
//
//        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/medata", "root", "")) {
//            String query = "SELECT username, firstName, lastName, doctorID FROM schedule WHERE username = ? and doctorID = ?";
//            PreparedStatement pstmt = con.prepareStatement(query);
//            pstmt.setString(1, "patient");
//            pstmt.setInt(2, currentDoctorSno);
//
//            ResultSet rs = pstmt.executeQuery();
//
//            while (rs.next()) {
//                cbfirstName = rs.getString("firstName");
//                cblastName = rs.getString("lastName");
//                ptUsername = rs.getString("username");
//                comboData.add(cbfirstName + " " + cblastName);
//            }
//
//            rs.close();
//            pstmt.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return comboData.toArray();
//    }
    
   public void showSched() {
        jTable2.setModel(model);
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/medata", "root", "");
            String query = "SELECT schedID, date, time  FROM schedule WHERE patientUsername = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, loggedInUser);

            ResultSet rs = pstmt.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Create an array to hold column names
            String[] columnNames = new String[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                columnNames[i - 1] = metaData.getColumnName(i);
            }
            model.setColumnIdentifiers(columnNames);

            //prevent duplicating the table data 
            if (model.getRowCount() > 0) {
                model.setRowCount(0);
            }

            while (rs.next()) {
                Object[] rowData = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    rowData[i - 1] = rs.getObject(i);
                }
                model.addRow(rowData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   
   public void showDocName(){
       int doctorID = 0;
        try {
           String query = "SELECT doctorID FROM userinfo WHERE username = ?";
           PreparedStatement statement = con.prepareStatement(query);
           statement.setString(1, loggedInUser);
           ResultSet resultSet = statement.executeQuery();

           if (resultSet.next()) {
               doctorID = resultSet.getInt("doctorID");
           }

           resultSet.close();
           statement.close();
       } catch (SQLException e) {
           e.printStackTrace();
       }
       
       
       
       try {
           String query = "SELECT firstName, lastName, contact FROM userinfo WHERE sno = ?";
           PreparedStatement statement = con.prepareStatement(query);
           statement.setInt(1, doctorID);
           ResultSet resultSet = statement.executeQuery();

           if (resultSet.next()) {
               String firstName = resultSet.getString("firstName");
               String contact = resultSet.getString("contact");
               String lastName = resultSet.getString("lastName");

               doctorFName.setText(firstName);
               doctorLName.setText(lastName);
               lblContact.setText(contact);
           }

           resultSet.close();
           statement.close();
       } catch (SQLException e) {
           e.printStackTrace();
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
        exit = new javax.swing.JButton();
        minimized = new javax.swing.JButton();
        logo = new javax.swing.JLabel();
        infoPane = new javax.swing.JPanel();
        informationPanel = new javax.swing.JPanel();
        middleBorder = new javax.swing.JLabel();
        organizationTitle = new javax.swing.JLabel();
        infoUsername1 = new javax.swing.JLabel();
        infoContact1 = new javax.swing.JLabel();
        iplblUN1 = new javax.swing.JLabel();
        iplblLN1 = new javax.swing.JLabel();
        infoLastName1 = new javax.swing.JLabel();
        iplblFN1 = new javax.swing.JLabel();
        infoFirstName1 = new javax.swing.JLabel();
        iplblMN1 = new javax.swing.JLabel();
        infoMiddleName1 = new javax.swing.JLabel();
        iplblContact1 = new javax.swing.JLabel();
        leftPartBorder = new javax.swing.JLabel();
        rightPartBorder = new javax.swing.JLabel();
        topBorder = new javax.swing.JLabel();
        botBorder = new javax.swing.JLabel();
        rightBorder = new javax.swing.JLabel();
        leftBorder = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        navbar = new javax.swing.JPanel();
        profileBtn = new javax.swing.JButton();
        doctorsBtn = new javax.swing.JButton();
        logOutBtn = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        windowPanel = new javax.swing.JPanel();
        welcomePage = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        medicationPage = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        patientProfile = new javax.swing.JPanel();
        infoUsername = new javax.swing.JLabel();
        infoContact = new javax.swing.JLabel();
        iplblUN = new javax.swing.JLabel();
        iplblLN = new javax.swing.JLabel();
        infoLastName = new javax.swing.JLabel();
        iplblFN = new javax.swing.JLabel();
        infoFirstName = new javax.swing.JLabel();
        iplblMN = new javax.swing.JLabel();
        infoMiddleName = new javax.swing.JLabel();
        iplblContact = new javax.swing.JLabel();
        contactBorder = new javax.swing.JLabel();
        lblAddUser = new javax.swing.JLabel();
        infoEmail = new javax.swing.JLabel();
        iplblEmail = new javax.swing.JLabel();
        patientiBorder = new javax.swing.JLabel();
        border = new javax.swing.JLabel();
        medicalHistory = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        results = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        pendingTests = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        doctorPage = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        doctorFName = new javax.swing.JLabel();
        lblContact = new javax.swing.JLabel();
        doctorLName = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        pharmacyPage = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        frameDrag = new javax.swing.JLabel();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(187, 209, 241));

        exit.setBackground(new java.awt.Color(147, 206, 255));
        exit.setText("X");
        exit.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        exit.setContentAreaFilled(false);
        exit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exit.setMaximumSize(new java.awt.Dimension(30, 30));
        exit.setMinimumSize(new java.awt.Dimension(30, 30));
        exit.setPreferredSize(new java.awt.Dimension(30, 30));
        exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                exitMouseExited(evt);
            }
        });
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });

        minimized.setBackground(new java.awt.Color(179, 234, 255));
        minimized.setText("—");
        minimized.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        minimized.setContentAreaFilled(false);
        minimized.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        minimized.setMaximumSize(new java.awt.Dimension(30, 30));
        minimized.setMinimumSize(new java.awt.Dimension(30, 30));
        minimized.setPreferredSize(new java.awt.Dimension(30, 30));
        minimized.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                minimizedMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                minimizedMouseExited(evt);
            }
        });
        minimized.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minimizedActionPerformed(evt);
            }
        });

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/my/medata/images/Logo.png"))); // NOI18N

        informationPanel.setPreferredSize(new java.awt.Dimension(631, 139));
        informationPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        middleBorder.setBackground(new java.awt.Color(0, 0, 0));
        middleBorder.setText(".");
        middleBorder.setOpaque(true);
        informationPanel.add(middleBorder, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 96, 570, 3));

        organizationTitle.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        organizationTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        organizationTitle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/my/medata/images/TuwaTech.png"))); // NOI18N
        organizationTitle.setOpaque(true);
        informationPanel.add(organizationTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 620, 60));

        infoUsername1.setBackground(new java.awt.Color(102, 255, 204));
        infoUsername1.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        infoUsername1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        infoUsername1.setText("Username");
        informationPanel.add(infoUsername1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 100, 30));

        infoContact1.setBackground(new java.awt.Color(102, 255, 204));
        infoContact1.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        infoContact1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        infoContact1.setText("Contact");
        informationPanel.add(infoContact1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 100, 120, 30));

        iplblUN1.setBackground(new java.awt.Color(102, 255, 204));
        iplblUN1.setFont(new java.awt.Font("Segoe UI", 0, 9)); // NOI18N
        iplblUN1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iplblUN1.setText("username");
        informationPanel.add(iplblUN1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 100, 30));

        iplblLN1.setBackground(new java.awt.Color(102, 255, 204));
        iplblLN1.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        iplblLN1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iplblLN1.setText("lastName");
        informationPanel.add(iplblLN1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, 100, 30));

        infoLastName1.setBackground(new java.awt.Color(102, 255, 204));
        infoLastName1.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        infoLastName1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        infoLastName1.setText("Last Name");
        informationPanel.add(infoLastName1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, 100, 30));

        iplblFN1.setBackground(new java.awt.Color(102, 255, 204));
        iplblFN1.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        iplblFN1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iplblFN1.setText("firstName");
        informationPanel.add(iplblFN1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 70, 130, 30));

        infoFirstName1.setBackground(new java.awt.Color(102, 255, 204));
        infoFirstName1.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        infoFirstName1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        infoFirstName1.setText("First Name");
        informationPanel.add(infoFirstName1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 100, 130, 30));

        iplblMN1.setBackground(new java.awt.Color(102, 255, 204));
        iplblMN1.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        iplblMN1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iplblMN1.setText("middleName");
        informationPanel.add(iplblMN1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 70, 100, 30));

        infoMiddleName1.setBackground(new java.awt.Color(102, 255, 204));
        infoMiddleName1.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        infoMiddleName1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        infoMiddleName1.setText("Middle Name");
        informationPanel.add(infoMiddleName1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 100, 100, 30));

        iplblContact1.setBackground(new java.awt.Color(102, 255, 204));
        iplblContact1.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        iplblContact1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iplblContact1.setText("contact");
        informationPanel.add(iplblContact1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 70, 120, 30));

        leftPartBorder.setBackground(new java.awt.Color(0, 0, 0));
        leftPartBorder.setText(".");
        leftPartBorder.setOpaque(true);
        informationPanel.add(leftPartBorder, new org.netbeans.lib.awtextra.AbsoluteConstraints(477, 60, -1, 70));

        rightPartBorder.setBackground(new java.awt.Color(0, 0, 0));
        rightPartBorder.setText(".");
        rightPartBorder.setOpaque(true);
        informationPanel.add(rightPartBorder, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 62, -1, 70));

        topBorder.setBackground(new java.awt.Color(0, 0, 0));
        topBorder.setText(".");
        topBorder.setOpaque(true);
        informationPanel.add(topBorder, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 59, 570, 10));

        botBorder.setBackground(new java.awt.Color(0, 0, 0));
        botBorder.setText(".");
        botBorder.setOpaque(true);
        informationPanel.add(botBorder, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 129, 570, 3));

        rightBorder.setBackground(new java.awt.Color(0, 0, 0));
        rightBorder.setText(".");
        rightBorder.setOpaque(true);
        informationPanel.add(rightBorder, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, 70));

        leftBorder.setBackground(new java.awt.Color(0, 0, 0));
        leftBorder.setText(".");
        leftBorder.setOpaque(true);
        informationPanel.add(leftBorder, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 60, -1, 72));

        javax.swing.GroupLayout infoPaneLayout = new javax.swing.GroupLayout(infoPane);
        infoPane.setLayout(infoPaneLayout);
        infoPaneLayout.setHorizontalGroup(
            infoPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 632, Short.MAX_VALUE)
            .addGroup(infoPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(infoPaneLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(informationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        infoPaneLayout.setVerticalGroup(
            infoPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 139, Short.MAX_VALUE)
            .addGroup(infoPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(infoPaneLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(informationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel3.setOpaque(false);

        navbar.setOpaque(false);
        navbar.setLayout(new java.awt.GridLayout(3, 1));

        profileBtn.setFont(new java.awt.Font("Quicksand", 0, 12)); // NOI18N
        profileBtn.setText("Patient Profile");
        profileBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        profileBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profileBtnActionPerformed(evt);
            }
        });
        navbar.add(profileBtn);

        doctorsBtn.setFont(new java.awt.Font("Quicksand", 0, 12)); // NOI18N
        doctorsBtn.setText("Doctor and Schedule");
        doctorsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doctorsBtnActionPerformed(evt);
            }
        });
        navbar.add(doctorsBtn);

        logOutBtn.setText("Log Out");
        logOutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOutBtnActionPerformed(evt);
            }
        });
        navbar.add(logOutBtn);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(navbar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(navbar, javax.swing.GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)
        );

        jPanel4.setPreferredSize(new java.awt.Dimension(644, 461));

        jLabel4.setText("Welcome!");

        javax.swing.GroupLayout welcomePageLayout = new javax.swing.GroupLayout(welcomePage);
        welcomePage.setLayout(welcomePageLayout);
        welcomePageLayout.setHorizontalGroup(
            welcomePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(welcomePageLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addContainerGap(573, Short.MAX_VALUE))
        );
        welcomePageLayout.setVerticalGroup(
            welcomePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(welcomePageLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addContainerGap(440, Short.MAX_VALUE))
        );

        jLabel6.setText("medication page");

        javax.swing.GroupLayout medicationPageLayout = new javax.swing.GroupLayout(medicationPage);
        medicationPage.setLayout(medicationPageLayout);
        medicationPageLayout.setHorizontalGroup(
            medicationPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(medicationPageLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addContainerGap(537, Short.MAX_VALUE))
        );
        medicationPageLayout.setVerticalGroup(
            medicationPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(medicationPageLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addContainerGap(440, Short.MAX_VALUE))
        );

        patientProfile.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        infoUsername.setBackground(new java.awt.Color(102, 255, 204));
        infoUsername.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        infoUsername.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        infoUsername.setText("Username");
        patientProfile.add(infoUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 130, 90, 30));

        infoContact.setBackground(new java.awt.Color(102, 255, 204));
        infoContact.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        infoContact.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        infoContact.setText("Contact");
        patientProfile.add(infoContact, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 210, 120, 30));

        iplblUN.setBackground(new java.awt.Color(102, 255, 204));
        iplblUN.setFont(new java.awt.Font("Segoe UI", 0, 9)); // NOI18N
        iplblUN.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iplblUN.setText("username");
        patientProfile.add(iplblUN, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 130, 100, 30));

        iplblLN.setBackground(new java.awt.Color(102, 255, 204));
        iplblLN.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        iplblLN.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iplblLN.setText("lastName");
        patientProfile.add(iplblLN, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 60, 100, 30));

        infoLastName.setBackground(new java.awt.Color(102, 255, 204));
        infoLastName.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        infoLastName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        infoLastName.setText("Last Name");
        patientProfile.add(infoLastName, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 90, 100, 30));

        iplblFN.setBackground(new java.awt.Color(102, 255, 204));
        iplblFN.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        iplblFN.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iplblFN.setText("firstName");
        patientProfile.add(iplblFN, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, 130, 30));

        infoFirstName.setBackground(new java.awt.Color(102, 255, 204));
        infoFirstName.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        infoFirstName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        infoFirstName.setText("First Name");
        patientProfile.add(infoFirstName, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, 130, 30));

        iplblMN.setBackground(new java.awt.Color(102, 255, 204));
        iplblMN.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        iplblMN.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iplblMN.setText("middleName");
        patientProfile.add(iplblMN, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 60, 100, 30));

        infoMiddleName.setBackground(new java.awt.Color(102, 255, 204));
        infoMiddleName.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        infoMiddleName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        infoMiddleName.setText("Middle Name");
        patientProfile.add(infoMiddleName, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 90, 100, 30));

        iplblContact.setBackground(new java.awt.Color(102, 255, 204));
        iplblContact.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        iplblContact.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iplblContact.setText("contact");
        patientProfile.add(iplblContact, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 180, 120, 30));

        contactBorder.setBackground(new java.awt.Color(153, 153, 153));
        contactBorder.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        contactBorder.setForeground(new java.awt.Color(255, 255, 255));
        contactBorder.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        contactBorder.setText("Contacts");
        contactBorder.setOpaque(true);
        patientProfile.add(contactBorder, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 600, 20));

        lblAddUser.setFont(new java.awt.Font("Quicksand Medium", 0, 24)); // NOI18N
        lblAddUser.setText("Patient Profile");
        patientProfile.add(lblAddUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        infoEmail.setBackground(new java.awt.Color(102, 255, 204));
        infoEmail.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        infoEmail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        infoEmail.setText("Email");
        patientProfile.add(infoEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 210, 120, 30));

        iplblEmail.setBackground(new java.awt.Color(102, 255, 204));
        iplblEmail.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        iplblEmail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iplblEmail.setText("email");
        patientProfile.add(iplblEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 180, 160, 30));

        patientiBorder.setBackground(new java.awt.Color(153, 153, 153));
        patientiBorder.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        patientiBorder.setForeground(new java.awt.Color(255, 255, 255));
        patientiBorder.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        patientiBorder.setText("Patient Information");
        patientiBorder.setOpaque(true);
        patientProfile.add(patientiBorder, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 600, 20));

        border.setBackground(new java.awt.Color(153, 153, 153));
        border.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        border.setForeground(new java.awt.Color(255, 255, 255));
        border.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        border.setOpaque(true);
        patientProfile.add(border, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 600, 20));

        jLabel7.setText("medical history pag");

        javax.swing.GroupLayout medicalHistoryLayout = new javax.swing.GroupLayout(medicalHistory);
        medicalHistory.setLayout(medicalHistoryLayout);
        medicalHistoryLayout.setHorizontalGroup(
            medicalHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(medicalHistoryLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addContainerGap(522, Short.MAX_VALUE))
        );
        medicalHistoryLayout.setVerticalGroup(
            medicalHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(medicalHistoryLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addContainerGap(440, Short.MAX_VALUE))
        );

        jLabel8.setText("results page");

        javax.swing.GroupLayout resultsLayout = new javax.swing.GroupLayout(results);
        results.setLayout(resultsLayout);
        resultsLayout.setHorizontalGroup(
            resultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(resultsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addContainerGap(563, Short.MAX_VALUE))
        );
        resultsLayout.setVerticalGroup(
            resultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(resultsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addContainerGap(440, Short.MAX_VALUE))
        );

        jLabel9.setText("pending tests page");

        javax.swing.GroupLayout pendingTestsLayout = new javax.swing.GroupLayout(pendingTests);
        pendingTests.setLayout(pendingTestsLayout);
        pendingTestsLayout.setHorizontalGroup(
            pendingTestsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pendingTestsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addContainerGap(526, Short.MAX_VALUE))
        );
        pendingTestsLayout.setVerticalGroup(
            pendingTestsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pendingTestsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addContainerGap(440, Short.MAX_VALUE))
        );

        doctorPage.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable2.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Doctor", "Date", "Time", "Contact", "Email"
            }
        ));
        jTable2.setGridColor(new java.awt.Color(255, 255, 255));
        jScrollPane3.setViewportView(jTable2);

        doctorPage.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 549, 270));

        doctorFName.setText("Doctor");
        doctorPage.add(doctorFName, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 30, -1, -1));

        lblContact.setText("Contact: ");
        doctorPage.add(lblContact, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, -1, -1));

        doctorLName.setText("Doctor");
        doctorPage.add(doctorLName, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 30, -1, -1));

        jLabel3.setText("Doctors:");
        doctorPage.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        jLabel5.setText("Contact: ");
        doctorPage.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, -1, -1));

        jLabel11.setText("pharma page");

        javax.swing.GroupLayout pharmacyPageLayout = new javax.swing.GroupLayout(pharmacyPage);
        pharmacyPage.setLayout(pharmacyPageLayout);
        pharmacyPageLayout.setHorizontalGroup(
            pharmacyPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pharmacyPageLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addContainerGap(556, Short.MAX_VALUE))
        );
        pharmacyPageLayout.setVerticalGroup(
            pharmacyPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pharmacyPageLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addContainerGap(434, Short.MAX_VALUE))
        );

        jLayeredPane1.setLayer(medicationPage, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(patientProfile, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(medicalHistory, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(results, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(pendingTests, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(doctorPage, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(pharmacyPage, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(doctorPage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pendingTests, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(results, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(medicalHistory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(patientProfile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(medicationPage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pharmacyPage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(doctorPage, javax.swing.GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pendingTests, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(results, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(medicalHistory, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(patientProfile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(medicationPage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                    .addComponent(pharmacyPage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout windowPanelLayout = new javax.swing.GroupLayout(windowPanel);
        windowPanel.setLayout(windowPanelLayout);
        windowPanelLayout.setHorizontalGroup(
            windowPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1)
            .addGroup(windowPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(windowPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(welcomePage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        windowPanelLayout.setVerticalGroup(
            windowPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1)
            .addGroup(windowPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(windowPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(welcomePage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(windowPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(windowPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(minimized, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(infoPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 632, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(minimized, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(infoPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 650));

        frameDrag.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                frameDragMouseDragged(evt);
            }
        });
        frameDrag.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                frameDragMousePressed(evt);
            }
        });
        getContentPane().add(frameDrag, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 650));
        getContentPane().add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 650));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMouseEntered
        exit.setContentAreaFilled(true);
    }//GEN-LAST:event_exitMouseEntered

    private void exitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMouseExited
        exit.setContentAreaFilled(false);
    }//GEN-LAST:event_exitMouseExited

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
        int exit = JOptionPane.showOptionDialog(null, "Are you sure you want to exit?",
                "Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        if (exit == 0) {//yes
            System.exit(0);
        }
    }//GEN-LAST:event_exitActionPerformed

    private void minimizedMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizedMouseEntered
        minimized.setContentAreaFilled(true);
    }//GEN-LAST:event_minimizedMouseEntered

    private void minimizedMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizedMouseExited
        minimized.setContentAreaFilled(false);
    }//GEN-LAST:event_minimizedMouseExited

    private void minimizedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minimizedActionPerformed
        setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_minimizedActionPerformed

    private void frameDragMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_frameDragMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_frameDragMouseDragged

    private void frameDragMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_frameDragMousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_frameDragMousePressed

    private void profileBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profileBtnActionPerformed
        setDisplay(patientProfile);
        showInfo();
    }//GEN-LAST:event_profileBtnActionPerformed

    private void doctorsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doctorsBtnActionPerformed
        setDisplay(doctorPage);
        showSched();
        showDocName();
    }//GEN-LAST:event_doctorsBtnActionPerformed

    private void logOutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOutBtnActionPerformed
        Methods.logout(this);
    }//GEN-LAST:event_logOutBtnActionPerformed

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
            java.util.logging.Logger.getLogger(Patient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Patient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Patient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Patient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Patient().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JLabel border;
    private javax.swing.JLabel botBorder;
    private javax.swing.JLabel contactBorder;
    private javax.swing.JLabel doctorFName;
    private javax.swing.JLabel doctorLName;
    private javax.swing.JPanel doctorPage;
    private javax.swing.JButton doctorsBtn;
    private javax.swing.JButton exit;
    private javax.swing.JLabel frameDrag;
    private javax.swing.JLabel infoContact;
    private javax.swing.JLabel infoContact1;
    private javax.swing.JLabel infoEmail;
    private javax.swing.JLabel infoFirstName;
    private javax.swing.JLabel infoFirstName1;
    private javax.swing.JLabel infoLastName;
    private javax.swing.JLabel infoLastName1;
    private javax.swing.JLabel infoMiddleName;
    private javax.swing.JLabel infoMiddleName1;
    private javax.swing.JPanel infoPane;
    private javax.swing.JLabel infoUsername;
    private javax.swing.JLabel infoUsername1;
    private javax.swing.JPanel informationPanel;
    private javax.swing.JLabel iplblContact;
    private javax.swing.JLabel iplblContact1;
    private javax.swing.JLabel iplblEmail;
    private javax.swing.JLabel iplblFN;
    private javax.swing.JLabel iplblFN1;
    private javax.swing.JLabel iplblLN;
    private javax.swing.JLabel iplblLN1;
    private javax.swing.JLabel iplblMN;
    private javax.swing.JLabel iplblMN1;
    private javax.swing.JLabel iplblUN;
    private javax.swing.JLabel iplblUN1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel lblAddUser;
    private javax.swing.JLabel lblContact;
    private javax.swing.JLabel leftBorder;
    private javax.swing.JLabel leftPartBorder;
    private javax.swing.JButton logOutBtn;
    private javax.swing.JLabel logo;
    private javax.swing.JPanel medicalHistory;
    private javax.swing.JPanel medicationPage;
    private javax.swing.JLabel middleBorder;
    private javax.swing.JButton minimized;
    private javax.swing.JPanel navbar;
    private javax.swing.JLabel organizationTitle;
    private javax.swing.JPanel patientProfile;
    private javax.swing.JLabel patientiBorder;
    private javax.swing.JPanel pendingTests;
    private javax.swing.JPanel pharmacyPage;
    private javax.swing.JButton profileBtn;
    private javax.swing.JPanel results;
    private javax.swing.JLabel rightBorder;
    private javax.swing.JLabel rightPartBorder;
    private javax.swing.JLabel topBorder;
    private javax.swing.JPanel welcomePage;
    private javax.swing.JPanel windowPanel;
    // End of variables declaration//GEN-END:variables
}
