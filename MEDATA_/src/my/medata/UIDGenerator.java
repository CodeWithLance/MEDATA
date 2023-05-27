package my.medata;

/**
 *
 * @author Neo
 */
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class UIDGenerator {
    private Set<String> usedUIDs;
    private Random random;

    public UIDGenerator() {
        usedUIDs = new HashSet<>();
        random = new Random();
    }

    public String generateUID(String lastName, String firstName, Date dateOfBirth, String uid) {
        StringBuilder username = new StringBuilder();
       
        // Append the last name
        if (lastName != null && !lastName.isEmpty()) {
            username.append(lastName.toLowerCase());
        }

        // Get the first two letters of the first name
        if (firstName != null && !firstName.isEmpty()) {
            String[] nameComponents = firstName.split(" ");
            for (String component : nameComponents) {
                if (!component.isEmpty()) {
                    username.append(component.charAt(0));
                }
            }
        }
        // Append the day of the date of birth
        if (dateOfBirth != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd");
            String dayOfBirth = dateFormat.format(dateOfBirth);
            username.append("-").append(dayOfBirth);
        }
        
        //return username.toString().toLowerCase();
        do {
            uid = generateRandomUID();
        } while (usedUIDs.contains(uid));
        
        usedUIDs.add(uid);
        username.append(uid);
        //return uid;
        return username.toString().toLowerCase();
    }

    private String generateRandomUID() {
        // Modify this method according to your UID generation requirements
        int length = 5;
        StringBuilder uidBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            uidBuilder.append(random.nextInt(10));
        }
        return uidBuilder.toString();
    }
    
       
}

