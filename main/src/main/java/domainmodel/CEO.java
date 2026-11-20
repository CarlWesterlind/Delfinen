package domainmodel;

import java.time.LocalDate;
import java.util.ArrayList;

public class CEO {

    public ArrayList<Member>members=new ArrayList<>();
    private int nextId=1;

    public Member addMember(String name, LocalDate birthDate){
        Member member=new Member(nextId,name,birthDate,MemberStatus.PENDING);
        nextId++;
        members.add(member);
        return member;
    }
    public ArrayList<Member>getMembers(){
        return  members;
    }
}
