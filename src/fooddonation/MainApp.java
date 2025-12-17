package fooddonation;

import java.util.Scanner;

public class MainApp {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n=== FOOD DONATION SYSTEM ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    UserService.register();
                    break;
                case 2:
                    UserService.login();
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!!");
            }
        }
    }
}

