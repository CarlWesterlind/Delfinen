package domainmodel;

import java.time.LocalDate;

public class Contingent{

    private MemberStatus memberType;
    private AgeCategory ageCategory;
    private double amount;
    private boolean paid;
    private LocalDate dueDate;

    public Contingent(MemberStatus memberType, AgeCategory ageCategory){
        this.memberType = memberType;
        this.ageCategory = ageCategory;
        this.paid = false;

        this.amount = calculateFee();
    }

    private double calculateFee(){
        if (memberType == memberType.PASSIVE) {
            return 500;
        }
        return switch (ageCategory) {
            case JUNIOR -> 1000;
            case SENIOR -> 1600;
            case SENIOR_60_PLUS -> 1600 * 0.75;
        };
    }

    public boolean idPaid(){
        return paid;
    }

    public double getAmount(){
        return amount;
    }

    public void registerPayment(){
        paid = true;
    }



}
