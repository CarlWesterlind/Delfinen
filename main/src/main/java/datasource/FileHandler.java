package datasource;

import domainmodel.CompetitiveSwimmer;
import domainmodel.Enum.Discipline;
import domainmodel.Member;
import domainmodel.Enum.MemberStatus;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {
   private static final String HEADER="id;name;birthdate;memberstatus;disciplines";
   private  final File file=new File("members.csv");


   public void Savememebers(ArrayList<Member>members) {
       try (PrintStream out = new PrintStream(file)) {
           out.println(HEADER);

           for (Member m : members) {
               String disciplines = "";
               if(m instanceof CompetitiveSwimmer swimmer){
                   ArrayList<String> names = new ArrayList<>();
                   for(Discipline discipline : swimmer.getDisciplines()){
                       names.add(discipline.name());
                   }
                   disciplines = String.join("|", names);
               }
               out.println(m.getId() + ";" + m.getName() + ";" + m.getBirthDate() + ";" + m.getMemberStatus()+ ";" + disciplines);
           }
       } catch (FileNotFoundException e) {
           throw new RuntimeException(e);
       }
   }
       public ArrayList<Member> LoadMember() {
           ArrayList<Member> members = new ArrayList<>();
           if (!file.exists()) return members;

           try (Scanner sc = new Scanner(file)) {
               sc.nextLine();
               while (sc.hasNextLine()) {
                   String line = sc.nextLine().trim();
                   String[] p = line.split(";", -1);
                   try {
                       int id = Integer.parseInt(p[0].trim());
                       String name = p[1].trim();
                       LocalDate birthDate = LocalDate.parse(p[2].trim());
                       MemberStatus status = MemberStatus.valueOf(p[3].trim());
                       String disciplinesText = p.length > 4 ? p[4].trim() : "";
                       if(disciplinesText.isEmpty()){
                        members.add(new Member(id, name, birthDate, status));
                       }else {
                           ArrayList<Discipline> disciplines = new ArrayList<>();
                           for(String part : disciplinesText.split("\\|")){
                               disciplines.add(Discipline.valueOf(part));
                           }
                           members.add(new CompetitiveSwimmer(id, name, birthDate, status, disciplines));
                       }
                   } catch (RuntimeException e) {
                       System.out.println(" wrong line added  : " + line);
                   }

               }


           } catch (FileNotFoundException e) {
               System.out.println("No Members found :" + e.getMessage());
           }
         return members;

       }

       }
