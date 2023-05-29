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
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

/*
 * Authors:
 * Arcega, Lance Angelo P.
 * Muñoz, Nathan Sheary G.
 * Pare, Neo Jezer A.
 */
public class Admin extends javax.swing.JFrame {

    Connection con;
    DefaultTableModel model = new DefaultTableModel();


    /**
     * Creates new form Admin_Page
     */
    public Admin() {
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

    private static final String email_Pattern = "^[_A-Za-z0-9-\\+ñÑ]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    int xMouse, yMouse;
    String uid;
    String username;
    String password;
    String setRole;

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
        LocalDate selectedDate = jDateChooser1.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(selectedDate, currentDate);
        int age = period.getYears();
        tfAge.setForeground(black);
        tfAge.setText(String.valueOf(age));
    }

    public void setDisplay(JPanel Panel) {
        for (int i = 0; i < jLayeredPane1.getComponentCount(); i++) {
            JComponent component = (JComponent) jLayeredPane1.getComponent(i);
            component.setVisible(false);
        }
        jLayeredPane1.setVisible(true);
        Panel.setVisible(true);
    }
    
    void determineRole(JButton button, String role){
        if(role == "doctor"){
            lblAddUser.setText("Add Doctor");
            setRole = "doctor";
            return;
        }
        else if(role == "patient"){
            lblAddUser.setText("Add Patient");
            setRole = "patient";
            return;
        }
    }
    
