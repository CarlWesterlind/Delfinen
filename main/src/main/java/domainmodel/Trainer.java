package domainmodel;

import java.time.LocalDate;
import java.util.ArrayList;

public class Trainer {

    public ArrayList<Member> getJuniorMembers(ArrayList<Member> members, LocalDate today) {
        ArrayList<Member> juniorMembers = new ArrayList<>();

        for (Member member : members) {
            if (member instanceof CompetitiveSwimmer && member.isJunior(today)) {
                juniorMembers.add(member);
            }
        }

        return juniorMembers;
    }

    public ArrayList<Member> getSeniorMembers(ArrayList<Member> members, LocalDate today) {
        ArrayList<Member> seniorMembers = new ArrayList<>();

        for (Member member : members) {
            if (member instanceof CompetitiveSwimmer && !member.isJunior(today)) {
                seniorMembers.add(member);
            }
        }

        return seniorMembers;
    }
}