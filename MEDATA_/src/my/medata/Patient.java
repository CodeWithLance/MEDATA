/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package my.medata;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Neo
 */
public class Patient extends javax.swing.JFrame {

    /**
     * Creates new form Patient
     */
    
    int xMouse, yMouse;
    Font fn;
    
    public Patient() {
        initComponents();
        setLocationRelativeTo(null);
        jLayeredPane1.setVisible(false);
        welcomePage.setVisible(true);
        
        //Font
        try{
            fn = Font.createFont(Font.TRUETYPE_FONT,new File("Quicksand-Regular.ttf"));
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Quicksand-Regular.ttf")));
        } catch(IOException | FontFormatException e){
             e.printStackTrace();
        }
    }
    
    public void setDisplay(JPanel Panel){
        
        for (int i = 0; i < jLayeredPane1.getComponentCount(); i++) {
            JComponent component = (JComponent) jLayeredPane1.getComponent(i);
            component.setVisible(false);
        }
        jLayeredPane1.setVisible(true);
        Panel.setVisible(true);
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        navbar = new javax.swing.JPanel();
        profileBtn = new javax.swing.JButton();
        medHistBtn = new javax.swing.JButton();
        resultsBtn = new javax.swing.JButton();
        testsBtn = new javax.swing.JButton();
        doctorsBtn = new javax.swing.JButton();
        medsBtn = new javax.swing.JButton();
        pharmaBtn = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        windowPanel = new javax.swing.JPanel();
        welcomePage = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        medicationPage = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        patientProfile = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        medicalHistory = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        results = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        pendingTests = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        doctorPage = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        pharmacyPage = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        frameDrag = new javax.swing.JLabel();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(800, 650));
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

        jLabel1.setBackground(new java.awt.Color(0, 204, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("picture");
        jLabel1.setOpaque(true);

        jLabel2.setBackground(new java.awt.Color(153, 255, 153));
        jLabel2.setText("jLabel2");
        jLabel2.setOpaque(true);

        jLabel3.setBackground(new java.awt.Color(102, 255, 204));
        jLabel3.setText("jLabel3");
        jLabel3.setOpaque(true);

        javax.swing.GroupLayout infoPaneLayout = new javax.swing.GroupLayout(infoPane);
        infoPane.setLayout(infoPaneLayout);
        infoPaneLayout.setHorizontalGroup(
            infoPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, infoPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(infoPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        infoPaneLayout.setVerticalGroup(
            infoPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(infoPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(infoPaneLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel3.setOpaque(false);

        navbar.setOpaque(false);
        navbar.setLayout(new java.awt.GridLayout(7, 1));

        profileBtn.setFont(new java.awt.Font("Quicksand", 0, 12)); // NOI18N
        profileBtn.setText("Patient Profile");
        profileBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        profileBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profileBtnActionPerformed(evt);
            }
        });
        navbar.add(profileBtn);

        medHistBtn.setFont(new java.awt.Font("Quicksand", 0, 12)); // NOI18N
        medHistBtn.setText("Medical History");
        medHistBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                medHistBtnActionPerformed(evt);
            }
        });
        navbar.add(medHistBtn);

        resultsBtn.setFont(new java.awt.Font("Quicksand", 0, 12)); // NOI18N
        resultsBtn.setText("Results");
        resultsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resultsBtnActionPerformed(evt);
            }
        });
        navbar.add(resultsBtn);

        testsBtn.setFont(new java.awt.Font("Quicksand", 0, 12)); // NOI18N
        testsBtn.setText("Pending Test/s");
        testsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                testsBtnActionPerformed(evt);
            }
        });
        navbar.add(testsBtn);

        doctorsBtn.setFont(new java.awt.Font("Quicksand", 0, 12)); // NOI18N
        doctorsBtn.setText("Doctor/s");
        doctorsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doctorsBtnActionPerformed(evt);
            }
        });
        navbar.add(doctorsBtn);

        medsBtn.setFont(new java.awt.Font("Quicksand", 0, 12)); // NOI18N
        medsBtn.setText("Medication");
        medsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                medsBtnActionPerformed(evt);
            }
        });
        navbar.add(medsBtn);

        pharmaBtn.setFont(new java.awt.Font("Quicksand", 0, 12)); // NOI18N
        pharmaBtn.setText("Pharmacy");
        pharmaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pharmaBtnActionPerformed(evt);
            }
        });
        navbar.add(pharmaBtn);

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

        jLabel5.setText("PAtient profile");

        javax.swing.GroupLayout patientProfileLayout = new javax.swing.GroupLayout(patientProfile);
        patientProfile.setLayout(patientProfileLayout);
        patientProfileLayout.setHorizontalGroup(
            patientProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(patientProfileLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addContainerGap(550, Short.MAX_VALUE))
        );
        patientProfileLayout.setVerticalGroup(
            patientProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(patientProfileLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addContainerGap(440, Short.MAX_VALUE))
        );

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

        jLabel10.setText("doctor page");

        javax.swing.GroupLayout doctorPageLayout = new javax.swing.GroupLayout(doctorPage);
        doctorPage.setLayout(doctorPageLayout);
        doctorPageLayout.setHorizontalGroup(
            doctorPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(doctorPageLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        doctorPageLayout.setVerticalGroup(
            doctorPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(doctorPageLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addContainerGap(439, Short.MAX_VALUE))
        );

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
            .addComponent(doctorPage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void profileBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profileBtnActionPerformed
        setDisplay(patientProfile);
    }//GEN-LAST:event_profileBtnActionPerformed

    private void medHistBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_medHistBtnActionPerformed
        setDisplay(medicalHistory);
    }//GEN-LAST:event_medHistBtnActionPerformed

    private void resultsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resultsBtnActionPerformed
        setDisplay(results);
    }//GEN-LAST:event_resultsBtnActionPerformed

    private void testsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_testsBtnActionPerformed
        setDisplay(pendingTests);
    }//GEN-LAST:event_testsBtnActionPerformed

    private void doctorsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doctorsBtnActionPerformed
        setDisplay(doctorPage);
    }//GEN-LAST:event_doctorsBtnActionPerformed

    private void medsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_medsBtnActionPerformed
        setDisplay(medicationPage);
    }//GEN-LAST:event_medsBtnActionPerformed

    private void pharmaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pharmaBtnActionPerformed
        setDisplay(pharmacyPage);
    }//GEN-LAST:event_pharmaBtnActionPerformed

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
    private javax.swing.JPanel doctorPage;
    private javax.swing.JButton doctorsBtn;
    private javax.swing.JButton exit;
    private javax.swing.JLabel frameDrag;
    private javax.swing.JPanel infoPane;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JLabel logo;
    private javax.swing.JButton medHistBtn;
    private javax.swing.JPanel medicalHistory;
    private javax.swing.JPanel medicationPage;
    private javax.swing.JButton medsBtn;
    private javax.swing.JButton minimized;
    private javax.swing.JPanel navbar;
    private javax.swing.JPanel patientProfile;
    private javax.swing.JPanel pendingTests;
    private javax.swing.JButton pharmaBtn;
    private javax.swing.JPanel pharmacyPage;
    private javax.swing.JButton profileBtn;
    private javax.swing.JPanel results;
    private javax.swing.JButton resultsBtn;
    private javax.swing.JButton testsBtn;
    private javax.swing.JPanel welcomePage;
    private javax.swing.JPanel windowPanel;
    // End of variables declaration//GEN-END:variables
}