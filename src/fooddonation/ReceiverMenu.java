package fooddonation;

import java.sql.*;
import java.util.Scanner;

public class ReceiverMenu {

    static Scanner sc = new Scanner(System.in);

    public static void menu(int receiverId) throws Exception {

        while (true) {
            System.out.println("\n--- RECEIVER MENU ---");
            System.out.println("1. View Available Food");
            System.out.println("2. Request Food");
            System.out.println("3. Logout");

            int choice = sc.nextInt();

            if (choice == 1) viewFood();
            else if (choice == 2) requestFood();
            else return;
        }
    }

    static void viewFood() throws Exception {
        Connection con = DBConnection.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM donations WHERE status='AVAILABLE'");

        while (rs.next()) {
            System.out.println(
                    rs.getInt("donation_id") + " | " +
                            rs.getString("food_type") + " | " +
                            rs.getInt("quantity")
            );
        }
    }

    static void requestFood() throws Exception {
        System.out.print("Enter Donation ID: ");
        int id = sc.nextInt();

        Connection con = DBConnection.getConnection();
        String sql = "UPDATE donations SET status='REQUESTED' WHERE donation_id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();

        System.out.println("Food requested!");
    }
}

