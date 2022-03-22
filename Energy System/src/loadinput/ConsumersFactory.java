package loadinput;

import energysystem.Consumers;

public final class ConsumersFactory {
    private static ConsumersFactory consumersFactory = null;
    private ConsumersFactory() { }
    /**
     * Metoda folosita in aplicarea pattern-ului Singleton.
     */
    public static ConsumersFactory getInstance() {
        if (consumersFactory == null) {
            consumersFactory = new ConsumersFactory();
        }
        return consumersFactory;
    }
    /**
     * Metoda folosita in aplicarea pattern-ului Factory.
     */
    public Consumers createConsumer(final String consumerType,
                                    final Integer id,
                                    final Integer initialBudget,
                                    final Integer monthlyIncome) {
        if (ConsumersAndDistributorsType.BASIC.toString().equals(consumerType)) {
            return new Consumers(id, initialBudget, monthlyIncome);
        } else {
            return new Consumers(id, initialBudget, monthlyIncome);
        }
    }
}
