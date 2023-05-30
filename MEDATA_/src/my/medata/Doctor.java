package my.medata;

import static java.awt.Color.*;
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

/*
 * Authors:
 * Arcega, Lance Angelo P.
 * Muñoz, Nathan Sheary G.
 * Pare, Neo Jezer A.
 */
public class Doctor extends javax.swing.JFrame {

    Connection con;
    

    /**
     * Creates new form Doctor
     */
    public Doctor() {
        initComponents();
        setLocationRelativeTo(null);
        jLayeredPane1.setVisible(false);
        welcomePage.setVisible(true);
        pack();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/medata", "root", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        showInfo();
    }
    

    private static final String email_Pattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    int xMouse, yMouse;
    String uid;
    String username;
    String password;
    String setRole;
    
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
                String query = "SELECT username, firstName, contact, lastName, middleName FROM userinfo WHERE username = ?";
                PreparedStatement statement = con.prepareStatement(query);
                statement.setString(1, loggedInUser);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    String retrievedUsername = resultSet.getString("username");
                    String firstName = resultSet.getString("firstName");
                    String contact = resultSet.getString("contact");
                    String lastName = resultSet.getString("lastName");
                    String middleName = resultSet.getString("middleName");

                    iplblUN1.setText(retrievedUsername);
                    iplblFN1.setText(firstName);
                    iplblLN1.setText(lastName);
                    iplblMN1.setText(middleName);
                    iplblContact1.setText(contact);
                }

                resultSet.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
}

    public void focusOn(JTextField textfield, String intext) {
        if (textfield.getText().equals(intext)) {
            textfield.setText("");
            textfield.setForeground(black);
            textfield.setHorizontalAlignment(LEFT);
        }
        textfield.selectAll();
    }

    public void focusOff(JTextField textfield, String intext) {
        if (textfield.getText().isEmpty()) {
            textfield.setText(intext);
            textfield.setForeground(gray);
            textfield.setHorizontalAlignment(CENTER);
        }
    }

    private void updateAgeLabel() {
        LocalDate selectedDate = jDateChooser3.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(selectedDate, currentDate);
        int age = period.getYears();
        ptAge.setForeground(black);
        ptAge.setText(String.valueOf(age));
    }

 void insertUserData() {
        String lastName = ptLastName.getText();
        String firstName = ptFirstName.getText();
        String middleName = ptMiddleName.getText();
        String email = ptEmail.getText();
        String contact = ptContact.getText();
        String sex;
        if (ptRbMale.isSelected()) {
            sex = ptRbMale.getText();
        } else {
            sex = ptRbMale.getText();
        }
        SimpleDateFormat birthDateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateOfBirth = birthDateFormatter.format(jDateChooser3.getDate());
        String civilStatus;
        if (ptCivilStatus.getSelectedIndex() == 0) {
            civilStatus = "Single";
        } else if (ptCivilStatus.getSelectedIndex() == 1) {
            civilStatus = "Married";
        } else if (ptCivilStatus.getSelectedIndex() == 2) {
            civilStatus = "Divorced";
        } else {
            civilStatus = "Widowed";
        }
        int age = Integer.parseInt(ptAge.getText());
        String address = "";
        int height = 0;
        int weight = 0;
        String role = "patient";
        int username_count = 0;
        do {
            try {
                username = new UIDGenerator().generateUID(lastName, firstName, jDateChooser3.getDate(), uid);
                String sql = "Select COUNT(username) as username_count from userinfo where username = ?";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, username);

                ResultSet resultSet = pst.executeQuery();
                if (resultSet.next()) {
                    username_count = resultSet.getInt("username_count");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (username_count > 0);
        password = new passwordGenerator().generatePassword(lastName);
        boolean isActivated = false;

        createUser.processInput(lastName, firstName, middleName, age, dateOfBirth, address, contact, email, sex, civilStatus, height, weight, username, password, role, isActivated);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        exit = new javax.swing.JButton();
        minimized = new javax.swing.JButton();
        logo = new javax.swing.JLabel();
        infoPane = new javax.swing.JPanel();
        organizationTitle1 = new javax.swing.JLabel();
        infoUsername1 = new javax.swing.JLabel();
        iplblUN1 = new javax.swing.JLabel();
        infoContact1 = new javax.swing.JLabel();
        iplblLN1 = new javax.swing.JLabel();
        infoLastName1 = new javax.swing.JLabel();
        iplblFN1 = new javax.swing.JLabel();
        infoFirstName1 = new javax.swing.JLabel();
        iplblMN1 = new javax.swing.JLabel();
        infoMiddleName1 = new javax.swing.JLabel();
        iplblContact1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        navbar = new javax.swing.JPanel();
        addDoctorBtn = new javax.swing.JButton();
        addPatBtn = new javax.swing.JButton();
        reportsBtn = new javax.swing.JButton();
        removeBtn = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        windowPanel = new javax.swing.JPanel();
        welcomePage = new javax.swing.JPanel();
        welcomeAdmin = new javax.swing.JLabel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        tba = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        listofpatients = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        requestPage = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        schedule = new javax.swing.JPanel();
        listofpatients1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        addPatient = new javax.swing.JPanel();
        lblName1 = new javax.swing.JLabel();
        addDoctorTitle1 = new javax.swing.JLabel();
        comma2 = new javax.swing.JLabel();
        ptLastName = new javax.swing.JTextField();
        ptFirstName = new javax.swing.JTextField();
        ptMiddleName = new javax.swing.JTextField();
        lblEmail1 = new javax.swing.JLabel();
        ptEmail = new javax.swing.JTextField();
        lblContacts1 = new javax.swing.JLabel();
        ptContact = new javax.swing.JTextField();
        lblSex1 = new javax.swing.JLabel();
        ptRbMale = new javax.swing.JRadioButton();
        ptRbFemale = new javax.swing.JRadioButton();
        lblBDay1 = new javax.swing.JLabel();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        jButton9 = new javax.swing.JButton();
        lblBDay2 = new javax.swing.JLabel();
        ptAge = new javax.swing.JTextField();
        lblBDay3 = new javax.swing.JLabel();
        ptCivilStatus = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        frameDrag = new javax.swing.JLabel();
        background = new javax.swing.JLabel();

        jFrame1.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        jFrame1.setUndecorated(true);
        jFrame1.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

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

        organizationTitle1.setBackground(new java.awt.Color(153, 255, 153));
        organizationTitle1.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        organizationTitle1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        organizationTitle1.setText("Transformative Unified Workflow Assistive Technology");
        organizationTitle1.setOpaque(true);

        infoUsername1.setBackground(new java.awt.Color(102, 255, 204));
        infoUsername1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        infoUsername1.setText("Username");
        infoUsername1.setOpaque(true);

        iplblUN1.setBackground(new java.awt.Color(102, 255, 204));
        iplblUN1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iplblUN1.setText("username");
        iplblUN1.setOpaque(true);

        infoContact1.setBackground(new java.awt.Color(102, 255, 204));
        infoContact1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        infoContact1.setText("Contact");
        infoContact1.setOpaque(true);

        iplblLN1.setBackground(new java.awt.Color(102, 255, 204));
        iplblLN1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iplblLN1.setText("lastName");
        iplblLN1.setOpaque(true);

        infoLastName1.setBackground(new java.awt.Color(102, 255, 204));
        infoLastName1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        infoLastName1.setText("Last Name");
        infoLastName1.setOpaque(true);

        iplblFN1.setBackground(new java.awt.Color(102, 255, 204));
        iplblFN1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iplblFN1.setText("firstName");
        iplblFN1.setOpaque(true);

        infoFirstName1.setBackground(new java.awt.Color(102, 255, 204));
        infoFirstName1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        infoFirstName1.setText("First Name");
        infoFirstName1.setOpaque(true);

        iplblMN1.setBackground(new java.awt.Color(102, 255, 204));
        iplblMN1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iplblMN1.setText("middleName");
        iplblMN1.setOpaque(true);

        infoMiddleName1.setBackground(new java.awt.Color(102, 255, 204));
        infoMiddleName1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        infoMiddleName1.setText("Middle Name");
        infoMiddleName1.setOpaque(true);

        iplblContact1.setBackground(new java.awt.Color(102, 255, 204));
        iplblContact1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iplblContact1.setText("contact");
        iplblContact1.setOpaque(true);

        javax.swing.GroupLayout infoPaneLayout = new javax.swing.GroupLayout(infoPane);
        infoPane.setLayout(infoPaneLayout);
        infoPaneLayout.setHorizontalGroup(
            infoPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 643, Short.MAX_VALUE)
            .addGroup(infoPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(infoPaneLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(infoPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(organizationTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(infoPaneLayout.createSequentialGroup()
                            .addGap(34, 34, 34)
                            .addGroup(infoPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(infoPaneLayout.createSequentialGroup()
                                    .addComponent(iplblUN1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(20, 20, 20)
                                    .addComponent(iplblLN1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, 0)
                                    .addComponent(iplblFN1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, 0)
                                    .addComponent(iplblMN1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(20, 20, 20)
                                    .addComponent(iplblContact1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(infoPaneLayout.createSequentialGroup()
                                    .addComponent(infoUsername1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(20, 20, 20)
                                    .addComponent(infoLastName1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, 0)
                                    .addComponent(infoFirstName1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, 0)
                                    .addComponent(infoMiddleName1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(20, 20, 20)
                                    .addComponent(infoContact1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        infoPaneLayout.setVerticalGroup(
            infoPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 151, Short.MAX_VALUE)
            .addGroup(infoPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(infoPaneLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(organizationTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(4, 4, 4)
                    .addGroup(infoPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(iplblUN1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(iplblLN1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(iplblFN1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(iplblMN1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(iplblContact1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(infoPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(infoUsername1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(infoLastName1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(infoFirstName1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(infoMiddleName1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(infoContact1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel3.setOpaque(false);

        navbar.setOpaque(false);
        navbar.setLayout(new java.awt.GridLayout(4, 1));

        addDoctorBtn.setFont(new java.awt.Font("Quicksand", 0, 12)); // NOI18N
        addDoctorBtn.setText("Add Patient");
        addDoctorBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addDoctorBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addDoctorBtnActionPerformed(evt);
            }
        });
        navbar.add(addDoctorBtn);

        addPatBtn.setFont(new java.awt.Font("Quicksand", 0, 12)); // NOI18N
        addPatBtn.setText("Schedule");
        addPatBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPatBtnActionPerformed(evt);
            }
        });
        navbar.add(addPatBtn);

        reportsBtn.setFont(new java.awt.Font("Quicksand", 0, 12)); // NOI18N
        reportsBtn.setText("List of Patients");
        reportsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportsBtnActionPerformed(evt);
            }
        });
        navbar.add(reportsBtn);

        removeBtn.setFont(new java.awt.Font("Quicksand", 0, 12)); // NOI18N
        removeBtn.setText("TBA");
        removeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeBtnActionPerformed(evt);
            }
        });
        navbar.add(removeBtn);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(navbar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(navbar, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
        );

        jPanel4.setPreferredSize(new java.awt.Dimension(689, 461));

        welcomeAdmin.setFont(new java.awt.Font("Quicksand", 0, 12)); // NOI18N
        welcomeAdmin.setText("Welcome Doctor!");

        javax.swing.GroupLayout welcomePageLayout = new javax.swing.GroupLayout(welcomePage);
        welcomePage.setLayout(welcomePageLayout);
        welcomePageLayout.setHorizontalGroup(
            welcomePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(welcomePageLayout.createSequentialGroup()
                .addComponent(welcomeAdmin)
                .addGap(0, 583, Short.MAX_VALUE))
        );
        welcomePageLayout.setVerticalGroup(
            welcomePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(welcomePageLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(welcomeAdmin)
                .addContainerGap(433, Short.MAX_VALUE))
        );

        jLayeredPane1.setPreferredSize(new java.awt.Dimension(689, 461));

        jLabel8.setText("remove Page");

        javax.swing.GroupLayout tbaLayout = new javax.swing.GroupLayout(tba);
        tba.setLayout(tbaLayout);
        tbaLayout.setHorizontalGroup(
            tbaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tbaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addContainerGap(614, Short.MAX_VALUE))
        );
        tbaLayout.setVerticalGroup(
            tbaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tbaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addContainerGap(439, Short.MAX_VALUE))
        );

        jTable1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Age", "Date of Birth", "Address", "Contact", "Email", "Sex"
            }
        ));
        jTable1.setGridColor(new java.awt.Color(255, 255, 255));
        jScrollPane2.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(2).setHeaderValue("Date of Birth");
            jTable1.getColumnModel().getColumn(3).setHeaderValue("Address");
            jTable1.getColumnModel().getColumn(4).setHeaderValue("Contact");
            jTable1.getColumnModel().getColumn(5).setHeaderValue("Email");
            jTable1.getColumnModel().getColumn(6).setHeaderValue("Sex");
        }

        javax.swing.GroupLayout listofpatientsLayout = new javax.swing.GroupLayout(listofpatients);
        listofpatients.setLayout(listofpatientsLayout);
        listofpatientsLayout.setHorizontalGroup(
            listofpatientsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 632, Short.MAX_VALUE)
        );
        listofpatientsLayout.setVerticalGroup(
            listofpatientsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(listofpatientsLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        requestPage.setPreferredSize(new java.awt.Dimension(677, 461));

        jLabel6.setText("request page");

        javax.swing.GroupLayout requestPageLayout = new javax.swing.GroupLayout(requestPage);
        requestPage.setLayout(requestPageLayout);
        requestPageLayout.setHorizontalGroup(
            requestPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(requestPageLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addContainerGap(603, Short.MAX_VALUE))
        );
        requestPageLayout.setVerticalGroup(
            requestPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(requestPageLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addContainerGap(433, Short.MAX_VALUE))
        );

        jTable2.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date", "Time"
            }
        ));
        jTable2.setGridColor(new java.awt.Color(255, 255, 255));
        jScrollPane3.setViewportView(jTable2);

        javax.swing.GroupLayout listofpatients1Layout = new javax.swing.GroupLayout(listofpatients1);
        listofpatients1.setLayout(listofpatients1Layout);
        listofpatients1Layout.setHorizontalGroup(
            listofpatients1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 619, Short.MAX_VALUE)
        );
        listofpatients1Layout.setVerticalGroup(
            listofpatients1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(listofpatients1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout scheduleLayout = new javax.swing.GroupLayout(schedule);
        schedule.setLayout(scheduleLayout);
        scheduleLayout.setHorizontalGroup(
            scheduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 631, Short.MAX_VALUE)
            .addGroup(scheduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(scheduleLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(listofpatients1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        scheduleLayout.setVerticalGroup(
            scheduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 482, Short.MAX_VALUE)
            .addGroup(scheduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(scheduleLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(listofpatients1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        addPatient.setMaximumSize(new java.awt.Dimension(615, 455));
        addPatient.setMinimumSize(new java.awt.Dimension(615, 455));

        lblName1.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        lblName1.setText("Name:");

        addDoctorTitle1.setFont(new java.awt.Font("Quicksand Medium", 0, 24)); // NOI18N
        addDoctorTitle1.setText("Add Patient");

        comma2.setText(",");

        ptLastName.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        ptLastName.setForeground(new java.awt.Color(153, 153, 153));
        ptLastName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ptLastName.setText("Last Name");
        ptLastName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ptLastNameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                ptLastNameFocusLost(evt);
            }
        });

        ptFirstName.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        ptFirstName.setForeground(new java.awt.Color(153, 153, 153));
        ptFirstName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ptFirstName.setText("First Name");
        ptFirstName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ptFirstNameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                ptFirstNameFocusLost(evt);
            }
        });

        ptMiddleName.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        ptMiddleName.setForeground(new java.awt.Color(153, 153, 153));
        ptMiddleName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ptMiddleName.setText("Middle Name");
        ptMiddleName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ptMiddleNameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                ptMiddleNameFocusLost(evt);
            }
        });

        lblEmail1.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        lblEmail1.setText("Email:");

        ptEmail.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        ptEmail.setForeground(new java.awt.Color(153, 153, 153));
        ptEmail.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ptEmail.setText("youdata@gmail.com");
        ptEmail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ptEmailFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                ptEmailFocusLost(evt);
            }
        });

        lblContacts1.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        lblContacts1.setText("Contact Number:");

        ptContact.setFont(new java.awt.Font("Quicksand", 0, 14)); // NOI18N
        ptContact.setForeground(new java.awt.Color(153, 153, 153));
        ptContact.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ptContact.setText("###-###-####");
        ptContact.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ptContactFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                ptContactFocusLost(evt);
            }
        });
        ptContact.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ptContactKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ptContactKeyTyped(evt);
            }
        });

        lblSex1.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        lblSex1.setText("Sex:");

        buttonGroup1.add(ptRbMale);
        ptRbMale.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        ptRbMale.setText("Male");

        buttonGroup1.add(ptRbFemale);
        ptRbFemale.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        ptRbFemale.setText("Female");

        lblBDay1.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        lblBDay1.setText("Date of Birth:");

        jDateChooser3.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooser3PropertyChange(evt);
            }
        });

        jButton9.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        jButton9.setText("Add");
        jButton9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        lblBDay2.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        lblBDay2.setText("Age:");

        ptAge.setEditable(false);
        ptAge.setBackground(new java.awt.Color(255, 255, 255));
        ptAge.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        ptAge.setForeground(new java.awt.Color(153, 153, 153));
        ptAge.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ptAge.setText("Age");
        ptAge.setFocusable(false);

        lblBDay3.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        lblBDay3.setText("Civil Status:");
        lblBDay3.setPreferredSize(new java.awt.Dimension(95, 30));

        ptCivilStatus.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        ptCivilStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Single", "Married", "Divorced", "Widowed" }));
        ptCivilStatus.setMaximumSize(new java.awt.Dimension(100, 30));
        ptCivilStatus.setMinimumSize(new java.awt.Dimension(100, 30));
        ptCivilStatus.setPreferredSize(new java.awt.Dimension(100, 30));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText("+63");

        javax.swing.GroupLayout addPatientLayout = new javax.swing.GroupLayout(addPatient);
        addPatient.setLayout(addPatientLayout);
        addPatientLayout.setHorizontalGroup(
            addPatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addPatientLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(addPatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addPatientLayout.createSequentialGroup()
                        .addComponent(lblContacts1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ptContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(addPatientLayout.createSequentialGroup()
                        .addGroup(addPatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(addPatientLayout.createSequentialGroup()
                                .addComponent(lblBDay3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ptCivilStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(addPatientLayout.createSequentialGroup()
                                .addComponent(lblBDay1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblBDay2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ptAge, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(addPatientLayout.createSequentialGroup()
                                .addComponent(lblSex1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ptRbMale, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ptRbFemale, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 85, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addPatientLayout.createSequentialGroup()
                        .addGroup(addPatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(addPatientLayout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addComponent(ptEmail))
                            .addGroup(addPatientLayout.createSequentialGroup()
                                .addGroup(addPatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblEmail1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblName1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ptLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comma2, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ptFirstName)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ptMiddleName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(28, 28, 28))))
            .addGroup(addPatientLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(addDoctorTitle1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        addPatientLayout.setVerticalGroup(
            addPatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addPatientLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(addDoctorTitle1)
                .addGap(36, 36, 36)
                .addGroup(addPatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblName1)
                    .addComponent(ptLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comma2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ptFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ptMiddleName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(addPatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmail1)
                    .addComponent(ptEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(addPatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblContacts1)
                    .addComponent(ptContact, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(addPatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSex1)
                    .addComponent(ptRbMale)
                    .addComponent(ptRbFemale))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(addPatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblBDay1)
                    .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(addPatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblBDay2)
                        .addComponent(ptAge, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addGroup(addPatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBDay3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ptCivilStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8))
        );

        jLayeredPane1.setLayer(tba, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(listofpatients, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(requestPage, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(schedule, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(addPatient, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(schedule, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(requestPage, javax.swing.GroupLayout.DEFAULT_SIZE, 631, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(listofpatients, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(tba, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                    .addGap(1, 1, 1)
                    .addComponent(addPatient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                    .addComponent(schedule, javax.swing.GroupLayout.PREFERRED_SIZE, 467, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 15, Short.MAX_VALUE)))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                    .addGap(3, 3, 3)
                    .addComponent(requestPage, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
                    .addGap(3, 3, 3)))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(listofpatients, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(tba, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                    .addGap(13, 13, 13)
                    .addComponent(addPatient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(14, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout windowPanelLayout = new javax.swing.GroupLayout(windowPanel);
        windowPanel.setLayout(windowPanelLayout);
        windowPanelLayout.setHorizontalGroup(
            windowPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 643, Short.MAX_VALUE)
            .addGroup(windowPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(windowPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(welcomePage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        windowPanelLayout.setVerticalGroup(
            windowPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 470, Short.MAX_VALUE)
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
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 643, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 25, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(minimized, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(infoPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(9, Short.MAX_VALUE))
        );

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 811, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(frameDrag, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 811, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(background)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 675, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(frameDrag, javax.swing.GroupLayout.DEFAULT_SIZE, 663, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(background)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

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

    private void addDoctorBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addDoctorBtnActionPerformed
        setDisplay(addPatient);
    }//GEN-LAST:event_addDoctorBtnActionPerformed

    private void addPatBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPatBtnActionPerformed
        setDisplay(schedule);
    }//GEN-LAST:event_addPatBtnActionPerformed

    private void reportsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportsBtnActionPerformed
        setDisplay(listofpatients);
    }//GEN-LAST:event_reportsBtnActionPerformed

    private void removeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeBtnActionPerformed
        setDisplay(tba);
    }//GEN-LAST:event_removeBtnActionPerformed

    private void frameDragMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_frameDragMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_frameDragMouseDragged

    private void frameDragMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_frameDragMousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_frameDragMousePressed

    private void ptLastNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ptLastNameFocusGained
        focusOn(ptLastName, "Last Name");
    }//GEN-LAST:event_ptLastNameFocusGained

    private void ptLastNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ptLastNameFocusLost
        focusOff(ptLastName, "Last Name");
    }//GEN-LAST:event_ptLastNameFocusLost

    private void ptFirstNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ptFirstNameFocusGained
        focusOn(ptFirstName, "First Name");
    }//GEN-LAST:event_ptFirstNameFocusGained

    private void ptFirstNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ptFirstNameFocusLost
        focusOff(ptFirstName, "First Name");
    }//GEN-LAST:event_ptFirstNameFocusLost

    private void ptMiddleNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ptMiddleNameFocusGained
        focusOn(ptMiddleName, "Middle Name");
    }//GEN-LAST:event_ptMiddleNameFocusGained

    private void ptMiddleNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ptMiddleNameFocusLost
        focusOff(ptMiddleName, "Middle Name");
    }//GEN-LAST:event_ptMiddleNameFocusLost

    private void ptEmailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ptEmailFocusGained
        focusOn(ptEmail, "youdata@gmail.com");
    }//GEN-LAST:event_ptEmailFocusGained

    private void ptEmailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ptEmailFocusLost
        focusOff(ptEmail, "youdata@gmail.com");
        if (!ptEmail.getText().matches(email_Pattern)) {
            JOptionPane.showMessageDialog(null, "Invalid email format", "Error", JOptionPane.ERROR_MESSAGE);
            ptEmail.setForeground(red);
            jButton9.setEnabled(false);
        } else if (ptEmail.getText().matches(email_Pattern) && !ptEmail.getText().equals("youdata@gmail.com")) {
            ptEmail.setForeground(black);
            jButton9.setEnabled(true);
        } else {
            focusOff(ptEmail, "youdata@gmail.com");
        }
    }//GEN-LAST:event_ptEmailFocusLost

    private void ptContactFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ptContactFocusGained
        focusOn(ptContact, "###-###-####");
        String contactNumber = ptContact.getText();
        if (contactNumber.length() == 10) {
            ptContact.setText(contactNumber.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "$1-$2-$3"));
        }
    }//GEN-LAST:event_ptContactFocusGained

    private void ptContactFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ptContactFocusLost
        focusOff(ptContact, "###-###-####");
    }//GEN-LAST:event_ptContactFocusLost

    private void ptContactKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ptContactKeyPressed
        String contactNumber = ptContact.getText();
        ptContact.setText(contactNumber.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "$1-$2-$3"));
    }//GEN-LAST:event_ptContactKeyPressed

    private void ptContactKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ptContactKeyTyped
        char c = evt.getKeyChar();
        String b = ptContact.getText();

        if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE && c != '-') {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Input only numbers", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (b.length() >= 12) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Reached contact number limit", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_ptContactKeyTyped

    private void jDateChooser3PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooser3PropertyChange
        if ("date".equals(evt.getPropertyName())) {
            updateAgeLabel();
        }
    }//GEN-LAST:event_jDateChooser3PropertyChange

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        insertUserData();
        JOptionPane.showMessageDialog(null, "Username: " + username + "\n Password: " + password, "Credentials", JOptionPane.INFORMATION_MESSAGE);
                
        JTextField[] textFields = {ptFirstName, ptMiddleName, ptLastName, ptAge, ptContact, ptEmail};
        for (int i = 0; i <textFields.length; i++) {
            textFields[i].setText(null);
        }
        
        ptCivilStatus.setSelectedIndex(0);
        buttonGroup1.clearSelection();
        jDateChooser3.setDate(null);
    }//GEN-LAST:event_jButton9ActionPerformed

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
            java.util.logging.Logger.getLogger(Doctor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Doctor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Doctor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Doctor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Doctor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addDoctorBtn;
    private javax.swing.JLabel addDoctorTitle1;
    private javax.swing.JButton addPatBtn;
    private javax.swing.JPanel addPatient;
    private javax.swing.JLabel background;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel comma2;
    private javax.swing.JButton exit;
    private javax.swing.JLabel frameDrag;
    private javax.swing.JLabel infoContact1;
    private javax.swing.JLabel infoFirstName1;
    private javax.swing.JLabel infoLastName1;
    private javax.swing.JLabel infoMiddleName1;
    private javax.swing.JPanel infoPane;
    private javax.swing.JLabel infoUsername1;
    private javax.swing.JLabel iplblContact1;
    private javax.swing.JLabel iplblFN1;
    private javax.swing.JLabel iplblLN1;
    private javax.swing.JLabel iplblMN1;
    private javax.swing.JLabel iplblUN1;
    private javax.swing.JButton jButton9;
    private com.toedter.calendar.JDateChooser jDateChooser3;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel lblBDay1;
    private javax.swing.JLabel lblBDay2;
    private javax.swing.JLabel lblBDay3;
    private javax.swing.JLabel lblContacts1;
    private javax.swing.JLabel lblEmail1;
    private javax.swing.JLabel lblName1;
    private javax.swing.JLabel lblSex1;
    private javax.swing.JPanel listofpatients;
    private javax.swing.JPanel listofpatients1;
    private javax.swing.JLabel logo;
    private javax.swing.JButton minimized;
    private javax.swing.JPanel navbar;
    private javax.swing.JLabel organizationTitle1;
    private javax.swing.JTextField ptAge;
    private javax.swing.JComboBox<String> ptCivilStatus;
    private javax.swing.JTextField ptContact;
    private javax.swing.JTextField ptEmail;
    private javax.swing.JTextField ptFirstName;
    private javax.swing.JTextField ptLastName;
    private javax.swing.JTextField ptMiddleName;
    private javax.swing.JRadioButton ptRbFemale;
    private javax.swing.JRadioButton ptRbMale;
    private javax.swing.JButton removeBtn;
    private javax.swing.JButton reportsBtn;
    private javax.swing.JPanel requestPage;
    private javax.swing.JPanel schedule;
    private javax.swing.JPanel tba;
    private javax.swing.JLabel welcomeAdmin;
    private javax.swing.JPanel welcomePage;
    private javax.swing.JPanel windowPanel;
    // End of variables declaration//GEN-END:variables
}
