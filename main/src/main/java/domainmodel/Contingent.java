package domainmodel;

import domainmodel.Enum.AgeCategory;
import domainmodel.Enum.MemberStatus;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class Contingent{

    private Member member;
    private double amount;
    private LocalDate dueDate;
    private LocalDate paymentDate;

    public ArrayList<Contingent> contingents = new ArrayList<>();

    public Contingent(Member member){
        this.member = member;
        this.amount = calculateFee();
    }

    private double calculateFee(){

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


    public void showContingents() {

        for (Contingent fee : contingents) {
            Member member = fee.getMember();

            System.out.println(
                    "ID: " + member.getId() +
                            " | Name: " + member.getName() +
                            " | Annual fee: " + fee.getAmount() + " kr."
            );
        }
    }

    public double calculateTotalContingents() {
        double total = 0;

        for (Contingent fee : contingents) {
            total += fee.getAmount();
        }

        return total;
    }

    public void registerPayment() {
        this.paymentDate = LocalDate.now();
    }


}
