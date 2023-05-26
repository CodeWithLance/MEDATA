package my.medata;

/*
 * Authors:
 * Arcega, Lance Angelo P.
 * Muñoz, Nathan Sheary G.
 * Pare, Neo Jezer A.
 */
public class passwordGenerator {

    public String generatePassword(String lastName) {
        StringBuilder password = new StringBuilder();

        if (lastName != null && !lastName.isEmpty()) {
            password.append(lastName.toLowerCase())
                    .append(".medata");
        }

        return password.toString();
    }
}
