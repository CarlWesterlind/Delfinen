package UI;

import domainmodel.CEO;
import domainmodel.CompetitiveSwimmer;
import domainmodel.Enum.Discipline;
import domainmodel.Enum.MemberStatus;
import domainmodel.Member;
import domainmodel.Trainer;
import domainmodel.SwimTimeFormat;
import domainmodel.TrainingResult;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class UserInterFace {
    Scanner scanner = new Scanner(System.in);
    private CEO ceo = new CEO();
    private Trainer trainer = new Trainer();

    public void startProgram() {
        boolean isRunning = true;
        System.out.println("Welcome to Delfinen\n");

        while (isRunning) {
            System.out.println("""
                    1. CEO
                    2. Cashier
                    3. Trainer
                    0. Stop\n""");


            int choice = readInt();
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
                    1. Add New member.
                    2. Members list.
                    3. Add competitive swimmer.
                    4. Make existing member a competitive swimmer.
                    5. Remove a competitive swimmer.
                    6. Change Status.
                    0. Back to main menu.
                    """);
            int choice = readInt();
            switch (choice){
                case 1 -> addMemeber();
                case 2 -> getMemebers();
                case 3 -> addCompetitiveSwimmer();
                case 4 -> makeCompetitiveSwimmer();
                case 5 -> removeCompetitiveSwimmer();
                case 6 -> changeStatus();
                case 7 -> registerTrainingTime();
                case 0 -> isRunning = false;
                default -> System.out.println("Unknown choice" + choice);

            }
        }
    }
    private MemberStatus readStatus(){
        while (true){
            System.out.println("""
                    1. Active
                    2. Passive
                    3. Pending
                    """);
            int choice = readInt();
            switch (choice){
                case 1 -> {return MemberStatus.AKTIVE;}
                case 2 -> {return MemberStatus.PASSIVE;}
                case 3 -> {return MemberStatus.PENDING;}
                default -> System.out.println("Unknown choice " + choice);
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

        int choice = readInt();
        switch (choice) {
         //   case 1 ->
         //   case 2 ->
         //   case 3 ->
            case 0 -> isRunning = false;
            default -> System.out.println("Unknown choice" + choice);
        }
        }
        //lägg till if

    }
    private void Trainer() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("""
                    1. Competitive junior
                    2. Competitive senior
                    3. Top 5 under every category
                    4. Register Training time
                    5. Register Competition time
                    0. Back to main menu
                    """);

            int choice = readInt();
            switch (choice) {
                case 1 -> showJuniorMembers();
                case 2 -> showSeniorMembers();
                //    case 3 -> ;
                case 4 -> registerTrainingTime();
                case 5 -> registerCompetitionTime();
                case 0 -> isRunning = false;
                default -> System.out.println("Unknown choice" + choice);
            }

        }
        //lägg till if
    }
    private void showJuniorMembers() {
        System.out.println("Junior Team: ");
        showTeam(trainer.getJuniorMembers(ceo.getMembers(), LocalDate.now()));
        }


    private void showSeniorMembers() {
        System.out.println("Senior Team: ");
        showTeam(trainer.getSeniorMembers(ceo.getMembers(), LocalDate.now()));
        }

        private void showTeam(ArrayList<CompetitiveSwimmer> team){
        if(team.isEmpty()){
            System.out.println("There are no swimmers on this team.\n");
            return;
        }
        for(CompetitiveSwimmer swimmer : team){
            System.out.println(swimmer.getName() + " --- Disciplines: " + formatDisciplines(swimmer.getDisciplines()));
        }
            System.out.println();
    }

    private String formatDisciplines(ArrayList<Discipline> disciplines){
        ArrayList<String> names = new ArrayList<>();
        for (Discipline discipline : disciplines){
            names.add(discipline.toString().toLowerCase());
        }
        return String.join(", ", names);
    }

    private int readInt(){
           return readInt("Enter one of the numbers : ");
    }
    private int readInt(String prompt){
        while (true){
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            }catch (NumberFormatException e){
                System.out.println("\"" + input + "\" is not a number.");
            }
        }
    }


    private LocalDate readDate(String promt){
        DateTimeFormatter f= DateTimeFormatter.ofPattern("dd-MM-yyyy");
        while (true){
            System.out.print(promt);
            String input=scanner.nextLine().trim();
            try {
                return LocalDate.parse(input,f);
            }catch (DateTimeException e){
                System.out.println("\"" + input + "\" is not  date! : dd-MM-yyyy");
            }
        }
    }

     private void addMemeber(){
        System.out.print("Name: ");
        String name=scanner.nextLine().trim();
         LocalDate birthDate=readDate("Birthdate (dd-MM-yyyy): ");

         Member member=ceo.addMember(name,birthDate);

         System.out.println(member.getName() + " has been added as a member.");

     }
     private void addCompetitiveSwimmer(){
         System.out.print("Name: ");
         String name = scanner.nextLine().trim();
         LocalDate birthDate = readDate("Birthdate (dd-MM-yyyy): ");
         ArrayList<Discipline> disciplines = readDiscipline();
         try {
             CompetitiveSwimmer swimmer = ceo.addCompetitiveSwimmer(name, birthDate, disciplines );
             System.out.println(swimmer.getName() + " has been added as a competitive swimmer. \n");

         }catch (IllegalArgumentException e){
             System.out.println(e.getMessage());
         }
     }
     private void makeCompetitiveSwimmer(){
         int id = readInt("Member ID number: ");
         try {
             Member member = ceo.findMember(id);
             System.out.println("Selected: " + member.getName());
             ArrayList<Discipline> disciplines = readDiscipline();
             ceo.makeCompetitiveSwimmer(id, disciplines);
             System.out.println("Member " + id + " is now a competitive swimmer.\n");
         }catch (IllegalArgumentException e){
             System.out.println(e.getMessage());
         }
     }

     private void getMemebers(){
        if(ceo.getMembers().isEmpty()){
            System.out.println("No Memebers\n");
            return;
        }
        System.out.println("Members:");
        for (Member member: ceo.getMembers()){

            System.out.println(member);
        }
     }
     private ArrayList<Discipline> readDiscipline (){
        ArrayList<Discipline> disciplines = new ArrayList<>();
        boolean isRunning = true;
        while (isRunning){
            System.out.println("""
                    1. Butterfly
                    2. Freestyle
                    3. Backstroke
                    4. Breaststroke
                    0. Back to menu
                    """);
            int choice = readInt();
            switch (choice){
                case 1 -> disciplines.add(Discipline.BUTTERFLY);
                case 2 -> disciplines.add(Discipline.FREESTYLE);
                case 3 -> disciplines.add(Discipline.BACKSTROKE);
                case 4 -> disciplines.add(Discipline.BREASTSTROKE);
                case 0 -> isRunning = false;
                default -> System.out.println("Unknown choice" + choice);
            }
        }
        return disciplines;
     }
     private void removeCompetitiveSwimmer(){
        int id = readInt("Member ID number: ");
        try {
            Member member = ceo.findMember(id);
            System.out.println("Selected: " + member.getName());
            MemberStatus status = readStatus();
            ceo.removeCompetitiveSwimmer(id, status);
            System.out.println("Member " + id + " is no longer a competitive swimmer. \n");

        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
     }
     private void changeStatus(){
        int id = readInt("Member ID number: " );
        try {
            Member member = ceo.findMember(id);
            System.out.println("Selected: " + member.getName());
            MemberStatus status = readStatus();
            ceo.changeStatus(id, status);
            System.out.println("Member " + id + " is now " + status + ". \n");
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
     }

     private void registerTrainingTime(){
         CompetitiveSwimmer swimmer = chooseCompetitiveSwimmer();
         if(swimmer == null){
             return;
         }

         Discipline discipline = readOneDiscipline();
         int time = readTime();
         LocalDate date = readDate("Date (dd-MM-yyyy): ");

         try {
             TrainingResult oldBest = swimmer.getBestTrainingResults(discipline);
             boolean isNewBest = swimmer.registerTrainingTime(discipline, time, date);

             if (isNewBest) {
                 System.out.println("New best time for " + swimmer.getName() + ": " + SwimTimeFormat.format(time) + "\n");
             } else {
                 System.out.println("Not better than the current best time "
                         + SwimTimeFormat.format(oldBest.getTime()) + " (" + oldBest.getDate() + "). The old time is kept.\n");
             }
         } catch (IllegalArgumentException e) {
             System.out.println(e.getMessage() + "\n");
         }
     }

     private Discipline readOneDiscipline(){
             while (true){
                 System.out.println("""
                         Discipline: 
                         1. Butterfly
                         2. Freestyle
                         3. Backstroke
                         4. Breaststroke
                         """);
                 int choice = readInt();
                 Discipline discipline = switch (choice){
                     case 1 -> Discipline.BUTTERFLY;
                     case 2 -> Discipline.FREESTYLE;
                     case 3 -> Discipline.BACKSTROKE;
                     case 4 -> Discipline.BREASTSTROKE;
                     default -> null;
                 };
                 if(discipline != null){
                     return discipline;
                 }
                 System.out.println("Unknown choice " + choice);
             }
         }

         private int readTime(){
        while (true){
            System.out.println("Time (minutes:seconds.hundredths, e.g. 1:05.32: ");
            String input = scanner.nextLine().trim();
            try {
                return SwimTimeFormat.parse(input);
            } catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
     }

    private CompetitiveSwimmer chooseCompetitiveSwimmer() {
        System.out.println("Enter the swimmer's ID or name:");
        String input = scanner.nextLine().trim();

        if (input.isEmpty()) {
            System.out.println("You didn't enter anything.\n");
            return null;
        }

        try {
            int id = Integer.parseInt(input);
            CompetitiveSwimmer swimmer = trainer.findCompetetiveSwimmer(ceo.getMembers(), id);
            if (swimmer == null) {
                System.out.println("No competitive swimmer with ID " + id + "\n");
            }
            return swimmer;
        } catch (NumberFormatException e) {

        }

        ArrayList<CompetitiveSwimmer> matches = trainer.findCompetitiveSwimmersByName(ceo.getMembers(), input);

        if (matches.isEmpty()) {
            System.out.println("No competitive swimmer found with the name \"" + input + "\"\n");
            return null;
        }

        if (matches.size() == 1) {
            return matches.get(0);
        }

        System.out.println("More than one swimmer matches:");
        for (CompetitiveSwimmer swimmer : matches) {
            System.out.println("ID: " + swimmer.getId() + " | Name: " + swimmer.getName());
        }
        System.out.println("Enter the ID of the swimmer:");
        int id = readInt();

        for (CompetitiveSwimmer swimmer : matches) {
            if (swimmer.getId() == id) {
                return swimmer;
            }
        }
        System.out.println("That ID is not in the list.\n");
        return null;
    }

     public void registerCompetitionTime(){
        CompetitiveSwimmer swimmer= chooseCompetitiveSwimmer();{
            if(swimmer == null){
                return;
            }

            Discipline discipline = readOneDiscipline();
            if(!swimmer.isActiveIn(discipline)){
                System.out.println(swimmer.getName() + " is not active in " + discipline + "\n");
                return;
            }

            String meetName = readMeetName();
            LocalDate date = readDate("The date of meet (dd--MM--YYYY");
            int placement = readPlacement();
            int time = readTime();

             try {
                 swimmer.addCompetitionResult(meetName, date, discipline, placement, time);
                 System.out.println("Result registered for " + swimmer.getName() + ": " + meetName
                         + " | " + discipline + " | Place " + placement
                         + " | " + SwimTimeFormat.format(time) + "\n");
             } catch (IllegalArgumentException e) {
                 System.out.println(e.getMessage() + "\n");
             }
         }
     }

     private String readMeetName(){
        while(true){
            System.out.println("The name of the meet:");
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()){
                return input;
            }
            System.out.println("The meet must have a name");
        }
     }

     private int readPlacement(){
        while(true){
            System.out.println("Placement (1, 2, 3...):");
            int placement = readInt();
            if(placement >= 1){
                return placement;
            }
            System.out.println("The placement must be at least 1 or higher");
        }
     }

}
