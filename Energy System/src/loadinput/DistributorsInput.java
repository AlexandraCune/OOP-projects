package loadinput;

public class DistributorsInput {
    private Integer id;
    private Integer contractLength;
    private Integer initialBudget;
    private Integer initialInfrastructureCost;
    private Integer energyNeededKW;
    private String producerStrategy;

    public final Integer getEnergyNeededKW() {
        return energyNeededKW;
    }

    public final void setEnergyNeededKW(final Integer energyNeededKW) {
        this.energyNeededKW = energyNeededKW;
    }

    public final String getProducerStrategy() {
        return producerStrategy;
    }

    public final void setProducerStrategy(final String producerStrategy) {
        this.producerStrategy = producerStrategy;
    }

    public final Integer getId() {
        return id;
    }

    public final void setId(final Integer id) {
        this.id = id;
    }

    public final Integer getContractLength() {
        return contractLength;
    }

    public final void setContractLength(final Integer contractLength) {
        this.contractLength = contractLength;
    }

    public final Integer getInitialBudget() {
        return initialBudget;
    }

    public final void setInitialBudget(final Integer initialBudget) {
        this.initialBudget = initialBudget;
    }

    public final Integer getInitialInfrastructureCost() {
        return initialInfrastructureCost;
    }

    public final void setInitialInfrastructureCost(final Integer initialInfrastructureCost) {
        this.initialInfrastructureCost = initialInfrastructureCost;
    }

}
