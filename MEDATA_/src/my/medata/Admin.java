package my.medata;

import static java.awt.Color.*;
import java.awt.Font;
import java.awt.event.KeyEvent;
//import java.awt.FontFormatException;
//import java.awt.GraphicsEnvironment;
//import java.io.File;
//import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.SwingConstants.*;

/*
 * Authors:
 * Arcega, Lance Angelo P.
 * Muñoz, Nathan Sheary G.
 * Pare, Neo Jezer A.
 */

public class Admin extends javax.swing.JFrame {

    /**
     * Creates new form Admin_Page
     */
    
   //Font fn;
    int xMouse, yMouse;
    String username;
    String password;
    String lastName;
    String firstName;
    Date dateOfBirth;
    String id;
    
    
    private static final String email_Pattern =   "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    
    
    UIDGenerator generateID = new UIDGenerator();
   
    public Admin() {
        initComponents();
        setLocationRelativeTo(null);
        jLayeredPane1.setVisible(false);
        welcomePage.setVisible(true);
        
        
        //Font
//        try{
//            fn = Font.createFont(Font.TRUETYPE_FONT,new File("Quicksand-Regular.ttf"));
//            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Quicksand-Regular.ttf")));
//        } catch(IOException | FontFormatException e){
//             e.printStackTrace();
//        } 
    }
    
    
    
    public void setDisplay(JPanel Panel){
        
        for (int i = 0; i < jLayeredPane1.getComponentCount(); i++) {
            JComponent component = (JComponent) jLayeredPane1.getComponent(i);
            component.setVisible(false);
        }
        jLayeredPane1.setVisible(true);
        Panel.setVisible(true);
    }
    
    public void focusOn(JTextField textfield, String intext){
        if (textfield.getText().equals(intext)) {
            textfield.setText("");
            textfield.setForeground(black);
            textfield.setHorizontalAlignment(LEFT);
        }
        textfield.selectAll();
    } 
    
     public void focusOff(JTextField textfield, String intext){
        if (textfield.getText().isEmpty()) {
            textfield.setText(intext);
            textfield.setForeground(gray);
            textfield.setHorizontalAlignment(CENTER);
        }
    }  
     
    public String generateID(){
        String uid;
        lastName = mdLastName.getText();
        firstName = mdFirstName.getText();
        
        for (int i = 0; i < 10; i++) {
            id = generateID.generateUID(lastName, firstName, jDateChooser3.getDate());
        }
        uid = id;
        
        return uid;
    }
    
    private void updateAgeLabel() {
        LocalDate selectedDate = jDateChooser3.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(selectedDate, currentDate);
        int age = period.getYears();
        mdAge.setForeground(black);
        mdAge.setText(String.valueOf(age));
    }
    
