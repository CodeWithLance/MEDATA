package my.medata;

import java.awt.Toolkit;
import javax.swing.JFrame;

/*
 * Authors:
 * Arcega, Lance Angelo P.
 * Muñoz, Nathan Sheary G.
 * Pare, Neo Jezer A.
 */
public class SetIcon {
    
    public static void SetIcon(JFrame frame) {
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(SetIcon.class.getResource("runtime.png")));
    }
    
}
