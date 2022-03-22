package loadoutput;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonPropertyOrder({"id", "energyNeededKW", "contractCost", "budget",
        "producerStrategy", "isBankrupt", "contracts"})
public class OutputDistributors implements Comparable<OutputDistributors> {
    private Integer id;
    private Integer energyNeededKW;
    private Integer contractCost;
    private Integer budget;
    private String producerStrategy;
    private Boolean isBankrupt;
    private List<Contracts> contracts;

    public OutputDistributors(final Integer id, final Integer energyNeededKW,
                              final Integer contractCost,
                              final Integer budget,
                              final String producerStrategy,
                              final Boolean isBankrupt, final List<Contracts> contracts) {
        this.id = id;
        this.energyNeededKW = energyNeededKW;
        this.contractCost = contractCost;
        this.budget = budget;
        this.producerStrategy = producerStrategy;
        this.isBankrupt = isBankrupt;
        this.contracts = contracts;
    }

    public final Integer getEnergyNeededKW() {
        return energyNeededKW;
    }

    public final void setEnergyNeededKW(final Integer energyNeededKW) {
        this.energyNeededKW = energyNeededKW;
    }

    public final Integer getContractCost() {
        return contractCost;
    }

    public final void setContractCost(final Integer contractCost) {
        this.contractCost = contractCost;
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

    public final Integer getBudget() {
        return budget;
    }

    public final void setBudget(final Integer budget) {
        this.budget = budget;
    }

    public final Boolean getIsBankrupt() {
        return isBankrupt;
    }

    public final void setIsBankrupt(final Boolean bankrupt) {
        isBankrupt = bankrupt;
    }

    public final List<Contracts> getContracts() {
        return contracts;
    }

    public final void setContracts(final List<Contracts> contracts) {
        this.contracts = contracts;
    }

    @Override
    public final int compareTo(final OutputDistributors o) {
        return id.compareTo(o.id);
    }
}
