package domainmodel;

import domainmodel.Enum.Discipline;
import domainmodel.Enum.MemberStatus;

import java.time.LocalDate;
import java.util.ArrayList;

public class CompetitiveSwimmer extends Member{
    private ArrayList<Discipline> disciplines;

    public CompetitiveSwimmer(int id, String name, LocalDate birthdate, MemberStatus memberstatus, ArrayList<Discipline> disciplines ){
       super(id,name,birthdate,memberstatus );
       if(disciplines.isEmpty()){
           throw new IllegalArgumentException("All competitive swimmers must have a discipline");
       }
       this.disciplines = new ArrayList<>(disciplines);

    }

    public ArrayList<Discipline> getDisciplines() {
        return new ArrayList<>(disciplines);
    }
}
