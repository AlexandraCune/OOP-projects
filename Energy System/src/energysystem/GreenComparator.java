package energysystem;

import entities.EnergyType;

import java.util.Comparator;

public class GreenComparator implements Comparator<Producers> {
    @Override
    public final int compare(final Producers p1, final Producers p2) {
        if (EnergyType.valueOf(p1.getEnergyType()).isRenewable()
                && EnergyType.valueOf(p2.getEnergyType()).isRenewable()) {
            int c = p1.getPriceKW().compareTo(p2.getPriceKW());
            if (c == 0) {
                return p2.getEnergyPerDistributor().compareTo(p1.getEnergyPerDistributor());
            } else {
                int c2 = p1.getPriceKW().compareTo(p2.getPriceKW());
                if (c2 == 0) {
                    return p1.getId().compareTo(p2.getId());
                }
                return p1.getPriceKW().compareTo(p2.getPriceKW());
            }
        } else {
            if (!EnergyType.valueOf(p1.getEnergyType()).isRenewable()
                    && !EnergyType.valueOf(p2.getEnergyType()).isRenewable()) {
                int c = p1.getPriceKW().compareTo(p2.getPriceKW());
                if (c == 0) {
                    return p2.getEnergyPerDistributor().compareTo(p1.getEnergyPerDistributor());
                } else {
                    int c2 = p1.getPriceKW().compareTo(p2.getPriceKW());
                    if (c2 == 0) {
                        return p1.getId().compareTo(p2.getId());
                    }
                    return p1.getPriceKW().compareTo(p2.getPriceKW());
                }
            }
            if (EnergyType.valueOf(p1.getEnergyType()).isRenewable()) {
                return -1; //este p1 mai mare
            } else {
                return 1; //este p2 mai mare
            }
        }
    }
}
