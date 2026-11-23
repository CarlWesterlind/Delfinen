package domainmodel;


import domainmodel.Enum.MemberStatus;

import java.time.LocalDate;

public class Member {
    private int Id;
    private String Name;
    private LocalDate birthDate;
    private MemberStatus memberStatus ;



    public Member(int id, String name, LocalDate birthdate, MemberStatus memberstatus ){
        Id=id;
        Name=name;
        birthDate=birthdate;
        memberStatus=memberstatus;


    }

    public int getId(){return Id;}
    public  String getName(){return Name;}
    public LocalDate getBirthDate() { return birthDate; }
    public MemberStatus getMemberStatus() { return memberStatus; }

    @Override
    public String toString() {
        return "ID: " + Id + " | Name: " + Name + " | Born: " + birthDate + " | Status: " + memberStatus;
    }

}
