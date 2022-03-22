package energysystem;

import java.util.Comparator;

public class QuantityComparator implements Comparator<Producers> {

    @Override
    public final int compare(final Producers p1, final Producers p2) {
        return p2.getEnergyPerDistributor().compareTo(p1.getEnergyPerDistributor());
    }
}
