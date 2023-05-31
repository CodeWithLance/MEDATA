package my.medata;

/*
 * Authors:
 * Arcega, Lance Angelo P.
 * Muñoz, Nathan Sheary G.
 * Pare, Neo Jezer A.
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


