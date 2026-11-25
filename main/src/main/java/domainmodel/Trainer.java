package domainmodel;

import java.time.LocalDate;
import java.util.ArrayList;

public class Trainer {

    public ArrayList<CompetitiveSwimmer> getJuniorMembers(ArrayList<Member> members, LocalDate today) {
        ArrayList<CompetitiveSwimmer> juniorMembers = new ArrayList<>();

        for (Member member : members) {
            if (member instanceof CompetitiveSwimmer swimmer && member.isJunior(today)) {
                juniorMembers.add(swimmer);
            }
        }

        return juniorMembers;
    }

    public ArrayList<CompetitiveSwimmer> getSeniorMembers(ArrayList<Member> members, LocalDate today) {
        ArrayList<CompetitiveSwimmer> seniorMembers = new ArrayList<>();

        for (Member member : members) {
            if (member instanceof CompetitiveSwimmer swimmer && !member.isJunior(today)) {
                seniorMembers.add(swimmer);
            }
        }

        return seniorMembers;
    }

    public CompetitiveSwimmer findCompetetiveSwimmer(ArrayList<Member> members, int id){
        for(Member member : members){
            if(member instanceof CompetitiveSwimmer swimmer && swimmer.getId() == id){
                return swimmer;
            }
        }
        return null;
    }


}