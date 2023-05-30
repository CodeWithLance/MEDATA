package my.medata;

import com.formdev.flatlaf.FlatLightLaf;
import static java.awt.Color.*;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.font.TextAttribute;
import java.util.Map;
import javax.swing.JFrame;
import static javax.swing.SwingConstants.*;
import java.sql.*;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/*
 * Authors:
 * Arcega, Lance Angelo P.
 * Muñoz, Nathan Sheary G.
 * Pare, Neo Jezer A.
 */
public class MEDATA_Application extends javax.swing.JFrame {

    Connection con; 

    /**
     * Creates new form MEDATA_Application
     */
    public MEDATA_Application() {
        initComponents();
        setLocationRelativeTo(null);
        pack();
        enterPassword.setEchoChar((char) 0);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/medata", "root", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    int xMouse, yMouse;
    
    dataBox getUser = new dataBox();
    String loggedUser;
    
    
    void userSignIn() {
        try {
            String sql = "SELECT * FROM userinfo WHERE username = ? and password = ?";

            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, enterUsername.getText());
            pst.setString(2, enterPassword.getText());

            ResultSet resultSet = pst.executeQuery();
            if (resultSet.next()) {
                boolean isActivated = resultSet.getBoolean("isActivated");

                if (!isActivated) {
                    promptPasswordChange(enterUsername.getText());
                    return;
                } else {
                    loggedUser = enterUsername.getText();
                    getUser.pushUserData(loggedUser);
                    JOptionPane.showMessageDialog(null, "Welcome!", "Success", JOptionPane.INFORMATION_MESSAGE);
                     
                    String query = "SELECT role FROM userinfo WHERE username = ?";
                    PreparedStatement statement = con.prepareStatement(query);
                    statement.setString(1, enterUsername.getText());
                    ResultSet rs = statement.executeQuery();
                    String result = null;

                    if (rs.next()) {
                        result = resultSet.getString("role");
                        rs.close();
                        statement.close();

                        if (result.equals("admin")) {
                            dispose();
                            new Admin().setVisible(true);
                        } else if (result.equals("doctor")) {
                            dispose();
                            new Doctor().setVisible(true);
                        } else if (result.equals("patient")) {
                            dispose();
                            new Patient().setVisible(true);
                        } else {
                            JOptionPane.showMessageDialog(null, "Error!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Your username and password does not match any data in our system.", "Sorry", JOptionPane.INFORMATION_MESSAGE);
            }

            pst.close();
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void validateTextfieldInput() {
        if ((enterUsername.getText().equals("Username") || enterUsername.getText().equals("")) && (enterPassword.getText().equals("Password") || enterPassword.getText().equals(""))) {
            JOptionPane.showMessageDialog(null, "Username and Password should not be empty", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (enterUsername.getText().equals("Username") || enterUsername.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Username should not be empty", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (enterPassword.getText().equals("Password") || enterPassword.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Password should not be empty", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            userSignIn();
        }
    }

    void promptPasswordChange(String username) {
        String newPassword = JOptionPane.showInputDialog(null, "Please type in your new password", "New Password", JOptionPane.QUESTION_MESSAGE);
        String confirmNewPassword = JOptionPane.showInputDialog(null, "Please confirm your new password", "Confirm Password", JOptionPane.QUESTION_MESSAGE);

        if (newPassword != null && confirmNewPassword != null && newPassword.equals(confirmNewPassword)) {
            try {
                String updateSql = "UPDATE userinfo SET password = ?, isActivated = ? WHERE username = ?";
                PreparedStatement updateStatement = con.prepareStatement(updateSql);
                updateStatement.setString(1, newPassword);
                updateStatement.setBoolean(2, true);
                updateStatement.setString(3, username);
                int rowsAffected = updateStatement.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Password updated successfully.\nPlease use new password to log in", "Success", JOptionPane.INFORMATION_MESSAGE);
                    enterPassword.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to update password.", "Error", JOptionPane.ERROR_MESSAGE);
                }

                updateStatement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Passwords do not match. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    void focusOn(JTextField textfield, String intext) {
        if (textfield.getText().equals(intext)) {
            textfield.setText("");
            textfield.setForeground(black);
            textfield.setHorizontalAlignment(LEFT);
        }
        textfield.selectAll();
    }

    void focusOff(JTextField textfield, String intext) {
        if (textfield.getText().isEmpty()) {
            textfield.setText(intext);
            textfield.setForeground(gray);
            textfield.setHorizontalAlignment(CENTER);
        }
    }

    void mouseEnter(JButton button) {
        button.setContentAreaFilled(true);
    }

    void mouseExit(JButton button) {
        button.setContentAreaFilled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        logInPanel = new javax.swing.JPanel();
        exit = new javax.swing.JButton();
        minimized = new javax.swing.JButton();
        enterUsername = new javax.swing.JTextField();
        enterPassword = new javax.swing.JPasswordField();
        forgotPassword = new javax.swing.JLabel();
        signIn = new javax.swing.JButton();
        showPassword = new javax.swing.JCheckBox();
        frameDrag = new javax.swing.JLabel();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(500, 600));
        setMinimumSize(new java.awt.Dimension(500, 600));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(500, 600));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        logInPanel.setBackground(new java.awt.Color(255, 204, 204));
        logInPanel.setMaximumSize(new java.awt.Dimension(500, 600));
        logInPanel.setMinimumSize(new java.awt.Dimension(500, 600));
        logInPanel.setOpaque(false);

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

        enterUsername.setFont(new java.awt.Font("Quicksand", 0, 16)); // NOI18N
        enterUsername.setForeground(new java.awt.Color(153, 153, 153));
        enterUsername.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        enterUsername.setText("Username");
        enterUsername.setMaximumSize(new java.awt.Dimension(250, 50));
        enterUsername.setMinimumSize(new java.awt.Dimension(250, 50));
        enterUsername.setPreferredSize(new java.awt.Dimension(250, 50));
        enterUsername.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                enterUsernameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                enterUsernameFocusLost(evt);
            }
        });
        enterUsername.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                enterUsernameKeyPressed(evt);
            }
        });

        enterPassword.setFont(new java.awt.Font("Quicksand", 0, 16)); // NOI18N
        enterPassword.setForeground(new java.awt.Color(153, 153, 153));
        enterPassword.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        enterPassword.setText("Password");
        enterPassword.setMaximumSize(new java.awt.Dimension(250, 50));
        enterPassword.setMinimumSize(new java.awt.Dimension(250, 50));
        enterPassword.setPreferredSize(new java.awt.Dimension(250, 50));
        enterPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                enterPasswordFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                enterPasswordFocusLost(evt);
            }
        });
        enterPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                enterPasswordKeyPressed(evt);
            }
        });

        forgotPassword.setFont(new java.awt.Font("Quicksand", 0, 14)); // NOI18N
        forgotPassword.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        forgotPassword.setText("Forgot Password?");
        forgotPassword.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        forgotPassword.setMaximumSize(new java.awt.Dimension(150, 30));
        forgotPassword.setMinimumSize(new java.awt.Dimension(150, 30));
        forgotPassword.setPreferredSize(new java.awt.Dimension(150, 30));
        forgotPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                forgotPasswordMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                forgotPasswordMouseExited(evt);
            }
        });

        signIn.setBackground(new java.awt.Color(147, 206, 255));
        signIn.setFont(new java.awt.Font("Quicksand", 0, 16)); // NOI18N
        signIn.setText("Sign In");
        signIn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        signIn.setContentAreaFilled(false);
        signIn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        signIn.setMaximumSize(new java.awt.Dimension(150, 30));
        signIn.setMinimumSize(new java.awt.Dimension(150, 30));
        signIn.setPreferredSize(new java.awt.Dimension(150, 30));
        signIn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                signInMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                signInMouseExited(evt);
            }
        });
        signIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signInActionPerformed(evt);
            }
        });

        showPassword.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        showPassword.setForeground(new java.awt.Color(51, 51, 51));
        showPassword.setText("Show Password");
        showPassword.setMaximumSize(new java.awt.Dimension(105, 50));
        showPassword.setMinimumSize(new java.awt.Dimension(105, 50));
        showPassword.setPreferredSize(new java.awt.Dimension(105, 50));
        showPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showPasswordActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout logInPanelLayout = new javax.swing.GroupLayout(logInPanel);
        logInPanel.setLayout(logInPanelLayout);
        logInPanelLayout.setHorizontalGroup(
            logInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(logInPanelLayout.createSequentialGroup()
                .addGroup(logInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, logInPanelLayout.createSequentialGroup()
                        .addContainerGap(428, Short.MAX_VALUE)
                        .addComponent(minimized, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(logInPanelLayout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addGroup(logInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(enterUsername, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(enterPassword, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(logInPanelLayout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addGroup(logInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(forgotPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(signIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(50, 50, 50)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(showPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        logInPanelLayout.setVerticalGroup(
            logInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(logInPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(logInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(minimized, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 200, Short.MAX_VALUE)
                .addComponent(enterUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(logInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(enterPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(showPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(forgotPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(signIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(162, 162, 162))
        );

        getContentPane().add(logInPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

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
        getContentPane().add(frameDrag, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, 600));

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/my/medata/images/MEDATA LOGIN.png"))); // NOI18N
        background.setToolTipText("");
        getContentPane().add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, 600));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void frameDragMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_frameDragMousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_frameDragMousePressed

    private void frameDragMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_frameDragMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_frameDragMouseDragged

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
        int exit = JOptionPane.showOptionDialog(null, "Are you sure you want to exit?",
                "Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        if (exit == 0) {//yes
            System.exit(0);
        }
    }//GEN-LAST:event_exitActionPerformed

    private void minimizedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minimizedActionPerformed
        setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_minimizedActionPerformed

    private void enterUsernameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_enterUsernameFocusGained
        focusOn(enterUsername, "Username");
    }//GEN-LAST:event_enterUsernameFocusGained

    private void enterUsernameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_enterUsernameFocusLost
        focusOff(enterUsername, "Username");
    }//GEN-LAST:event_enterUsernameFocusLost

    private void enterPasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_enterPasswordFocusGained
        focusOn(enterPassword, "Password");
        enterPassword.setEchoChar('\u2022');
        enterPassword.selectAll();
    }//GEN-LAST:event_enterPasswordFocusGained

    private void enterPasswordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_enterPasswordFocusLost
        if (enterPassword.getText().isEmpty()) {
            enterPassword.setText("Password");
            enterPassword.setForeground(gray);
            enterPassword.setHorizontalAlignment(CENTER);
            enterPassword.setEchoChar((char) 0);
        }
    }//GEN-LAST:event_enterPasswordFocusLost

    private void forgotPasswordMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_forgotPasswordMouseEntered
        forgotPassword.setForeground(blue);
        Font font = forgotPassword.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_LOW_ONE_PIXEL);
        forgotPassword.setFont(font.deriveFont(attributes));
    }//GEN-LAST:event_forgotPasswordMouseEntered

    private void forgotPasswordMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_forgotPasswordMouseExited
        forgotPassword.setForeground(black);
        Font font = forgotPassword.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, null);
        forgotPassword.setFont(font.deriveFont(attributes));
    }//GEN-LAST:event_forgotPasswordMouseExited

    private void signInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signInActionPerformed
        validateTextfieldInput();
    }//GEN-LAST:event_signInActionPerformed

    private void enterUsernameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_enterUsernameKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            validateTextfieldInput();
        }
    }//GEN-LAST:event_enterUsernameKeyPressed

    private void enterPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_enterPasswordKeyPressed
        if(showPassword.isSelected()){
            enterPassword.setEchoChar((char)0);
        }
        
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            validateTextfieldInput();
        }
    }//GEN-LAST:event_enterPasswordKeyPressed

    private void exitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMouseEntered
        mouseEnter(exit);
    }//GEN-LAST:event_exitMouseEntered

    private void minimizedMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizedMouseEntered
        mouseEnter(minimized);
    }//GEN-LAST:event_minimizedMouseEntered

    private void signInMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_signInMouseEntered
        mouseEnter(signIn);
    }//GEN-LAST:event_signInMouseEntered

    private void exitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMouseExited
        mouseExit(exit);
    }//GEN-LAST:event_exitMouseExited

    private void minimizedMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizedMouseExited
        mouseExit(minimized);
    }//GEN-LAST:event_minimizedMouseExited

    private void signInMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_signInMouseExited
        mouseExit(signIn);
    }//GEN-LAST:event_signInMouseExited

    private void showPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showPasswordActionPerformed
        if (enterPassword.getText().equals("Password")) {
            //Empty || Null
        } else {
            if (showPassword.isSelected()) {
                enterPassword.setEchoChar((char) 0);
            } else {
                enterPassword.setEchoChar('\u2022');
            }
        }
    }//GEN-LAST:event_showPasswordActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(MEDATA_Application.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(MEDATA_Application.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(MEDATA_Application.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(MEDATA_Application.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//
        FlatLightLaf.setup();
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MEDATA_Application().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JPasswordField enterPassword;
    private javax.swing.JTextField enterUsername;
    private javax.swing.JButton exit;
    private javax.swing.JLabel forgotPassword;
    private javax.swing.JLabel frameDrag;
    private javax.swing.JPanel logInPanel;
    private javax.swing.JButton minimized;
    private javax.swing.JCheckBox showPassword;
    private javax.swing.JButton signIn;
    // End of variables declaration//GEN-END:variables
}
