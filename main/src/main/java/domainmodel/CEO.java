
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
        fileHandler.Savememebers(members);
        return member;
    }

    public Member findMember(int id){
        for(Member member : members){
            if(member.getId() == id){
                return member;
            }
        }
        throw new IllegalArgumentException("Member id not found");
    }
    public void makeCompetitiveSwimmer(int id, ArrayList<Discipline> disciplines){
        Member member = findMember(id);
        if(member instanceof CompetitiveSwimmer){
            throw new IllegalArgumentException(member.getId() + " " + member.getName() + " is already a competition swimmer\n" );
        }
        CompetitiveSwimmer competitiveSwimmer = new CompetitiveSwimmer(member.getId(), member.getName(), member.getBirthDate(), MemberStatus.AKTIVE, disciplines);
        int index = members.indexOf(member);
        members.set(index, competitiveSwimmer);
        fileHandler.Savememebers(members);

    }
    public void removeCompetitiveSwimmer(int id, MemberStatus status){
        Member member = findMember(id);
        if(!(member instanceof CompetitiveSwimmer)){
            throw new IllegalArgumentException(member.getName() + " is not a competitive swimmer");
        }
        Member memberPlain = new Member(member.getId(), member.getName(), member.getBirthDate(), status);
        int index = members.indexOf(member);
        members.set(index, memberPlain);
        fileHandler.Savememebers(members);
    }

    public void changeStatus( int id, MemberStatus status){
        Member member = findMember(id);
        member.setStatus(status);
        fileHandler.Savememebers(members);
    }
}

