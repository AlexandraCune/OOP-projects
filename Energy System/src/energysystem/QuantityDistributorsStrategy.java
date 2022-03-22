package energysystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuantityDistributorsStrategy implements DistributorsStrategy {
    private List<Producers> producersList;
    private List<Producers> producersForDistributor = new ArrayList<>();
    private Distributors distributors;

    public QuantityDistributorsStrategy(final List<Producers> producersList,
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
        //se sorteaza lista de producatori dupa cantitatea de energie oferita
        Collections.sort(producersList, new QuantityComparator());
        for (Producers producer : producersList) {
            if (distributors.getProducersList().contains(producer)) {
                //stergem producatorii din lista de producatori ai distribuitorului dat
                //modificam si numarul de distribuitori ai producatorului respectiv
                producer.setNumberOfDistributors(producer.getNumberOfDistributors() - 1);
                distributors.getProducersList().remove(producer);
            }
        }
        for (Producers producer : producersList) {
            //vedem daca producatorul mai accepta distribuitori
            if (producer.getNumberOfDistributors() < producer.getMaxDistributors()) {
                producer.setNumberOfDistributors(producer.getNumberOfDistributors() + 1);
                //adaugam producatorul in lista de producatori pe care o vom returna,
                //lista ce contine producatorii distribuitorului dat
                producersForDistributor.add(producer);
                distributors.setEnergyNeededKWCopy(distributors.getEnergyNeededKWCopy()
                        - producer.getEnergyPerDistributor());
                //vedem daca distribuitorul mai are nevoie de alti producatori pentru a
                //primi cantitatea de energie ceruta
                if (distributors.getEnergyNeededKWCopy() < 0) {
                    //daca energia e negativa inseamna ca distribuitorului i se acopera nevoia
                    //de energie
                    distributors.setEnergyNeededKWCopy(distributors.getEnergyNeededKW());
                    break;
                }
            }
        }
        return producersForDistributor;
    }
}