    void insertUserData() {
        String lastName = tfLastName.getText();
        String firstName = tfFirstName.getText();
        String middleName = tfMiddleName.getText();
        String email = tfEmail.getText();
        String contact = "+63 " + tfContact.getText();
        String sex;
        if (rbMaleSex.isSelected()) {
            sex = rbMaleSex.getText();
        } else {
            sex = rbFemaleSex.getText();
        }
        SimpleDateFormat birthDateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateOfBirth = birthDateFormatter.format(jDateChooser1.getDate());
        String civilStatus;
        if (cbCivilStatus.getSelectedIndex() == 0) {
            civilStatus = "Single";
        } else if (cbCivilStatus.getSelectedIndex() == 1) {
            civilStatus = "Married";
        } else if (cbCivilStatus.getSelectedIndex() == 2) {
            civilStatus = "Divorced";
        } else {
            civilStatus = "Widowed";
        }
        int age = Integer.parseInt(tfAge.getText());
        String address = "";
        int height = 0;
        int weight = 0;
        String role = setRole;
        int username_count = 0;
        do {
            try {
                username = new UIDGenerator().generateUID(lastName, firstName, jDateChooser1.getDate(), uid);
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

    public void showList() {
        jTable3.setModel(model);
        String role = setRole;
        try {
            String query = "SELECT username, lastName, firstName, sex, civilStatus, contact FROM userinfo WHERE role = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, role);

            ResultSet rs = pstmt.executeQuery();
            // Get the metadata of the ResultSet  
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

            deleteBtn1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Get the selected row index
                    int selectedRow = jTable3.getSelectedRow();

                    // Ensure a row is selected
                    if (selectedRow != -1) {
                        // Retrieve the data from the selected row
                        Object[] rowData = new Object[columnCount];
                        for (int i = 0; i < columnCount; i++) {
                            rowData[i] = jTable3.getValueAt(selectedRow, i);
                        }

                        try {
                            // Prepare the DELETE query
                            String deleteQuery = "DELETE FROM userinfo WHERE username = ?";
                            PreparedStatement pstmt = con.prepareStatement(deleteQuery);

                            // Assuming the username is in the first column (adjust the index if needed)
                            pstmt.setObject(1, rowData[0]);

                            // Execute the delete query
                            pstmt.executeUpdate();

                            // Remove the selected row from the model
                            model.removeRow(selectedRow);

                            // Update the JTable to reflect the changes
                            jTable3.setModel(model);

                            rs.close();
                            pstmt.close();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showInfo() { //make this a class
        try {
            String query = "SELECT username, firstName, contact, lastName, middleName FROM userinfo WHERE username = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, this.username);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String retrievedUsername = resultSet.getString("username");
                String firstName = resultSet.getString("firstName");
                String contact = resultSet.getString("contact");
                String lastName = resultSet.getString("lastName");
                String middleName = resultSet.getString("middleName");

                iplblUN.setText(retrievedUsername);
                iplblFN.setText(firstName);
                iplblLN.setText(lastName);
                iplblMN.setText(middleName);
                iplblContact.setText(contact);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        mainAdminPanel = new javax.swing.JPanel();
        exit = new javax.swing.JButton();
        minimized = new javax.swing.JButton();
        logoIcon = new javax.swing.JLabel();
        informationPanel = new javax.swing.JPanel();
        organizationTitle = new javax.swing.JLabel();
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
        sidebar = new javax.swing.JPanel();
        navbar = new javax.swing.JPanel();
        addDoctorBtn = new javax.swing.JButton();
        addPatBtn = new javax.swing.JButton();
        ptListBtn = new javax.swing.JButton();
        mdListBtn = new javax.swing.JButton();
        reportsBtn = new javax.swing.JButton();
        uiButtonPanel = new javax.swing.JPanel();
        windowPanel = new javax.swing.JPanel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        addUser = new javax.swing.JPanel();
        lblName = new javax.swing.JLabel();
        lblAddUser = new javax.swing.JLabel();
        lblComma = new javax.swing.JLabel();
        tfLastName = new javax.swing.JTextField();
        tfFirstName = new javax.swing.JTextField();
        tfMiddleName = new javax.swing.JTextField();
        lblEmail = new javax.swing.JLabel();
        tfEmail = new javax.swing.JTextField();
        lblContact = new javax.swing.JLabel();
        tfContact = new javax.swing.JTextField();
        lblSex = new javax.swing.JLabel();
        rbMaleSex = new javax.swing.JRadioButton();
        rbFemaleSex = new javax.swing.JRadioButton();
        lblBirthDate = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        btnAddUserToSQL = new javax.swing.JButton();
        lblAge = new javax.swing.JLabel();
        tfAge = new javax.swing.JTextField();
        lblCivilStatus = new javax.swing.JLabel();
        cbCivilStatus = new javax.swing.JComboBox<>();
        lblPH63 = new javax.swing.JLabel();
        listPage = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        updBtn = new javax.swing.JButton();
        deleteBtn1 = new javax.swing.JButton();
        reportsPage = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        welcomePage = new javax.swing.JPanel();
        welcomeAdmin = new javax.swing.JLabel();
        frameDrag = new javax.swing.JLabel();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        mainAdminPanel.setBackground(new java.awt.Color(187, 209, 241));
        mainAdminPanel.setPreferredSize(new java.awt.Dimension(800, 650));

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

        logoIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/my/medata/images/Logo.png"))); // NOI18N

        informationPanel.setPreferredSize(new java.awt.Dimension(631, 139));
        informationPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        organizationTitle.setBackground(new java.awt.Color(153, 255, 153));
        organizationTitle.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        organizationTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        organizationTitle.setText("Transformative Unified Workflow Assistive Technology");
        organizationTitle.setOpaque(true);
        informationPanel.add(organizationTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 620, 60));

        infoUsername.setBackground(new java.awt.Color(102, 255, 204));
        infoUsername.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        infoUsername.setText("Username");
        infoUsername.setOpaque(true);
        informationPanel.add(infoUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 100, 30));

        infoContact.setBackground(new java.awt.Color(102, 255, 204));
        infoContact.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        infoContact.setText("Contact");
        infoContact.setOpaque(true);
        informationPanel.add(infoContact, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 100, 120, 30));

        iplblUN.setBackground(new java.awt.Color(102, 255, 204));
        iplblUN.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iplblUN.setText("username");
        iplblUN.setOpaque(true);
        informationPanel.add(iplblUN, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 100, 30));

        iplblLN.setBackground(new java.awt.Color(102, 255, 204));
        iplblLN.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iplblLN.setText("lastName");
        iplblLN.setOpaque(true);
        informationPanel.add(iplblLN, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, 100, 30));

        infoLastName.setBackground(new java.awt.Color(102, 255, 204));
        infoLastName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        infoLastName.setText("Last Name");
        infoLastName.setOpaque(true);
        informationPanel.add(infoLastName, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, 100, 30));

        iplblFN.setBackground(new java.awt.Color(102, 255, 204));
        iplblFN.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iplblFN.setText("firstName");
        iplblFN.setOpaque(true);
        informationPanel.add(iplblFN, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 70, 100, 30));

        infoFirstName.setBackground(new java.awt.Color(102, 255, 204));
        infoFirstName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        infoFirstName.setText("First Name");
        infoFirstName.setOpaque(true);
        informationPanel.add(infoFirstName, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 100, 100, 30));

        iplblMN.setBackground(new java.awt.Color(102, 255, 204));
        iplblMN.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iplblMN.setText("middleName");
        iplblMN.setOpaque(true);
        informationPanel.add(iplblMN, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 70, 100, 30));

