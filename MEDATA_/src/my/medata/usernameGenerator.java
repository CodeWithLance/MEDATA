package my.medata;

import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * Authors:
 * Arcega, Lance Angelo P.
 * Muñoz, Nathan Sheary G.
 * Pare, Neo Jezer A.
 */

public class usernameGenerator {

    public String generateUsername(String lastName, String firstName, Date dateOfBirth) {
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
            username.append(".").append(dayOfBirth);
        }

        return username.toString().toLowerCase();
    }
}
