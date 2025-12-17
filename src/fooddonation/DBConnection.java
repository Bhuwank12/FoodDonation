package fooddonation;


import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/food_donation",
                    "root",
                    "bhuwan@2002"
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

