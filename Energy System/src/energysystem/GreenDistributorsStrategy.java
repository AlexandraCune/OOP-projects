package energysystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GreenDistributorsStrategy implements DistributorsStrategy {
    private List<Producers> producersList;
    private List<Producers> producersForDistributor = new ArrayList<>();
    private Distributors distributors;

    public GreenDistributorsStrategy(final List<Producers> producersList,
                                     final Distributors distributors) {
        this.producersList = producersList;
        this.distributors = distributors;
    }

    /**
     * Pentru distribuitorul dat se aleg unul sau mai multi producatori,
     * astfel incat sa i se ofere cantitatea de energie de care are nevoie.
     * <p>
     * Returneaza o lista de producatori, reprezentand producatorii distribuiorului dat.
     */
    @Override
    public List<Producers> pickProducers() {
        //se sorteaza producatorii dupa cum se sugereaza
        // la GREEN Strategy in enunt
        Collections.sort(producersList, new GreenComparator());
        for (Producers producer : producersList) {
            if (distributors.getProducersList().contains(producer)) {
                producer.setNumberOfDistributors(producer.getNumberOfDistributors() - 1);
                distributors.getProducersList().remove(producer);
            }
        }
        for (Producers producer : producersList) {
            if (producer.getNumberOfDistributors() < producer.getMaxDistributors()) {
                producer.setNumberOfDistributors(producer.getNumberOfDistributors() + 1);
                producersForDistributor.add(producer);
                distributors.setEnergyNeededKWCopy(distributors.getEnergyNeededKWCopy()
                        - producer.getEnergyPerDistributor());
                if (distributors.getEnergyNeededKWCopy() < 0) {
                    distributors.setEnergyNeededKWCopy(distributors.getEnergyNeededKW());
                    break;
                }
            }
        }
        return producersForDistributor;
    }
}
