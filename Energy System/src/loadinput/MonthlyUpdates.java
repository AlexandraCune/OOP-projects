package loadinput;

import java.util.List;


public class MonthlyUpdates {
    private List<ConsumersInput> newConsumers;
    private List<DistributorChanges> distributorChanges;
    private List<ProducerChanges> producerChanges;

    public final List<ProducerChanges> getProducerChanges() {
        return producerChanges;
    }

    public final void setProducerChanges(final List<ProducerChanges> producerChanges) {
        this.producerChanges = producerChanges;
    }

    public final List<ConsumersInput> getNewConsumers() {
        return newConsumers;
    }

    public final void setNewConsumers(final List<ConsumersInput> newConsumers) {
        this.newConsumers = newConsumers;
    }

    public final List<DistributorChanges> getDistributorChanges() {
        return distributorChanges;
    }

    public final void setDistributorChanges(final List<DistributorChanges> distributorChanges) {
        this.distributorChanges = distributorChanges;
    }
}
