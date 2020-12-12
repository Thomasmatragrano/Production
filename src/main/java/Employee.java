

import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Represents an employee on the production line.
 * An employee can sign in using their name and a password.
 * @author Thomas Matragrano
 */
public class Employee {
    private final String name;
    private String email;
     String username;
     String password;

    /**
     * Sets this Employee's name and password.
     * @param name This Employee's first and last name.
     * @param password This Employee's password.
     */
    public Employee(String name, String password) {
        this.password = password;
        this.name = name;
        //Checking if name is valid
        if (checkName())
        //Setting the password
        isValidPassword();
        if (isValidPassword())
            this.password = password;
        else
            this.password = "pw";
        //Printing the reverse of This Employee's password
        System.out.println("Reverse of Password: "+reverseString(password));
    }

    /**
     * Gets the reverse of this Employee's password.
     * @param pw this Employee's password
     * @return the reverse of this Employee's password/
     */
    public String reverseString(String pw) {
        if (pw.isEmpty())
            return pw;
        return reverseString(pw.substring(1)) + pw.charAt(0);
    }

    /**
     * Sets this Employee's Email address from their name.
     */
    private void setEmail() {
        char space;
        //Looping through this Employee's name
        for (int i = 0; i < name.length(); i++) {
            space = name.charAt(i);
            //Setting the email based on this Employee's name
            if (space == ' ') {
                String firstName = name.substring(0, i).toLowerCase();
                String lastName = name.substring(i + 1).toLowerCase();
                String address = "@oracleacademy.Test";
                email = firstName + "." + lastName + address;
            }
        }
    }

    /**
     * Sets this Employee's username based on their name.
     */
    private void setUsername() {
        char space;
        //Looping through this Employee's name
        for (int i = 0; i < name.length(); i++) {
            space = name.charAt(i);
            //Setting the username based on this Employee's name
            if (space == ' ') {
                username = name.substring(0, 1).toLowerCase() + name.substring(i + 1).toLowerCase();
            }
        }
    }

    /**
     * Checks this Employee's name to see if it contains a space.
     * @return whether or not this Employee's name contains a space.
     */
    private boolean checkName() {
        char space;
        //Looping through this Employee's name
        for (int i = 0; i < name.length(); i++) {
            space = name.charAt(i);
            //Verifying this Employee's name contains a space
            if (space == ' ') {
                setUsername();
                setEmail();
                return true;
            } else username = "default";
            email = "user@oracleacademy.Test";
        }
        return false;
    }

    /**
     * Check if this Employee's password is valid.
     * @return whether or not this Employee's password is valid.
     */
    private boolean isValidPassword() {
        boolean hasSpecial = false;
        boolean hasCap = false;
        boolean hasLow = false;
        char c;
        //Looping through this Employee's password
        for (int i = 0; i < password.length(); i++) {
            c = password.charAt(i);
            //Checks if the character is uppercase
            if (Character.isUpperCase(c)) {
                hasCap = true;
            }
            //Checks if the character is lowercase
            if (Character.isLowerCase(c)) {
                hasLow = true;
            }
            Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(password);
            boolean check = m.find();
            //Checks if the character is special
            if (check) {
                hasSpecial = true;
            }
        }
        return hasCap && hasLow && hasSpecial;
    }

    /**
     * Gets a formatted String with this Employee's information.
     * @return a concatenated String for this Employee's information.
     */
    public String toString() {
        return "Employee Details\n"
                + "Name : "
                + name
                + "\nUsername : "
                + username
                + "\nEmail : "
                + email
                + "\nInitial Password : "
                + password;
    }
}

