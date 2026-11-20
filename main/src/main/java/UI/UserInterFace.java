package UI;

import java.util.Scanner;

public class UserInterFace {
    Scanner scanner = new Scanner(System.in);

    public void startProgram() {
        boolean isRunning = true;
        System.out.println("Welcome to Delfinen\n");

        while (isRunning) {
            System.out.println("""
                    1. CEO
                    2. Cashier
                    3. Trainer
                    0. Stop\n""");


            int choice = readInt("Enter one of the numbers");
            switch (choice) {
                case 1 -> CEO();
                case 2 -> Cashier();
                case 3 -> Trainer();
                case 0 -> isRunning = false;
                default -> System.out.println("Unknown choice" + choice);
            }

        }


    }
    private void CEO(){
        boolean isRunning = true;
        while (isRunning){
            System.out.println("""
                    1. New member
                    2. Members list
                    3.
                    0. Back to main menu
                    """);
            int choice = readInt("Enter one of the numbers");
            switch (choice){
                case 1 ->
                case 2 ->
                case 3 ->
                case 0 -> isRunning = false;
                default -> System.out.println("Unknown choice" + choice);

            }
        }
    }

    private void Cashier(){
        boolean isRunning = true;
        while (isRunning){
        System.out.println("""
                    1. List of members
                    2. Has paid
                    3. Missing payment
                    0. Back to main menu
                    """);

        int choice = readInt("Enter one of the numbers");
        switch (choice) {
            case 1 ->
            case 2 ->
            case 3 ->
            case 0 -> isRunning = false;
            default -> System.out.println("Unknown choice" + choice);
        }
        }
        //lägg till if

    }
    private int Trainer(){
        boolean isRunning = true;
        while (isRunning){
        System.out.println("""
           1. Competitive junior
           2. Competitive senior
           3. Top 5 under every category
           0. Back to main menu
           """);

        int choice = readInt("Enter one of the numbers");
        switch (choice) {
            case 1 -> ;
            case 2 -> ;
            case 3 -> ;
            case 0 -> isRunning = false;
            default -> System.out.println("Unknown choice" + choice);
        }

        }
        //lägg till if
    }

    private int readInt(String prompt){
        while (true){
            System.out.println(prompt);
            String input = scanner.nextLine().trim();
            try{
                return Integer.parseInt(input);
            }catch (NumberFormatException e){
                System.out.println("\"" + input + "\" is not a number.");
            }
        }
    }


}
