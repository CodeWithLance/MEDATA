/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package my.medata;

/**
 *
 * @author Neo
 */
public class dataBox {
    private static String loggedInUser;

    public static void pushUserData(String user) {
        loggedInUser = user;
    }

    public static String pullUserData() {
        return loggedInUser;
    }
    
    
    
}


