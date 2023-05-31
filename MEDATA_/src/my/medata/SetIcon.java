/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package my.medata;

import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author Neo
 */
public class SetIcon {
    
    public static void SetIcon(JFrame frame) {
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(
            SetIcon.class.getResource("runtime.png")));
    }
    
}
