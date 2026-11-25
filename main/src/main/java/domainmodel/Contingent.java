package domainmodel;

import domainmodel.Enum.AgeCategory;
import domainmodel.Enum.MemberStatus;

import java.time.LocalDate;
import java.time.Period;

public class Contingent {

    private Member member;
    private double amount;
    private LocalDate dueDate;
    private LocalDate paymentDate;

    public Contingent(Member member) {

        if (member == null) {
            throw new IllegalArgumentException(
                    "Member cannot be null"
            );
        }

        if (member.getBirthDate() == null) {
            throw new IllegalArgumentException(
                    "Birth date is missing"
            );
        }

        if (member.getBirthDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException(
                    "Invalid birth date"
            );
        }

        if (member.getMemberStatus() == null) {
            throw new IllegalArgumentException(
                    "Member status is missing"
            );
        }

        this.member = member;
        this.amount = calculateFee();
    }

    private double calculateFee() {

        int age = Period.between(
                member.getBirthDate(),
                LocalDate.now()
        ).getYears();

        AgeCategory ageCategory;

        if (age < 18) {
            ageCategory = AgeCategory.JUNIOR;
        } else if (age > 60) {
            ageCategory = AgeCategory.SENIOR_60_PLUS;
        } else {
            ageCategory = AgeCategory.SENIOR;
        }

        if (member.getMemberStatus() == MemberStatus.PASSIVE) {
            return 500;
        }

        return switch (ageCategory) {
            case JUNIOR -> 1000;
            case SENIOR -> 1600;
            case SENIOR_60_PLUS -> 1600 * 0.75;
        };
    }

    public Member getMember() {
        return member;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public boolean isPaid() {
        return paymentDate != null;
    }

    public void registerPayment() {
        this.paymentDate = LocalDate.now();
    }
}
