package domainmodel;

import domainmodel.Enum.Discipline;
import domainmodel.Enum.MemberStatus;

import java.time.LocalDate;
import java.util.ArrayList;

public class CEO {

    public ArrayList<Member>members=new ArrayList<>();
    private int nextId=1;

    public Member addMember(String name, LocalDate birthDate){
        Member member=new Member(nextId,name,birthDate, MemberStatus.PENDING);
        nextId++;
        members.add(member);
        return member;
    }
    public ArrayList<Member>getMembers(){
        return  members;
    }

    public CompetitiveSwimmer addCompetitiveSwimmer(String name, LocalDate birthDate, ArrayList<Discipline> disciplines){
        CompetitiveSwimmer member=new CompetitiveSwimmer(nextId,name,birthDate, MemberStatus.AKTIVE, disciplines);
        nextId++;
        members.add(member);
        return member;
    }
}

