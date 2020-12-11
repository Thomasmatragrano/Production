import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Employee {
    private String name;
     String username;
     String password;
    private String email;

    public Employee(String name, String password) {
        this.password = password;
        this.name = name;
        checkName();
        isValidPassword();
        if (isValidPassword() == true)
            this.password = password;
        else
            this.password = "pw";

    }

    public String reverseString(String pw) {


        if (pw.isEmpty())
            return pw;

        return reverseString(pw.substring(1)) + pw.charAt(0);
    }

    private void setEmail() {
        char space;
        for (int i = 0; i < name.length(); i++) {

            space = name.charAt(i);
            if (space == ' ') {
                String firstName = name.substring(0, i).toLowerCase();
                String lastName = name.substring(i + 1).toLowerCase();
                String address = "@oracleacademy.Test";
                email = firstName + "." + lastName + address;
            }
        }
    }

    private void setUsername() {
        char space;
        for (int i = 0; i < name.length(); i++) {

            space = name.charAt(i);
            if (space == ' ') {
                username = name.substring(0, 1).toLowerCase() + name.substring(i + 1).toLowerCase();
            }
        }
    }

    private boolean checkName() {
        String last;
        char space;
        for (int i = 0; i < name.length(); i++) {

            space = name.charAt(i);
            if (space == ' ') {
                setUsername();
                setEmail();
                return true;
            } else username = "default";
            email = "user@oracleacademy.Test";
        }

        return false;
    }

    private boolean isValidPassword() {
        Pattern regex = Pattern.compile("[$&+,:;=?@#|]");
        Matcher matcher = regex.matcher(password);
        boolean b = matcher.matches();
        boolean hasSpecial = false;
        boolean hasCap = false;
        boolean hasLow = false;
        char c;

        forloop:
        for (int i = 0; i < password.length(); i++) {
            c = password.charAt(i);
            if (Character.isUpperCase(c)) {
                hasCap = true;
            }
            if (Character.isLowerCase(c)) {
                hasLow = true;
            }
            Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(password);
            boolean check = m.find();
            if (check) {
                hasSpecial = true;
            }
        }
        if (hasCap && hasLow && hasSpecial) {
            return true;
        } else {
            return false;
        }
    }

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

