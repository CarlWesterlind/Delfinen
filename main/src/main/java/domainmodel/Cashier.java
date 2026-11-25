package domainmodel;

import java.util.ArrayList;

public class Cashier {

    private ArrayList<Contingent> contingents = new ArrayList<>();

    public void addContingent(Member member) {
        try {
            contingents.add(new Contingent(member));
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
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
