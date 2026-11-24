package domainmodel;


import domainmodel.Enum.MemberStatus;
import domainmodel.Enum.AgeCategory;

import java.time.LocalDate;
import java.util.Date;
import java.time.Period;

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
    public AgeCategory getAgeCategory(LocalDate today) {
        int age = Period.between(birthDate, today).getYears();

        if (age < 18){
            return AgeCategory.JUNIOR;
        } else if (age < 60) {
            return AgeCategory.SENIOR;
        } else{
            return AgeCategory.SENIOR_60_PLUS;
        }
    }
    public boolean isJunior(LocalDate today) {
        return getAgeCategory(today) == AgeCategory.JUNIOR;
    }

    @Override
    public String toString() {
        return "ID: " + Id + " | Name: " + Name + " | Born: " + birthDate + " | Status: " + memberStatus;
    }

}
