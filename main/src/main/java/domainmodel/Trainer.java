package domainmodel;

import java.time.LocalDate;
import java.util.ArrayList;

public class Trainer {
    public void showJuniorMembers(ArrayList<Member> members, LocalDate today){
        for (Member member : members) {
            if (member.isJunior(today)) {
                System.out.println(member.getName());
            }
        }
    }

    public void showSeniorMembers(ArrayList<Member> members, LocalDate today) {
        for (Member member : members) {
            if (!member.isJunior(today)) {
                System.out.println(member.getName());
            }
        }
    }

}
