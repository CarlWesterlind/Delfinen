package domainmodel;

import domainmodel.Enum.MemberStatus;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class MemberTest {

    @Test
    void seventeenYearOldIsJunior() {
        LocalDate today = LocalDate.of(2026, 11, 24);

        Member member = new Member(
                1, "Peter",
                LocalDate.of(2009, 11, 24),
                MemberStatus.AKTIVE
        );

        assertTrue(member.isJunior(today));
    }

    @Test
    void eighteenYearOldIsSenior() {
        LocalDate today = LocalDate.of(2026, 11, 24);

        Member member = new Member(
                2, "Mikkel",
                LocalDate.of(2008, 11, 24),
                MemberStatus.AKTIVE
        );

        assertFalse(member.isJunior(today));
    }
}