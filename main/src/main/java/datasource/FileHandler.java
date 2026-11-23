package datasource;

import domainmodel.Member;
import domainmodel.Enum.MemberStatus;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {
   private static final String HEADER="id;name;birthdate;memerstatus";
   private  final File file=new File("members.csv");


   public void Savememebers(ArrayList<Member>members) {
       try (PrintStream out = new PrintStream(file)) {
           out.println(HEADER);

           for (Member m : members) {
               out.println(m.getId() + ";" + m.getName() + ";" + m.getBirthDate() + ";" + m.getMemberStatus());
           }
       } catch (FileNotFoundException e) {
           throw new RuntimeException(e);
       }
   }
       public ArrayList<Member> LoadMember() {
           ArrayList<Member> members = new ArrayList<>();
           if (!file.exists()) return members;

           try (Scanner sc = new Scanner(file)) {
               while (sc.hasNextLine()) {
                   String line = sc.nextLine().trim();
                   if (line.isEmpty() || line.equalsIgnoreCase(HEADER)) continue;
                   String[] p = line.split(";");
                   try {
                       members.add(new Member(
                               Integer.parseInt(p[0].trim()),
                               p[1].trim(),
                               LocalDate.parse(p[2].trim()),
                               MemberStatus.valueOf(p[3].trim())));
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
