import java.util.*;

// Login class (includes logout functionality)
class Login implements CourseOperation {
    private Map<String, User> users;
    private User[] currentUser;
    private Scanner scanner;

    // Constructor that receives users map, current user, and scanner instance
    Login(Map<String, User> users, User[] currentUser, Scanner scanner) {
        this.users = users;
        this.currentUser = currentUser;
        this.scanner = scanner; // Assign the shared scanner instance
    }

    @Override
    public void execute() {
        // Prompt for username and read input
        if (currentUser[0] != null) 
        {
            System.out.println("You have been log in!");
            return;
        }
        System.out.print("Enter username: ");
        System.out.flush();  // Ensure output is displayed immediately
        String username = scanner.nextLine();

        // Prompt for password and read input
        System.out.print("Enter password: ");
        System.out.flush();  // Ensure output is displayed immediately
        String password = scanner.nextLine();

        // Validate credentials
        if (users.containsKey(username) && users.get(username).password.equals(password)) {
            currentUser[0] = users.get(username); // Set current user if login successful
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid credentials!");
        }
    }

    // Method to log out the current user
    public void logout() {
        if (currentUser[0] != null) {
            currentUser[0] = null;
            System.out.println("Logged out successfully.");
        } else {
            System.out.println("Please login first!");
        }
    }
}
