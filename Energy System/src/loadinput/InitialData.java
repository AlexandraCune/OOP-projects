package loadinput;

import java.util.List;

public class InitialData {
    private List<ConsumersInput> consumers;
    private List<DistributorsInput> distributors;
    private List<ProducersInput> producers;

    public final List<ProducersInput> getProducers() {
        return producers;
    }

    public final void setProducers(final List<ProducersInput> producers) {
        this.producers = producers;
    }

    public final List<ConsumersInput> getConsumers() {
        return consumers;
    }

    public final void setConsumers(final List<ConsumersInput> consumers) {
        this.consumers = consumers;
    }

    public final List<DistributorsInput> getDistributors() {
        return distributors;
    }

    public final void setDistributors(final List<DistributorsInput> distributors) {
        this.distributors = distributors;
    }
}
