package fooddonation;

import java.sql.*;
import java.util.Scanner;

public class UserService {

    static Scanner sc = new Scanner(System.in);

    public static void register() {
        System.out.print("Name: ");
        String name = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        System.out.print("Role (DONOR/RECEIVER): ");
        String role = sc.nextLine().toUpperCase();

        try {
            Connection con = DBConnection.getConnection();
            String sql = "INSERT INTO users(name,email,password,role) VALUES(?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setString(4, role);
            ps.executeUpdate();
            System.out.println("Registration successful!");
        } catch (Exception e) {
            System.out.println("Registration failed!");
        }
    }

    public static void login() {
        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT * FROM users WHERE email=? AND password=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int userId = rs.getInt("id");
                String role = rs.getString("role");

                System.out.println("Login successful!");

                if (role.equals("DONOR"))
                    DonorMenu.menu(userId);
                else if (role.equals("RECEIVER"))
                    ReceiverMenu.menu(userId);
                else
                    AdminMenu.menu();
            } else {
                System.out.println("Invalid login!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
