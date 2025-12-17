package fooddonation;

import java.sql.*;
import java.util.Scanner;

public class AdminMenu {

    static Scanner sc = new Scanner(System.in);

    public static void menu() throws Exception {

        while (true) {
            System.out.println("\n--- ADMIN MENU ---");
            System.out.println("1. View All Donations");
            System.out.println("2. Approve Requests");
            System.out.println("3. Logout");

            int choice = sc.nextInt();

            if (choice == 1) viewAll();
            else if (choice == 2) approve();
            else return;
        }
    }

    static void viewAll() throws Exception {
        Connection con = DBConnection.getConnection();
        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM donations");

        while (rs.next()) {
            System.out.println(
                    rs.getInt("donation_id") + " | " +
                            rs.getString("food_type") + " | " +
                            rs.getInt("quantity") + " | " +
                            rs.getString("status")
            );
        }
    }

    static void approve() throws Exception {
        System.out.print("Donation ID: ");
        int id = sc.nextInt();

        Connection con = DBConnection.getConnection();
        String sql = "UPDATE donations SET status='APPROVED' WHERE donation_id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();

        System.out.println("Donation approved!");
    }
}

