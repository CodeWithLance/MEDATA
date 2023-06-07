/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package my.medata;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Neo
 */
public class Methods {
    
    public static void logout(JFrame frame) {
        
        int logOut = JOptionPane.showOptionDialog(null, "Are you sure you to log out?",
                "Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        if (logOut == 0) {//yes
            frame.dispose();
            new MEDATA_Application().setVisible(true);
        }

    }
    
}