        infoMiddleName.setBackground(new java.awt.Color(102, 255, 204));
        infoMiddleName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        infoMiddleName.setText("Middle Name");
        infoMiddleName.setOpaque(true);
        informationPanel.add(infoMiddleName, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 100, 100, 30));

        iplblContact.setBackground(new java.awt.Color(102, 255, 204));
        iplblContact.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iplblContact.setText("contact");
        iplblContact.setOpaque(true);
        informationPanel.add(iplblContact, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 70, 120, 30));

        sidebar.setOpaque(false);

        navbar.setOpaque(false);
        navbar.setLayout(new java.awt.GridLayout(5, 1));

        addDoctorBtn.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        addDoctorBtn.setText("Add Doctor");
        addDoctorBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addDoctorBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addDoctorBtnActionPerformed(evt);
            }
        });
        navbar.add(addDoctorBtn);

        addPatBtn.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        addPatBtn.setText("Add Patient");
        addPatBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPatBtnActionPerformed(evt);
            }
        });
        navbar.add(addPatBtn);

        ptListBtn.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        ptListBtn.setText("List of Patients");
        ptListBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ptListBtnActionPerformed(evt);
            }
        });
        navbar.add(ptListBtn);

        mdListBtn.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        mdListBtn.setText("List of Doctors");
        mdListBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mdListBtnActionPerformed(evt);
            }
        });
        navbar.add(mdListBtn);

        reportsBtn.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        reportsBtn.setText("Reports");
        reportsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportsBtnActionPerformed(evt);
            }
        });
        navbar.add(reportsBtn);

        javax.swing.GroupLayout sidebarLayout = new javax.swing.GroupLayout(sidebar);
        sidebar.setLayout(sidebarLayout);
        sidebarLayout.setHorizontalGroup(
            sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(navbar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        sidebarLayout.setVerticalGroup(
            sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidebarLayout.createSequentialGroup()
                .addComponent(navbar, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        uiButtonPanel.setPreferredSize(new java.awt.Dimension(689, 461));

        windowPanel.setPreferredSize(new java.awt.Dimension(689, 641));

        jLayeredPane1.setPreferredSize(new java.awt.Dimension(689, 461));

        addUser.setMaximumSize(new java.awt.Dimension(600, 400));
        addUser.setMinimumSize(new java.awt.Dimension(600, 400));
        addUser.setPreferredSize(new java.awt.Dimension(600, 400));
        addUser.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblName.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        lblName.setText("Name:");
        addUser.add(lblName, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 89, -1, -1));

        lblAddUser.setFont(new java.awt.Font("Quicksand Medium", 0, 24)); // NOI18N
        lblAddUser.setText("Add User");
        addUser.add(lblAddUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 18, -1, -1));

        lblComma.setText(",");
        addUser.add(lblComma, new org.netbeans.lib.awtextra.AbsoluteConstraints(236, 92, 6, 22));

        tfLastName.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tfLastName.setForeground(new java.awt.Color(153, 153, 153));
        tfLastName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfLastName.setText("Last Name");
        tfLastName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfLastNameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfLastNameFocusLost(evt);
            }
        });
        addUser.add(tfLastName, new org.netbeans.lib.awtextra.AbsoluteConstraints(97, 86, 133, 30));

        tfFirstName.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tfFirstName.setForeground(new java.awt.Color(153, 153, 153));
        tfFirstName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfFirstName.setText("First Name");
        tfFirstName.setMaximumSize(new java.awt.Dimension(190, 30));
        tfFirstName.setMinimumSize(new java.awt.Dimension(190, 30));
        tfFirstName.setPreferredSize(new java.awt.Dimension(190, 30));
        tfFirstName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfFirstNameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfFirstNameFocusLost(evt);
            }
        });
        addUser.add(tfFirstName, new org.netbeans.lib.awtextra.AbsoluteConstraints(248, 86, 190, -1));

        tfMiddleName.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tfMiddleName.setForeground(new java.awt.Color(153, 153, 153));
        tfMiddleName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfMiddleName.setText("Middle Name");
        tfMiddleName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfMiddleNameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfMiddleNameFocusLost(evt);
            }
        });
        addUser.add(tfMiddleName, new org.netbeans.lib.awtextra.AbsoluteConstraints(447, 86, 140, 30));

        lblEmail.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        lblEmail.setText("Email:");
        addUser.add(lblEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 131, 52, -1));

        tfEmail.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tfEmail.setForeground(new java.awt.Color(153, 153, 153));
        tfEmail.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfEmail.setText("youdata@gmail.com");
        tfEmail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfEmailFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfEmailFocusLost(evt);
            }
        });
        addUser.add(tfEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(97, 128, 490, 30));

        lblContact.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        lblContact.setText("Contact Number:");
        addUser.add(lblContact, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 171, -1, -1));

        tfContact.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tfContact.setForeground(new java.awt.Color(153, 153, 153));
        tfContact.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfContact.setText("###-###-####");
        tfContact.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfContactFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfContactFocusLost(evt);
            }
        });
        tfContact.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfContactKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfContactKeyTyped(evt);
            }
        });
        addUser.add(tfContact, new org.netbeans.lib.awtextra.AbsoluteConstraints(224, 170, -1, 30));

        lblSex.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        lblSex.setText("Sex:");
        addUser.add(lblSex, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 214, -1, -1));

        buttonGroup1.add(rbMaleSex);
        rbMaleSex.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        rbMaleSex.setSelected(true);
        rbMaleSex.setText("Male");
        addUser.add(rbMaleSex, new org.netbeans.lib.awtextra.AbsoluteConstraints(86, 212, 65, -1));

        buttonGroup1.add(rbFemaleSex);
        rbFemaleSex.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        rbFemaleSex.setText("Female");
        addUser.add(rbFemaleSex, new org.netbeans.lib.awtextra.AbsoluteConstraints(157, 212, 118, -1));

        lblBirthDate.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        lblBirthDate.setText("Date of Birth:");
        addUser.add(lblBirthDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 253, -1, -1));

        jDateChooser1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooser1PropertyChange(evt);
            }
        });
        addUser.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(156, 253, 192, 30));

        btnAddUserToSQL.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnAddUserToSQL.setText("Add");
        btnAddUserToSQL.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAddUserToSQL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddUserToSQLActionPerformed(evt);
            }
        });
        addUser.add(btnAddUserToSQL, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 370, 130, 39));

        lblAge.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        lblAge.setText("Age:");
        addUser.add(lblAge, new org.netbeans.lib.awtextra.AbsoluteConstraints(366, 256, -1, -1));

        tfAge.setEditable(false);
        tfAge.setBackground(new java.awt.Color(255, 255, 255));
        tfAge.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tfAge.setForeground(new java.awt.Color(153, 153, 153));
        tfAge.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfAge.setText("Age");
        tfAge.setFocusable(false);
        addUser.add(tfAge, new org.netbeans.lib.awtextra.AbsoluteConstraints(408, 253, 133, 30));

        lblCivilStatus.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        lblCivilStatus.setText("Civil Status:");
        lblCivilStatus.setPreferredSize(new java.awt.Dimension(95, 30));
        addUser.add(lblCivilStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 295, -1, -1));

        cbCivilStatus.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        cbCivilStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Single", "Married", "Divorced", "Widowed" }));
        cbCivilStatus.setMaximumSize(new java.awt.Dimension(100, 30));
        cbCivilStatus.setMinimumSize(new java.awt.Dimension(100, 30));
        cbCivilStatus.setPreferredSize(new java.awt.Dimension(100, 30));
        addUser.add(cbCivilStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 296, 175, -1));

        lblPH63.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblPH63.setText("+63");
        addUser.add(lblPH63, new org.netbeans.lib.awtextra.AbsoluteConstraints(186, 170, -1, -1));

        listPage.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1"
            }
        ));
        jTable3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane9.setViewportView(jTable3);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
        );

        jScrollPane8.setViewportView(jPanel5);

        updBtn.setText("Update");

        deleteBtn1.setText("Delete");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 606, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 14, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(107, 107, 107)
                .addComponent(updBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(16, 16, 16)
                    .addComponent(deleteBtn1)
                    .addContainerGap(532, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(updBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addContainerGap(376, Short.MAX_VALUE)
                    .addComponent(deleteBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        listPage.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 620, 410));

        jLabel7.setText("reports PAge");

        javax.swing.GroupLayout reportsPageLayout = new javax.swing.GroupLayout(reportsPage);
        reportsPage.setLayout(reportsPageLayout);
        reportsPageLayout.setHorizontalGroup(
            reportsPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reportsPageLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addContainerGap(956, Short.MAX_VALUE))
        );
        reportsPageLayout.setVerticalGroup(
            reportsPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reportsPageLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addContainerGap(445, Short.MAX_VALUE))
        );

        jLayeredPane1.setLayer(addUser, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(listPage, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(reportsPage, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 631, Short.MAX_VALUE)
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(reportsPage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(listPage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                    .addGap(1, 1, 1)
                    .addComponent(addUser, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 460, Short.MAX_VALUE)
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(reportsPage, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(listPage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(addUser, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        welcomeAdmin.setFont(new java.awt.Font("Quicksand", 0, 12)); // NOI18N
        welcomeAdmin.setText("Welcome Admin!");

        javax.swing.GroupLayout welcomePageLayout = new javax.swing.GroupLayout(welcomePage);
        welcomePage.setLayout(welcomePageLayout);
        welcomePageLayout.setHorizontalGroup(
            welcomePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(welcomePageLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(welcomeAdmin)
                .addContainerGap(549, Short.MAX_VALUE))
        );
        welcomePageLayout.setVerticalGroup(
            welcomePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(welcomePageLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(welcomeAdmin)
                .addContainerGap(387, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout windowPanelLayout = new javax.swing.GroupLayout(windowPanel);
        windowPanel.setLayout(windowPanelLayout);
        windowPanelLayout.setHorizontalGroup(
            windowPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 631, Short.MAX_VALUE)
            .addGroup(windowPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(windowPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(welcomePage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        windowPanelLayout.setVerticalGroup(
            windowPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(windowPanelLayout.createSequentialGroup()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(windowPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(windowPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(welcomePage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(199, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout uiButtonPanelLayout = new javax.swing.GroupLayout(uiButtonPanel);
        uiButtonPanel.setLayout(uiButtonPanelLayout);
        uiButtonPanelLayout.setHorizontalGroup(
            uiButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(windowPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 631, Short.MAX_VALUE)
        );
        uiButtonPanelLayout.setVerticalGroup(
            uiButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(windowPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout mainAdminPanelLayout = new javax.swing.GroupLayout(mainAdminPanel);
        mainAdminPanel.setLayout(mainAdminPanelLayout);
        mainAdminPanelLayout.setHorizontalGroup(
            mainAdminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainAdminPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainAdminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(logoIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sidebar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainAdminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(mainAdminPanelLayout.createSequentialGroup()
                        .addGroup(mainAdminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(informationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(uiButtonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 631, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 1, Short.MAX_VALUE))
                    .addGroup(mainAdminPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(minimized, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        mainAdminPanelLayout.setVerticalGroup(
            mainAdminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainAdminPanelLayout.createSequentialGroup()
                .addGroup(mainAdminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(mainAdminPanelLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(logoIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(mainAdminPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(mainAdminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(minimized, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(informationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(mainAdminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sidebar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(uiButtonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        getContentPane().add(mainAdminPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 650));

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

    private void addDoctorBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addDoctorBtnActionPerformed
        setDisplay(addUser);
        determineRole(addDoctorBtn, "doctor");
    }//GEN-LAST:event_addDoctorBtnActionPerformed

    private void addPatBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPatBtnActionPerformed
        setDisplay(addUser);
        determineRole(addPatBtn, "patient");
    }//GEN-LAST:event_addPatBtnActionPerformed

    private void ptListBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ptListBtnActionPerformed
        setDisplay(listPage);
        determineRole(ptListBtn, "patient");
        showList();
    }//GEN-LAST:event_ptListBtnActionPerformed

    private void mdListBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mdListBtnActionPerformed
        setDisplay(listPage);
        determineRole(mdListBtn, "doctor");
        showList();
    }//GEN-LAST:event_mdListBtnActionPerformed

    private void reportsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportsBtnActionPerformed
        setDisplay(reportsPage);
    }//GEN-LAST:event_reportsBtnActionPerformed

    private void btnAddUserToSQLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddUserToSQLActionPerformed
        insertUserData();
        JOptionPane.showMessageDialog(null, "Username: " + username + "\n Password: " + password, "Credentials", JOptionPane.INFORMATION_MESSAGE);
                
        JTextField[] textFields = {tfFirstName, tfMiddleName, tfLastName, tfAge, tfContact, tfEmail};
        String[] phText = {"First Name", "Middle Name", "Last Name", "Age", "###-###-####", "youdata@gmail.com"};
        for (int i = 0; i <textFields.length; i++) {
            textFields[i].setText(phText[i]);
            textFields[i].setForeground(gray);
            textFields[i].setHorizontalAlignment(CENTER);
        }
        rbMaleSex.setSelected(true);
        cbCivilStatus.setSelectedIndex(0);
        Calendar calendar = Calendar.getInstance();
        java.util.Date currentDate = calendar.getTime();
        Date sqlDate = new Date(currentDate.getTime());
        jDateChooser1.setDate(sqlDate);
    }//GEN-LAST:event_btnAddUserToSQLActionPerformed

    private void tfLastNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfLastNameFocusGained
        focusOn(tfLastName, "Last Name");
    }//GEN-LAST:event_tfLastNameFocusGained

    private void tfLastNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfLastNameFocusLost
        focusOff(tfLastName, "Last Name");
    }//GEN-LAST:event_tfLastNameFocusLost

    private void tfFirstNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfFirstNameFocusGained
        focusOn(tfFirstName, "First Name");
    }//GEN-LAST:event_tfFirstNameFocusGained

    private void tfFirstNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfFirstNameFocusLost
        focusOff(tfFirstName, "First Name");
    }//GEN-LAST:event_tfFirstNameFocusLost

    private void tfMiddleNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfMiddleNameFocusGained
        focusOn(tfMiddleName, "Middle Name");
    }//GEN-LAST:event_tfMiddleNameFocusGained

    private void tfMiddleNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfMiddleNameFocusLost
        focusOff(tfMiddleName, "Middle Name");
    }//GEN-LAST:event_tfMiddleNameFocusLost

    private void tfEmailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfEmailFocusGained
        focusOn(tfEmail, "youdata@gmail.com");
    }//GEN-LAST:event_tfEmailFocusGained

    private void tfEmailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfEmailFocusLost
        if (!tfEmail.getText().matches(email_Pattern)) {
            JOptionPane.showMessageDialog(null, "Invalid email format", "Error", JOptionPane.ERROR_MESSAGE);
            tfEmail.setForeground(red);
            btnAddUserToSQL.setEnabled(false);
        } else if (tfEmail.getText().matches(email_Pattern) && !tfEmail.getText().equals("youdata@gmail.com")) {
            tfEmail.setForeground(black);
            btnAddUserToSQL.setEnabled(true);
        } else {
            focusOff(tfEmail, "youdata@gmail.com");
        }
    }//GEN-LAST:event_tfEmailFocusLost

    private void tfContactFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfContactFocusGained
        focusOn(tfContact, "###-###-####");
        String contactNumber = tfContact.getText();
        if (contactNumber.length() == 10) {
            tfContact.setText(contactNumber.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "$1-$2-$3"));
        }
    }//GEN-LAST:event_tfContactFocusGained

    private void tfContactFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfContactFocusLost
        focusOff(tfContact, "###-###-####");
    }//GEN-LAST:event_tfContactFocusLost

    private void jDateChooser1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooser1PropertyChange
        if ("date".equals(evt.getPropertyName())) {
            updateAgeLabel();
        }
    }//GEN-LAST:event_jDateChooser1PropertyChange

    private void tfContactKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfContactKeyTyped
        char c = evt.getKeyChar();
        String b = tfContact.getText();

        if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE && c != '-') {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Numbers input only", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (b.length() >= 12) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Maxed Character Input", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_tfContactKeyTyped

    private void tfContactKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfContactKeyPressed
        String contactNumber = tfContact.getText();
        tfContact.setText(contactNumber.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "$1-$2-$3"));
    }//GEN-LAST:event_tfContactKeyPressed

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
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Admin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addDoctorBtn;
    private javax.swing.JButton addPatBtn;
    private javax.swing.JPanel addUser;
    private javax.swing.JLabel background;
    private javax.swing.JButton btnAddUserToSQL;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbCivilStatus;
    private javax.swing.JButton deleteBtn1;
    private javax.swing.JButton exit;
    private javax.swing.JLabel frameDrag;
    private javax.swing.JLabel infoContact;
    private javax.swing.JLabel infoFirstName;
    private javax.swing.JLabel infoLastName;
    private javax.swing.JLabel infoMiddleName;
    private javax.swing.JLabel infoUsername;
    private javax.swing.JPanel informationPanel;
    private javax.swing.JLabel iplblContact;
    private javax.swing.JLabel iplblFN;
    private javax.swing.JLabel iplblLN;
    private javax.swing.JLabel iplblMN;
    private javax.swing.JLabel iplblUN;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTable jTable3;
    private javax.swing.JLabel lblAddUser;
    private javax.swing.JLabel lblAge;
    private javax.swing.JLabel lblBirthDate;
    private javax.swing.JLabel lblCivilStatus;
    private javax.swing.JLabel lblComma;
    private javax.swing.JLabel lblContact;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblPH63;
    private javax.swing.JLabel lblSex;
    private javax.swing.JPanel listPage;
    private javax.swing.JLabel logoIcon;
    private javax.swing.JPanel mainAdminPanel;
    private javax.swing.JButton mdListBtn;
    private javax.swing.JButton minimized;
    private javax.swing.JPanel navbar;
    private javax.swing.JLabel organizationTitle;
    private javax.swing.JButton ptListBtn;
    private javax.swing.JRadioButton rbFemaleSex;
    private javax.swing.JRadioButton rbMaleSex;
    private javax.swing.JButton reportsBtn;
    private javax.swing.JPanel reportsPage;
    private javax.swing.JPanel sidebar;
    private javax.swing.JTextField tfAge;
    private javax.swing.JTextField tfContact;
    private javax.swing.JTextField tfEmail;
    private javax.swing.JTextField tfFirstName;
    private javax.swing.JTextField tfLastName;
    private javax.swing.JTextField tfMiddleName;
    private javax.swing.JPanel uiButtonPanel;
    private javax.swing.JButton updBtn;
    private javax.swing.JLabel welcomeAdmin;
    private javax.swing.JPanel welcomePage;
    private javax.swing.JPanel windowPanel;
    // End of variables declaration//GEN-END:variables

}
