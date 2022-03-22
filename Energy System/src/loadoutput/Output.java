package loadoutput;

import java.util.ArrayList;
import java.util.List;

public class Output {
    private List<OutputConsumers> consumers = new ArrayList<>();
    private List<OutputDistributors> distributors = new ArrayList<>();
    private List<OutputProducers> energyProducers = new ArrayList<>();

    public final List<OutputProducers> getEnergyProducers() {
        return energyProducers;
    }

    public final void setEnergyProducers(final List<OutputProducers> energyProducers) {
        this.energyProducers = energyProducers;
    }

    public final List<OutputConsumers> getConsumers() {
        return consumers;
    }

    public final void setConsumers(final List<OutputConsumers> consumers) {
        this.consumers = consumers;
    }

    public final List<OutputDistributors> getDistributors() {
        return distributors;
    }

    public final void setDistributors(final List<OutputDistributors> distributors) {
        this.distributors = distributors;
    }
}
