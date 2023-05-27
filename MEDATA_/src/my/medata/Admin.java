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
import javax.swing.JButton;

/*
 * Authors:
 * Arcega, Lance Angelo P.
 * Muñoz, Nathan Sheary G.
 * Pare, Neo Jezer A.
 */
public class Admin extends javax.swing.JFrame {

    Connection con;

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
    }

    private static final String email_Pattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

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
        String contact = tfContact.getText();
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

        createUser.processInput(lastName, firstName, middleName, age, dateOfBirth, address, contact, email, sex, civilStatus, height, weight, username, password, role);
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
        adminPicture = new javax.swing.JLabel();
        organizationTitle = new javax.swing.JLabel();
        adminInformation = new javax.swing.JLabel();
        sidebar = new javax.swing.JPanel();
        navbar = new javax.swing.JPanel();
        addDoctorBtn = new javax.swing.JButton();
        addPatBtn = new javax.swing.JButton();
        requestsBtn = new javax.swing.JButton();
        removeBtn = new javax.swing.JButton();
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
        removePage = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        reportsPage = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        requestPage = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        addPatient = new javax.swing.JPanel();
        lblptName = new javax.swing.JLabel();
        ptEmail1 = new javax.swing.JTextField();
        ptMiddleName1 = new javax.swing.JTextField();
        comma1 = new javax.swing.JLabel();
        ptLastName = new javax.swing.JTextField();
        lblptEmail = new javax.swing.JLabel();
        ptFirstName1 = new javax.swing.JTextField();
        addPatientTitle = new javax.swing.JLabel();
        lblptSex = new javax.swing.JLabel();
        ptContacts1 = new javax.swing.JTextField();
        lblptBday = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        lblptContact = new javax.swing.JLabel();
        jRadioButton3 = new javax.swing.JRadioButton();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jRadioButton4 = new javax.swing.JRadioButton();
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

        adminPicture.setBackground(new java.awt.Color(0, 204, 204));
        adminPicture.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        adminPicture.setText("picture");
        adminPicture.setOpaque(true);

        organizationTitle.setBackground(new java.awt.Color(153, 255, 153));
        organizationTitle.setText("jLabel2");
        organizationTitle.setOpaque(true);

        adminInformation.setBackground(new java.awt.Color(102, 255, 204));
        adminInformation.setText("jLabel3");
        adminInformation.setOpaque(true);

        javax.swing.GroupLayout informationPanelLayout = new javax.swing.GroupLayout(informationPanel);
        informationPanel.setLayout(informationPanelLayout);
        informationPanelLayout.setHorizontalGroup(
            informationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, informationPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(informationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(organizationTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
                    .addComponent(adminInformation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(adminPicture, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        informationPanelLayout.setVerticalGroup(
            informationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(informationPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(informationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(adminPicture, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(informationPanelLayout.createSequentialGroup()
                        .addComponent(organizationTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(adminInformation, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        sidebar.setOpaque(false);

        navbar.setOpaque(false);
        navbar.setLayout(new java.awt.GridLayout(5, 1));

        addDoctorBtn.setFont(new java.awt.Font("Quicksand", 0, 12)); // NOI18N
        addDoctorBtn.setText("Add Doctor");
        addDoctorBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addDoctorBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addDoctorBtnActionPerformed(evt);
            }
        });
        navbar.add(addDoctorBtn);

        addPatBtn.setFont(new java.awt.Font("Quicksand", 0, 12)); // NOI18N
        addPatBtn.setText("Add Patient");
        addPatBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPatBtnActionPerformed(evt);
            }
        });
        navbar.add(addPatBtn);

        requestsBtn.setFont(new java.awt.Font("Quicksand", 0, 12)); // NOI18N
        requestsBtn.setText("Requests");
        requestsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                requestsBtnActionPerformed(evt);
            }
        });
        navbar.add(requestsBtn);

        removeBtn.setFont(new java.awt.Font("Quicksand", 0, 12)); // NOI18N
        removeBtn.setText("Remove");
        removeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeBtnActionPerformed(evt);
            }
        });
        navbar.add(removeBtn);

        reportsBtn.setFont(new java.awt.Font("Quicksand", 0, 12)); // NOI18N
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

        addUser.setMaximumSize(new java.awt.Dimension(615, 455));
        addUser.setMinimumSize(new java.awt.Dimension(615, 455));
        addUser.setPreferredSize(new java.awt.Dimension(615, 455));

        lblName.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        lblName.setText("Name:");

        lblAddUser.setFont(new java.awt.Font("Quicksand Medium", 0, 24)); // NOI18N
        lblAddUser.setText("Add User");

        lblComma.setText(",");

        tfLastName.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
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

        tfFirstName.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        tfFirstName.setForeground(new java.awt.Color(153, 153, 153));
        tfFirstName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfFirstName.setText("First Name");
        tfFirstName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfFirstNameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfFirstNameFocusLost(evt);
            }
        });

        tfMiddleName.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
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

        lblEmail.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        lblEmail.setText("Email:");

        tfEmail.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
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

        lblContact.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        lblContact.setText("Contact Number:");

        tfContact.setFont(new java.awt.Font("Quicksand", 0, 14)); // NOI18N
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

        lblSex.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        lblSex.setText("Sex:");

        buttonGroup1.add(rbMaleSex);
        rbMaleSex.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        rbMaleSex.setText("Male");

        buttonGroup1.add(rbFemaleSex);
        rbFemaleSex.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        rbFemaleSex.setText("Female");

        lblBirthDate.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        lblBirthDate.setText("Date of Birth:");

        jDateChooser1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooser1PropertyChange(evt);
            }
        });

        btnAddUserToSQL.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        btnAddUserToSQL.setText("Add");
        btnAddUserToSQL.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAddUserToSQL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddUserToSQLActionPerformed(evt);
            }
        });

        lblAge.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        lblAge.setText("Age:");

        tfAge.setEditable(false);
        tfAge.setBackground(new java.awt.Color(255, 255, 255));
        tfAge.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        tfAge.setForeground(new java.awt.Color(153, 153, 153));
        tfAge.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfAge.setText("Age");
        tfAge.setFocusable(false);

        lblCivilStatus.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        lblCivilStatus.setText("Civil Status:");
        lblCivilStatus.setPreferredSize(new java.awt.Dimension(95, 30));

        cbCivilStatus.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        cbCivilStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Single", "Married", "Divorced", "Widowed" }));
        cbCivilStatus.setMaximumSize(new java.awt.Dimension(100, 30));
        cbCivilStatus.setMinimumSize(new java.awt.Dimension(100, 30));
        cbCivilStatus.setPreferredSize(new java.awt.Dimension(100, 30));

        lblPH63.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblPH63.setText("+63");

        javax.swing.GroupLayout addUserLayout = new javax.swing.GroupLayout(addUser);
        addUser.setLayout(addUserLayout);
        addUserLayout.setHorizontalGroup(
            addUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addUserLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(addUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addUserLayout.createSequentialGroup()
                        .addComponent(lblContact)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblPH63)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(addUserLayout.createSequentialGroup()
                        .addGroup(addUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(addUserLayout.createSequentialGroup()
                                .addComponent(lblCivilStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbCivilStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(addUserLayout.createSequentialGroup()
                                .addComponent(lblBirthDate)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblAge)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfAge, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(addUserLayout.createSequentialGroup()
                                .addComponent(lblSex)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rbMaleSex, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rbFemaleSex, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 89, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addUserLayout.createSequentialGroup()
                        .addGroup(addUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(addUserLayout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addComponent(tfEmail))
                            .addGroup(addUserLayout.createSequentialGroup()
                                .addGroup(addUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblComma, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfFirstName)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfMiddleName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(28, 28, 28))))
            .addGroup(addUserLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(lblAddUser)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(btnAddUserToSQL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        addUserLayout.setVerticalGroup(
            addUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addUserLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(lblAddUser)
                .addGap(36, 36, 36)
                .addGroup(addUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblName)
                    .addComponent(tfLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblComma, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfMiddleName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(addUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmail)
                    .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(addUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblContact)
                    .addComponent(tfContact, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPH63))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(addUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSex)
                    .addComponent(rbMaleSex)
                    .addComponent(rbFemaleSex))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(addUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblBirthDate)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(addUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblAge)
                        .addComponent(tfAge, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addGroup(addUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCivilStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbCivilStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                .addComponent(btnAddUserToSQL, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8))
        );

        jLabel8.setText("remove Page");

        javax.swing.GroupLayout removePageLayout = new javax.swing.GroupLayout(removePage);
        removePage.setLayout(removePageLayout);
        removePageLayout.setHorizontalGroup(
            removePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(removePageLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addContainerGap(562, Short.MAX_VALUE))
        );
        removePageLayout.setVerticalGroup(
            removePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(removePageLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addContainerGap(445, Short.MAX_VALUE))
        );

        jLabel7.setText("reports PAge");

        javax.swing.GroupLayout reportsPageLayout = new javax.swing.GroupLayout(reportsPage);
        reportsPage.setLayout(reportsPageLayout);
        reportsPageLayout.setHorizontalGroup(
            reportsPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reportsPageLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addContainerGap(563, Short.MAX_VALUE))
        );
        reportsPageLayout.setVerticalGroup(
            reportsPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reportsPageLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addContainerGap(445, Short.MAX_VALUE))
        );

        requestPage.setPreferredSize(new java.awt.Dimension(660, 461));

        jLabel6.setText("request page");

        javax.swing.GroupLayout requestPageLayout = new javax.swing.GroupLayout(requestPage);
        requestPage.setLayout(requestPageLayout);
        requestPageLayout.setHorizontalGroup(
            requestPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(requestPageLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addContainerGap(557, Short.MAX_VALUE))
        );
        requestPageLayout.setVerticalGroup(
            requestPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(requestPageLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addContainerGap(436, Short.MAX_VALUE))
        );

        lblptName.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        lblptName.setText("Name:");

        ptEmail1.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        ptEmail1.setForeground(new java.awt.Color(153, 153, 153));
        ptEmail1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ptEmail1.setText("youdata@gmail.com");

        ptMiddleName1.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        ptMiddleName1.setForeground(new java.awt.Color(153, 153, 153));
        ptMiddleName1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ptMiddleName1.setText("Middle Name");

        comma1.setText(",");

        ptLastName.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        ptLastName.setForeground(new java.awt.Color(153, 153, 153));
        ptLastName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ptLastName.setText("Last Name");

        lblptEmail.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        lblptEmail.setText("Email:");

        ptFirstName1.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        ptFirstName1.setForeground(new java.awt.Color(153, 153, 153));
        ptFirstName1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ptFirstName1.setText("First Name");

        addPatientTitle.setFont(new java.awt.Font("Quicksand Medium", 0, 24)); // NOI18N
        addPatientTitle.setText("Add Patient");

        lblptSex.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        lblptSex.setText("Sex:");

        ptContacts1.setFont(new java.awt.Font("Quicksand", 0, 14)); // NOI18N
        ptContacts1.setForeground(new java.awt.Color(153, 153, 153));
        ptContacts1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ptContacts1.setText("####-###-####");

        lblptBday.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        lblptBday.setText("Date of Birth:");

        jButton4.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        jButton4.setText("Add");
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        lblptContact.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        lblptContact.setText("Contact Number:");

        buttonGroup1.add(jRadioButton3);
        jRadioButton3.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        jRadioButton3.setText("Male");

        buttonGroup1.add(jRadioButton4);
        jRadioButton4.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        jRadioButton4.setText("Female");

        javax.swing.GroupLayout addPatientLayout = new javax.swing.GroupLayout(addPatient);
        addPatient.setLayout(addPatientLayout);
        addPatientLayout.setHorizontalGroup(
            addPatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addPatientLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(addPatientTitle)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(addPatientLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(addPatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addPatientLayout.createSequentialGroup()
                        .addComponent(lblptContact)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ptContacts1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(addPatientLayout.createSequentialGroup()
                        .addGroup(addPatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblptEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblptName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(addPatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(addPatientLayout.createSequentialGroup()
                                .addComponent(ptLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comma1, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ptFirstName1, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ptMiddleName1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(ptEmail1))
                        .addGap(25, 25, 25))
                    .addGroup(addPatientLayout.createSequentialGroup()
                        .addGroup(addPatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(addPatientLayout.createSequentialGroup()
                                .addComponent(lblptBday)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(addPatientLayout.createSequentialGroup()
                                .addComponent(lblptSex)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jRadioButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 288, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addPatientLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        addPatientLayout.setVerticalGroup(
            addPatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addPatientLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(addPatientTitle)
                .addGap(36, 36, 36)
                .addGroup(addPatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblptName)
                    .addComponent(ptLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comma1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ptFirstName1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ptMiddleName1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(addPatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblptEmail)
                    .addComponent(ptEmail1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(addPatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblptContact)
                    .addComponent(ptContacts1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(addPatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblptSex)
                    .addComponent(jRadioButton3)
                    .addComponent(jRadioButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(addPatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblptBday)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(107, Short.MAX_VALUE))
        );

        jLayeredPane1.setLayer(addUser, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(removePage, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(reportsPage, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(requestPage, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(addPatient, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(addPatient, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                    .addComponent(requestPage, javax.swing.GroupLayout.DEFAULT_SIZE, 631, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(reportsPage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(removePage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                    .addGap(1, 1, 1)
                    .addComponent(addUser, javax.swing.GroupLayout.PREFERRED_SIZE, 630, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                    .addComponent(addPatient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 15, Short.MAX_VALUE)))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                    .addGap(3, 3, 3)
                    .addComponent(requestPage, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(reportsPage, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(removePage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(addUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            .addComponent(jLayeredPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 637, Short.MAX_VALUE)
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

    private void requestsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_requestsBtnActionPerformed
        setDisplay(requestPage);
    }//GEN-LAST:event_requestsBtnActionPerformed

    private void removeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeBtnActionPerformed
        setDisplay(reportsPage);
    }//GEN-LAST:event_removeBtnActionPerformed

    private void reportsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportsBtnActionPerformed
        setDisplay(removePage);
    }//GEN-LAST:event_reportsBtnActionPerformed

    private void btnAddUserToSQLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddUserToSQLActionPerformed
        insertUserData();
        JOptionPane.showMessageDialog(null, "Username: " + username + "\n Password: " + password, "Credentials", JOptionPane.INFORMATION_MESSAGE);
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
    private javax.swing.JPanel addPatient;
    private javax.swing.JLabel addPatientTitle;
    private javax.swing.JPanel addUser;
    private javax.swing.JLabel adminInformation;
    private javax.swing.JLabel adminPicture;
    private javax.swing.JLabel background;
    private javax.swing.JButton btnAddUserToSQL;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbCivilStatus;
    private javax.swing.JLabel comma1;
    private javax.swing.JButton exit;
    private javax.swing.JLabel frameDrag;
    private javax.swing.JPanel informationPanel;
    private javax.swing.JButton jButton4;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
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
    private javax.swing.JLabel lblptBday;
    private javax.swing.JLabel lblptContact;
    private javax.swing.JLabel lblptEmail;
    private javax.swing.JLabel lblptName;
    private javax.swing.JLabel lblptSex;
    private javax.swing.JLabel logoIcon;
    private javax.swing.JPanel mainAdminPanel;
    private javax.swing.JButton minimized;
    private javax.swing.JPanel navbar;
    private javax.swing.JLabel organizationTitle;
    private javax.swing.JTextField ptContacts1;
    private javax.swing.JTextField ptEmail1;
    private javax.swing.JTextField ptFirstName1;
    private javax.swing.JTextField ptLastName;
    private javax.swing.JTextField ptMiddleName1;
    private javax.swing.JRadioButton rbFemaleSex;
    private javax.swing.JRadioButton rbMaleSex;
    private javax.swing.JButton removeBtn;
    private javax.swing.JPanel removePage;
    private javax.swing.JButton reportsBtn;
    private javax.swing.JPanel reportsPage;
    private javax.swing.JPanel requestPage;
    private javax.swing.JButton requestsBtn;
    private javax.swing.JPanel sidebar;
    private javax.swing.JTextField tfAge;
    private javax.swing.JTextField tfContact;
    private javax.swing.JTextField tfEmail;
    private javax.swing.JTextField tfFirstName;
    private javax.swing.JTextField tfLastName;
    private javax.swing.JTextField tfMiddleName;
    private javax.swing.JPanel uiButtonPanel;
    private javax.swing.JLabel welcomeAdmin;
    private javax.swing.JPanel welcomePage;
    private javax.swing.JPanel windowPanel;
    // End of variables declaration//GEN-END:variables

}
