package domainmodel;

import domainmodel.Enum.Discipline;
import domainmodel.Enum.MemberStatus;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CompetitiveSwimmerTest {
    @Test
    void swimmerWithoutDisciplineRejected(){
        assertThrows(IllegalArgumentException.class,() -> new CompetitiveSwimmer(2, "Sara", LocalDate.of(2003,2,12) ,MemberStatus.AKTIVE, new ArrayList<>()));
    }
    @Test
    void swimmerWithAllDisciplineIsAccepted (){
        ArrayList<Discipline> disciplines = new ArrayList<>();
        disciplines.add(Discipline.FREESTYLE);
        disciplines.add(Discipline.BACKSTROKE);
        disciplines.add(Discipline.BREASTSTROKE);
        disciplines.add(Discipline.BUTTERFLY);
        assertDoesNotThrow(() -> new CompetitiveSwimmer(2,"Sara", LocalDate.of(2003,2,12), MemberStatus.AKTIVE, disciplines));
    }

    @Test
    void swimmerWithOneDisciplineIsAccepted(){
        ArrayList<Discipline> disciplines = new ArrayList<>();
        disciplines.add(Discipline.FREESTYLE);
        assertDoesNotThrow(()-> new CompetitiveSwimmer(3, "Pelle", LocalDate.of(2002,3,12), MemberStatus.AKTIVE, disciplines));
    }



}