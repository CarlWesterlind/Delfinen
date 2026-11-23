package domainmodel;

import java.util.ArrayList;

public class Cashier {

    ArrayList<Contingent> contingents;


    public void addContingent(Member member) {
        contingents.add(new Contingent(member));
    }

    public void showPaymentDates() {

        for (Contingent fee : contingents) {

            Member member = fee.getMember();

            if (fee.isPaid()) {
                System.out.println(
                        member.getName() +
                                " | Paid on: " + fee.getPaymentDate()
                );
            } else {
                System.out.println(
                        member.getName() + " | Not paid"
                );
            }
        }
    }

}
