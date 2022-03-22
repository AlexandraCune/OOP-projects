package energysystem;

import loadinput.DistributorsInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Distributors implements Comparable<Distributors>, DistributorsInterface, Observer {
    private Integer contractLength;
    private List<Consumers> consumersList = new ArrayList<>();
    private List<Producers> producersList = new ArrayList<>();
    private Integer productionCost;
    private Integer distributorCost;
    private Integer contractCost;
    private Integer profit;
    private Integer infrastructureCost;
    private Integer id;
    private Integer initialBudget;
    private Boolean isBankrupt = false;
    private Integer initialInfrastructureCost;
    private Integer energyNeededKW;
    private Integer energyNeededKWCopy;
    // facem o copie a cantitatii de energie de care distribuitorul are
    // nevoie pentru ca vom face operatii cu ea si nu vrem sa pierdem
    // cantitatea initiala.
    private String producerStrategy;
    private Integer budget;
    private Boolean hasNoProducer = true;
    private static final Integer TEN = 10;
    private static final Double PERCENT = 0.2;


    public Distributors(final Integer id, final Integer initialBudget,
                        final Integer initialInfrastructureCost,
                        final Integer energyNeededKW,
                        final Integer contractLength,
                        final String producerStrategy) {
        this.contractLength = contractLength;
        this.initialInfrastructureCost = initialInfrastructureCost;
        this.energyNeededKW = energyNeededKW;
        this.setEnergyNeededKWCopy(energyNeededKW);
        this.id = id;
        this.initialBudget = initialBudget;
        this.producerStrategy = producerStrategy;
    }

    public final Integer getEnergyNeededKWCopy() {
        return energyNeededKWCopy;
    }

    public final void setEnergyNeededKWCopy(final Integer energyNeededKWCopy) {
        this.energyNeededKWCopy = energyNeededKWCopy;
    }

    public final Boolean getHasNoProducer() {
        return hasNoProducer;
    }

    public final void setHasNoProducer(final Boolean hasNoProducer) {
        this.hasNoProducer = hasNoProducer;
    }

    public final List<Producers> getProducersList() {
        return producersList;
    }

    public final void setProducersList(final List<Producers> producersList) {
        this.producersList = producersList;
    }

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

    public final Boolean getIsBankrupt() {
        return isBankrupt;
    }

    public final void setIsBankrupt(final Boolean isBankrupt) {
        this.isBankrupt = isBankrupt;
    }

    public final Integer getId() {
        return id;
    }

    public final void setId(final Integer id) {
        this.id = id;
    }

    public final Integer getInitialBudget() {
        return initialBudget;
    }

    public final void setInitialBudget(final Integer initialBudget) {
        this.initialBudget = initialBudget;
    }

    public final Integer getBudget() {
        return budget;
    }

    public final void setBudget(final Integer budget) {
        this.budget = budget;
    }

    public final List<Consumers> getConsumersList() {
        return consumersList;
    }

    public final void setConsumersList(final List<Consumers> consumersList) {
        this.consumersList = consumersList;
    }

    public final Integer getContractCost() {
        return contractCost;
    }

    public final void setContractCost(final Integer contractCost) {
        this.contractCost = contractCost;
    }

    public final Integer getProfit() {
        return profit;
    }

    public final void setProfit(final Integer profit) {
        this.profit = profit;
    }

    public final Integer getInfrastructureCost() {
        return infrastructureCost;
    }

    public final void setInfrastructureCost(final Integer infrastructureCost) {
        this.infrastructureCost = infrastructureCost;
    }

    public final Integer getProductionCost() {
        return productionCost;
    }

    public final void setProductionCost(final Integer productionCost) {
        this.productionCost = productionCost;
    }

    public final Integer getDistributorCost() {
        return distributorCost;
    }

    public final void setDistributorCost(final Integer distributorCost) {
        this.distributorCost = distributorCost;
    }


    public final Integer getContractLength() {
        return contractLength;
    }

    public final void setContractLength(final Integer contractLength) {
        this.contractLength = contractLength;
    }

    public final Integer getInitialInfrastructureCost() {
        return initialInfrastructureCost;
    }

    public final void setInitialInfrastructureCost(final Integer initialInfrastructureCost) {
        this.initialInfrastructureCost = initialInfrastructureCost;
    }

    /**
     *Calculeaza costul contractului in cazul in care distribuitorul nu are consumatori.
     */
    public  void calculateContractCostForNULL() {
        contractCost = infrastructureCost + productionCost + profit;
    }
    /**
     *Calculeaza costul contractului in cazul in care distribuitorul are consumatori.
     */
    public  void calculateContractCost() {
        contractCost = Math.toIntExact(Math.round(Math.floor(
                infrastructureCost / consumersList.size())
                + productionCost + profit));
    }
    /**
     *Calculeaza profitul distribuitorul intr-o luna.
     */
    public  void calculateProfit() {
        this.profit = Math.toIntExact(Math.round(Math.floor(PERCENT * productionCost)));
    }
    /**
     *Calculeaza cheltuielile distribuitorul intr-o luna.
     */
    public  void calculateDistributorCost() {
        distributorCost = infrastructureCost + productionCost * consumersList.size();
    }

    @Override
    public final int compareTo(final Distributors o) {
        return contractCost.compareTo(o.contractCost);
    }

    /**
     * Calculeaza costul de productie.
     */
    public void calculateProductionCost() {
        double cost = 0;
        for (Producers producers : producersList) {
            cost = cost + producers.getEnergyPerDistributor() * producers.getPriceKW();
        }
        productionCost = Math.toIntExact(Math.round(Math.floor(cost / TEN)));

    }

    /**
     * Anunta distribuitorul ca s-a facut update la unul din producatorii sai
     * si trebuie sa-si gaseasca un distribuitor nou tura viitoare.
     */
    @Override
    public void update(Observable o, Object arg) {
        //trebuie sa cautam producatori noi pentru acest distribuitor pentru ca
        //unul din producatorii sai a suferit schimbari
        //anuntam faptul ca distribuitorul trebuie sa-si caute
        //producatori noi luna viitoare
        setHasNoProducer(true);

    }

}
