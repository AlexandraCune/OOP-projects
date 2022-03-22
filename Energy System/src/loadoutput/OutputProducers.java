package loadoutput;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonPropertyOrder({"id", "maxDistributors", "priceKW", "energyType",
        "energyPerDistributor", "monthlyStats"})
public class OutputProducers implements Comparable<OutputProducers> {
    private Integer id;
    private Integer maxDistributors;
    private Double priceKW;
    private String energyType;
    private Integer energyPerDistributor;
    private List<MonthlyStats> monthlyStats;

    public OutputProducers(final Integer id, final Integer maxDistributors,
                           final Double priceKW, final String energyType,
                           final Integer energyPerDistributor,
                           final List<MonthlyStats> monthlyStats) {
        this.id = id;
        this.maxDistributors = maxDistributors;
        this.priceKW = priceKW;
        this.energyType = energyType;
        this.energyPerDistributor = energyPerDistributor;
        this.monthlyStats = monthlyStats;
    }

    public final Integer getId() {
        return id;
    }

    public final void setId(final Integer id) {
        this.id = id;
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

    public final String getEnergyType() {
        return energyType;
    }

    public final void setEnergyType(final String energyType) {
        this.energyType = energyType;
    }

    public final Integer getEnergyPerDistributor() {
        return energyPerDistributor;
    }

    public final void setEnergyPerDistributor(final Integer energyPerDistributor) {
        this.energyPerDistributor = energyPerDistributor;
    }

    public final List<MonthlyStats> getMonthlyStats() {
        return monthlyStats;
    }

    public final void setMonthlyStats(final List<MonthlyStats> monthlyStats) {
        this.monthlyStats = monthlyStats;
    }

    @Override
    public final int compareTo(final OutputProducers o) {
        return id.compareTo(o.id);
    }
}
