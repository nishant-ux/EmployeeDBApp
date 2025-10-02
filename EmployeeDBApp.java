import java.sql.*;
import java.util.Scanner;

public class EmployeeDBApp {
    private static final String URL = "jdbc:mysql://localhost:3306/employeedb"; 
    private static final String USER = "root";  
    private static final String PASSWORD = "password"; 

    private Connection conn;
    private Scanner scanner;

    public EmployeeDBApp() {
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            scanner = new Scanner(System.in);
            System.out.println("✅ Database Connected Successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addEmployee() {
        try {
            System.out.print("Enter Employee Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Employee Age: ");
            int age = scanner.nextInt();
            scanner.nextLine(); // consume newline
            System.out.print("Enter Employee Department: ");
            String dept = scanner.nextLine();

            String sql = "INSERT INTO employees (name, age, department) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.setString(3, dept);

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Employee added successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewEmployees() {
        try {
            String sql = "SELECT * FROM employees";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("\n--- Employee Records ---");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                        ", Name: " + rs.getString("name") +
                        ", Age: " + rs.getInt("age") +
                        ", Department: " + rs.getString("department"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateEmployee() {
        try {
            System.out.print("Enter Employee ID to Update: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter New Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter New Age: ");
            int age = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter New Department: ");
            String dept = scanner.nextLine();

            String sql = "UPDATE employees SET name=?, age=?, department=? WHERE id=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.setString(3, dept);
            pstmt.setInt(4, id);

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Employee updated successfully!");
            } else {
                System.out.println("⚠️ Employee not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteEmployee() {
        try {
            System.out.print("Enter Employee ID to Delete: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            String sql = "DELETE FROM employees WHERE id=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Employee deleted successfully!");
            } else {
                System.out.println("⚠️ Employee not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void menu() {
        while (true) {
            System.out.println("\n==== Employee Database Menu ====");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: addEmployee(); break;
                case 2: viewEmployees(); break;
                case 3: updateEmployee(); break;
                case 4: deleteEmployee(); break;
                case 5:
                    System.out.println("👋 Exiting...");
                    try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
                    return;
                default: System.out.println("❌ Invalid choice.");
            }
        }
    }

    public static void main(String[] args) {
        EmployeeDBApp app = new EmployeeDBApp();
        app.menu();
    }
}
