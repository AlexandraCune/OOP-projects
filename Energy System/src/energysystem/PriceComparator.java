package energysystem;

import java.util.Comparator;

public class PriceComparator implements Comparator<Producers> {

    @Override
    public final int compare(final Producers p1, final Producers p2) {
        if (p1.getPriceKW().equals(p2.getPriceKW())) {
            return p2.getEnergyPerDistributor().compareTo(p1.getEnergyPerDistributor());
        } else {
            return p1.getPriceKW().compareTo(p2.getPriceKW());
        }
    }
}
