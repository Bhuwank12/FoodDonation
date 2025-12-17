package fooddonation;

import java.sql.*;
import java.util.Scanner;

public class DonorMenu {

    static Scanner sc = new Scanner(System.in);

    public static void menu(int donorId) throws Exception {

        while (true) {
            System.out.println("\n--- DONOR MENU ---");
            System.out.println("1. Donate Food");
            System.out.println("2. View My Donations");
            System.out.println("3. Logout");

            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) donateFood(donorId);
            else if (choice == 2) viewDonations(donorId);
            else return;
        }
    }

    static void donateFood(int donorId) {
        System.out.print("Food Type: ");
        String food = sc.nextLine();

        System.out.print("Quantity: ");
        int qty = sc.nextInt();

        try {
            Connection con = DBConnection.getConnection();
            String sql = "INSERT INTO donations(donor_id,food_type,quantity,status) VALUES(?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, donorId);
            ps.setString(2, food);
            ps.setInt(3, qty);
            ps.setString(4, "AVAILABLE");
            ps.executeUpdate();
            System.out.println("Food donated!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void viewDonations(int donorId) throws Exception {
        Connection con = DBConnection.getConnection();
        String sql = "SELECT * FROM donations WHERE donor_id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, donorId);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            System.out.println(
                    rs.getInt("donation_id") + " | " +
                            rs.getString("food_type") + " | " +
                            rs.getInt("quantity") + " | " +
                            rs.getString("status")
            );
        }
    }
}

