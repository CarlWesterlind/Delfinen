package domainmodel;

import java.util.ArrayList;

public class Cashier {

    ArrayList<Contingent> contingents;


    public void addContingent(Member member) {
        contingents.add(new Contingent(member));
    }

}
