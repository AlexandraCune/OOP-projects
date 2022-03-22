package loadinput;

import energysystem.Distributors;

public final class DistributorsFactory {
    private static DistributorsFactory distributorsFactory = null;
    private DistributorsFactory() { }

    /**
     * Metoda folosita in aplicarea pattern-ului Singleton.
     */
    public static DistributorsFactory getInstance() {
        if (distributorsFactory == null) {
            distributorsFactory = new DistributorsFactory();
        }
        return distributorsFactory;
    }
    /**
     * Metoda folosita in aplicarea pattern-ului Factory.
     */
    public Distributors createDistributors(final String distributorType,
                                           final Integer id, final Integer initialBudget,
                                           final Integer initialInfrastructureCost,
                                           final Integer energyNeededKW,
                                           final Integer contractLength,
                                           final String producerStrategy) {
        if (ConsumersAndDistributorsType.BASIC.toString().equals(distributorType)) {
            return new Distributors(id, initialBudget,
                    initialInfrastructureCost, energyNeededKW, contractLength, producerStrategy);
        } else {
            return new Distributors(id, initialBudget,
                    initialInfrastructureCost, energyNeededKW, contractLength, producerStrategy);
        }
    }
}