    void injectData() {
        lastName = mdLastName.getText();
        firstName = mdFirstName.getText();
        String middleName = mdMiddleName.getText();
        String email = mdEmail.getText();
        String contact = mdContact.getText();
        String sex;
        if (mdSexMale.isSelected()) {
            sex = mdSexMale.getText();
        } else {
            sex = mdSexFemale.getText();
        }
        SimpleDateFormat birthDateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateOfBirth = birthDateFormatter.format(jDateChooser3.getDate());
        String civilStatus;
        if (mdCivilStatus.getSelectedIndex() == 0) {
            civilStatus = "Single";
        } else if (mdCivilStatus.getSelectedIndex() == 1) {
            civilStatus = "Married";
        } else if (mdCivilStatus.getSelectedIndex() == 2) {
            civilStatus = "Divorced";
        } else {
            civilStatus = "Widowed";
        }
        int age = Integer.parseInt(mdAge.getText());
        String address = "";
        int height = 0;
        int weight = 0;
        String role = "doctor";
        username = new usernameGenerator().generateUsername(lastName, firstName, jDateChooser3.getDate());
        password = new passwordGenerator().generatePassword(lastName);
        String confirmPassword = "";
        createUser.processInput(lastName, firstName, middleName, age, dateOfBirth, address, contact, email, sex, civilStatus, height, weight, username, password, confirmPassword,role);
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
        proposedDoctorPanel = new javax.swing.JPanel();
        lblName1 = new javax.swing.JLabel();
        addDoctorTitle1 = new javax.swing.JLabel();
        comma2 = new javax.swing.JLabel();
        mdLastName = new javax.swing.JTextField();
        mdFirstName = new javax.swing.JTextField();
        mdMiddleName = new javax.swing.JTextField();
        lblEmail1 = new javax.swing.JLabel();
        mdEmail = new javax.swing.JTextField();
        lblContacts1 = new javax.swing.JLabel();
        mdContact = new javax.swing.JTextField();
        lblSex1 = new javax.swing.JLabel();
        mdSexMale = new javax.swing.JRadioButton();
        mdSexFemale = new javax.swing.JRadioButton();
        lblBDay1 = new javax.swing.JLabel();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        lblGPass1 = new javax.swing.JLabel();
        generatedPassword = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        lblGUID1 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        generatedUsername = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        lblBDay2 = new javax.swing.JLabel();
        mdAge = new javax.swing.JTextField();
        lblBDay3 = new javax.swing.JLabel();
        mdCivilStatus = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
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
        lblptGenPass = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblptGenUID = new javax.swing.JLabel();
        lblptBday = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        lblptContact = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jRadioButton3 = new javax.swing.JRadioButton();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jButton6 = new javax.swing.JButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        addDoctorInitialPanel = new javax.swing.JPanel();
        lblName = new javax.swing.JLabel();
        addDoctorTitle = new javax.swing.JLabel();
        comma = new javax.swing.JLabel();
        tfLastName = new javax.swing.JTextField();
        tfFirstName = new javax.swing.JTextField();
        tfMiddleName = new javax.swing.JTextField();
        lblEmail = new javax.swing.JLabel();
        tfEmail = new javax.swing.JTextField();
        lblContacts = new javax.swing.JLabel();
        tfContacts = new javax.swing.JTextField();
        lblSex = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        lblBDay = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        lblGPass = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        lblGUID = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
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

        proposedDoctorPanel.setMaximumSize(new java.awt.Dimension(615, 455));
        proposedDoctorPanel.setMinimumSize(new java.awt.Dimension(615, 455));
        proposedDoctorPanel.setPreferredSize(new java.awt.Dimension(615, 455));

        lblName1.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        lblName1.setText("Name:");

        addDoctorTitle1.setFont(new java.awt.Font("Quicksand Medium", 0, 24)); // NOI18N
        addDoctorTitle1.setText("Add Doctor");

        comma2.setText(",");

        mdLastName.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        mdLastName.setForeground(new java.awt.Color(153, 153, 153));
        mdLastName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        mdLastName.setText("Last Name");
        mdLastName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                mdLastNameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                mdLastNameFocusLost(evt);
            }
        });

        mdFirstName.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        mdFirstName.setForeground(new java.awt.Color(153, 153, 153));
        mdFirstName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        mdFirstName.setText("First Name");
        mdFirstName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                mdFirstNameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                mdFirstNameFocusLost(evt);
            }
        });

        mdMiddleName.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        mdMiddleName.setForeground(new java.awt.Color(153, 153, 153));
        mdMiddleName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        mdMiddleName.setText("Middle Name");
        mdMiddleName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                mdMiddleNameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                mdMiddleNameFocusLost(evt);
            }
        });

        lblEmail1.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        lblEmail1.setText("Email:");

        mdEmail.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        mdEmail.setForeground(new java.awt.Color(153, 153, 153));
        mdEmail.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        mdEmail.setText("youdata@gmail.com");
        mdEmail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                mdEmailFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                mdEmailFocusLost(evt);
            }
        });

        lblContacts1.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        lblContacts1.setText("Contact Number:");

        mdContact.setFont(new java.awt.Font("Quicksand", 0, 14)); // NOI18N
        mdContact.setForeground(new java.awt.Color(153, 153, 153));
        mdContact.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        mdContact.setText("###-###-####");
        mdContact.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                mdContactFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                mdContactFocusLost(evt);
            }
        });
        mdContact.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                mdContactKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                mdContactKeyTyped(evt);
            }
        });

        lblSex1.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        lblSex1.setText("Sex:");

        buttonGroup1.add(mdSexMale);
        mdSexMale.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        mdSexMale.setText("Male");

        buttonGroup1.add(mdSexFemale);
        mdSexFemale.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        mdSexFemale.setText("Female");

        lblBDay1.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        lblBDay1.setText("Date of Birth:");

        jDateChooser3.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooser3PropertyChange(evt);
            }
        });

        lblGPass1.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        lblGPass1.setText("Generated Password:");

        generatedPassword.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        generatedPassword.setText("(generated password)");

        jButton7.setFont(new java.awt.Font("Quicksand", 0, 12)); // NOI18N
        jButton7.setText("Generate");
        jButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        lblGUID1.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        lblGUID1.setText("Generated UID:");

        jButton8.setFont(new java.awt.Font("Quicksand", 0, 12)); // NOI18N
        jButton8.setText("Generate");
        jButton8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        generatedUsername.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        generatedUsername.setText("(generated UID)");

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

        mdAge.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        mdAge.setForeground(new java.awt.Color(153, 153, 153));
        mdAge.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        mdAge.setText("Age");
        mdAge.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                mdAgeFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                mdAgeFocusLost(evt);
            }
        });

        lblBDay3.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        lblBDay3.setText("Civil Status:");
        lblBDay3.setPreferredSize(new java.awt.Dimension(95, 30));

        mdCivilStatus.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        mdCivilStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Single", "Married", "Divorced", "Widowed" }));
        mdCivilStatus.setMaximumSize(new java.awt.Dimension(100, 30));
        mdCivilStatus.setMinimumSize(new java.awt.Dimension(100, 30));
        mdCivilStatus.setPreferredSize(new java.awt.Dimension(100, 30));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("+63");

        javax.swing.GroupLayout proposedDoctorPanelLayout = new javax.swing.GroupLayout(proposedDoctorPanel);
        proposedDoctorPanel.setLayout(proposedDoctorPanelLayout);
        proposedDoctorPanelLayout.setHorizontalGroup(
            proposedDoctorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(proposedDoctorPanelLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(proposedDoctorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(proposedDoctorPanelLayout.createSequentialGroup()
                        .addComponent(lblContacts1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mdContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(proposedDoctorPanelLayout.createSequentialGroup()
                        .addGroup(proposedDoctorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(proposedDoctorPanelLayout.createSequentialGroup()
                                .addComponent(lblBDay3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mdCivilStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(proposedDoctorPanelLayout.createSequentialGroup()
                                .addComponent(lblGPass1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(generatedPassword))
                            .addGroup(proposedDoctorPanelLayout.createSequentialGroup()
                                .addComponent(lblBDay1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblBDay2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mdAge, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(proposedDoctorPanelLayout.createSequentialGroup()
                                .addComponent(lblSex1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(mdSexMale, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mdSexFemale, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(proposedDoctorPanelLayout.createSequentialGroup()
                                .addComponent(lblGUID1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(generatedUsername)))
                        .addGap(0, 70, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, proposedDoctorPanelLayout.createSequentialGroup()
                        .addGroup(proposedDoctorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(proposedDoctorPanelLayout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addComponent(mdEmail))
                            .addGroup(proposedDoctorPanelLayout.createSequentialGroup()
                                .addGroup(proposedDoctorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblEmail1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblName1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mdLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comma2, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mdFirstName)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mdMiddleName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(28, 28, 28))))
            .addGroup(proposedDoctorPanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(addDoctorTitle1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        proposedDoctorPanelLayout.setVerticalGroup(
            proposedDoctorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(proposedDoctorPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(addDoctorTitle1)
                .addGap(36, 36, 36)
                .addGroup(proposedDoctorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblName1)
                    .addComponent(mdLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comma2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mdFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mdMiddleName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(proposedDoctorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmail1)
                    .addComponent(mdEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(proposedDoctorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblContacts1)
                    .addComponent(mdContact, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(proposedDoctorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSex1)
                    .addComponent(mdSexMale)
                    .addComponent(mdSexFemale))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(proposedDoctorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblBDay1)
                    .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(proposedDoctorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblBDay2)
                        .addComponent(mdAge, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addGroup(proposedDoctorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBDay3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mdCivilStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(proposedDoctorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGUID1)
                    .addComponent(jButton8)
                    .addComponent(generatedUsername))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(proposedDoctorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGPass1)
                    .addComponent(jButton7)
                    .addComponent(generatedPassword))
                .addGap(18, 18, 18)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        lblptGenPass.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        lblptGenPass.setText("Generated Password:");

        jLabel10.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        jLabel10.setText("(generated UID)");

        lblptGenUID.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        lblptGenUID.setText("Generated UID:");

        lblptBday.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        lblptBday.setText("Date of Birth:");

        jButton4.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        jButton4.setText("Add");
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        lblptContact.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        lblptContact.setText("Contact Number:");

        jButton5.setFont(new java.awt.Font("Quicksand", 0, 12)); // NOI18N
        jButton5.setText("Generate");
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel11.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        jLabel11.setText("(generated password)");

        buttonGroup1.add(jRadioButton3);
        jRadioButton3.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        jRadioButton3.setText("Male");

        jButton6.setFont(new java.awt.Font("Quicksand", 0, 12)); // NOI18N
        jButton6.setText("Generate");
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

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
                                .addComponent(lblptGenUID)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel10))
                            .addGroup(addPatientLayout.createSequentialGroup()
                                .addComponent(lblptGenPass)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton6)
                                .addGap(12, 12, 12)
                                .addComponent(jLabel11))
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
                        .addGap(0, 0, Short.MAX_VALUE))))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(addPatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblptGenPass)
                    .addComponent(jButton6)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(addPatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblptGenUID)
                    .addComponent(jButton5)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        addDoctorInitialPanel.setPreferredSize(new java.awt.Dimension(615, 461));

        lblName.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        lblName.setText("Name:");

        addDoctorTitle.setFont(new java.awt.Font("Quicksand Medium", 0, 24)); // NOI18N
        addDoctorTitle.setText("Add Doctor");

        comma.setText(",");

        tfLastName.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        tfLastName.setForeground(new java.awt.Color(153, 153, 153));
        tfLastName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfLastName.setText("Last Name");

        tfFirstName.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        tfFirstName.setForeground(new java.awt.Color(153, 153, 153));
        tfFirstName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfFirstName.setText("First Name");

        tfMiddleName.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        tfMiddleName.setForeground(new java.awt.Color(153, 153, 153));
        tfMiddleName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfMiddleName.setText("Middle Name");

        lblEmail.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        lblEmail.setText("Email:");

        tfEmail.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        tfEmail.setForeground(new java.awt.Color(153, 153, 153));
        tfEmail.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfEmail.setText("youdata@gmail.com");

        lblContacts.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        lblContacts.setText("Contact Number:");

        tfContacts.setFont(new java.awt.Font("Quicksand", 0, 14)); // NOI18N
        tfContacts.setForeground(new java.awt.Color(153, 153, 153));
        tfContacts.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfContacts.setText("####-###-####");

        lblSex.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        lblSex.setText("Sex:");

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        jRadioButton1.setText("Male");

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        jRadioButton2.setText("Female");

        lblBDay.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        lblBDay.setText("Date of Birth:");

        lblGPass.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        lblGPass.setText("Generated Password:");

        jLabel4.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        jLabel4.setText("(generated password)");

        jButton2.setFont(new java.awt.Font("Quicksand", 0, 12)); // NOI18N
        jButton2.setText("Generate");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        lblGUID.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        lblGUID.setText("Generated UID:");

        jButton3.setFont(new java.awt.Font("Quicksand", 0, 12)); // NOI18N
        jButton3.setText("Generate");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel9.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        jLabel9.setText("(generated UID)");

        jButton1.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        jButton1.setText("Add");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout addDoctorInitialPanelLayout = new javax.swing.GroupLayout(addDoctorInitialPanel);
        addDoctorInitialPanel.setLayout(addDoctorInitialPanelLayout);
        addDoctorInitialPanelLayout.setHorizontalGroup(
            addDoctorInitialPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addDoctorInitialPanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(addDoctorTitle)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(addDoctorInitialPanelLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(addDoctorInitialPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addDoctorInitialPanelLayout.createSequentialGroup()
                        .addComponent(lblContacts)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfContacts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(addDoctorInitialPanelLayout.createSequentialGroup()
                        .addGroup(addDoctorInitialPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(addDoctorInitialPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(addDoctorInitialPanelLayout.createSequentialGroup()
                                .addComponent(tfLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comma, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfFirstName)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfMiddleName, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(tfEmail))
                        .addGap(25, 25, 25))
                    .addGroup(addDoctorInitialPanelLayout.createSequentialGroup()
                        .addGroup(addDoctorInitialPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(addDoctorInitialPanelLayout.createSequentialGroup()
                                .addComponent(lblGUID)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel9))
                            .addGroup(addDoctorInitialPanelLayout.createSequentialGroup()
                                .addComponent(lblGPass)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton2)
                                .addGap(12, 12, 12)
                                .addComponent(jLabel4))
                            .addGroup(addDoctorInitialPanelLayout.createSequentialGroup()
                                .addComponent(lblBDay)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(addDoctorInitialPanelLayout.createSequentialGroup()
                                .addComponent(lblSex)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jRadioButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addDoctorInitialPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        addDoctorInitialPanelLayout.setVerticalGroup(
            addDoctorInitialPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addDoctorInitialPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(addDoctorTitle)
                .addGap(36, 36, 36)
                .addGroup(addDoctorInitialPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblName)
                    .addComponent(tfLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comma, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfMiddleName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(addDoctorInitialPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmail)
                    .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(addDoctorInitialPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblContacts)
                    .addComponent(tfContacts, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(addDoctorInitialPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSex)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(addDoctorInitialPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblBDay)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(addDoctorInitialPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGPass)
                    .addComponent(jButton2)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(addDoctorInitialPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGUID)
                    .addComponent(jButton3)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLayeredPane1.setLayer(proposedDoctorPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(removePage, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(reportsPage, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(requestPage, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(addPatient, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(addDoctorInitialPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(addDoctorInitialPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 637, Short.MAX_VALUE)
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
                    .addComponent(proposedDoctorPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 630, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(addDoctorInitialPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(proposedDoctorPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addContainerGap(497, Short.MAX_VALUE))
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
                "Exit",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        if (exit == 0){//yes
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
        setDisplay(proposedDoctorPanel);
    }//GEN-LAST:event_addDoctorBtnActionPerformed

    private void addPatBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPatBtnActionPerformed
        setDisplay(addPatient);
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

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        injectData();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed

        generatedUsername.setText(generateID());
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        password = new passwordGenerator().generatePassword(lastName);
        generatedPassword.setText(password);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void mdLastNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_mdLastNameFocusGained
        focusOn(mdLastName, "Last Name");
    }//GEN-LAST:event_mdLastNameFocusGained

    private void mdLastNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_mdLastNameFocusLost
        focusOff(mdLastName, "Last Name");
    }//GEN-LAST:event_mdLastNameFocusLost

    private void mdFirstNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_mdFirstNameFocusGained
        focusOn(mdFirstName, "First Name");
    }//GEN-LAST:event_mdFirstNameFocusGained

    private void mdFirstNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_mdFirstNameFocusLost
        focusOff(mdFirstName, "First Name");
    }//GEN-LAST:event_mdFirstNameFocusLost

    private void mdMiddleNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_mdMiddleNameFocusGained
        focusOn(mdMiddleName, "Middle Name");
    }//GEN-LAST:event_mdMiddleNameFocusGained

    private void mdMiddleNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_mdMiddleNameFocusLost
        focusOff(mdMiddleName, "Middle Name");
    }//GEN-LAST:event_mdMiddleNameFocusLost

    private void mdEmailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_mdEmailFocusGained
        focusOn(mdEmail, "youdata@gmail.com");
    }//GEN-LAST:event_mdEmailFocusGained

    private void mdEmailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_mdEmailFocusLost
     
        if (!mdEmail.getText().matches(email_Pattern)) {
            JOptionPane.showMessageDialog(null, "Invalid email format", "Error", JOptionPane.ERROR_MESSAGE);
            mdEmail.setForeground(red);
            jButton9.setEnabled(false);
        } else if (mdEmail.getText().matches(email_Pattern) && !mdEmail.getText().equals("youdata@gmail.com")){
            mdEmail.setForeground(black);
            jButton9.setEnabled(true);
        }else{
            focusOff(mdEmail, "youdata@gmail.com");
        }
    }//GEN-LAST:event_mdEmailFocusLost

    private void mdContactFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_mdContactFocusGained
        focusOn(mdContact, "###-###-####");
        String contactNumber = mdContact.getText();
        if (contactNumber.length() == 10) {
            mdContact.setText(contactNumber.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "$1-$2-$3"));
        }

    }//GEN-LAST:event_mdContactFocusGained

    private void mdContactFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_mdContactFocusLost
        focusOff(mdContact, "###-###-####");
    }//GEN-LAST:event_mdContactFocusLost

    private void mdAgeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_mdAgeFocusGained
       focusOn(mdAge, "Age");
       //mdAge.setText(""+updateAge(jDateChooser3.getDate()));
    }//GEN-LAST:event_mdAgeFocusGained

    private void mdAgeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_mdAgeFocusLost
       focusOff(mdAge, "Age");
    }//GEN-LAST:event_mdAgeFocusLost

    private void jDateChooser3PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooser3PropertyChange
        if ("date".equals(evt.getPropertyName())) {
            updateAgeLabel();
        }
    }//GEN-LAST:event_jDateChooser3PropertyChange

    private void mdContactKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mdContactKeyTyped

        char c = evt.getKeyChar();
        String b = mdContact.getText();
        
            if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE && c != '-' ) {
                 evt.consume();
                 JOptionPane.showMessageDialog(null, "Input only numbers", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (b.length()>=12){
                evt.consume();
                JOptionPane.showMessageDialog(null, "Reached contact number limit", "Error", JOptionPane.ERROR_MESSAGE);
            }
    }//GEN-LAST:event_mdContactKeyTyped

    private void mdContactKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mdContactKeyPressed
       String contactNumber = mdContact.getText();
        mdContact.setText(contactNumber.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "$1-$2-$3"));
    }//GEN-LAST:event_mdContactKeyPressed

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
    private javax.swing.JPanel addDoctorInitialPanel;
    private javax.swing.JLabel addDoctorTitle;
    private javax.swing.JLabel addDoctorTitle1;
    private javax.swing.JButton addPatBtn;
    private javax.swing.JPanel addPatient;
    private javax.swing.JLabel addPatientTitle;
    private javax.swing.JLabel adminInformation;
    private javax.swing.JLabel adminPicture;
    private javax.swing.JLabel background;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel comma;
    private javax.swing.JLabel comma1;
    private javax.swing.JLabel comma2;
    private javax.swing.JButton exit;
    private javax.swing.JLabel frameDrag;
    private javax.swing.JLabel generatedPassword;
    private javax.swing.JLabel generatedUsername;
    private javax.swing.JPanel informationPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateChooser3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JLabel lblBDay;
    private javax.swing.JLabel lblBDay1;
    private javax.swing.JLabel lblBDay2;
    private javax.swing.JLabel lblBDay3;
    private javax.swing.JLabel lblContacts;
    private javax.swing.JLabel lblContacts1;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblEmail1;
    private javax.swing.JLabel lblGPass;
    private javax.swing.JLabel lblGPass1;
    private javax.swing.JLabel lblGUID;
    private javax.swing.JLabel lblGUID1;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblName1;
    private javax.swing.JLabel lblSex;
    private javax.swing.JLabel lblSex1;
    private javax.swing.JLabel lblptBday;
    private javax.swing.JLabel lblptContact;
    private javax.swing.JLabel lblptEmail;
    private javax.swing.JLabel lblptGenPass;
    private javax.swing.JLabel lblptGenUID;
    private javax.swing.JLabel lblptName;
    private javax.swing.JLabel lblptSex;
    private javax.swing.JLabel logoIcon;
    private javax.swing.JPanel mainAdminPanel;
    private javax.swing.JTextField mdAge;
    private javax.swing.JComboBox<String> mdCivilStatus;
    private javax.swing.JTextField mdContact;
    private javax.swing.JTextField mdEmail;
    private javax.swing.JTextField mdFirstName;
    private javax.swing.JTextField mdLastName;
    private javax.swing.JTextField mdMiddleName;
    private javax.swing.JRadioButton mdSexFemale;
    private javax.swing.JRadioButton mdSexMale;
    private javax.swing.JButton minimized;
    private javax.swing.JPanel navbar;
    private javax.swing.JLabel organizationTitle;
    private javax.swing.JPanel proposedDoctorPanel;
    private javax.swing.JTextField ptContacts1;
    private javax.swing.JTextField ptEmail1;
    private javax.swing.JTextField ptFirstName1;
    private javax.swing.JTextField ptLastName;
    private javax.swing.JTextField ptMiddleName1;
    private javax.swing.JButton removeBtn;
    private javax.swing.JPanel removePage;
    private javax.swing.JButton reportsBtn;
    private javax.swing.JPanel reportsPage;
    private javax.swing.JPanel requestPage;
    private javax.swing.JButton requestsBtn;
    private javax.swing.JPanel sidebar;
    private javax.swing.JTextField tfContacts;
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
