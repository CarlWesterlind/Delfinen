package UI;

import domainmodel.CEO;
import domainmodel.Cashier;
import domainmodel.Trainer;

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
    private int CEO(){
        while(true){
            System.out.println("""
                    1. New member
                    2. Members list
                    3. 
                    0. Back to main menu  
                    """);
            int choice = readInt("Enter one of the following numbers");
            switch (choice){
                case 1 ->;
                case 2 ->;
                case 3 ->;
                case 8 -> startProgram();

            }
        }
    }

    private int Cashier(){
        System.out.println("""
                    1. List of members
                    2. Has paid
                    3. Missing payment""");

        int choice = readInt("Enter one of the numbers");
        switch (choice){
                case 1 ->;
                case 2 -> isPaid();
                case 3 ->;
                case 8 -> startProgram();
                default ->;
        };
        //lägg till if

    }
    private int Trainer(){
        System.out.println("""
           1. 
           """);

        int choice = readInt("Enter one of the numbers");
        switch (choice){

        }
    };

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
