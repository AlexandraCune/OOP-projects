package energysystem;

import loadoutput.MonthlyStats;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Producers extends Observable {
    private Integer id;
    private String energyType;
    private Integer maxDistributors;
    private Integer numberOfDistributors = 0;
    private Double priceKW;
    private Integer energyPerDistributor;
    private List<MonthlyStats> monthlyStats = new ArrayList<>();

    public Producers(final Integer id,
                     final String energyType,
                     final Integer maxDistributors,
                     final Double priceKW,
                     final Integer energyPerDistributor) {
        this.id = id;
        this.energyType = energyType;
        this.maxDistributors = maxDistributors;
        this.priceKW = priceKW;
        this.energyPerDistributor = energyPerDistributor;
    }

    public final Integer getNumberOfDistributors() {
        return numberOfDistributors;
    }

    public final void setNumberOfDistributors(final Integer numberOfDistributors) {
        this.numberOfDistributors = numberOfDistributors;
    }

    public final List<MonthlyStats> getMonthlyStats() {
        return monthlyStats;
    }

    public final void setMonthlyStats(final List<MonthlyStats> monthlyStats) {
        this.monthlyStats = monthlyStats;
    }

    public final Integer getId() {
        return id;
    }

    public final void setId(final Integer id) {
        this.id = id;
    }

    public final String getEnergyType() {
        return energyType;
    }

    public final void setEnergyType(final String energyType) {
        this.energyType = energyType;
    }

    public final Integer getMaxDistributors() {
        return maxDistributors;
    }

    public final void setMaxDistributors(final Integer maxDistributors) {
        this.maxDistributors = maxDistributors;
    }

    public final Double getPriceKW() {
        return priceKW;
    }

    public final void setPriceKW(final Double priceKW) {
        this.priceKW = priceKW;
    }

    public final Integer getEnergyPerDistributor() {
        return energyPerDistributor;
    }

    public final void setEnergyPerDistributor(final Integer energyPerDistributor) {
        this.energyPerDistributor = energyPerDistributor;
    }

    /**
     * Modifica energia oferita de producator unui distribuitor.
     *
     * @param newEnergyPerDistributor noua energie a distribuitorului.
     */
    public void changesEnergyPerDistributor(final Integer newEnergyPerDistributor) {
        this.setEnergyPerDistributor(newEnergyPerDistributor);
        setChanged();
        notifyObservers();
    }

}
