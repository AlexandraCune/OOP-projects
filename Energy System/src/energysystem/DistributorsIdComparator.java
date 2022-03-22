package energysystem;

import java.util.Comparator;

public class DistributorsIdComparator implements Comparator<Distributors> {

    @Override
    public final int compare(final Distributors o1, final Distributors o2) {
        return o1.getId().compareTo(o2.getId());
    }
}
