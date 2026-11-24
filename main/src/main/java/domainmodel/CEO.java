
package domainmodel;

import datasource.FileHandler;
import domainmodel.Enum.Discipline;
import domainmodel.Enum.MemberStatus;

import java.time.LocalDate;
import java.util.ArrayList;

public class CEO {

    private FileHandler fileHandler=new FileHandler();
    public ArrayList<Member>members=new ArrayList<>();
    private int nextId;
    public CEO(){
        members= fileHandler.LoadMember();
        nextId=findNextId();
    }

    public Member addMember(String name, LocalDate birthDate){
        Member member=new Member(nextId,name,birthDate, MemberStatus.PENDING);
        nextId++;
        members.add(member);
        fileHandler.Savememebers(members);
        return member;
    }
    public int findNextId(){
        int highest=0;
        for(Member m:members){
            if(m.getId() >highest) highest=m.getId();

        }
        return highest + 1 ;

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

